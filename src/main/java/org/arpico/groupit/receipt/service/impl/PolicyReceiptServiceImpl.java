package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.ProposalNoSeqNoDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.arpico.groupit.receipt.model.ReFundModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.PolicyReceiptService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyReceiptServiceImpl implements PolicyReceiptService {

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private InAgentMastDao inAgentMastDao;

	@Autowired
	private CommonMethodsUtility commonethodUtility;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private InTransactionService inTransactionService;

	
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

		InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
				saveReceiptDto);

		inTransactionsModel.getInTransactionsModelPK().setDoccod("RCPL");

		InBillingTransactionsModel deposit = commonethodUtility.getInBillingTransactionModel(inProposalsModel,
				saveReceiptDto, inTransactionsModel);

		deposit.getBillingTransactionsModelPK().setDoccod("RCPL");
		deposit.setTxntyp("POLDEP");

		List<InBillingTransactionsModel> unSetOffList = billingTransactionsCustomDao
				.getUnSetOffs(inProposalsModel.getInProposalsModelPK().getPprnum());

		InBillingTransactionsModel invoice = null;

		if (unSetOffList != null && unSetOffList.size() > 0) {
			invoice = unSetOffList.get(0);
		} else {
			invoice = createInvoice(inProposalsModel);
			inBillingTransactionDao.save(invoice);
		}

		List<InBillingTransactionsModel> setoffList = null;

		List<ReFundModel> fundModels = billingTransactionsCustomDao
				.getRefundList(inProposalsModel.getInProposalsModelPK().getPprnum());

		Double amount = saveReceiptDto.getAmount();

		if (fundModels != null && fundModels.size() > 0) {
			for (ReFundModel reFundModel : fundModels) {
				amount += reFundModel.getRefamount();
			}
		}

		if (invoice.getAmount() <= amount) {
			setoffList = getSetOff(invoice, deposit, setoffList, inProposalsModel);
		}

		inBillingTransactionDao.save(deposit);
		if(setoffList != null && setoffList.size() > 0) {
			inBillingTransactionDao.save(setoffList);
		}
		
		return null;

	}

	private List<InBillingTransactionsModel> getSetOff(InBillingTransactionsModel invoice,
			InBillingTransactionsModel deposit, List<InBillingTransactionsModel> setoffList,
			InProposalsModel inProposalsModel) throws Exception {
		setoffList = new ArrayList<>();
		if (setoffList != null && setoffList.size() > 0) {
			for (InBillingTransactionsModel setoff : setoffList) {
				setoffList.add(getSetOff(invoice,setoff, inProposalsModel));
			}
		}
		
		setoffList.add(getSetOff(invoice,deposit, inProposalsModel));
		return setoffList;
	}

	private InBillingTransactionsModel getSetOff(InBillingTransactionsModel invoice, InBillingTransactionsModel setoff,
			InProposalsModel inProposalsModel) throws Exception {
		
		String[] numberGen = numberGenerator.generateNewId("", "", "PRMISQ", "");
		
		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(inProposalsModel.getAdvcod());

		AgentMastModel agentMastModel = agentMastModels.get(0);

		
		if(numberGen[0].equals("Success")) {
			InBillingTransactionsModelPK modelPK = new InBillingTransactionsModelPK();
			modelPK.setDoccod("RCPL");
			modelPK.setDocnum(Integer.parseInt(numberGen[1]));
			modelPK.setLinnum(0);
			modelPK.setLoccod(inProposalsModel.getBrncod());
			modelPK.setTxndat(AppConstant.DATE);
			modelPK.setSbucod(AppConstant.SBU_CODE);
			
			
			InBillingTransactionsModel model = new InBillingTransactionsModel();
			model.setBillingTransactionsModelPK(modelPK);
			
			model.setAdmfee(AppConstant.ZERO_TWO_DECIMAL);
			model.setAdvcod(Integer.parseInt(inProposalsModel.getAdvcod()));
			model.setAgncls(agentMastModel.getAgncls());
			model.setAmount(
					inProposalsModel.getTaxamt() + inProposalsModel.getAdmfee() + inProposalsModel.getTotprm());
			model.setBrncod(inProposalsModel.getBrncod());
			model.setChqrel("N");
			model.setComiss(AppConstant.ZERO_FOR_DECIMAL);
			model.setComper(AppConstant.ZERO_FOR_DECIMAL);
			// billingTransactionsModel.setCreaby(inTransactionsModel.getCreaby());
			model.setCreadt(AppConstant.DATE);
			try {
				model.setCscode(Integer.parseInt(inProposalsModel.getCscode()));
			} catch (Exception e) {
			}
			model.setDepost(AppConstant.ZERO_TWO_DECIMAL);
			model.setGlintg("N");

			model.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
			model.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
			model.setInsnum(0);
			model.setLockin(AppConstant.DATE);
			model.setOldprm(AppConstant.ZERO_FOR_DECIMAL);

			model.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));
			model.setPolfee(inProposalsModel.getPolfee());
			model.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
			model.setPrdcod(inProposalsModel.getPrdcod());
			model.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
			model.setRefdoc(modelPK.getDoccod());
			model.setRefnum(modelPK.getDocnum());
			model.setSrcdoc(modelPK.getDoccod());
			model.setSrcnum(modelPK.getDocnum());
			model.setTaxamt(inProposalsModel.getTaxamt());
			model.setToptrm(inProposalsModel.getToptrm());
			model.setTxntyp("INVOICE");
			if (agentMastModel.getAgncls().equalsIgnoreCase("IC")) {
				model.setUnlcod(agentMastModel.getUnlcod());
			}
			if (agentMastModel.getAgncls().equalsIgnoreCase("UNL")) {
				model.setUnlcod(agentMastModel.getBrnmanager());
			}

			
			model.setTxnyer(invoice.getTxnyer());
			model.setTxnmth(invoice.getTxnmth());
			
			return model;
		}
		
		return null;
	}

	@Override
	public InBillingTransactionsModel createInvoice(InProposalsModel inProposalsModel) throws Exception {

		String[] numberGen = numberGenerator.generateNewId("", "", "PRMISQ", "");
		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(inProposalsModel.getAdvcod());

		AgentMastModel agentMastModel = agentMastModels.get(0);

		if (numberGen[0].equals("Success")) {

			InBillingTransactionsModelPK billingTransactionsModelPK = new InBillingTransactionsModelPK();

			billingTransactionsModelPK.setDoccod("PRMI");
			billingTransactionsModelPK.setDocnum(Integer.parseInt(numberGen[1]));
			billingTransactionsModelPK.setLinnum(0);
			billingTransactionsModelPK.setLoccod(inProposalsModel.getInProposalsModelPK().getLoccod());
			billingTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
			billingTransactionsModelPK.setTxndat(AppConstant.DATE);

			InBillingTransactionsModel billingTransactionsModel = new InBillingTransactionsModel();

			billingTransactionsModel.setBillingTransactionsModelPK(billingTransactionsModelPK);

			billingTransactionsModel.setAdmfee(AppConstant.ZERO_TWO_DECIMAL);
			billingTransactionsModel.setAdvcod(Integer.parseInt(inProposalsModel.getAdvcod()));
			billingTransactionsModel.setAgncls(agentMastModel.getAgncls());
			billingTransactionsModel.setAmount(
					inProposalsModel.getTaxamt() + inProposalsModel.getAdmfee() + inProposalsModel.getTotprm());
			billingTransactionsModel.setBrncod(inProposalsModel.getBrncod());
			billingTransactionsModel.setChqrel("N");
			billingTransactionsModel.setComiss(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setComper(AppConstant.ZERO_FOR_DECIMAL);
			// billingTransactionsModel.setCreaby(inTransactionsModel.getCreaby());
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
			billingTransactionsModel.setPolfee(inProposalsModel.getPolfee());
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

			try {
				InBillingTransactionsModel model = billingTransactionsCustomDao
						.getTxnYearDate(inProposalsModel.getInProposalsModelPK().getPprnum());
				billingTransactionsModel.setTxnyer(model.getTxnyer() + 1);
				billingTransactionsModel.setTxnmth(model.getTxnmth() + 1);
			} catch (Exception e) {

				System.out.println(e.getMessage());

				billingTransactionsModel.setTxnyer(Calendar.getInstance().get(Calendar.YEAR));
				billingTransactionsModel.setTxnmth(Calendar.getInstance().get(Calendar.MONTH));
			}
			return billingTransactionsModel;

		} else {
			return null;
		}

	}

}
