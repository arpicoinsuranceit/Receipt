package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.DepartmentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubDepartmentDao extends JpaRepository<SubDepartmentModel, Integer>{
	
	public List<SubDepartmentModel> findByDepId(DepartmentModel depId)throws Exception;
	
	public List<SubDepartmentModel> findBySudDepNameContaining(String sudDepName)throws Exception;

}
