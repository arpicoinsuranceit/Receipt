package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.CourierDao;
import org.arpico.groupit.receipt.dao.DepartmentCourierDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentCourierDao;
import org.arpico.groupit.receipt.dto.CourierDto;
import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentCourierModel;
import org.arpico.groupit.receipt.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourierServiceImpl implements CourierService{
	
	@Autowired
	private CourierDao courierDao;
	
	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;
	
	@Autowired
	private DepartmentCourierDao departmentCourierDao;
	
	@Autowired
	private SubDepartmentDocumentCourierDao subDepartmentDocumentCourierDao;

	@Override
	public List<CourierDto> findByCourierStatusAndBranchCodeIn(String userCode) throws Exception {
		
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		
		List<CourierModel> courierModels=courierDao.findByCourierStatusAndBranchCodeIn("BRANCH", branches);
		
		List<CourierDto> courierDtos=new ArrayList<>();
		
		
		courierModels.forEach(co -> {
			System.out.println(co.getToken() + " Token ----- Token ");
			CourierDto courierDto=new CourierDto();
			
			courierDto.setBranchCode(co.getBranchCode());
			courierDto.setCourierId(co.getCourierId());
			courierDto.setCourierStatus(co.getCourierStatus());
			courierDto.setCreateBy(co.getCreateBy());
			courierDto.setCreateDate(co.getCreateDate());
			courierDto.setModifyBy(co.getModifyBy());
			courierDto.setModifyDate(co.getModifyDate());
			courierDto.setRemark(co.getRemark());
			courierDto.setToken(co.getToken());
			
			courierDtos.add(courierDto);
			
		});
		
		return courierDtos;
	}

	@Override
	public String saveCourier(CourierDto courierDto) throws Exception {
		
		return null;
	}

	@Override
	public List<String> getBranches(String userCode) throws Exception {
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		return branches;
	}

	@Override
	public String updateStatus(Integer couId, String status) throws Exception {
		CourierModel courierModel=courierDao.findOne(couId);
		if(courierModel!=null) {
			
			List<DepartmentCourierModel> departmentCourierModels=departmentCourierDao.findByCourier(courierModel);
			
			departmentCourierModels.forEach(dcm ->{
				try {
					List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourierModels=subDepartmentDocumentCourierDao.findByDepartmentCourier(dcm);
					dcm.setCourierStatus(status);
					
					departmentCourierDao.save(dcm);
					
					subDepartmentDocumentCourierModels.forEach(sddc -> {
						
						sddc.setStatus(status);
						
						subDepartmentDocumentCourierDao.save(sddc);
						
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			courierModel.setCourierStatus(status);
			return courierDao.save(courierModel) != null ? "200":"204";
		}
		return "204";
	}

	@Override
	public List<CourierDto> findByCourierStatusNotInAndBranchCodeIn(String userCode) throws Exception {
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		
		List<String> status=new ArrayList<>();
		status.add("BRANCH");
		status.add("CLOSED");
		
		List<CourierModel> courierModels=courierDao.findByCourierStatusNotInAndBranchCodeIn(status, branches);
		
		List<CourierDto> courierDtos=new ArrayList<>();
		
		
		courierModels.forEach(co -> {
			System.out.println(co.getToken() + " Token ----- Token ");
			CourierDto courierDto=new CourierDto();
			
			courierDto.setBranchCode(co.getBranchCode());
			courierDto.setCourierId(co.getCourierId());
			courierDto.setCourierStatus(co.getCourierStatus());
			courierDto.setCreateBy(co.getCreateBy());
			courierDto.setCreateDate(co.getCreateDate());
			courierDto.setModifyBy(co.getModifyBy());
			courierDto.setModifyDate(co.getModifyDate());
			courierDto.setRemark(co.getRemark());
			courierDto.setToken(co.getToken());
			
			courierDtos.add(courierDto);
			
		});
		
		return courierDtos;
	}

}
