package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.DocumentTypeDto;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentDto;

public interface SubDepartmentDocumentService {
	
	public List<SubDepartmentDocumentDto> getAllSubDepartmentDocs()throws Exception;
	
	public List<DocumentTypeDto> findBySubDepartment(Integer subDepId)throws Exception;

}
