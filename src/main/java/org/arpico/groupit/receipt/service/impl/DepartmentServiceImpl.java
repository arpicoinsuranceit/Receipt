package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.arpico.groupit.receipt.dao.DepartmentDao;
import org.arpico.groupit.receipt.dto.DepartmentDto;
import org.arpico.groupit.receipt.model.DepartmentModel;
import org.arpico.groupit.receipt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<DepartmentDto> getAllDepartments() throws Exception {
		
		List<DepartmentModel> departmentModels=(List<DepartmentModel>) departmentDao.findAll();
		List<DepartmentDto> departmentDtos=new ArrayList<>();
		
		
		departmentModels.forEach(d->{
			
			DepartmentDto departmentDto=new DepartmentDto();
			departmentDto.setCreateBy(d.getCreateBy());
			departmentDto.setCreateDate(d.getCreateDate());
			departmentDto.setDepartmentId(d.getDepartmentId());
			departmentDto.setDepartmentName(d.getDepartmentName());
			departmentDto.setModifyBy(d.getModifyBy());
			departmentDto.setModifyDate(d.getModifyDate());
			
			departmentDtos.add(departmentDto);
			
		});
		
		return departmentDtos;
	}

}
