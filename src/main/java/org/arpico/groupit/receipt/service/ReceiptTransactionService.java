package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;

public interface ReceiptTransactionService {
	
	boolean saveTransactions(List<InBillingTransactionsModel> setoffs) throws Exception;
	
	void saveReceipt(InTransactionsModel inTransactionsModel, InBillingTransactionsModel deposit) throws Exception;
	
	void autoIssueSave(InProposalsModel inProposalsModel, InProposalsModel proposalsModelNew,
			List<InPropAddBenefitModel> addBenefitModels, List<InPropFamDetailsModel> famDetailsModels,
			List<InPropLoadingModel> inPropLoadingModels, List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropNomDetailsModel> propNomDetailsModels, List<InPropPrePolsModel> inPropPrePolsModels,
			List<InPropSchedulesModel> propSchedulesModels, List<InPropSurrenderValsModel> propSurrenderValsModels,
			List<InBillingTransactionsModel> setoffList) throws Exception;

	void saveQuoReceipt(InProposalsModel inProposalsModel, List<InPropAddBenefitModel> addBenefitModels,
			List<InPropSchedulesModel> inPropScheduleList, List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropFamDetailsModel> propFamDetailsModels, List<InPropLoadingModel> inPropLoadingModels,
			List<InPropSurrenderValsModel> inPropSurrenderValsModels,
			List<InPropNomDetailsModel> inPropNomDetailsModels, InTransactionsModel inTransactionsModel,
			InBillingTransactionsModel inBillingTransactionsModel) throws Exception;

}
