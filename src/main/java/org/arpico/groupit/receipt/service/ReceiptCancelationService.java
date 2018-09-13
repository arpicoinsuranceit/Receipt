package org.arpico.groupit.receipt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ReceiptCancelationService {
	
	List<String> findReceiptLikeReceiptId(String receiptId,String token) throws Exception;
	
	ResponseEntity<Object> saveRequest(String receiptNo,String reason, String token)throws Exception;

}
