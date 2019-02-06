package org.arpico.groupit.receipt.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.arpico.groupit.receipt.client.UserManagementClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InLoanTransactionCustomDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.RmsDocTxndCustomDao;
import org.arpico.groupit.receipt.dao.RmsDocTxnmCustomDao;
import org.arpico.groupit.receipt.dao.RmsItemMasterCustomDao;
import org.arpico.groupit.receipt.dao.RmsRecdCustomDao;
import org.arpico.groupit.receipt.dao.RmsRecmCustomDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.AccountGLDto;
import org.arpico.groupit.receipt.dto.InventoryDetailsDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.MisGlItemModel;
import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.arpico.groupit.receipt.model.RmsItemMasterModel;
import org.arpico.groupit.receipt.model.RmsRecmModel;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.ReprintService;
import org.arpico.groupit.receipt.util.CurrencyFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@PropertySource("classpath:application.properties")
public class ReprintServiceImpl implements ReprintService {

	@Autowired
	private ItextReceipt itextReceipt;

	@Autowired
	private InTransactionCustomDao inTransactionCustomDao;

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private RmsUserDao rmsUserDao;

	@Autowired
	private UserManagementClient userManagementClient;

	@Autowired
	private RmsDocTxnmCustomDao rmsDocTxnmCustomDao;

	@Autowired
	private RmsDocTxndCustomDao rmsDocTxndCustomDao;

	@Autowired
	private CurrencyFormat currencyFormat;

	@Autowired
	private RmsItemMasterCustomDao rmsItemMasterCustomDao;

	@Autowired
	private RmsRecdCustomDao rmsRecdCustomDao;

	@Autowired
	private RmsRecmCustomDao rmsRecmCustomDao;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;

	@Value("${gl_acc_param}")
	private String accounts;

	@Autowired
	private InLoanTransactionCustomDao inLoanTransactionCustomDao;

