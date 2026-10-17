package suivonline.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import suivonline.model.Utilisateur;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey cle;
    private final long expirationMs;

    // @Value lit les valeurs dans application.properties (etape 1)
    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-ms}") long expirationMs) {
        this.cle = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    // Cree un token pour cet utilisateur : contient son email + son role, signe avec la cle
    public String genererToken(Utilisateur u) {
        return Jwts.builder()
                .subject(u.getEmail())
                .claim("role", u.getRole().name())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(cle)
                .compact();
    }

    // Verifie la signature et la date d'expiration, puis renvoie le contenu du token.
    // Si le token est faux ou expire -> une exception est levee
    public Claims lireToken(String token) {
        return Jwts.parser().verifyWith(cle).build()
                .parseSignedClaims(token)
                .getPayload();
    }
}