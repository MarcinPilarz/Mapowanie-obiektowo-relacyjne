package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
