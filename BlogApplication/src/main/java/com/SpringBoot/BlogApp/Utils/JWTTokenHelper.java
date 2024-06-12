 package com.SpringBoot.BlogApp.Utils;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTTokenHelper {

	public static final long JWT_TKN_VALIDITY = 5*60*60;
	
	private String screct = "HariPrabodham@369";
	
	//retrieve username from JWT token
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public <T>T getClaimFromToken(String token,Function<Claims, T> claimsResolver){
		final Claims claims = getAllClaimsFromToken(token); 
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(screct).parseClaimsJws(token).getBody(); 
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
}
