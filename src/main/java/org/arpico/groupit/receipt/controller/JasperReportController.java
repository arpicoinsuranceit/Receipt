package org.arpico.groupit.receipt.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.arpico.groupit.receipt.dto.AgentDto;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.AgentService;
import org.arpico.groupit.receipt.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@RestController
@CrossOrigin(origins = "*")
public class JasperReportController {

	@Autowired
	private JasperReportService jasperReportService;
	
	@Autowired
	private AgentService agentService;

	private JwtDecoder jwtDecorder;

	@RequestMapping(value = "/report/receiptRegister/{fromDate}/{toDate}/{token}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] receiptRegister(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String token, HttpServletResponse response) {
		System.out.println(fromDate);
		response.setHeader("Content-Disposition", "inline; filename=receipt_register.pdf");
		response.setContentType("application/pdf");
		System.out.println(toDate + "," + token);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(token);
		token = new String(decodedByte);
		
	    DateFormat inputFormat = new SimpleDateFormat(
	        "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);

		try {
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd",
				        Locale.ENGLISH);
			outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			//String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			//final Date date = format.parse(curDate);
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			String output1 = outputFormat.format(calendar.getTime()); 
			
			fromDate=output1;
			toDate=output2;
			
			System.out.println(fromDate + " From Date After format");
			System.out.println(toDate + " To Date After format");
			
			jwtDecorder = new JwtDecoder();
			String userCode=jwtDecorder.generate(token);
			
			return jasperReportService.receiptRegisterReport(fromDate, toDate, userCode);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/report/lapsedSummery/{fromDate}/{toDate}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] lapsedSummery(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String branch, HttpServletResponse response) {
		System.out.println(fromDate);
		response.setHeader("Content-Disposition", "inline; filename=lapsed_summery.pdf");
		response.setContentType("application/pdf");
		System.out.println(toDate + "," + branch);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);
		
	    DateFormat inputFormat = new SimpleDateFormat(
	        "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);

		try {
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd",
				        Locale.ENGLISH);
			outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

			//String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			//final Date date = format.parse(curDate);
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			String output1 = outputFormat.format(calendar.getTime()); 
			
			fromDate=output1;
			toDate=output2;
			
			System.out.println(fromDate + " From Date After format");
			System.out.println(toDate + " To Date After format");
			
			return jasperReportService.lapsedSummeryReport(fromDate, toDate, branch);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	@RequestMapping(value = "/report/premiumDue/{agent}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] premiumDue(@PathVariable String agent, @PathVariable String branch, HttpServletResponse response) {

		response.setHeader("Content-Disposition", "inline; filename=premium_due.pdf");
		response.setContentType("application/pdf");
		System.out.println(agent + "," + branch);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(agent);
		agent = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		try {
			
			return jasperReportService.premiumDueReport(agent, branch);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/report/paymentHistory/{polnum}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] paymentHistory(@PathVariable String polnum, HttpServletResponse response) {

		response.setHeader("Content-Disposition", "inline; filename=premium_due.pdf");
		response.setContentType("application/pdf");
		System.out.println(polnum);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(polnum);
		polnum = new String(decodedByte);

		try {
			
			return jasperReportService.paymentHistory(polnum);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/report/getAgents",method=RequestMethod.POST)
	public List<AgentDto> getAllAgentRelatedToBranches(@RequestBody String branches){
		try {
			return agentService.findAgentByLocations(branches);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
