package com.cabinetcomptable.security.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}") // 24h en millisecondes
    private long jwtExpiration;

    private final ObjectMapper mapper = new ObjectMapper();

    public String extractUsername(String token) {
        Map<String, Object> payload = decodePayload(token);
        Object sub = payload.get("sub");
        return sub != null ? sub.toString() : null;
    }

    public String extractRole(String token) {
        Map<String, Object> payload = decodePayload(token);
        Object role = payload.get("role");
        return role != null ? role.toString() : null;
    }

    public Long extractCompanyId(String token) {
        Map<String, Object> payload = decodePayload(token);
        Object val = payload.get("companyId");
        if (val instanceof Number) {
            return ((Number) val).longValue();
        }
        if (val instanceof String) {
            try {
                return Long.parseLong((String) val);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public String generateToken(UserDetails userDetails, String role, Long companyId) {
        return generateToken(new HashMap<>(), userDetails, role, companyId);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, String role, Long companyId) {
        extraClaims.put("role", role);
        if (companyId != null) {
            extraClaims.put("companyId", companyId);
        }

        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username != null && username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Map<String, Object> payload = decodePayload(token);
        Object expObj = payload.get("exp");
        if (expObj == null) return true;
        long expMillis;
        if (expObj instanceof Number) {
            long val = ((Number) expObj).longValue();
            // JWT 'exp' is typically seconds since epoch or milliseconds; handle both
            if (val > 1_000_000_000_000L) {
                expMillis = val; // already millis
            } else {
                expMillis = val * 1000L; // seconds -> millis
            }
        } else {
            try {
                long val = Long.parseLong(expObj.toString());
                if (val > 1_000_000_000_000L) {
                    expMillis = val;
                } else {
                    expMillis = val * 1000L;
                }
            } catch (NumberFormatException e) {
                return true;
            }
        }
        return Instant.ofEpochMilli(expMillis).isBefore(Instant.now());
    }

    private Map<String, Object> decodePayload(String token) {
        try {
            if (token == null) return Map.of();
            String[] parts = token.split("\\.");
            if (parts.length < 2) return Map.of();
            String payloadB64 = parts[1];
            byte[] decoded = Base64.getUrlDecoder().decode(payloadB64);
            return mapper.readValue(decoded, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            return Map.of();
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}