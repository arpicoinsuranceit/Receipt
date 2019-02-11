package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InLoanTransactionsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.LoanReceiptDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.pk.InLoanTransactionsModelPK;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.service.LoanReceiptService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanReceiptServiceImpl implements LoanReceiptService{
	
	@Autowired
	private LoanReceiptDao loanReceiptDao;
	
	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;
	
	@Autowired
	private InTransactionService inTransactionService;

	@Autowired
	private InLoanTransactionsDao inLoanTransactionDao;

	@Autowired
	private NumberGenerator numberGenerator;
	
	@Autowired
	private JwtDecoder decoder;
	
	@Autowired
	private AgentDao agentDao;

	@Autowired
	private RmsUserDao rmsUserDao;
	
	@Autowired
	private ItextReceipt itextReceipt;

	@Override
	public List<Integer> findLoanNoByPolnum(String polnum) throws Exception {
		return loanReceiptDao.findLoanNoByPolnum(polnum);
	}
	
	@Override
	public ProposalBasicDetailsDto getBasicDetails(Integer polNo, Integer seqNo) throws Exception {
		InProposalBasicsModel basicsModel = inProposalCustomDao.geInPolicyBasics(polNo, seqNo);

		List<LastReceiptSummeryDto> dtos = inTransactionService.getLastLoanReceiptsByPolNo(polNo.toString());

		ProposalBasicDetailsDto basicDetailsDto = getBasicDetailsDto(basicsModel);
		basicDetailsDto.setAmtPayble(billingTransactionsCustomDao.paybleAmountThisMonth(polNo, "polnum"));
		basicDetailsDto.setLastReceiptSummeryDtos(dtos);
		return basicDetailsDto;
	}
	
	public ProposalBasicDetailsDto getBasicDetailsDto(InProposalBasicsModel basicsModel) {

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
	public ResponseEntity<Object> saveLoanReceipt(SaveReceiptDto saveReceiptDto) throws Exception {

		ResponseDto dto = null;

		InProposalsModel inProposalsModel = inProposalCustomDao.getProposalBuPolicy(saveReceiptDto.getPolId(),
				saveReceiptDto.getPolSeq());

		String agentCode = decoder.generate(saveReceiptDto.getToken());

		String locCode = decoder.generateLoc(saveReceiptDto.getToken());

		if (locCode != null) {

			String[] numberGen = numberGenerator.generateNewId("", "", "RCLNSQ", "");

			if (numberGen[0].equals("Success")) {
				
				InLoanTransactionsModel inLoanTransactionsModel=new InLoanTransactionsModel();
				
				InLoanTransactionsModelPK inLoanTransactionsModelPK=new InLoanTransactionsModelPK();
				
				inLoanTransactionsModelPK.setDoccod("RCLN");
				inLoanTransactionsModelPK.setDocnum(Integer.valueOf(numberGen[1]));
				inLoanTransactionsModelPK.setLinnum(0);
				inLoanTransactionsModelPK.setLoccod(locCode);
				inLoanTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
				
				inLoanTransactionsModel.setInLoanTransactionsModelPK(inLoanTransactionsModelPK);
				inLoanTransactionsModel.setAdvcod(inProposalsModel.getAdvcod());
				inLoanTransactionsModel.setAmtwrd(saveReceiptDto.getPayAmountWord());
				inLoanTransactionsModel.setBancod(saveReceiptDto.getBankCode());
				inLoanTransactionsModel.setBildat(new Date());
				inLoanTransactionsModel.setBilpik("Y");
				inLoanTransactionsModel.setChqrel("N");
				inLoanTransactionsModel.setCompad("Y");
				inLoanTransactionsModel.setCreaby(agentCode);
				inLoanTransactionsModel.setCreadt(new Date());
				inLoanTransactionsModel.setCscode(inProposalsModel.getCscode());
				inLoanTransactionsModel.setFclnum(saveReceiptDto.getLoanNo());
				inLoanTransactionsModel.setGlpik("N");
				inLoanTransactionsModel.setLockin(new Date());
				inLoanTransactionsModel.setNtitle(inProposalsModel.getNtitle());
				inLoanTransactionsModel.setPaymod(saveReceiptDto.getPayMode());
				inLoanTransactionsModel.setPolnum(saveReceiptDto.getPolId());
				inLoanTransactionsModel.setPpdad1(inProposalsModel.getPpdad1());
				inLoanTransactionsModel.setPpdad2(inProposalsModel.getPpdad2());
				inLoanTransactionsModel.setPpdad3(inProposalsModel.getPpdad3());
				inLoanTransactionsModel.setPpdnam(inProposalsModel.getPpdnam());
				inLoanTransactionsModel.setPprnum(inProposalsModel.getInProposalsModelPK().getPprnum());
				inLoanTransactionsModel.setQuonum(inProposalsModel.getQuonum());
				inLoanTransactionsModel.setRctsta("VALID");
				inLoanTransactionsModel.setRemark(saveReceiptDto.getRemark());
				inLoanTransactionsModel.setSeqnum(1);
				inLoanTransactionsModel.setTotprm(saveReceiptDto.getAmount());
				inLoanTransactionsModel.setTxndat(new Date());
				
				if(saveReceiptDto.getPayMode().equalsIgnoreCase("CQ")) {
					inLoanTransactionsModel.setChqnum(saveReceiptDto.getChequeno());
					inLoanTransactionsModel.setChqbnk(saveReceiptDto.getChequebank());
					try {
						inLoanTransactionsModel.setChqdat(new SimpleDateFormat("yyyy-MM-dd").parse(saveReceiptDto.getChequedate()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if (saveReceiptDto.getPayMode().equalsIgnoreCase("CR")) {
					inLoanTransactionsModel.setCcdnum(saveReceiptDto.getTransferno());
				}
					
				ReceiptPrintDto printDto = null;
				
				try {
					inLoanTransactionDao.save(inLoanTransactionsModel);
					try {
						printDto = getReceiptPrintDto(inProposalsModel, inLoanTransactionsModel, agentCode, locCode, false);
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
					dto.setMessage(inLoanTransactionsModel.getInLoanTransactionsModelPK().getDocnum().toString());
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
			dto.setMessage("Unable to generate DOC NUM");
			
			return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
		}

		dto = new ResponseDto();
		dto.setCode("204");
		dto.setStatus("Error");
		dto.setMessage("Location Not Found");

		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}
	
	public ReceiptPrintDto getReceiptPrintDto(InProposalsModel inProposalsModel,
			InLoanTransactionsModel inTransactionsModel, String agentCode, String locCode, boolean b) throws Exception {
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
		printDto.setPolNum(Integer.parseInt(inProposalsModel.getPolnum()));
		printDto.setDocCode(inTransactionsModel.getInLoanTransactionsModelPK().getDoccod());
		printDto.setDocNum(inTransactionsModel.getInLoanTransactionsModelPK().getDocnum());
		printDto.setLocation(locCode);
		printDto.setPayMode(inTransactionsModel.getPaymod());
		printDto.setPropNum(Integer.parseInt(inTransactionsModel.getPprnum()));
		printDto.setQuoNum(inProposalsModel.getQuonum());
		printDto.setQdId(inProposalsModel.getInProposalsModelPK().getPrpseq());
		printDto.setRctDate(inTransactionsModel.getCreadt());
		printDto.setRctStatus("");
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
		return printDto;
	}

	
	

}
