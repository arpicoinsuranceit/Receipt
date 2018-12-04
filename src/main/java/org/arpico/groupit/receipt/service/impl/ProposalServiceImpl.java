package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.AgentDao;
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
import org.arpico.groupit.receipt.dao.InShortPremiumActProductDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.AgentModel;
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
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;/*
																import org.arpico.groupit.receipt.model.ReFundModel;*/
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.model.pk.InShortPremiumModelPK;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.ProposalServce;
import org.arpico.groupit.receipt.service.SetoffService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
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
	private InAgentMastDao inAgentMastDao;

	@Autowired
	private RmsUserDao rmsUserDao;

	@Autowired
	private SetoffService setoffService;

	@Autowired
	private InShortPremiumActProductDao actProductDao;

	@Autowired
	private ItextReceipt itextReceipt;

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

		String locCode = rmsUserDao.getLocation(agentCode);

		if (locCode != null) {

			InProposalsModel inProposalsModel = inProposalCustomDao.getProposal(saveReceiptDto.getPropId(),
					saveReceiptDto.getPropSeq());

			if (inProposalsModel != null) {

				Integer pprNo = Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum());
				Integer seqNo = inProposalsModel.getInProposalsModelPK().getPrpseq();

				///////////// save billing//////////////////////
				InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
						saveReceiptDto, agentCode, locCode);
				inTransactionsModel.getInTransactionsModelPK().setDoccod("RCPP");

				InBillingTransactionsModel inBillingTransactionsModel = commonethodUtility
						.getInBillingTransactionModel(inProposalsModel, saveReceiptDto, inTransactionsModel);

				inBillingTransactionsModel.getBillingTransactionsModelPK().setDoccod("RCPP");
				inBillingTransactionsModel.setRefdoc("RCPP");
				inBillingTransactionsModel.setSrcdoc("RCPP");
				inBillingTransactionsModel.setTaxamt(0.0);
				inBillingTransactionsModel.setAdmfee(0.0);
				inBillingTransactionsModel.setPolfee(0.0);


				System.out.println(inBillingTransactionsModel.toString());
				try {
					saveReceipt(inTransactionsModel, inBillingTransactionsModel);

					System.out.println("save in");
					
					if (!saveReceiptDto.getPayMode().equals("CQ")) {
						checkPolicy(inProposalsModel, pprNo, seqNo, saveReceiptDto, agentCode, locCode,
								inBillingTransactionsModel);
					}

					System.out.println("dto");

					ReceiptPrintDto dto = null;

					try {
						dto = getReceiptPrintDto(inProposalsModel, inTransactionsModel, agentCode, locCode, false);
					} catch (Exception e) {
						e.printStackTrace();
						ResponseDto responseDto = new ResponseDto();
						responseDto.setCode("500");
						responseDto.setStatus("Error");
						responseDto.setMessage("Error at print receipt");
						return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
					}

					ResponseDto responseDto = new ResponseDto();
					responseDto.setCode("200");
					responseDto.setStatus("Success");
					responseDto.setMessage(inBillingTransactionsModel.getBillingTransactionsModelPK().getDocnum().toString());
					responseDto.setData(itextReceipt.createReceipt(dto));
					
					return new ResponseEntity<>(responseDto, HttpStatus.OK);
					
				}catch (Exception e) {
					e.printStackTrace();
					ResponseDto responseDto = new ResponseDto();
					responseDto.setCode("500");
					responseDto.setStatus("Error");
					responseDto.setMessage("Error at save receipt");
					return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				

				///////////////// end save billing ///////////////

				

			} else {
				ResponseDto responseDto = new ResponseDto();
				responseDto.setCode("204");
				responseDto.setStatus("Error");
				responseDto.setMessage("Proposal Not Found");
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			}

		} else {
			ResponseDto responseDto = new ResponseDto();
			responseDto.setCode("204");
			responseDto.setStatus("Error");
			responseDto.setMessage("User or Location Not Found");
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}

	}

	@Transactional
	private void saveReceipt(InTransactionsModel inTransactionsModel,
			InBillingTransactionsModel inBillingTransactionsModel) {
		inTransactionDao.save(inTransactionsModel);
		
		inBillingTransactionDao.save(inBillingTransactionsModel);

	}

	private ReceiptPrintDto getReceiptPrintDto(InProposalsModel inProposalsModel,
			InTransactionsModel inTransactionsModel, String agentCode, String locCode, boolean b) throws Exception {

		ReceiptPrintDto printDto = new ReceiptPrintDto();

		List<AgentModel> agentModels = agentDao.findAgentByCodeAll(inProposalsModel.getAdvcod());
		
		System.out.println(inProposalsModel.getAdvcod());

		String userName = rmsUserDao.getName(agentCode);

		printDto.setAgtCode(Integer.parseInt(inProposalsModel.getAdvcod()));
		printDto.setAgtName(agentModels.get(0).getAgentName());
		printDto.setAmt(inTransactionsModel.getTotprm());
		printDto.setAmtInWord(inTransactionsModel.getAmtwrd());
		printDto.setCusAddress1(inProposalsModel.getPpdad1());
		printDto.setCusAddress2(inProposalsModel.getPpdad2());
		printDto.setCusAddress3(inProposalsModel.getPpdad3());
		printDto.setCusCode(Integer.parseInt(inProposalsModel.getCscode()));
		printDto.setCusName(inProposalsModel.getPpdini());
		printDto.setCusTitle(inProposalsModel.getNtitle());
		printDto.setDocCode(inTransactionsModel.getInTransactionsModelPK().getDoccod());
		printDto.setDocNum(inTransactionsModel.getInTransactionsModelPK().getDocnum());
		printDto.setLocation(locCode);
		printDto.setPayMode(inTransactionsModel.getPaymod());
		printDto.setPolNum(inTransactionsModel.getPolnum());
		printDto.setPropNum(Integer.parseInt(inTransactionsModel.getPprnum()));
		printDto.setQuoNum(inProposalsModel.getQuonum());
		printDto.setQdId(inProposalsModel.getInProposalsModelPK().getPrpseq());
		printDto.setRctDate(inTransactionsModel.getCreadt());
		printDto.setRctStatus("");
		printDto.setRemark(inTransactionsModel.getRemark());
		printDto.setUserName(userName);
		if (inTransactionsModel.getChqnum() != null) {
			printDto.setChqNo(Integer.parseInt(inTransactionsModel.getChqnum()));
		}
		if (inTransactionsModel.getChqdat() != null) {
			printDto.setChqDate(new SimpleDateFormat("dd/MM/yyyy").format(inTransactionsModel.getChqdat()));
		}
		if (inTransactionsModel.getChqbnk() != null) {
			printDto.setBankCode(Integer.parseInt(inTransactionsModel.getChqbnk()));
		}
		return printDto;
	}

	@Transactional
	@Override
	public void checkPolicy(InProposalsModel inProposalsModel, Integer pprNo, Integer seqNo,
			SaveReceiptDto saveReceiptDto, String agentCode, String locCode, InBillingTransactionsModel deposit)
			throws Exception {
		if (inProposalsModel.getPprsta().equalsIgnoreCase("L3")) {

			List<ProposalL3Dto> proposalL3Dtos = inProposalCustomDao.checkL3(saveReceiptDto.getPropId());
			if (!proposalL3Dtos.isEmpty()) {

				System.out.println("PASS CHECK POLICY");

				String[] numberGen = numberGenerator.generateNewId("", "", "POLCSQ", "");
				if (numberGen[0].equals("Success")) {

					inProposalsModel.setPprsta("INAC");
					inProposalsModel.setLockin(new Date());
					inProposalsModel.setIcpdat(new Date());

					inProposalDao.save(inProposalsModel);

					InProposalsModel proposalsModelNew = getProposalPolicyStage(inProposalsModel, numberGen[1],
							saveReceiptDto);

					inProposalDao.save(proposalsModelNew);

					System.out.println("proposal save done");

					Integer updatedSeqNo = proposalsModelNew.getInProposalsModelPK().getPrpseq();
					seqNo = updatedSeqNo;

					List<InPropAddBenefitModel> addBenefitModels = addBenefictCustomDao.getBenefByPprSeq(pprNo, seqNo);
					if (addBenefitModels != null && !addBenefitModels.isEmpty()) {
						addBenefitModels = incrementSeqAddBenef(addBenefitModels, updatedSeqNo);
						addBenefictDao.save(addBenefitModels);
					}

					System.out.println("benef save done");

					List<InPropFamDetailsModel> famDetailsModels = famDetailsCustomDao.getFamilyByPprNoAndSeqNo(pprNo,
							seqNo);
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

					List<InPropPrePolsModel> inPropPrePolsModels = propPrePolsCustomDao.getPrePolByPprNoAndPprSeq(pprNo,
							seqNo);
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

					InShortPremiumModelPK inShortPremiumModelPK = new InShortPremiumModelPK();
					inShortPremiumModelPK.setPrdcod(inProposalsModel.getPrdcod());
					inShortPremiumModelPK.setSbucod("450");

					Double recovery = actProductDao.findByStatusAndInShortPremiumModelPK("ACT", inShortPremiumModelPK)
							.getSpiamt();

					setoffService.setoff(proposalsModelNew, agentCode, locCode, saveReceiptDto, deposit, recovery);

				}
			} else {
				System.out.println("FAIL CHECK POLICY");
			}
		} else {
			System.out.println("FAIL CHECK POLICY");
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
		proposalsModel.setPoldat(new Date());
		proposalsModel.setCreaby(AppConstant.SYSTEM_CREATE);
		proposalsModel.setCreadt(new Date());
		proposalsModel.setPprsta(AppConstant.POLICY_STATUS_PLISU);
		proposalsModel.setProsta(AppConstant.POLICY_STATUS_PLISU);

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
			billingTransactionsModelPK.setTxndat(new Date());

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
			billingTransactionsModel.setCreadt(new Date());
			try {
				billingTransactionsModel.setCscode(Integer.parseInt(inProposalsModel.getCscode()));
			} catch (Exception e) {
			}
			billingTransactionsModel.setDepost(AppConstant.ZERO_TWO_DECIMAL);
			billingTransactionsModel.setGlintg("N");

			billingTransactionsModel.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setInsnum(0);
			billingTransactionsModel.setLockin(new Date());
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
					billingTransactionsModel.setTxnmth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				}
			}
			return billingTransactionsModel;

		} else {
			return null;
		}

	}

	@Override
	public ProposalNoSeqNoDto getProposalNoSeqNoDto(String pprNo) throws Exception {
		ProposalNoSeqNoDto proposalNoSeqNoDtos = null;

		List<ProposalNoSeqNoModel> list = inProposalCustomDao.getProposalNoSeqNoModel(pprNo);

		System.out.println(list.size());

		if (list != null && !(list.isEmpty())) {
			proposalNoSeqNoDtos = getProposalNoSeqNoDto(list.get(0));
		}

		return proposalNoSeqNoDtos;
	}

}
