package com.backend.visitsdoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.visitsdoctor.models.AddressType;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, Long>{

}
