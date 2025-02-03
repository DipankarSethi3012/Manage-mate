package com.dipankar.Project.Management.System.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class JwtProvider {

    static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY_STRING.getBytes());
//    Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
//
//    String email = String.valueOf(claims.get("email"));
//    String authorities = String.valueOf(claims.get("authorities"));
    public static String generateToken(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorites = authentication.getAuthorities();
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", authentication.getName())
                .signWith(secretKey)
                .compact();
    }


    public static String getEmail(String jwt){
        System.out.println(jwt);
        jwt = jwt.substring(7);
        System.out.println(jwt);
        Claims claims = Jwts.parserBuilder().
                setSigningKey(secretKey)
                .build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }

}
