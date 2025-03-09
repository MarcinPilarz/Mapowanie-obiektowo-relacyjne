package com.backend.visitsdoctor.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Doctor extends Person {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;

	@Column(name = "npwz")
	private String npwz;
	@Column(name = "title")
	private String title;

	@OneToMany(mappedBy = "doctor")
	private List<Specialization> specializations;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Visit> visits;

	public String getNpwz() {
		return npwz;
	}

	public void setNpwz(String npwz) {
		this.npwz = npwz;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(List<Specialization> specializations) {
		this.specializations = specializations;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}

}
