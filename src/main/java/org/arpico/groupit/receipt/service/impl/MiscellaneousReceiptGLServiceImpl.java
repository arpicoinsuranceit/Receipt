package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.GlCharOfAccsDao;
import org.arpico.groupit.receipt.dao.GlTranTempDao;
import org.arpico.groupit.receipt.dao.RmsGlAccCodesDao;
import org.arpico.groupit.receipt.dao.RmsRecdDao;
import org.arpico.groupit.receipt.dao.RmsRecmCustomDao;
import org.arpico.groupit.receipt.dao.RmsRecmDao;
import org.arpico.groupit.receipt.dto.AccountGLDto;
import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.dto.RmsRecmDto;
import org.arpico.groupit.receipt.model.AccountGLModel;
import org.arpico.groupit.receipt.model.GlTranTempModel;
import org.arpico.groupit.receipt.model.RmsRecdModel;
import org.arpico.groupit.receipt.model.RmsRecmGridModel;
import org.arpico.groupit.receipt.model.RmsRecmModel;
import org.arpico.groupit.receipt.model.pk.GlTranTempModelPK;
import org.arpico.groupit.receipt.model.pk.RmsRecdModelPK;
import org.arpico.groupit.receipt.model.pk.RmsRecmModelPK;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.MiscellaneousReceiptGLService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.DaoParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@PropertySource("classpath:application.properties")
public class MiscellaneousReceiptGLServiceImpl implements MiscellaneousReceiptGLService {

	@Value("${gl_acc_param}")
	private String accounts;

	@Autowired
	private DaoParameters daoParameters;

	@Autowired
	private GlCharOfAccsDao glCharOfAccsDao;

	@Autowired
	private JwtDecoder decoder;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private RmsRecmDao rmsRecmDao;

	@Autowired
	private RmsRecdDao rmsRecdDao;

	@Autowired
	private ItextReceipt itextReceipt;

	@Autowired
	private RmsGlAccCodesDao accCodesDao;

	@Autowired
	private GlTranTempDao glTranTempDao;
	
	@Autowired
	private RmsRecmCustomDao rmsRecmCustomDao;

	@Override
	public List<AccountGLDto> getAccounts() throws Exception {
		String AccountList = daoParameters.getParaForIn(accounts);

		List<AccountGLModel> accountGLModels = glCharOfAccsDao.getAccounts(AccountList);

		List<AccountGLDto> accountGLDtos = new ArrayList<>();

		accountGLModels.forEach(e -> {
			accountGLDtos.add(getAccountGLDto(e));
		});

		return accountGLDtos;
	}

	private AccountGLDto getAccountGLDto(AccountGLModel e) {
		AccountGLDto dto = new AccountGLDto();
		dto.setId(e.getId());
		dto.setDescription(e.getDescription());
		return dto;
	}

	@Override
	public ResponseEntity<Object> save(MiscellaneousReceiptInvDto dto, String token) throws Exception {
		ResponseDto responseDto = null;

		String user = decoder.generate(token);

		String[] numberGen = numberGenerator.generateNewId("", "", "SQGLRC", "");
		String[] numberGen2 = numberGenerator.generateNewId("", "", "SQGLRB", "");

		if (numberGen[0].equals("Success") && numberGen2[0].equals("Success")) {

			String docNo = numberGen[1];
			String batno = numberGen2[1];

			RmsRecmModel recmModel = getRmsRecmModel(dto, user, docNo, batno);

			List<RmsRecdModel> recdModels = new ArrayList<>();

			List<GlTranTempModel> tranTempModels = new ArrayList<>();

			for (Integer i = 0; i < dto.getAccounts().size(); i++) {

				AccountGLDto e = dto.getAccounts().get(i);

				RmsRecdModel recdModel = getRecdModel(dto, user, docNo, e, i);

				recdModels.add(recdModel);
				tranTempModels.add(getTransTempModel(dto, user, docNo, e, i, batno, recdModel, recmModel));
			}

			tranTempModels.add(getTransTempModelDR(dto, user, docNo, null, 1, batno, null, recmModel));

			boolean b = false;

			if (rmsRecmDao.save(recmModel) != null) {
				if (rmsRecdDao.save(recdModels) != null) {
					if (glTranTempDao.save(tranTempModels) != null) {
						b = true;
					}

				}
			}

			if (b) {

				ReceiptPrintDto printDto = null;

				try {
					printDto = getReceiptPrintDto(recmModel, recdModels, user, dto, false);
				} catch (Exception e) {
					e.printStackTrace();
				}

				responseDto = new ResponseDto();
				responseDto.setCode("200");
				responseDto.setStatus("Success");
				responseDto.setMessage(docNo);
				responseDto.setData(itextReceipt.createReceipt(printDto));
				return new ResponseEntity<>(responseDto, HttpStatus.OK);

			} else {

				responseDto = new ResponseDto();
				responseDto.setCode("204");
				responseDto.setStatus("Error");
				responseDto.setMessage("Error Occour at saving");
				return new ResponseEntity<>(responseDto, HttpStatus.OK);

			}

		} else {
			responseDto = new ResponseDto();
			responseDto.setCode("204");
			responseDto.setStatus("Error");
			responseDto.setMessage("Number Gereration Error");
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}

	}

