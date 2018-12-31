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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.CommisDaoCustom;
import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.arpico.groupit.receipt.model.CommisModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
//import org.arpico.groupit.receipt.model.ReFundModel;
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

//	@Autowired
//	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private InAgentMastDao inAgentMastDao;

	@Autowired
	private CommisDaoCustom commisDaoCustom;

	public InBillingTransactionsModel createInvoice(InProposalsModel inProposalsModel,
			InBillingTransactionsModel previousInvoice, String user, String loc, boolean isAutoIssue) throws Exception {

		String[] numberGen = numberGenerator.generateNewId("", "", "PRMISQ", "");
		List<AgentMastModel> agentMastModels = inAgentMastDao.getAgentDetails(inProposalsModel.getAdvcod());

		AgentMastModel agentMastModel = agentMastModels.get(0);

		System.out.println(Arrays.toString(numberGen));

		if (numberGen[0].equals("Success")) {

			InBillingTransactionsModelPK billingTransactionsModelPK = new InBillingTransactionsModelPK();

			billingTransactionsModelPK.setDoccod("PRMI");
			billingTransactionsModelPK.setDocnum(Integer.parseInt(numberGen[1]));
			billingTransactionsModelPK.setLinnum(0);
			billingTransactionsModelPK.setLoccod(inProposalsModel.getInProposalsModelPK().getLoccod());
			billingTransactionsModelPK.setSbucod(AppConstant.SBU_CODE);
			billingTransactionsModelPK.setTxndat(new Date());

			InBillingTransactionsModel billingTransactionsModel = new InBillingTransactionsModel();

			billingTransactionsModel.setBillingTransactionsModelPK(billingTransactionsModelPK);

			billingTransactionsModel.setAdmfee(inProposalsModel.getAdmfee());
			billingTransactionsModel.setAdvcod(Integer.parseInt(inProposalsModel.getAdvcod()));
			billingTransactionsModel.setAgncls(agentMastModel.getAgncls());
			if (previousInvoice != null) {
				billingTransactionsModel.setAmount(inProposalsModel.getTotprm());
			} else {
				billingTransactionsModel.setAmount(inProposalsModel.getTotprm() + inProposalsModel.getPolfee());
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

			billingTransactionsModel.setGrsprm(inProposalsModel.getGrsprm());
			billingTransactionsModel.setHrbprm(AppConstant.ZERO_FOR_DECIMAL);
			billingTransactionsModel.setInsnum(1);
			billingTransactionsModel.setLockin(new Date());
			billingTransactionsModel.setOldprm(AppConstant.ZERO_FOR_DECIMAL);

			billingTransactionsModel.setPaytrm(Integer.parseInt(inProposalsModel.getPaytrm()));
			if (previousInvoice != null) {
				billingTransactionsModel.setPolfee(AppConstant.ZERO_TWO_DECIMAL);
			} else {
				billingTransactionsModel.setPolfee(inProposalsModel.getPolfee());
			}

			billingTransactionsModel.setOtham1(inProposalsModel.getOtham1());
			billingTransactionsModel.setOtham2(inProposalsModel.getOtham2());
			billingTransactionsModel.setOtham3(inProposalsModel.getOtham3());
			billingTransactionsModel.setOtham4(inProposalsModel.getOtham4());

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
			billingTransactionsModel.setTxnbno(AppConstant.ZERO);

			billingTransactionsModel.setUnlcod(agentMastModel.getUnlcod());
			Date date2 = inProposalsModel.getIcpdat();

			Calendar calendar2 = new GregorianCalendar();
			calendar2.setTime(date2);

			billingTransactionsModel.setIcpyer(calendar2.get(Calendar.YEAR));
			billingTransactionsModel.setIcpmon(calendar2.get(Calendar.MONTH) + 1);

			if (previousInvoice != null) {

				String term = inProposalsModel.getPaytrm();
				String isSingle = inProposalsModel.getSinprm();

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(previousInvoice.getDuedat());

				if (isSingle == null || !isSingle.equals("1")) {
					switch (term) {
					case "12":
						calendar.add(Calendar.MONTH, 1);
						break;
					case "6":
						calendar.add(Calendar.MONTH, 6);
						break;
					case "3":
						calendar.add(Calendar.MONTH, 3);
						break;
					case "1":
						calendar.add(Calendar.YEAR, 1);
						break;

					default:
						break;
					}
				}

				billingTransactionsModel.setDuedat(calendar.getTime());

				System.out.println("calendar.get(Calendar.YEAR) : " + calendar.get(Calendar.YEAR));

				System.out.println("calendar2.get(Calendar.YEAR) : " + calendar2.get(Calendar.YEAR));

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String icpdat = simpleDateFormat.format(inProposalsModel.getIcpdat());

				String dueDate = simpleDateFormat.format(billingTransactionsModel.getDuedat());

				LocalDate policyDate = LocalDate.parse(icpdat);
				LocalDate currentDate = LocalDate.parse(dueDate);
				
				int diffInMonths = (int) ChronoUnit.MONTHS.between(policyDate, currentDate);

				System.out.println("diffInMonths : " + diffInMonths);
				
				billingTransactionsModel.setPolyer((diffInMonths / 12) + 1);

				billingTransactionsModel.setTxnyer(calendar.get(Calendar.YEAR));
				billingTransactionsModel.setTxnmth(calendar.get(Calendar.MONTH) + 1);

			} else {
				try {
					billingTransactionsModel.setPolyer(1);

					billingTransactionsModel.setDuedat(inProposalsModel.getIcpdat());

					Date date = inProposalsModel.getIcpdat();

					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);

					billingTransactionsModel.setTxnyer(calendar.get(Calendar.YEAR));
					billingTransactionsModel.setTxnmth(calendar.get(Calendar.MONTH) + 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (isAutoIssue) {
				billingTransactionsModel.setCreaby(AppConstant.SYSTEM_CREATE);
			}

			billingTransactionsModel.setPrcyer(billingTransactionsModel.getTxnyer());

			return billingTransactionsModel;

		} else {
			return null;
		}

	}

	@Override
	public List<InBillingTransactionsModel> setoff(InProposalsModel inProposalsModel, String userCode, String locCode,
			SaveReceiptDto saveReceiptDto, InBillingTransactionsModel deposit, Double hrbamt,
			ProposalL3Dto autoIssueData, String setoffType) throws Exception {

		System.out.println("Setoff");

		List<InBillingTransactionsModel> setoffList = new ArrayList<InBillingTransactionsModel>();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if (setoffType.equalsIgnoreCase("NEW")) {

			System.out.println("NEW PROPOSAL SETOFF");

			InBillingTransactionsModel invoice = createInvoice(inProposalsModel, null, userCode, locCode, true);
			setoffList.add(invoice);

			System.out.println("Invoice Created \n");

			System.out.println(invoice.toString());

			List<InBillingTransactionsModel> fundModels = billingTransactionsCustomDao
					.getRefundList(inProposalsModel.getInProposalsModelPK().getPprnum());

			System.out.println("FUND SIZE : " + fundModels.size());

			fundModels.forEach(System.out::println);

			if (autoIssueData.getTotprm() >= autoIssueData.getPayment()) {
				int linnum = 1;

				System.out.println("autoIssueData.getTotprm() >= autoIssueData.getPayment() : true");

				String icpdat = simpleDateFormat.format(inProposalsModel.getIcpdat());

				String dueDate = simpleDateFormat.format(invoice.getDuedat());

				LocalDate policyDate = LocalDate.parse(icpdat);
				LocalDate currentDate = LocalDate.parse(dueDate);
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

				InBillingTransactionsModel recoveryModel = null;

				for (InBillingTransactionsModel inBillingTransactionsModel : fundModels) {
					inBillingTransactionsModel.setPolnum(invoice.getPolnum());
					inBillingTransactionsModel.getBillingTransactionsModelPK().setLinnum(linnum);
					inBillingTransactionsModel.setTxnyer(invoice.getTxnyer());
					inBillingTransactionsModel.setTxnmth(invoice.getTxnmth());
					inBillingTransactionsModel.getBillingTransactionsModelPK().setTxndat(new Date());
					inBillingTransactionsModel.setInsnum(1);
					inBillingTransactionsModel.setCreaby(userCode);
					inBillingTransactionsModel.setCreadt(new Date());
					inBillingTransactionsModel.setLockin(new Date());
					inBillingTransactionsModel.setPrpseq(invoice.getPrpseq());
					inBillingTransactionsModel.setPolfee(invoice.getPolfee());
					inBillingTransactionsModel.setAdmfee(invoice.getAdmfee());
					inBillingTransactionsModel.setTaxamt(invoice.getTaxamt());
					inBillingTransactionsModel.setOtham1(invoice.getOtham1());
					inBillingTransactionsModel.setOtham2(invoice.getOtham2());
					inBillingTransactionsModel.setOtham3(invoice.getOtham3());
					inBillingTransactionsModel.setOtham4(invoice.getOtham4());
					inBillingTransactionsModel.setToptrm(invoice.getToptrm());
					inBillingTransactionsModel.setPaytrm(invoice.getPaytrm());
					inBillingTransactionsModel.setIcpyer(invoice.getIcpyer());
					inBillingTransactionsModel.setPrcyer(invoice.getPrcyer());
					inBillingTransactionsModel.setIcpmon(invoice.getIcpmon());
					inBillingTransactionsModel.setDuedat(invoice.getDuedat());
					inBillingTransactionsModel.setGrsprm(invoice.getGrsprm());
					inBillingTransactionsModel.setPrdcod(invoice.getPrdcod());
					inBillingTransactionsModel.setBattyp(AppConstant.NULL);
					inBillingTransactionsModel.setBatcno(AppConstant.ZERO);
					inBillingTransactionsModel.setGlintg("N");
					inBillingTransactionsModel.setTxnbno(AppConstant.ZERO);
					inBillingTransactionsModel
							.setCandoc(inBillingTransactionsModel.getBillingTransactionsModelPK().getDoccod());

					inBillingTransactionsModel.setHrbprm(hrbamt);

					if (inProposalsModel.getSinprm() == "1") {
						inBillingTransactionsModel.setComper(commisModel.getComsin());
						BigDecimal commis = new BigDecimal(inProposalsModel.getGrsprm())
								.multiply(new BigDecimal(commisModel.getComsin()).divide(new BigDecimal(100)));
						inBillingTransactionsModel.setComiss(commis.setScale(2, RoundingMode.HALF_UP).doubleValue());
					} else {
						inBillingTransactionsModel.setComper(commisModel.getComper());
						BigDecimal commis = new BigDecimal(inProposalsModel.getGrsprm())
								.multiply(new BigDecimal(commisModel.getComper()).divide(new BigDecimal(100)));
						inBillingTransactionsModel.setComiss(commis.setScale(2, RoundingMode.HALF_UP).doubleValue());
					}

					recoveryModel = getCopyBilling(inBillingTransactionsModel);
					setoffList.add(inBillingTransactionsModel);
					linnum++;

				}

				System.out.println("SETOFF SIZE : " + setoffList.size());

				if (autoIssueData.getTotprm() > autoIssueData.getPayment()) {
					recoveryModel.setTxntyp(AppConstant.RECOVERY);
					recoveryModel.setAmount((autoIssueData.getTotprm() - autoIssueData.getPayment())*-1);
					recoveryModel.setDepost(AppConstant.ZERO_FOR_DECIMAL);
					recoveryModel.getBillingTransactionsModelPK().setLinnum(linnum);
					recoveryModel.setCreaby(AppConstant.SYSTEM_CREATE);
					setoffList.add(recoveryModel);

					System.out.println("Recovery Added");
					System.out.println("SETOFF SIZE : " + setoffList.size());
				}

				InBillingTransactionsModel invoiceNew = createInvoice(inProposalsModel, invoice, userCode, locCode,
						true);
				setoffList.add(invoiceNew);

			} else {

				System.out.println("autoIssueData.getTotprm() >= autoIssueData.getPayment() : false");

				Double totPrm = autoIssueData.getPayment();
				Double invAmount = invoice.getAmount();

				while (totPrm > invAmount) {

					System.out.println("TOTPRM : " + totPrm);
					System.out.println("INVAMOUNT : " + invAmount);

					String icpdat = simpleDateFormat.format(inProposalsModel.getIcpdat());

					String dueDate = simpleDateFormat.format(invoice.getDuedat());

					LocalDate policyDate = LocalDate.parse(icpdat);
					LocalDate currentDate = LocalDate.parse(dueDate);
					int diffInYears = (int) ChronoUnit.YEARS.between(policyDate, currentDate) + 1;

					for (Integer i = 0; i < fundModels.size(); i++) {

						System.out.println("TOTPRM : " + totPrm);
						System.out.println("INVAMOUNT : " + invAmount);

						InBillingTransactionsModel fundModel = fundModels.get(i);

						System.out.println(fundModel.toString());

						if (fundModel.getDepost() >= invAmount) {

							System.out.println("fundModel.getDepost() : true");

							CommisModel commisModel = null;
							if (inProposalsModel.getPrdcod().equals("ARTM")) {
								commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
										inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
							} else {
								commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
										inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
							}

							setoffList.add(getSetoff(fundModel, invoice,
									(fundModel.getBillingTransactionsModelPK().getLinnum() + 1), userCode,
									inProposalsModel, hrbamt, commisModel, invAmount, true));

							System.out.println("setoffList size : " + setoffList.size());

							fundModel.setDepost((fundModel.getDepost() - invAmount));
							fundModel.setAmount(((fundModel.getDepost() - invAmount) * -1));
							fundModel.getBillingTransactionsModelPK()
									.setLinnum(fundModel.getBillingTransactionsModelPK().getLinnum() + 1);

							fundModels.set(i, fundModel);

							totPrm = totPrm - invoice.getAmount();

							invoice = createInvoice(inProposalsModel, invoice, userCode, locCode, true);
							invAmount = invoice.getAmount();
							setoffList.add(invoice);

							System.out.println("After set fund amt to model");
							System.out.println(fundModels.get(i).toString());

							System.out.println(invoice.toString());

							System.out.println("totPrm : " + totPrm);
							break;

						} else {

							System.out.println("fundModel.getDepost() : false");

							System.out.println("setoffList size : " + setoffList.size());

							System.out.println(fundModel.getDepost());

							if (fundModel.getDepost() > 0) {
								CommisModel commisModel = null;
								if (inProposalsModel.getPrdcod().equals("ARTM")) {
									commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
											inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
								} else {
									commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
											inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
								}

								setoffList.add(getSetoff(fundModel, invoice,
										(fundModel.getBillingTransactionsModelPK().getLinnum() + 1), userCode,
										inProposalsModel, hrbamt, commisModel, fundModel.getDepost(), true));

								System.out.println("setoffList size : " + setoffList.size());

								invAmount = invAmount - fundModel.getDepost();
								totPrm = totPrm - fundModel.getDepost();

								System.out.println("invAmount : " + invAmount);
								System.out.println("totPrm : " + totPrm);

								fundModel.setDepost(0.0);
								fundModel.setAmount(0.0);
								fundModels.set(i, fundModel);

							}

						}
					}
				}

			}

		} else if (setoffType.equalsIgnoreCase("OLD")) {

			List<InBillingTransactionsModel> fundModels = billingTransactionsCustomDao
					.getRefundList(inProposalsModel.getInProposalsModelPK().getPprnum());

			System.out.println("FUND SIZE : " + fundModels.size());

			fundModels.forEach(System.out::println);

			InBillingTransactionsModel lastInvTemp = null;

			InBillingTransactionsModel invoice = null;

			List<InBillingTransactionsModel> unsetoffs = billingTransactionsCustomDao
					.getUnSetOffs(inProposalsModel.getInProposalsModelPK().getPprnum());

			if (unsetoffs != null && !unsetoffs.isEmpty()) {

				System.out.println("UNSETOFF NOT NULL");

			} else {

				System.out.println("UNSETOFF NULL");

				unsetoffs = new ArrayList<>();

				InBillingTransactionsModel lastInvoice = billingTransactionsCustomDao
						.getLasiInvoice(inProposalsModel.getInProposalsModelPK().getPprnum());

				unsetoffs.add(createInvoice(inProposalsModel, lastInvoice, userCode, locCode, false));
			}

			Double totPrm = 0.0;
			for (InBillingTransactionsModel inBillingTransactionsModel : fundModels) {
				totPrm = inBillingTransactionsModel.getDepost() + totPrm;
			}

			System.out.println("TOT PREMIUM : " + totPrm);

			for (Integer inv = 0; inv < unsetoffs.size(); inv++) {

				invoice = unsetoffs.get(inv);

				System.out.println(invoice.toString());

				lastInvTemp = unsetoffs.get(inv);

				Double invAmount = invoice.getAmount();

				System.out.println("TOT PREMIUM : " + totPrm);
				System.out.println("INV AMOUNT : " + invAmount);

				if (totPrm > invAmount) {

					System.out.println("TOTPRM : " + totPrm);
					System.out.println("INVAMOUNT : " + invAmount);

					System.out.println(inProposalsModel.toString());

					String icpdat = simpleDateFormat.format(inProposalsModel.getIcpdat());

					String dueDate = simpleDateFormat.format(invoice.getDuedat());

					LocalDate policyDate = LocalDate.parse(icpdat);
					LocalDate currentDate = LocalDate.parse(dueDate);
					int diffInYears = (int) ChronoUnit.YEARS.between(policyDate, currentDate) + 1;

					for (Integer i = 0; i < fundModels.size(); i++) {

						System.out.println("TOTPRM : " + totPrm);
						System.out.println("INVAMOUNT : " + invAmount);

						InBillingTransactionsModel fundModel = fundModels.get(i);

						System.out.println(fundModel.toString());

						if (fundModel.getDepost() >= invAmount) {

							System.out.println("fundModel.getDepost() : true");

							CommisModel commisModel = null;
							if (inProposalsModel.getPrdcod().equals("ARTM")) {
								commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
										inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
							} else {
								commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
										inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
							}

							setoffList.add(getSetoff(fundModel, invoice,
									(fundModel.getBillingTransactionsModelPK().getLinnum() + 1), userCode,
									inProposalsModel, hrbamt, commisModel, invAmount, false));

							System.out.println("setoffList size : " + setoffList.size());

							fundModel.setDepost((fundModel.getDepost() - invAmount));
							fundModel.setAmount(((fundModel.getDepost() - invAmount) * -1));
							fundModel.getBillingTransactionsModelPK()
									.setLinnum(fundModel.getBillingTransactionsModelPK().getLinnum() + 1);

							fundModels.set(i, fundModel);

							totPrm = totPrm - invoice.getAmount();

//							invoice = createInvoice(inProposalsModel, invoice, userCode, locCode, true);
//							invAmount = invoice.getAmount();
//							setoffList.add(invoice);

							System.out.println("After set fund amt to model");
							System.out.println(fundModels.get(i).toString());

							// System.out.println(invoice.toString());

							System.out.println("totPrm : " + totPrm);
							break;

						} else {

							System.out.println("fundModel.getDepost() : false");

							System.out.println("setoffList size : " + setoffList.size());

							System.out.println(fundModel.getDepost());

							if (fundModel.getDepost() > 0) {
								CommisModel commisModel = null;
								if (inProposalsModel.getPrdcod().equals("ARTM")) {
									commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
											inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
								} else {
									commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
											inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
								}

								setoffList.add(getSetoff(fundModel, invoice,
										(fundModel.getBillingTransactionsModelPK().getLinnum() + 1), userCode,
										inProposalsModel, hrbamt, commisModel, fundModel.getDepost(), false));

								System.out.println("setoffList size : " + setoffList.size());

								invAmount = invAmount - fundModel.getDepost();
								totPrm = totPrm - fundModel.getDepost();

								System.out.println("invAmount : " + invAmount);
								System.out.println("totPrm : " + totPrm);

								fundModel.setDepost(0.0);
								fundModel.setAmount(0.0);
								fundModels.set(i, fundModel);

							}

						}
					}

				}

			}

			invoice = createInvoice(inProposalsModel, lastInvTemp, userCode, locCode, false);

			Double invAmount = invoice.getAmount();

			if (totPrm > invAmount) {
				setoffList.add(invoice);
			}

			while (totPrm > invAmount) {

				System.out.println("TOTPRM : " + totPrm);
				System.out.println("INVAMOUNT : " + invAmount);

				String icpdat = simpleDateFormat.format(inProposalsModel.getIcpdat());

				String dueDate = simpleDateFormat.format(invoice.getDuedat());

				LocalDate policyDate = LocalDate.parse(icpdat);
				LocalDate currentDate = LocalDate.parse(dueDate);
				int diffInYears = (int) ChronoUnit.YEARS.between(policyDate, currentDate) + 1;

				for (Integer i = 0; i < fundModels.size(); i++) {

					System.out.println("TOTPRM : " + totPrm);
					System.out.println("INVAMOUNT : " + invAmount);

					InBillingTransactionsModel fundModel = fundModels.get(i);

					System.out.println(fundModel.toString());

					if (fundModel.getDepost() >= invAmount) {

						System.out.println("fundModel.getDepost() : true");

						CommisModel commisModel = null;
						if (inProposalsModel.getPrdcod().equals("ARTM")) {
							commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
									inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
						} else {
							commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
									inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
						}

						setoffList.add(getSetoff(fundModel, invoice,
								(fundModel.getBillingTransactionsModelPK().getLinnum() + 1), userCode, inProposalsModel,
								hrbamt, commisModel, invAmount, false));

						System.out.println("setoffList size : " + setoffList.size());

						fundModel.setDepost((fundModel.getDepost() - invAmount));
						fundModel.setAmount(((fundModel.getDepost() - invAmount) * -1));
						fundModel.getBillingTransactionsModelPK()
								.setLinnum(fundModel.getBillingTransactionsModelPK().getLinnum() + 1);

						fundModels.set(i, fundModel);

						totPrm = totPrm - invoice.getAmount();

						invoice = createInvoice(inProposalsModel, invoice, userCode, locCode, false);
						invAmount = invoice.getAmount();
						setoffList.add(invoice);

						System.out.println("After set fund amt to model");
						System.out.println(fundModels.get(i).toString());

						System.out.println(invoice.toString());

						System.out.println("totPrm : " + totPrm);
						break;

					} else {

						System.out.println("fundModel.getDepost() : false");

						System.out.println("setoffList size : " + setoffList.size());

						System.out.println(fundModel.getDepost());

						if (fundModel.getDepost() > 0) {
							CommisModel commisModel = null;
							if (inProposalsModel.getPrdcod().equals("ARTM")) {
								commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
										inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
							} else {
								commisModel = commisDaoCustom.getCommis(diffInYears, inProposalsModel.getPrdcod(),
										inProposalsModel.getToptrm(), inProposalsModel.getIcpdat());
							}

							setoffList.add(getSetoff(fundModel, invoice,
									(fundModel.getBillingTransactionsModelPK().getLinnum() + 1), userCode,
									inProposalsModel, hrbamt, commisModel, fundModel.getDepost(), false));

							System.out.println("setoffList size : " + setoffList.size());

							invAmount = invAmount - fundModel.getDepost();
							totPrm = totPrm - fundModel.getDepost();

							System.out.println("invAmount : " + invAmount);
							System.out.println("totPrm : " + totPrm);

							fundModel.setDepost(0.0);
							fundModel.setAmount(0.0);
							fundModels.set(i, fundModel);

						}

					}
				}
			}

		}

		return setoffList;
	}

	private InBillingTransactionsModel getCopyBilling(InBillingTransactionsModel billing) {

		InBillingTransactionsModelPK pk = new InBillingTransactionsModelPK();

		pk.setDoccod(billing.getBillingTransactionsModelPK().getDoccod());
		pk.setDocnum(billing.getBillingTransactionsModelPK().getDocnum());
		pk.setLinnum(billing.getBillingTransactionsModelPK().getLinnum() + 1);
		pk.setLoccod(billing.getBillingTransactionsModelPK().getLoccod());
		pk.setSbucod(billing.getBillingTransactionsModelPK().getSbucod());
		pk.setTxndat(billing.getBillingTransactionsModelPK().getTxndat());

		InBillingTransactionsModel model = new InBillingTransactionsModel();
		model.setBillingTransactionsModelPK(pk);

		model.setAdmfee(billing.getAdmfee());
		model.setAdvcod(billing.getAdvcod());
		model.setAgncls(billing.getAgncls());
		model.setAmount(billing.getAmount());
		model.setBatcno(billing.getBatcno());
		model.setBattyp(billing.getBattyp());
		model.setBrncod(billing.getBrncod());
		model.setCandoc(billing.getCandoc());
		model.setChqrel(billing.getChqrel());
		model.setComiss(billing.getComiss());
		model.setComper(billing.getComper());
		model.setCreaby(billing.getCreaby());
		model.setCreadt(billing.getCreadt());
		model.setCscode(billing.getCscode());
		model.setDepost(billing.getDepost());
		model.setDuedat(billing.getDuedat());
		model.setGlintg(billing.getGlintg());
		model.setGrsprm(billing.getGrsprm());
		model.setHrbprm(billing.getHrbprm());
		model.setIcpmon(billing.getIcpmon());
		model.setIcpyer(billing.getIcpyer());
		model.setInsnum(billing.getInsnum());
		model.setLockin(billing.getLockin());
		model.setOldprm(billing.getOldprm());
		model.setOtham1(billing.getOtham1());
		model.setOtham2(billing.getOtham2());
		model.setOtham3(billing.getOtham3());
		model.setOtham4(billing.getOtham4());
		model.setPaymod(billing.getPaymod());
		model.setPaytrm(billing.getPaytrm());
		model.setPolfee(billing.getPolfee());
		model.setPolnum(billing.getPolnum());
		model.setPolyer(billing.getPolyer());
		model.setPprnum(billing.getPprnum());
		model.setPrcyer(billing.getPrcyer());
		model.setPrdcod(billing.getPrdcod());
		model.setPrpseq(billing.getPrpseq());
		model.setRefdoc(billing.getRefdoc());
		model.setRefnum(billing.getRefnum());
		model.setSrcdoc(billing.getSrcdoc());
		model.setSrcnum(billing.getSrcnum());
		model.setTaxamt(billing.getTaxamt());
		model.setToptrm(billing.getToptrm());
		model.setTxnbno(billing.getTxnbno());
		model.setTxnmth(billing.getTxnmth());
		model.setTxntyp(billing.getTxntyp());
		model.setTxnyer(billing.getTxnyer());
		model.setUnlcod(billing.getUnlcod());

		return model;
	}

	private InBillingTransactionsModel getSetoff(InBillingTransactionsModel fund, InBillingTransactionsModel invoice,
			Integer linnum, String userCode, InProposalsModel inProposalsModel, Double hrbamt, CommisModel commisModel,
			Double amount, boolean isAutoIssue) {

		InBillingTransactionsModelPK pk = new InBillingTransactionsModelPK();

		pk.setDoccod(fund.getBillingTransactionsModelPK().getDoccod());
		pk.setDocnum(fund.getBillingTransactionsModelPK().getDocnum());
		pk.setLinnum(linnum);
		pk.setLoccod(fund.getBillingTransactionsModelPK().getLoccod());
		pk.setSbucod(fund.getBillingTransactionsModelPK().getSbucod());
		pk.setTxndat(fund.getBillingTransactionsModelPK().getTxndat());

		InBillingTransactionsModel setoff = new InBillingTransactionsModel();

		setoff.setAdvcod(fund.getAdvcod());
		setoff.setAgncls(fund.getAgncls());
		setoff.setBrncod(fund.getBrncod());
		setoff.setChqrel(fund.getChqrel());
		setoff.setComper(fund.getComper());
		setoff.setCscode(fund.getCscode());
		setoff.setHrbprm(fund.getHrbprm());
		setoff.setOldprm(fund.getOldprm());
		setoff.setPaymod(fund.getPaymod());
		setoff.setPolyer(invoice.getPolyer());
		setoff.setPprnum(fund.getPprnum());
		setoff.setRefdoc(fund.getRefdoc());
		setoff.setRefnum(fund.getRefnum());
		setoff.setSrcdoc(fund.getSrcdoc());
		setoff.setSrcnum(fund.getSrcnum());
		setoff.setTxntyp(fund.getTxntyp());

		setoff.setUnlcod(fund.getUnlcod());

		setoff.setBillingTransactionsModelPK(pk);

		setoff.setAmount((amount * -1));
		setoff.setDepost(amount);

		setoff.setPolnum(invoice.getPolnum());
		setoff.getBillingTransactionsModelPK().setLinnum(linnum);
		setoff.setTxnyer(invoice.getTxnyer());
		setoff.setTxnmth(invoice.getTxnmth());
		setoff.getBillingTransactionsModelPK().setTxndat(new Date());
		setoff.setInsnum(1);
		setoff.setCreaby(userCode);
		setoff.setCreadt(new Date());
		setoff.setLockin(new Date());
		setoff.setPrpseq(invoice.getPrpseq());
		setoff.setPolfee(invoice.getPolfee());
		setoff.setAdmfee(invoice.getAdmfee());
		setoff.setTaxamt(invoice.getTaxamt());
		setoff.setOtham1(invoice.getOtham1());
		setoff.setOtham2(invoice.getOtham2());
		setoff.setOtham3(invoice.getOtham3());
		setoff.setOtham4(invoice.getOtham4());
		setoff.setToptrm(invoice.getToptrm());
		setoff.setPaytrm(invoice.getPaytrm());
		setoff.setIcpyer(invoice.getIcpyer());
		setoff.setPrcyer(invoice.getPrcyer());
		setoff.setIcpmon(invoice.getIcpmon());
		setoff.setDuedat(invoice.getDuedat());
		setoff.setGrsprm(invoice.getGrsprm());
		setoff.setPrdcod(invoice.getPrdcod());
		setoff.setBattyp(AppConstant.NULL);
		setoff.setBatcno(AppConstant.ZERO);
		setoff.setGlintg("N");
		setoff.setTxnbno(AppConstant.ZERO);
		setoff.setCandoc(fund.getBillingTransactionsModelPK().getDoccod());

		setoff.setHrbprm(hrbamt);

		if (inProposalsModel.getSinprm() == "1") {
			setoff.setComper(commisModel.getComsin());
			BigDecimal commis = new BigDecimal(inProposalsModel.getGrsprm())
					.multiply(new BigDecimal(commisModel.getComsin()).divide(new BigDecimal(100)));
			setoff.setComiss(commis.setScale(2, RoundingMode.HALF_UP).doubleValue());
		} else {
			setoff.setComper(commisModel.getComper());
			BigDecimal commis = new BigDecimal(inProposalsModel.getGrsprm())
					.multiply(new BigDecimal(commisModel.getComper()).divide(new BigDecimal(100)));
			setoff.setComiss(commis.setScale(2, RoundingMode.HALF_UP).doubleValue());
		}

		if (isAutoIssue) {
			setoff.setCreaby(AppConstant.SYSTEM_CREATE);
		}

		return setoff;
	}

}
