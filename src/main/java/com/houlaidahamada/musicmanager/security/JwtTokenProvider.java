package com.houlaidahamada.musicmanager.security;

import com.houlaidahamada.musicmanager.entity.InvalidatedToken;
import com.houlaidahamada.musicmanager.repository.InvalidatedTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long expirationMillis;
    private final long refreshExpirationMillis;
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    public JwtTokenProvider(@Value("${jwt.secret:changeitchangeitchangeitchangeit}") String secret,
                            @Value("${jwt.expiration:3600000}") long expirationMillis,
                            @Value("${jwt.refresh-expiration:604800000}") long refreshExpirationMillis,
                            InvalidatedTokenRepository invalidatedTokenRepository) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMillis = expirationMillis;
        this.refreshExpirationMillis = refreshExpirationMillis;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    public String createToken(String username, String email, Long userId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshExpirationMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            // Check if token is invalidated
            return invalidatedTokenRepository.findByToken(token).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public void invalidateToken(String token) {
        if (invalidatedTokenRepository.findByToken(token).isEmpty()) {
            InvalidatedToken invalidatedToken = new InvalidatedToken();
            invalidatedToken.setToken(token);
            invalidatedTokenRepository.save(invalidatedToken);
        }
    }
}
