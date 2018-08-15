package org.arpico.groupit.receipt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dao.BenefictDetailsDao;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.InOccuLoadDatDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsDao;
import org.arpico.groupit.receipt.dao.InPropLoadingDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsDao;
import org.arpico.groupit.receipt.dao.InPropShedulesDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InProposalDao;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.arpico.groupit.receipt.dto.ChildrenDto;
import org.arpico.groupit.receipt.dto.MedicalRequirementsDto;
import org.arpico.groupit.receipt.dto.QuoBenfDto;
import org.arpico.groupit.receipt.dto.QuoChildBenefDto;
import org.arpico.groupit.receipt.dto.SaveUnderwriteDto;
import org.arpico.groupit.receipt.dto.SheduleDto;
import org.arpico.groupit.receipt.dto.SurrenderValsDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.model.InOcuLoadDetModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropPreviousPolModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.pk.InPropFamDetailsModelPK;
import org.arpico.groupit.receipt.model.pk.InPropLoadingModelPK;
import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;
import org.arpico.groupit.receipt.model.pk.InPropNomDetailsModelPK;
import org.arpico.groupit.receipt.model.pk.InPropPrePolsModelPK;
import org.arpico.groupit.receipt.model.pk.InPropSchedulesModelPK;
import org.arpico.groupit.receipt.model.pk.InPropSurrenderValsPK;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.BranchUnderwriteService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.CalculationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BranchUnderwriteServiceImpl implements BranchUnderwriteService{

	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;
	
	@Autowired
	private InProposalCustomDao inProposalCustomDao;
	
	@Autowired
	private BenefictDetailsDao benefictDetailsDao;
	
	@Autowired
	private QuotationClient quotationClient;
	
	@Autowired
	private InOccuLoadDatDao occuLoadDatdao;
	
	@Autowired
	private InPropAddBenefictDao addBenefictDao;

	@Autowired
	private InPropFamDetailsDao famDetailsDao;

	@Autowired
	private InPropLoadingDao propLoadingDao;

	@Autowired
	private InPropMedicalReqDao propMedicalReqDao;

	@Autowired
	private InPropNomDetailsDao propNomDetailsDao;

	@Autowired
	private InPropPrePolsDao propPrePolsDao;

	@Autowired
	private InPropShedulesDao propScheduleDao;

	@Autowired
	private InPropSurrenderValsDao surrenderValDao;
	
	@Autowired
	private RmsUserDao rmsUserDao;
	
	@Autowired
	private InProposalDao inProposalDao;

	@Override
	public List<InProposalUnderwriteModel> getProposalToUnderwrite(String usercode) throws Exception {
		//System.out.println(usercode + "user code...");
		List<String> loccodes=branchUnderwriteDao.findLocCodes(usercode);
		String locations="";
		if(loccodes != null) {
			for (String string : loccodes) {
				locations+="'"+string+"'"+",";
			}
		}
		
		locations=locations.replaceAll(",$", "");
		
		//System.out.println(locations + " Branch Codes ------");
		
		if(locations != "") {
			return branchUnderwriteDao.findProposalToUnderwrite(locations);
		}else {
			return null;
		}
		
		
	}

	@Override
	public InProposalsModel getInProposalDetails(Integer propId, Integer propSeq) throws Exception {
		return inProposalCustomDao.getProposal(propId, propSeq);
	}

	@Override
	public ResponseEntity<Object> saveUnderwrite(SaveUnderwriteDto saveUnderwriteDto) throws Exception {
		
		String agentCode = new JwtDecoder().generate(saveUnderwriteDto.getToken());

		System.out.println(agentCode);
		String locCode = rmsUserDao.getLocation(agentCode);
		
		if (locCode != null ) {
		
			/* load InProposalDetails from marksys */
			InProposalsModel inProposalsModel=getInProposalDetails(saveUnderwriteDto.getProposalNo(), saveUnderwriteDto.getSeqNo());
			
			if(inProposalsModel != null) {
				
				inProposalsModel.setPprsta("INAC");
				inProposalsModel.setLockin(AppConstant.DATE);
				
				/* Inactive Before Line of InProposal */
				inProposalDao.save(inProposalsModel);
				
				/* get new prpseq no */
				Integer newPrpSeqNo=inProposalsModel.getInProposalsModelPK().getPrpseq() + 1;
				
				/* Set new primary key for inproposal */
				InProposalsModelPK newInProposalsModelPK=inProposalsModel.getInProposalsModelPK();
				newInProposalsModelPK.setLoccod(locCode);
				newInProposalsModelPK.setDoccod("PROP");
				newInProposalsModelPK.setPrpseq(newPrpSeqNo);
				
				/* load Quotation Details From QuotationDB */
				ViewQuotationDto quotationDto=quotationClient.getQuotation(saveUnderwriteDto.getQuotationDetailNo(), saveUnderwriteDto.getQuotationNo());
				
				/* Set new Proposal model to save */
				InProposalsModel newInProposalsModel=getInProposalModel(inProposalsModel,saveUnderwriteDto,quotationDto);
				newInProposalsModel.setInProposalsModelPK(newInProposalsModelPK);
				newInProposalsModel.setCurusr(agentCode);
				
				if(saveUnderwriteDto.getSendToApprove()) {
					newInProposalsModel.setPprsta("L1");
				}else {
					newInProposalsModel.setPprsta("L0");
				}
				
				/* Save new Line of InProposal */
				inProposalDao.save(newInProposalsModel);
				
				List<InPropLoadingModel> inPropLoadingModels = new ArrayList<>();
				
				/* Get benefit and prop loading to save */
				List<InPropAddBenefitModel> addBenefitModels = benefictDetailsDao
						.getBenefictByProduct(quotationDto.getProductCode());

				for (InPropAddBenefitModel inPropAddBenefitModel : addBenefitModels) {
					inPropAddBenefitModel.getInPropAddBenefitPK().setLoccod(newInProposalsModelPK.getLoccod());
					inPropAddBenefitModel.getInPropAddBenefitPK()
							.setPprnum(Integer.parseInt(newInProposalsModelPK.getPprnum()));
					inPropAddBenefitModel.getInPropAddBenefitPK().setPrpseq(newInProposalsModelPK.getPrpseq());
					inPropAddBenefitModel.setRidtrm(0);
					inPropAddBenefitModel.setSumasu(0.0);
					inPropAddBenefitModel.setRdrprm(0.0);
					inPropAddBenefitModel.setPrmmth(0.0);
					inPropAddBenefitModel.setPrmqat(0.0);
					inPropAddBenefitModel.setPrmhlf(0.0);
					inPropAddBenefitModel.setPrmyer(0.0);

					InPropLoadingModelPK inPropLoadingModelPK = new InPropLoadingModelPK();
					inPropLoadingModelPK.setLoccod(newInProposalsModelPK.getLoccod());
					inPropLoadingModelPK.setPprnum(Integer.parseInt(newInProposalsModelPK.getPprnum()));
					inPropLoadingModelPK.setRidcod(inPropAddBenefitModel.getInPropAddBenefitPK().getRidcod());
					inPropLoadingModelPK.setSbucod(AppConstant.SBU_CODE);
					inPropLoadingModelPK.setPrpseq(newInProposalsModelPK.getPrpseq());

					InPropLoadingModel inPropLoadingModel = new InPropLoadingModel();
					inPropLoadingModel.setInPropLoadingPK(inPropLoadingModelPK);
					inPropLoadingModel.setRidnam(inPropAddBenefitModel.getRidnam());
					inPropLoadingModel.setGrdord(inPropAddBenefitModel.getGrdord());
					inPropLoadingModel.setLockin(AppConstant.DATE);
					inPropLoadingModel.setInstyp(inPropAddBenefitModel.getInstyp());
					inPropLoadingModel.setRidtyp(inPropAddBenefitModel.getRidtyp());

					inPropLoadingModels.add(inPropLoadingModel);

				}

				/* get mainlife benefits */
				for (QuoBenfDto benfDto : quotationDto.get_mainLifeBenefits()) {
					getInPropAddBebefit(benfDto, addBenefitModels, "main", quotationDto.get_plan().get_frequance(),
							inPropLoadingModels, quotationDto.get_mainlife().get_occuCode());
				}
				/* get spouse benefits */
				for (QuoBenfDto benfDto : quotationDto.get_spouseBenefits()) {
					getInPropAddBebefit(benfDto, addBenefitModels, "spouse", quotationDto.get_plan().get_frequance(),
							inPropLoadingModels, quotationDto.get_spouse().getOccuCode());
				}
				/* get children benefits */
				addBenefitModels = getChildBenefits(quotationDto.get_childrenBenefits(), addBenefitModels, "children",
						quotationDto.get_plan().get_frequance(), inPropLoadingModels);
				
				/* Save In Prop Loadings */
				if(inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {
					propLoadingDao.save(inPropLoadingModels);
				}
				
				/* Save In Add Loadings */
				if(addBenefitModels != null && !addBenefitModels.isEmpty()) {
					addBenefictDao.save(addBenefitModels);
				}
				
				List<InPropFamDetailsModel> propFamDetailsModels = new ArrayList<>();
				/* Load Family Details */
				
				for (ChildrenDto childrenDto : quotationDto.get_children()) {
					for (ChildrenDto childrenDto1 : saveUnderwriteDto.getChildren()) {
						if(childrenDto.get_cTitle().equals(childrenDto1.get_cTitle()) && childrenDto.get_cAge().equals(childrenDto1.get_cAge()) 
								) {
							childrenDto.set_cName(childrenDto1.get_cName());
						}
					}
					
					propFamDetailsModels.add(getFamily(childrenDto, newInProposalsModelPK.getPprnum(),
							newInProposalsModelPK.getPrpseq(), newInProposalsModelPK.getLoccod()));
				}
				
				/* Save Family Details */
				if(propFamDetailsModels != null && !propFamDetailsModels.isEmpty()) {
					famDetailsDao.save(propFamDetailsModels);
				}
				
				/* Get Medical Requirements From Quotation DB */
				List<MedicalRequirementsDto> medicalRequirementsDtos = quotationClient
						.getMediReq(saveUnderwriteDto.getQuotationDetailNo());
				
				/* Get Schedule Details From Quotation DB */
				List<SheduleDto> sheduleDtos = quotationClient.getShedule(saveUnderwriteDto.getQuotationDetailNo());
		
				/* Get Surrender Values From Quotation DB */
				List<SurrenderValsDto> surrenderValsDtos = quotationClient
						.getSurrenderVals(saveUnderwriteDto.getQuotationDetailNo());
				
				List<InPropSchedulesModel> inPropScheduleList = null;
		
				/* Set Proposal Schedules */
				if (sheduleDtos != null && sheduleDtos.size() > 0) {
					inPropScheduleList = new ArrayList<>();
					for (SheduleDto sheduleDto : sheduleDtos) {
						inPropScheduleList.add(getPropShedule(sheduleDto, newInProposalsModelPK.getPprnum(),
								newInProposalsModelPK.getPrpseq(), newInProposalsModelPK.getLoccod()));
					}
		
				}
				
				/* Save Shedule */
				if(inPropScheduleList != null && !inPropScheduleList.isEmpty()) {
					propScheduleDao.save(inPropScheduleList);
				}
				
				final List<InPropMedicalReqModel> inPropMedicalReqModels = new ArrayList<>();
				/* Set Medical Requirements */
				if (medicalRequirementsDtos != null && medicalRequirementsDtos.size() > 0) {
					medicalRequirementsDtos.forEach(
							mediReq -> inPropMedicalReqModels.add(getMediReq(mediReq, newInProposalsModelPK.getPprnum(),
									newInProposalsModelPK.getPrpseq(), newInProposalsModelPK.getLoccod())));
				}
				/* Save Medical Requirements */
				if(inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {
					propMedicalReqDao.save(inPropMedicalReqModels);
				}
				
				final List<InPropSurrenderValsModel> inPropSurrenderValsModels = new ArrayList<>();
				/* Set Surrender Values */
				if (surrenderValsDtos != null && surrenderValsDtos.size() > 0) {
					surrenderValsDtos.forEach(
							surVal -> inPropSurrenderValsModels.add(getSurrenderVals(newInProposalsModel.getAdvcod(),
									saveUnderwriteDto.getQuotationNo(), surVal, newInProposalsModelPK.getPprnum(),
									newInProposalsModelPK.getPrpseq(), newInProposalsModelPK.getLoccod())));
				}
				
				/* Save Surrender Values */
				if(inPropSurrenderValsModels != null && !inPropSurrenderValsModels.isEmpty()) {
					surrenderValDao.save(inPropSurrenderValsModels);
				}
				
				/* Get Nominee Details */
				List<InPropNomDetailsModel> nomDetailsModels=getNomineeDetails(saveUnderwriteDto, newInProposalsModelPK.getPprnum(), newInProposalsModelPK.getPrpseq(), newInProposalsModelPK.getLoccod());
				
				/* Save Nominee Details */
				if(nomDetailsModels != null && !nomDetailsModels.isEmpty()) {
					propNomDetailsDao.save(nomDetailsModels);
				}
				
				/* Get Previous Policies Passing NIC */
				List<InPropPreviousPolModel> inPropPrePolsModels=inProposalCustomDao.getPreviousPolicies("450", newInProposalsModel.getPpdnic());
				List<InPropPrePolsModel> prePolsModels=new ArrayList<>();
				
				/* Set Previous Policies To Save */
				if(inPropPrePolsModels!=null && !inPropPrePolsModels.isEmpty()) {
					prePolsModels=getPreviousPol(inPropPrePolsModels,newInProposalsModel);
				}
				
				/* Save Previous Policies */
				if(prePolsModels != null && !prePolsModels.isEmpty()) {
					propPrePolsDao.save(prePolsModels);
				}
				
				return new ResponseEntity<>("Success", HttpStatus.OK);
				
				
			}else {
				return new ResponseEntity<>("Proposal Not Found", HttpStatus.NOT_FOUND);
			}
			
			
		}else {
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		}

	}

	private List<InPropPrePolsModel> getPreviousPol(List<InPropPreviousPolModel> inPropPrePolsModels,
			InProposalsModel newInProposalsModel) {
		List<InPropPrePolsModel> polsModels=new ArrayList<>();
		
		InPropPrePolsModelPK inPropPrePolsModelPK=new InPropPrePolsModelPK();
		inPropPrePolsModelPK.setLoccod(newInProposalsModel.getInProposalsModelPK().getLoccod());
		inPropPrePolsModelPK.setPprnum(Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()));
		inPropPrePolsModelPK.setSbucod(AppConstant.SBU_CODE);
		inPropPrePolsModelPK.setPrpseq(newInProposalsModel.getInProposalsModelPK().getPrpseq());
		
		inPropPrePolsModels.forEach(e ->{
			inPropPrePolsModelPK.setPolnum(Integer.toString(e.getPolnum()));
			
			InPropPrePolsModel inPropPrePolsModel=new InPropPrePolsModel();
			inPropPrePolsModel.setInPropPrePolsModelPK(inPropPrePolsModelPK);
			inPropPrePolsModel.setPplinf(e.getPplinf());
			inPropPrePolsModel.setPrdcod(e.getPrdcod());
			inPropPrePolsModel.setSumrkm(e.getSumrkm());
			
			polsModels.add(inPropPrePolsModel);
		});
		
		return polsModels;
	}

	/* Set Data to InProposalModel */
	private InProposalsModel getInProposalModel(InProposalsModel inProposalsModel, SaveUnderwriteDto saveUnderwriteDto,
			ViewQuotationDto quotationDto) throws Exception {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		InProposalsModel newInProposalsModel=inProposalsModel;
		
		newInProposalsModel.setPpdnam(saveUnderwriteDto.getMainlifeUnderwriteDto().getMainlifeFullName());
		newInProposalsModel.setPpdini(saveUnderwriteDto.getMainlifeUnderwriteDto().getMainlifeNameInitial());
		newInProposalsModel.setPpdad1(saveUnderwriteDto.getMainlifeUnderwriteDto().getAddress1());
		newInProposalsModel.setPpdad2(saveUnderwriteDto.getMainlifeUnderwriteDto().getAddress2());
		newInProposalsModel.setPpdad3(saveUnderwriteDto.getMainlifeUnderwriteDto().getAddress3());
		newInProposalsModel.setPpddob(dateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(quotationDto.get_mainlife().get_mDob()))));
		newInProposalsModel.setPpdnag(Integer.valueOf(quotationDto.get_mainlife().get_mAge()));
		newInProposalsModel.setPpdnic(quotationDto.get_mainlife().get_mNic());
		newInProposalsModel.setPpdsex(quotationDto.get_mainlife().get_mGender());
		newInProposalsModel.setPpdcst(saveUnderwriteDto.getMainlifeUnderwriteDto().getCivilStatus());
		newInProposalsModel.setPpdtel(saveUnderwriteDto.getMainlifeUnderwriteDto().getTele());
		newInProposalsModel.setPpdocu(quotationDto.get_mainlife().get_occuCode());
		newInProposalsModel.setHighcm(Double.valueOf(saveUnderwriteDto.getMainlifeUnderwriteDto().getMainlifeHeight()));
		newInProposalsModel.setWighkg(Double.valueOf(saveUnderwriteDto.getMainlifeUnderwriteDto().getMainlifeWeight()));
		newInProposalsModel.setPpdmob(saveUnderwriteDto.getMainlifeUnderwriteDto().getMobile());
		newInProposalsModel.setCscode(quotationDto.get_mainlife().get_mCustCode());
		newInProposalsModel.setPrflng(saveUnderwriteDto.getMainlifeUnderwriteDto().getMainlifePreferedLang());
		newInProposalsModel.setNtitle(quotationDto.get_mainlife().get_mTitle());
		newInProposalsModel.setCscode(inProposalsModel.getCscode());
		
		
		newInProposalsModel.setToptrm(quotationDto.get_plan().get_term());
		newInProposalsModel.setPaytrm(new CalculationUtils().getPaytrm(quotationDto.get_plan().get_frequance()));
		newInProposalsModel.setBassum(quotationDto.get_plan().get_bsa());
		newInProposalsModel.setPremum(quotationDto.get_plan().getContribution());
		newInProposalsModel.setTotprm(quotationDto.get_plan().get_bsaTotal());
		newInProposalsModel.setTrgprm(0.0);
		newInProposalsModel.setQuonum(saveUnderwriteDto.getQuotationNo());
		newInProposalsModel.setSeqnum(saveUnderwriteDto.getQuotationDetailNo());
		newInProposalsModel.setIntrat(quotationDto.get_plan().get_interestRate() != null ? quotationDto.get_plan().get_interestRate() : 0);
		newInProposalsModel.setSmksta("N");
		newInProposalsModel.setPayamt(quotationDto.get_plan().getContribution());
		newInProposalsModel.setPolfee(quotationDto.get_plan().getPolicyFee());
		newInProposalsModel.setAdmfee(quotationDto.get_plan().getAdminFee());
		newInProposalsModel.setTaxamt(quotationDto.get_plan().getTax());
		newInProposalsModel.setGrsprm(quotationDto.get_plan().getGrsprm());
		newInProposalsModel.setInvpos(quotationDto.get_plan().getInvPos());
		newInProposalsModel.setLifpos(quotationDto.get_plan().getLifePos());
		newInProposalsModel.setSumrkm(quotationDto.get_plan().getSumatRiskMain());
		newInProposalsModel.setPprsta("L1");
		newInProposalsModel
				.setSumrks(quotationDto.get_plan().getSumatRiskSpouse() != null ? quotationDto.get_plan().getSumatRiskSpouse() : 0.0);

		newInProposalsModel.setRlftrm(quotationDto.get_plan().get_payingterm() != null ? quotationDto.get_plan().get_payingterm() : "0");
		newInProposalsModel.setJlfsex(quotationDto.get_mainlife().get_mGender());
		newInProposalsModel.setNewnic(quotationDto.get_mainlife().get_mNic());
		
		
		switch (quotationDto.get_plan().get_frequance()) {
		case "Monthly":
			newInProposalsModel.setPrmmth(quotationDto.get_plan().getContribution());
			newInProposalsModel.setPrmmtt(quotationDto.get_plan().get_bsaTotal());
			break;
		case "Quartaly":
			newInProposalsModel.setPrmqat(quotationDto.get_plan().getContribution());
			newInProposalsModel.setPrmqtt(quotationDto.get_plan().get_bsaTotal());
			break;
		case "Half Yearly":
			newInProposalsModel.setPrmhlf(quotationDto.get_plan().getContribution());
			newInProposalsModel.setPrmhlt(quotationDto.get_plan().get_bsaTotal());
			break;
		case "Yearly":
			newInProposalsModel.setPrmyer(quotationDto.get_plan().getContribution());
			newInProposalsModel.setPrmyet(quotationDto.get_plan().get_bsaTotal());
			break;
		case "Single Premium":
			newInProposalsModel.setSinprm("1");
			break;
		default:
			break;
		}
		
		if (quotationDto.get_spouse() != null && quotationDto.get_spouse().is_sActive()) {

			newInProposalsModel.setSponam(saveUnderwriteDto.getSpouseUnderwriteDto().getSpouseFullName());
			newInProposalsModel.setSpoini(saveUnderwriteDto.getSpouseUnderwriteDto().getSpouseNameInitial());
			newInProposalsModel.setSpoocu(saveUnderwriteDto.getSpouseUnderwriteDto().getSpouseOccupation());
			newInProposalsModel.setStitle(quotationDto.get_spouse().get_sTitle());
			newInProposalsModel.setSponic(quotationDto.get_spouse().get_sNic());
			newInProposalsModel.setSpodob(dateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(quotationDto.get_spouse().get_sDob()))));
			newInProposalsModel.setSagnxt(Integer.valueOf(quotationDto.get_spouse().get_sAge()));
			newInProposalsModel.setShighc(Double.valueOf(saveUnderwriteDto.getSpouseUnderwriteDto().getSpouseHeight()));
			newInProposalsModel.setWighkg(Double.valueOf(saveUnderwriteDto.getSpouseUnderwriteDto().getSpouseWeight()));
			
		}

		if (quotationDto.getProductCode().equalsIgnoreCase("ARTM")) {
			newInProposalsModel.setTrgprm(quotationDto.get_plan().getPensionPaingTerm().doubleValue());
			newInProposalsModel.setIntrat(quotationDto.get_plan().getRetAge().doubleValue());
		}
		
		newInProposalsModel.setPrpdat(new SimpleDateFormat("yyyy-MM-dd").parse(saveUnderwriteDto.getPropDate()));
		newInProposalsModel.setAgmcod(saveUnderwriteDto.getAgentCode());
		newInProposalsModel.setAdvcod(saveUnderwriteDto.getAgentCode());
		
		newInProposalsModel.setNumchl(quotationDto.get_children().size());
		newInProposalsModel.setPrdcod(quotationDto.getProductCode());
		newInProposalsModel.setPrdnam(quotationDto.getProductName());
		
		newInProposalsModel.setCreadt(AppConstant.DATE);
		newInProposalsModel.setLockin(AppConstant.DATE);
		
		return newInProposalsModel;
	}
	
	/* Set Mainlife and Spouse Benefits */
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

				List<InOcuLoadDetModel> detModels = occuLoadDatdao.inOccuLoadDatDaosByOccupation(ocuCode);
				if (detModels.size() > 0) {
					InOcuLoadDetModel detModel = detModels.get(0);
					for (InPropLoadingModel propLoadingModel : inPropLoadingModels) {
						if (propLoadingModel.getInPropLoadingPK().getRidcod().equals(benfDto.getRiderCode())) {
							propLoadingModel.setOculod(Double.parseDouble(detModel.getOcucod()));
							propLoadingModel.setInstyp(insType);

						}
					}
				}
			}
		}
	}
	
	/* Set Child Benefits */
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
				RuntimeException runtimeException = new RuntimeException("Benefict not found : " + v.getRiderCode());
				throw runtimeException;
			}
		});

		return addBenefitModels;
	}

	/* Set Child Details */
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
		detailsModel.setHrbapp(childrenDto.is_cHrbfc() || childrenDto.is_cHrbic()  ? "Y" : "N");
		//detailsModel.setHrbapp(childrenDto.is_cHrbic() ? "Y" : "N");
		detailsModel.setShrbap(childrenDto.is_cSuhrbc() ? "Y" : "N");

		return detailsModel;
	}
	
	/* Set Schedule Details */
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
	
	private List<InPropNomDetailsModel> getNomineeDetails(SaveUnderwriteDto saveUnderwriteDto,
			String pprnum, Integer prpseq, String branchCode){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		InPropNomDetailsModelPK inPropNomineeValsPK = new InPropNomDetailsModelPK();

		inPropNomineeValsPK.setPprnum(Integer.parseInt(pprnum));
		inPropNomineeValsPK.setPrpseq(prpseq);
		inPropNomineeValsPK.setSbucod(AppConstant.SBU_CODE);
		inPropNomineeValsPK.setLoccod(branchCode);
		
		List<InPropNomDetailsModel> inPropNomDetailsModels=new ArrayList<>();
		
		if(saveUnderwriteDto.getNominee().size() > 0) {
			saveUnderwriteDto.getNominee().forEach(e ->{
				InPropNomDetailsModel detailsModel=new InPropNomDetailsModel();
				inPropNomineeValsPK.setNomnam(e.getName());

				detailsModel.setInPropNomDetailsModelPK(inPropNomineeValsPK);
				detailsModel.setGurdnm(e.getGuardianName());
				detailsModel.setGurnic(e.getGuardianNic());
				detailsModel.setGurrel(e.getGuardianRelation());
				detailsModel.setLockin(AppConstant.DATE);
				
				try {
					if(e.getGuardianDOB() != "" && e.getGuardianDOB() != null) {
						detailsModel.setGurdob(dateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(e.getGuardianDOB()))));
					}
					
					if(e.getDob() != "" && e.getDob() != null) {
						detailsModel.setNomdob(dateFormat.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(e.getDob()))));
					}
					
				} catch (ParseException e1) {
					detailsModel.setGurdob(null);
					detailsModel.setNomdob(null);
					e1.printStackTrace();
				}
				
				detailsModel.setNomnic(e.getNic());
				detailsModel.setNomrel(e.getRelationship());
				if(e.getShare() != null) {
					detailsModel.setNomshr(Double.parseDouble(e.getShare()));
				}
				
				detailsModel.setNomsum(0.0);
				detailsModel.setNomtyp(e.getType());
				
				inPropNomDetailsModels.add(detailsModel);
			});
		}
		
		return inPropNomDetailsModels;
	}
	

}
