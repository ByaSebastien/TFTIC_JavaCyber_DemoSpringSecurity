package be.tftic.spring.demo.api.utils;

import be.tftic.spring.demo.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private static final SecretKey SECRET_KEY = new SecretKeySpec("Yabadabadooooooooooooooooooooooooooooooooooooooooo".getBytes(StandardCharsets.UTF_8),"HmacSHA384");
    private static final int EXPIRE_AT = 86400;

    private final JwtBuilder builder;
    private final JwtParser parser;

    public JwtUtils() {
        this.builder = Jwts.builder().signWith(SECRET_KEY);
        this.parser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build();
    }

    public String generateToken(User user){
        return builder
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_AT * 1000L))
                .compact();
    }

    public Claims getClaims(String token){
        return parser.parseClaimsJws(token).getBody();
    }

    public long getId(String token){
        return getClaims(token).get("id", Long.class);
    }

    public String getUsername(String token){
        return getClaims(token).get("username", String.class);
    }

    public boolean isValid(String token){
        Claims claims = getClaims(token);
        Date now = new Date();

        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
