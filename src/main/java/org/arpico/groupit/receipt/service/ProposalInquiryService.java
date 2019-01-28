package org.arpico.groupit.receipt.service;

import org.arpico.groupit.receipt.dto.DataTableResponseDto;
import org.arpico.groupit.receipt.dto.ProposalInquiryDataDto;

public interface ProposalInquiryService {

	Integer getCount(String token, String equality, String column, String data);

	DataTableResponseDto propoInqloadData(String token, Integer page, Integer offset, String equality, String column,
			String data);

	ProposalInquiryDataDto loadInfo(String token, String proposalNo) throws Exception;

}
