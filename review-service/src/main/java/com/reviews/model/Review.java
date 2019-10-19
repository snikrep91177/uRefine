package com.reviews.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Review")
public class Review {
	
	@Id
	@Column(name = "reviewId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reviewId;
	
	@ManyToOne()
	@JoinColumn(name = "author_id", nullable = false)
	private User author;
	
	@ManyToOne()
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "body", length = 2000)
	private String body;
	
	@Column(name = "overallRating")
	private int overallRating;
	
	@Column(name = "waitTime")
	private int waitTime;
	
	@Column(name = "staffResponsiveness")
	private int staffResponsiveness;
	
	@Column(name = "doctorCommunication")
	private int doctorCommunication;
	
	@Column(name = "nurseCommunication")
	private int nurseCommunication;
	
	@Column(name = "medicationCommunication")
	private int medicationCommunication;
	
	@Column(name = "options")
	private int options;
	
	@Column(name = "recommendations")
	private int recommendations;
	
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "specializationRating")
	private int specializationRating;
	
	@Column(name = "datePosted")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePosted;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<User> upvotes;

	public Review() {
		
	}

	public Review(String title, String body, int overallRating, int waitTime, int staffResponsiveness,
			int doctorCommunication, int nurseCommunication, int medicationCommunication, int options, 
			int recommendations, String specialization, int specializationRating, List<User> upvotes) {
		super();
		this.title = title;
		this.body = body;
		this.overallRating = overallRating;
		this.waitTime = waitTime;
		this.staffResponsiveness = staffResponsiveness;
		this.doctorCommunication = doctorCommunication;
		this.nurseCommunication = nurseCommunication;
		this.medicationCommunication = medicationCommunication;
		this.options = options;
		this.recommendations = recommendations;
		this.specialization = specialization;
		this.specializationRating = specializationRating;
		this.upvotes = upvotes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getStaffResponsiveness() {
		return staffResponsiveness;
	}

	public void setStaffResponsiveness(int staffResponsiveness) {
		this.staffResponsiveness = staffResponsiveness;
	}

	public int getDoctorCommunication() {
		return doctorCommunication;
	}

	public void setDoctorCommunication(int doctorCommunication) {
		this.doctorCommunication = doctorCommunication;
	}

	public int getNurseCommunication() {
		return nurseCommunication;
	}

	public void setNurseCommunication(int nurseCommunication) {
		this.nurseCommunication = nurseCommunication;
	}

	public int getMedicationCommunication() {
		return medicationCommunication;
	}

	public void setMedicationCommunication(int medicationCommunication) {
		this.medicationCommunication = medicationCommunication;
	}

	public int getOptions() {
		return options;
	}

	public void setOptions(int options) {
		this.options = options;
	}

	public int getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(int recommendations) {
		this.recommendations = recommendations;
	}
	
	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	
	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getSpecializationRating() {
		return specializationRating;
	}

	public void setSpecializationRating(int specializationRating) {
		this.specializationRating = specializationRating;
	}
	
	public List<User> getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(List<User> upvotes) {
		this.upvotes = upvotes;
	}

	@Override
	public String toString() {
		return "Review [title=" + title + ", body=" + body + ", overallRating=" + overallRating + ", waitTime="
				+ waitTime + ", staffResponsiveness=" + staffResponsiveness + ", doctorCommunication="
				+ doctorCommunication + ", nurseCommunication=" + nurseCommunication + ", medicationCommunication="
				+ medicationCommunication + ", recommendations=" + recommendations + "]";
	}

}
