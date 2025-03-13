package com.backend.visitsdoctor.controllers;

import com.backend.visitsdoctor.dtos.VisitRequestDto;
import com.backend.visitsdoctor.models.*;
import com.backend.visitsdoctor.repository.*;
import com.backend.visitsdoctor.repository.DeadlineRepository;
import com.backend.visitsdoctor.repository.InstitutionRepository;
import com.backend.visitsdoctor.repository.PatientRepository;
import com.backend.visitsdoctor.repository.VisitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private DeadlineRepository deadlineRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addVisit(@RequestBody VisitRequestDto visitDto) {
        Optional<Patient> patientOpt = patientRepository.findById(visitDto.getPatientId());
        Optional<Institution> institutionOpt = institutionRepository.findById(visitDto.getInstitutionId());

        if (patientOpt.isEmpty() || institutionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Patient or Institution not found");
        }

        Patient patient = patientOpt.get();
        Institution institution = institutionOpt.get();

        // Pobieramy pierwszy dostępny termin (Deadline) dla danej daty
        Optional<Deadline> availableDeadlineOpt = deadlineRepository.findFirstByDateOrderByIdAsc(visitDto.getDateOfVisit());

        if (availableDeadlineOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("No available deadlines for this date.");
        }

        Deadline selectedDeadline = availableDeadlineOpt.get();

        //Pobieramy lekarza przypisanego do terminu
        Doctor doctor = selectedDeadline.getDoctor();
        if (doctor == null) {
            return ResponseEntity.badRequest().body("No doctor assigned to the selected deadline.");
        }

        //Tworzymy wizytę (Visit) i przypisujemy lekarza przez Deadline
        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setInstitution(institution);
        visit.setDateOfVisit(visitDto.getDateOfVisit());
        visit.setStatus(VisitStatus.SCHEDULED);
        visit.setDeadline(selectedDeadline); 

        visitRepository.save(visit);

        return ResponseEntity.ok("Visit added successfully with doctor: " + doctor.getId());
    }
}
