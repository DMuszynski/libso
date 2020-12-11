package pl.dmuszynski.libso.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.model.User;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;

@Component
public class JwtUtils {

    @Value("${libso.jwtSecret}")
    private String jwtSecret;

    public String generateJwtToken(Authentication authentication) {
        final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        final User userPrincipal = (User) authentication.getPrincipal();

        return JWT.create()
            .withIssuer("auth0")
            .withSubject(userPrincipal.getUsername())
            .sign(algorithm);
    }

    public String getUsernameFromJwtToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        try {
            final JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
            jwtVerifier.verify(authToken);
            return true;
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Unauthorized exception");
        }
    }
}