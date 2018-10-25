package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.DepartmentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.springframework.data.repository.CrudRepository;


public interface SubDepartmentDao extends CrudRepository<SubDepartmentModel, Integer>{
	
	public List<SubDepartmentModel> findByDepId(DepartmentModel depId)throws Exception;

}
