package com.reviews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviews.model.Hospital;
import com.reviews.service.HospitalService;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin("http://localhost:4200")
public class HospitalController {
	
	private HospitalService hospitalServ;
	
	@Autowired
	public HospitalController(HospitalService hospitalServ) {
		this.hospitalServ = hospitalServ;
	}
	
	@GetMapping(value = "allHospitals", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hospital> allHospitals() {
		return hospitalServ.getHospitals();
	}

}
