package com.example.JwtToken.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.JwtToken.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtUtils {

    private static String SECRET = "This_is_secret";
    private static long expiryDuration = 60*60;
    
    public String generateJwt(UserEntity userEntity){

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        //claims
        Claims claims = Jwts.claims()
        .setIssuer(userEntity.getId().toString())
        .setIssuedAt(issuedAt)
        .setExpiration(expiryAt);

        //optional claims
        claims.put("type", userEntity.getUserType());
        claims.put("name", userEntity.getName());
        claims.put("emailId", userEntity.getEmailId());

        //generate Jwt using claims

        return Jwts.builder()
        .setClaims(claims)
        .compact();
    }
}