	private GlTranTempModel getTransTempModelDR(MiscellaneousReceiptInvDto dto, String user, String docNo,
			Object object, int i, String batno, Object object2, RmsRecmModel recmModel) throws Exception {

		String accCode = accCodesDao.getAccCode(AppConstant.DOC_CODE_GLRC);

		GlTranTempModelPK pk = new GlTranTempModelPK();

		pk.setBatcno(batno);
		pk.setBatType(AppConstant.DOC_CODE_GLRB);
		pk.setDocCod(AppConstant.DOC_CODE_GLRC);
		pk.setDocNum(Integer.parseInt(docNo));
		pk.setLinNum(1);
		pk.setLocCode("HO");
		pk.setSbuCode(AppConstant.SBU_CODE);
		pk.setSeqNum(i);

		GlTranTempModel model = new GlTranTempModel();

		model.setGlTranTempModelPK(pk);
		model.setAmount(recmModel.getAmtfcu());
		model.setAmtfcu(recmModel.getAmtfcu());
		model.setAstCode(AppConstant.EMPTY_STRING);
		model.setTranDt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.setInterId(Double.parseDouble(accCode));
		model.setDimm1("1");
		model.setDimm2(AppConstant.EMPTY_STRING);
		model.setDimm3(AppConstant.EMPTY_STRING);
		model.setDimm4(dto.getBranch());
		model.setDimm5(AppConstant.LIFE);
		model.setDrCrTy("DR");
		model.setCurCod("LKR");
		model.setExCart(1.000000);
		model.setRemark(recmModel.getRemark());
		model.setModiType(AppConstant.EMPTY_STRING);
		model.setCreateBy(user);
		model.setCreateDt(new Date());
		model.setModiBy(AppConstant.EMPTY_STRING);
		model.setModiDate("0000-00-00 00:00:00");
		model.setBnkRec(AppConstant.EMPTY_STRING);
		model.setUnofms(AppConstant.EMPTY_STRING);
		model.setUnitQty(AppConstant.ZERO_FOR_DECIMAL);
		model.setJcat1(AppConstant.EMPTY_STRING);
		model.setJcat2(AppConstant.EMPTY_STRING);
		model.setJcat3(AppConstant.EMPTY_STRING);
		model.setJcat4(AppConstant.EMPTY_STRING);
		model.setJcat5(AppConstant.EMPTY_STRING);
		model.setReaCode(AppConstant.EMPTY_STRING);
		model.setRefTxt(AppConstant.EMPTY_STRING);
		model.setAstCode(AppConstant.EMPTY_STRING);
		model.setLkrrat(AppConstant.ZERO_FOR_DECIMAL);
		model.setPostDate("0000-00-00");
		model.setPeriod(AppConstant.EMPTY_STRING);

		return model;
	}

