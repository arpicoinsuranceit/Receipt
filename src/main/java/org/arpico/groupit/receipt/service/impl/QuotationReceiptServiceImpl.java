package org.arpico.groupit.receipt.service.impl;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dto.EditQuotation;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.QuotationReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuotationReceiptServiceImpl implements QuotationReceiptService{

	@Autowired
	private QuotationClient quotationClient;
	
	@Override
	public String saveQuotationReceipt(SaveReceiptDto saveReceiptDto) throws Exception {
		EditQuotation resp = quotationClient.getQuotation(saveReceiptDto.getQuotationDetailId());
		
		
		
		return null;
	}

}
