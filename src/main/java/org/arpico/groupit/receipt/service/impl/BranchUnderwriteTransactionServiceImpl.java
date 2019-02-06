package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dao.BenefictDetailsDao;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.CourierDao;
import org.arpico.groupit.receipt.dao.DepartmentCourierDao;
import org.arpico.groupit.receipt.dao.DocumentTypeDao;
import org.arpico.groupit.receipt.dao.InOccuLoadDatDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictCustomDao;
import org.arpico.groupit.receipt.dao.InPropAddBenefictDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InPropFamDetailsDao;
import org.arpico.groupit.receipt.dao.InPropLoadingCustomDao;
import org.arpico.groupit.receipt.dao.InPropLoadingDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqCustomDao;
import org.arpico.groupit.receipt.dao.InPropMedicalReqDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsCustomDao;
import org.arpico.groupit.receipt.dao.InPropNomDetailsDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsCustomDao;
import org.arpico.groupit.receipt.dao.InPropPrePolsDao;
import org.arpico.groupit.receipt.dao.InPropShedulesCustomDao;
import org.arpico.groupit.receipt.dao.InPropShedulesDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsCustomDao;
import org.arpico.groupit.receipt.dao.InPropSurrenderValsDao;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.InProposalDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentCourierDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.DocumentTypeModel;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.BranchUnderwriteTransactionService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BranchUnderwriteTransactionServiceImpl implements BranchUnderwriteTransactionService {

	@Autowired
	private NumberGenerator numberGenerator;

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
	private InPropFamDetailsCustomDao famDetailsCustomDao;

	@Autowired
	private InPropLoadingDao propLoadingDao;

	@Autowired
	private InPropLoadingCustomDao propLoadingCustomDao;

	@Autowired
	private InPropMedicalReqDao propMedicalReqDao;

	@Autowired
	private InPropNomDetailsDao propNomDetailsDao;

	@Autowired
	private InPropNomDetailsCustomDao propNomDetailsCustomDao;

	@Autowired
	private InPropPrePolsDao propPrePolsDao;

	@Autowired
	private InPropPrePolsCustomDao propPrePolsCustomDao;

	@Autowired
	private InPropShedulesDao propScheduleDao;

	@Autowired
	private InPropShedulesCustomDao propScheduleCustomDao;

	@Autowired
	private InPropSurrenderValsDao surrenderValDao;

	@Autowired
	private InPropSurrenderValsCustomDao surrenderValCustomDao;

	@Autowired
	private InProposalDao inProposalDao;

	@Autowired
	private InPropMedicalReqCustomDao propMedicalReqCustomDao;

	@Autowired
	private SubDepartmentDao subDepartmentDao;

	@Autowired
	private DocumentTypeDao documentTypeDao;

	@Autowired
	private SubDepartmentDocumentDao subDepartmentDocumentDao;

	@Autowired
	private CourierDao courierDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SubDepartmentDocumentCourierDao subDepartmentDocumentCourierDao;

	@Autowired
	private DepartmentCourierDao departmentCourierDao;

	@Autowired
	private JwtDecoder decoder;

	@Autowired
	private InPropAddBenefictCustomDao inPropAddBenefictCustomDao;
	
	private boolean isExistDepartment = false;
	private DepartmentCourierModel departmentCourierModel=null;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void saveProposalNotApprove(InProposalsModel newInProposalsModel,
			List<InPropLoadingModel> inPropLoadingModels, List<InPropAddBenefitModel> addBenefitModels,
			List<InPropFamDetailsModel> propFamDetailsModels, List<InPropSchedulesModel> inPropScheduleList,
			List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropSurrenderValsModel> inPropSurrenderValsModels, List<InPropNomDetailsModel> nomDetailsModels,
			List<InPropPrePolsModel> prePolsModels) throws Exception {

		inProposalDao.save(newInProposalsModel);

		/* remove exist prop loading */
		try {
			propLoadingCustomDao.removePropLoadingByPprNumAndSeq(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		/* Save In Prop Loadings */
		if (inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {

			propLoadingDao.save(inPropLoadingModels);
		}

		/* Remove In Add Loadings */

		try {
			inPropAddBenefictCustomDao.removeBenefByPprSeq(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save In Add Loadings */
		if (addBenefitModels != null && !addBenefitModels.isEmpty()) {

			//// System.out.println("addBenefitModels save" );

			addBenefictDao.save(addBenefitModels);
		}

		/* Remove Family Details */
		try {
			famDetailsCustomDao.removeFamilyByPprNoAndSeqNo(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save Family Details */
		if (propFamDetailsModels != null && !propFamDetailsModels.isEmpty()) {

			//// System.out.println("propFamDetailsModels save" );

			famDetailsDao.save(propFamDetailsModels);
		}

		/* Remove Shedule */

		try {
			propScheduleCustomDao.removeScheduleByPprNoAndSeqNo(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save Shedule */
		if (inPropScheduleList != null && !inPropScheduleList.isEmpty()) {

			//// System.out.println("inPropScheduleList save" );

			propScheduleDao.save(inPropScheduleList);
		}

		/* Remove Medical Requirements */

		try {
			propMedicalReqCustomDao.removeMedicalReqByPprNoAndSeq(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save Medical Requirements */
		if (inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {

			//// System.out.println("inPropMedicalReqModels save" );

			propMedicalReqDao.save(inPropMedicalReqModels);
		}

		/* Remove Surrender Values */
		try {
			surrenderValCustomDao.removeSurrenderValByInpprNoAndSeq(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save Surrender Values */
		if (inPropSurrenderValsModels != null && !inPropSurrenderValsModels.isEmpty()) {

			//// System.out.println("inPropSurrenderValsModels save" );

			surrenderValDao.save(inPropSurrenderValsModels);
		}

		/* Remove Nominee Details */
		try {

			propNomDetailsCustomDao.removeNomByPprNoAndPprSeq(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save Nominee Details */
		if (nomDetailsModels != null && !nomDetailsModels.isEmpty()) {
			propNomDetailsDao.save(nomDetailsModels);

		}

		/* Remove Previous Policies */
		try {
			propPrePolsCustomDao.removePrePolByPprNoAndPprSeq(
					Integer.valueOf(newInProposalsModel.getInProposalsModelPK().getPprnum()),
					newInProposalsModel.getInProposalsModelPK().getPrpseq());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Save Previous Policies */
		if (prePolsModels != null && !prePolsModels.isEmpty()) {

			//// System.out.println("prePolsModels save" );

			propPrePolsDao.save(prePolsModels);
		}

	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void saveProposal(InProposalsModel inProposalsModel, InProposalsModel newInProposalsModel,
			List<InPropLoadingModel> inPropLoadingModels, List<InPropAddBenefitModel> addBenefitModels,
			List<InPropFamDetailsModel> propFamDetailsModels, List<InPropSchedulesModel> inPropScheduleList,
			List<InPropMedicalReqModel> inPropMedicalReqModels,
			List<InPropSurrenderValsModel> inPropSurrenderValsModels, List<InPropNomDetailsModel> nomDetailsModels,
			List<InPropPrePolsModel> prePolsModels) throws Exception {

		inProposalDao.save(inProposalsModel);

		// System.out.println("newInProposalsModel save" );
		/* Save new Line of InProposal */
		inProposalDao.save(newInProposalsModel);

		/* Save In Prop Loadings */
		if (inPropLoadingModels != null && !inPropLoadingModels.isEmpty()) {

			//// System.out.println("inPropLoadingModels save" );

			propLoadingDao.save(inPropLoadingModels);
		}

		/* Save In Add Loadings */
		if (addBenefitModels != null && !addBenefitModels.isEmpty()) {

			//// System.out.println("addBenefitModels save" );

			addBenefictDao.save(addBenefitModels);
		}

		/* Save Family Details */
		if (propFamDetailsModels != null && !propFamDetailsModels.isEmpty()) {

			//// System.out.println("propFamDetailsModels save" );

			famDetailsDao.save(propFamDetailsModels);
		}

		/* Save Shedule */
		if (inPropScheduleList != null && !inPropScheduleList.isEmpty()) {

			//// System.out.println("inPropScheduleList save" );

			propScheduleDao.save(inPropScheduleList);
		}

		/* Save Medical Requirements */
		if (inPropMedicalReqModels != null && !inPropMedicalReqModels.isEmpty()) {

			//// System.out.println("inPropMedicalReqModels save" );

			propMedicalReqDao.save(inPropMedicalReqModels);
		}

		/* Save Surrender Values */
		if (inPropSurrenderValsModels != null && !inPropSurrenderValsModels.isEmpty()) {

			//// System.out.println("inPropSurrenderValsModels save" );

			surrenderValDao.save(inPropSurrenderValsModels);
		}
		/* Save Nominee Details */
		if (nomDetailsModels != null && !nomDetailsModels.isEmpty()) {

			//// System.out.println("nomDetailsModels save" );

			propNomDetailsDao.save(nomDetailsModels);
		}

		/* Save Previous Policies */
		if (prePolsModels != null && !prePolsModels.isEmpty()) {

			//// System.out.println("prePolsModels save" );

			propPrePolsDao.save(prePolsModels);
		}

	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public String saveCourierDocument(Integer pprNo, Integer seqNo, String branchCode, String userCode,
			boolean isExist, DepartmentCourierModel dcm) throws Exception {
		
		this.isExistDepartment = isExist;
		this.departmentCourierModel = dcm;
		
		List<InPropMedicalReqModel> medicalReqModels=propMedicalReqCustomDao.getMedicalReqByPprNoAndSeq(pprNo, seqNo);
		List<SubDepartmentModel> subDepartmentModels=subDepartmentDao.findBySudDepNameContaining("Underwriting");
		String underwriterEmail=userDao.getUserEmail(userCode);
		////System.out.println("saveCourierDocument ////  saveCourierDocument");
		//System.out.println(medicalReqModels.size()  + "medicalReqModels");
		//System.out.println(subDepartmentModels.size()  + "subDepartmentModels");
		
		isExistDepartment=false;
		
		if(!subDepartmentModels.isEmpty()) {
			////System.out.println(subDepartmentModels.isEmpty()  + "subDepartmentModels.isEmpty()");
			
			List<DocumentTypeModel> documentTypeModel1=documentTypeDao.findByDocName("PENDING CLEARANCE");
				
			List<DocumentTypeModel> documentTypeModelProp=documentTypeDao.findByDocNameAndParent("PROPOSAL FORM",documentTypeModel1.get(0).getDocTypeId());
			//System.out.println(documentTypeModelProp.size() + " documentTypeModelProp.size()");
			SubDepartmentDocumentModel subDepartmentDocumentModelProp=subDepartmentDocumentDao.findBySubDepartmentAndDocumentType(subDepartmentModels.get(0), documentTypeModelProp.get(0));
				
			List<String> branches=new ArrayList<>();
			branches.add(branchCode);
			//System.out.println(branchCode + " branchcode");
			//check is already exist couriers in branch
			List<CourierModel> courierModelsProp=courierDao.findByCourierStatusAndBranchCodeIn("BRANCH", branches);
				
			if(!courierModelsProp.isEmpty()) {
				CourierModel courierModel=courierModelsProp.get(0);
					
				List<DepartmentCourierModel> depCouriers=courierModel.getDepartmentCourier();
					
				//System.out.println(depCouriers.size() + " depCouriers.size() **********");

				depCouriers.forEach(dc-> {
					//System.out.println(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId()));
					if(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId())) {
						departmentCourierModel=dc;
						isExistDepartment=true;
					}
				});
					
				if(isExistDepartment) {
					//System.out.println("isExistDepartment");
					//add sub department document courier
						
					SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
					subDepDocCouModel.setBranchCode(branchCode);
					subDepDocCouModel.setCreateBy(userCode);
					subDepDocCouModel.setCreateDate(new Date());
					subDepDocCouModel.setCurrentUser(userCode);
					subDepDocCouModel.setDepartmentCourier(departmentCourierModel);
					subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
					subDepDocCouModel.setRemark("PROPOSAL FORM");
					subDepDocCouModel.setStatus("BRANCH");
						
					String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
						
					if (numberGenCourierDoc[0].equals("Success")) {
						subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
					}
						
					subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModelProp);
					subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
					subDepDocCouModel.setReferenceType("Proposal No");
					//System.out.println("save subDepartmentDocumentCourier");
					subDepartmentDocumentCourierDao.save(subDepDocCouModel);
						
						
				}else {
					//System.out.println("isExistDepartment not");
					//add department courier
					DepartmentCourierModel departmentCourier=new DepartmentCourierModel();
					departmentCourier.setCourier(courierModel);
					departmentCourier.setCourierStatus("BRANCH");
					departmentCourier.setCreateBy(userCode);
					departmentCourier.setCreateDate(new Date());
					departmentCourier.setDepartment(subDepartmentModels.get(0).getDepId());
					
					String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
					
					if (numberGenCourierDep[0].equals("Success")) {
						departmentCourier.setToken("DEP-"+numberGenCourierDep[1]);
					}
					
					
					DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourier);
					departmentCourierModel=departmentCourierModel2;
					
					isExistDepartment=true;
					if(departmentCourierModel2 != null) {
						//add sub department document courier
						
						SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
						subDepDocCouModel.setBranchCode(branchCode);
						subDepDocCouModel.setCreateBy(userCode);
						subDepDocCouModel.setCreateDate(new Date());
						subDepDocCouModel.setCurrentUser(userCode);
						subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
						subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
						subDepDocCouModel.setRemark("PROPOSAL FORM");
						subDepDocCouModel.setStatus("BRANCH");
						
						String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
						
						if (numberGenCourierDoc[0].equals("Success")) {
							subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
						}
						
						subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModelProp);
						subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
						subDepDocCouModel.setReferenceType("Proposal No");
						//System.out.println("save subDepartmentDocumentCourier");
						subDepartmentDocumentCourierDao.save(subDepDocCouModel);
						
					}
					
					
				}
			}else{
				//add courier
				//System.out.println("save Courier");
				CourierModel courierModel=new CourierModel();
				courierModel.setBranchCode(branchCode);
				courierModel.setCourierStatus("BRANCH");
				courierModel.setCreateBy(userCode);
				courierModel.setCreateDate(new Date());
				courierModel.setRemark("");
				courierModel.setToBranch("HO");
				String[] numberGenCourier = numberGenerator.generateNewId("", "", "COURIER", "");
				
				if (numberGenCourier[0].equals("Success")) {
					courierModel.setToken("COU-"+branchCode+"-"+numberGenCourier[1]);
				}
				
				CourierModel courierModel2=courierDao.save(courierModel);
				
				if(courierModel2 != null) {

					//add department courier
					
					List<DepartmentCourierModel> depCouriers=courierModel2.getDepartmentCourier();
					depCouriers.forEach(dc-> {
						//System.out.println(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId()));
						if(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId())) {
							//System.out.println("Exist department courier ***********************");
							departmentCourierModel=dc;
							isExistDepartment=true;
						}
					});
					
					if(isExistDepartment) {
						//System.out.println("Exist department ***********************");
						//add sub department document courier
						
						SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
						subDepDocCouModel.setBranchCode(branchCode);
						subDepDocCouModel.setCreateBy(userCode);
						subDepDocCouModel.setCreateDate(new Date());
						subDepDocCouModel.setCurrentUser(userCode);
						subDepDocCouModel.setDepartmentCourier(departmentCourierModel);
						subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
						subDepDocCouModel.setRemark("PROPOSAL FORM");
						subDepDocCouModel.setStatus("BRANCH");
						
						String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
						
						if (numberGenCourierDoc[0].equals("Success")) {
							subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
						}
						
						subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModelProp);
						subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
						subDepDocCouModel.setReferenceType("Proposal No");
						//System.out.println("save subDepartmentDocumentCourier");
						subDepartmentDocumentCourierDao.save(subDepDocCouModel);
					
					}else {
						//System.out.println("not Exist department ***********************");
						
						DepartmentCourierModel depCourierModel=new DepartmentCourierModel();
						depCourierModel.setCourier(courierModel2);
						depCourierModel.setCourierStatus("BRANCH");
						depCourierModel.setCreateBy(userCode);
						depCourierModel.setCreateDate(new Date());
						depCourierModel.setDepartment(subDepartmentModels.get(0).getDepId());
						
						String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
						
						if (numberGenCourierDep[0].equals("Success")) {
							depCourierModel.setToken("DEP-"+numberGenCourierDep[1]);
						}
						
						
						//System.out.println("saveDepartment Courier");
						DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(depCourierModel);
						departmentCourierModel=departmentCourierModel2;
						
						isExistDepartment=true;
						if(departmentCourierModel2 != null) {
							//add sub department document courier
							
							SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
							subDepDocCouModel.setBranchCode(branchCode);
							subDepDocCouModel.setCreateBy(userCode);
							subDepDocCouModel.setCreateDate(new Date());
							subDepDocCouModel.setCurrentUser(userCode);
							subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
							subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
							subDepDocCouModel.setRemark("PROPOSAL FORM");
							subDepDocCouModel.setStatus("BRANCH");
							
							String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
							
							if (numberGenCourierDoc[0].equals("Success")) {
								subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
							}
							
							subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModelProp);
							subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
							subDepDocCouModel.setReferenceType("Proposal No");
							//System.out.println("save subDepartmentDocumentCourier");
							subDepartmentDocumentCourierDao.save(subDepDocCouModel);
							
						}
					}

				}
			
			}
				
			//}
			
			medicalReqModels.forEach(med -> {
				//System.out.println(med  + "med");
				try {
					////System.out.println(med.getInPropMedicalReqModelPK().getMedcod() + " med.getInPropMedicalReqModelPK().getMedcod()");
					////System.out.println(subDepartmentModels.get(0).getSubDepId() + " subDepartmentModels.get(0).getSubDepId()");
					//List<DocumentTypeModel> documentTypeModel1=documentTypeDao.findByDocName("PENDING CLEARANCE");
					////System.out.println(documentTypeModel1.size() + " documentTypeModel1");
					
					if(!documentTypeModel1.isEmpty()) {
						////System.out.println(documentTypeModel1 + " documentTypeModel1");
					List<DocumentTypeModel> documentTypeModel=documentTypeDao.findByDocTypeCodeAndParent(med.getInPropMedicalReqModelPK().getMedcod(),documentTypeModel1.get(0).getDocTypeId());
					////System.out.println(documentTypeModel.size() + " documentTypeModel");
					if(!documentTypeModel.isEmpty()) {
						////System.out.println("documentTypeModel not empty");
						//get sub department document regarding to SubDepartment and Document Type because many to many relationship
						SubDepartmentDocumentModel subDepartmentDocumentModel=subDepartmentDocumentDao.findBySubDepartmentAndDocumentType(subDepartmentModels.get(0), documentTypeModel.get(0));
						
						//List<String> branches=new ArrayList<>();
						//branches.add(branchCode);
						//System.out.println(branchCode + " branchcode");
						//check is already exist couriers in branch
						List<CourierModel> courierModels=courierDao.findByCourierStatusAndBranchCodeIn("BRANCH", branches);
						
						if(!courierModels.isEmpty()) {
							CourierModel courierModel=courierModels.get(0);
							
							List<DepartmentCourierModel> depCouriers=courierModel.getDepartmentCourier();
							
							//System.out.println(depCouriers.size() + " depCouriers.size() **********");

							depCouriers.forEach(dc-> {
								//System.out.println(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId()));
								if(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId())) {
									departmentCourierModel=dc;
									isExistDepartment=true;
								}
							});
							
							if(isExistDepartment) {
								//System.out.println("isExistDepartment");
								//add sub department document courier
								
								SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
								subDepDocCouModel.setBranchCode(branchCode);
								subDepDocCouModel.setCreateBy(userCode);
								subDepDocCouModel.setCreateDate(new Date());
								subDepDocCouModel.setCurrentUser(userCode);
								subDepDocCouModel.setDepartmentCourier(departmentCourierModel);
								subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
								subDepDocCouModel.setRemark(med.getInPropMedicalReqModelPK().getMedcod() +" / " + med.getInPropMedicalReqModelPK().getInstyp());
								subDepDocCouModel.setStatus("BRANCH");
								
								String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
								
								if (numberGenCourierDoc[0].equals("Success")) {
									subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
								}
								
								subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModel);
								subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
								subDepDocCouModel.setReferenceType("Proposal No");
								//System.out.println("save subDepartmentDocumentCourier");
								subDepartmentDocumentCourierDao.save(subDepDocCouModel);
								
								
							}else {
								//System.out.println("isExistDepartment not");
								//add department courier
								DepartmentCourierModel departmentCourier=new DepartmentCourierModel();
								departmentCourier.setCourier(courierModel);
								departmentCourier.setCourierStatus("BRANCH");
								departmentCourier.setCreateBy(userCode);
								departmentCourier.setCreateDate(new Date());
								departmentCourier.setDepartment(subDepartmentModels.get(0).getDepId());
								
								String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
								
								if (numberGenCourierDep[0].equals("Success")) {
									departmentCourier.setToken("DEP-"+numberGenCourierDep[1]);
								}
								
								
								DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourier);
								departmentCourierModel=departmentCourierModel2;
								
								isExistDepartment=true;
								if(departmentCourierModel2 != null) {
									//add sub department document courier
									
									SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
									subDepDocCouModel.setBranchCode(branchCode);
									subDepDocCouModel.setCreateBy(userCode);
									subDepDocCouModel.setCreateDate(new Date());
									subDepDocCouModel.setCurrentUser(userCode);
									subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
									subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
									subDepDocCouModel.setRemark(med.getInPropMedicalReqModelPK().getMedcod() +" / " + med.getInPropMedicalReqModelPK().getInstyp());
									subDepDocCouModel.setStatus("BRANCH");
									
									String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
									
									if (numberGenCourierDoc[0].equals("Success")) {
										subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
									}
									
									subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModel);
									subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
									subDepDocCouModel.setReferenceType("Proposal No");
									//System.out.println("save subDepartmentDocumentCourier");
									subDepartmentDocumentCourierDao.save(subDepDocCouModel);
									
								}
								
								
							}
						}else{
							//add courier
							//System.out.println("save Courier");
							CourierModel courierModel=new CourierModel();
							courierModel.setBranchCode(branchCode);
							courierModel.setCourierStatus("BRANCH");
							courierModel.setCreateBy(userCode);
							courierModel.setCreateDate(new Date());
							courierModel.setRemark("");
							courierModel.setToBranch("HO");
							String[] numberGenCourier = numberGenerator.generateNewId("", "", "COURIER", "");
							
							if (numberGenCourier[0].equals("Success")) {
								courierModel.setToken("COU-"+branchCode+"-"+numberGenCourier[1]);
							}
							
							CourierModel courierModel2=courierDao.save(courierModel);
							
							if(courierModel2 != null) {
	
								//add department courier
								
								List<DepartmentCourierModel> depCouriers=courierModel2.getDepartmentCourier();
								depCouriers.forEach(dc-> {
									//System.out.println(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId()));
									if(subDepartmentModels.get(0).getDepId().getDepartmentId().equals(dc.getDepartment().getDepartmentId())) {
										//System.out.println("Exist department courier ***********************");
										departmentCourierModel=dc;
										isExistDepartment=true;
									}
								});
								
								if(isExistDepartment) {
									//System.out.println("Exist department ***********************");
									//add sub department document courier
									
									SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
									subDepDocCouModel.setBranchCode(branchCode);
									subDepDocCouModel.setCreateBy(userCode);
									subDepDocCouModel.setCreateDate(new Date());
									subDepDocCouModel.setCurrentUser(userCode);
									subDepDocCouModel.setDepartmentCourier(departmentCourierModel);
									subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
									subDepDocCouModel.setRemark(med.getInPropMedicalReqModelPK().getMedcod() +" / " + med.getInPropMedicalReqModelPK().getInstyp());
									subDepDocCouModel.setStatus("BRANCH");
									
									String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
									
									if (numberGenCourierDoc[0].equals("Success")) {
										subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
									}
									
									subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModel);
									subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
									subDepDocCouModel.setReferenceType("Proposal No");
									//System.out.println("save subDepartmentDocumentCourier");
									subDepartmentDocumentCourierDao.save(subDepDocCouModel);
								
								}else {
									//System.out.println("not Exist department ***********************");
									
									DepartmentCourierModel depCourierModel=new DepartmentCourierModel();
									depCourierModel.setCourier(courierModel2);
									depCourierModel.setCourierStatus("BRANCH");
									depCourierModel.setCreateBy(userCode);
									depCourierModel.setCreateDate(new Date());
									depCourierModel.setDepartment(subDepartmentModels.get(0).getDepId());
									
									String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
									
									if (numberGenCourierDep[0].equals("Success")) {
										depCourierModel.setToken("DEP-"+numberGenCourierDep[1]);
									}
									
									
									//System.out.println("saveDepartment Courier");
									DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(depCourierModel);
									departmentCourierModel=departmentCourierModel2;
									
									isExistDepartment=true;
									if(departmentCourierModel2 != null) {
										//add sub department document courier
										
										SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
										subDepDocCouModel.setBranchCode(branchCode);
										subDepDocCouModel.setCreateBy(userCode);
										subDepDocCouModel.setCreateDate(new Date());
										subDepDocCouModel.setCurrentUser(userCode);
										subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
										subDepDocCouModel.setReferenceNo(Integer.toString(pprNo));
										String[] noteSize=med.getAddnot().split("-");
										
										if(noteSize.length > 1) {
											subDepDocCouModel.setRemark(med.getInPropMedicalReqModelPK().getMedcod() +" / " + med.getInPropMedicalReqModelPK().getInstyp() + "(" + noteSize[1]+")");
										}else {
											subDepDocCouModel.setRemark(med.getInPropMedicalReqModelPK().getMedcod() +" / " + med.getInPropMedicalReqModelPK().getInstyp());
										}
										
										subDepDocCouModel.setStatus("BRANCH");
										
										String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
										
										if (numberGenCourierDoc[0].equals("Success")) {
											subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
										}
										
										subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentModel);
										subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
										subDepDocCouModel.setReferenceType("Proposal No");
										//System.out.println("save subDepartmentDocumentCourier");
										subDepartmentDocumentCourierDao.save(subDepDocCouModel);
										
									}
								}

							}
						
						}
					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			
			
			////System.out.println("saveCourierDocument --- 200");
			return "200";
			
		}
		
		////System.out.println("saveCourierDocument --- 204");
		return "204";
	}

	

}
