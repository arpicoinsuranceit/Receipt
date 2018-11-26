package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;

import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.PolicyReceiptService;
import org.arpico.groupit.receipt.service.SetoffService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PolicyReceiptServiceImpl implements PolicyReceiptService {

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private InAgentMastDao inAgentMastDao;

	@Autowired
	private CommonMethodsUtility commonethodUtility;

	@Autowired
	private InTransactionsDao inTransactionDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private InTransactionService inTransactionService;

	@Autowired
	private RmsUserDao rmsUserDao;
	
	@Autowired
	private JwtDecoder decoder;


	/*
	 * @Autowired private CommisDaoCustom commisDaoCustom;
	 */

	@Autowired
	SetoffService setoffService;

	@Autowired
	private ItextReceipt itextReceipt;

	@Override
	public List<ProposalNoSeqNoDto> getPolicyNoSeqNoDtoList(String val) throws Exception {

		List<ProposalNoSeqNoDto> proposalNoSeqNoDtos = new ArrayList<ProposalNoSeqNoDto>();

		List<ProposalNoSeqNoModel> list = inProposalCustomDao.getPolicyNoSeqNoModelList(val);

		for (ProposalNoSeqNoModel proposalNoSeqNoModel : list) {
			proposalNoSeqNoDtos.add(getPolicyNoSeqNoDto(proposalNoSeqNoModel));

		}

		return proposalNoSeqNoDtos;
	}

	private ProposalNoSeqNoDto getPolicyNoSeqNoDto(ProposalNoSeqNoModel proposalNoSeqNoModel) {
		ProposalNoSeqNoDto dto = new ProposalNoSeqNoDto();
		dto.setProposalNo(proposalNoSeqNoModel.getProposalNo());
		dto.setSeqNo(proposalNoSeqNoModel.getSeqNo());
		return dto;
	}

	@Override
	public ProposalBasicDetailsDto getBasicDetails(Integer polNo, Integer seqNo) throws Exception {
		InProposalBasicsModel basicsModel = inProposalCustomDao.geInPolicyBasics(polNo, seqNo);

		List<LastReceiptSummeryDto> dtos = inTransactionService.getLastReceiptsByPolNo(polNo.toString());

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
	public ResponseEntity<Object> savePolicyReceipt(SaveReceiptDto saveReceiptDto) throws Exception {

		ResponseDto dto = null;

		InProposalsModel inProposalsModel = inProposalCustomDao.getProposalBuPolicy(saveReceiptDto.getPolId(),
				saveReceiptDto.getPolSeq());

		String agentCode = decoder.generate(saveReceiptDto.getToken());

		String locCode = decoder.generateLoc(saveReceiptDto.getToken());

		if (locCode != null) {

			InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
					saveReceiptDto, agentCode, locCode);

			inTransactionsModel.getInTransactionsModelPK().setDoccod("RCPL");
			// inTransactionDao.save(inTransactionsModel);

			System.out.println("transaction model save");

			InBillingTransactionsModel deposit = commonethodUtility.getInBillingTransactionModel(inProposalsModel,
					saveReceiptDto, inTransactionsModel);
			deposit.setCreaby(agentCode);
			deposit.setPolnum(inTransactionsModel.getPolnum());
			deposit.getBillingTransactionsModelPK().setDoccod("RCPL");
			deposit.setRefdoc("RCPL");
			deposit.setSrcdoc("RCPL");
			deposit.setTaxamt(0.0);
			deposit.setAdmfee(0.0);
			deposit.setPolfee(0.0);
			deposit.setTxntyp("POLDEP");

			// inBillingTransactionDao.save(deposit);
			ReceiptPrintDto printDto = null;
			try {
				saveReceipt(inTransactionsModel, deposit);
				if (!saveReceiptDto.equals("CQ")) {
					setoff(inProposalsModel, agentCode, locCode, saveReceiptDto, deposit, 0.0);
				}
				try {
					printDto = getReceiptPrintDto(inProposalsModel, inTransactionsModel, agentCode, locCode, false);
				} catch (Exception e) {
					e.printStackTrace();
					dto = new ResponseDto();
					dto.setCode("500");
					dto.setStatus("Error");
					dto.setMessage("Error at print receipt");
				}

				dto = new ResponseDto();
				dto.setCode("200");
				dto.setStatus("Success");
				dto.setMessage(deposit.getBillingTransactionsModelPK().getDocnum().toString());
				dto.setData(itextReceipt.createReceipt(printDto));

				return new ResponseEntity<>(dto, HttpStatus.OK);
				
			} catch (Exception e) {
				dto = new ResponseDto();
				dto.setCode("500");
				dto.setStatus("Error");
				dto.setMessage("Error at receipt Saving");
				dto.setData(itextReceipt.createReceipt(printDto));
				return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		dto = new ResponseDto();
		dto.setCode("204");
		dto.setStatus("Error");
		dto.setMessage("Location Not Found");

		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}

	@Transactional
	private void saveReceipt(InTransactionsModel inTransactionsModel, InBillingTransactionsModel deposit) {
		inTransactionDao.save(inTransactionsModel);
		inBillingTransactionDao.save(deposit);
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
		printDto.setPolNum(Integer.parseInt(inProposalsModel.getPolnum()));
		printDto.setDocCode(inTransactionsModel.getInTransactionsModelPK().getDoccod());
		printDto.setDocNum(inTransactionsModel.getInTransactionsModelPK().getDocnum());
		printDto.setLocation(locCode);
		printDto.setPayMode(inTransactionsModel.getPaymod());
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

	private void setoff(InProposalsModel inProposalsModel, String agentCode, String locCode,
			SaveReceiptDto saveReceiptDto, InBillingTransactionsModel deposit, double d) throws Exception {
		setoffService.setoff(inProposalsModel, agentCode, locCode, saveReceiptDto, deposit, 0.0);

	}

	@Override
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
				billingTransactionsModel.setAmount(inProposalsModel.getTotprm());
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
			billingTransactionsModel.setPolnum(Integer.parseInt(inProposalsModel.getPolnum()));
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
//			if (agentMastModel.getAgncls().equalsIgnoreCase("IC")) {
				billingTransactionsModel.setUnlcod(agentMastModel.getUnlcod());
//			}
//			if (agentMastModel.getAgncls().equalsIgnoreCase("UNL")) {
//				billingTransactionsModel.setUnlcod(agentMastModel.getBrnmanager());
//			}

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

			inBillingTransactionDao.save(billingTransactionsModel);

			return billingTransactionsModel;

		} else {
			return null;
		}

	}

}
