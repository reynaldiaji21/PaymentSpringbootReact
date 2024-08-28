package com.rad.spring.react.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.rad.spring.react.security.service.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${jwt.expirationMs}")
	private int expirationMs;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		}catch (SignatureException e) {
			logger.error("Signature wrong :: " + e.getMessage());
		}catch (ExpiredJwtException e) {
			logger.error("Token is expired :: " + e.getMessage());
		}catch (MalformedJwtException e) {
			logger.error("Malformat Jwt :: " + e.getMessage());
		}catch (IllegalArgumentException e) {
			logger.error("Illegal Argument empty :: " + e.getMessage());
		}
		catch (Exception e) {
			logger.error("Invalid token :: " + e.getMessage());
			// TODO: handle exception
		}
		return false;
	}
    
    public String generateJwtToken(Authentication authentication) {
    	logger.info("Signature check secretkey as :: " + key);

    	UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
    	return Jwts.builder().setSubject((principal.getUsername()))
    			.setIssuedAt(new Date())
    			.setExpiration(new Date((new Date()).getTime() + expirationMs))
    			.signWith(SignatureAlgorithm.HS512, key)
    			.compact();
    }
    
    public String getUsernameFromJwtToken(String token) {
    	return Jwts.parser().setSigningKey(key)
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
    }
    
	
}
