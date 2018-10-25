package org.arpico.groupit.receipt.service;

import java.util.List;
import org.arpico.groupit.receipt.dto.DepartmentDto;

public interface DepartmentService {
	
	public List<DepartmentDto> getAllDepartments()throws Exception;

}
