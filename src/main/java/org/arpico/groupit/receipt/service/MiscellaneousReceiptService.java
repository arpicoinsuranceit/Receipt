package org.arpico.groupit.receipt.service;

import java.util.ArrayList;

import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.RmsDocTxnmGridDto;
import org.springframework.http.ResponseEntity;

public interface MiscellaneousReceiptService {
	
	ResponseEntity<Object> save (MiscellaneousReceiptInvDto dto, String token) throws Exception;

	ArrayList<RmsDocTxnmGridDto> getLast(String token) throws Exception;

}
