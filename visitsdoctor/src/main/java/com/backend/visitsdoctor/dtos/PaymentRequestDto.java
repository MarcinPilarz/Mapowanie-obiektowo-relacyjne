package com.backend.visitsdoctor.dtos;

public class PaymentRequestDto {
    private Long visitId;
    private Double amount;
    private String description;

    // get/set
    public Long getVisitId() { return visitId; }
    public void setVisitId(Long visitId) { this.visitId = visitId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
