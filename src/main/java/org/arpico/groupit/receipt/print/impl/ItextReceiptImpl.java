package org.arpico.groupit.receipt.print.impl;

import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.print.ReceiptPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItextReceiptImpl implements ItextReceipt {

	@Autowired
	private ReceiptPrintService receiptPrintService;

	@Override
	public byte[] createReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {

		if (receiptPrintDto != null) {
			System.out.println(receiptPrintDto.getDocCode());
			
			switch (receiptPrintDto.getDocCode()) {
			case "RCNB":
				return receiptPrintService.createNewBusinessReceipt(receiptPrintDto);
			case "RCPP":
				return receiptPrintService.createProposalReceipt(receiptPrintDto);
			case "RCPL":
				return receiptPrintService.createPremiumReceipt(receiptPrintDto);
			case "OIIS":
				return receiptPrintService.createMiscInvtReceipt(receiptPrintDto);
			case "GLRC":
				return receiptPrintService.createGLRCReceipt(receiptPrintDto);
			case "RCLN":
				return receiptPrintService.createLoanReceipt(receiptPrintDto);
			default:
				return null;
			}

		} else {
			return null;
		}

	}

}
