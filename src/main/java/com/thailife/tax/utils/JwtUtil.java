package com.thailife.tax.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.hibernate.boot.model.source.spi.SingularAttributeNature;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String SECRET_KEY = "secret";

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public String extractUserName(String token){
		return extractClaim(token,Claims::getSubject);
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public Boolean validateToken (String token,String userName){
		final String user = extractUserName(token);
		return (user.equals(userName) && !isTokenExpired(token));
	}
	
	private Boolean isTokenExpired(String token){
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token){
		return extractClaim(token,Claims::getExpiration);
	}

}
