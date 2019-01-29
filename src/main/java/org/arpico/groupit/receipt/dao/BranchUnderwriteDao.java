package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;

public interface BranchUnderwriteDao {
	
	List<String> findLocCodes(String usercode);
	
	List<String> findLocCodesZonalBranch(String usercode);
	
	List<InProposalUnderwriteModel> findProposalToUnderwrite(String locodes,Integer limit,Integer offset,boolean isHO);
	
	Integer findProposalCount(boolean isHO,String locodes);

}
