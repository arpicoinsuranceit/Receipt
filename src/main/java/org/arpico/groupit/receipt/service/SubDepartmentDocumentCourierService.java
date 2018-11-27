package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.SubDepartmentDocumentCourierDto;

public interface SubDepartmentDocumentCourierService {
	
	public String saveSubDepDocCourier(SubDepartmentDocumentCourierDto subDepartmentDocumentCourierDto, Integer depId, Integer subDepId,Boolean isHOUser)throws Exception;
	
	public List<SubDepartmentDocumentCourierDto> getSubDepDocCourierByDepCou(Integer depCouId)throws Exception;

}
