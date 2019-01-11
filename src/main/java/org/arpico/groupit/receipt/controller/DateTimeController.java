package org.arpico.groupit.receipt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class DateTimeController {

	
	@RequestMapping(value="/dateTime",method=RequestMethod.GET)
		public String getCurrentTime() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
	    Date date = new Date();  
	    String time=formatter.format(date).toString();
	    return time;
		}
	
	@RequestMapping(value="/getdate",method=RequestMethod.GET)
	public String getCurrentDate() {
	SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy ");  
    Date date = new Date();  
    String currentdate=formatter.format(date).toString(); 
    return currentdate;
   
	}
	
}
