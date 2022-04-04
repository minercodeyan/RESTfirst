package com.example.restfirst.security.jwt;

import com.example.restfirst.security.SecurityUser;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {


    private final String jwtSecret="MySecretKey";
    private int jwtExpirationMs=86400000;

    public String generateJwtToken(Authentication authentication) {

        SecurityUser userPrincipal = (SecurityUser) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println(1);
        } catch (MalformedJwtException e) {
            System.out.println(2);
        } catch (ExpiredJwtException e) {
            System.out.println(3);
        } catch (UnsupportedJwtException e) {
            System.out.println(4);
        } catch (IllegalArgumentException e) {
            System.out.println(5);
        }
        return false;
    }
}
