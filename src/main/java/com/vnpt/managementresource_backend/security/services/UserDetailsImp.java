package com.vnpt.managementresource_backend.security.services;

import com.vnpt.managementresource_backend.database.Permission;
import com.vnpt.managementresource_backend.database.Role;
import com.vnpt.managementresource_backend.database.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImp implements UserDetails {

    private long id;
    private String fullName;
    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    private String role;
    private List<String> listPermissionString;

    public UserDetailsImp(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = getAuthorities(user);
        this.role = user.getRole().getName();
        this.listPermissionString = user.getRole().getPermissions().stream().map(Permission::getName).collect(Collectors.toList());

    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            user.getRole().getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });

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
