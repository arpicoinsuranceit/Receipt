package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.CourierDao;
import org.arpico.groupit.receipt.dao.DepartmentCourierDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentDao;
import org.arpico.groupit.receipt.dto.CourierDetailsHelperDto;
import org.arpico.groupit.receipt.dto.DepartmentHelperDto;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentCourierDto;
import org.arpico.groupit.receipt.dto.SubDepartmentHelperDto;
import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentModel;
import org.arpico.groupit.receipt.service.DepartmentCourierService;
import org.arpico.groupit.receipt.service.SubDepartmentDocumentCourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentCourierHelperDto;

@Service
@Transactional
public class DepartmentCourierServiceImpl implements DepartmentCourierService{
	
	@Autowired
	private DepartmentCourierDao departmentCourierDao;
	
	@Autowired
	private CourierDao courierDao;
	
	@Autowired
	private SubDepartmentDocumentDao subDepDocDao;
	
	@Autowired
	private SubDepartmentDocumentCourierService subDepDocCouService;

	@Override
	public CourierDetailsHelperDto getCourierDetailsByCourier(Integer couId) throws Exception {
		
		CourierModel courierModel=courierDao.findOne(couId);
		
		List<DepartmentCourierModel> departmentCourierModels=departmentCourierDao.findByCourier(courierModel);
		
		//HashMap<String, HashMap<String,List<SubDepartmentDocumentCourierDto>>> couDetailsMap=new HashMap<>();
		
		ArrayList<DepartmentHelperDto> departmentHelperDtos=new ArrayList<>();
		
		CourierDetailsHelperDto helperDto=new CourierDetailsHelperDto();
		helperDto.setCourierId(couId);
		helperDto.setCourierRef(courierModel.getToken());
		helperDto.setCourierStatus(courierModel.getCourierStatus());
		
		departmentCourierModels.forEach(dc -> {
			try {
				List<SubDepartmentDocumentCourierDto> subDepDocCouDto=subDepDocCouService.getSubDepDocCourierByDepCou(dc.getCourierDepartmentId());
				HashMap<String, ArrayList<SubDepartmentDocumentCourierHelperDto>> subDepDocMap=new HashMap<>();
				ArrayList<SubDepartmentHelperDto> subDepartmentHelperDtos=new ArrayList<>(); 
				
				subDepDocCouDto.forEach(sddcd -> {
					SubDepartmentDocumentModel subDepartmentDocumentModel=subDepDocDao.findOne(sddcd.getSubDepartmentDocumentId());
					
					
					if(subDepDocMap.containsKey(subDepartmentDocumentModel.getSubDepartment().getSudDepName())) {
						
						ArrayList<SubDepartmentDocumentCourierHelperDto> courierDtos=subDepDocMap.get(subDepartmentDocumentModel.getSubDepartment().getSudDepName());
						
						SubDepartmentDocumentCourierHelperDto dto=new SubDepartmentDocumentCourierHelperDto();
						dto.setBranchCode(sddcd.getBranchCode());
						dto.setCreateBy(sddcd.getCreateBy());
						dto.setCreateDate(sddcd.getCreateDate());
						dto.setDocumentType(subDepartmentDocumentModel.getDocumentType().getDocName());
						dto.setReferenceNo(sddcd.getReferenceNo());
						dto.setRemark(sddcd.getRemark());
						dto.setStatus(sddcd.getStatus());
						dto.setSubDepartmentDocumentCourierId(sddcd.getSubDepartmentDocumentCourierId());
						dto.setSubDepDocCouToken(sddcd.getSubDepDocCouToken());
						dto.setUnderwriterEmail(sddcd.getUnderwriterEmail());
						
						courierDtos.add(dto);
						
					}else {
						ArrayList<SubDepartmentDocumentCourierHelperDto> courierDtos=new ArrayList<>();
						
						SubDepartmentDocumentCourierHelperDto dto=new SubDepartmentDocumentCourierHelperDto();
						dto.setBranchCode(sddcd.getBranchCode());
						dto.setCreateBy(sddcd.getCreateBy());
						dto.setCreateDate(sddcd.getCreateDate());
						dto.setDocumentType(subDepartmentDocumentModel.getDocumentType().getDocName());
						dto.setReferenceNo(sddcd.getReferenceNo());
						dto.setRemark(sddcd.getRemark());
						dto.setStatus(sddcd.getStatus());
						dto.setSubDepartmentDocumentCourierId(sddcd.getSubDepartmentDocumentCourierId());
						dto.setSubDepDocCouToken(sddcd.getSubDepDocCouToken());
						dto.setUnderwriterEmail(sddcd.getUnderwriterEmail());
						dto.setRcvdBy(sddcd.getReceivedBy());
						dto.setRcvdDate(sddcd.getReceivedDate());
						
						courierDtos.add(dto);
						
						subDepDocMap.put(subDepartmentDocumentModel.getSubDepartment().getSudDepName(), courierDtos);
					}
					
				});

				for (Entry<String, ArrayList<SubDepartmentDocumentCourierHelperDto>> entry : subDepDocMap.entrySet()) {
				    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				    SubDepartmentHelperDto subDepartmentHelperDto=new SubDepartmentHelperDto();
				    subDepartmentHelperDto.setSudDepName(entry.getKey());
				    subDepartmentHelperDto.setSubDepartmentDocumentCourierDtos(entry.getValue());
				    
				    subDepartmentHelperDtos.add(subDepartmentHelperDto);
				}
				
				DepartmentHelperDto departmentHelperDto=new DepartmentHelperDto();
				departmentHelperDto.setDepName(dc.getDepartment().getDepartmentName());
				departmentHelperDto.setSubDepartmentHelperDtos(subDepartmentHelperDtos);
				departmentHelperDto.setDepCouStatus(dc.getCourierStatus());
				departmentHelperDto.setRcvdBy(dc.getReceivedBy());
				departmentHelperDto.setRcvdDate(dc.getReceivedDate());
				
				
				departmentHelperDtos.add(departmentHelperDto);

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		helperDto.setDepartmentHelperDtos(departmentHelperDtos);
		
		return helperDto;
	}

}
