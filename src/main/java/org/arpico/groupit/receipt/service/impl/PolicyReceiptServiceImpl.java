package org.arpico.groupit.receipt.service.impl;

/*import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
/*
import org.arpico.groupit.receipt.dao.CommisDaoCustom;*/
import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;/*
														import org.arpico.groupit.receipt.model.CommisModel;*/
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;/*
																import org.arpico.groupit.receipt.model.ReFundModel;*/
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
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
	private InTransactionService inTransactionService;

	@Autowired
	private RmsUserDao rmsUserDao;

	/*
	 * @Autowired private CommisDaoCustom commisDaoCustom;
	 */

	@Autowired
	SetoffService setoffService;

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

		InProposalsModel inProposalsModel = inProposalCustomDao.getProposalBuPolicy(saveReceiptDto.getPolId(),
				saveReceiptDto.getPolSeq());

		String agentCode = new JwtDecoder().generate(saveReceiptDto.getToken());

		String locCode = rmsUserDao.getLocation(agentCode);

		if (locCode != null) {

			InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
					saveReceiptDto, agentCode, locCode);

			inTransactionsModel.getInTransactionsModelPK().setDoccod("RCPL");
			inTransactionDao.save(inTransactionsModel);

			System.out.println("transaction model save");

			InBillingTransactionsModel deposit = commonethodUtility.getInBillingTransactionModel(inProposalsModel,
					saveReceiptDto, inTransactionsModel);
			deposit.setCreaby(agentCode);
			deposit.setPolnum(inTransactionsModel.getPolnum());
			deposit.getBillingTransactionsModelPK().setDoccod("RCPL");
			deposit.setTxntyp("POLDEP");

			inBillingTransactionDao.save(deposit);

			setoff(inProposalsModel, agentCode, locCode, saveReceiptDto, deposit, 0.0);

			return new ResponseEntity<>("Success", HttpStatus.OK);
		}
		return null;
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

			inBillingTransactionDao.save(billingTransactionsModel);

			return billingTransactionsModel;

		} else {
			return null;
		}

	}

}
