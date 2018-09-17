package org.arpico.groupit.receipt.print.impl;

import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.print.ReceiptPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItextReceiptImpl implements ItextReceipt {

	@Autowired
	private ReceiptPrintService receiptPrintService;

	@Override
	public byte[] createReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {

		if (receiptPrintDto != null) {
			System.out.println(receiptPrintDto.getDocCode());

			if (receiptPrintDto.getDocCode().equalsIgnoreCase("RCNB")) {
				return receiptPrintService.createNewBusinessReceipt(receiptPrintDto);

			} else if (receiptPrintDto.getDocCode().equalsIgnoreCase("RCPP")) {
				return receiptPrintService.createProposalReceipt(receiptPrintDto);

			} else if (receiptPrintDto.getDocCode().equalsIgnoreCase("RCPL")) {
				return receiptPrintService.createPremiumReceipt(receiptPrintDto);

			} else if (receiptPrintDto.getDocCode().equalsIgnoreCase("OIIS")) {
				return receiptPrintService.createMiscInvtReceipt(receiptPrintDto);

			} else if (receiptPrintDto.getDocCode().equalsIgnoreCase("GLRC")) {
				return receiptPrintService.createGLRCReceipt(receiptPrintDto);

			}

		}

		return new byte[] {};
	}

}