	private GlTranTempModel getTransTempModel(MiscellaneousReceiptInvDto dto, String user, String docNo, AccountGLDto e,
			Integer i, String batno, RmsRecdModel recdModel, RmsRecmModel recmModel) {

		GlTranTempModelPK pk = new GlTranTempModelPK();

		pk.setBatcno(batno);
		pk.setBatType(AppConstant.DOC_CODE_GLRB);
		pk.setDocCod(AppConstant.DOC_CODE_GLRC);
		pk.setDocNum(Integer.parseInt(docNo));
		pk.setLinNum(1);
		pk.setLocCode("HO");
		pk.setSbuCode(AppConstant.SBU_CODE);
		pk.setSeqNum(i + 2);

		GlTranTempModel model = new GlTranTempModel();

		model.setGlTranTempModelPK(pk);
		model.setAmount(recdModel.getAmt() * -1);
		model.setAmtfcu(recdModel.getAmtfcu() * -1);
		model.setAstCode(AppConstant.EMPTY_STRING);
		model.setTranDt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.setInterId(Double.parseDouble(Integer.toString(e.getId())));
		model.setDimm1(AppConstant.EMPTY_STRING);
		model.setDimm2(AppConstant.EMPTY_STRING);
		model.setDimm3(AppConstant.EMPTY_STRING);
		model.setDimm4(dto.getBranch());
		model.setDimm5(AppConstant.LIFE);
		model.setDrCrTy("CR");
		model.setCurCod("LKR");
		model.setExCart(1.000000);
		model.setRemark(e.getRemark());
		model.setModiType(AppConstant.EMPTY_STRING);
		model.setCreateBy(user);
		model.setCreateDt(new Date());
		model.setModiBy(AppConstant.EMPTY_STRING);
		model.setModiDate("0000-00-00 00:00:00");
		model.setBnkRec(AppConstant.EMPTY_STRING);
		model.setUnofms(AppConstant.EMPTY_STRING);
		model.setUnitQty(AppConstant.ZERO_FOR_DECIMAL);
		model.setJcat1(AppConstant.EMPTY_STRING);
		model.setJcat2(AppConstant.EMPTY_STRING);
		model.setJcat3(AppConstant.EMPTY_STRING);
		model.setJcat4(AppConstant.EMPTY_STRING);
		model.setJcat5(AppConstant.EMPTY_STRING);
		model.setReaCode(AppConstant.EMPTY_STRING);
		model.setRefTxt(AppConstant.EMPTY_STRING);
		model.setAstCode(AppConstant.EMPTY_STRING);
		model.setLkrrat(AppConstant.ZERO_FOR_DECIMAL);
		model.setPostDate("0000-00-00");
		model.setPeriod(AppConstant.EMPTY_STRING);

		// model.se

		return model;
	}

	private ReceiptPrintDto getReceiptPrintDto(RmsRecmModel recmModel, List<RmsRecdModel> recdModels, String user,
			MiscellaneousReceiptInvDto dto, boolean b) {
		ReceiptPrintDto printDto = new ReceiptPrintDto();

		printDto.setRctStatus("");
		printDto.setAmt(recmModel.getAmtfcu());
		printDto.setPayMode(recdModels.get(0).getPayMode());
		printDto.setAmtInWord(dto.getAmtInWord());
		printDto.setDocCode(recmModel.getRmsRecmModelPK().getDocCode());
		printDto.setDocNum(recmModel.getRmsRecmModelPK().getDocNo());
		printDto.setRctDate(new Date());
		printDto.setLocation(recmModel.getRmsRecmModelPK().getLocCode());
		//printDto.setRemark(recmModel.getRemark());
		printDto.setUserName(user);
		printDto.setCusName(recmModel.getRemark());
		printDto.setLocation(recmModel.getRmsRecmModelPK().getLocCode());
		printDto.setAccounts(dto.getAccounts());

		if (dto.getPaymode().equalsIgnoreCase("CQ")) {
			printDto.setChqDate(dto.getChqDate());
			printDto.setChqNo(Integer.parseInt(dto.getChqNo()));
			printDto.setBankCode(Integer.parseInt(dto.getChqBank()));
		}

		return printDto;
	}

