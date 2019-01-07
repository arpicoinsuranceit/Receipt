package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentCourierDao extends JpaRepository<DepartmentCourierModel, Integer>{
	
	public List<DepartmentCourierModel> findByCourier(CourierModel courier)throws Exception;

}
