package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;

public interface BranchUnderwriteTransactionService {

	void saveProposalNotApprove(InProposalsModel newInProposalsModel, List<InPropLoadingModel> inPropLoadingModels,
			List<InPropAddBenefitModel> addBenefitModels, List<InPropFamDetailsModel> propFamDetailsModels,
			List<InPropSchedulesModel> inPropScheduleList, List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropSurrenderValsModel> inPropSurrenderValsModels, List<InPropNomDetailsModel> nomDetailsModels,
			List<InPropPrePolsModel> prePolsModels) throws Exception;
	
	void saveProposal(InProposalsModel inProposalsModel, InProposalsModel newInProposalsModel,
			List<InPropLoadingModel> inPropLoadingModels, List<InPropAddBenefitModel> addBenefitModels,
			List<InPropFamDetailsModel> propFamDetailsModels, List<InPropSchedulesModel> inPropScheduleList,
			List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropSurrenderValsModel> inPropSurrenderValsModels, List<InPropNomDetailsModel> nomDetailsModels,
			List<InPropPrePolsModel> prePolsModels) throws Exception;
	
	public String saveCourierDocument(Integer pprNo, Integer seqNo,String branchCode, String userCode, boolean isExistDepartment, DepartmentCourierModel departmentCourierModel) throws Exception;

}
