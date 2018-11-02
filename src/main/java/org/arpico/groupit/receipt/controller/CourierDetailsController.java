package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.dto.CourierDetailsDto;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.CourierDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins="*")
public class CourierDetailsController {
	
	@Autowired
	private CourierDetailsService courierDetailsService;
	
	@RequestMapping(value="/courier_details/save",method=RequestMethod.POST)
	public String saveCourierDetail(@RequestBody CourierDetailsDto courierDetailsDto) throws Exception{

		return courierDetailsService.saveCourierDetail(courierDetailsDto);
		
	}
	
	@RequestMapping(value="/courier_details/view/{token:.+}",method=RequestMethod.GET)
	public List<CourierDetailsDto> getAllCourierDetail(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierDetailsService.findByBranchIn(userCode);
		
	}

}
