package com.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviews.model.User;
import com.reviews.repo.UserDAO;

@Service
public class UserService {
	
	private UserDAO userDao;
	
	@Autowired
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public List<User> getUsers() {
		return userDao.findAll();
	}

	public User selectByUname(String uname) {
		return userDao.findByUsername(uname);
	}

	public User register(User user) {
		return userDao.save(user);
	}
	
	public User updateUser(User user, Boolean isPending) {
		User toUpdate = userDao.findByUsername(user.getUsername());
		toUpdate.setResetPending(isPending);
		return userDao.save(toUpdate);
	}

}
