package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.CourierModel;
import org.springframework.data.repository.CrudRepository;

public interface CourierDao extends CrudRepository<CourierModel, Integer>{
	
	public List<CourierModel> findByCourierStatusAndBranchCodeIn(String courierStatus,List<String> branches)throws Exception;
	
	public List<CourierModel> findByCourierStatusNotInAndBranchCodeIn(List<String> courierStatus,List<String> branches)throws Exception;

}
