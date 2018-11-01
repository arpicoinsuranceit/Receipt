package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
	private UserDao userDao;
	
	private boolean isExistDepartment=false;
	
	private DepartmentCourierModel departmentCourierModel=null;

	@Override
	public String saveSubDepDocCourier(SubDepartmentDocumentCourierDto subDepDocCouDto, Integer depId, Integer subDepId)
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
		List<CourierModel> courierModels=courierDao.findByCourierStatusAndBranchCodeIn("BRANCH", branches);
		
		String underwriterEmail=userDao.getUserEmail(subDepDocCouDto.getCreateBy());
		
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
				subDepDocCouModel.setBranchCode(subDepDocCouDto.getBranchCode());
				subDepDocCouModel.setCreateBy(subDepDocCouDto.getCreateBy());
				subDepDocCouModel.setCreateDate(new Date());
				subDepDocCouModel.setCurrentUser(subDepDocCouDto.getCreateBy());
				subDepDocCouModel.setDepartmentCourier(departmentCourierModel);
				subDepDocCouModel.setReferenceNo(subDepDocCouDto.getReferenceNo());
				subDepDocCouModel.setRemark(subDepDocCouDto.getRemark());
				subDepDocCouModel.setStatus("BRANCH");
				subDepDocCouModel.setSubDepDocCouToken(UUID.randomUUID().toString());
				subDepDocCouModel.setSubDepartmentDocument(subDepartmentDocumentDao.findOne(subDepDocCouDto.getSubDepartmentDocumentId()));
				subDepDocCouModel.setUnderwriterEmail(underwriterEmail);
				subDepDocCouModel.setReferenceType(subDepDocCouDto.getReferenceType());
				
				isExistDepartment=false;
				return subDepartmentDocumentCourierDao.save(subDepDocCouModel) != null ? "200":"204";
				
				
			}else {
				//add department courier
				DepartmentCourierModel departmentCourier=new DepartmentCourierModel();
				departmentCourier.setCourier(courierModel);
				departmentCourier.setCourierStatus("BRANCH");
				departmentCourier.setCreateBy(subDepDocCouDto.getCreateBy());
				departmentCourier.setCreateDate(new Date());
				departmentCourier.setDepartment(departmentDao.findOne(depId));
				departmentCourier.setToken(UUID.randomUUID().toString());
				
				DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourier);
				
				if(departmentCourierModel2 != null) {
					//add sub department document courier
					
					SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
					subDepDocCouModel.setBranchCode(subDepDocCouDto.getBranchCode());
					subDepDocCouModel.setCreateBy(subDepDocCouDto.getCreateBy());
					subDepDocCouModel.setCreateDate(new Date());
					subDepDocCouModel.setCurrentUser(subDepDocCouDto.getCreateBy());
					subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
					subDepDocCouModel.setReferenceNo(subDepDocCouDto.getReferenceNo());
					subDepDocCouModel.setRemark(subDepDocCouDto.getRemark());
					subDepDocCouModel.setStatus("BRANCH");
					subDepDocCouModel.setSubDepDocCouToken(UUID.randomUUID().toString());
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
			courierModel.setBranchCode(subDepDocCouDto.getBranchCode());
			courierModel.setCourierStatus("BRANCH");
			courierModel.setCreateBy(subDepDocCouDto.getCreateBy());
			courierModel.setCreateDate(new Date());
			courierModel.setRemark("");
			courierModel.setToken(UUID.randomUUID().toString());
			
			CourierModel courierModel2=courierDao.save(courierModel);
			
			if(courierModel2 != null) {

				//add department courier
				
				DepartmentCourierModel departmentCourierModel=new DepartmentCourierModel();
				departmentCourierModel.setCourier(courierModel2);
				departmentCourierModel.setCourierStatus("BRANCH");
				departmentCourierModel.setCreateBy(subDepDocCouDto.getCreateBy());
				departmentCourierModel.setCreateDate(new Date());
				departmentCourierModel.setDepartment(departmentDao.findOne(depId));
				departmentCourierModel.setToken(UUID.randomUUID().toString());
				
				DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourierModel);
				
				if(departmentCourierModel2 != null) {
					//add sub department document courier
					
					SubDepartmentDocumentCourierModel subDepDocCouModel=new SubDepartmentDocumentCourierModel();
					subDepDocCouModel.setBranchCode(subDepDocCouDto.getBranchCode());
					subDepDocCouModel.setCreateBy(subDepDocCouDto.getCreateBy());
					subDepDocCouModel.setCreateDate(new Date());
					subDepDocCouModel.setCurrentUser(subDepDocCouDto.getCreateBy());
					subDepDocCouModel.setDepartmentCourier(departmentCourierModel2);
					subDepDocCouModel.setReferenceNo(subDepDocCouDto.getReferenceNo());
					subDepDocCouModel.setRemark(subDepDocCouDto.getRemark());
					subDepDocCouModel.setStatus("BRANCH");
					subDepDocCouModel.setSubDepDocCouToken(UUID.randomUUID().toString());
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
