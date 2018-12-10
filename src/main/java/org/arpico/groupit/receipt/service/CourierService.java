package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.CourierDetailsHelperDto;
import org.arpico.groupit.receipt.dto.CourierDto;

public interface CourierService {
	
	public List<CourierDto> findByCourierStatusAndBranchCodeIn(String userCode,Boolean isHoUser)throws Exception;
	
	public List<CourierDto> findByCourierStatusAndBranchCodeIn(String userCode,String status)throws Exception;

	public String receiveCourier(CourierDetailsHelperDto courierDetailsHelperDto,String userCode)throws Exception;
	
	public String receiveCourierDocument(CourierDetailsHelperDto courierDetailsHelperDto,String userCode)throws Exception;
	
	public List<String> getBranches(String userCode)throws Exception;
	
	public String updateStatus(Integer couId,String status)throws Exception;
	
	public List<CourierDto> findByCourierStatusNotInAndBranchCodeIn(String userCode)throws Exception;

	List<CourierDto> findByCourierStatusAndToBranchIn(String userCode, Boolean isHoUser, String status)
			throws Exception;

	public String sendCourier(CourierDetailsHelperDto courierDetailsHelperDto, String userCode, String couType)throws Exception;
	
	public String removeCourier(Integer id)throws Exception;

}
