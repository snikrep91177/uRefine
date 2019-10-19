package com.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviews.model.Hospital;
import com.reviews.model.Review;
import com.reviews.model.User;
import com.reviews.repo.ReviewDAO;

@Service
public class ReviewService {
	
	private ReviewDAO reviewDao;
	
	@Autowired
	public ReviewService(ReviewDAO reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	public List<Review> getReviews() {
		return reviewDao.findAll();
	}
	
	public List<Review> getReviewsByAuthor(User user) {
		return reviewDao.findByAuthor(user);
	}
	
	public List<Review> getReviewsByHospital(Hospital hospital) {
		return reviewDao.findByHospitalOrderByDatePostedDesc(hospital);
	}
	
	public boolean postReview(Review r) {
		Review res = reviewDao.save(r);
		if (res != null) {
			return true;
		} else {
			return false;
		}
	}

	public Review updateReview(Review r) {
		if (reviewDao.findById(r.getReviewId()) != null) {
			return reviewDao.save(r);
		} else {
			return null;
		}
	}

}
