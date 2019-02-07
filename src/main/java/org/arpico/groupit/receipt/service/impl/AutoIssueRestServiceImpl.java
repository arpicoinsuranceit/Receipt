package org.arpico.groupit.receipt.service.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.service.AutoIssueRestService;
import org.arpico.groupit.receipt.service.ProposalServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AutoIssueRestServiceImpl implements AutoIssueRestService{
	
	@Autowired
	private InProposalCustomDao inProposalCustomDao;
	
	@Autowired 
	private ProposalServce proposalServce;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;
	
	@Override
	public String autoIssue(Integer pprno) throws Exception {
		
		InProposalsModel inProposalsModel = inProposalCustomDao.getProposalFromPprnum(pprno);
		
		SaveReceiptDto receiptDto = new SaveReceiptDto();
		
		receiptDto.setPropId(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
		
		InBillingTransactionsModel deposit = billingTransactionsCustomDao.getLastDeposit(inProposalsModel.getInProposalsModelPK().getPprnum());
		
		proposalServce.checkPolicy(inProposalsModel, Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()), inProposalsModel.getInProposalsModelPK().getPrpseq(), receiptDto, "system", inProposalsModel.getInProposalsModelPK().getLoccod(), deposit);
		
		return "AUTO ISSUE DONE";
	}

}
