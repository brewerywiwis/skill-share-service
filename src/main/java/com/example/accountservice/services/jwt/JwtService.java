package com.example.accountservice.services.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.accountservice.repositories.UserRepository;
import com.example.accountservice.services.user.UserService;
import com.example.accountservice.types.TokenPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final String issuerName = "skill share system";
    private final Algorithm algorithm;
    private final String secretKey;

    @Autowired
    public JwtService(UserRepository userRepository, UserService userService, @Value("${jwt.secret}") String secretKey) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.secretKey = secretKey;
        this.algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public String generateToken(UUID uid, String username, List<String> roles, HttpServletRequest request) {
        return JWT.create().withSubject("skill share access token")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 6000))
                .withIssuer(issuerName)
                .withClaim("uid", uid.toString())
                .withClaim("username", username)
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuerName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public TokenPayload decodeToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuerName)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String uid = decodedJWT.getClaim("uid").asString();
            String username = decodedJWT.getClaim("username").asString();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            if (uid == null ||  username == null || roles.length == 0) {
                throw new IllegalArgumentException();
            }
            return new TokenPayload(uid, username, List.of(roles));
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            return null;
        }
    }


}