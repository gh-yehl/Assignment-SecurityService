package com.security.utils;

import com.security.model.UsersDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;

@ConfigurationProperties(prefix="jwt")
@Component
public class JWTHelper {

    //    @Autowired
//    private Environment env;
    private String secret;
    private String expire;
    private String header;

    /**
     * generate jwt token
     */
    public  String generateToken(UsersDTO usersDTO) {
        String userName = usersDTO.getUserName();
        String userType = usersDTO.getUserType();
        String token = Jwts.builder()
                .setSubject(userName)
                .claim("userName", userName)
                .claim("userType", userType)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +  Integer.parseInt(expire) * 1000))
                .compact();
        return token;
    }

    private Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    // check whether token is expired
    public boolean isTokenExpired(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public HashMap<String, String> validateToken(String token) throws ServletException {
        HashMap map = new HashMap();

        String userName;
        String userType;
        try {
            final Claims claims = getTokenBody(token);
            userName= (String) claims.get("userName");
            userType= (String) claims.get("userType");
        } catch (ExpiredJwtException e) {
            map.put("status","fail");
            map.put("message","Token is expired");
            return map;
        } catch (Exception e) {
            map.put("status","fail");
            map.put("message","Token has been manipulated");
            return map;
        }

        map.put("status","success");
        map.put("userName", userName);
        map.put("userType", userType);
        map.put("message","Token is valid");
        return map;
    }


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
