package com.reviews.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reviews.model.Hospital;

@Repository
public interface HospitalDAO extends CrudRepository<Hospital, Integer> {
	public List<Hospital> findAll();
}
