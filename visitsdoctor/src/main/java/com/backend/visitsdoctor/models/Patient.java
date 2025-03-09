package com.backend.visitsdoctor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class Patient extends Person {

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
	
	@Column(name="telephonNumber")
	private String telephonNumber;
	
	@Column(name="email")
	private String email;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getTelephonNumber() {
		return telephonNumber;
	}

	public void setTelephonNumber(String telephonNumber) {
		this.telephonNumber = telephonNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
