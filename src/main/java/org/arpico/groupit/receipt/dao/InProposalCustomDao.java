package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.model.InPropPreviousPolModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;

public interface InProposalCustomDao {

	List<ProposalNoSeqNoModel> getProposalNoSeqNoModelList(String val) throws Exception;
	
	List<ProposalNoSeqNoModel> getPolicyNoSeqNoModelList(String val) throws Exception;

	InProposalBasicsModel geInProposalBasics(Integer pprNo, Integer prpseq) throws Exception;

	InProposalsModel getProposal(Integer propId, Integer propSeq) throws Exception;
	
	InProposalsModel getProposalBuPolicy(Integer polId, Integer propSeq) throws Exception;
	
	List<ProposalL3Dto> checkL3(Integer propId) throws Exception;

	InProposalBasicsModel geInPolicyBasics(int polNo, int seqNo) throws Exception;
	
	List<InPropPreviousPolModel> getPreviousPolicies(String sbu,String nic)throws Exception;
	
	List<InPropPreviousPolModel> getAllPreviousPolicies(String sbu,String nic)throws Exception;

	List<ProposalNoSeqNoModel> getProposalNoSeqNoModel(String pprNo) throws Exception;

	InProposalsModel getProposalFromPprnum(Integer pprnum) throws Exception;

	InProposalsModel getProposalFromPolnum(Integer polnum) throws Exception;

}
