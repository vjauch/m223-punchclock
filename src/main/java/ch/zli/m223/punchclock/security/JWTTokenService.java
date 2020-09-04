package ch.zli.m223.punchclock.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static ch.zli.m223.punchclock.security.SecurityConstants.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class JWTTokenService {

    @Value("${jwt.expiration-time}")
    private long jwtExpirationTime;

    public String createToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .sign(HMAC512(SECRET.getBytes()));
    }

    public String verifyToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }
}
