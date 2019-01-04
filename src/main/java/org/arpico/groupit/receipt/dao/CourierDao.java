package org.arpico.groupit.receipt.dao;

import java.util.List;
import org.arpico.groupit.receipt.model.CourierModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierDao extends JpaRepository<CourierModel, Integer>{
	
	public List<CourierModel> findByCourierStatusAndBranchCodeIn(String courierStatus,List<String> branches)throws Exception;
	
	public List<CourierModel> findByCourierStatus(String courierStatus)throws Exception;
	
	public List<CourierModel> findByCourierStatusAndToBranchIn(String courierStatus,List<String> branches)throws Exception;
	
	public List<CourierModel> findByCourierStatusNotInAndBranchCodeIn(List<String> courierStatus,List<String> branches)throws Exception;
	
	public List<CourierModel> findByCourierStatusAndBranchCodeIn(String courierStatus,List<String> branches, Pageable pageable)throws Exception;

	public List<CourierModel> findByCourierStatusAndToBranchIn(String courierStatus,List<String> branches, Pageable pageable)throws Exception;

	public Integer countByCourierStatusAndToBranchIn(String courierStatus, List<String> branches) throws Exception;

	public Integer countByCourierStatusAndBranchCodeIn(String courierStatus, List<String> branches) throws Exception;
	
}
