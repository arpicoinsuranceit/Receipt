package org.arpico.groupit.receipt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.BenefictDetailsDao;
import org.arpico.groupit.receipt.dao.CustomerDao;
import org.arpico.groupit.receipt.dao.InBillingTransactionsDao;
import org.arpico.groupit.receipt.dao.InOccuLoadDatDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsDao;
import org.arpico.groupit.receipt.dao.InPropLoadingDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqDao;
import org.arpico.groupit.receipt.dao.InPropShedulesDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsDao;
import org.arpico.groupit.receipt.dao.InProposalDao;
import org.arpico.groupit.receipt.dao.InTransactionsDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.ChildrenDto;
import org.arpico.groupit.receipt.dto.MedicalRequirementsDto;
import org.arpico.groupit.receipt.dto.ProposalBasicDetailsDto;
import org.arpico.groupit.receipt.dto.QuoBenfDto;
import org.arpico.groupit.receipt.dto.QuoChildBenefDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.dto.SheduleDto;
import org.arpico.groupit.receipt.dto.SurrenderValsDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.CustomerModel;
import org.arpico.groupit.receipt.model.InBillingTransactionsModel;
import org.arpico.groupit.receipt.model.InOcuLoadDetModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InPropFamDetailsModelPK;
import org.arpico.groupit.receipt.model.pk.InPropLoadingModelPK;
import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;
import org.arpico.groupit.receipt.model.pk.InPropSchedulesModelPK;
import org.arpico.groupit.receipt.model.pk.InPropSurrenderValsPK;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.QuotationReceiptService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CalculationUtils;
import org.arpico.groupit.receipt.util.CommonMethodsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuotationReceiptServiceImpl implements QuotationReceiptService {

	@Autowired
	private InProposalDao inProposalDao;

	@Autowired
	private InPropAddBenefictDao inPropAddBenefictDao;

	@Autowired
	private QuotationClient quotationClient;

	@Autowired
	private NumberGenerator numberGenerator;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private BenefictDetailsDao benefictDetailsDao;

	@Autowired
	private InPropShedulesDao inPropShedulesDao;

	@Autowired
	private InPropFamDetailsDao inPropFamDetailsDao;

	@Autowired
	private InOccuLoadDatDao occuLoadDatdao;

	@Autowired
	private InPropLoadingDao inPropLoadingDao;

	@Autowired
	private InPropMedicalReqDao inPropMedicalReqDao;

	@Autowired
	private InTransactionsDao inTransactionDao;

	@Autowired
	private InPropSurrenderValsDao inPropSurrenderValsDao;

	@Autowired
	private InBillingTransactionsDao inBillingTransactionDao;

	@Autowired
	private CommonMethodsUtility commonethodUtility;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RmsUserDao rmsUserDao;

	@Override
	public String saveQuotationReceipt(SaveReceiptDto saveReceiptDto) throws Exception {

		List<AgentModel> agentModels = agentDao.findAgentByCode(saveReceiptDto.getAgentCode());
		
		String agentCode = new JwtDecoder().generate(saveReceiptDto.getToken());

		System.out.println(agentCode);
		String locCode = rmsUserDao.getLocation(agentCode);
		
		if (locCode != null) {
			if (agentModels != null && agentModels.size() > 0) {

				String[] numberGen = numberGenerator.generateNewId("", "", "PROSQ", "");

				if (numberGen[0].equals("Success")) {

					ViewQuotationDto resp = quotationClient.getQuotation(saveReceiptDto.getSeqNo(),
							saveReceiptDto.getQuotationId());

					System.out.println(resp);

					List<MedicalRequirementsDto> medicalRequirementsDtos = quotationClient
							.getMediReq(saveReceiptDto.getSeqNo(), saveReceiptDto.getQuotationId());

					List<SheduleDto> sheduleDtos = quotationClient.getShedule(saveReceiptDto.getSeqNo(),
							saveReceiptDto.getQuotationId());

					List<SurrenderValsDto> surrenderValsDtos = quotationClient
							.getSurrenderVals(saveReceiptDto.getSeqNo(),
									saveReceiptDto.getQuotationId());

					// System.out.println(sheduleDtos.size());
					// Primary Keys
					InProposalsModelPK inProposalsModelPK = getProposalModelPK(saveReceiptDto, locCode);
					inProposalsModelPK.setPprnum(numberGen[1]);

					System.out.println(inProposalsModelPK.getPprnum());

					InProposalsModel inProposalsModel = getProposalModel(resp, saveReceiptDto);

					// Set Primary Keys to model
					inProposalsModel.setInProposalsModelPK(inProposalsModelPK);
					inProposalsModel.setCreaby(agentCode);
					inProposalsModel.setCurusr(agentCode);

					List<InPropLoadingModel> inPropLoadingModels = new ArrayList<>();

					List<InPropAddBenefitModel> addBenefitModels = benefictDetailsDao
							.getBenefictByProduct(resp.getProductCode());

					for (InPropAddBenefitModel inPropAddBenefitModel : addBenefitModels) {
						inPropAddBenefitModel.getInPropAddBenefitPK().setLoccod(saveReceiptDto.getBranchCode());
						inPropAddBenefitModel.getInPropAddBenefitPK()
								.setPprnum(Integer.parseInt(inProposalsModelPK.getPprnum()));
						inPropAddBenefitModel.getInPropAddBenefitPK().setPrpseq(inProposalsModelPK.getPrpseq());
						inPropAddBenefitModel.setRidtrm(0);
						inPropAddBenefitModel.setSumasu(0.0);
						inPropAddBenefitModel.setRdrprm(0.0);
						inPropAddBenefitModel.setPrmmth(0.0);
						inPropAddBenefitModel.setPrmqat(0.0);
						inPropAddBenefitModel.setPrmhlf(0.0);
						inPropAddBenefitModel.setPrmyer(0.0);

						InPropLoadingModelPK inPropLoadingModelPK = new InPropLoadingModelPK();
						inPropLoadingModelPK.setLoccod(saveReceiptDto.getBranchCode());
						inPropLoadingModelPK.setPprnum(Integer.parseInt(inProposalsModelPK.getPprnum()));
						inPropLoadingModelPK.setRidcod(inPropAddBenefitModel.getInPropAddBenefitPK().getRidcod());
						inPropLoadingModelPK.setSbucod(AppConstant.SBU_CODE);
						inPropLoadingModelPK.setPrpseq(inProposalsModelPK.getPrpseq());

						InPropLoadingModel inPropLoadingModel = new InPropLoadingModel();
						inPropLoadingModel.setInPropLoadingPK(inPropLoadingModelPK);
						inPropLoadingModel.setRidnam(inPropAddBenefitModel.getRidnam());
						inPropLoadingModel.setGrdord(inPropAddBenefitModel.getGrdord());
						inPropLoadingModel.setLockin(AppConstant.DATE);
						inPropLoadingModel.setInstyp(inPropAddBenefitModel.getInstyp());
						inPropLoadingModel.setRidtyp(inPropAddBenefitModel.getRidtyp());

						inPropLoadingModels.add(inPropLoadingModel);

					}

					for (QuoBenfDto benfDto : resp.get_mainLifeBenefits()) {
						getInPropAddBebefit(benfDto, addBenefitModels, "main", resp.get_plan().get_frequance(),
								inPropLoadingModels, resp.get_mainlife().get_occuCode());
					}

					for (QuoBenfDto benfDto : resp.get_spouseBenefits()) {
						getInPropAddBebefit(benfDto, addBenefitModels, "spouse", resp.get_plan().get_frequance(),
								inPropLoadingModels, resp.get_spouse().getOccuCode());
					}

					addBenefitModels = getChildBenefits(resp.get_childrenBenefits(), addBenefitModels, "children",
							resp.get_plan().get_frequance(), inPropLoadingModels);

					List<InPropFamDetailsModel> propFamDetailsModels = new ArrayList<>();

					for (ChildrenDto childrenDto : resp.get_children()) {
						propFamDetailsModels.add(getFamily(childrenDto, inProposalsModelPK.getPprnum(),
								inProposalsModelPK.getPrpseq(), saveReceiptDto.getBranchCode()));
					}

					List<InPropSchedulesModel> inPropScheduleList = null;

					if (sheduleDtos != null && sheduleDtos.size() > 0) {
						inPropScheduleList = new ArrayList<>();
						for (SheduleDto sheduleDto : sheduleDtos) {
							inPropScheduleList.add(getPropShedule(sheduleDto, inProposalsModelPK.getPprnum(),
									inProposalsModelPK.getPrpseq(), saveReceiptDto.getBranchCode()));
						}

					}

					final List<InPropMedicalReqModel> inPropMedicalReqModels = new ArrayList<>();

					if (medicalRequirementsDtos != null && medicalRequirementsDtos.size() > 0) {
						medicalRequirementsDtos.forEach(
								mediReq -> inPropMedicalReqModels.add(getMediReq(mediReq, inProposalsModelPK.getPprnum(),
										inProposalsModelPK.getPrpseq(), saveReceiptDto.getBranchCode())));
					}

					final List<InPropSurrenderValsModel> inPropSurrenderValsModels = new ArrayList<>();
					// System.out.println("Surrendeer Vals : " + surrenderValsDtos.size());
					if (surrenderValsDtos != null && surrenderValsDtos.size() > 0) {
						surrenderValsDtos.forEach(
								surVal -> inPropSurrenderValsModels.add(getSurrenderVals(saveReceiptDto.getAgentCode(),
										saveReceiptDto.getQuotationId(), surVal, inProposalsModelPK.getPprnum(),
										inProposalsModelPK.getPrpseq(), saveReceiptDto.getBranchCode())));
					}
					
					/////////// When Ho //////////
					
					inProposalsModel.setPprsta("L1");
					inProposalsModel.setProsta("L1");
					
					

					inProposalDao.save(inProposalsModel);
					inPropAddBenefictDao.save(addBenefitModels);
					if (inPropScheduleList != null) {
						inPropShedulesDao.save(inPropScheduleList);
					}
					if (inPropMedicalReqModels != null && inPropMedicalReqModels.size() > 0) {
						inPropMedicalReqDao.save(inPropMedicalReqModels);
					}

					InTransactionsModel inTransactionsModel = commonethodUtility.getInTransactionModel(inProposalsModel,
							saveReceiptDto, agentCode, locCode);

					InBillingTransactionsModel inBillingTransactionsModel = commonethodUtility
							.getInBillingTransactionModel(inProposalsModel, saveReceiptDto, inTransactionsModel);

					inPropFamDetailsDao.save(propFamDetailsModels);
					inPropLoadingDao.save(inPropLoadingModels);
					inTransactionDao.save(inTransactionsModel);
					inBillingTransactionDao.save(inBillingTransactionsModel);
					inPropSurrenderValsDao.save(inPropSurrenderValsModels);
					try {
						quotationClient.updateStatus(saveReceiptDto.getSeqNo(),
								saveReceiptDto.getQuotationId());
					} catch (Exception e) {
						return "Receipt Save Successfully, Error at change Quotation Status";
					}
					return "Work";
				} else {

					return "Number Generation Error";
				}
			} else {
				return "Agent not Found";
			}
		} else {
			return "User not Found";
		}

		

	}

	private InPropSurrenderValsModel getSurrenderVals(String agentCode, Integer QuoId, SurrenderValsDto surVal,
			String pprnum, Integer prpseq, String branchCode) {
		InPropSurrenderValsPK inPropSurrenderValsPK = new InPropSurrenderValsPK();

		inPropSurrenderValsPK.setPadtrm(surVal.getPadtrm().toString());
		inPropSurrenderValsPK.setPolyer(surVal.getPolyer());
		inPropSurrenderValsPK.setPprnum(pprnum);
		inPropSurrenderValsPK.setPrpseq(prpseq);
		inPropSurrenderValsPK.setQuonum(QuoId);
		inPropSurrenderValsPK.setSbucod(AppConstant.SBU_CODE);

		InPropSurrenderValsModel inPropSurrenderValsModel = new InPropSurrenderValsModel();
		inPropSurrenderValsModel.setInPropSurrenderValsPK(inPropSurrenderValsPK);
		inPropSurrenderValsModel.setIsumas(surVal.getIsumas());
		inPropSurrenderValsModel.setAdvcod(agentCode);
		inPropSurrenderValsModel.setMature(surVal.getMature());
		inPropSurrenderValsModel.setPaidup(surVal.getPaidup());
		inPropSurrenderValsModel.setPrmpad(surVal.getPrmpad());
		inPropSurrenderValsModel.setPrmpyr(surVal.getPrmpyr());
		inPropSurrenderValsModel.setSurrnd(surVal.getSurrnd());

		return inPropSurrenderValsModel;
	}

	private InPropMedicalReqModel getMediReq(MedicalRequirementsDto mediReq, String pprnum, Integer prpseq,
			String branchCode) {

		InPropMedicalReqModelPK inPropMedicalReqModelPK = new InPropMedicalReqModelPK();

		inPropMedicalReqModelPK.setInstyp(mediReq.getInsType());
		inPropMedicalReqModelPK.setLoccod(branchCode);
		inPropMedicalReqModelPK.setMedcod(mediReq.getMediCode());
		inPropMedicalReqModelPK.setPprnum(Integer.parseInt(pprnum));
		inPropMedicalReqModelPK.setPrpseq(prpseq);
		inPropMedicalReqModelPK.setSbucod(AppConstant.SBU_CODE);

		InPropMedicalReqModel inPropMedicalReqModel = new InPropMedicalReqModel();

		inPropMedicalReqModel.setInPropMedicalReqModelPK(inPropMedicalReqModelPK);
		inPropMedicalReqModel.setLockin(AppConstant.DATE);
		inPropMedicalReqModel.setTessta("N");
		inPropMedicalReqModel.setHoscod("NA");
		inPropMedicalReqModel.setPaysta("");
		inPropMedicalReqModel.setMedorg("Requested");
		inPropMedicalReqModel.setPayamt(0.00);
		inPropMedicalReqModel.setAddnot(mediReq.getAddNote());

		return inPropMedicalReqModel;
	}

	private void getInPropAddBebefit(QuoBenfDto benfDto, List<InPropAddBenefitModel> addBenefitModels, String insType,
			String frequency, List<InPropLoadingModel> inPropLoadingModels, String ocuCode) throws Exception {

		for (InPropAddBenefitModel addBenefitModel : addBenefitModels) {
			if (benfDto.getRiderCode().equals(addBenefitModel.getInPropAddBenefitPK().getRidcod())) {
				addBenefitModel.setRidtrm(benfDto.getRiderTerm());
				addBenefitModel.setSumasu(benfDto.getRiderSum());
				addBenefitModel.setRdrprm(benfDto.getPremium());
				addBenefitModel.setLockin(AppConstant.DATE);
				addBenefitModel.setInstyp(insType);

				switch (frequency) {
				case "Monthly":
					addBenefitModel.setPrmmth(benfDto.getPremium());
					break;
				case "Quartaly":
					addBenefitModel.setPrmqat(benfDto.getPremium());
					break;
				case "Half Yearly":
					addBenefitModel.setPrmhlf(benfDto.getPremium());
					break;
				case "Yearly":
					addBenefitModel.setPrmyer(benfDto.getPremium());
					break;
				case "Single Premium":
					// TODO
					break;
				default:
					break;
				}

				List<InOcuLoadDetModel> detModels = occuLoadDatdao.inOccuLoadDatDaosByOccupation(ocuCode, benfDto.getRiderCode());
				if (detModels.size() > 0) {
					InOcuLoadDetModel detModel = detModels.get(0);
					for (InPropLoadingModel propLoadingModel : inPropLoadingModels) {
						if (propLoadingModel.getInPropLoadingPK().getRidcod().equals(benfDto.getRiderCode())) {
							propLoadingModel.setOculod(Double.parseDouble(detModel.getLodcls()));
							propLoadingModel.setInstyp(insType);

						}
					}
				}
			}
		}
	}

	private InPropSchedulesModel getPropShedule(SheduleDto sheduleDto, String pprnum, Integer prpseq,
			String branchCode) {

		InPropSchedulesModelPK inPropSchedulesPK = new InPropSchedulesModelPK();
		inPropSchedulesPK.setLoccod(branchCode);
		inPropSchedulesPK.setPolyer(sheduleDto.getPolicyYear());
		inPropSchedulesPK.setPprnum(Integer.parseInt(pprnum));
		inPropSchedulesPK.setPrpseq(prpseq);
		inPropSchedulesPK.setSbucod(AppConstant.SBU_CODE);

		InPropSchedulesModel inPropSchedules = new InPropSchedulesModel();

		inPropSchedules.setInPropSchedulesPK(inPropSchedulesPK);
		inPropSchedules.setLonred(sheduleDto.getLorned());
		inPropSchedules.setOutsum(sheduleDto.getOutSum());
		inPropSchedules.setOutyer(sheduleDto.getOutYear());
		inPropSchedules.setPremum(sheduleDto.getPremium());
		inPropSchedules.setPrmrat(sheduleDto.getPremiumRate());

		return inPropSchedules;
	}

	private InPropFamDetailsModel getFamily(ChildrenDto childrenDto, String pprnum, Integer prpseq, String branchCode)
			throws ParseException {
		InPropFamDetailsModelPK famDetailsModelPK = new InPropFamDetailsModelPK();
		famDetailsModelPK.setFmlnam(childrenDto.get_cName());
		famDetailsModelPK.setLoccod(branchCode);
		famDetailsModelPK.setPprnum(Integer.parseInt(pprnum));
		famDetailsModelPK.setPrpseq(prpseq);
		famDetailsModelPK.setSbucod(AppConstant.SBU_CODE);

		InPropFamDetailsModel detailsModel = new InPropFamDetailsModel();

		detailsModel.setInPropFamDetailsPK(famDetailsModelPK);
		detailsModel.setFmlrel(childrenDto.get_cTitle().toUpperCase());
		detailsModel.setFmldob(new SimpleDateFormat("dd-MM-yyyy").parse(childrenDto.get_cDob()));
		detailsModel.setLockin(AppConstant.DATE);
		detailsModel.setFmlsex(childrenDto.get_cTitle().equals("Son") ? "M" : "F");
		detailsModel.setFmlage(childrenDto.get_cAge().floatValue());
		detailsModel.setCicapp(childrenDto.is_cCibc() ? "Y" : "N");
		detailsModel.setHbcapp(childrenDto.is_cHbc() ? "Y" : "N");
		detailsModel.setHrbapp(childrenDto.is_cHrbfc() ? "Y" : "N");
		detailsModel.setHrbapp(childrenDto.is_cHrbic() ? "Y" : "N");
		detailsModel.setShrbap(childrenDto.is_cSuhrbc() ? "Y" : "N");

		return detailsModel;
	}

	private List<InPropAddBenefitModel> getChildBenefits(ArrayList<QuoChildBenefDto> get_childrenBenefits,
			List<InPropAddBenefitModel> addBenefitModels, String insType, String frequency,
			List<InPropLoadingModel> inPropLoadingModels) throws Exception {

		Map<String, QuoBenfDto> map = new HashMap<String, QuoBenfDto>();

		for (QuoChildBenefDto quoChildBenefDto : get_childrenBenefits) {

			for (QuoBenfDto quoBenfDto : quoChildBenefDto.getBenfs()) {
				QuoBenfDto benfDto = map.get(quoBenfDto.getRiderCode());

				if (benfDto == null) {
					map.put(quoBenfDto.getRiderCode(), quoBenfDto);
				} else {
					benfDto.setPremium(benfDto.getPremium() + quoBenfDto.getPremium());
					benfDto.setRiderTerm(benfDto.getRiderTerm() > quoBenfDto.getRiderTerm() ? benfDto.getRiderTerm()
							: quoBenfDto.getRiderTerm());
				}

			}

		}

		map.forEach((k, v) -> {
			try {
				for (InPropAddBenefitModel addBenefitModel : addBenefitModels) {
					if (v.getRiderCode().equals(addBenefitModel.getInPropAddBenefitPK().getRidcod())) {
						addBenefitModel.setRidtrm(v.getRiderTerm());
						addBenefitModel.setSumasu(v.getRiderSum());
						addBenefitModel.setRdrprm(v.getPremium());
						addBenefitModel.setLockin(AppConstant.DATE);
						addBenefitModel.setInstyp(insType);

						switch (frequency) {
						case "Monthly":
							addBenefitModel.setPrmmth(v.getPremium());
							break;
						case "Quartaly":
							addBenefitModel.setPrmqat(v.getPremium());
							break;
						case "Half Yearly":
							addBenefitModel.setPrmhlf(v.getPremium());
							break;
						case "Yearly":
							addBenefitModel.setPrmyer(v.getPremium());
							break;
						case "Single Premium":
							// TODO
							break;
						default:
							break;
						}
					}
				}
			} catch (Exception e) {
				RuntimeException runtimeException = new RuntimeException("Benefict not founf : " + v.getRiderCode());
				throw runtimeException;
			}
		});

		return addBenefitModels;
	}

	private InProposalsModel getProposalModel(ViewQuotationDto resp, SaveReceiptDto saveReceiptDto)
			throws Exception {
		InProposalsModel inProposalsModel = new InProposalsModel();

		System.out.println(resp.get_mainlife().get_mDob());
		// System.out.println(resp.);

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
		inProposalsModel.setIntrat(resp.get_plan().get_interestRate() != null ? resp.get_plan().get_interestRate() : 0);
		inProposalsModel.setSmksta("N");
		inProposalsModel.setPayamt(resp.get_plan().getContribution());
		inProposalsModel.setPolfee(resp.get_plan().getPolicyFee());
		inProposalsModel.setAdmfee(resp.get_plan().getAdminFee());
		inProposalsModel.setTaxamt(resp.get_plan().getTax());
		inProposalsModel.setGrsprm(resp.get_plan().getGrsprm());
		inProposalsModel.setInvpos(resp.get_plan().getInvPos() == null ? 0 : resp.get_plan().getInvPos());
		inProposalsModel.setLifpos(resp.get_plan().getLifePos() == null ? 0 : resp.get_plan().getLifePos());
		inProposalsModel.setSumrkm(resp.get_plan().getSumatRiskMain());
		inProposalsModel.setPprsta("L0");
		inProposalsModel.setProsta("L0");
		
		inProposalsModel
				.setSumrks(resp.get_plan().getSumatRiskSpouse() != null ? resp.get_plan().getSumatRiskSpouse() : 0.0);

		inProposalsModel.setRlftrm(resp.get_plan().get_payingterm() != null ? resp.get_plan().get_payingterm() : "0");
		inProposalsModel.setJlfsex(resp.get_mainlife().get_mGender());
		inProposalsModel.setNewnic(resp.get_mainlife().get_mNic());

		switch (resp.get_plan().get_frequance()) {
		case "Monthly":
			inProposalsModel.setPrmmth(resp.get_plan().getContribution());
			inProposalsModel.setPrmmtt(resp.get_plan().get_bsaTotal());
			break;
		case "Quartaly":
			inProposalsModel.setPrmqat(resp.get_plan().getContribution());
			inProposalsModel.setPrmqtt(resp.get_plan().get_bsaTotal());
			break;
		case "Half Yearly":
			inProposalsModel.setPrmhlf(resp.get_plan().getContribution());
			inProposalsModel.setPrmhlt(resp.get_plan().get_bsaTotal());
			break;
		case "Yearly":
			inProposalsModel.setPrmyer(resp.get_plan().getContribution());
			inProposalsModel.setPrmyet(resp.get_plan().get_bsaTotal());
			break;
		case "Single Premium":
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
		}else {
			inProposalsModel.setSpoocu(Integer.toString(0));
		}

		if (resp.getProductCode().equalsIgnoreCase("ARTM")) {
			inProposalsModel.setTrgprm(resp.get_plan().getPensionPaingTerm().doubleValue());
			inProposalsModel.setIntrat(resp.get_plan().getRetAge().doubleValue());
		}

		inProposalsModel.setPrpdat(AppConstant.DATE);

		inProposalsModel.setAgmcod(saveReceiptDto.getAgentCode());
		inProposalsModel.setAdvcod(saveReceiptDto.getAgentCode());

		inProposalsModel.setNumchl(resp.get_children().size());
		inProposalsModel.setPrdcod(resp.getProductCode());
		inProposalsModel.setPrdnam(resp.getProductName());
		if (resp.get_mainlife().get_mCustCode() == null || resp.get_mainlife().get_mCustCode().length() == 0) {
			CustomerModel customerModel = getCustomer(resp);
			inProposalsModel.setCscode(customerModel.getCscode());
			
		} else {
			inProposalsModel.setCscode(resp.get_mainlife().get_mCustCode());
		}
		inProposalsModel.setCreadt(AppConstant.DATE);

		return inProposalsModel;
	}

	private InProposalsModelPK getProposalModelPK(SaveReceiptDto saveReceiptDto, String loccode) {
		InProposalsModelPK inProposalsModelPK = new InProposalsModelPK();

		inProposalsModelPK.setLoccod(loccode);
		inProposalsModelPK.setPrpseq(saveReceiptDto.getQuotationDetailId());
		inProposalsModelPK.setSbucod(AppConstant.SBU_CODE);
		inProposalsModelPK.setDoccod(AppConstant.DOC_CODE_QUOT);

		return inProposalsModelPK;
	}
	
	private CustomerModel getCustomer(ViewQuotationDto resp) throws Exception {
		
		String[] numberGen = numberGenerator.generateNewId("450", "", "CSPINSQ", "");

		System.out.println(numberGen[0]);
		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setSbucod(AppConstant.SBU_CODE);
		customerModel.setCreaby("system");
		customerModel.setCreadt(AppConstant.DATE);
		if (numberGen[0].equals("Success")) {
			customerModel.setCscode(numberGen[1]);
		}else {
			throw new RuntimeException(numberGen[0]);
		}
		
		customerModel.setLockin(AppConstant.DATE);
		customerModel.setNtitle(resp.get_mainlife().get_mTitle());
		customerModel.setNumchl(resp.get_children()!= null ? resp.get_children().size() : 0);
		customerModel.setPpdcst(resp.get_mainlife().get_mCivilStatus());
		try {
			customerModel.setPpddob(new SimpleDateFormat("dd-MM-yyyy").parse(resp.get_mainlife().get_mDob()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customerModel.setPpdeml(resp.get_mainlife().get_mEmail());
		customerModel.setPpdnag(Integer.parseInt(resp.get_mainlife().get_mAge()));
		customerModel.setPpdnam(resp.get_mainlife().get_mName());
		customerModel.setPpdnic(resp.get_mainlife().get_mNic());
		customerModel.setPpdsex(resp.get_mainlife().get_mGender());
		customerModel.setPpdtel(resp.get_mainlife().get_mMobile());
		if(resp.get_spouse() != null && resp.get_spouse().get_sAge()!= null &&  resp.get_spouse().get_sGender() != null && resp.get_spouse().getOccuCode() != null) {
			System.out.println(resp.get_spouse().toString());
			customerModel.setSagnxt(Integer.parseInt(resp.get_spouse().get_sAge()));
			try {
				customerModel.setSpodob(new SimpleDateFormat("dd-MM-yyyy").parse(resp.get_spouse().get_sDob()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			customerModel.setSponam(resp.get_spouse().get_sName());
			customerModel.setSponic(resp.get_spouse().get_sNic());
			
		}
		customerDao.save(customerModel);
		return customerModel;
	}

	@Override
	public ProposalBasicDetailsDto getBasicDetails(Integer quoId, Integer seqId) throws Exception {
		
		return null;
	}

}
