package com.backend.visitsdoctor.repository;

import com.backend.visitsdoctor.models.Deadline;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadlineRepository extends JpaRepository<Deadline, Long> {
   // boolean existsByDoctorIdAndDate(Long doctorId, Date date);
	//boolean existsByDoctor_InstitutionIdAndDate(Long institutionId, Date date);
	
	//boolean existsByDoctorIdInAndDate(Iterable<Long> doctorIds, Date date);
	//boolean existsByDoctorIdInAndDate(List<Long> doctorIds, Date date);
	//List<Deadline> findAllByDate(Date date);
	Optional<Deadline> findFirstByDateOrderByIdAsc(Date date);
}
