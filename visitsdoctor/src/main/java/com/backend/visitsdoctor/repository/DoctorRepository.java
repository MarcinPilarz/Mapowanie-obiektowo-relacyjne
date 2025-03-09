package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
