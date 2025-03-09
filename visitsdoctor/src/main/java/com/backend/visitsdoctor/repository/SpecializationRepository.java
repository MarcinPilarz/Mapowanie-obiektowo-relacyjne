package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long>{

}
