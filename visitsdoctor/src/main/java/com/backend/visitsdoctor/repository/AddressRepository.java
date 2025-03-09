package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
