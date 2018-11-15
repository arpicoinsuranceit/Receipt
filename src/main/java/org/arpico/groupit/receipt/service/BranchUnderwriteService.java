package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.SaveUnderwriteDto;
import org.arpico.groupit.receipt.dto.UnderwriteDto;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.springframework.http.ResponseEntity;

public interface BranchUnderwriteService {

	UnderwriteDto getProposalToUnderwrite(String usercode,Integer pageIndex, Integer pageSize) throws Exception;
	
	InProposalsModel getInProposalDetails(Integer propId, Integer propSeq)throws Exception;
	
	List<InPropFamDetailsModel> getInProposalFamDetails(Integer propId, Integer propSeq)throws Exception;
	
	List<InPropNomDetailsModel> getInProposalNomineeDetails(Integer propId, Integer propSeq)throws Exception;
	
	ResponseEntity<Object> saveUnderwrite(SaveUnderwriteDto saveUnderwriteDto)throws Exception;
}
