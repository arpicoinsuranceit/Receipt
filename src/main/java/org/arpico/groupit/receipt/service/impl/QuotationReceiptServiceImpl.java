package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.InProposalDao;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.QuotationReceiptService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuotationReceiptServiceImpl implements QuotationReceiptService {

	@Autowired
	private InProposalDao inProposalDao;

	@Autowired
	private QuotationClient quotationClient;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private AgentDao agentDao;

	@Override
	public String saveQuotationReceipt(SaveReceiptDto saveReceiptDto) throws Exception {

		System.out.println("called");

		List<AgentModel> agentModels = agentDao.findAgentByCode(saveReceiptDto.getAgentCode());
		
		if (agentModels != null && agentModels.size() > 0) {

			String[] numberGen = numberGenerator.generateNewId("", "", "RCNBSQ", "");

			if (numberGen[0].equals("Success")) {

				ViewQuotationDto resp = quotationClient.getQuotation(saveReceiptDto.getQuotationDetailId(),
						saveReceiptDto.getQuotationId());
				// System.out.println(resp.getProductName());

				// Primary Keys
				InProposalsModelPK inProposalsModelPK = new InProposalsModelPK();

				inProposalsModelPK.setPprnum(numberGen[1]);
				inProposalsModelPK.setLoccod(saveReceiptDto.getBranchCode());
				inProposalsModelPK.setPrpseq(saveReceiptDto.getQuotationDetailId());
				inProposalsModelPK.setSbucod(AppConstant.SBU_CODE);
				inProposalsModelPK.setDoccod(AppConstant.DOC_CODE_QUOT);

				InProposalsModel inProposalsModel = new InProposalsModel();

				// Set Primary Keys to model
				inProposalsModel.setInProposalsModelPK(inProposalsModelPK);

				System.out.println(resp.get_mainlife().get_mDob());

				inProposalsModel.setPpdnam(resp.get_mainlife().get_mName());
				inProposalsModel.setPpddob(new SimpleDateFormat("dd-MM-yyyy").parse(resp.get_mainlife().get_mDob()));
				inProposalsModel.setPpdnag(Integer.parseInt(resp.get_mainlife().get_mAge()));
				inProposalsModel.setPpdnic(resp.get_mainlife().get_mNic());
				inProposalsModel.setPpdsex(resp.get_mainlife().get_mGender());
				inProposalsModel.setPpdcst(resp.get_mainlife().get_mCivilStatus());
				inProposalsModel.setPpdmob(resp.get_mainlife().get_mMobile());
				inProposalsModel.setPpdeml(resp.get_mainlife().get_mEmail());
				inProposalsModel.setNtitle(resp.get_mainlife().get_mTitle());
				inProposalsModel.setPpdocu(resp.get_mainlife().get_occuCode());

				inProposalsModel.setToptrm(resp.get_plan().get_term());
				inProposalsModel.setPaytrm(new CalculationUtils().getPaytrm(resp.get_plan().get_frequance()));
				inProposalsModel.setBassum(resp.get_plan().get_bsa());
				inProposalsModel.setPremum(resp.get_plan().getContribution());
				inProposalsModel.setTotprm(resp.get_plan().get_bsaTotal());
				inProposalsModel.setTrgprm(0.0);
				inProposalsModel.setQuonum(saveReceiptDto.getQuotationId());
				inProposalsModel.setSeqnum(saveReceiptDto.getQuotationDetailId());
				inProposalsModel.setQuosta("ACT");
				inProposalsModel
						.setIntrat(resp.get_plan().get_interestRate() != null ? resp.get_plan().get_interestRate() : 0);
				inProposalsModel.setSmksta("N");
				inProposalsModel.setPayamt(resp.get_plan().getContribution());
				inProposalsModel.setPolfee(resp.get_plan().getPolicyFee());
				inProposalsModel.setAdmfee(resp.get_plan().getAdminFee());
				inProposalsModel.setTaxamt(resp.get_plan().getTax());
				inProposalsModel.setGrsprm(resp.get_plan().getGrsprm());
				inProposalsModel.setInvpos(resp.get_plan().getInvPos());
				inProposalsModel.setLifpos(resp.get_plan().getLifePos());
				inProposalsModel.setSumrkm(resp.get_plan().getSumatRiskMain());
				inProposalsModel.setSumrks(
						resp.get_plan().getSumatRiskSpouse() != null ? resp.get_plan().getSumatRiskSpouse() : 0.0);

				inProposalsModel
						.setRlftrm(resp.get_plan().get_payingterm() != null ? resp.get_plan().get_payingterm() : "0");
				inProposalsModel.setJlfsex(resp.get_mainlife().get_mGender());
				inProposalsModel.setNewnic(resp.get_mainlife().get_mNic());

				switch (resp.get_plan().get_frequance()) {
				case "M":
					inProposalsModel.setPrmmth(resp.get_plan().getContribution());
					inProposalsModel.setPrmmtt(resp.get_plan().get_bsaTotal());
					break;
				case "Q":
					inProposalsModel.setPrmqat(resp.get_plan().getContribution());
					inProposalsModel.setPrmqtt(resp.get_plan().get_bsaTotal());
					break;
				case "H":
					inProposalsModel.setPrmhlf(resp.get_plan().getContribution());
					inProposalsModel.setPrmhlt(resp.get_plan().get_bsaTotal());
					break;
				case "Y":
					inProposalsModel.setPrmyer(resp.get_plan().getContribution());
					inProposalsModel.setPrmyet(resp.get_plan().get_bsaTotal());
					break;
				case "S":
					inProposalsModel.setSinprm("1");
					break;
				default:
					break;
				}

				if (resp.get_spouse() != null && resp.get_spouse().is_sActive()) {
					inProposalsModel.setSponam(resp.get_spouse().get_sName());
					inProposalsModel.setStitle(resp.get_spouse().get_sTitle());
					inProposalsModel.setSponic(resp.get_spouse().get_sNic());
					inProposalsModel.setSpodob(new SimpleDateFormat("dd-MM-yyyy").parse(resp.get_spouse().get_sDob()));
					inProposalsModel.setSagnxt(Integer.parseInt(resp.get_spouse().get_sAge()));
					inProposalsModel.setSpoocu(resp.get_spouse().getOccuCode());
				}

				inProposalsModel.setPrpdat(AppConstant.DATE);

				inProposalsModel.setAgmcod(saveReceiptDto.getAgentCode());
				inProposalsModel.setAdvcod(saveReceiptDto.getAgentCode());

				inProposalsModel.setNumchl(resp.get_children().size());
				inProposalsModel.setPrdcod(resp.getProductCode());
				inProposalsModel.setPrdnam(resp.getProductName());
				inProposalsModel.setCscode(resp.get_mainlife().get_mCustCode());

				inProposalsModel.setCreadt(AppConstant.DATE);

				inProposalDao.save(inProposalsModel);

			} else {
				System.out.println("Error");
			}
		}else {
			System.out.println("Agent not Found");
		}

		return "WORK";
	}

}
