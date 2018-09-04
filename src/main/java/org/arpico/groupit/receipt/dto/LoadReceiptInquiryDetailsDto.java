package org.arpico.groupit.receipt.dto;

import java.util.List;

public class LoadReceiptInquiryDetailsDto {
	
	private List<ReceiptDetailsDto> receiptDetailsDto;
	private Integer receiptCount;
	
	public List<ReceiptDetailsDto> getReceiptDetailsDto() {
		return receiptDetailsDto;
	}
	public void setReceiptDetailsDto(List<ReceiptDetailsDto> receiptDetailsDto) {
		this.receiptDetailsDto = receiptDetailsDto;
	}
	public Integer getReceiptCount() {
		return receiptCount;
	}
	public void setReceiptCount(Integer receiptCount) {
		this.receiptCount = receiptCount;
	}
	@Override
	public String toString() {
		return "LoadReceiptInquiryDetailsDto [receiptDetailsDto=" + receiptDetailsDto + ", receiptCount=" + receiptCount
				+ "]";
	}
	
	

}
