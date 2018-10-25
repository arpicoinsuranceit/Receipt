package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentCourierModel;
import org.springframework.data.repository.CrudRepository;

public interface SubDepartmentDocumentCourierDao extends CrudRepository<SubDepartmentDocumentCourierModel, Integer>{
	
	public List<SubDepartmentDocumentCourierModel> findByDepartmentCourier(DepartmentCourierModel departmentCourier)throws Exception;

}
