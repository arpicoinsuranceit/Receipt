package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;

public interface InTransactionService {
	
	List<LastReceiptSummeryDto> getLastReceipts (String token) throws Exception;

}
