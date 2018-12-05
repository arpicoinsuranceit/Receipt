package org.arpico.groupit.receipt.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.CommisDaoCustom;
import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.CommisModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.ReFundModel;
import org.arpico.groupit.receipt.model.pk.InBillingTransactionsModelPK;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.SetoffService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SetoffServiceImpl implements SetoffService {

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private InAgentMastDao inAgentMastDao;

	@Autowired
	private CommisDaoCustom commisDaoCustom;

	@Override
	@Transactional
	public Integer setoff(InProposalsModel inProposalsModel, String agentCode, String locCode,
			SaveReceiptDto saveReceiptDto, InBillingTransactionsModel deposit, Double recovery) throws Exception {
		
		System.out.println("setoff");
		
		String pprNum = inProposalsModel.getInProposalsModelPK().getPprnum();

		List<InBillingTransactionsModel> unSetOffList = billingTransactionsCustomDao.getUnSetOffs(inProposalsModel.getInProposalsModelPK().getPprnum());

		
		System.out.println("unsetoff : " + unSetOffList.size() );
		
		InBillingTransactionsModel previousInvoice = null;
		try {
			previousInvoice = billingTransactionsCustomDao.getLasiInvoice(pprNum);
		} catch (Exception e) {
			System.out.println("No Invoince Found");
		}

		InBillingTransactionsModel invoice = null;

		if (unSetOffList != null && unSetOffList.size() > 0) {
			invoice = unSetOffList.get(0);
		} else {
			invoice = createInvoice(inProposalsModel, previousInvoice, agentCode, locCode);
			inBillingTransactionDao.save(invoice);
		}

		System.out.println(invoice.toString());
		System.out.println("halfway");

		List<InBillingTransactionsModel> setoffList = null;

		List<ReFundModel> fundModels = billingTransactionsCustomDao
				.getRefundList(inProposalsModel.getInProposalsModelPK().getPprnum());

		fundModels.forEach(System.out::println);

		Double amount = 0.0;

		if (fundModels != null && fundModels.size() > 0) {
			for (ReFundModel reFundModel : fundModels) {
				amount += reFundModel.getRefamount();
			}
		}

		Double recoveryAmount = 0.0;
		Boolean saverecovery = false;

		System.out.println("recovery : " + recovery);

		if (recovery > 0) {

			if (invoice.getAmount() > amount && invoice.getAmount() <= (amount + recovery)) {
				recoveryAmount = invoice.getAmount() - amount;
				InBillingTransactionsModel recoveryModel = getRecovery(deposit, recoveryAmount);

				System.out.println("recovery : " + recovery);

				inBillingTransactionDao.save(recoveryModel);

				System.out.println("recovery saved ");

				saverecovery = true;
			}
		}

		System.out.println(invoice.getAmount());
		
		System.out.println(amount);
		
		if (invoice.getAmount() <= amount || (saverecovery && invoice.getAmount() <= (amount + recoveryAmount))) {

			System.out.println("amount if");

			ReFundModel fundModel = new ReFundModel();
			fundModel.setDoccod(deposit.getBillingTransactionsModelPK().getDoccod());
			fundModel.setDocnum(deposit.getBillingTransactionsModelPK().getDocnum());
			fundModel.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
			if (saverecovery) {
				fundModel.setRefamount((deposit.getDepost() + (recoveryAmount * -1)) * -1);
			} else {

				fundModel.setRefamount(deposit.getDepost() * -1);
			}
			fundModel.setLinnum(deposit.getBillingTransactionsModelPK().getLinnum());
			fundModel.setPaymode(deposit.getPaymod());
			fundModel.setActive(1);

			setoffList = getSetOff(invoice, fundModel, setoffList, inProposalsModel, fundModels, agentCode, locCode,
					unSetOffList, 1, deposit.getBillingTransactionsModelPK().getDocnum(), recoveryAmount);

			setoffList.forEach(System.out::println);
		}

		if (setoffList != null && setoffList.size() > 0) {

			inBillingTransactionDao.save(setoffList);
		}

		System.out.println("END_____________----------");

		return 1;
	}

	private InBillingTransactionsModel getRecovery(InBillingTransactionsModel deposit, Double amount) {

		InBillingTransactionsModelPK recoveryId = new InBillingTransactionsModelPK();

		recoveryId.setDoccod(deposit.getBillingTransactionsModelPK().getDoccod());
		recoveryId.setDocnum(deposit.getBillingTransactionsModelPK().getDocnum());
		recoveryId.setLinnum(deposit.getBillingTransactionsModelPK().getLinnum() + 2);
		recoveryId.setSbucod(deposit.getBillingTransactionsModelPK().getSbucod());
		recoveryId.setLoccod(deposit.getBillingTransactionsModelPK().getLoccod());
		recoveryId.setTxndat(deposit.getBillingTransactionsModelPK().getTxndat());

		InBillingTransactionsModel recovery = new InBillingTransactionsModel();

		recovery.setBillingTransactionsModelPK(recoveryId);

		recovery.setAdmfee(deposit.getAdmfee());
		recovery.setAdvcod(deposit.getAdvcod());
		recovery.setAgncls(deposit.getAgncls());
		recovery.setAmount(amount * -1);
		recovery.setBatcno(deposit.getBatcno());
		recovery.setBattyp(deposit.getBattyp());
		recovery.setBrncod(deposit.getBrncod());
		recovery.setCandoc(deposit.getCandoc());
		recovery.setChqrel(deposit.getChqrel());
		recovery.setComiss(deposit.getComiss());
		recovery.setComper(deposit.getComper());
		recovery.setCreaby(deposit.getCreaby());
		recovery.setCreadt(deposit.getCreadt());
		recovery.setCscode(deposit.getCscode());
		recovery.setDepost(0.0);
		recovery.setDuedat(deposit.getDuedat());
		recovery.setGlintg(deposit.getGlintg());
		recovery.setGrsprm(deposit.getGrsprm());
		recovery.setHrbprm(deposit.getHrbprm());
		recovery.setIcpmon(deposit.getIcpmon());
		recovery.setIcpyer(deposit.getIcpyer());
		recovery.setInsnum(deposit.getInsnum());
		recovery.setLockin(deposit.getLockin());
		recovery.setOldprm(deposit.getOldprm());
		recovery.setOtham1(deposit.getOtham1());
		recovery.setOtham2(deposit.getOtham2());
		recovery.setOtham3(deposit.getOtham3());
		recovery.setOtham4(deposit.getOtham4());
		recovery.setPaymod(deposit.getPaymod());
		recovery.setPaytrm(deposit.getPaytrm());
		recovery.setPolfee(deposit.getPolfee());
		recovery.setPolnum(deposit.getPolnum());
		recovery.setPolyer(deposit.getPolyer());
		recovery.setPprnum(deposit.getPprnum());
		recovery.setPrcyer(deposit.getPrcyer());
		recovery.setPrdcod(deposit.getPrdcod());
		recovery.setPrpseq(deposit.getPrpseq());
		recovery.setRefdoc(deposit.getRefdoc());
		recovery.setRefnum(deposit.getRefnum());
		recovery.setSrcdoc(deposit.getSrcdoc());
		recovery.setSrcnum(deposit.getSrcnum());
		recovery.setTaxamt(deposit.getTaxamt());
		recovery.setToptrm(deposit.getToptrm());
		recovery.setTxnbno(deposit.getTxnbno());
		recovery.setTxnmth(deposit.getTxnmth());
		recovery.setTxntyp("RECOVERY");
		recovery.setTxnyer(deposit.getTxnyer());
		recovery.setUnlcod(deposit.getUnlcod());

		return recovery;
	}

	public InBillingTransactionsModel createInvoice(InProposalsModel inProposalsModel,
			InBillingTransactionsModel previousInvoice, String user, String loc) throws Exception {

		String[] numberGen = numberGenerator.generateNewId("", "", "PRMISQ", "");
		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(inProposalsModel.getAdvcod());

		AgentMastModel agentMastModel = agentMastModels.get(0);

		System.out.println(Arrays.toString(numberGen));

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

			billingTransactionsModel.setBrncod(agentMastModel.getLocation());
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
						billingTransactionsModel.setTxnyer(model.getTxnyer());
						billingTransactionsModel.setTxnmth(model.getTxnmth());
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

	private List<InBillingTransactionsModel> getSetOff(InBillingTransactionsModel invoice, ReFundModel deposit,
			List<InBillingTransactionsModel> setoffList, InProposalsModel inProposalsModel,
			List<ReFundModel> fundModels, String user, String loc, List<InBillingTransactionsModel> unsetoffList,
			Integer count, Integer depDocNo, Double recoveryAmount) throws Exception {

		List<InBillingTransactionsModel> setoffList1 = new ArrayList<>();

		fundModels.forEach(System.out::println);

		Double invoiceAmount = invoice.getAmount();

		Double amount = 0.0;

		for (int i = 0; i < fundModels.size(); i++) {
			ReFundModel fundModel = fundModels.get(i);
			if (fundModel.getDocnum().equals(depDocNo)) {
				amount = fundModel.getRefamount() + amount + recoveryAmount;
				fundModels.get(i).setRefamount(fundModel.getRefamount() + recoveryAmount);
			} else {
				amount = fundModel.getRefamount() + amount;
			}
		}

		//Integer j = 0;

		while (amount >= invoiceAmount) {
			
			if(amount == 0 ) {
				break;
			}

			boolean b = true;

			System.out.println("while");
			System.out.println("amount :  " + amount);
			System.out.println("invoiceAmount : " + invoiceAmount);
			for (int i = 0; i < fundModels.size(); i++) {

				ReFundModel fundModel = fundModels.get(i);
				System.out.println("fundModel.getRefamount()" + fundModel.getRefamount());
				System.out.println("fundModel.getRefamount()" + fundModel.getRefamount());
				System.out.println("fundModel.getActive()" + fundModel.getActive());
				if (fundModel.getActive() == 1) {

					System.out.println("main if");
					System.out.println("fundModel.getRefamount()" + fundModel.getRefamount());

					if (fundModel.getRefamount() <= invoiceAmount) {

						System.out.println("fundModel.getRefamount() <= invoiceAmount");

						System.out.println("amount :  " + amount);
						System.out.println("invoiceAmount : " + invoiceAmount);

						setoffList1.add(
								getSetoff(fundModel, invoice, inProposalsModel, user, loc, fundModel.getPaymode()));
						fundModels.get(i).setActive(0);
						amount = amount - fundModel.getRefamount();
						invoiceAmount = invoiceAmount - fundModel.getRefamount();
						
						System.out.println("amount :  " + amount);
						System.out.println("invoiceAmount : " + invoiceAmount);

					} else {

						System.out.println("fundModel.getRefamount() <= invoiceAmount else");

						System.out.println("amount :  " + amount);
						System.out.println("invoiceAmount : " + invoiceAmount);
						
						Double fundAmountTemp = fundModel.getRefamount();
						
						fundModel.setRefamount(invoiceAmount);
						amount = amount - invoiceAmount;

						System.out.println("amount 2: " + amount);

						setoffList1.add(
								getSetoff(fundModel, invoice, inProposalsModel, user, loc, fundModel.getPaymode()));

						InBillingTransactionsModel newInvoice = null;

						Integer txnYer = invoice.getTxnyer();
						Integer txnMth = invoice.getTxnmth() + 1;

						if (invoice.getTxnmth() == 12) {
							txnYer = invoice.getTxnyer() + 1;
							txnMth = 1;
						}

						for (InBillingTransactionsModel e : unsetoffList) {
							if (e.getTxnyer().equals(txnYer) && e.getTxnmth().equals(txnMth)) {
								newInvoice = e;
							}
						}
						if (newInvoice == null) {
							newInvoice = createInvoice(inProposalsModel, invoice, user, loc);

							setoffList1.add(newInvoice);
						}

						invoice = newInvoice;
						invoiceAmount = newInvoice.getAmount();
						
						System.out.println("amount :  " + amount);
						System.out.println("invoiceAmount : " + invoiceAmount);
						System.out.println("fundModels.get(i).getRefamount() ; " + fundModels.get(i).getRefamount());
						System.out.println("fundAmountTemp : " + fundAmountTemp);
						System.out.println("fundAmountTemp - fundModels.get(i).getRefamount() :" + (fundAmountTemp - fundModels.get(i).getRefamount()));
						fundModels.get(i).setRefamount(fundAmountTemp - fundModels.get(i).getRefamount());
						fundModels.get(i).setLinnum(fundModels.get(i).getLinnum() + 1);
					}

				} else {

					System.out.println("main else");

					for (ReFundModel model : fundModels) {
						if (model.getActive() == 1) {
							b = false;
						}
					}
					System.out.println("false" + b);
				}

			}

			/*if (!b) {
				break;
			}*/

		}

		System.out.println("End Amount : " + amount);
		System.out.println("End invoiceAmount : " + invoiceAmount );
		
		
		fundModels.forEach(System.out::println);

		System.out.println(setoffList1.size());

		return setoffList1;
	}

	private InBillingTransactionsModel getSetoff(ReFundModel reFundModel, InBillingTransactionsModel invoice,
			InProposalsModel inProposalsModel, String user, String loc, String paymode) throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = null;
		if (invoice.getTxnmth() < 10) {
			dateString = invoice.getTxnyer() + "-0" + invoice.getTxnmth() + "-01";
		} else {
			dateString = invoice.getTxnyer() + "-" + invoice.getTxnmth() + "-01";
		}

		String polDate = simpleDateFormat.format(inProposalsModel.getIcpdat());

		LocalDate policyDate = LocalDate.parse(polDate);
		LocalDate currentDate = LocalDate.parse(dateString);
		int diffInYears = (int) ChronoUnit.YEARS.between(policyDate, currentDate) + 1;

		CommisModel commisModel = null;
		if (inProposalsModel.getPrdcod().equals("ARTM")) {
			commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
					inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
		} else {
			commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
					inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
		}

		System.out.println(commisModel.toString());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inProposalsModel.getIcpdat());

		InBillingTransactionsModelPK modelPK = new InBillingTransactionsModelPK();
		modelPK.setDoccod(reFundModel.getDoccod());
		modelPK.setDocnum(reFundModel.getDocnum());
		modelPK.setLinnum(reFundModel.getLinnum() + 1);
		modelPK.setLoccod(loc);
		modelPK.setTxndat(new Date());
		modelPK.setSbucod(AppConstant.SBU_CODE);

		InBillingTransactionsModel model = new InBillingTransactionsModel();
		model.setBillingTransactionsModelPK(modelPK);

		model.setAdmfee(invoice.getAdmfee());
		model.setAdvcod(invoice.getAdvcod());
		model.setAgncls(invoice.getAgncls());
		model.setAmount(reFundModel.getRefamount() * -1);
		model.setBrncod(invoice.getBrncod());
		model.setChqrel("N");
		model.setComiss(AppConstant.ZERO_FOR_DECIMAL);
		model.setComper(AppConstant.ZERO_FOR_DECIMAL);
		model.setCreaby(user);
		model.setCreadt(new Date());
		model.setIcpmon(policyDate.getMonthValue());
		model.setIcpyer(policyDate.getYear());
		model.setPaymod(paymode);

		try {
			model.setCscode(Integer.parseInt(inProposalsModel.getCscode()));
		} catch (Exception e) {
		}
		model.setDepost(reFundModel.getRefamount());
		model.setGlintg("N");

		model.setGrsprm(AppConstant.ZERO_FOR_DECIMAL);
		model.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
		model.setInsnum(0);
		model.setLockin(new Date());
		model.setOldprm(AppConstant.ZERO_FOR_DECIMAL);
		model.setOtham1(AppConstant.ZERO_FOR_DECIMAL);
		model.setOtham2(AppConstant.ZERO_FOR_DECIMAL);
		model.setOtham3(AppConstant.ZERO_FOR_DECIMAL);
		model.setOtham4(AppConstant.ZERO_FOR_DECIMAL);

		model.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));

		model.setPolfee(invoice.getPolfee());
		model.setPolnum(Integer.parseInt(inProposalsModel.getPolnum()));
		model.setPprnum(Integer.parseInt(inProposalsModel.getInProposalsModelPK().getPprnum()));
		model.setPrdcod(inProposalsModel.getPrdcod());
		model.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
		model.setRefdoc(modelPK.getDoccod());
		model.setRefnum(modelPK.getDocnum());
		model.setSrcdoc(modelPK.getDoccod());
		model.setSrcnum(modelPK.getDocnum());
		model.setTaxamt(inProposalsModel.getTaxamt());
		model.setToptrm(inProposalsModel.getToptrm());
		model.setTxntyp("POLDEP");
		model.setUnlcod(invoice.getUnlcod());
		model.setGrsprm(inProposalsModel.getGrsprm());

		if (inProposalsModel.getSinprm() == "1") {
			model.setComper(commisModel.getComsin());
			BigDecimal commis = new BigDecimal(inProposalsModel.getGrsprm())
					.multiply(new BigDecimal(commisModel.getComsin()).divide(new BigDecimal(100)));
			model.setComiss(commis.setScale(2, RoundingMode.HALF_UP).doubleValue());
		} else {
			model.setComper(commisModel.getComper());
			BigDecimal commis = new BigDecimal(inProposalsModel.getGrsprm())
					.multiply(new BigDecimal(commisModel.getComper()).divide(new BigDecimal(100)));
			model.setComiss(commis.setScale(2, RoundingMode.HALF_UP).doubleValue());
		}

		model.setTxnyer(invoice.getTxnyer());
		model.setTxnmth(invoice.getTxnmth());
		model.setPolyer(diffInYears);

		return model;

	}

}
