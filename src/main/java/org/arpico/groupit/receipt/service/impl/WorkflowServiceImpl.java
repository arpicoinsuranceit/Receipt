package org.arpico.groupit.receipt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.InBillingTransactionsCustomDao;
import org.arpico.groupit.receipt.dao.InPromiseDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictCustomDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.PaymentHistoryDto;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.dto.ShortPremiumDto;
import org.arpico.groupit.receipt.dto.WorkFlowPolicyGridDto;
import org.arpico.groupit.receipt.dto.WorkflowProposalBenefictDetailDto;
import org.arpico.groupit.receipt.dto.WorkflowProposalChildrenDto;
import org.arpico.groupit.receipt.dto.WorkflowProposalMainLifeDto;
import org.arpico.groupit.receipt.dto.WorkflowProposalSpouseDto;
import org.arpico.groupit.receipt.dto.WorkfolwProposalDto;
import org.arpico.groupit.receipt.model.InPromisesModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;
import org.arpico.groupit.receipt.model.PaymentHistoryModel;
import org.arpico.groupit.receipt.model.ShortPremiumModel;
import org.arpico.groupit.receipt.model.WorkFlowPolicyGridModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.WorkflowService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.DaoParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private InPromiseDao inPromiseDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtDecoder decoder;

	@Autowired
	private InProposalCustomDao inProposalCustomDao;

	@Autowired
	private InPropAddBenefictCustomDao addBenefictCustomDao;

	@Autowired
	private InPropFamDetailsCustomDao inPropFamDetailsCustomDao;

	@Autowired
	private InBillingTransactionsCustomDao billingTransactionsCustomDao;

	@Autowired
	private InTransactionCustomDao transactionCustomDao;

	@Autowired
	private DaoParameters daoParameters;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<PromisesGridDto> getPromisesList(String token, Integer page, Integer offset) throws Exception {
		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		List<InPromisesModel> promisesModels = null;

		List<PromisesGridDto> promisesGridDtos = new ArrayList<>();

		if (branches.contains("HO")) {
			promisesModels = inPromiseDao.findAllBySbuCodeAndActiveOrderByCreateDateDesc("450", 1,
					new PageRequest(page, offset));
		} else {
			promisesModels = inPromiseDao.findAllBySbuCodeAndLocCodeInAndActiveOrderByCreateDateDesc("450", branches, 1,
					new PageRequest(page, offset));
		}

		if (promisesModels != null && !promisesModels.isEmpty()) {
			promisesModels.forEach(e -> {
				promisesGridDtos.add(getPromisesGridDto(e));
			});

		} else {

		}

		return promisesGridDtos;
	}

	private PromisesGridDto getPromisesGridDto(InPromisesModel e) {
		PromisesGridDto dto = new PromisesGridDto();
		dto.setId(e.getId());
		dto.setCustName(e.getCustName());
		dto.setCustNic(e.getCustNic());
		dto.setDueDate(format.format(e.getDueDate()));
		dto.setPhoneNum(e.getPhoneNo());
		dto.setPolNum(e.getPolicyNo());
		dto.setPprNum(e.getPprno());
		dto.setPromiseDate(e.getSettleDate());
		dto.setAmount(e.getAmount());
		dto.setPayType(e.getPayType());
		dto.setRemark(e.getRemark());
		return dto;
	}

	@Override
	public Integer getLength(String token) throws Exception {
		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		Integer count = 0;

		if (branches.contains("HO")) {
			count = inPromiseDao.countBySbuCodeAndActive("450", 1);
		} else {
			count = inPromiseDao.countBySbuCodeAndLocCodeInAndActive("450", branches, 1);
		}

		return count;
	}

	@Override
	public ResponseEntity<Object> savePromise(PromisesGridDto promise, String token) throws Exception {
		try {
			String userCode = decoder.generate(token);
			String branch = decoder.generateLoc(token);

			InPromisesModel model = getInPromiseModel(userCode, branch, promise);

			List<InPromisesModel> inPromisesModels = inPromiseDao
					.findAllBySbuCodeAndActiveAndPprno(AppConstant.SBU_CODE, 1, promise.getPprNum());

			for (InPromisesModel inPromisesModel : inPromisesModels) {
				inPromisesModel.setActive(0);
				inPromisesModel.setUpdateBy(userCode);
				inPromisesModel.setUpdateDate(new Date());
			}

			inPromiseDao.save(inPromisesModels);

			inPromiseDao.save(model);

			return new ResponseEntity<Object>("200", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private InPromisesModel getInPromiseModel(String userCode, String branch, PromisesGridDto promise)
			throws ParseException {
		InPromisesModel model = new InPromisesModel();
		model.setActive(1);
		model.setAmount(promise.getAmount());
		model.setCustName(promise.getCustName());
		model.setCustNic(promise.getCustNic());
		model.setDueDate(new SimpleDateFormat("EEE MMM dd yyyy").parse(promise.getDueDate()));
		model.setLocCode(branch);
		model.setPhoneNo(promise.getPhoneNum());
		model.setPolicyNo(promise.getPolNum());
		model.setPprno(promise.getPprNum());
		model.setSbuCode(AppConstant.SBU_CODE);
		model.setSettleDate(promise.getPromiseDate());
		model.setRemark(promise.getRemark());
		model.setPayType(promise.getPayType());

		model.setCreateBy(userCode);
		model.setCreateDate(new Date());

		return model;
	}

	@Override
	public ResponseEntity<Object> settlePromise(PromisesGridDto promise, String token) throws Exception {
		InPromisesModel inPromisesModel = inPromiseDao.findOne(promise.getId());

		if (inPromisesModel != null) {

			inPromisesModel.setActive(0);
			inPromisesModel.setUpdateBy(decoder.generate(token));
			inPromisesModel.setUpdateDate(new Date());

			inPromiseDao.save(inPromisesModel);

			return new ResponseEntity<Object>("200", HttpStatus.OK);

		} else {
			return new ResponseEntity<Object>("404", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Object> getPolicyDetails(String polnum, String pprnum) throws Exception {

		Integer propNo = Integer.parseInt(pprnum);

		InProposalsModel inProposalsModel = inProposalCustomDao.getProposalFromPprnumWorkFolw(propNo);

		String freq = "";

		if (inProposalsModel != null) {

			if (inProposalsModel.getPaytrm().equals("12")) {
				freq = "M";
			}
			if (inProposalsModel.getPaytrm().equals("6")) {
				freq = "H";
			}
			if (inProposalsModel.getPaytrm().equals("3")) {
				freq = "Q";
			}
			if (inProposalsModel.getPaytrm().equals("1")) {
				freq = "Y";
			}
			if (inProposalsModel.getPaytrm().equals("1") && inProposalsModel.getSinprm().equals("1")) {
				freq = "S";
			}

			List<InPropAddBenefitModel> addBenefitModels = addBenefictCustomDao.getBenefByPprSeqAndSumAsu(propNo,
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			List<InPropFamDetailsModel> famDetailsModels = inPropFamDetailsCustomDao.getFamilyByPprNoAndSeqNo(propNo,
					inProposalsModel.getInProposalsModelPK().getPrpseq());

			WorkflowProposalMainLifeDto mainLifeDto = getWorkflowProposalMainLifeDto(inProposalsModel);
			WorkflowProposalSpouseDto spouseDto = getWorkflowProposalSpouseDto(inProposalsModel);
			List<WorkflowProposalChildrenDto> childrenDtos = getWorkflowProposalChildrenDtos(famDetailsModels);

			List<WorkflowProposalBenefictDetailDto> benefictDetailsMain = getBenefictDetails(addBenefitModels, "main",
					freq);
			List<WorkflowProposalBenefictDetailDto> benefictDetailsSpouse = getBenefictDetails(addBenefitModels,
					"spouse", freq);
			List<WorkflowProposalBenefictDetailDto> benefictDetailsChildren = getBenefictDetails(addBenefitModels,
					"children", freq);

			WorkfolwProposalDto workfolwProposalDto = getWorkfolwProposalDto(inProposalsModel, mainLifeDto, spouseDto,
					childrenDtos, benefictDetailsMain, benefictDetailsSpouse, benefictDetailsChildren, freq);

			return new ResponseEntity<Object>(workfolwProposalDto, HttpStatus.OK);
		}

		return new ResponseEntity<Object>("404", HttpStatus.NOT_FOUND);
	}

	private WorkfolwProposalDto getWorkfolwProposalDto(InProposalsModel inProposalsModel,
			WorkflowProposalMainLifeDto mainLifeDto, WorkflowProposalSpouseDto spouseDto,
			List<WorkflowProposalChildrenDto> childrenDtos, List<WorkflowProposalBenefictDetailDto> benefictDetailsMain,
			List<WorkflowProposalBenefictDetailDto> benefictDetailsSpouse,
			List<WorkflowProposalBenefictDetailDto> benefictDetailsChildren, String freq) throws Exception {
		WorkfolwProposalDto dto = new WorkfolwProposalDto();

		dto.setMainLifeDto(mainLifeDto);
		dto.setSpouseDto(spouseDto);
		dto.setChildrenDtos(childrenDtos);
		dto.setBenefictDetailsMain(benefictDetailsMain);
		dto.setBenefictDetailsSpouse(benefictDetailsSpouse);
		dto.setBenefictDetailsChildren(benefictDetailsChildren);

		dto.setAgent(inProposalsModel.getAdvcod());
		dto.setBranch(inProposalsModel.getInProposalsModelPK().getLoccod());
		dto.setBsa(inProposalsModel.getBassum());
		dto.setComDate(format.format(inProposalsModel.getComdat()));
		dto.setExpDate(format.format(inProposalsModel.getExpdat()));
		dto.setPlan(inProposalsModel.getPrdcod());
		dto.setPolNum(inProposalsModel.getPolnum());
		dto.setPprNum(inProposalsModel.getInProposalsModelPK().getPprnum());
		dto.setStatus(inProposalsModel.getPprsta());
		dto.setTerm(inProposalsModel.getToptrm());
		dto.setTotprm(inProposalsModel.getTotprm());

		switch (freq) {
		case "M":
			dto.setFrequancy("Monthly");

			break;
		case "H":
			dto.setFrequancy("Half Yearly");
			break;
		case "Q":
			dto.setFrequancy("Quartaly");
			break;
		case "Y":
			dto.setFrequancy("Yearly");
			break;
		case "S":
			dto.setFrequancy("Single Premium");
			break;

		default:
			break;
		}

		return dto;
	}

	private List<WorkflowProposalChildrenDto> getWorkflowProposalChildrenDtos(
			List<InPropFamDetailsModel> famDetailsModels) {

		List<WorkflowProposalChildrenDto> childrenDtos = new ArrayList<>();

		famDetailsModels.forEach(e -> {
			WorkflowProposalChildrenDto dto = new WorkflowProposalChildrenDto();
			dto.setAge(e.getFmlage().intValue());
			dto.setCibc(e.getCicapp().equals("Y") ? true : false);
			dto.setHbc(e.getHbcapp().equals("Y") ? true : false);
			dto.setHcbc(e.getHrbapp().equals("Y") ? true : false);
			dto.setShcbc(e.getShrbap().equals("Y") ? true : false);
			dto.setDob(format.format(e.getFmldob()));
			dto.setFullName(e.getInPropFamDetailsPK().getFmlnam());
			dto.setRelation(e.getFmlrel());

			childrenDtos.add(dto);

		});

		return childrenDtos;
	}

	private WorkflowProposalMainLifeDto getWorkflowProposalMainLifeDto(InProposalsModel inProposalsModel) {
		WorkflowProposalMainLifeDto mainLifeDto = new WorkflowProposalMainLifeDto();

		String address = "";

		if (inProposalsModel.getPpdad1() != null && !inProposalsModel.getPpdad1().isEmpty()) {
			address += inProposalsModel.getPpdad1();
		}

		if (inProposalsModel.getPpdad2() != null && !inProposalsModel.getPpdad2().isEmpty()) {
			address += inProposalsModel.getPpdad2();
		}

		if (inProposalsModel.getPpdad3() != null && !inProposalsModel.getPpdad3().isEmpty()) {
			address += inProposalsModel.getPpdad3();
		}

		mainLifeDto.setFullName(inProposalsModel.getPpdnam());
		mainLifeDto.setAge(inProposalsModel.getPpdnag());
		mainLifeDto.setAddress(address);
		mainLifeDto.setCivilStatus(inProposalsModel.getPpdcst());
		mainLifeDto.setDob(format.format(inProposalsModel.getPpddob()));
		mainLifeDto.setEmail(inProposalsModel.getPpdeml());
		mainLifeDto.setMobile(inProposalsModel.getPpdmob());
		mainLifeDto.setNameIni(inProposalsModel.getPpdini());
		mainLifeDto.setNic(inProposalsModel.getPpdnic());
		mainLifeDto.setOccupation(inProposalsModel.getPpdocu());
		mainLifeDto.setPhone(inProposalsModel.getPpdtel());
		mainLifeDto.setPrevilageCard(inProposalsModel.getCrmnum());
		mainLifeDto.setSex(inProposalsModel.getPpdsex());

		return mainLifeDto;
	}

	private WorkflowProposalSpouseDto getWorkflowProposalSpouseDto(InProposalsModel inProposalsModel) {
		WorkflowProposalSpouseDto spouseDto = null;

		if (inProposalsModel.getSpoini() != null && inProposalsModel.getSpodob() != null) {
			spouseDto = new WorkflowProposalSpouseDto();
			spouseDto.setDob(format.format(inProposalsModel.getSpodob()));
			spouseDto.setFullName(inProposalsModel.getSponam());
			spouseDto.setNameIni(inProposalsModel.getSpoini());
			spouseDto.setNic(inProposalsModel.getSponic());
			spouseDto.setOccupation(inProposalsModel.getSpoocu());

		} else {

		}

		return spouseDto;
	}

	private List<WorkflowProposalBenefictDetailDto> getBenefictDetails(List<InPropAddBenefitModel> addBenefitModels,
			String type, String freq) {
		List<WorkflowProposalBenefictDetailDto> benefictDetails = new ArrayList<>();
		addBenefitModels.forEach(e -> {
			if (e.getInstyp().equals(type)) {

				WorkflowProposalBenefictDetailDto dao = new WorkflowProposalBenefictDetailDto();

				dao.setBenefictCode(e.getInPropAddBenefitPK().getRidcod());
				dao.setBenefictNAme(e.getRidnam());

//				switch (freq) {
//				case "M":
//					dao.setPremium(e.getPrmmth());
//					break;
//				case "H":
//					dao.setPremium(e.getPrmhlf());
//					break;
//				case "Q":
//					dao.setPremium(e.getPrmqat());
//					break;
//				case "Y":
//					dao.setPremium(e.getPrmyer());
//					break;
//				case "S":
//					dao.setPremium(e.getPrmyer());
//					break;
//
//				default:
//					break;
//				}
				dao.setPremium(e.getRdrprm());
				dao.setSumassured(e.getSumasu());
				benefictDetails.add(dao);

			}
		});

		return benefictDetails;
	}

	@Override
	public List<PromisesGridDto> getPolicies(String token, Integer page, Integer offset, String type) throws Exception {
		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		List<InProposalsModel> proposalsModels = null;

		List<PromisesGridDto> promisesGridDtos = new ArrayList<>();

		String brancheList = daoParameters.getParaForIn(branches);

		switch (type) {
		case "ACTIVE":
			if (branches.contains("HO")) {
				proposalsModels = inProposalCustomDao.getPoliciesToWorkFlowHO("PLISU");
			} else {
				proposalsModels = inProposalCustomDao.getPoliciesToWorkFlow(brancheList, "PLISU");
			}
			break;
		case "TEMP":
			if (branches.contains("HO")) {
				proposalsModels = inProposalCustomDao.getPoliciesToWorkFlowHO("PLISU");
			} else {
				proposalsModels = inProposalCustomDao.getPoliciesToWorkFlow(brancheList, "PLISU");
			}
			break;
		case "PERMANANT":
			if (branches.contains("HO")) {
				proposalsModels = inProposalCustomDao.getPoliciesToWorkFlowHO("PLISU");
			} else {
				proposalsModels = inProposalCustomDao.getPoliciesToWorkFlow(brancheList, "PLISU");
			}
			break;

		default:
			break;
		}

		if (proposalsModels != null && !proposalsModels.isEmpty()) {
			proposalsModels.forEach(e -> {
				promisesGridDtos.add(getPromisesGridDtoFromInProposal(e));
			});

		} else {

		}

		return promisesGridDtos;
	}

	private PromisesGridDto getPromisesGridDtoFromInProposal(InProposalsModel e) {

		PromisesGridDto dto = new PromisesGridDto();
		dto.setId(0);
		dto.setCustName(e.getPpdini());
		dto.setCustNic(e.getPpdnic());
		dto.setDueDate(format.format(e.getComdat()));
		dto.setPhoneNum(e.getPpdmob());
		dto.setPolNum(e.getPolnum());
		dto.setPprNum(e.getInProposalsModelPK().getPprnum());
		dto.setPromiseDate(e.getComdat());
		dto.setAmount(e.getTotprm());
		return dto;
	}

	@Override
	public ResponseEntity<Object> getPaymentHistory(String polnum, String pprnum) throws Exception {

		List<PaymentHistoryDto> historyDtos = new ArrayList<>();

		List<PaymentHistoryModel> historyModels = billingTransactionsCustomDao.getPaymentHistory(pprnum);

		historyModels.forEach(e -> {
			historyDtos.add(getPaymentHistoryDto(e));
		});

		return new ResponseEntity<Object>(historyDtos, HttpStatus.OK);
	}

	private PaymentHistoryDto getPaymentHistoryDto(PaymentHistoryModel e) {
		PaymentHistoryDto dto = new PaymentHistoryDto();

		dto.setDueamt(e.getDueamt());
		dto.setDuedat(e.getDuedat());
		dto.setOutstd(e.getOutstd());
		dto.setRemark(e.getRemark());
		dto.setSetamt(e.getSetamt());
		dto.setTxndat(e.getTxndat());
		dto.setTxnmth(e.getTxnmth());
		dto.setTxnyer(e.getTxnyer());

		return dto;
	}

	@Override
	public ResponseEntity<Object> getReceiptHistory(String polnum, String pprnum) throws Exception {
		List<LastReceiptSummeryDto> lastReceiptSummeryDtos = new ArrayList<>();

		List<LastReceiptSummeryModel> lastReceiptSummeryModels = transactionCustomDao.getLastReceiptsByProposal(pprnum);

		lastReceiptSummeryModels.forEach(e -> lastReceiptSummeryDtos.add(getLastReceiptDto(e)));

		return new ResponseEntity<Object>(lastReceiptSummeryDtos, HttpStatus.OK);
	}

	private LastReceiptSummeryDto getLastReceiptDto(LastReceiptSummeryModel e) {
		LastReceiptSummeryDto dto = new LastReceiptSummeryDto();

		dto.setAmount(e.getTotprm());
		dto.setCreadt(new SimpleDateFormat("yyyy/MM/dd").format(e.getCreadt()));
		dto.setDoccod(e.getDoccod());
		dto.setDocNo(e.getDocnum());
		dto.setPolnum(Integer.toString(e.getPolnum()));
		dto.setPprnum(e.getPprnum());
		dto.setChqrel(e.getChqrel());
		dto.setPaymod(e.getPaymod());
		return dto;
	}

	@Override
	public List<WorkFlowPolicyGridDto> getPendingActPolicies(String token) throws Exception {

		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		List<InPromisesModel> promisesModels = null;

		List<WorkFlowPolicyGridModel> flowPolicyGridModels = null;

		List<WorkFlowPolicyGridDto> flowPolicyGridDtos = new ArrayList<>();

		if (branches.contains("HO")) {
			promisesModels = inPromiseDao.findAllBySbuCodeAndActiveOrderByCreateDateDesc("450", 1);
			flowPolicyGridModels = inProposalCustomDao.getWorkFlowPolicyGridHo("PLISU");
		} else {
			promisesModels = inPromiseDao.findAllBySbuCodeAndLocCodeInAndActiveOrderByCreateDateDesc("450", branches,
					1);
			flowPolicyGridModels = inProposalCustomDao.getWorkFlowPolicyGrid("PLISU",
					daoParameters.getParaForIn(branches));
		}

		System.out.println("flowPolicyGridModels.size()" + flowPolicyGridModels.size());
		System.out.println("promisesModels.size()" + promisesModels.size());

		for (WorkFlowPolicyGridModel polGrid : flowPolicyGridModels) {

			if (!promisesModels.isEmpty()) {
				for (InPromisesModel promise : promisesModels) {

					System.out.println(promise.getPolicyNo() + "       " + polGrid.getPolicy());

					if (!(promise.getPprno().equals(polGrid.getProposal()))) {
						flowPolicyGridDtos.add(getFolwPolicyGridDto(polGrid));
					}
				}
			} else {
				flowPolicyGridDtos.add(getFolwPolicyGridDto(polGrid));
			}

		}

		return flowPolicyGridDtos;
	}

	private WorkFlowPolicyGridDto getFolwPolicyGridDto(WorkFlowPolicyGridModel polGrid) {
		WorkFlowPolicyGridDto dto = new WorkFlowPolicyGridDto();

		dto.setPprNum(polGrid.getProposal());
		dto.setAgent(polGrid.getAgent());
		dto.setBrncod(polGrid.getBrncod());
		dto.setDuedat(polGrid.getDuedat());
		dto.setPolicy(polGrid.getPolicy());
		dto.setPpdini(polGrid.getPpdini());
		dto.setTotprm(polGrid.getTotprm());

		return dto;
	}

	@Override
	public List<ShortPremiumDto> findShortPremium(String userCode, Integer page, Integer offset) throws Exception {
		List<ShortPremiumDto> premiumDtos = new ArrayList<>();

		List<String> branches = userDao.getUserLocations(userCode);

		String sql = "";

		if (!branches.contains("HO")) {
			sql = " m.loccod in (" + daoParameters.getParaForIn(branches) + ") ";
		}

		List<ShortPremiumModel> models = inProposalCustomDao.getShortPremium(sql, page, offset);

		models.forEach(e -> {
			premiumDtos.add(getShortPremiumDto(e));
		});

		return premiumDtos;
	}

	private ShortPremiumDto getShortPremiumDto(ShortPremiumModel e) {
		ShortPremiumDto dto = new ShortPremiumDto();

		dto.setAddnot(e.getAddnot());
		dto.setAgent(e.getAgent());
		dto.setLoccod(e.getLoccod());
		dto.setPprnum(e.getPprnum());
		dto.setPrpseq(e.getPrpseq());
		dto.setQuonum(e.getQuonum());
		dto.setCount(e.getReqcnt());

		return dto;
	}

	@Override
	public Integer findShortPremiumCount(String userCode) throws Exception {
		List<String> branches = userDao.getUserLocations(userCode);

		String sql = "";

		if (!branches.contains("HO")) {
			sql = " m.loccod in (" + daoParameters.getParaForIn(branches) + ") ";
		}
		return inProposalCustomDao.getShortPremiumCount(sql);
	}

	@Override
	public List<ShortPremiumDto> findPendingReq(String userCode, Integer page, Integer offset) throws Exception {
		List<ShortPremiumDto> premiumDtos = new ArrayList<>();

		List<String> branches = userDao.getUserLocations(userCode);

		String sql = "";

		if (!branches.contains("HO")) {
			sql = " m.loccod in (" + daoParameters.getParaForIn(branches) + ") ";
		}

		List<ShortPremiumModel> models = inProposalCustomDao.getPendingReq(sql, page, offset);

		models.forEach(e -> {
			premiumDtos.add(getShortPremiumDto(e));
		});

		return premiumDtos;
	}

	@Override
	public Integer findPendingReqCount(String userCode) throws Exception {
		List<String> branches = userDao.getUserLocations(userCode);

		String sql = "";

		if (!branches.contains("HO")) {
			sql = " m.loccod in (" + daoParameters.getParaForIn(branches) + ") ";
		}
		return inProposalCustomDao.getPendingReqCount(sql);
	}

}
