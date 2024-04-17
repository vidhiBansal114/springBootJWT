package com.example.hotelApp.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationHelper {
	
	private String secret = "thisisacodingninjasdemonstrationforsecretkeyinspringsecurityjsonwebtokenauthentication";
	private static final long JWT_TOKEN_VALIDITY = 60*60;
	public String getUsernameFromToken(String token) throws Exception {
			Claims claims=getClaimsFromToken(token);
			return claims.getSubject();
		
		
	}
	public Claims getClaimsFromToken(String token) {
	    Claims claims;
	    try {
	        claims = Jwts.parser()
	                .setSigningKey(secret).build()
	                .parseClaimsJws(token)
	                .getBody();
	    } catch (Exception e) {
	        claims = null;
	    }
	
	    return claims;
	}
	
//	public Claims getClaimsFromToken(String jwt) throws Exception {
//		Claims claims;
//		System.out.print("ttt");
//
//	    try {
//	        claims =(Claims) Jwts.parser().setSigningKey(secret).build().parseClaimsJws(jwt);
//	    	
//
//
//
//	    } catch (Exception e) {
//	        claims = null;
//	    }
//	    return (Claims) claims;
//    }
	public Boolean isTokenExpired(String token) throws Exception {

		Claims claims=getClaimsFromToken(token);
		return 	claims.getExpiration().before(new Date());
	}
	public String generateToken(UserDetails userDetails) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder().setSubject(userDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
		.signWith(SignatureAlgorithm.HS512,secret)
		.compact();
	}
	

}
