package com.vnpt.managementresource_backend.security;


import com.vnpt.managementresource_backend.models.EPermission;
import com.vnpt.managementresource_backend.models.ERole;
import com.vnpt.managementresource_backend.security.jwt.JwtAuthenticationEntryPoint;
import com.vnpt.managementresource_backend.security.jwt.JwtTokenFilter;
import com.vnpt.managementresource_backend.security.jwt.JwtUtils;
import com.vnpt.managementresource_backend.security.services.UserDetailsImp;
import com.vnpt.managementresource_backend.security.services.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        return  new UserDetailsServiceImp();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return  new JwtTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationConfigurer(){
        DaoAuthenticationProvider authProivder = new DaoAuthenticationProvider();
        authProivder.setUserDetailsService(userDetailsService());
        authProivder.setPasswordEncoder(passwordEncoder());
        return authProivder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws  Exception{
        return  authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("api/v1/auth/**").permitAll()
                                .requestMatchers("api/v1/units/**").permitAll()
                                .requestMatchers("api/v1/users").permitAll()
                                .requestMatchers("api/v1/users/deleteUser/**").hasAuthority("REMOVE_USER")
                                .requestMatchers("api/v1/users/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        http.authenticationProvider(authenticationConfigurer());
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/images/**");
    }



}
