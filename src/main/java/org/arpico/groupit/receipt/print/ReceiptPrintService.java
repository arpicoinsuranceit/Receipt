package org.arpico.groupit.receipt.print;

import org.arpico.groupit.receipt.dto.ReceiptPrintDto;

public interface ReceiptPrintService {

	byte[] createNewBusinessReceipt(ReceiptPrintDto receiptPrintDto) throws Exception;
	
	byte[] createProposalReceipt(ReceiptPrintDto receiptPrintDto) throws Exception;
	
	byte[] createPremiumReceipt(ReceiptPrintDto receiptPrintDto) throws Exception;
	
	byte[] createMiscInvtReceipt(ReceiptPrintDto receiptPrintDto)throws Exception;
	
	byte[] createGLRCReceipt (ReceiptPrintDto receiptPrintDto)throws Exception;

}
