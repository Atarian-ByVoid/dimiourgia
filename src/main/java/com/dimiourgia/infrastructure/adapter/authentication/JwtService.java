package com.dimiourgia.infrastructure.adapter.authentication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dimiourgia.abstracts.exception.MsErrorException;
import com.dimiourgia.infrastructure.adapter.authentication.records.AccessToken;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private final static String SECRET = "InfinityVoid";
    private final static String ISSUER = "ATR-API";

    public AccessToken generateToken(AuthDetails principalDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            String token = JWT.create().withIssuer(ISSUER)
                    .withSubject(principalDetails.getEmail())
                    .withClaim("user_document", principalDetails.getDocument())
                    .withExpiresAt(genExpirationDate()).sign(algorithm);

            return new AccessToken(token);

        } catch (JWTCreationException exception) {
            throw new MsErrorException();
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new MsErrorException();
        }
    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public String getUserDocumentFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(ISSUER).build().verify(token);
            return decodedJWT.getClaim("user_document").asString();
        } catch (JWTVerificationException exception) {
            throw new MsErrorException();
        }
    }

}
