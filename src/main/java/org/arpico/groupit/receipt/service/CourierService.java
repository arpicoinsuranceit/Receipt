package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.CourierDto;

public interface CourierService {
	
	public List<CourierDto> findByCourierStatusAndBranchCodeIn(String userCode)throws Exception;
	
	public String saveCourier(CourierDto courierDto)throws Exception;
	
	public List<String> getBranches(String userCode)throws Exception;
	
	public String updateStatus(Integer couId,String status)throws Exception;
	
	public List<CourierDto> findByCourierStatusNotInAndBranchCodeIn(String userCode)throws Exception;

}
