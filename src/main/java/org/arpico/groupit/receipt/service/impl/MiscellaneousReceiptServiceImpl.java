package org.arpico.groupit.receipt.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.RmsDocTxndDao;
import org.arpico.groupit.receipt.dao.RmsDocTxnmCustomDao;
import org.arpico.groupit.receipt.dao.RmsDocTxnmDao;
import org.arpico.groupit.receipt.dao.RmsItemMasterCustomDao;
import org.arpico.groupit.receipt.dto.ExpenseDto;
import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.RmsDocTxnmGridDto;
import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmGridModel;
import org.arpico.groupit.receipt.model.RmsDocTxnmModel;
import org.arpico.groupit.receipt.model.RmsItemMasterModel;
import org.arpico.groupit.receipt.model.pk.RmsDocTxndModelPK;
import org.arpico.groupit.receipt.model.pk.RmsDocTxnmModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.MiscellaneousReceiptService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.util.AppConstant;
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


	@Override
	public ResponseEntity<Object> save(MiscellaneousReceiptInvDto dto, String token) throws Exception {

		ResponseDto responseDto = null;

		String user = decoder.generate(token);

		String[] numberGen = numberGenerator.generateNewId("", "", "SQOIIS", "");

		System.out.println(Arrays.toString(numberGen));

		if (numberGen[0].equals("Success")) {

			String docNo = numberGen[1];

			RmsDocTxnmModel docTxnmModel = getRmsDocTxnmModelInv(dto, user, docNo);

			List<RmsDocTxndModel> docTxndModels = new ArrayList<>();

			for (int i = 0; i < dto.getExpencess().size(); i++) {
				ExpenseDto e = dto.getExpencess().get(i);
				docTxndModels.add(getDocTxndModelInv(dto, user, docNo, e, i));
			}

			RmsDocTxnmModel docTxnmModel2 = rmsDocTxnmDao.save(docTxnmModel);
			List<RmsDocTxndModel> docTxndModels2 = (List<RmsDocTxndModel>) rmsDocTxndDao.save(docTxndModels);

			if (docTxnmModel2 != null) {
				if (docTxndModels2 != null) {
					responseDto = new ResponseDto();
					responseDto.setCode("200");
					responseDto.setStatus("Success");
					responseDto.setMessage(docNo);
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

	private RmsDocTxndModel getDocTxndModelInv(MiscellaneousReceiptInvDto dto, String user, String docNo, ExpenseDto e,
			Integer seqNo) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		RmsItemMasterModel itemMasterModel = rmsItemMasterCustomDao.findbyId(e.getExpenseId());

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
		model.setMod_date(simpleDateFormat.parse("0000-00-00"));
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

	private RmsDocTxnmModel getRmsDocTxnmModelInv(MiscellaneousReceiptInvDto dto, String user, String docNo)
			throws ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		RmsDocTxnmModelPK pk = new RmsDocTxnmModelPK();
		pk.setDocCode(AppConstant.DOC_CODE_OIIS);
		pk.setDocNo(Integer.parseInt(docNo));
		pk.setLocCode("HO");
		pk.setSbuCode(AppConstant.SBU_CODE);

		RmsDocTxnmModel model = new RmsDocTxnmModel();

		model.setRmsDocTxnmModelPK(pk);
		model.setRef1(dto.getAgent());
		model.setRef2(dto.getPaymode());
		model.setMstat("VAL");
		model.setCreBy(user);
		model.setTxnDate(new Date());
		model.setCreDate(new Date());
		model.setModDate(simpleDateFormat.parse("0000-00-00"));
		model.setInvAmt(dto.getAmount());
		model.setSetOffAmt(dto.getAmount());
		model.setDownloaded("0");
		model.setDeliDate(simpleDateFormat.parse("0000-00-00"));
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
		model.setCustSupF(AppConstant.EMPTY_STRING);
		model.setCustSupCode(AppConstant.EMPTY_STRING);
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
