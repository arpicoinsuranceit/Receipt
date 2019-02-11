package org.arpico.groupit.receipt.service.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InPropAddBenefictCustomDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.ReceiptTransactionService;
import org.arpico.groupit.receipt.service.SetoffRestService;
import org.arpico.groupit.receipt.service.SetoffService;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetoffRestServiceImpl implements SetoffRestService{

	@Autowired
	private InProposalCustomDao inProposalCustomDao;
	
	@Autowired
	private InPropAddBenefictCustomDao addBenefictCustomDao;
	
	@Autowired
	private SetoffService setoffService;
	
	@Autowired
	private NumberGenerator numberGenerator;
	
	@Autowired
	private CommonMethodsUtility commonethodUtility;
	
	@Autowired
	private ReceiptTransactionService receiptTransactionService;
	
	@Override
	public String setoffRest(Integer policyNo) throws Exception {
		
		String[] batNoArr2 = numberGenerator.generateNewId("", "", "#TXNSQ#", "");
		
		if (batNoArr2[0].equals("Success")) {
			
		}else {
			return "Batch No Generation Error";
		}
		
		InProposalsModel proposalsModel = inProposalCustomDao.getProposalFromPolnum(policyNo);
		
		if(proposalsModel == null) {
			return "Policy Not Found";
		}
		
		List<InPropAddBenefitModel> addBenefitModels = addBenefictCustomDao.getBenefByPprSeq(Integer.parseInt(proposalsModel.getInProposalsModelPK().getPprnum()), proposalsModel.getInProposalsModelPK().getPrpseq());
		
		Double hrbamt = commonethodUtility.getHrbAmt(addBenefitModels);
		
		List<InBillingTransactionsModel> setoffs = setoffService.setoff(proposalsModel, "system", proposalsModel.getInProposalsModelPK().getLoccod(), null, null, hrbamt, null, "OLD", Integer.parseInt(batNoArr2[1]));
		
		receiptTransactionService.saveTransactions(setoffs);
		
		return "SETOFF SAVED";
	}

}
