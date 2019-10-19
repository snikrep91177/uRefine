package com.reviews.controllers;

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.reviews.model.Message;
import com.reviews.model.User;
import com.reviews.service.MailService;
import com.reviews.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	private UserService userServ;
	
	private MailService mailServ;
	
	private static final String SECRET_KEY = System.getenv("proj3secret");
	
	@Autowired
	public UserController(UserService userServ, MailService mailServ) {
		this.userServ = userServ;
		this.mailServ = mailServ;
	}
	
	@GetMapping(value="/getUsers", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers() {
		return this.userServ.getUsers();
	}
	
	@GetMapping(value="/getCurrentUser", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getCurrentUser(@RequestHeader("token") String token) {
		Claims res = decodeJWT(token);
		return userServ.selectByUname((String) res.get("iss"));
	}
	
	@HystrixCommand(fallbackMethod = "RJMethod", commandProperties = {
		       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	@PostMapping(value="/register", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity registerUser(@RequestBody User user) throws JsonProcessingException {
		RestTemplate temp = new RestTemplate();
		String url = "http://localhost:8765/auth-service/auth/register";
		AuthUser authUser = new AuthUser(user.getUsername(), user.getPassword());
		String body = new ObjectMapper().writeValueAsString(authUser);
		System.out.println(body);
		
		HttpHeaders head = new HttpHeaders();
		head.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> http = new HttpEntity<String>(body, head);
		
		ResponseEntity<User> response = temp.postForEntity(url, http, User.class);
		
		if (response.getStatusCode() != HttpStatus.OK)
			return new ResponseEntity<>(response.getStatusCode());
		
		User newAuthUser = response.getBody();
		
		user.setUserId(newAuthUser.getUserId());
		
		User newUser = userServ.register(user);
		
		if (newUser == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
	@HystrixCommand(fallbackMethod = "RJMethod", commandProperties = {
		       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50000") })
	@PostMapping(value="/resetPassword", produces=MediaType.APPLICATION_JSON_VALUE)
	public Message resetPassword(@RequestBody User user) throws JsonProcessingException {
		//Generate a temporary password
		String pword = generateAlphaNumericPassword();
		
		System.out.println(user);
		
		//Attempt to reset password on the auth server
		RestTemplate temp = new RestTemplate();
		String url = "http://localhost:8765/auth-service/auth/updateUser";
		AuthUser authUser = new AuthUser(user.getUsername(), pword);
		String body = new ObjectMapper().writeValueAsString(authUser);
		
		HttpHeaders head = new HttpHeaders();
		head.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> http = new HttpEntity<String>(body, head);
		
		ResponseEntity<User> response = temp.postForEntity(url, http, User.class);
		
		if (response.getStatusCode() != HttpStatus.OK)
			return new Message("Update failed");
		
		//Update the database to show the password has changed
		
		User updatedUser = userServ.updateUser(user, true);
		if (updatedUser == null)
			return new Message("User update failed");
		
		//Send the email informing the user of the password reset
		
		String senderEmailId = "noreply@URefine.com";
		String receiverEmailId = updatedUser.getEmail();
		String subject = "Request to reset password";
		String message = "Your password has been successfully reset, please log in using your temporary "
				+ "password, you will be prompted to change it upon logging in.\n\n"
				+ "Your temporary password: " + pword;
		
		mailServ.sendEmail(senderEmailId, receiverEmailId, subject, message);
		
		return new Message("Email sent");
	}
	
	public void RJMethod() {
		System.out.println("Fallback--------------------------------------------");
	}
	
	@PostMapping(value="updatePassword", produces=MediaType.APPLICATION_JSON_VALUE)
	public Message changePassword(@RequestBody User user) throws JsonProcessingException {
		//Update the resetPending value and send the new password over to the auth server.
		
		RestTemplate temp = new RestTemplate();
		String url = "http://localhost:8765/auth-service/auth/updateUser";
		AuthUser authUser = new AuthUser(user.getUsername(), user.getPassword());
		String body = new ObjectMapper().writeValueAsString(authUser);
		
		HttpHeaders head = new HttpHeaders();
		head.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> http = new HttpEntity<String>(body, head);
		
		ResponseEntity<User> response = temp.postForEntity(url, http, User.class);
		
		if (response.getStatusCode() != HttpStatus.OK)
			return new Message("Update failed");
		
		User updatedUser = userServ.updateUser(user, false);
		if (updatedUser == null)
			return new Message("Password update failed");
		
		return new Message("Password change success");
	}
	
	public static String generateAlphaNumericPassword() {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int len = 8;
		StringBuilder builder = new StringBuilder();
		while (len-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString().toLowerCase();
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	
	/**
	 * Utility class used to pack and send JSON to the auth server
	 * @author rj
	 *
	 */
	private class AuthUser {
		private String username;
		private String password;
		
		private AuthUser() {
			
		}
		
		private AuthUser(String uname, String pwd) {
			this.setUsername(uname);
			this.setPassword(pwd);
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String uname) {
			this.username = uname;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String pwd) {
			this.password = pwd;
		}
	}

}
