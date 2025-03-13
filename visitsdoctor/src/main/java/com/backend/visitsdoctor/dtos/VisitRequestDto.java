package com.backend.visitsdoctor.dtos;

import java.sql.Date;

public class VisitRequestDto {
    private Long patientId;
    private Long institutionId;
    private Date dateOfVisit;
   // private Long doctorId;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

//	public Long getDoctorId() {
//		return doctorId;
//	}
//
//	public void setDoctorId(Long doctorId) {
//		this.doctorId = doctorId;
//	}
    
}
