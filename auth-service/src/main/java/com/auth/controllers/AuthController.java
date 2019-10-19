package com.auth.controllers;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.Message;
import com.auth.model.User;
import com.auth.service.AuthService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Rest controller for authentication service.
 * JWT creation courtesy of https://developer.okta.com/blog/2018/10/31/jwts-with-java
 * @author rj
 *
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {
	
	private AuthService authServ;
	private static final String SECRET_KEY = System.getenv("proj3secret");
	
	@Autowired
	public AuthController(AuthService authServ) {
		this.authServ = authServ;
	}
	
	@GetMapping(value="/allUsers", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> allusers() {
		return authServ.getUsers();
	}
	
	/**
	 * For debug
	 * @param token
	 * @return
	 */
	@PostMapping(value="/decode", produces=MediaType.APPLICATION_JSON_VALUE)
	public Message decode(@RequestHeader("token") String token) {
		//String token = req.getHeader("Authorization");
		System.out.println("Token = :" + token);
		Claims res = decodeJWT(token);
		System.out.println("Decode success");
		System.out.println(res.get("username"));
		return new Message(res.toString());
	}
	
	@PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
	public Message login(@RequestBody User user) {
		User auth = authServ.doLogin(user);
		if (auth != null) {
			System.out.println(auth.getUsername());
			return new Message(createJWT("0", auth.getUsername(), 1_800_000l));
		} else {
			return null;
		}
	}
	
	@PostMapping(value="/register", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		User newUser = authServ.registerUser(user);
		if (newUser != null) 
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@HystrixCommand(fallbackMethod = "RJMethod", commandProperties = {
		       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	@PostMapping(value="updateUser", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User newUser = authServ.updateUser(user);
		if (newUser != null) 
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static String createJWT(String id, String issuer, long ttlMillis) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject("auth")
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}

}
