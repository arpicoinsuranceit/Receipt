package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.DocumentTypeModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.springframework.data.repository.CrudRepository;

public interface SubDepartmentDocumentDao extends CrudRepository<SubDepartmentDocumentModel, Integer>{
	
	public List<SubDepartmentDocumentModel> findBySubDepartment(SubDepartmentModel subDepartment)throws Exception;
	
	public SubDepartmentDocumentModel findBySubDepartmentAndDocumentType(SubDepartmentModel subDepartment,DocumentTypeModel documentType)throws Exception;


}
