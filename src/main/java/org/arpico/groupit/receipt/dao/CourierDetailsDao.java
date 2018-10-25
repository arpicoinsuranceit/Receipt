package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.CourierDetailsModel;
import org.springframework.data.repository.CrudRepository;

public interface CourierDetailsDao extends CrudRepository<CourierDetailsModel, Integer>{
	
	public List<CourierDetailsModel> findByBranchIn(List<String> branches)throws Exception;

}
