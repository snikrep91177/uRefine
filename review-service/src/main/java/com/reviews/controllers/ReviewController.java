package com.reviews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviews.model.Hospital;
import com.reviews.model.Message;
import com.reviews.model.Review;
import com.reviews.model.User;
import com.reviews.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("http://localhost:4200")
public class ReviewController {
	
	private ReviewService reviewServ;
	
	@Autowired
	public ReviewController(ReviewService reviewServ) {
		this.reviewServ = reviewServ;
	}
	
	@GetMapping(value="/getReviews", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Review> getReviews() {
		return reviewServ.getReviews();
	}
	
	@PostMapping(value="/findByAuthor", consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Review> findReviewsByAuthor(@RequestBody User author) {
		return reviewServ.getReviewsByAuthor(author);
	}
	
	@PostMapping(value ="/findByHospital", consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Review> findReviewsByHospital(@RequestBody Hospital hospital) {
		return reviewServ.getReviewsByHospital(hospital);
	}
	
	@PostMapping(value="/postReview", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Message postReview(@RequestBody Review r) {
		
		boolean res = reviewServ.postReview(r);
		
		return new Message(""+res);
	}
	
	@PostMapping(value="updateReview", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Message updateReview(@RequestBody Review r) {
		Review updatedReview = reviewServ.updateReview(r);
		if (updatedReview != null) {
			return new Message("Update successful");
		} else {
			return new Message("Update failed");
		}
	}

}
