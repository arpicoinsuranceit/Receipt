package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.AccountGLDto;
import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.RmsRecmDto;
import org.springframework.http.ResponseEntity;


public interface MiscellaneousReceiptGLService {

	List<AccountGLDto> getAccounts() throws Exception;

	ResponseEntity<Object> save(MiscellaneousReceiptInvDto dto, String token) throws Exception;

	List<RmsRecmDto> getLatestReceipts(String token) throws Exception;

}
