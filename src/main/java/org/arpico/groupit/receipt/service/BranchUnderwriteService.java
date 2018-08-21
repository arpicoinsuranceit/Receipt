package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.SaveUnderwriteDto;
import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.springframework.http.ResponseEntity;

public interface BranchUnderwriteService {

	List<InProposalUnderwriteModel> getProposalToUnderwrite(String usercode) throws Exception;
	
	InProposalsModel getInProposalDetails(Integer propId, Integer propSeq)throws Exception;
	
	ResponseEntity<Object> saveUnderwrite(SaveUnderwriteDto saveUnderwriteDto)throws Exception;
}
