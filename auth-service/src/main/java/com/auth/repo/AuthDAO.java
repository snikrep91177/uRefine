package com.auth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auth.model.User;

@Repository
public interface AuthDAO extends CrudRepository<User, Integer> {
	
	public User save(User user);	
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT GET_HASH(:username, :password) FROM dual")
    String getHash(@Param("username") String username, @Param("password") String password);

}
