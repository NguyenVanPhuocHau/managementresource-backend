package com.vnpt.managementresource_backend.security.jwt;

import com.vnpt.managementresource_backend.security.services.UserDetailsImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;


@Component
public class JwtUtils {

    @Value("2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D")
    private String secret;
    @Value("3600000")
    private int expiration = 3600000;
    private static  final Logger logger =  LoggerFactory.getLogger(JwtUtils.class);

    public String generateJwtToken(Authentication authentication){
        UserDetailsImp userPrincial = (UserDetailsImp) authentication.getPrincipal();
        Date now = new Date();
        Date expriationDate = new Date(now.getTime() + expiration);
        return Jwts.builder().setSubject(userPrincial.getUsername())
                .setIssuedAt(now)
                .setExpiration(expriationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            System.out.println("Invalid JWT token: " + e.getMessage());
        }
        return false;
    }

    public String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")){
            return  authHeader.substring(7);
        }
        return  null;
    }

}
