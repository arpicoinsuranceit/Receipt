package org.arpico.groupit.receipt.dto;

import java.util.List;

public class ProposalInquiryDataDto {

	ProposalGeneralDto generalDto;
	List<ChildInqDto> childDtos;
	List<NomineeInquiryDto> nomineeInquiryDaos;
	List<BenefictInquiryDto> benefictInquiryDtos;
	List<MedicalReqDto> medicalReqDtos;
	List<TransferHistoryDto> transferHistoryDtos;
	List<SettlementDto> settlementDtos;
	List<PaymentHistoryInqDto> paymentHistoryInqDtos;
	PolicyDispatchAcknowDto policyDispatchAcknowDto;

	public ProposalGeneralDto getGeneralDto() {
		return generalDto;
	}

	public void setGeneralDto(ProposalGeneralDto generalDto) {
		this.generalDto = generalDto;
	}

	public List<ChildInqDto> getChildDtos() {
		return childDtos;
	}

	public void setChildDtos(List<ChildInqDto> childDtos) {
		this.childDtos = childDtos;
	}

	public List<NomineeInquiryDto> getNomineeInquiryDaos() {
		return nomineeInquiryDaos;
	}

	public void setNomineeInquiryDaos(List<NomineeInquiryDto> nomineeInquiryDaos) {
		this.nomineeInquiryDaos = nomineeInquiryDaos;
	}

	public List<BenefictInquiryDto> getBenefictInquiryDtos() {
		return benefictInquiryDtos;
	}

	public void setBenefictInquiryDtos(List<BenefictInquiryDto> benefictInquiryDtos) {
		this.benefictInquiryDtos = benefictInquiryDtos;
	}

	public List<MedicalReqDto> getMedicalReqDtos() {
		return medicalReqDtos;
	}

	public void setMedicalReqDtos(List<MedicalReqDto> medicalReqDtos) {
		this.medicalReqDtos = medicalReqDtos;
	}

	public List<TransferHistoryDto> getTransferHistoryDtos() {
		return transferHistoryDtos;
	}

	public void setTransferHistoryDtos(List<TransferHistoryDto> transferHistoryDtos) {
		this.transferHistoryDtos = transferHistoryDtos;
	}

	public List<SettlementDto> getSettlementDtos() {
		return settlementDtos;
	}

	public void setSettlementDtos(List<SettlementDto> settlementDtos) {
		this.settlementDtos = settlementDtos;
	}

	public List<PaymentHistoryInqDto> getPaymentHistoryInqDtos() {
		return paymentHistoryInqDtos;
	}

	public void setPaymentHistoryInqDtos(List<PaymentHistoryInqDto> paymentHistoryInqDtos) {
		this.paymentHistoryInqDtos = paymentHistoryInqDtos;
	}

	public PolicyDispatchAcknowDto getPolicyDispatchAcknowDto() {
		return policyDispatchAcknowDto;
	}

	public void setPolicyDispatchAcknowDto(PolicyDispatchAcknowDto policyDispatchAcknowDto) {
		this.policyDispatchAcknowDto = policyDispatchAcknowDto;
	}

	@Override
	public String toString() {
		return "ProposalInquiryDataDto [generalDto=" + generalDto + ", childDtos=" + childDtos + ", nomineeInquiryDaos="
				+ nomineeInquiryDaos + ", benefictInquiryDtos=" + benefictInquiryDtos + ", medicalReqDtos="
				+ medicalReqDtos + ", transferHistoryDtos=" + transferHistoryDtos + ", settlementDtos=" + settlementDtos
				+ ", paymentHistoryInqDtos=" + paymentHistoryInqDtos + ", policyDispatchAcknowDto="
				+ policyDispatchAcknowDto + "]";
	}

}
