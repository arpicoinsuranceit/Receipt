package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ReceiptDetailsDto;

public interface ReceiptInquiryService {
	public List<ReceiptDetailsDto> getAllReceiptDetails(String token,Integer pageNum,Integer limit)throws Exception;
}
