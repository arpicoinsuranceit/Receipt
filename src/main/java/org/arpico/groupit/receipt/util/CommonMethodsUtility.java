package org.arpico.groupit.receipt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.model.pk.InTransactionsModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonMethodsUtility {

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private InAgentMastDao inAgentMastDao;

	public InTransactionsModel getInTransactionModel(InProposalsModel inProposalsModel, SaveReceiptDto saveReceiptDto,
			String userCode, String locCode) throws Exception {

		String[] numberGen = numberGenerator.generateNewId("", "", "RCNBSQ", "");

		String agentCode = new JwtDecoder().generate(saveReceiptDto.getToken());

		System.out.println(agentCode);

		System.out.println(numberGen[1]);

		if (numberGen[0].equalsIgnoreCase("Success")) {
			InTransactionsModelPK inTransactionsModelPK = new InTransactionsModelPK();
			inTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
			inTransactionsModelPK.setLoccod(locCode);
			inTransactionsModelPK.setDoccod("RCNB");
			inTransactionsModelPK.setDocnum(Integer.parseInt(numberGen[1]));
			inTransactionsModelPK.setLinnum(0);

			InTransactionsModel inTransactionsModel = new InTransactionsModel();

			inTransactionsModel.setAdvcod(saveReceiptDto.getAgentCode());
			inTransactionsModel.setAmtwrd(saveReceiptDto.getPayAmountWord());
			inTransactionsModel.setBancod(saveReceiptDto.getBankCode());
			inTransactionsModel.setBildat(AppConstant.DATE);
			inTransactionsModel.setBilpik("N");
			inTransactionsModel.setChqrel("N");
			inTransactionsModel.setCompad("N");
			inTransactionsModel.setCreaby(agentCode);
			inTransactionsModel.setCreadt(AppConstant.DATE);

			try {
				inTransactionsModel.setCscode(inProposalsModel.getCscode());
			}catch (Exception e) {}
			

			inTransactionsModel.setInTransactionsModelPK(inTransactionsModelPK);
			inTransactionsModel.setLockin(AppConstant.DATE);
			inTransactionsModel.setNtitle(inProposalsModel.getNtitle());
			inTransactionsModel.setPpdnam(inProposalsModel.getPpdnam());
			inTransactionsModel.setPaymod(saveReceiptDto.getPayMode());
			inTransactionsModel.setPpdad1(inProposalsModel.getPpdad1());
			inTransactionsModel.setPpdad2(inProposalsModel.getPpdad2());
			inTransactionsModel.setPpdad3(inProposalsModel.getPpdad3());
			inTransactionsModel.setPprnum(inProposalsModel.getInProposalsModelPK().getPprnum());
			inTransactionsModel.setQuonum(saveReceiptDto.getQuotationId());
			inTransactionsModel.setPolnum(saveReceiptDto.getPolId());
			inTransactionsModel.setRctsta("VALID");
			inTransactionsModel.setRemark(saveReceiptDto.getRemark());
			inTransactionsModel.setSeqnum(saveReceiptDto.getQuotationDetailId());
			inTransactionsModel.setTotprm(saveReceiptDto.getAmount());
			inTransactionsModel.setTxndat(AppConstant.DATE);

			if (saveReceiptDto.getPayMode().equalsIgnoreCase("CQ")) {
				inTransactionsModel.setChqbnk(saveReceiptDto.getChequebank());
				inTransactionsModel.setChqdat(new SimpleDateFormat("dd-MM-yyyy").parse(saveReceiptDto.getChequedate()));
				inTransactionsModel.setChqnum(saveReceiptDto.getChequeno());
			}
			if (saveReceiptDto.getPayMode().equalsIgnoreCase("CR")) {
				inTransactionsModel.setCcdnum(saveReceiptDto.getTransferno());
			}

			return inTransactionsModel;
		}
		return null;

	}

	public InBillingTransactionsModel getInBillingTransactionModel(InProposalsModel inProposalsModel,
			SaveReceiptDto saveReceiptDto, InTransactionsModel inTransactionsModel) throws Exception {

		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(saveReceiptDto.getAgentCode());

		AgentMastModel agentMastModel = agentMastModels.get(0);

		if (agentMastModels != null && agentMastModels.size() > 0) {
			InBillingTransactionsModelPK billingTransactionsModelPK = new InBillingTransactionsModelPK();
			billingTransactionsModelPK.setDoccod(inTransactionsModel.getInTransactionsModelPK().getDoccod());
			billingTransactionsModelPK.setDocnum(inTransactionsModel.getInTransactionsModelPK().getDocnum());
			billingTransactionsModelPK.setLinnum(inTransactionsModel.getInTransactionsModelPK().getLinnum());
			billingTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
			billingTransactionsModelPK.setTxndat(AppConstant.DATE);
			billingTransactionsModelPK.setLoccod(inTransactionsModel.getInTransactionsModelPK().getLoccod());

			InBillingTransactionsModel billingTransactionsModel = new InBillingTransactionsModel();

			billingTransactionsModel.setBillingTransactionsModelPK(billingTransactionsModelPK);
			billingTransactionsModel.setAdmfee(AppConstant.ZERO_TWO_DECIMAL);
			billingTransactionsModel.setAdvcod(Integer.parseInt(saveReceiptDto.getAgentCode()));
			billingTransactionsModel.setAgncls(agentMastModel.getAgncls());
			billingTransactionsModel.setAmount(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setBrncod(saveReceiptDto.getBranchCode());
			billingTransactionsModel.setChqrel("N");
			billingTransactionsModel.setComiss(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setComper(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setCreaby(inTransactionsModel.getCreaby());
			billingTransactionsModel.setCreadt(AppConstant.DATE);
			billingTransactionsModel.setBrncod(inProposalsModel.getBrncod());
			billingTransactionsModel.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
			try {
				billingTransactionsModel.setCscode(Integer.parseInt(inTransactionsModel.getCscode()));
			} catch (Exception e) {
			}
			billingTransactionsModel.setDepost((saveReceiptDto.getAmount() * -1));
			billingTransactionsModel.setGlintg("N");

			billingTransactionsModel.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setInsnum(inTransactionsModel.getInTransactionsModelPK().getLinnum());
			billingTransactionsModel.setLockin(AppConstant.DATE);
			billingTransactionsModel.setOldprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setPaymod(inTransactionsModel.getPaymod());
			billingTransactionsModel.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));
			billingTransactionsModel.setPolfee(AppConstant.ZERO_TWO_DECIMAL);
			billingTransactionsModel.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
			billingTransactionsModel.setPrdcod(inProposalsModel.getPrdcod());
			billingTransactionsModel.setPrpseq(saveReceiptDto.getQuotationDetailId());
			billingTransactionsModel.setRefdoc(inTransactionsModel.getInTransactionsModelPK().getDoccod());
			billingTransactionsModel.setRefnum(inTransactionsModel.getInTransactionsModelPK().getDocnum());
			billingTransactionsModel.setSrcdoc(inTransactionsModel.getInTransactionsModelPK().getDoccod());
			billingTransactionsModel.setSrcnum(inTransactionsModel.getInTransactionsModelPK().getDocnum());
			billingTransactionsModel.setTaxamt(inProposalsModel.getTaxamt());
			billingTransactionsModel.setToptrm(inProposalsModel.getToptrm());
			billingTransactionsModel.setTxntyp("PROPDEP");
			if (agentMastModel.getAgncls().equalsIgnoreCase("IC")) {
				billingTransactionsModel.setUnlcod(agentMastModel.getUnlcod());
			}
			if (agentMastModel.getAgncls().equalsIgnoreCase("UNL")) {
				billingTransactionsModel.setUnlcod(agentMastModel.getBrnmanager());
			}
			billingTransactionsModel.setTxnyer(Calendar.getInstance().get(Calendar.YEAR));
			billingTransactionsModel.setTxnmth(Calendar.getInstance().get(Calendar.MONTH));

			return billingTransactionsModel;
		}

		return null;
	}

}
