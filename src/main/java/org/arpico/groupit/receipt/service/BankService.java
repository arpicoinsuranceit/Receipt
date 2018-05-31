package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.BankDto;

public interface BankService {
	
	List<BankDto> getBanksByUser(String userCode) throws Exception;

}
