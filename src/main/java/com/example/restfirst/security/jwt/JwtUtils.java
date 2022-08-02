package com.example.restfirst.security.jwt;

import com.example.restfirst.security.SecurityUser;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
public class JwtUtils {
    private String jwtSecret="MyComponent";
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
            System.out.println("SignatureException "+e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("MalformedJwtException " +e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException "+e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("UnsupportedJwtException "+e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException" + e.getMessage());
        }
        return false;
    }
}
