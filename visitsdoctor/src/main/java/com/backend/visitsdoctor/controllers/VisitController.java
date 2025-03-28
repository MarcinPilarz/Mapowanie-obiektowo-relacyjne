package com.backend.visitsdoctor.controllers;

import com.backend.visitsdoctor.dtos.PaymentRequestDto;
import com.backend.visitsdoctor.dtos.VisitRequestDto;
import com.backend.visitsdoctor.models.*;
import com.backend.visitsdoctor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

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

		Optional<Deadline> availableDeadlineOpt = deadlineRepository
				.findFirstByDateOrderByIdAsc(visitDto.getDateOfVisit());

		if (availableDeadlineOpt.isEmpty()) {
			return ResponseEntity.badRequest().body("No available deadlines for this date.");
		}

		Deadline selectedDeadline = availableDeadlineOpt.get();
		Doctor doctor = selectedDeadline.getDoctor();
		if (doctor == null) {
			return ResponseEntity.badRequest().body("No doctor assigned to the selected deadline.");
		}

		// Utwórz wizytę
		Visit visit = new Visit();
		visit.setPatient(patientOpt.get());
		visit.setInstitution(institutionOpt.get());
		visit.setDateOfVisit(visitDto.getDateOfVisit());
		visit.setStatus(VisitStatus.SCHEDULED);
		visit.setDeadline(selectedDeadline);

		visitRepository.save(visit);

		RestTemplate restTemplate = new RestTemplate();
		String dotNetUrl = "http://localhost:5109/api/platnosci";

		PaymentRequestDto paymentDto = new PaymentRequestDto();
		paymentDto.setVisitId(visit.getId());
		paymentDto.setAmount(150.0);
		paymentDto.setDescription("Wizyta nr " + visit.getId());

		try {

			ResponseEntity<String> paymentResponse = restTemplate.postForEntity(dotNetUrl, paymentDto, String.class);

			if (paymentResponse.getStatusCode().is2xxSuccessful()) {

				String createdPaymentId = paymentResponse.getBody();
				System.out.println("Created payment in .NET, ID=" + createdPaymentId);
			} else {
				System.out.println("Payment creation in .NET failed: " + paymentResponse.getStatusCode());
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return ResponseEntity.ok("Visit added successfully with doctor: " + doctor.getId());
	}

	@PutMapping("/{visitId}/confirmPayment")
	public ResponseEntity<?> confirmPayment(@PathVariable Long visitId) {
		Optional<Visit> visitOpt = visitRepository.findById(visitId);
		if (visitOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Visit visit = visitOpt.get();

		visit.setStatus(VisitStatus.PAID);
		visitRepository.save(visit);

		return ResponseEntity.ok("Visit " + visitId + " confirmed as PAID");
	}
}
