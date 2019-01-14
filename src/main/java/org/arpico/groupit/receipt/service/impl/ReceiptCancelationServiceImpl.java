package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.client.InfosysWSClient;
import org.arpico.groupit.receipt.dao.BranchDao;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.InLoanTransactionsDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.ReceiptCancelationCustomDao;
import org.arpico.groupit.receipt.dao.ReceiptCancelationDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.CanceledReceiptDto;
import org.arpico.groupit.receipt.dto.EmailDto;
import org.arpico.groupit.receipt.dto.EmailResponseDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.ReceiptCancelationService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReceiptCancelationServiceImpl implements ReceiptCancelationService {

	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;

	@Autowired
	private ReceiptCancelationCustomDao receiptCancelationCustomDao;

	@Autowired
	private ReceiptCancelationDao receiptCancelationDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private InfosysWSClient infosysWSClient;

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private InPropMedicalReqDao propMedicalReqDao;

	@Autowired
	private InLoanTransactionsDao inLoanTransactionDao;

	@Override
	public List<String> findReceiptLikeReceiptId(String receiptId, String token) throws Exception {
		String userCode = new JwtDecoder().generate(token);

		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);
			String locations = "";
			if (loccodes != null) {
				for (String string : loccodes) {
					locations += "'" + string + "'" + ",";
				}
			}

			locations = locations.replaceAll(",$", "");

			if (locations != "") {
				return receiptCancelationCustomDao.findReceiptLikeReceiptId(receiptId, locations,
						loccodes.contains("HO"));
			}
		}

		return null;
	}

	@Override
	public ResponseEntity<Object> saveRequest(String receiptNo, String reason, String token, String doccod)
			throws Exception {
		ResponseDto dto = null;

		InTransactionsModel inTransactionsModel = null;
		InLoanTransactionsModel inLoanTransactionsModel = null;
		String userCode = new JwtDecoder().generate(token);
		String userName = userDao.getUserFullName(userCode);

		List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);

		List<CanceledReceiptModel> canceledReceiptModels = receiptCancelationDao.findByStatusAndReceiptNo("PENDING",
				receiptNo);

		if (canceledReceiptModels.isEmpty()) {

			if (!doccod.equals("RCLN")) {

				try {
					inTransactionsModel = receiptCancelationCustomDao.findTransctionRow("450", receiptNo, doccod,
							userCode, loccodes.contains("HO"));

				} catch (Exception e) {
					dto = new ResponseDto();
					dto.setCode("204");
					dto.setStatus("Error");
					dto.setMessage("Receipt Not Found");
					return new ResponseEntity<>(dto, HttpStatus.OK);
				}

				if (inTransactionsModel != null) {

					if (doccod.equals("RCPP") || doccod.equals("RCPL")) {
						InProposalsModel inProposalsModel = inProposalCustomDao
								.getProposalFromPprnum(Integer.valueOf(inTransactionsModel.getPprnum()));

						if (inProposalsModel.getPolnum() != null && inProposalsModel.getPolnum() != ""){
							InPropMedicalReqModelPK inPropMedicalReqModelPK = new InPropMedicalReqModelPK();

							inPropMedicalReqModelPK.setInstyp("main");
							inPropMedicalReqModelPK.setLoccod(inProposalsModel.getInProposalsModelPK().getLoccod());
							inPropMedicalReqModelPK.setMedcod("AD-RC");
							inPropMedicalReqModelPK.setPprnum(Integer.parseInt(inTransactionsModel.getPprnum()));
							inPropMedicalReqModelPK.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
							inPropMedicalReqModelPK.setSbucod(AppConstant.SBU_CODE);

							InPropMedicalReqModel inPropMedicalReqModel = new InPropMedicalReqModel();

							inPropMedicalReqModel.setInPropMedicalReqModelPK(inPropMedicalReqModelPK);
							inPropMedicalReqModel.setLockin(new Date());
							inPropMedicalReqModel.setTessta("N");
							inPropMedicalReqModel.setHoscod("NA");
							inPropMedicalReqModel.setPaysta("");
							inPropMedicalReqModel.setMedorg("Requested");
							inPropMedicalReqModel.setPayamt(0.00);
							inPropMedicalReqModel.setAddnot("Receipt Cancelation Requirement");
							inPropMedicalReqModel.setMednam("Additional Benefit Receipt Cancelation");

							propMedicalReqDao.save(inPropMedicalReqModel);

							if (userCode != null) {

								String branchName = branchDao
										.getBranchName(inTransactionsModel.getInTransactionsModelPK().getLoccod());

								CanceledReceiptModel canceledReceiptModel = new CanceledReceiptModel();
								canceledReceiptModel.setReason(reason);
								canceledReceiptModel.setReceiptNo(receiptNo);
								canceledReceiptModel.setCreateBy(userCode);
								canceledReceiptModel.setCreateDate(new Date());
								canceledReceiptModel.setPolNum(String.valueOf(inTransactionsModel.getPolnum()));
								canceledReceiptModel.setPprNum(inTransactionsModel.getPprnum());
								canceledReceiptModel.setRequestBy(userCode);
								canceledReceiptModel.setRequestDate(new Date());
								canceledReceiptModel
										.setLocCode(inTransactionsModel.getInTransactionsModelPK().getLoccod());
								canceledReceiptModel.setSbuCode("450");
								canceledReceiptModel.setStatus("PENDING");
								canceledReceiptModel
										.setDocCode(inTransactionsModel.getInTransactionsModelPK().getDoccod());
								canceledReceiptModel.setAmount(inTransactionsModel.getTotprm());

								if (receiptCancelationDao.save(canceledReceiptModel) != null) {

									String toEmail = receiptCancelationCustomDao.findGMEmail("450",
											inTransactionsModel.getInTransactionsModelPK().getLoccod());
									String fromEmail = userDao.getUserEmail(userCode);

									String body = "Dear Sir , \n\n Receipt Cancelation Request from " + branchName
											+ " branch. \n Cancelation reason for " + reason + ".\n\n";

									EmailDto emailDto = new EmailDto();
									if (toEmail != null && toEmail != "" && fromEmail != null && fromEmail != "") {
										List<String> ccMails = new ArrayList<>();
										ccMails.add(fromEmail);

										emailDto.setAttachments(new ArrayList<>());
										emailDto.setCcMails(ccMails);
										emailDto.setFromMail(fromEmail);
										emailDto.setToMail(toEmail);
										emailDto.setToken(token);
										emailDto.setUserCode(userCode);
										emailDto.setSubject("Receipt Cancelation Approval ("
												+ inTransactionsModel.getInTransactionsModelPK().getDoccod() + "/"
												+ receiptNo + " - " + branchName + ")");

										body += " Receipt Number : "
												+ inTransactionsModel.getInTransactionsModelPK().getDoccod() + "/"
												+ receiptNo + "\n";
										body += " Receipted Amount : " + inTransactionsModel.getTotprm() + "\n";
										body += " Receipted Date : " + inTransactionsModel.getCreadt() + "\n";

										if (inTransactionsModel.getChqnum() != null) {
											body += " Cheque Number : " + inTransactionsModel.getChqnum() + "\n";
										}

										if (inTransactionsModel.getPprnum() != null) {
											body += " Proposal Number : " + inTransactionsModel.getPprnum() + "\n";
										}

										if (inTransactionsModel.getPolnum() != null) {
											body += " Policy Number : " + inTransactionsModel.getPolnum() + "\n";
										}

										body += " User : " + userName + "\n";
										body += " Branch : " + branchName + "\n\n\n";

										body += "Thanks." + "\n\n";
//									body += "This is system generated email. Do not reply.";

										emailDto.setBody(body);
										emailDto.setDepartment(AppConstant.EMAIL_DEP_CODE_FINANCE);

										EmailResponseDto responseDto = infosysWSClient.sendEmail(emailDto);

										if (responseDto.getMessage() == "Success") {
											dto = new ResponseDto();
											dto.setCode("200");
											dto.setStatus("Success");
											dto.setMessage("Success");
											return new ResponseEntity<>(dto, HttpStatus.OK);
										}

										dto = new ResponseDto();
										dto.setCode("200");
										dto.setStatus("Success");
										dto.setMessage("Success");
										return new ResponseEntity<>(dto, HttpStatus.OK);
									}

								}

							}

						}else {
							dto = new ResponseDto();
							dto.setCode("204");
							dto.setStatus("Error");
							dto.setMessage("Unable to cancel this Receipt. Please contact Finance Department");
							return new ResponseEntity<>(dto, HttpStatus.OK);
						}

					}

				} else {
					dto = new ResponseDto();
					dto.setCode("204");
					dto.setStatus("Error");
					dto.setMessage("Receipt Not Found");
					return new ResponseEntity<>(dto, HttpStatus.OK);
				}
			} else {

				inLoanTransactionsModel = receiptCancelationCustomDao.findLoanTransctionRow("450", receiptNo, doccod,
						userCode, loccodes.contains("HO"));
				InProposalsModel inProposalsModel = inProposalCustomDao
						.getProposalFromPprnum(Integer.valueOf(inLoanTransactionsModel.getPprnum()));

				if (inLoanTransactionsModel != null) {
					InPropMedicalReqModelPK inPropMedicalReqModelPK = new InPropMedicalReqModelPK();

					inPropMedicalReqModelPK.setInstyp("main");
					inPropMedicalReqModelPK.setLoccod(inProposalsModel.getInProposalsModelPK().getLoccod());
					inPropMedicalReqModelPK.setMedcod("AD-RC");
					inPropMedicalReqModelPK.setPprnum(Integer.parseInt(inLoanTransactionsModel.getPprnum()));
					inPropMedicalReqModelPK.setPrpseq(inProposalsModel.getInProposalsModelPK().getPrpseq());
					inPropMedicalReqModelPK.setSbucod(AppConstant.SBU_CODE);

					InPropMedicalReqModel inPropMedicalReqModel = new InPropMedicalReqModel();

					inPropMedicalReqModel.setInPropMedicalReqModelPK(inPropMedicalReqModelPK);
					inPropMedicalReqModel.setLockin(new Date());
					inPropMedicalReqModel.setTessta("N");
					inPropMedicalReqModel.setHoscod("NA");
					inPropMedicalReqModel.setPaysta("");
					inPropMedicalReqModel.setMedorg("Requested");
					inPropMedicalReqModel.setPayamt(0.00);
					inPropMedicalReqModel.setAddnot("Receipt Cancelation Requirement");
					inPropMedicalReqModel.setMednam("Additional Benefit Receipt Cancelation");

					propMedicalReqDao.save(inPropMedicalReqModel);

					if (userCode != null) {

						String branchName = branchDao
								.getBranchName(inLoanTransactionsModel.getInLoanTransactionsModelPK().getLoccod());

						CanceledReceiptModel canceledReceiptModel = new CanceledReceiptModel();
						canceledReceiptModel.setReason(reason);
						canceledReceiptModel.setReceiptNo(receiptNo);
						canceledReceiptModel.setCreateBy(userCode);
						canceledReceiptModel.setCreateDate(new Date());
						canceledReceiptModel.setPolNum(String.valueOf(inLoanTransactionsModel.getPolnum()));
						canceledReceiptModel.setPprNum(inLoanTransactionsModel.getPprnum());
						canceledReceiptModel.setRequestBy(userCode);
						canceledReceiptModel.setRequestDate(new Date());
						canceledReceiptModel
								.setLocCode(inLoanTransactionsModel.getInLoanTransactionsModelPK().getLoccod());
						canceledReceiptModel.setSbuCode("450");
						canceledReceiptModel.setStatus("PENDING");
						canceledReceiptModel
								.setDocCode(inLoanTransactionsModel.getInLoanTransactionsModelPK().getDoccod());
						canceledReceiptModel.setAmount(inLoanTransactionsModel.getTotprm());

						if (receiptCancelationDao.save(canceledReceiptModel) != null) {

							inLoanTransactionsModel.setCompad("N");

							inLoanTransactionDao.save(inLoanTransactionsModel);

							String toEmail = receiptCancelationCustomDao.findGMEmail("450",
									inLoanTransactionsModel.getInLoanTransactionsModelPK().getLoccod());
							String fromEmail = userDao.getUserEmail(userCode);

							String body = "Dear Sir , \n\n Receipt Cancelation Request from " + branchName
									+ " branch. \n Cancelation reason for " + reason + ".\n\n";

							EmailDto emailDto = new EmailDto();
							if (toEmail != null && toEmail != "" && fromEmail != null && fromEmail != "") {
								List<String> ccMails = new ArrayList<>();
								ccMails.add(fromEmail);

								emailDto.setAttachments(new ArrayList<>());
								emailDto.setCcMails(ccMails);
								emailDto.setFromMail(fromEmail);
								emailDto.setToMail(toEmail);
								emailDto.setToken(token);
								emailDto.setUserCode(userCode);
								emailDto.setSubject("Receipt Cancelation Approval ("
										+ inLoanTransactionsModel.getInLoanTransactionsModelPK().getDoccod() + "/"
										+ receiptNo + " - " + branchName + ")");

								body += " Receipt Number : "
										+ inLoanTransactionsModel.getInLoanTransactionsModelPK().getDoccod() + "/"
										+ receiptNo + "\n";
								body += " Receipted Amount : " + inLoanTransactionsModel.getTotprm() + "\n";
								body += " Receipted Date : " + inLoanTransactionsModel.getCreadt() + "\n";

								if (inLoanTransactionsModel.getChqnum() != null) {
									body += " Cheque Number : " + inLoanTransactionsModel.getChqnum() + "\n";
								}

								if (inLoanTransactionsModel.getPprnum() != null) {
									body += " Proposal Number : " + inLoanTransactionsModel.getPprnum() + "\n";
								}

								if (inLoanTransactionsModel.getPolnum() != null) {
									body += " Policy Number : " + inLoanTransactionsModel.getPolnum() + "\n";
								}

								body += " User : " + userName + "\n";
								body += " Branch : " + branchName + "\n\n\n";

								body += "Thanks." + "\n\n";
//							body += "This is system generated email. Do not reply.";

								emailDto.setBody(body);
								emailDto.setDepartment(AppConstant.EMAIL_DEP_CODE_FINANCE);

								EmailResponseDto responseDto = infosysWSClient.sendEmail(emailDto);

								if (responseDto.getMessage() == "Success") {
									dto = new ResponseDto();
									dto.setCode("200");
									dto.setStatus("Success");
									dto.setMessage("Success");
									return new ResponseEntity<>(dto, HttpStatus.OK);
								}

								dto = new ResponseDto();
								dto.setCode("200");
								dto.setStatus("Success");
								dto.setMessage("Success");
								return new ResponseEntity<>(dto, HttpStatus.OK);
							}

						}

					}
				}
			}
		}

		dto = new ResponseDto();
		dto.setCode("204");
		dto.setStatus("Error");
		dto.setMessage("This Receipt is already pending to cancelation.");
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@Override
	public List<CanceledReceiptDto> findPendingRequest(String token) throws Exception {

		String userCode = new JwtDecoder().generate(token);

		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);
			String locations = "";
			if (loccodes != null) {
				for (String string : loccodes) {
					locations += "'" + string + "'" + ",";
				}
			}

			locations = locations.replaceAll(",$", "");

			if (locations != "") {

				List<CanceledReceiptModel> canceledReceiptModels = receiptCancelationCustomDao
						.findPendingRequest(locations, "PENDING", loccodes.contains("HO"));
				List<CanceledReceiptDto> canceledReceiptDtos = new ArrayList<>();

				canceledReceiptModels.forEach(ca -> {
					CanceledReceiptDto canceledReceiptDto = new CanceledReceiptDto();
					canceledReceiptDto.setLocCode(ca.getLocCode());
					canceledReceiptDto.setPolNum(ca.getPolNum());
					canceledReceiptDto.setPprNum(ca.getPprNum());
					canceledReceiptDto.setReason(ca.getReason());
					canceledReceiptDto.setReceiptNo(ca.getReceiptNo());
					canceledReceiptDto.setRequestBy(ca.getRequestBy());
					canceledReceiptDto.setRequestDate(ca.getRequestDate());
					canceledReceiptDto.setSbuCode(ca.getSbuCode());
					canceledReceiptDto.setStatus(ca.getStatus());
					canceledReceiptDto.setAmount(ca.getAmount());
					canceledReceiptDto.setDocCode(ca.getDocCode());
					canceledReceiptDto.setGmRemark(ca.getApproverRemark());
					canceledReceiptDto.setApprovedDate(ca.getApprovedDate());
					canceledReceiptDto.setApprovedBy(ca.getApprovedBy());

					canceledReceiptDtos.add(canceledReceiptDto);
				});

				return canceledReceiptDtos;

			}
		}

		return null;
	}

	@Override
	public List<CanceledReceiptDto> findCanceledRequest(String token) throws Exception {

		String userCode = new JwtDecoder().generate(token);

		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);
			String locations = "";
			if (loccodes != null) {
				for (String string : loccodes) {
					locations += "'" + string + "'" + ",";
				}
			}

			locations = locations.replaceAll(",$", "");

			if (locations != "") {

				List<CanceledReceiptModel> canceledReceiptModels = receiptCancelationCustomDao
						.findPendingRequest(locations, "CANCELED", loccodes.contains("HO"));
				List<CanceledReceiptDto> canceledReceiptDtos = new ArrayList<>();

				canceledReceiptModels.forEach(ca -> {
					CanceledReceiptDto canceledReceiptDto = new CanceledReceiptDto();
					canceledReceiptDto.setLocCode(ca.getLocCode());
					canceledReceiptDto.setPolNum(ca.getPolNum());
					canceledReceiptDto.setPprNum(ca.getPprNum());
					canceledReceiptDto.setReason(ca.getReason());
					canceledReceiptDto.setReceiptNo(ca.getReceiptNo());
					canceledReceiptDto.setRequestBy(ca.getRequestBy());
					canceledReceiptDto.setRequestDate(ca.getRequestDate());
					canceledReceiptDto.setSbuCode(ca.getSbuCode());
					canceledReceiptDto.setStatus(ca.getStatus());
					canceledReceiptDto.setAmount(ca.getAmount());
					canceledReceiptDto.setDocCode(ca.getDocCode());
					canceledReceiptDto.setGmRemark(ca.getApproverRemark());
					canceledReceiptDto.setApprovedDate(ca.getApprovedDate());
					canceledReceiptDto.setApprovedBy(ca.getApprovedBy());

					canceledReceiptDtos.add(canceledReceiptDto);
				});

				return canceledReceiptDtos;

			}
		}

		return null;
	}

	@Override
	public List<CanceledReceiptDto> findApprovedRequest(String token) throws Exception {

		String userCode = new JwtDecoder().generate(token);

		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);
			String locations = "";
			if (loccodes != null) {
				for (String string : loccodes) {
					locations += "'" + string + "'" + ",";
				}
			}

			locations = locations.replaceAll(",$", "");

			if (locations != "") {

				List<CanceledReceiptModel> canceledReceiptModels = receiptCancelationCustomDao
						.findPendingRequest(locations, "APPROVED", loccodes.contains("HO"));
				List<CanceledReceiptDto> canceledReceiptDtos = new ArrayList<>();

				canceledReceiptModels.forEach(ca -> {
					CanceledReceiptDto canceledReceiptDto = new CanceledReceiptDto();
					canceledReceiptDto.setLocCode(ca.getLocCode());
					canceledReceiptDto.setPolNum(ca.getPolNum());
					canceledReceiptDto.setPprNum(ca.getPprNum());
					canceledReceiptDto.setReason(ca.getReason());
					canceledReceiptDto.setReceiptNo(ca.getReceiptNo());
					canceledReceiptDto.setRequestBy(ca.getRequestBy());
					canceledReceiptDto.setRequestDate(ca.getRequestDate());
					canceledReceiptDto.setSbuCode(ca.getSbuCode());
					canceledReceiptDto.setStatus(ca.getStatus());
					canceledReceiptDto.setAmount(ca.getAmount());
					canceledReceiptDto.setDocCode(ca.getDocCode());
					canceledReceiptDto.setGmRemark(ca.getApproverRemark());
					canceledReceiptDto.setApprovedDate(ca.getApprovedDate());
					canceledReceiptDto.setApprovedBy(ca.getApprovedBy());

					canceledReceiptDtos.add(canceledReceiptDto);
				});

				return canceledReceiptDtos;

			}
		}

		return null;
	}

	@Override
	public List<CanceledReceiptDto> findPendingRequest(String token, Integer page, Integer offset) throws Exception {
		String userCode = new JwtDecoder().generate(token);

		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);
			String locations = "";
			if (loccodes != null) {
				for (String string : loccodes) {
					locations += "'" + string + "'" + ",";
				}
			}

			locations = locations.replaceAll(",$", "");

			if (locations != "") {

				List<CanceledReceiptModel> canceledReceiptModels = receiptCancelationCustomDao
						.findPendingRequest(locations, "PENDING", loccodes.contains("HO"), page, offset);
				List<CanceledReceiptDto> canceledReceiptDtos = new ArrayList<>();

				canceledReceiptModels.forEach(ca -> {
					CanceledReceiptDto canceledReceiptDto = new CanceledReceiptDto();
					canceledReceiptDto.setLocCode(ca.getLocCode());
					canceledReceiptDto.setPolNum(ca.getPolNum());
					canceledReceiptDto.setPprNum(ca.getPprNum());
					canceledReceiptDto.setReason(ca.getReason());
					canceledReceiptDto.setReceiptNo(ca.getReceiptNo());
					canceledReceiptDto.setRequestBy(ca.getRequestBy());
					canceledReceiptDto.setRequestDate(ca.getRequestDate());
					canceledReceiptDto.setSbuCode(ca.getSbuCode());
					canceledReceiptDto.setStatus(ca.getStatus());
					canceledReceiptDto.setAmount(ca.getAmount());
					canceledReceiptDto.setDocCode(ca.getDocCode());
					canceledReceiptDto.setGmRemark(ca.getApproverRemark());
					canceledReceiptDto.setApprovedDate(ca.getApprovedDate());
					canceledReceiptDto.setApprovedBy(ca.getApprovedBy());

					canceledReceiptDtos.add(canceledReceiptDto);
				});

				return canceledReceiptDtos;

			}
		}

		return null;
	}

	@Override
	public Integer findPendingRequestLength(String token) throws Exception {
		String userCode = new JwtDecoder().generate(token);

		if (userCode != null) {
			List<String> loccodes = branchUnderwriteDao.findLocCodes(userCode);
			String locations = "";
			if (loccodes != null) {
				for (String string : loccodes) {
					locations += "'" + string + "'" + ",";
				}
			}

			locations = locations.replaceAll(",$", "");

			if (locations != "") {

				Integer count = receiptCancelationCustomDao
						.findPendingRequestLength(locations, "PENDING", loccodes.contains("HO"));
				

				return count;

			}
		}

		return 0;
	}

	

}
