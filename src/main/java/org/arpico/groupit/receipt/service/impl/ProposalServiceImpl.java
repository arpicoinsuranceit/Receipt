package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.InAgentMastDao;
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
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.arpico.groupit.receipt.model.ReFundModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.ProposalServce;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProposalServiceImpl implements ProposalServce {

	@Autowired
	private InProposalDao inProposalDao;

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
	private InAgentMastDao inAgentMastDao;
	
	@Autowired
	private RmsUserDao rmsUserDao;

	@Override
	public List<ProposalNoSeqNoDto> getProposalNoSeqNoDtoList(String val) throws Exception {
		List<ProposalNoSeqNoDto> proposalNoSeqNoDtos = new ArrayList<ProposalNoSeqNoDto>();

		List<ProposalNoSeqNoModel> list = inProposalCustomDao.getProposalNoSeqNoModelList(val);

		for (ProposalNoSeqNoModel proposalNoSeqNoModel : list) {
			proposalNoSeqNoDtos.add(getProposalNoSeqNoDto(proposalNoSeqNoModel));

		}

		return proposalNoSeqNoDtos;
	}

	private ProposalNoSeqNoDto getProposalNoSeqNoDto(ProposalNoSeqNoModel proposalNoSeqNoModel) {
		ProposalNoSeqNoDto dto = new ProposalNoSeqNoDto();
		dto.setProposalNo(proposalNoSeqNoModel.getProposalNo());
		dto.setSeqNo(proposalNoSeqNoModel.getSeqNo());
		return dto;
	}

	@Override
	public ProposalBasicDetailsDto getBasicDetails(Integer pprNum, Integer seqNum) throws Exception {
		InProposalBasicsModel basicsModel = inProposalCustomDao.geInProposalBasics(pprNum, seqNum);
		List<LastReceiptSummeryDto> dtos = inTransactionService.getLastReceiptsByPprNo(pprNum.toString());
		ProposalBasicDetailsDto basicDetailsDto = getBasicDetailsDto(basicsModel);
		basicDetailsDto.setAmtPayble(billingTransactionsCustomDao.paybleAmountThisMonth(basicsModel.getProposalNo()));
		basicDetailsDto.setLastReceiptSummeryDtos(dtos);
		return basicDetailsDto;
	}

	private ProposalBasicDetailsDto getBasicDetailsDto(InProposalBasicsModel basicsModel) {

		ProposalBasicDetailsDto basicDetailsDto = new ProposalBasicDetailsDto();

		basicDetailsDto.setAgentCode(basicsModel.getAgentCode());
		basicDetailsDto.setCustName(basicsModel.getCustName());
		basicDetailsDto.setCustTitle(basicDetailsDto.getCustTitle());
		basicDetailsDto.setProduct(basicsModel.getProduct());
		basicDetailsDto.setProposalNo(basicsModel.getProposalNo());
		basicDetailsDto.setSeqNo(basicsModel.getSeqNo());
		basicDetailsDto.setPremium(basicsModel.getPremium());
		return basicDetailsDto;
	}

	@Override
	public ResponseEntity<Object> saveProposal(SaveReceiptDto saveReceiptDto) throws Exception {

		String agentCode = new JwtDecoder().generate(saveReceiptDto.getToken());

		System.out.println(agentCode);
		String locCode = rmsUserDao.getLocation(agentCode);

		if (locCode != null ) {

			InProposalsModel inProposalsModel = inProposalCustomDao.getProposal(saveReceiptDto.getPropId(),
					saveReceiptDto.getPropSeq());

			if (inProposalsModel != null) {

				Integer pprNo = Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum());
				Integer seqNo = inProposalsModel.getInProposalsModelPK().getPrpseq();

				if (inProposalsModel.getPprsta().equalsIgnoreCase("L3")) {
					List<ProposalL3Dto> proposalL3Dtos = inProposalCustomDao.checkL3(saveReceiptDto.getPropId());
					if (!proposalL3Dtos.isEmpty()) {

						String[] numberGen = numberGenerator.generateNewId("", "", "POLCSQ", "");
						if (numberGen[0].equals("Success")) {

							InProposalsModel proposalsModelNew = getProposalPolicyStage(inProposalsModel, numberGen[1],
									saveReceiptDto);

							inProposalDao.save(proposalsModelNew);

							System.out.println("proposal save done");

							Integer updatedSeqNo = proposalsModelNew.getInProposalsModelPK().getPrpseq();
							seqNo = updatedSeqNo;

							List<InPropAddBenefitModel> addBenefitModels = addBenefictCustomDao.getBenefByPprSeq(pprNo,
									seqNo);
							if (addBenefitModels != null && !addBenefitModels.isEmpty()) {
								addBenefitModels = incrementSeqAddBenef(addBenefitModels, updatedSeqNo);
								addBenefictDao.save(addBenefitModels);
							}

							System.out.println("benef save done");

							List<InPropFamDetailsModel> famDetailsModels = famDetailsCustomDao
									.getFamilyByPprNoAndSeqNo(pprNo, seqNo);
							if (famDetailsModels != null && !famDetailsModels.isEmpty()) {
								famDetailsModels = incrementSeqFamDetails(famDetailsModels, updatedSeqNo);
								famDetailsDao.save(famDetailsModels);
							}

							System.out.println("fam save done");

							List<InPropLoadingModel> inPropLoadingModels = propLoadingCustomDao
									.getPropLoadingBuPprNumAndSeq(pprNo, seqNo);
							if (inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {
								inPropLoadingModels = getInPropLoadings(inPropLoadingModels, updatedSeqNo);
								propLoadingDao.save(inPropLoadingModels);
							}

							System.out.println("pro loading save done");

							List<InPropMedicalReqModel> inPropMedicalReqModels = propMedicalReqCustomDao
									.getMedicalReqByPprNoAndSeq(pprNo, seqNo);
							if (inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {
								inPropMedicalReqModels = incrementPropMedical(inPropMedicalReqModels, updatedSeqNo);
								propMedicalReqDao.save(inPropMedicalReqModels);
							}

							System.out.println("propMedi save done");

							List<InPropNomDetailsModel> propNomDetailsModels = propNomDetailsCustomDao
									.getNomByPprNoAndPprSeq(pprNo, seqNo);
							if (propNomDetailsModels != null && !propNomDetailsModels.isEmpty()) {
								propNomDetailsModels = incrementPropNomDetailsSeq(propNomDetailsModels, updatedSeqNo);
								propNomDetailsDao.save(propNomDetailsModels);
							}

							System.out.println("nominee save done");

							List<InPropPrePolsModel> inPropPrePolsModels = propPrePolsCustomDao
									.getPrePolByPprNoAndPprSeq(pprNo, seqNo);
							if (inPropPrePolsModels != null && !inPropPrePolsModels.isEmpty()) {
								inPropPrePolsModels = incrementPropPolSeq(inPropPrePolsModels, updatedSeqNo);
								propPrePolsDao.save(inPropPrePolsModels);

							}

							System.out.println("proposalPrePol save done");

							List<InPropSchedulesModel> propSchedulesModels = propScheduleCustomDao
									.getScheduleBuPprNoAndSeqNo(pprNo, seqNo);
							if (propSchedulesModels != null && !propSchedulesModels.isEmpty()) {
								propSchedulesModels = incremenntScheduleSeq(propSchedulesModels, updatedSeqNo);
								propScheduleDao.save(propSchedulesModels);
							}

							System.out.println("proposal Schedule save done");

							List<InPropSurrenderValsModel> propSurrenderValsModels = surrenderValCustomDao
									.getSurrenderValByInpprNoAndSeq(pprNo, seqNo);
							if (propSurrenderValsModels != null && !propSurrenderValsModels.isEmpty()) {
								propSurrenderValsModels = incrementSurrenderVals(propSurrenderValsModels, updatedSeqNo,
										proposalsModelNew.getPolnum());
								surrenderValDao.save(propSurrenderValsModels);
							}

							System.out.println("proposal surrender Val save done");

							InTransactionsModel inTransactionsModel = commonethodUtility
									.getInTransactionModel(inProposalsModel, saveReceiptDto, agentCode, locCode);

							inTransactionsModel.setSeqnum(updatedSeqNo);

							System.out.println("transaction generated");

							InBillingTransactionsModel deposit = commonethodUtility.getInBillingTransactionModel(
									inProposalsModel, saveReceiptDto, inTransactionsModel);
							deposit.setPrpseq(updatedSeqNo);

							System.out.println("deposit generated");

							List<InBillingTransactionsModel> unSetOffList = billingTransactionsCustomDao
									.getUnSetOffs(inProposalsModel.getInProposalsModelPK().getPprnum());

							System.out.println("unSetOffList");

							InBillingTransactionsModel invoice = null;

							if (unSetOffList != null && unSetOffList.size() > 0) {
								invoice = unSetOffList.get(0);
							} else {
								invoice = createInvoice(inProposalsModel, null, agentCode, locCode);
								inBillingTransactionDao.save(invoice);
							}

							System.out.println("invoice");

							List<InBillingTransactionsModel> setoffList = null;

							List<ReFundModel> fundModels = billingTransactionsCustomDao
									.getRefundList(inProposalsModel.getInProposalsModelPK().getPprnum());

							System.out.println("fundModels size : " + fundModels.size());

							Double amount = saveReceiptDto.getAmount();

							if (fundModels != null && fundModels.size() > 0) {
								for (ReFundModel reFundModel : fundModels) {
									amount += reFundModel.getRefamount();
								}
							}

							System.out.println("amount size : " + amount);
							System.out.println("amount size : " + (invoice.getAmount() <= amount));
							if (invoice.getAmount() <= amount) {

								ReFundModel fundModel = new ReFundModel();
								fundModel.setDoccod(deposit.getBillingTransactionsModelPK().getDoccod());
								fundModel.setDocnum(deposit.getBillingTransactionsModelPK().getDocnum());
								fundModel.setPprnum(
										Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
								fundModel.setRefamount(deposit.getDepost() * -1);
								fundModel.setLinnum(deposit.getBillingTransactionsModelPK().getLinnum());
								setoffList = getSetOff(invoice, fundModel, setoffList, inProposalsModel, fundModels,
										agentCode, locCode);
							}

							inTransactionDao.save(inTransactionsModel);
							inBillingTransactionDao.save(deposit);

							if (setoffList != null && setoffList.size() > 0) {
								System.out.println(setoffList.size() + "  setoff list");

								for (InBillingTransactionsModel e : setoffList) {
									System.out.println(e.getBillingTransactionsModelPK().toString());
								}
								for (InBillingTransactionsModel e : setoffList) {
									System.out.println(e.toString());
								}

								inBillingTransactionDao.save(setoffList);
							}

							return new ResponseEntity<>("Success", HttpStatus.OK);

						} else {
							return new ResponseEntity<>("Error at generate Policy No",
									HttpStatus.INTERNAL_SERVER_ERROR);
						}

					} else {
						InTransactionsModel inTransactionsModel = commonethodUtility
								.getInTransactionModel(inProposalsModel, saveReceiptDto, agentCode, locCode);
						InBillingTransactionsModel inBillingTransactionsModel = commonethodUtility
								.getInBillingTransactionModel(inProposalsModel, saveReceiptDto, inTransactionsModel);

						inTransactionDao.save(inTransactionsModel);
						inBillingTransactionDao.save(inBillingTransactionsModel);

						return new ResponseEntity<>("Success", HttpStatus.OK);
					}

				} else {
					InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
							saveReceiptDto, agentCode, locCode);
					InBillingTransactionsModel inBillingTransactionsModel = commonethodUtility
							.getInBillingTransactionModel(inProposalsModel, saveReceiptDto, inTransactionsModel);
					inTransactionDao.save(inTransactionsModel);
					inBillingTransactionDao.save(inBillingTransactionsModel);

					return new ResponseEntity<>("Success", HttpStatus.OK);
				}

			} else {
				return new ResponseEntity<>("Proposal Not Found", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		}

	}

	private List<InBillingTransactionsModel> getSetOff(InBillingTransactionsModel invoice, ReFundModel deposit,
			List<InBillingTransactionsModel> setoffList, InProposalsModel inProposalsModel,
			List<ReFundModel> fundModels, String user, String loc) throws Exception {

		Double invoiceAmount = invoice.getAmount();

		System.out.println("invoiceAmount 1 : " + invoiceAmount);

		if (setoffList == null) {
			setoffList = new ArrayList<>();
		}

		if (fundModels != null && fundModels.size() > 0) {

			for (Integer i = 0; i < fundModels.size(); i++) {
				ReFundModel reFundModel = fundModels.get(i);
				System.out.println("refund : " + reFundModel.getRefamount());
				invoiceAmount = invoiceAmount - reFundModel.getRefamount();

				System.out.println("invoiceAmount 2 : " + invoiceAmount);
				if (invoiceAmount > 0) {
					System.out.println("invoiceAmount max : " + invoiceAmount);
					setoffList.add(getSetoff(reFundModel, invoice, inProposalsModel, user, loc));
					fundModels.remove(reFundModel);

					System.out.println("FundModel Size : " + fundModels.size());

					i++;
				} else {
					System.out.println("invoiceAmount min : " + invoiceAmount);

					ReFundModel tempReFundModel = new ReFundModel();
					tempReFundModel.setDoccod(reFundModel.getDoccod());
					tempReFundModel.setDocnum(reFundModel.getDocnum());
					tempReFundModel.setPprnum(reFundModel.getPprnum());
					tempReFundModel.setRefamount(reFundModel.getRefamount() + invoiceAmount);
					tempReFundModel.setLinnum(reFundModel.getLinnum() + 1);

					setoffList.add(getSetoff(tempReFundModel, invoice, inProposalsModel, user, loc));
					reFundModel.setRefamount(reFundModel.getRefamount() - tempReFundModel.getRefamount());

					InBillingTransactionsModel newInvoice = createInvoice(inProposalsModel, invoice, user, loc);
					setoffList.add(newInvoice);
					getSetOff(newInvoice, deposit, setoffList, inProposalsModel, fundModels, user, loc);
					/*
					 * List<InBillingTransactionsModel> billingTransactionsModels = if
					 * (billingTransactionsModels != null && !billingTransactionsModels.isEmpty()) {
					 * setoffList.addAll(billingTransactionsModels); }
					 */
					i++;
				}

			}

		}

		System.out.println("invoiceAmount end refunds : " + invoiceAmount);
		System.out
				.println("invoiceAmount <= (deposit.getRefamount()) : " + (invoiceAmount <= (deposit.getRefamount())));
		if (invoiceAmount <= (deposit.getRefamount())) {

			Double depAmount = deposit.getRefamount();

			System.out.println("depAmount 1 : " + depAmount);

			deposit.setRefamount(invoiceAmount);
			depAmount = depAmount - invoiceAmount;

			System.out.println("depAmount 2 : " + depAmount);

			setoffList.add(getSetoff(deposit, invoice, inProposalsModel, user, loc));

			InBillingTransactionsModel newInvoice = createInvoice(inProposalsModel, invoice,user, loc);
			setoffList.add(newInvoice);

			System.out.println("depAmount >= newInvoice.getAmount() : " + (depAmount >= newInvoice.getAmount()));

			if (depAmount >= newInvoice.getAmount()) {

				deposit.setRefamount(depAmount);
				deposit.setLinnum(deposit.getLinnum() + 1);

				System.out.println("depAmount 3 : " + depAmount);
				System.out.println("depAmount 4 : " + deposit.getRefamount());

				System.out.println("fundModel 4 : " + fundModels.size());
				getSetOff(newInvoice, deposit, setoffList, inProposalsModel, fundModels, user, loc);
				/*
				 * List<InBillingTransactionsModel> billingTransactionsModels = if
				 * (billingTransactionsModels != null && !billingTransactionsModels.isEmpty()) {
				 * setoffList.addAll(billingTransactionsModels); }
				 */

			}

			return setoffList;
		} else {
			return setoffList;
		}
	}

	private List<InPropSurrenderValsModel> incrementSurrenderVals(
			List<InPropSurrenderValsModel> propSurrenderValsModels, Integer updatedSeqNo, String polNo) {
		propSurrenderValsModels.forEach(e -> {
			e.getInPropSurrenderValsPK().setPrpseq(updatedSeqNo);
			e.setPolnum(polNo);
		});
		return propSurrenderValsModels;
	}

	private List<InPropSchedulesModel> incremenntScheduleSeq(List<InPropSchedulesModel> propSchedulesModels,
			Integer updatedSeqNo) {
		propSchedulesModels.forEach(e -> {
			e.getInPropSchedulesPK().setPrpseq(updatedSeqNo);
		});
		return propSchedulesModels;
	}

	private List<InPropPrePolsModel> incrementPropPolSeq(List<InPropPrePolsModel> inPropPrePolsModels,
			Integer updatedSeqNo) {
		inPropPrePolsModels.forEach(e -> {
			e.getInPropPrePolsModelPK().setPrpseq(updatedSeqNo);
		});
		return inPropPrePolsModels;
	}

	private List<InPropNomDetailsModel> incrementPropNomDetailsSeq(List<InPropNomDetailsModel> propNomDetailsModels,
			Integer updatedSeqNo) {
		propNomDetailsModels.forEach(e -> {
			e.getInPropNomDetailsModelPK().setPrpseq(updatedSeqNo);
		});
		return propNomDetailsModels;
	}

	private List<InPropMedicalReqModel> incrementPropMedical(List<InPropMedicalReqModel> inPropMedicalReqModels,
			Integer seqNo) {
		inPropMedicalReqModels.forEach(e -> {
			e.getInPropMedicalReqModelPK().setPrpseq(seqNo);
		});
		return inPropMedicalReqModels;
	}

	private List<InPropLoadingModel> getInPropLoadings(List<InPropLoadingModel> inPropLoadingModels, Integer seqNo) {
		inPropLoadingModels.forEach(e -> {
			e.getInPropLoadingPK().setPrpseq(seqNo);
		});
		return inPropLoadingModels;
	}

	private List<InPropFamDetailsModel> incrementSeqFamDetails(List<InPropFamDetailsModel> famDetailsModels,
			Integer seqNo) {
		famDetailsModels.forEach(e -> {
			e.getInPropFamDetailsPK().setPrpseq(seqNo);
		});
		return famDetailsModels;
	}

	private List<InPropAddBenefitModel> incrementSeqAddBenef(List<InPropAddBenefitModel> addBenefitModels,
			Integer seqNo) {
		addBenefitModels.forEach(e -> {
			e.getInPropAddBenefitPK().setPrpseq(seqNo);
		});
		return addBenefitModels;
	}

	private InProposalsModel getProposalPolicyStage(InProposalsModel inProposalsModel, String polNo,
			SaveReceiptDto saveReceiptDto) {
		InProposalsModel proposalsModel = inProposalsModel;

		proposalsModel.getInProposalsModelPK().setPrpseq(proposalsModel.getInProposalsModelPK().getPrpseq() + 1);
		proposalsModel.setPolnum(polNo);
		proposalsModel.setPoldat(AppConstant.DATE);
		proposalsModel.setCreaby(AppConstant.SYSTEM_CREATE);
		proposalsModel.setCreadt(AppConstant.DATE);
		proposalsModel.setPprsta(AppConstant.POLICY_STATUS_PLISU);

		return proposalsModel;
	}

	public InBillingTransactionsModel createInvoice(InProposalsModel inProposalsModel,
			InBillingTransactionsModel previousInvoice, String user, String loc) throws Exception {

		String[] numberGen = numberGenerator.generateNewId("", "", "PRMISQ", "");
		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(inProposalsModel.getAdvcod());

		AgentMastModel agentMastModel = agentMastModels.get(0);

		if (numberGen[0].equals("Success")) {

			InBillingTransactionsModelPK billingTransactionsModelPK = new InBillingTransactionsModelPK();

			billingTransactionsModelPK.setDoccod("PRMI");
			billingTransactionsModelPK.setDocnum(Integer.parseInt(numberGen[1]));
			billingTransactionsModelPK.setLinnum(0);
			billingTransactionsModelPK.setLoccod(loc);
			billingTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
			billingTransactionsModelPK.setTxndat(AppConstant.DATE);

			InBillingTransactionsModel billingTransactionsModel = new InBillingTransactionsModel();

			billingTransactionsModel.setBillingTransactionsModelPK(billingTransactionsModelPK);

			billingTransactionsModel.setAdmfee(AppConstant.ZERO_TWO_DECIMAL);
			billingTransactionsModel.setAdvcod(Integer.parseInt(inProposalsModel.getAdvcod()));
			billingTransactionsModel.setAgncls(agentMastModel.getAgncls());
			if (previousInvoice != null) {
				billingTransactionsModel.setAmount(
						inProposalsModel.getTaxamt() + inProposalsModel.getAdmfee() + inProposalsModel.getTotprm());
			} else {
				billingTransactionsModel.setAmount(inProposalsModel.getTaxamt() + inProposalsModel.getAdmfee()
						+ inProposalsModel.getTotprm() + inProposalsModel.getPolfee());
			}

			billingTransactionsModel.setBrncod(inProposalsModel.getBrncod());
			billingTransactionsModel.setChqrel("N");
			billingTransactionsModel.setComiss(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setComper(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setCreaby(user);
			billingTransactionsModel.setCreadt(AppConstant.DATE);
			try {
				billingTransactionsModel.setCscode(Integer.parseInt(inProposalsModel.getCscode()));
			} catch (Exception e) {
			}
			billingTransactionsModel.setDepost(AppConstant.ZERO_TWO_DECIMAL);
			billingTransactionsModel.setGlintg("N");

			billingTransactionsModel.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setInsnum(0);
			billingTransactionsModel.setLockin(AppConstant.DATE);
			billingTransactionsModel.setOldprm(AppConstant.ZERO_FOR_DECIMAL);

			billingTransactionsModel.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));
			if (previousInvoice != null) {
				billingTransactionsModel.setPolfee(AppConstant.ZERO_TWO_DECIMAL);
			} else {
				billingTransactionsModel.setPolfee(inProposalsModel.getPolfee());
			}

			billingTransactionsModel.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
			billingTransactionsModel.setPrdcod(inProposalsModel.getPrdcod());
			billingTransactionsModel.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
			billingTransactionsModel.setRefdoc(billingTransactionsModelPK.getDoccod());
			billingTransactionsModel.setRefnum(billingTransactionsModelPK.getDocnum());
			billingTransactionsModel.setSrcdoc(null);
			billingTransactionsModel.setSrcnum(null);
			billingTransactionsModel.setTaxamt(inProposalsModel.getTaxamt());
			billingTransactionsModel.setToptrm(inProposalsModel.getToptrm());
			billingTransactionsModel.setTxntyp("INVOICE");
			if (agentMastModel.getAgncls().equalsIgnoreCase("IC")) {
				billingTransactionsModel.setUnlcod(agentMastModel.getUnlcod());
			}
			if (agentMastModel.getAgncls().equalsIgnoreCase("UNL")) {
				billingTransactionsModel.setUnlcod(agentMastModel.getBrnmanager());
			}

			if (previousInvoice != null) {
				if (previousInvoice.getTxnmth() >= 12) {
					billingTransactionsModel.setTxnyer(previousInvoice.getTxnyer() + 1);
					billingTransactionsModel.setTxnmth(1);
				} else {
					billingTransactionsModel.setTxnyer(previousInvoice.getTxnyer());
					billingTransactionsModel.setTxnmth(previousInvoice.getTxnmth() + 1);
				}

			} else {
				try {
					InBillingTransactionsModel model = billingTransactionsCustomDao
							.getTxnYearDate(inProposalsModel.getInProposalsModelPK().getPprnum());

					if (model.getTxnmth() >= 12) {
						billingTransactionsModel.setTxnyer(model.getTxnyer() + 1);
						billingTransactionsModel.setTxnmth(1);
					} else {
						billingTransactionsModel.setTxnyer(model.getTxnyer());
						billingTransactionsModel.setTxnmth(model.getTxnmth() + 1);
					}
				} catch (Exception e) {
					billingTransactionsModel.setTxnyer(Calendar.getInstance().get(Calendar.YEAR));
					billingTransactionsModel.setTxnmth(Calendar.getInstance().get(Calendar.MONTH));
				}
			}
			return billingTransactionsModel;

		} else {
			return null;
		}

	}

	private InBillingTransactionsModel getSetoff(ReFundModel reFundModel, InBillingTransactionsModel invoice,
			InProposalsModel inProposalsModel, String user, String loc) throws Exception {

		InBillingTransactionsModelPK modelPK = new InBillingTransactionsModelPK();
		modelPK.setDoccod(reFundModel.getDoccod());
		modelPK.setDocnum(reFundModel.getDocnum());
		modelPK.setLinnum(reFundModel.getLinnum() + 1);
		modelPK.setLoccod(loc);
		modelPK.setTxndat(AppConstant.DATE);
		modelPK.setSbucod(AppConstant.SBU_CODE);

		InBillingTransactionsModel model = new InBillingTransactionsModel();
		model.setBillingTransactionsModelPK(modelPK);

		model.setAdmfee(invoice.getAdmfee());
		model.setAdvcod(invoice.getAdvcod());
		model.setAgncls(invoice.getAgncls());
		model.setAmount(reFundModel.getRefamount() * -1);
		model.setBrncod(inProposalsModel.getBrncod());
		model.setChqrel("N");
		model.setComiss(AppConstant.ZERO_FOR_DECIMAL);
		model.setComper(AppConstant.ZERO_FOR_DECIMAL);
		model.setCreaby(user);
		model.setCreadt(AppConstant.DATE);
		try {
			model.setCscode(Integer.parseInt(inProposalsModel.getCscode()));
		} catch (Exception e) {
		}
		model.setDepost(reFundModel.getRefamount());
		model.setGlintg("N");

		model.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
		model.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
		model.setInsnum(0);
		model.setLockin(AppConstant.DATE);
		model.setOldprm(AppConstant.ZERO_FOR_DECIMAL);

		model.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));
		model.setPolfee(invoice.getPolfee());
		model.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
		model.setPrdcod(inProposalsModel.getPrdcod());
		model.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
		model.setRefdoc(modelPK.getDoccod());
		model.setRefnum(modelPK.getDocnum());
		model.setSrcdoc(modelPK.getDoccod());
		model.setSrcnum(modelPK.getDocnum());
		model.setTaxamt(inProposalsModel.getTaxamt());
		model.setToptrm(inProposalsModel.getToptrm());
		model.setTxntyp("PROPDEP");
		model.setUnlcod(invoice.getUnlcod());

		model.setTxnyer(invoice.getTxnyer());
		model.setTxnmth(invoice.getTxnmth());

		return model;

	}

}
