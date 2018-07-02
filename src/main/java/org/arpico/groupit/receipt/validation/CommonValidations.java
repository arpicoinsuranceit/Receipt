package org.arpico.groupit.receipt.validation;

import org.arpico.groupit.receipt.dto.SaveReceiptDto;

public interface CommonValidations {

	public String validateQuotationReceiptInputs(SaveReceiptDto saveReceiptDto) throws Exception;
	
}
