package org.arpico.groupit.receipt.print;

import org.arpico.groupit.receipt.dto.ReceiptPrintDto;

public interface ItextReceipt {
	
	byte[] createReceipt(ReceiptPrintDto receiptPrintDto) throws Exception;

}
