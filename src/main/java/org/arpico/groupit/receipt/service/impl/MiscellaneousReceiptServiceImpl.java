package org.arpico.groupit.receipt.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.client.InfosysWSClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.BankDao;
import org.arpico.groupit.receipt.dao.RmsDocTxndDao;
import org.arpico.groupit.receipt.dao.RmsDocTxnmCustomDao;
import org.arpico.groupit.receipt.dao.RmsDocTxnmDao;
import org.arpico.groupit.receipt.dao.RmsItemMasterCustomDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.EmailDto;
import org.arpico.groupit.receipt.dto.ExpenseDto;
import org.arpico.groupit.receipt.dto.InventoryDetailsDto;
import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.RmsDocTxnmGridDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.BankModel;
import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.arpico.groupit.receipt.model.RmsItemMasterModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxndModelPK;
import org.arpico.groupit.receipt.model.pk.RmsDocTxnmModelPK;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.MiscellaneousReceiptService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CurrencyFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MiscellaneousReceiptServiceImpl implements MiscellaneousReceiptService {

	@Autowired
	private JwtDecoder decoder;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private RmsItemMasterCustomDao rmsItemMasterCustomDao;

	@Autowired
	private RmsDocTxndDao rmsDocTxndDao;

	@Autowired
	private RmsDocTxnmDao rmsDocTxnmDao;

	@Autowired
	private RmsDocTxnmCustomDao rmsDocTxnmCustomDao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private RmsUserDao rmsUserDao;

	@Autowired
	private CurrencyFormat currencyFormat;

	@Autowired
	private ItextReceipt itextReceipt;

	@Autowired
	private BankDao bankDao;

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<Object> save(MiscellaneousReceiptInvDto dto, String token) throws Exception {

		ResponseDto responseDto = null;

		String user = decoder.generate(token);
		String physicalBranch = decoder.generateLoc(token);

		String[] numberGen = numberGenerator.generateNewId("", "", "SQOIIS", "");

		System.out.println(Arrays.toString(numberGen));

		if (numberGen[0].equals("Success")) {

			String docNo = numberGen[1];

			RmsDocTxnmModel docTxnmModel = getRmsDocTxnmModelInv(dto, user, docNo, physicalBranch);
			List<RmsItemMasterModel> itemList = new ArrayList<>();
			List<RmsDocTxndModel> docTxndModels = new ArrayList<>();

			for (int i = 0; i < dto.getExpencess().size(); i++) {
				ExpenseDto e = dto.getExpencess().get(i);
				RmsItemMasterModel itemMasterModel = rmsItemMasterCustomDao.findbyId(e.getExpenseId());
				itemList.add(itemMasterModel);
				docTxndModels.add(getDocTxndModelInv(dto, user, docNo, e, i, itemMasterModel));
			}

			RmsDocTxnmModel docTxnmModel2 = rmsDocTxnmDao.save(docTxnmModel);
			List<RmsDocTxndModel> docTxndModels2 = (List<RmsDocTxndModel>) rmsDocTxndDao.save(docTxndModels);

			ReceiptPrintDto printDto = null;

			if (docTxnmModel2 != null) {
				if (docTxndModels2 != null) {

					try {
						EmailDto emailDto = new EmailDto();

						String toEmail = userDao.getUserEmail(user);
						// String toEmail="anjana.t@arpicoinsurance.com";
						String fromEmail = toEmail;

						if (toEmail != null && toEmail != "" && fromEmail != null && fromEmail != "") {

							List<String> ccMails = new ArrayList<>();
							ccMails.add(fromEmail);

							emailDto.setAttachments(new ArrayList<>());
							emailDto.setCcMails(ccMails);
							emailDto.setFromMail(fromEmail);
							emailDto.setToMail(toEmail);
							emailDto.setToken("no token");

							emailDto.setUserCode(user);

							emailDto.setSubject("Miscellaneous Receipt (INV)");

							String body = "Miscellaneous Receipt (INV) \n\n";
							body += "Receipt No : " + docTxnmModel.getRmsDocTxnmModelPK().getDocCode() + "\n";
							body += "Receipted Date : " + docTxnmModel.getCreDate() + "\n";
							body += "Branch : " + docTxndModels.get(0).getDimm04() + "\n";
							body += "Advisor Code : " + docTxnmModel.getRef1() + "\n\n Items \n\n";

							Integer count = 1;

							for (RmsDocTxndModel docTxndModel : docTxndModels) {

								for (RmsItemMasterModel item : itemList) {
									if (item.getItemCode().equals(docTxndModel.getItemCode())) {
										body += count + ".\t " + item.getItemName() + "(" + item.getItemCode() + ") : "
												+ docTxndModel.getQty();
									}
								}

							}

							body += "\n\n";

							body += "User Name : " + docTxnmModel.getCreBy();
							body += "Remark : " + docTxnmModel.getRemarks();

							emailDto.setBody(body);

							emailDto.setDepartment(AppConstant.EMAIL_ADMIN);

							new InfosysWSClient().sendEmail(emailDto);

						}

					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						printDto = getReceiptPrintDto(docTxnmModel, docTxndModels, user, itemList, dto, false);
					} catch (Exception e) {
						e.printStackTrace();
					}

					responseDto = new ResponseDto();
					responseDto.setCode("200");
					responseDto.setStatus("Success");
					responseDto.setMessage(docNo);
					responseDto.setData(itextReceipt.createReceipt(printDto));
					return new ResponseEntity<>(responseDto, HttpStatus.OK);
				}
			}
		} else {
			responseDto = new ResponseDto();
			responseDto.setCode("204");
			responseDto.setStatus("Error");
			responseDto.setMessage("Number Gereration Error");
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}

		return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ReceiptPrintDto getReceiptPrintDto(RmsDocTxnmModel docTxnmModel, List<RmsDocTxndModel> docTxndModels,
			String user, List<RmsItemMasterModel> itemList, MiscellaneousReceiptInvDto miscellaneousReceiptInvDto,
			boolean b) throws Exception {
		ReceiptPrintDto printDto = new ReceiptPrintDto();

		List<InventoryDetailsDto> detailsDtos = new ArrayList<>();

		List<AgentModel> agentModels = agentDao.findAgentByCodeAll(docTxnmModel.getRef1());

		String userName = rmsUserDao.getName(user);

		printDto.setAgtCode(Integer.parseInt(docTxnmModel.getRef1()));
		printDto.setAgtName(agentModels.get(0).getAgentName());
		printDto.setAmt(docTxnmModel.getAmtfcu());
		printDto.setAmtInWord(currencyFormat.numberToWords(docTxnmModel.getAmtfcu()));
		printDto.setDocCode(docTxnmModel.getRmsDocTxnmModelPK().getDocCode());
		printDto.setDocNum(docTxnmModel.getRmsDocTxnmModelPK().getDocNo());
		printDto.setLocation(docTxndModels.get(0).getDimm04());
		printDto.setPayMode(docTxnmModel.getRef2());
		printDto.setRctDate(docTxnmModel.getCreDate());
		printDto.setRctStatus("");
		printDto.setRemark(docTxnmModel.getRemarks());
		printDto.setUserName(userName);

		System.out.println(miscellaneousReceiptInvDto.getChqNo());
		if (miscellaneousReceiptInvDto.getChqNo() != null && !miscellaneousReceiptInvDto.getChqNo().equals("")) {
			printDto.setChqNo(Integer.parseInt(miscellaneousReceiptInvDto.getChqNo()));
		}
		if (miscellaneousReceiptInvDto.getChqDate() != null && !miscellaneousReceiptInvDto.getChqDate().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			printDto.setChqDate(
					new SimpleDateFormat("dd/MM/yyyy").format(sdf.parse(miscellaneousReceiptInvDto.getChqDate())));
		}
		if (miscellaneousReceiptInvDto.getChqBank() != null && !miscellaneousReceiptInvDto.getChqBank().equals("")) {
			printDto.setBankCode(Integer.parseInt(miscellaneousReceiptInvDto.getChqBank()));
		}

		System.out.println("Item List");

		docTxndModels.forEach(System.out::println);

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

		detailsDtos.forEach(System.out::println);

		return printDto;
	}

	private RmsDocTxndModel getDocTxndModelInv(MiscellaneousReceiptInvDto dto, String user, String docNo, ExpenseDto e,
			Integer seqNo, RmsItemMasterModel itemMasterModel) throws Exception {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		RmsDocTxndModelPK pk = new RmsDocTxndModelPK();
		pk.setDocCode(AppConstant.DOC_CODE_OIIS);
		pk.setDocNo(Integer.parseInt(docNo));
		pk.setLocCode("HO");
		pk.setSbuCode(AppConstant.SBU_CODE);
		pk.setSeqNo(seqNo + 1);

		RmsDocTxndModel model = new RmsDocTxndModel();
		model.setRmsDocTxndModelPK(pk);
		model.setPluCode(itemMasterModel.getPluCode());
		model.setItemCode(itemMasterModel.getItemCode());
		model.setUnit(itemMasterModel.getUnit());
		model.setQty(Double.parseDouble(e.getQty().toString()));
		model.setPrice(itemMasterModel.getUnitPrice());
		model.setAvgpri(itemMasterModel.getAvgPrice());
		model.setRemark(e.getRemark());
		model.setDstat("VAL");
		model.setCreateBy(user);
		model.setCreDate(new Date());
		model.setMod_date("0000-00-00");
		model.setGlgrup(itemMasterModel.getGlGroup());
		model.setItemGroup(itemMasterModel.getItmGroup());
		model.setDimm04(dto.getBranch());
		model.setDimm05(AppConstant.LIFE);
		model.setSysupload("Y");
		model.setAmtfcu(itemMasterModel.getUnitPrice());

		///////// Empty //////////////////////////////

		model.setDisrate(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxRate(AppConstant.ZERO_FOR_DECIMAL);
		model.setProfiteRate(AppConstant.ZERO_FOR_DECIMAL);
		model.setIssuedQty(AppConstant.ZERO_FOR_DECIMAL);
		model.setSourceDocNo(AppConstant.ZERO);
		model.setPosNo(AppConstant.ZERO);
		model.setTaxRate2(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxRate3(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxRate4(AppConstant.ZERO_FOR_DECIMAL);
		model.setDiscReimberse(AppConstant.ZERO_TWO_DECIMAL);
		model.setTaxAmt1(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxAmt2(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxAmt3(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxAmt4(AppConstant.ZERO_FOR_DECIMAL);
		model.setJobTyp(Double.toString(AppConstant.ZERO_TWO_DECIMAL));
		model.setJobHid(AppConstant.ZERO);
		model.setJobNo(Double.toString(AppConstant.ZERO_TWO_DECIMAL));
		model.setJobSeq(AppConstant.ZERO);
		model.setMatcst(AppConstant.ZERO_FOR_DECIMAL);
		model.setEnecst(AppConstant.ZERO_FOR_DECIMAL);
		model.setLabCst(Double.toString(AppConstant.ZERO_FOR_DECIMAL));
		model.setFixovh(AppConstant.ZERO_FOR_DECIMAL);
		model.setVarovh(AppConstant.ZERO_FOR_DECIMAL);
		model.setStdcst(AppConstant.ZERO_FOR_DECIMAL);
		model.setUnipak(AppConstant.ZERO_ZERO_DECIMAL);
		model.setInterid(AppConstant.ZERO_ZERO_DECIMAL);
		model.setLotnum(AppConstant.ZERO_ZERO_DECIMAL);
		model.setStockLoc(AppConstant.EMPTY_STRING);
		model.setReaCode(AppConstant.EMPTY_STRING);
		model.setMod_by(AppConstant.EMPTY_STRING);
		model.setSourceDocCode(AppConstant.EMPTY_STRING);
		model.setTaxCode(AppConstant.EMPTY_STRING);
		model.setTaxCode2(AppConstant.EMPTY_STRING);
		model.setTaxCode3(AppConstant.EMPTY_STRING);
		model.setTaxCode4(AppConstant.EMPTY_STRING);
		model.setPrcflg(AppConstant.EMPTY_STRING);
		model.setDescri(AppConstant.EMPTY_STRING);
		model.setDimm06(AppConstant.EMPTY_STRING);
		model.setDimm07(AppConstant.EMPTY_STRING);
		model.setDimm08(AppConstant.EMPTY_STRING);
		model.setDimm09(AppConstant.EMPTY_STRING);
		model.setDimm10(AppConstant.EMPTY_STRING);

		////// END Empty //////////////////////////////

		return model;
	}

	private RmsDocTxnmModel getRmsDocTxnmModelInv(MiscellaneousReceiptInvDto dto, String user, String docNo,
			String physicalBranch) throws Exception {

		BankModel bankModel = bankDao.getBankById(dto.getBank());

		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		RmsDocTxnmModelPK pk = new RmsDocTxnmModelPK();
		pk.setDocCode(AppConstant.DOC_CODE_OIIS);
		pk.setDocNo(Integer.parseInt(docNo));
		pk.setLocCode("HO");
		pk.setSbuCode(AppConstant.SBU_CODE);

		RmsDocTxnmModel model = new RmsDocTxnmModel();

		model.setCustSupF("S");
		model.setCustSupCode(bankModel.getAcccode());
		model.setRmsDocTxnmModelPK(pk);
		model.setRef1(dto.getAgent());
		model.setRef2(dto.getPaymode());
		model.setMstat("VAL");
		model.setCreBy(user);
		model.setTxnDate(new Date());
		model.setCreDate(new Date());
		model.setModDate("0000-00-00");
		model.setInvAmt(dto.getAmount());
		model.setSetOffAmt(dto.getAmount());
		model.setDownloaded("0");
		model.setDeliDate("0000-00-00");
		model.setVourai("F");
		model.setGlupdt("F");
		model.setExcrat(1.0000);
		model.setCurrCode("LKR");
		model.setAmtfcu(dto.getAmount());
		model.setRemarks(dto.getRemark() != null ? dto.getRemark() : AppConstant.EMPTY_STRING);
		model.setRef3(dto.getChqNo() != null ? dto.getChqNo() : AppConstant.EMPTY_STRING);

		///////// Empty //////////////////////////////

		model.setDetlineSeq(AppConstant.ZERO);
		model.setTaxAmt1(AppConstant.ZERO_FOR_DECIMAL);
		model.setTaxAmt2(AppConstant.ZERO_FOR_DECIMAL);
		model.setSetOffAmtPd(AppConstant.ZERO_FOR_DECIMAL);
		model.setTradis(AppConstant.ZERO_FOR_DECIMAL);
		model.setBatcno(AppConstant.ZERO_ZERO_DECIMAL);
		model.setBattyp(AppConstant.EMPTY_STRING);
		model.setCostcent(AppConstant.EMPTY_STRING);
		model.setJobNo(AppConstant.EMPTY_STRING);
		model.setReqBy(AppConstant.EMPTY_STRING);
		model.setIsuBy(AppConstant.EMPTY_STRING);
		model.setRecBy(AppConstant.EMPTY_STRING);
		model.setPassBy(AppConstant.EMPTY_STRING);
		model.setFromLoc(AppConstant.EMPTY_STRING);
		model.setToLoc(AppConstant.EMPTY_STRING);
		model.setModBy(AppConstant.EMPTY_STRING);
		model.setRepId(AppConstant.EMPTY_STRING);
		model.setFormInvloc(AppConstant.EMPTY_STRING);
		model.setToInvLoc(AppConstant.EMPTY_STRING);
		model.setConfirmd(AppConstant.EMPTY_STRING);
		model.setCnfuser(AppConstant.EMPTY_STRING);
		model.setGlgrup(AppConstant.EMPTY_STRING);
		model.setLocser(AppConstant.EMPTY_STRING);
		model.setRef4(AppConstant.EMPTY_STRING);
		model.setRef5(AppConstant.EMPTY_STRING);

		////// END Empty //////////////////////////////
		return model;
	}

	@Override
	public ArrayList<RmsDocTxnmGridDto> getLast(String token) throws Exception {

		String creBy = decoder.generate(token);
		ArrayList<RmsDocTxnmGridDto> docTxnmGridDtos = new ArrayList<>();
		List<RmsDocTxnmGridModel> docTxnmModels = rmsDocTxnmCustomDao.findTop10(creBy);

		if (docTxnmModels != null) {
			docTxnmModels.forEach(e -> {
				docTxnmGridDtos.add(getTxnmGridDto(e));
			});
		}

		return docTxnmGridDtos;
	}

	private RmsDocTxnmGridDto getTxnmGridDto(RmsDocTxnmGridModel e) {
		RmsDocTxnmGridDto dto = new RmsDocTxnmGridDto();
		dto.setAgentCode(e.getAgentCode());
		dto.setAmount(new BigDecimal(e.getAmount()).setScale(2, RoundingMode.HALF_UP).doubleValue());
		dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(e.getDate()));
		dto.setDocCode(e.getDocCode());
		dto.setDocNo(e.getDocNo());
		return dto;
	}

}
