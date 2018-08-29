package com.jdj.movie.utils;

import com.jdj.movie.model.ReturnModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class CreateTokenUtils {
    private static Logger logger = LoggerFactory.getLogger(CreateTokenUtils.class);

    /**
     *
     * @param request
     * @return s;
     * @throws Exception
     */
    public static ReturnModel getUserInfoByRequest(HttpServletRequest request,String base64Secret)throws Exception{
        Boolean b = null;
        String auth = request.getHeader("Authorization");
        if((auth != null) && (auth.length() > 6)){
            String HeadStr = auth.substring(0,5).toLowerCase();
            if(HeadStr.compareTo("basic") == 0){
                auth = auth.substring(6,auth.length());
                b = parseJWT(auth,base64Secret) != null?true:false;
            }
        }
        if(b == null){
            logger.error("getUserInfoByRequest:"+ auth);
            return new ReturnModel(-1,b);
        }
        return new ReturnModel(0,b);
    }

    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    public static String createJWT(String name,String audience, String issuer, long TTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("unique_name", name)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        return builder.compact();
    }

}
