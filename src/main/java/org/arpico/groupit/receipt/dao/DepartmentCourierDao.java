package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentCourierDao extends CrudRepository<DepartmentCourierModel, Integer>{
	
	public List<DepartmentCourierModel> findByCourier(CourierModel courier)throws Exception;

}
