package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentCourierModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubDepartmentDocumentCourierDao extends JpaRepository<SubDepartmentDocumentCourierModel, Integer>{
	
	public List<SubDepartmentDocumentCourierModel> findByDepartmentCourier(DepartmentCourierModel departmentCourier)throws Exception;

}
