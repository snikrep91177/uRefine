package com.reviews.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reviews.model.Hospital;
import com.reviews.model.Review;
import com.reviews.model.User;

@Repository
public interface ReviewDAO extends CrudRepository<Review, Integer>{
	
	public List<Review> findAll();
	
	public List<Review> findByAuthor(User author);
	
	public List<Review> findByHospitalOrderByDatePostedDesc(Hospital hospital);
	
	public Review save(Review r);
}
