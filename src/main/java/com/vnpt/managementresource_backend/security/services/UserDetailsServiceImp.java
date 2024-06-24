package com.vnpt.managementresource_backend.security.services;

import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.respository.UserRespo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
   @Autowired
    UserRespo userRespo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRespo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email Not Found username: " + email));
        return UserDetailsImp.bulid(user);
    }
}
