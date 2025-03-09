package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
