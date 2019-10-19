package com.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.repo.AuthDAO;
import com.auth.model.User;

@Service
public class AuthService {
	
	private AuthDAO authDao;
	
	@Autowired
	public AuthService(AuthDAO authDao) {
		this.authDao = authDao;
	}
	
	public List<User> getUsers() {
		return authDao.findAll();
	}

	public User doLogin(User user) {
		System.out.println(user.getUsername());
		User auth = authDao.findByUsername(user.getUsername());
		String pword = authDao.getHash(user.getUsername(), user.getPassword());
		if (auth == null)
			return null;
		if (auth.getPassword().equals(pword)) {
			return auth;
		} else {
			return null;
		}
	}

	public User registerUser(User user) {
		if (authDao.findByUsername(user.getUsername()) != null) {
			return null;
		} else {
			return authDao.save(user);
		}
	}

	public User updateUser(User user) {
		return authDao.save(user);
	}

}
