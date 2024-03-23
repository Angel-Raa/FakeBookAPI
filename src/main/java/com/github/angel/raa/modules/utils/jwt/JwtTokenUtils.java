package com.github.angel.raa.modules.utils.jwt;


import com.github.angel.raa.modules.persistence.modesl.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtils {
    @Value("${security.jwt.expiration-in-minutes}")
    private  Long EXPIRATION_IN_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    /**
     * Genera un token JWT para el usuario proporcionado.
     *
     * @param users El usuario para el que se genera el token.
     * @return El token JWT generado.
     */
    public String generateToken(Users users) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime());
        // Claims
        Map<String, Object> claims = generateClaims(users);
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .claims(claims)
                .subject(users.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
    }


    /**
     * Obtiene la clave de firma para la generación.
     *
     * @return La clave de firma.
     */
    @Contract(" -> new")
    private @NotNull SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    /**
     * Verifica si el token JWT proporcionado es válido para el usuario proporcionado.
     *
     * @param token       El token JWT a validar.
     * @param userDetails Los detalles del usuario para validar el token.
     * @return true si el token es válido, false en caso contrario.
     */
    public  boolean isTokenValid(String token, @NotNull UserDetails userDetails) {
        String username = getUsernameFromToke(token);
        Boolean isExpired = isTokenExpired(token);
        return username.equals(userDetails.getUsername()) && !isExpired;
    }
    /**
     * Obtiene la fecha de expiración del token JWT proporcionado.
     *
     * @param token El token JWT del que se extraerá la fecha de expiración.
     * @return La fecha de expiración del token.
     */
    private Date getExpirationFromToken(String token){
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si el token JWT proporcionado ha expirado.
     *
     * @param token El token JWT a verificar.
     * @return true si el token ha expirado, false en caso contrario.
     */
    @Contract(pure = true)
    private @NotNull Boolean isTokenExpired(String token) {
        return getExpirationFromToken(token).before(new Date());
    }
    /**
     * Obtiene un claim específico del token JWT.
     *
     * @param token          El token JWT del que se extraerá el claim.
     * @param claimsResolver El resolvedor de claims que se utilizará para obtener el claim específico.
     * @param <T>            El tipo de dato del claim.
     * @return El valor del claim especificado.
     */
    private <T> T getClaim(String token, @NotNull Function<Claims, T> claimsResolver) {
        Claims payload = Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(payload);

    }

    /**
     * Obtiene el nombre de usuario del token JWT proporcionado.
     *
     * @param token El token JWT del que se extraerá el nombre de usuario.
     * @return El nombre de usuario extraído del token.
     */
    public String getUsernameFromToke(String token) {
        return getClaim(token, Claims::getSubject);
    }
    /**
     * Genera los claims adicionales para el token JWT a partir de los datos del usuario.
     *
     * @param users El usuario para el que se generan los claims.
     * @return Un mapa que contiene los claims adicionales.
     */
    private @NotNull Map<String, Object> generateClaims(@NotNull Users users) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Username", users.getUsername());
        claims.put("Email", users.getEmail());
        claims.put("Role", users.getRole());
        claims.put("Authorities", users.getAuthorities());
        return claims;
    }



}
