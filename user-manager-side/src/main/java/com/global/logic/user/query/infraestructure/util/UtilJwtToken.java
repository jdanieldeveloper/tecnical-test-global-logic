package com.global.logic.user.query.infraestructure.util;

import com.global.logic.user.command.infrastructure.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class UtilJwtToken {

    // for simplicity of the exercise the secret is here
    private static String SECRET_KEY = "secret";

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if(Objects.nonNull(userDetails.getAuthorities())) {
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                claims.put(authority.getAuthority(), authority.getAuthority());
            }
        }
        return createToken(claims, userDetails.getUsername());
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                    .setClaims(claims)
                        .setSubject(subject)
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public static boolean checkRole(Collection<? extends GrantedAuthority> authorities) {
        for(GrantedAuthority authority: authorities) {
            if(RoleEnum.getHashMapValues().containsKey(authority.getAuthority()))
                return true;
        }
        return false;
    }
}
