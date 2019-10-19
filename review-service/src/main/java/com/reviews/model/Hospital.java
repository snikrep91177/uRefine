package com.reviews.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Hospital")
public class Hospital {
	
	@Id
	@Column(name = "hospitalId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hospitalId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "specialty")
	private String specialty;
	
	public Hospital() {
		
	}
	
	public Hospital(int id) {
		this.hospitalId = id;
	}

	public Hospital(String name, String address, String img, String specialty) {
		super();
		this.name = name;
		this.address = address;
		this.img = img;
		this.setSpecialty(specialty);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
}
