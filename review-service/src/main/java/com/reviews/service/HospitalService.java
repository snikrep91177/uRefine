package com.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviews.model.Hospital;
import com.reviews.repo.HospitalDAO;

@Service
public class HospitalService {
	
	private HospitalDAO hospitalDao;
	
	@Autowired
	public HospitalService(HospitalDAO hospitalDao) {
		this.hospitalDao = hospitalDao;
	}
	
	public List<Hospital> getHospitals() {
		return hospitalDao.findAll();
	}

}
