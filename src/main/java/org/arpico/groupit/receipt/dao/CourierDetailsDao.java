package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.CourierDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierDetailsDao extends JpaRepository<CourierDetailsModel, Integer>{
	
	public List<CourierDetailsModel> findByBranchIn(List<String> branches)throws Exception;

}
