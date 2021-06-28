package com.chemcool.school.braingames.twoofoureight.web.api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Data
public class JWTParser {

    private final String SECRET_KEY = "926D96C90030DD58429D2751AC1BDBBC";

    public Jws<Claims> getUserOfToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }

    public String getEmailUserOfToken(String token) {
        Jws<Claims> jws = getUserOfToken(token);
        return jws.getBody().get("userEmail", String.class);
    }

    public String getIdUserOfToken(String token) {
        Jws<Claims> jws = getUserOfToken(token);
        System.out.println(jws.getBody());
        return jws.getBody().getSubject();
    }
}

