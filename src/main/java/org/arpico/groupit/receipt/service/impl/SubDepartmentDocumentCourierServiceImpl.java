package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.CourierDao;
import org.arpico.groupit.receipt.dao.DepartmentCourierDao;
import org.arpico.groupit.receipt.dao.DepartmentDao;
import org.arpico.groupit.receipt.dao.DocumentTypeDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentCourierDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentCourierDto;
import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.DocumentTypeModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.arpico.groupit.receipt.service.SubDepartmentDocumentCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SubDepartmentDocumentCourierServiceImpl implements SubDepartmentDocumentCourierService{
	
	@Autowired
	private CourierDao courierDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private DepartmentCourierDao departmentCourierDao;
	
	@Autowired
	private SubDepartmentDocumentDao subDepartmentDocumentDao;
	
	@Autowired
	private SubDepartmentDocumentCourierDao subDepartmentDocumentCourierDao;
	
	@Autowired
	private SubDepartmentDao subDepartmentDao;
	
	@Autowired
	private DocumentTypeDao documentTypeDao;
	
	@Autowired
	private NumberGenerator numberGenerator;
	
	@Autowired
	private UserDao userDao;
	
	private boolean isExistDepartment=false;
	
	private DepartmentCourierModel departmentCourierModel=null;

	@Override
	public String saveSubDepDocCourier(SubDepartmentDocumentCourierDto subDepDocCouDto, Integer depId, Integer subDepId,Boolean isHOUser)
			throws Exception {
		
		SubDepartmentModel subDepartmentModel=subDepartmentDao.findOne(subDepId);
		DocumentTypeModel documentTypeModel=documentTypeDao.findOne(subDepDocCouDto.getSubDepartmentDocumentId());
		
		//get subdepartmentdocument regarding to SubDepartment and Document Type because many to many relationship
		SubDepartmentDocumentModel subDepartmentDocumentModel=subDepartmentDocumentDao.findBySubDepartmentAndDocumentType(subDepartmentModel, documentTypeModel);
		
		//set sub department doc id 
		subDepDocCouDto.setSubDepartmentDocumentId(subDepartmentDocumentModel.getSubDepDocId());
		
		List<String> branches=new ArrayList<>();
		branches.add(subDepDocCouDto.getBranchCode());
		
		//check is already exist couriers in branch
		List<CourierModel> courierModels=new ArrayList<>();
		
		if(isHOUser) {
			courierModels=courierDao.findByCourierStatusAndToBranchIn("HO", branches);
		}else {
			courierModels=courierDao.findByCourierStatusAndBranchCodeIn("BRANCH", branches);

		}
		
		String underwriterEmail=userDao.getUserEmail(subDepDocCouDto.getCreateBy());
		
		String[] numberGenCourierDoc = numberGenerator.generateNewId("", "", "COURIERDOC", "");
		
		
		if (numberGenCourierDoc[0].equals("Success")) {
			if(!courierModels.isEmpty()) {
				
				CourierModel courierModel=courierModels.get(0);
				
				List<DepartmentCourierModel> depCouriers=courierModel.getDepartmentCourier();
				
				depCouriers.forEach(dc-> {
					if(depId.equals(dc.getDepartment().getDepartmentId())) {
						departmentCourierModel=dc;
						isExistDepartment=true;
					}
				});
				
				if(isExistDepartment) {
					//add sub department document courier
					
					SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
					if(isHOUser) {
						subDepDocCouModel.setBranchCode("HO");
					}else {
						subDepDocCouModel.setBranchCode(subDepDocCouDto.getBranchCode());
					}
					subDepDocCouModel.setCreateBy(subDepDocCouDto.getCreateBy());
					subDepDocCouModel.setCreateDate(new Date());
					subDepDocCouModel.setCurrentUser(subDepDocCouDto.getCreateBy());
					subDepDocCouModel.setDepartmentCourier(departmentCourierModel);
					subDepDocCouModel.setReferenceNo(subDepDocCouDto.getReferenceNo());
					subDepDocCouModel.setRemark(subDepDocCouDto.getRemark());
					if(isHOUser) {
						subDepDocCouModel.setStatus("HO");
					}else {
						subDepDocCouModel.setStatus("BRANCH");
					}
					
					if (numberGenCourierDoc[0].equals("Success")) {
						subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
					}
					
					subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentDao.findOne(subDepDocCouDto.getSubDepartmentDocumentId()));
					subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
					subDepDocCouModel.setReferenceType(subDepDocCouDto.getReferenceType());
					
					isExistDepartment=false;
					return subDepartmentDocumentCourierDao.save(subDepDocCouModel) != null ? "200":"204";
					
					
				}else {
					//add department courier
					DepartmentCourierModel departmentCourier=new DepartmentCourierModel();
					departmentCourier.setCourier(courierModel);
					if(isHOUser) {
						departmentCourier.setCourierStatus("HO");
					}else {
						departmentCourier.setCourierStatus("BRANCH");
					}
					departmentCourier.setCreateBy(subDepDocCouDto.getCreateBy());
					departmentCourier.setCreateDate(new Date());
					departmentCourier.setDepartment(departmentDao.findOne(depId));
					
					String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
					
					if (numberGenCourierDep[0].equals("Success")) {
						departmentCourier.setToken("DEP-"+numberGenCourierDep[1]);
					}
					
					
					DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourier);
					
					if(departmentCourierModel2 != null) {
						//add sub department document courier
						
						SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
						if(isHOUser) {
							subDepDocCouModel.setBranchCode("HO");
						}else {
							subDepDocCouModel.setBranchCode(subDepDocCouDto.getBranchCode());
						}
						subDepDocCouModel.setCreateBy(subDepDocCouDto.getCreateBy());
						subDepDocCouModel.setCreateDate(new Date());
						subDepDocCouModel.setCurrentUser(subDepDocCouDto.getCreateBy());
						subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
						subDepDocCouModel.setReferenceNo(subDepDocCouDto.getReferenceNo());
						subDepDocCouModel.setRemark(subDepDocCouDto.getRemark());
						if(isHOUser) {
							subDepDocCouModel.setStatus("HO");
						}else {
							subDepDocCouModel.setStatus("BRANCH");
						}
						
						if (numberGenCourierDoc[0].equals("Success")) {
							subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
						}
						
						subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentDao.findOne(subDepDocCouDto.getSubDepartmentDocumentId()));
						subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
						subDepDocCouModel.setReferenceType(subDepDocCouDto.getReferenceType());
						
						return subDepartmentDocumentCourierDao.save(subDepDocCouModel) != null ? "200":"204";
						
					}else {
						return "204";
					}
					
					
				}
				
				
				
			}else {
				//add courier
				
				CourierModel courierModel=new CourierModel();
				
				if(isHOUser) {
					courierModel.setCourierStatus("HO");
					courierModel.setBranchCode("HO");
					courierModel.setToBranch(subDepDocCouDto.getBranchCode());
				}else {
					courierModel.setCourierStatus("BRANCH");
					courierModel.setBranchCode(subDepDocCouDto.getBranchCode());
					courierModel.setToBranch("HO");
				}
				
				courierModel.setCreateBy(subDepDocCouDto.getCreateBy());
				courierModel.setCreateDate(new Date());
				courierModel.setRemark("");
				
				String[] numberGenCourier = numberGenerator.generateNewId("", "", "COURIER", "");
				
				if (numberGenCourier[0].equals("Success")) {
					if(isHOUser) {
						courierModel.setToken("COU-HO-"+numberGenCourier[1]);
					}else {
						courierModel.setToken("COU-"+subDepDocCouDto.getBranchCode()+"-"+numberGenCourier[1]);
					}
				}else {
					return "204";
				}
				
				
				
				CourierModel courierModel2=courierDao.save(courierModel);
				
				if(courierModel2 != null) {

					//add department courier
					
					DepartmentCourierModel departmentCourierModel=new DepartmentCourierModel();
					departmentCourierModel.setCourier(courierModel2);
					if(isHOUser) {
						departmentCourierModel.setCourierStatus("HO");
					}else {
						departmentCourierModel.setCourierStatus("BRANCH");
					}
					
					departmentCourierModel.setCreateBy(subDepDocCouDto.getCreateBy());
					departmentCourierModel.setCreateDate(new Date());
					departmentCourierModel.setDepartment(departmentDao.findOne(depId));
					
					String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
					
					if (numberGenCourierDep[0].equals("Success")) {
						departmentCourierModel.setToken("DEP-"+numberGenCourierDep[1]);
					}
					
					DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourierModel);
					
					if(departmentCourierModel2 != null) {
						//add sub department document courier
						
						SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
						if(isHOUser) {
							subDepDocCouModel.setBranchCode("HO");
						}else {
							subDepDocCouModel.setBranchCode(subDepDocCouDto.getBranchCode());
						}
						subDepDocCouModel.setCreateBy(subDepDocCouDto.getCreateBy());
						subDepDocCouModel.setCreateDate(new Date());
						subDepDocCouModel.setCurrentUser(subDepDocCouDto.getCreateBy());
						subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
						subDepDocCouModel.setReferenceNo(subDepDocCouDto.getReferenceNo());
						subDepDocCouModel.setRemark(subDepDocCouDto.getRemark());
						if(isHOUser) {
							subDepDocCouModel.setStatus("HO");
						}else {
							subDepDocCouModel.setStatus("BRANCH");
						}
						
						if (numberGenCourierDoc[0].equals("Success")) {
							subDepDocCouModel.setSubDepDocCouToken("DOC-"+numberGenCourierDoc[1]);
						}
						
						subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentDao.findOne(subDepDocCouDto.getSubDepartmentDocumentId()));
						subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
						subDepDocCouModel.setReferenceType(subDepDocCouDto.getReferenceType());
						
						return subDepartmentDocumentCourierDao.save(subDepDocCouModel) != null ? "200":"204";
						
					}else {
						return "204";
					}
				}else {
					return "204";
				}
			}
		}
		
		return "204";
		
	}

	@Override
	public List<SubDepartmentDocumentCourierDto> getSubDepDocCourierByDepCou(Integer depCouId) throws Exception {
		DepartmentCourierModel departmentCourierModel=departmentCourierDao.findOne(depCouId);
		List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourierModels=subDepartmentDocumentCourierDao.findByDepartmentCourier(departmentCourierModel);
		
		List<SubDepartmentDocumentCourierDto> courierDtos=new ArrayList<>();
		subDepartmentDocumentCourierModels.forEach(dc ->{
			SubDepartmentDocumentCourierDto dto=new SubDepartmentDocumentCourierDto();
			
			dto.setBranchCode(dc.getBranchCode());
			dto.setCreateBy(dc.getCreateBy());
			dto.setCreateDate(dc.getCreateDate());
			dto.setCurrentUser(dc.getCurrentUser());
			dto.setDepartmentCourierId(dc.getDepartmentCourier().getCourierDepartmentId());
			dto.setModifyBy(dc.getModifyBy());
			dto.setModifyDate(dc.getModifyDate());
			dto.setReceivedBy(dc.getReceivedBy());
			dto.setReceivedDate(dc.getReceivedDate());
			dto.setReferenceNo(dc.getReferenceNo());
			dto.setRemark(dc.getRemark());
			dto.setStatus(dc.getStatus());
			dto.setSubDepartmentDocumentCourierId(dc.getSubDepartmentDocumentCourierId());
			dto.setSubDepDocCouToken(dc.getSubDepDocCouToken());
			dto.setSubDepartmentDocumentId(dc.getSubDepartmentDocument().getSubDepDocId());
			dto.setUnderwriterEmail(dc.getUnderwriterEmail());
			
			courierDtos.add(dto);
		});
		
		return courierDtos;
	}

	

}
