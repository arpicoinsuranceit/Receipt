package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.arpico.groupit.receipt.dao.DepartmentDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDao;
import org.arpico.groupit.receipt.dto.SubDepartmentDto;
import org.arpico.groupit.receipt.model.DepartmentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.arpico.groupit.receipt.service.SubDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SubDepartmentServiceImp implements SubDepartmentService{
	
	@Autowired
	private SubDepartmentDao subDepartmentDao;
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<SubDepartmentDto> getAllSubDepartments() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubDepartmentDto> findByDepartment(Integer departmentId) throws Exception {
		DepartmentModel departmentModel=departmentDao.findOne(departmentId);
		
		List<SubDepartmentModel> subDepartmentModels=subDepartmentDao.findByDepId(departmentModel);
		
		List<SubDepartmentDto> subDepartmentDtos=new ArrayList<>();
		
		subDepartmentModels.forEach(sd ->{
			SubDepartmentDto subDepartmentDto=new SubDepartmentDto();
			
			subDepartmentDto.setDepId(sd.getDepId().getDepartmentId());
			subDepartmentDto.setSubDepId(sd.getSubDepId());
			subDepartmentDto.setSudDepName(sd.getSudDepName());
			subDepartmentDto.setCreateDate(sd.getCreateDate());
			subDepartmentDto.setCreateBy(sd.getCreateBy());
			
			subDepartmentDtos.add(subDepartmentDto);
			
		});
		
		return subDepartmentDtos;
	}

	

}
