package com.ale94.digital_banking_api.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey; // firmar nuestro token

    @Value("${jwt.time.expiration}")
    private String timeExpiration; // tiempo de expiracion de token

    // Generar token de acceso
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username) // usuario que genera token
                .issuedAt(new Date(System.currentTimeMillis())) // fecha de creación del token
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(
                        timeExpiration))) // fecha de cuando expira el token
                .signWith(getSignatureKey(), Jwts.SIG.HS256) // firma del token y elegir algoritmo de encriptacion
                .compact();
    }

    // Validar el token de acceso
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignatureKey()) // verifica si la firma es correcta esta firma o es invalida
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (Exception e) {
            log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;
        }
    }

    // Obtener el username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo claims
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // Obtener todos los claims del token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignatureKey()) // verifica si la firma es correcta esta firma o es invalida
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Obtener firma del token
    public SecretKey getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // decodificar clave secreta
        return Keys.hmacShaKeyFor(keyBytes); // volver a encriptar pero algoritmo de encriptar p firmar token
    }
}
