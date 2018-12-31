package org.arpico.groupit.receipt.service;

import java.util.List;
import org.arpico.groupit.receipt.dto.CanceledReceiptDto;
import org.springframework.http.ResponseEntity;

public interface ReceiptCancelationService {
	
	List<String> findReceiptLikeReceiptId(String receiptId,String token) throws Exception;
	
	ResponseEntity<Object> saveRequest(String receiptNo,String reason, String token,String doccod)throws Exception;
	
	List<CanceledReceiptDto> findPendingRequest(String token) throws Exception;

	List<CanceledReceiptDto> findCanceledRequest(String token) throws Exception;

	List<CanceledReceiptDto> findPendingRequest(String token, Integer page, Integer offset) throws Exception;

	Integer findPendingRequestLength(String token)  throws Exception;

	

}
