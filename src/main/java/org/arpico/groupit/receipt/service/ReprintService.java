package org.arpico.groupit.receipt.service;

import org.springframework.http.ResponseEntity;

public interface ReprintService {

	ResponseEntity<Object> getReprint(String docCode, Integer receiptNo, String token) throws Exception;
}