	private RmsRecdModel getRecdModel(MiscellaneousReceiptInvDto dto, String user, String docNo, AccountGLDto e,
			Integer i) {
		RmsRecdModelPK modelPK = new RmsRecdModelPK();

		modelPK.setDocCode(AppConstant.DOC_CODE_GLRC);
		modelPK.setDocNo(Integer.parseInt(docNo));
		modelPK.setLocCode("HO");
		modelPK.setSbuCode(AppConstant.SBU_CODE);
		modelPK.setSeqNo(i + 1);

		RmsRecdModel model = new RmsRecdModel();

		model.setRmsRecdModelPK(modelPK);
		model.setBankCode(AppConstant.EMPTY_STRING);
		model.setAmt(e.getAmount());
		model.setRemarks(e.getRemark());
		model.setCreBy(user);
		model.setCreDate(new Date());
		model.setModBy(AppConstant.EMPTY_STRING);
		model.setModDate("0000-00-00");
		model.setVoucherNo(AppConstant.EMPTY_STRING);
		model.setBankBra(AppConstant.EMPTY_STRING);
		model.setCrdTyp(AppConstant.EMPTY_STRING);
		model.setPcChqDt(new Date());
		model.setBnkDt(new Date());
		model.setVouSeq(0);
		model.setOldBkDt("0000-00-00");
		model.setDimm04(dto.getBranch());
		model.setDimm05(AppConstant.EMPTY_STRING);
		model.setAmtfcu(e.getAmount());
		model.setPayMode(dto.getPaymode());

		return model;
	}

	private RmsRecmModel getRmsRecmModel(MiscellaneousReceiptInvDto dto, String user, String docNo, String batno) {

		RmsRecmModelPK modelPK = new RmsRecmModelPK();
		modelPK.setDocCode(AppConstant.DOC_CODE_GLRC);
		modelPK.setLocCode("HO");
		modelPK.setDocNo(Integer.parseInt(docNo));
		modelPK.setSbuCode(AppConstant.SBU_CODE);

		RmsRecmModel model = new RmsRecmModel();

		String data = "";
		if(dto.getPaymode().equalsIgnoreCase("CQ")) {
			data = " ("+dto.getChqNo()+")";
		}
		
		model.setRmsRecmModelPK(modelPK);

		model.setAmtfcu(dto.getAmount());
		model.setTxnDate(new Date());
		model.setCustSup("C");
		model.setCscode(AppConstant.ONE_TIME_CUSTOMER);
		model.setRemark(dto.getRemark() + data);
		model.setCreBy(user);
		model.setCreDate(new Date());
		model.setModBy(AppConstant.EMPTY_STRING);
		model.setModDate("0000-00-00");
		model.setMstat("VAL");
		model.setRecAmt(dto.getAmount());
		model.setRecBal(dto.getAmount());
		model.setJobNo(AppConstant.EMPTY_STRING);
		model.setPdBal(AppConstant.ZERO_TWO_DECIMAL);
		model.setPdChq("N");
		model.setRepManFlg("R");
		model.setRepId(AppConstant.EMPTY_STRING);
		model.setBattyp(AppConstant.DOC_CODE_GLRB);
		model.setBatcno(Double.parseDouble(batno));
		model.setGlgrup("1");
		model.setGlupdt("Y");
		model.setExcrat("1.0000");
		model.setCurrCode("LKR");
		model.setSysupload(AppConstant.EMPTY_STRING);
		model.setOldRemark(AppConstant.EMPTY_STRING);

		return model;
	}

	@Override
	public List<RmsRecmDto> getLatestReceipts(String token) throws Exception {
		
		String creBy = decoder.generate(token);
		
		List<RmsRecmGridModel> gridModels = rmsRecmCustomDao.findTop10(creBy);
		
		List<RmsRecmDto> dtos = new ArrayList<>();
		
		gridModels.forEach(e -> {
			dtos.add(getDto(e));
		});
		
		return dtos;
	}

	private RmsRecmDto getDto(RmsRecmGridModel e) {
		RmsRecmDto recmDto = new RmsRecmDto();
		
		recmDto.setAmtfcu(e.getAmtfcu());
		recmDto.setCreateDate(e.getCreateDate());
		recmDto.setDocCode(e.getDocCode());
		recmDto.setDonNo(e.getDonNo());
		recmDto.setRemark(e.getRemark());
		
		return recmDto;
	}

}
