package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SMSDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.dto.SearchDto;
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
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		basicDetailsDto.setId2(basicDetailsDto.getId2());

		if (basicsModel.getMobNo() != null && basicsModel.getMobNo().length() > 0) {
			basicDetailsDto.setMobile("true");
		} else {
			basicDetailsDto.setMobile("false");
		}
		basicDetailsDto.setStatus(basicsModel.getPrsta());
		return basicDetailsDto;
	}

	@Override
	public ResponseEntity<Object> saveProposal(SaveReceiptDto saveReceiptDto) throws Exception {

		System.out.println("SAVE PROPOSAL SERVICE");

		String userCode = decoder.generate(saveReceiptDto.getToken());

		String locCode = decoder.generateLoc(saveReceiptDto.getToken());

		String[] batNoArr = numberGenerator.generateNewId("", "", "#TXNSQ#", "");

		if (batNoArr[0].equals("Success")) {
			
			System.out.println("RECEIPT NUMBER GENERATED : " + batNoArr[1]);

			if (locCode != null) {
				
				System.out.println("LOCATION FOUND : " + locCode);

				//System.out.println("if");

				InProposalsModel inProposalsModel = inProposalCustomDao.getProposal(saveReceiptDto.getPropId(),
						saveReceiptDto.getPropSeq());

				//System.out.println(inProposalsModel);

				if (inProposalsModel != null) {
					
					System.out.println("PROPOSAL FOUND : " + inProposalsModel.toString());

					//System.out.println("//System.out.println(inProposalsModel); ok ");

					Integer pprNo = Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum());
					Integer seqNo = inProposalsModel.getInProposalsModelPK().getPrpseq();

					///////////// save billing//////////////////////
					InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
							saveReceiptDto, userCode, locCode);
					inTransactionsModel.getInTransactionsModelPK().setDoccod("RCPP");

					
					
					//System.out.println(inTransactionsModel);

					try {
						inTransactionsModel.setPolnum(Integer.parseInt(inProposalsModel.getPolnum()));
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println("POLICY NUMBER NOT FOUND");
						//System.out.println("Proposal not found");
					}

					System.out.println("TRANSACTIONMODEL GENETATED : " + inTransactionsModel.toString());
					
					//System.out.println("WORK");

					//System.out.println(inTransactionsModel.toString());

					InBillingTransactionsModel inBillingTransactionsModel = commonethodUtility
							.getInBillingTransactionModel(inProposalsModel, saveReceiptDto, inTransactionsModel);

					//System.out.println(inBillingTransactionsModel.toString());

					inBillingTransactionsModel.setTxnbno(AppConstant.ZERO);

					inBillingTransactionsModel.getBillingTransactionsModelPK().setDoccod("RCPP");
					inBillingTransactionsModel.setRefdoc("RCPP");
					inBillingTransactionsModel.setSrcdoc("RCPP");
					inBillingTransactionsModel.setTaxamt(0.0);
					inBillingTransactionsModel.setAdmfee(0.0);
					inBillingTransactionsModel.setPolfee(0.0);
					inBillingTransactionsModel.setPolnum(inTransactionsModel.getPolnum());
					inBillingTransactionsModel.setTxnbno(Integer.parseInt(batNoArr[1]));

					System.out.println("BILLINGTRANSACTIONMODEL GENETATED : " + inBillingTransactionsModel.toString());
					//System.out.println(inBillingTransactionsModel.toString());
					try {
						saveReceipt(inTransactionsModel, inBillingTransactionsModel);

						System.out.println("RECEIPT SAVED IN BOTH TRANSACTION TABLE");
						
						//System.out.println("save in");

						if (!saveReceiptDto.getPayMode().equals("CQ")
								&& inProposalsModel.getPprsta().equalsIgnoreCase("L3")) {

							System.out.println("L3 and CASH, DD OR CC");
							
							inBillingTransactionsModel.setTxnbno(1);

							checkPolicy(inProposalsModel, pprNo, seqNo, saveReceiptDto, userCode, locCode,
									inBillingTransactionsModel);
						} else {
							System.out.println("CHEQUE OR NOT L3");
						}

						//System.out.println("dto");

						ReceiptPrintDto dto = null;

						try {
							dto = getReceiptPrintDto(inProposalsModel, inTransactionsModel, userCode, locCode, false);
						} catch (Exception e) {
							e.printStackTrace();
							ResponseDto responseDto = new ResponseDto();
							responseDto.setCode("500");
							responseDto.setStatus("Error at print receipt. Proposal No : "
									+ inProposalsModel.getInProposalsModelPK().getPprnum() + ", Receipt No : "
									+ inBillingTransactionsModel.getBillingTransactionsModelPK().getDoccod() + " / "
									+ inBillingTransactionsModel.getBillingTransactionsModelPK().getDocnum());
							responseDto.setMessage("Error at print receipt");
							return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
						}

						ResponseDto responseDto = new ResponseDto();
						responseDto.setCode("200");
						responseDto.setStatus("Successfully saved. Proposal No : "
								+ inProposalsModel.getInProposalsModelPK().getPprnum() + ", Receipt No : "
								+ inBillingTransactionsModel.getBillingTransactionsModelPK().getDoccod() + " / "
								+ inBillingTransactionsModel.getBillingTransactionsModelPK().getDocnum());
						responseDto.setMessage(
								inBillingTransactionsModel.getBillingTransactionsModelPK().getDocnum().toString());
						responseDto.setData(itextReceipt.createReceipt(dto));

						SMSDto smsDto = new SMSDto();
						smsDto.setDocCode("RCPP");
						smsDto.setSmsType("proposal");
						smsDto.setRcptNo(Integer.toString(dto.getDocNum()));
						smsDto.setUserCode(userCode);
						;

						infosysWSClient.sendSMS(smsDto);

						return new ResponseEntity<>(responseDto, HttpStatus.OK);

					} catch (Exception e) {
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
		} else {
			ResponseDto responseDto = new ResponseDto();
			responseDto.setCode("204");
			responseDto.setStatus("Error");
			responseDto.setMessage("Batch No not Created");
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

		//System.out.println(inProposalsModel.getAdvcod());

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

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@Override
	public void checkPolicy(InProposalsModel inProposalsModel, Integer pprNo, Integer seqNo,
			SaveReceiptDto saveReceiptDto, String userCode, String locCode, InBillingTransactionsModel deposit)
			throws Exception {

		System.out.println("CHCK POLICY METHOD");
		
		try {
			if (inProposalsModel.getPprsta().equalsIgnoreCase("L3")) {

				List<ProposalL3Dto> proposalL3Dtos = inProposalCustomDao.checkL3(saveReceiptDto.getPropId());
				if (!proposalL3Dtos.isEmpty()) {

					System.out.println("PASS CHECK POLICY");

					String[] batNoArr2 = numberGenerator.generateNewId("", "", "#TXNSQ#", "");

					String[] numberGen = numberGenerator.generateNewId("", "", "POLCSQ", "");
					if (numberGen[0].equals("Success") && batNoArr2[0].equals("Success")) {

						System.out.println("BATHC NO AND POLICY NO GENERATED");
						
						inProposalsModel.setPprsta("INAC");
						inProposalsModel.setLockin(new Date());
						// inProposalsModel.setIcpdat(new Date());

						// inProposalDao.save(inProposalsModel);
						
						System.out.println("PROPOSAL STATUS CHANGE" + inProposalsModel.toString());

						InProposalsModel proposalsModelNew = getProposalPolicyStage(inProposalsModel, numberGen[1],
								saveReceiptDto);

						// inProposalDao.save(proposalsModelNew);

						//System.out.println("proposal save done");
						
						System.out.println("NEW PROPOSAL CREATED" + proposalsModelNew.toString());

						Integer updatedSeqNo = proposalsModelNew.getInProposalsModelPK().getPrpseq();
						seqNo = updatedSeqNo;

						List<InPropAddBenefitModel> addBenefitModels = addBenefictCustomDao.getBenefByPprSeq(pprNo,
								seqNo);
						if (addBenefitModels != null && !addBenefitModels.isEmpty()) {
							addBenefitModels = incrementSeqAddBenef(addBenefitModels, updatedSeqNo);
							
							System.out.println("ADD BENEFICT" + addBenefitModels.toString());
							
							// addBenefictDao.save(addBenefitModels);
						}

						// //System.out.println("benef save done");

						List<InPropFamDetailsModel> famDetailsModels = famDetailsCustomDao
								.getFamilyByPprNoAndSeqNo(pprNo, seqNo);
						if (famDetailsModels != null && !famDetailsModels.isEmpty()) {
							famDetailsModels = incrementSeqFamDetails(famDetailsModels, updatedSeqNo);
							// famDetailsDao.save(famDetailsModels);
							System.out.println("FAM DETAILS" + famDetailsModels.toString());
						}

//					//System.out.println("fam save done");

						List<InPropLoadingModel> inPropLoadingModels = propLoadingCustomDao
								.getPropLoadingBuPprNumAndSeq(pprNo, seqNo);
						if (inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {
							inPropLoadingModels = getInPropLoadings(inPropLoadingModels, updatedSeqNo);
							// propLoadingDao.save(inPropLoadingModels);
							System.out.println("PROP_LOADING" + inPropLoadingModels.toString());
						}

						// //System.out.println("pro loading save done");

						List<InPropMedicalReqModel> inPropMedicalReqModels = propMedicalReqCustomDao
								.getMedicalReqByPprNoAndSeq(pprNo, seqNo);
						if (inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {
							inPropMedicalReqModels = incrementPropMedical(inPropMedicalReqModels, updatedSeqNo);
							// propMedicalReqDao.save(inPropMedicalReqModels);
							System.out.println("MEDICALS" + inPropMedicalReqModels.toString());
						}

						// //System.out.println("propMedi save done");

						List<InPropNomDetailsModel> propNomDetailsModels = propNomDetailsCustomDao
								.getNomByPprNoAndPprSeq(pprNo, seqNo);
						if (propNomDetailsModels != null && !propNomDetailsModels.isEmpty()) {
							propNomDetailsModels = incrementPropNomDetailsSeq(propNomDetailsModels, updatedSeqNo);
							// propNomDetailsDao.save(propNomDetailsModels);
							System.out.println("NOMINEE" + propNomDetailsModels.toString());
						}

						// //System.out.println("nominee save done");

						List<InPropPrePolsModel> inPropPrePolsModels = propPrePolsCustomDao
								.getPrePolByPprNoAndPprSeq(pprNo, seqNo);
						if (inPropPrePolsModels != null && !inPropPrePolsModels.isEmpty()) {
							inPropPrePolsModels = incrementPropPolSeq(inPropPrePolsModels, updatedSeqNo);
							// propPrePolsDao.save(inPropPrePolsModels);
							System.out.println("PROPPERPOL" + inPropPrePolsModels.toString());

						}

						// //System.out.println("proposalPrePol save done");

						List<InPropSchedulesModel> propSchedulesModels = propScheduleCustomDao
								.getScheduleBuPprNoAndSeqNo(pprNo, seqNo);
						if (propSchedulesModels != null && !propSchedulesModels.isEmpty()) {
							propSchedulesModels = incremenntScheduleSeq(propSchedulesModels, updatedSeqNo);
							// propScheduleDao.save(propSchedulesModels);
							System.out.println("SCHEDULES" + propSchedulesModels.toString());
						}

						// //System.out.println("proposal Schedule save done");

						List<InPropSurrenderValsModel> propSurrenderValsModels = surrenderValCustomDao
								.getSurrenderValByInpprNoAndSeq(pprNo, seqNo);
						if (propSurrenderValsModels != null && !propSurrenderValsModels.isEmpty()) {
							propSurrenderValsModels = incrementSurrenderVals(propSurrenderValsModels, updatedSeqNo,
									proposalsModelNew.getPolnum());
							// surrenderValDao.save(propSurrenderValsModels);
							System.out.println("SURRENDER" + propSurrenderValsModels.toString());
						}

						// //System.out.println("proposal surrender Val save done");

						InShortPremiumModelPK inShortPremiumModelPK = new InShortPremiumModelPK();
						inShortPremiumModelPK.setPrdcod(inProposalsModel.getPrdcod());
						inShortPremiumModelPK.setSbucod("450");

						// Double recovery = proposalL3Dtos.get(0).getRecovery();

						Double hrbamt = commonethodUtility.getHrbAmt(addBenefitModels);

						List<InBillingTransactionsModel> setoffList = setoffService.setoff(proposalsModelNew, userCode,
								locCode, saveReceiptDto, deposit, hrbamt, proposalL3Dtos.get(0), "NEW",
								Integer.parseInt(batNoArr2[1]));
						
						System.out.println("SETOFF LIST SIZE : " + setoffList.size());
						
						//System.out.println("SETOFF : setoffList : "  + setoffList.size());

						// inBillingTransactionDao.save(setoffList);


						try {

							saveData(inProposalsModel, proposalsModelNew, addBenefitModels, famDetailsModels,
									inPropLoadingModels, inPropMedicalReqModels, propNomDetailsModels,
									inPropPrePolsModels, propSchedulesModels, propSurrenderValsModels, setoffList);
							
							System.out.println("SETOFF : settoff list saved");

						} catch (Exception e) {
							
							System.out.println("SETOFF : settoff list error");
							e.printStackTrace();
						}

					}
				} else {
					System.out.println("FAIL CHECK POLICY");
				}
			} else {
				System.out.println("FAIL CHECK POLICY");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	private void saveData(InProposalsModel inProposalsModel, InProposalsModel proposalsModelNew,
			List<InPropAddBenefitModel> addBenefitModels, List<InPropFamDetailsModel> famDetailsModels,
			List<InPropLoadingModel> inPropLoadingModels, List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropNomDetailsModel> propNomDetailsModels, List<InPropPrePolsModel> inPropPrePolsModels,
			List<InPropSchedulesModel> propSchedulesModels, List<InPropSurrenderValsModel> propSurrenderValsModels,
			List<InBillingTransactionsModel> setoffList) throws Exception {

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
			SaveReceiptDto saveReceiptDto) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String icpDate = "";
		String expDate = "";

		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(new Date());

		icpDate += calendar1.get(Calendar.YEAR) + "-" + (calendar1.get(Calendar.MONTH) + 1) + "-";

		expDate += (calendar1.get(Calendar.YEAR) + inProposalsModel.getToptrm()) + "-"
				+ (calendar1.get(Calendar.MONTH) + 1) + "-";

		if (calendar1.get(Calendar.DATE) > 28) {
			expDate += "28";
			icpDate += "28";
		} else {
			expDate += Integer.toString(calendar1.get(Calendar.DATE));
			icpDate += Integer.toString(calendar1.get(Calendar.DATE));
		}

		//System.out.println("ICP DATE : " + icpDate);
		//System.out.println("EXP DATE : " + expDate);

		InProposalsModelPK pk = new InProposalsModelPK();
		
		pk.setDoccod(inProposalsModel.getInProposalsModelPK().getDoccod());
		pk.setLoccod(inProposalsModel.getInProposalsModelPK().getLoccod());
		pk.setPprnum(inProposalsModel.getInProposalsModelPK().getPprnum());
		pk.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq() + 1);
		pk.setSbucod(inProposalsModel.getInProposalsModelPK().getSbucod());
		
		
		InProposalsModel model = new InProposalsModel();
		
		model.setInProposalsModelPK(pk);
		
		model.setAccnum(inProposalsModel.getAccnum());
		model.setAdmfee(inProposalsModel.getAdmfee());
		model.setAdvcod(inProposalsModel.getAdvcod());
		model.setAgmcod(inProposalsModel.getAgmcod());
		model.setBan_no(inProposalsModel.getBan_no());
		model.setBassum(inProposalsModel.getBassum());
		model.setBrncod(inProposalsModel.getBrncod());
		model.setCanadf(inProposalsModel.getCanadf());
		model.setCanmdc(inProposalsModel.getCanmdc());
		model.setCanoth(inProposalsModel.getCanoth());
		model.setChgtyp(inProposalsModel.getChgtyp());
		model.setComdat(dateFormat.parse(icpDate));
		model.setCreaby(AppConstant.SYSTEM_CREATE);
		model.setCreadt(new Date());
		model.setCrmnum(inProposalsModel.getCrmnum());
		model.setCrmsts(inProposalsModel.getCrmsts());
		model.setCscode(inProposalsModel.getCscode());
		model.setCurusr(inProposalsModel.getCurusr());
		model.setDoclvl(inProposalsModel.getDoclvl());
		model.setEdttim(inProposalsModel.getEdttim());
		model.setEndmod(inProposalsModel.getEndmod());
		model.setEndnum(inProposalsModel.getEndnum());
		model.setEnfdat(inProposalsModel.getEnfdat());
		model.setExclut(inProposalsModel.getExclut());
		model.setExpdat(dateFormat.parse(expDate));
		model.setGrppol(inProposalsModel.getGrppol());
		model.setGrsprm(inProposalsModel.getGrsprm());
		model.setHighcm(inProposalsModel.getHighcm());
		model.setIcpdat(dateFormat.parse(icpDate));
		model.setInsnum(inProposalsModel.getInsnum());
		model.setIntrat(inProposalsModel.getIntrat());
		model.setInvpos(inProposalsModel.getInvpos());
		model.setJlfsex(inProposalsModel.getJlfsex());
		model.setLifpos(inProposalsModel.getLifpos());
		model.setLinmon(inProposalsModel.getLinmon());
		model.setLinyer(inProposalsModel.getLinyer());
		model.setLockin(inProposalsModel.getLockin());
		model.setLpsdat(inProposalsModel.getLpsdat());
		model.setLsripd(inProposalsModel.getLsripd());
		model.setLstchd(inProposalsModel.getLstchd());
		model.setMnagad(inProposalsModel.getMnagad());
		model.setMthinc(inProposalsModel.getMthinc());
		model.setNetref(inProposalsModel.getNetref());
		model.setNewnic(inProposalsModel.getNewnic());
		model.setNtitle(inProposalsModel.getNtitle());
		model.setNumchl(inProposalsModel.getNumchl());
		model.setOcupat(inProposalsModel.getOcupat());
		model.setOldprm(inProposalsModel.getOldprm());
		model.setOtham1(inProposalsModel.getOtham1());
		model.setOtham2(inProposalsModel.getOtham2());
		model.setOtham3(inProposalsModel.getOtham3());
		model.setOtham4(inProposalsModel.getOtham4());
		model.setPayamt(inProposalsModel.getPayamt());
		model.setPaymth(inProposalsModel.getPaymth());
		model.setPaynam(inProposalsModel.getPaynam());
		model.setPaytrm(inProposalsModel.getPaytrm());
		model.setPoldat(new Date());
		model.setPolfee(inProposalsModel.getPolfee());
		model.setPolnum(polNo);
		model.setPoltyp(inProposalsModel.getPoltyp());
		model.setPpdad1(inProposalsModel.getPpdad1());
		model.setPpdad2(inProposalsModel.getPpdad2());
		model.setPpdad3(inProposalsModel.getPpdad3());
		model.setPpdani(inProposalsModel.getPpdani());
		model.setPpdcst(inProposalsModel.getPpdcst());
		model.setPpddob(inProposalsModel.getPpddob());
		model.setPpdeml(inProposalsModel.getPpdeml());
		model.setPpdini(inProposalsModel.getPpdini());
		model.setPpdmob(inProposalsModel.getPpdmob());
		model.setPpdnag(inProposalsModel.getPpdnag());
		model.setPpdnam(inProposalsModel.getPpdnam());
		model.setPpdndu(inProposalsModel.getPpdndu());
		model.setPpdnic(inProposalsModel.getPpdnic());
		model.setPpdocu(inProposalsModel.getPpdocu());
		model.setPpdsex(inProposalsModel.getPpdsex());
		model.setPpdtel(inProposalsModel.getPpdtel());
		model.setPprsta(AppConstant.POLICY_STATUS_PLISU);
		model.setPrpdat(inProposalsModel.getPrpdat());
		model.setPspndt(inProposalsModel.getPspndt());
		model.setPrflng(inProposalsModel.getPrflng());
		model.setPrdnam(inProposalsModel.getPrdnam());
		model.setPremum(inProposalsModel.getPremum());
		model.setPrflng(inProposalsModel.getPrflng());
		model.setPrmhlf(inProposalsModel.getPrmhlf());
		model.setPrmhlt(inProposalsModel.getPrmhlt());
		model.setPrmmth(inProposalsModel.getPrmmth());
		model.setPrmmtt(inProposalsModel.getPrmmtt());
		model.setPrmqat(inProposalsModel.getPrmqat());
		model.setPrmqtt(inProposalsModel.getPrmqtt());
		model.setPrmyer(inProposalsModel.getPrmyer());
		model.setPrmyet(inProposalsModel.getPrmyet());
		model.setPrncnt(inProposalsModel.getPrncnt());
		model.setProsta(AppConstant.POLICY_STATUS_PLISU);
		model.setPrpdat(inProposalsModel.getPrpdat());
		model.setPspndt(inProposalsModel.getPspndt());
		model.setPspntp(inProposalsModel.getPspntp());
		model.setPspnvl(inProposalsModel.getPspnvl());
		model.setQuonum(inProposalsModel.getQuonum());
		model.setQuosta(inProposalsModel.getQuosta());
		model.setRevlof(inProposalsModel.getRevlof());
		model.setRlftrm(inProposalsModel.getRlftrm());
		model.setSagnxt(inProposalsModel.getSagnxt());
		model.setSeqnum(inProposalsModel.getSeqnum());
		model.setShighc(inProposalsModel.getShighc());
		model.setSeqnum(inProposalsModel.getSeqnum());
		model.setShighc(inProposalsModel.getShighc());
		model.setSinprm(inProposalsModel.getSinprm());
		model.setSmksta(inProposalsModel.getSmksta());
		model.setSndapp(inProposalsModel.getSndapp());
		model.setSpagad(inProposalsModel.getSpagad());
		model.setSpoani(inProposalsModel.getSpoani());
		model.setSpodob(inProposalsModel.getSpodob());
		model.setSpoeml(inProposalsModel.getSpoeml());
		model.setSpoini(inProposalsModel.getSpoini());
		model.setSpomob(inProposalsModel.getSpomob());
		model.setSponam(inProposalsModel.getSponam());
		model.setSpondu(inProposalsModel.getSpondu());
		model.setSponic(inProposalsModel.getSponic());
		model.setSpoocu(inProposalsModel.getSpoocu());
		model.setSpotel(inProposalsModel.getSpotel());
		model.setStitle(inProposalsModel.getStitle());
		model.setSumrkm(inProposalsModel.getSumrkm());
		model.setSumrks(inProposalsModel.getSumrks());
		model.setSwighk(inProposalsModel.getSwighk());
		model.setTaxamt(inProposalsModel.getTaxamt());
		model.setToptrm(inProposalsModel.getToptrm());
		model.setTrgprm(inProposalsModel.getTrgprm());
		model.setTxndat(inProposalsModel.getTxndat());
		model.setUnddec(inProposalsModel.getUnddec());
		model.setWighkg(inProposalsModel.getWighkg());

		return model;
	}

//	public InBillingTransactionsModel createInvoice(InProposalsModel inProposalsModel,
//			InBillingTransactionsModel previousInvoice, String user, String loc) throws Exception {
//
//		String[] numberGen = numberGenerator.generateNewId("", "", "PRMISQ", "");
//		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(inProposalsModel.getAdvcod());
//
//		AgentMastModel agentMastModel = agentMastModels.get(0);
//
//		if (numberGen[0].equals("Success")) {
//
//			InBillingTransactionsModelPK billingTransactionsModelPK = new InBillingTransactionsModelPK();
//
//			billingTransactionsModelPK.setDoccod("PRMI");
//			billingTransactionsModelPK.setDocnum(Integer.parseInt(numberGen[1]));
//			billingTransactionsModelPK.setLinnum(0);
//			billingTransactionsModelPK.setLoccod(loc);
//			billingTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
//			billingTransactionsModelPK.setTxndat(new Date());
//
//			InBillingTransactionsModel billingTransactionsModel = new InBillingTransactionsModel();
//
//			billingTransactionsModel.setBillingTransactionsModelPK(billingTransactionsModelPK);
//
//			billingTransactionsModel.setAdmfee(AppConstant.ZERO_TWO_DECIMAL);
//			billingTransactionsModel.setAdvcod(Integer.parseInt(inProposalsModel.getAdvcod()));
//			billingTransactionsModel.setAgncls(agentMastModel.getAgncls());
//			if (previousInvoice != null) {
//				billingTransactionsModel.setAmount(
//						inProposalsModel.getTaxamt() + inProposalsModel.getAdmfee() + inProposalsModel.getTotprm());
//			} else {
//				billingTransactionsModel.setAmount(inProposalsModel.getTaxamt() + inProposalsModel.getAdmfee()
//						+ inProposalsModel.getTotprm() + inProposalsModel.getPolfee());
//			}
//
//			billingTransactionsModel.setBrncod(agentMastModel.getLocation());
//			billingTransactionsModel.setChqrel("N");
//			billingTransactionsModel.setComiss(AppConstant.ZERO_FOR_DECIMAL);
//			billingTransactionsModel.setComper(AppConstant.ZERO_FOR_DECIMAL);
//			billingTransactionsModel.setCreaby(user);
//			billingTransactionsModel.setCreadt(new Date());
//			try {
//				billingTransactionsModel.setCscode(Integer.parseInt(inProposalsModel.getCscode()));
//			} catch (Exception e) {
//			}
//			billingTransactionsModel.setDepost(AppConstant.ZERO_TWO_DECIMAL);
//			billingTransactionsModel.setGlintg("N");
//
//			billingTransactionsModel.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
//			billingTransactionsModel.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
//			billingTransactionsModel.setInsnum(0);
//			billingTransactionsModel.setLockin(new Date());
//			billingTransactionsModel.setOldprm(AppConstant.ZERO_FOR_DECIMAL);
//
//			billingTransactionsModel.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));
//			if (previousInvoice != null) {
//				billingTransactionsModel.setPolfee(AppConstant.ZERO_TWO_DECIMAL);
//			} else {
//				billingTransactionsModel.setPolfee(inProposalsModel.getPolfee());
//			}
//
//			billingTransactionsModel.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
//			billingTransactionsModel.setPrdcod(inProposalsModel.getPrdcod());
//			billingTransactionsModel.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
//			billingTransactionsModel.setRefdoc(billingTransactionsModelPK.getDoccod());
//			billingTransactionsModel.setRefnum(billingTransactionsModelPK.getDocnum());
//			billingTransactionsModel.setSrcdoc(null);
//			billingTransactionsModel.setSrcnum(null);
//			billingTransactionsModel.setTaxamt(inProposalsModel.getTaxamt());
//			billingTransactionsModel.setToptrm(inProposalsModel.getToptrm());
//			billingTransactionsModel.setTxntyp("INVOICE");
////			if (agentMastModel.getAgncls().equalsIgnoreCase("IC")) {
//			billingTransactionsModel.setUnlcod(agentMastModel.getUnlcod());
////			}
////			if (agentMastModel.getAgncls().equalsIgnoreCase("UNL")) {
////				billingTransactionsModel.setUnlcod(agentMastModel.getBrnmanager());
////			}
//
//			if (previousInvoice != null) {
//				if (previousInvoice.getTxnmth() >= 12) {
//					billingTransactionsModel.setTxnyer(previousInvoice.getTxnyer() + 1);
//					billingTransactionsModel.setTxnmth(1);
//				} else {
//					billingTransactionsModel.setTxnyer(previousInvoice.getTxnyer());
//					billingTransactionsModel.setTxnmth(previousInvoice.getTxnmth() + 1);
//				}
//
//			} else {
//				try {
//					InBillingTransactionsModel model = billingTransactionsCustomDao
//							.getTxnYearDate(inProposalsModel.getInProposalsModelPK().getPprnum());
//
//					if (model.getTxnmth() >= 12) {
//						billingTransactionsModel.setTxnyer(model.getTxnyer() + 1);
//						billingTransactionsModel.setTxnmth(1);
//					} else {
//						billingTransactionsModel.setTxnyer(model.getTxnyer());
//						billingTransactionsModel.setTxnmth(model.getTxnmth() + 1);
//					}
//				} catch (Exception e) {
//					billingTransactionsModel.setTxnyer(Calendar.getInstance().get(Calendar.YEAR));
//					billingTransactionsModel.setTxnmth(Calendar.getInstance().get(Calendar.MONTH) + 1);
//				}
//			}
//			return billingTransactionsModel;
//
//		} else {
//			return null;
//		}
//
//	}

	@Override
	public ProposalNoSeqNoDto getProposalNoSeqNoDto(String pprNo) throws Exception {
		ProposalNoSeqNoDto proposalNoSeqNoDtos = null;

		List<ProposalNoSeqNoModel> list = inProposalCustomDao.getProposalNoSeqNoModel(pprNo);

		//System.out.println(list.size());

		if (list != null && !(list.isEmpty())) {
			proposalNoSeqNoDtos = getProposalNoSeqNoDto(list.get(0));
		}

		return proposalNoSeqNoDtos;
	}

	@Override
	public List<SearchDto> getSearch(String value, String type, String receiptType) throws Exception {

		String sql = "";

		if (receiptType.equals("pol")) {
			switch (type) {
			case "ppr":

				sql = " pprnum like '" + value + "%'  and pprsta <> 'INAC' and polnum is not null ";

				break;
			case "quo":

				sql = " pprsta <> 'INAC' and quonum like '" + value + "%' and polnum is not null ";

				break;
			case "cnm":

				sql = " pprsta <> 'INAC' and (ppdini like '%" + value + "%' OR ppdnam like '" + value
						+ "%') and polnum is not null  ";

				break;
			case "nic":

				sql = "(ppdnic = '" + value + "' or sponic = '" + value
						+ "') and pprsta <> 'INAC' and polnum is not null";

				break;
			case "ppl":

				sql = "polnum like '" + value + "%' and pprsta <> 'INAC'";

				break;

			default:
				break;
			}
		}

		if (receiptType.equals("ppr")) {
			switch (type) {
			case "ppr":

				sql = " pprnum like '" + value + "%'  and pprsta not in ('PLISU', 'PLAPS')";

				break;
			case "quo":

				sql = " pprsta <> 'INAC' and pprsta not in ('PLISU', 'PLAPS') and quonum like '" + value + "%'";

				break;
			case "cnm":

				sql = " pprsta <> 'INAC' and pprsta not in ('PLISU', 'PLAPS') and (ppdini like '%" + value
						+ "%' OR ppdnam like '" + value + "%')";

				break;
			case "nic":

				sql = "(ppdnic = '" + value + "' or sponic = '" + value
						+ "') and pprsta <> 'INAC' and pprsta not in ('PLISU', 'PLAPS')";

				break;
			case "ppl":

				sql = "polnum like '" + value + "%' and pprsta not in ('PLISU', 'PLAPS')";

				break;

			default:
				break;
			}
		}

		List<InProposalsModel> inProposalsModels = null;

		if (!sql.isEmpty()) {
			inProposalsModels = inProposalCustomDao.searchProposal(sql);
		}

		if (!inProposalsModels.isEmpty()) {
			List<SearchDto> dtos = new ArrayList<>();

			inProposalsModels.forEach(e -> {
				dtos.add(getSearchDto(e));
			});

			return dtos;
		}

		return null;
	}

	private SearchDto getSearchDto(InProposalsModel e) {
		SearchDto dto = new SearchDto();
		dto.setCustName(e.getPpdini());
		dto.setNic(e.getPpdnic());
		dto.setPolnum(e.getPolnum());
		dto.setPprnum(e.getInProposalsModelPK().getPprnum());
		dto.setProduct(e.getPrdcod());
		dto.setQuonum(e.getQuonum().toString());
		dto.setSeqNum(e.getInProposalsModelPK().getPrpseq());
		return dto;
	}

	@Override
	public ProposalNoSeqNoDto getPolicyNoSeqNoDto(String polNum) throws Exception {
		ProposalNoSeqNoDto proposalNoSeqNoDtos = null;

		List<ProposalNoSeqNoModel> list = inProposalCustomDao.getPolicyNoSeqNoModel(polNum);

		//System.out.println(list.size());

		if (list != null && !(list.isEmpty())) {
			proposalNoSeqNoDtos = getProposalNoSeqNoDto(list.get(0));
		}

		return proposalNoSeqNoDtos;
	}
}