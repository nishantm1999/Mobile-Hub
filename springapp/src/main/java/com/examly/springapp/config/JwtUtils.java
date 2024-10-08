package com.examly.springapp.config;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
@Component
public class JwtUtils {
   
   //requirement :
   public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
 
   //    public static final long JWT_TOKEN_VALIDITY =  60;
   private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXC";
 
   //retrieve username from jwt token
   public String getUsernameFromToken(String token) {
       return getClaimFromToken(token, Claims::getSubject);
   }
 
   //retrieve expiration date from jwt token
   public Date getExpirationDateFromToken(String token) {
       return getClaimFromToken(token, Claims::getExpiration);
   }
 
   public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
       final Claims claims = getAllClaimsFromToken(token);
       return claimsResolver.apply(claims);
   }
 
   //for retrieveing any information from token we will need the secret key
   private Claims getAllClaimsFromToken(String token) {
       return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
   }
 
   //check if the token has expired
   private Boolean isTokenExpired(String token) {
       final Date expiration = getExpirationDateFromToken(token);
       return expiration.before(new Date());
   }
 
   //generate token for user
   public String generateToken(UserDetails userDetails,String role) {
       return doGenerateToken(role, userDetails.getUsername());
   }
 
   
   private String doGenerateToken(String role, String subject) {
       return Jwts.builder().setSubject(subject).claim("role",role).setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
               .signWith(SignatureAlgorithm.HS512, secret).compact();
   }
 
   //validate token
   public Boolean validateToken(String token, UserDetails userDetails) {
       final String username = getUsernameFromToken(token);
       return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }
 
}
 
