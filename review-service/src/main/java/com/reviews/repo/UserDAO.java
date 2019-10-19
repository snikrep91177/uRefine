package com.reviews.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reviews.model.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
	
	public User save(User user);	
	
	public List<User> findAll();
	
	public User findByUsername(String username);
}
