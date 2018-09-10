package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.BranchDto;

public interface BranchService {

	List<BranchDto> getBranches(String token) throws Exception;

}
