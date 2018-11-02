package org.arpico.groupit.receipt.service;

import org.arpico.groupit.receipt.dto.CourierDetailsHelperDto;

public interface DepartmentCourierService {
	
	public CourierDetailsHelperDto getCourierDetailsByCourier(Integer couId)throws Exception;

}
