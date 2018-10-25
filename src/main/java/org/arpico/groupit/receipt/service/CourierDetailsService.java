package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.CourierDetailsDto;

public interface CourierDetailsService {
	
	public String saveCourierDetail(CourierDetailsDto courierDetailsDto)throws Exception;
	
	public List<CourierDetailsDto> findByBranchIn(String userCode)throws Exception;

}
