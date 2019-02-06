package org.arpico.groupit.receipt.service.impl;

import java.util.List;

import org.arpico.groupit.receipt.client.InfosysWSClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictCustomDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsDao;
import org.arpico.groupit.receipt.dao.InPropLoadingCustomDao;
import org.arpico.groupit.receipt.dao.InPropLoadingDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqCustomDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsCustomDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsDao;
import org.arpico.groupit.receipt.dao.InPropShedulesCustomDao;
import org.arpico.groupit.receipt.dao.InPropShedulesDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsCustomDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InProposalDao;
import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
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
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.ReceiptTransactionService;
import org.arpico.groupit.receipt.service.SetoffService;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReceiptTransactionServiceImpl implements ReceiptTransactionService{
	
	@Autowired
	private InProposalDao inProposalDao;
	
	@Autowired
	private InTransactionCustomDao transactionCustomDao;

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private CommonMethodsUtility commonethodUtility;

	@Autowired
	private InTransactionsDao inTransactionDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private InPropAddBenefictDao addBenefictDao;

	@Autowired
	private InPropAddBenefictCustomDao addBenefictCustomDao;

	@Autowired
	private InPropFamDetailsDao famDetailsDao;

	@Autowired
	private InPropFamDetailsCustomDao famDetailsCustomDao;

	@Autowired
	private InPropLoadingDao propLoadingDao;

	@Autowired
	private InPropLoadingCustomDao propLoadingCustomDao;

	@Autowired
	private InPropMedicalReqDao propMedicalReqDao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private InPropMedicalReqCustomDao propMedicalReqCustomDao;

	@Autowired
	private InPropNomDetailsDao propNomDetailsDao;

	@Autowired
	private InPropNomDetailsCustomDao propNomDetailsCustomDao;

	@Autowired
	private InPropPrePolsDao propPrePolsDao;

	@Autowired
	private InPropPrePolsCustomDao propPrePolsCustomDao;

	@Autowired
	private InPropShedulesDao propScheduleDao;

	@Autowired
	private InPropShedulesCustomDao propScheduleCustomDao;

	@Autowired
	private InPropSurrenderValsDao surrenderValDao;

	@Autowired
	private InPropSurrenderValsCustomDao surrenderValCustomDao;

	@Autowired
	private InTransactionService inTransactionService;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;

	@Autowired
	private RmsUserDao rmsUserDao;

	@Autowired
	private SetoffService setoffService;

	@Autowired
	private ItextReceipt itextReceipt;

	@Autowired
	private JwtDecoder decoder;

	@Autowired
	private InfosysWSClient infosysWSClient;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public boolean saveTransactions(List<InBillingTransactionsModel> setoffs) throws Exception {
		if (setoffs != null && setoffs.size() > 0) {
			inBillingTransactionDao.save(setoffs);
		}

		System.out.println("setoff saved");

		InBillingTransactionsModel model = null;

		for (InBillingTransactionsModel e : setoffs) {
			if (model != null) {
				System.out.println("not null");
				if (e.getBillingTransactionsModelPK().getDoccod().equals("PRMI") && e.getBillingTransactionsModelPK()
						.getDocnum() > model.getBillingTransactionsModelPK().getDocnum()) {

					System.out.println("prim");
					System.out.println((e.getBillingTransactionsModelPK().getDocnum()));

					model = e;
				}
			} else {
				System.out.println("null");
				if (e.getBillingTransactionsModelPK().getDoccod().equals("PRMI")) {
					System.out.println("prim");
					System.out.println((e.getBillingTransactionsModelPK().getDocnum()));

					model = e;
				}
			}

			if (model != null) {
				System.out.println(model.toString());
			}
		}

		
		System.out.println("Last");
		

		if (model != null) {
			inProposalCustomDao.changeLinNum(model.getPprnum(), model.getTxnyer(), model.getTxnmth());
		}

		return true;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void saveReceipt(InTransactionsModel inTransactionsModel, InBillingTransactionsModel deposit)
			throws Exception {
		inTransactionDao.save(inTransactionsModel);
		inBillingTransactionDao.save(deposit);
		
	}

	@Override
	public void autoIssueSave(InProposalsModel inProposalsModel, InProposalsModel proposalsModelNew,
			List<InPropAddBenefitModel> addBenefitModels, List<InPropFamDetailsModel> famDetailsModels,
			List<InPropLoadingModel> inPropLoadingModels, List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropNomDetailsModel> propNomDetailsModels, List<InPropPrePolsModel> inPropPrePolsModels,
			List<InPropSchedulesModel> propSchedulesModels, List<InPropSurrenderValsModel> propSurrenderValsModels,
			List<InBillingTransactionsModel> setoffList) throws Exception {
		InBillingTransactionsModel model = null;

		for (InBillingTransactionsModel e : setoffList) {
			if (model != null) {
				if (e.getBillingTransactionsModelPK().getDoccod().equals("PRMI") && e.getBillingTransactionsModelPK()
						.getDocnum() > model.getBillingTransactionsModelPK().getDocnum()) {
					model = e;
				}
			} else {
				if (e.getBillingTransactionsModelPK().getDoccod().equals("PRMI")) {
					model = e;
				}
			}
		}

		if (model != null) {

			inProposalsModel.setLinyer(model.getIcpyer());
			inProposalsModel.setLinmon(model.getIcpmon());

		}
		
		
		updatePolNum(proposalsModelNew.getInProposalsModelPK().getPprnum(), proposalsModelNew.getPolnum());
		
		
		inProposalDao.save(inProposalsModel);

		System.out.println("Proposal Status Update Done");

		inProposalDao.save(proposalsModelNew);

		System.out.println("Proposal Save Done");

		if (addBenefitModels != null && !addBenefitModels.isEmpty()) {
			addBenefictDao.save(addBenefitModels);
		}

		System.out.println("benef save done");

		if (famDetailsModels != null && !famDetailsModels.isEmpty()) {
			famDetailsDao.save(famDetailsModels);
		}

		System.out.println("fam save done");

		if (inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {
			propLoadingDao.save(inPropLoadingModels);
		}
		System.out.println("pro loading save done");

		if (inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {
			propMedicalReqDao.save(inPropMedicalReqModels);
		}

		System.out.println("propMedi save done");

		if (propNomDetailsModels != null && !propNomDetailsModels.isEmpty()) {
			propNomDetailsDao.save(propNomDetailsModels);
		}

		System.out.println("nominee save done");

		if (inPropPrePolsModels != null && !inPropPrePolsModels.isEmpty()) {
			propPrePolsDao.save(inPropPrePolsModels);

		}

		System.out.println("proposalPrePol save done");

		if (propSchedulesModels != null && !propSchedulesModels.isEmpty()) {
			propScheduleDao.save(propSchedulesModels);
		}

		System.out.println("proposal Schedule save done");

		if (propSurrenderValsModels != null && !propSurrenderValsModels.isEmpty()) {
			surrenderValDao.save(propSurrenderValsModels);
		}

		System.out.println("proposal surrender Val save done");

		inBillingTransactionDao.save(setoffList);
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	private void updatePolNum(String pprnum, String polnum) throws Exception {
		billingTransactionsCustomDao.updatePolNum(pprnum, polnum);
		transactionCustomDao.updatePolNum(pprnum, polnum);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void saveQuoReceipt(InProposalsModel inProposalsModel, List<InPropAddBenefitModel> addBenefitModels,
			List<InPropSchedulesModel> inPropScheduleList, List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropFamDetailsModel> propFamDetailsModels, List<InPropLoadingModel> inPropLoadingModels,
			List<InPropSurrenderValsModel> inPropSurrenderValsModels,
			List<InPropNomDetailsModel> inPropNomDetailsModels, InTransactionsModel inTransactionsModel,
			InBillingTransactionsModel inBillingTransactionsModel) throws Exception {

		inProposalDao.save(inProposalsModel);
		addBenefictDao.save(addBenefitModels);
		if (inPropScheduleList != null) {
			propScheduleDao.save(inPropScheduleList);
		}
		if (inPropMedicalReqModels != null && inPropMedicalReqModels.size() > 0) {
			propMedicalReqDao.save(inPropMedicalReqModels);
		}

		famDetailsDao.save(propFamDetailsModels);
		propLoadingDao.save(inPropLoadingModels);
		surrenderValDao.save(inPropSurrenderValsModels);
		propNomDetailsDao.save(inPropNomDetailsModels);
		
		inTransactionDao.save(inTransactionsModel);
		inBillingTransactionDao.save(inBillingTransactionsModel);
		
	}

}