	@Override
	public ResponseEntity<Object> getReprint(String docCode, Integer receiptNo, String token) throws Exception {

		String agentCode = new JwtDecoder().generate(token);

		String agentBranch = userManagementClient.getBranch(agentCode);
		
		System.out.println(agentBranch);

		String[] tempArr = agentBranch.split(",");

		List<String> agentBranchs = new ArrayList<>(Arrays.asList(tempArr));

		byte[] pdf = null;

		try {

			switch (docCode) {
			case "RCNB":

				pdf = getFromInTrans(docCode, receiptNo, agentBranchs);

				break;
			case "RCPL":

				pdf = getFromInTrans(docCode, receiptNo, agentBranchs);
				break;
			case "RCPP":

				pdf = getFromInTrans(docCode, receiptNo, agentBranchs);
				break;
			case "OIIS":

				pdf = getFromDocTxnm(docCode, receiptNo, agentBranchs);
				break;
			case "GLRC":

				pdf = getFromRecm(docCode, receiptNo, agentBranchs);
				break;

			case "RCLN":

				pdf = getFromInLoanTrans(docCode, receiptNo, agentBranchs);

				break;

			default:
				break;
			}

		} catch (Exception e) {
			ResponseDto responseDto = new ResponseDto();
			responseDto.setCode("404");
			responseDto.setStatus("Error");
			if (e.getMessage().equals("BranchIlligal")) {
				//System.out.println("Error 1");
				responseDto.setMessage("Receipt Branch not Equal");
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			}
			if (e.getMessage().equals("NotFoundReceipt")) {
				//System.out.println("Error 2");
				responseDto.setMessage("Receipt Not Found");
				return new ResponseEntity<>(responseDto, HttpStatus.OK);
			}
		}

		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode("200");
		responseDto.setStatus("Success");
		responseDto.setMessage(receiptNo.toString());
		responseDto.setData(pdf);

		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	 byte[] getFromInTrans(String docCode, Integer receiptNo, List<String> agentBranchs) throws Exception {

		InTransactionsModel inTransactionsModel = null;
		try {
			inTransactionsModel = inTransactionCustomDao.getTransaction(docCode, receiptNo);
			
			//System.out.println(inTransactionsModel.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("NotFoundReceipt");
		}

		if (!agentBranchs.contains("HO")) {
			if (!agentBranchs.contains(inTransactionsModel.getInTransactionsModelPK().getLoccod())) {
				throw new RuntimeException("BranchIlligal");
			}
		}

		List<InBillingTransactionsModel> inBillingTransactionsModels = billingTransactionsCustomDao
				.getSetoffsForRcpl(receiptNo, docCode);

		List<HashMap<String, String>> setoffList = new ArrayList<>();

		inBillingTransactionsModels.forEach(e -> {
			HashMap<String, String> setoff = new HashMap<>();

			setoff.put("txnMonth", Integer.toString(e.getTxnmth()));
			setoff.put("txnYear", Integer.toString(e.getTxnyer()));
			setoff.put("amount", Double.toString(e.getDepost()));

			setoffList.add(setoff);

		});

//		InProposalsModel inProposalsModel = inProposalCustomDao
//				.getProposal(Integer.parseInt(inTransactionsModel.getPprnum()), inTransactionsModel.getSeqnum());
		
		InProposalsModel inProposalsModel = inProposalCustomDao.getProposalFromPprnum(Integer.parseInt(inTransactionsModel.getPprnum()));

		ReceiptPrintDto receiptPrintDto = getReceiptPrintDtoInTran(inProposalsModel, inTransactionsModel);
		receiptPrintDto.setSetOffs(setoffList);

		return itextReceipt.createReceipt(receiptPrintDto);

	}

	 byte[] getFromInLoanTrans(String docCode, Integer receiptNo, List<String> agentBranchs) throws Exception {

		InLoanTransactionsModel inLoanTransactionsModel = null;
		try {
			inLoanTransactionsModel = inLoanTransactionCustomDao.getLoanTransaction(docCode, receiptNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("NotFoundReceipt");
		}

		if (!agentBranchs.contains("HO")) {
			if (!agentBranchs.contains(inLoanTransactionsModel.getInLoanTransactionsModelPK().getLoccod())) {
				throw new RuntimeException("BranchIlligal");
			}
		}

		InProposalsModel inProposalsModel = inProposalCustomDao
				.getProposalFromPolnum(inLoanTransactionsModel.getPolnum());

		ReceiptPrintDto receiptPrintDto = getReceiptPrintDtoRCLN(inProposalsModel, inLoanTransactionsModel,
				inLoanTransactionsModel.getAdvcod(),
				inLoanTransactionsModel.getInLoanTransactionsModelPK().getLoccod());

		return itextReceipt.createReceipt(receiptPrintDto);

	}

	 byte[] getFromDocTxnm(String docCode, Integer receiptNo, List<String> agentBranchs) throws Exception {
		RmsDocTxnmModel docTxnmModel = null;
		List<RmsDocTxndModel> docTxndModels = null;
		try {
			docTxnmModel = rmsDocTxnmCustomDao.getModel(docCode, receiptNo);
			docTxndModels = rmsDocTxndCustomDao.getDocTxndModels(docCode, receiptNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("NotFoundReceipt");
		}

		if (docTxndModels != null && docTxndModels.size() > 0) {
			if (!agentBranchs.contains("HO")) {
				if (!agentBranchs.contains(docTxndModels.get(0).getDimm04())) {
					throw new RuntimeException("BranchIlligal");
				}
			}
		} else {
			throw new RuntimeException("BranchIlligal");
		}

		ReceiptPrintDto receiptPrintDto = getReceiptPrintDtoINV(docTxnmModel, docTxndModels);

		return itextReceipt.createReceipt(receiptPrintDto);

	}

	 byte[] getFromRecm(String docCode, Integer receiptNo, List<String> agentBranchs) throws Exception {

		List<String> accountList = new ArrayList<>(Arrays.asList(accounts.split(",")));

		List<MisGlItemModel> glItemModels = null;
		RmsRecmModel recmModel = null;
		String payMode = "";
		try {
			glItemModels = rmsRecdCustomDao.glItemModels(docCode, receiptNo);
			recmModel = rmsRecmCustomDao.getRecm(docCode, receiptNo);
			payMode = rmsRecdCustomDao.getPayMode(docCode, receiptNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("NotFoundReceipt");
		}

		if (!agentBranchs.contains("HO")) {
			if (!agentBranchs.contains(glItemModels.get(0).getBranch())) {
				throw new RuntimeException("BranchIlligal");
			}
		}

		List<MisGlItemModel> glItemModelsNew = new ArrayList<>();

		//System.out.println("glItemModels.size() :  " + glItemModels.size());

		glItemModels.forEach(e -> {

			accountList.forEach(item -> {

				if (item.equals(e.getInterId().toString())) {
					glItemModelsNew.add(e);
				}
			});

		});

		ReceiptPrintDto receiptPrintDto = getReceiptPrintDtoGL(recmModel, glItemModelsNew, payMode);

		return itextReceipt.createReceipt(receiptPrintDto);
	}

	 ReceiptPrintDto getReceiptPrintDtoInTran(InProposalsModel inProposalsModel,
			InTransactionsModel inTransactionsModel) throws Exception {
		
		//System.out.println("inTransactionsModel.getCreadt() : " + inTransactionsModel.getCreadt());
		
		ReceiptPrintDto printDto = new ReceiptPrintDto();

		List<AgentModel> agentModels = agentDao.findAgentByCodeAll(inProposalsModel.getAdvcod());

		//System.out.println(inProposalsModel.getAdvcod());

		String userName = rmsUserDao.getName(inTransactionsModel.getCreaby());

		printDto.setRctStatus("DUP");
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
		printDto.setPolNum(inProposalsModel.getPolnum() != null ? Integer.parseInt(inProposalsModel.getPolnum()) : 0);
		printDto.setDocCode(inTransactionsModel.getInTransactionsModelPK().getDoccod());
		printDto.setDocNum(inTransactionsModel.getInTransactionsModelPK().getDocnum());
		printDto.setLocation(inTransactionsModel.getInTransactionsModelPK().getLoccod());
		printDto.setPayMode(inTransactionsModel.getPaymod());
		printDto.setPropNum(Integer.parseInt(inTransactionsModel.getPprnum()));
		printDto.setQuoNum(inProposalsModel.getQuonum());
		printDto.setQdId(inProposalsModel.getInProposalsModelPK().getPrpseq());
		printDto.setRctDate(inTransactionsModel.getCreadt());
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

	 ReceiptPrintDto getReceiptPrintDtoINV(RmsDocTxnmModel docTxnmModel, List<RmsDocTxndModel> docTxndModels)
			throws Exception {
		ReceiptPrintDto printDto = new ReceiptPrintDto();

		List<RmsItemMasterModel> itemList = new ArrayList<>();

		for (RmsDocTxndModel docTxndModel : docTxndModels) {
			RmsItemMasterModel itemMasterModel = rmsItemMasterCustomDao.findbyId(docTxndModel.getItemCode());
			itemList.add(itemMasterModel);
		}

		List<InventoryDetailsDto> detailsDtos = new ArrayList<>();

		List<AgentModel> agentModels = agentDao.findAgentByCodeAll(docTxnmModel.getRef1());

		String userName = rmsUserDao.getName(docTxnmModel.getCreBy());

		printDto.setAgtCode(Integer.parseInt(docTxnmModel.getRef1()));
		printDto.setAgtName(agentModels.get(0).getAgentName());
		printDto.setAmt(docTxnmModel.getAmtfcu());
		printDto.setAmtInWord(currencyFormat.numberToWords(docTxnmModel.getAmtfcu()));
		printDto.setDocCode(docTxnmModel.getRmsDocTxnmModelPK().getDocCode());
		printDto.setDocNum(docTxnmModel.getRmsDocTxnmModelPK().getDocNo());
		printDto.setLocation(docTxndModels.get(0).getDimm04());
		printDto.setPayMode(docTxnmModel.getRef2());
		printDto.setRctDate(docTxnmModel.getCreDate());
		printDto.setRctStatus("DUP");
		printDto.setRemark(docTxnmModel.getRemarks());
		printDto.setUserName(userName);

		if (docTxnmModel.getRef2().equalsIgnoreCase("04.CHEQUE")
				|| docTxnmModel.getRef2().equalsIgnoreCase("01.CHEQUE")) {
			printDto.setChqNo(Integer.parseInt(docTxnmModel.getRef3()));
		}

		for (RmsDocTxndModel docTxndModel : docTxndModels) {
			InventoryDetailsDto detailsDto = new InventoryDetailsDto();
			detailsDto.setItemCod(docTxndModel.getItemCode());

			itemList.forEach(e -> {
				if (e.getItemCode().equals(docTxndModel.getItemCode())) {
					detailsDto.setUntPrice(e.getUnitPrice());
					detailsDto.setUntPriceTot(
							new BigDecimal(docTxndModel.getQty().intValue()).multiply(new BigDecimal(e.getUnitPrice()))
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
					detailsDto.setItemName(e.getItemName());

				}
			});

			detailsDto.setQty(docTxndModel.getQty().intValue());
			detailsDto.setQtyTot(docTxndModel.getAmtfcu());
			detailsDto.setSubTot(docTxnmModel.getAmtfcu());
			detailsDto.setSubTotInWrd(currencyFormat.numberToWords(docTxnmModel.getAmtfcu()));

			detailsDtos.add(detailsDto);
		}

		printDto.setInventoryDtl(detailsDtos);

		//detailsDtos.forEach(//System.out::println);

		return printDto;
	}

	 ReceiptPrintDto getReceiptPrintDtoGL(RmsRecmModel recmModel, List<MisGlItemModel> glItemModels,
			String payMode) throws Exception {
		ReceiptPrintDto printDto = new ReceiptPrintDto();

		List<AccountGLDto> accounts = new ArrayList<>();

		glItemModels.forEach(e -> {
			AccountGLDto dto = new AccountGLDto();
			dto.setAmount(e.getAmount() < 0 ? e.getAmount() * -1 : e.getAmount());
			dto.setDescription(e.getDescription());
			dto.setId(e.getInterId());
			dto.setRemark(e.getRemark());

			accounts.add(dto);
		});

		printDto.setRctStatus("DUP");
		printDto.setAmt(recmModel.getAmtfcu());
		printDto.setPayMode(payMode);
		printDto.setAmtInWord(currencyFormat.numberToWords(recmModel.getAmtfcu()));
		printDto.setDocCode(recmModel.getRmsRecmModelPK().getDocCode());
		printDto.setDocNum(recmModel.getRmsRecmModelPK().getDocNo());
		printDto.setRctDate(new Date());
		printDto.setLocation(recmModel.getRmsRecmModelPK().getLocCode());
		// printDto.setRemark(recmModel.getRemark());
		printDto.setUserName(recmModel.getCreBy());
		printDto.setCusName(recmModel.getRemark());
		printDto.setLocation(recmModel.getRmsRecmModelPK().getLocCode());
		printDto.setAccounts(accounts);

		return printDto;
	}

	 ReceiptPrintDto getReceiptPrintDtoRCLN(InProposalsModel inProposalsModel,
			InLoanTransactionsModel inTransactionsModel, String agentCode, String locCode) throws Exception {
		ReceiptPrintDto printDto = new ReceiptPrintDto();

		List<AgentModel> agentModels = agentDao.findAgentByCodeAll(inProposalsModel.getAdvcod());

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
		printDto.setPolNum(inTransactionsModel.getPolnum());
		printDto.setDocCode(inTransactionsModel.getInLoanTransactionsModelPK().getDoccod());
		printDto.setDocNum(inTransactionsModel.getInLoanTransactionsModelPK().getDocnum());
		printDto.setLocation(locCode);
		printDto.setPayMode(inTransactionsModel.getPaymod());
		printDto.setPropNum(Integer.parseInt(inTransactionsModel.getPprnum()));
		printDto.setQuoNum(inProposalsModel.getQuonum());
		printDto.setQdId(inProposalsModel.getInProposalsModelPK().getPrpseq());
		printDto.setRctDate(inTransactionsModel.getCreadt());
		printDto.setRctStatus("DUP");
		printDto.setRemark(inTransactionsModel.getRemark());
		printDto.setUserName(userName);
		printDto.setLoanNum(inTransactionsModel.getFclnum());

		if (inTransactionsModel.getChqnum() != null) {
			printDto.setChqNo(Integer.parseInt(inTransactionsModel.getChqnum()));
		}
		if (inTransactionsModel.getChqdat() != null) {
			printDto.setChqDate(new SimpleDateFormat("dd/MM/yyyy").format(inTransactionsModel.getChqdat()));
		}
		if (inTransactionsModel.getChqbnk() != null) {
			printDto.setBankCode(Integer.parseInt(inTransactionsModel.getChqbnk()));
		}

		//System.out.println(printDto);
		return printDto;
	}

}
