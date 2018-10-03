package org.arpico.groupit.receipt.validation;

import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;

public interface CommonValidations {

	public String validateQuotationReceiptInputs(SaveReceiptDto saveReceiptDto) throws Exception;
	
	public String validateProposalReceiptInputs(SaveReceiptDto saveReceiptDto) throws Exception;
	
	public String validateMiscellaneousReceiptInvInputs(MiscellaneousReceiptInvDto dto, String token) throws Exception;
	
	public String validateMiscellaneousReceiptGlInputs(MiscellaneousReceiptInvDto dto, String token) throws Exception;
	
}
