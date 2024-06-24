package com.vnpt.managementresource_backend.security.services;

import com.vnpt.managementresource_backend.database.Role;
import com.vnpt.managementresource_backend.database.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImp implements UserDetails {

    private long id;
    private String fullName;
    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

//    public UserDetailsImp(String fullName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.fullName = fullName;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
    public UserDetailsImp(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.password = user.getPassword();
//        this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        this.authorities = getAuthorities(user)  ;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission));
            });
        }
        return authorities;
    }



    public static UserDetails bulid(User user){
        return  new UserDetailsImp(user);
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


}
