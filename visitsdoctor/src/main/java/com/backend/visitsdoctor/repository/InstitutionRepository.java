package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long>{
	

}
