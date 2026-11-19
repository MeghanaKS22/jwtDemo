package com.skyllx.jwtdemo.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.ssl.SslBundleProperties;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Date;

@Service
public class JWTService {

    private String secretkey = "MySecretKeyForDemoClass@1234567890#123456789";

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis() + 1000*60*60)))
                .signWith(Keys.hmacShaKeyFor(secretkey.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretkey.getBytes()))
                .build()
                .parseSignedClaims(token);
        return true;
    }

    public String extractUsername(String token) {
//        Claims claims = Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(secretkey.getBytes()))
//                .build().parseSignedClaims(token).getPayload();
//        return claims.getSubject();
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretkey.getBytes()))
                .build().parseSignedClaims(token).getPayload().getSubject();
    }
}







//vinayhp22
//springsecurity-repository
//8470877229-vinay sir
//principle - on what bases token generated(identity)
//claim-identity+exp+issue
//signature verification-stamp
//header-algorithms,type:JWT
//payload-identity+exp+issue date
//signature verification-stamp