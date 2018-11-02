package org.arpico.groupit.receipt.service;

import java.util.List;
import org.arpico.groupit.receipt.dto.SubDepartmentDto;

public interface SubDepartmentService {
	
	public List<SubDepartmentDto> getAllSubDepartments()throws Exception;
	
	public List<SubDepartmentDto> findByDepartment(Integer departmentId)throws Exception;

}
