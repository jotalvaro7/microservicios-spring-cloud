package org.personales.authservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.personales.authservice.model.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretkey;

    //Metodo para crear el token
    public String createToken(UserEntity user){
        Map<String, Object>  claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", user.getId());
        //TODO remove hardcode
        claims.put("twitter_account", "https://twitter.com/PersonalesOrg");
        Date now = new Date(); //fecha de expedicion del token
        Date exp = new Date(now.getTime() + 3600*1000); //fecha de expiracion del token
        return Jwts.builder()
                //TODO remove hardcode
                .setHeaderParam("company_name", "julio_compay")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact();
    }

    //Metodo para validar el token
    public boolean validatetoken(String token){
        try {
            Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Metodo para obtener el username del token
    public String getUsernameFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().getSubject();

        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
        }
    }

}
