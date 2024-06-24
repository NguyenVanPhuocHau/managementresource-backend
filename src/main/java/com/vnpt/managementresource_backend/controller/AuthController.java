package com.vnpt.managementresource_backend.controller;

import com.vnpt.managementresource_backend.database.Role;
import com.vnpt.managementresource_backend.payload.request.LoginRequest;
import com.vnpt.managementresource_backend.payload.response.LoginResponse;
import com.vnpt.managementresource_backend.respository.RoleRespo;
import com.vnpt.managementresource_backend.respository.UserRespo;
import com.vnpt.managementresource_backend.security.jwt.JwtUtils;

import com.vnpt.managementresource_backend.security.services.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( "api/v1/auth")
public class AuthController {

    @Autowired
    UserRespo userRespo;
    @Autowired
    RoleRespo roleRespo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String> roleList = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(userDetails.getId(),userDetails.getFullName(),userDetails.getEmail(),roleList,jwt,"Bearer"));

    }

}
