package org.arpico.groupit.receipt.controller;

import java.util.Base64;
import java.util.Base64.Decoder;

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
		//System.out.println(fromDate);
		response.setHeader("Content-Disposition", "inline; filename=receipt_register.pdf");
		response.setContentType("application/pdf");
		//System.out.println(toDate + "," + token);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(token);
		token = new String(decodedByte);
		
		//System.out.println(fromDate + "," + toDate);
		
	    DateFormat inputFormat = new SimpleDateFormat(
	        "E MMM dd yyyy HH:mm:ss");

		try {
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;
			
			//System.out.println(fromDate + " From Date After format");
			//System.out.println(toDate + " To Date After format");
			
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
		//System.out.println(fromDate);
		response.setHeader("Content-Disposition", "inline; filename=lapsed_summery.pdf");
		response.setContentType("application/pdf");
		//System.out.println(toDate + "," + branch);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);
		
	    DateFormat inputFormat = new SimpleDateFormat(
	        "E MMM dd yyyy HH:mm:ss");

		try {
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;
			
			//System.out.println(fromDate + " From Date After format");
			//System.out.println(toDate + " To Date After format");
			
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
		//System.out.println(agent + "," + branch);

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
		//System.out.println(polnum);

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
	
	@RequestMapping(value = "/report/detailsOfPolicies/{fromDate}/{toDate}/{ic}/{ul}/{branch}/{sp}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] detailsOfPolicies(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String ic,
			@PathVariable String ul, @PathVariable String branch, @PathVariable String sp, HttpServletResponse response) {
		System.out.println(
				fromDate + "," + toDate + "," + ic + "," + ul + "," + branch  + "," + sp );
		
		response.setHeader("Content-Disposition", "inline; filename=details_of_policies.pdf");
		response.setContentType("application/pdf");
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(ul);
		ul = new String(decodedByte);

		decodedByte = decoder.decode(ic);
		ic = new String(decodedByte);

		decodedByte = decoder.decode(sp);
		sp = new String(decodedByte);
		
		 DateFormat inputFormat = new SimpleDateFormat(
			        "E MMM dd yyyy HH:mm:ss");
		 
		try {
			if (ic.equalsIgnoreCase("ALL")) {
				ic = "%";
			} else if (ic.equalsIgnoreCase("undefined")) {
				ic = "%";
			}

			if (ul.equalsIgnoreCase("ALL")) {
				ul = "%";
			} else if (ul.equalsIgnoreCase("undefined")) {
				ul = "%";
			}

			if (branch.equalsIgnoreCase("ALL")) {
				branch = "%";
			} else if (branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}
			
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;

			return jasperReportService.detailsOfPolicies(fromDate, toDate, ic, ul, branch, "%", "%", sp);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/report/mcfpReport/{fromDate}/{toDate}/{advisor}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] mcfpReport(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String advisor,
			@PathVariable String branch, HttpServletResponse response) {
		System.out.println(fromDate);
		response.setHeader("Content-Disposition", "inline; filename=mcfpr.pdf");
		response.setContentType("application/pdf");
		System.out.println(advisor + "," + branch);

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(advisor);
		advisor = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);
		
		DateFormat inputFormat = new SimpleDateFormat(
			        "E MMM dd yyyy HH:mm:ss");

		try {
			if (branch.equalsIgnoreCase("ALL")) {
				branch = "%";
			} else if (branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (advisor.equalsIgnoreCase("ALL")) {
				advisor = "%";
			}
			
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;

			return jasperReportService.mcfpReport(fromDate, toDate, advisor, branch);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/report/proposalRegister/{fromDate}/{toDate}/{branch}/{unl}/{frequency}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] proposalRegister(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String branch,
			@PathVariable String unl, @PathVariable String frequency, HttpServletResponse response) {
		
		
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
		response.setContentType("application/pdf");

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(unl);
		unl = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		DateFormat inputFormat = new SimpleDateFormat(
			        "E MMM dd yyyy HH:mm:ss");

		try {
			if (unl.equalsIgnoreCase("ALL") || unl.equalsIgnoreCase("undefined")) {
				unl = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}
			
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;
			
			System.out.println(fromDate + "," + toDate + ","  + branch + "," + unl + "," + frequency);
			
			return jasperReportService.proposalRegister(fromDate, toDate, "%", "%", branch, unl, frequency);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/report/pendingRequirements/{advisor}/{branch}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] pendingRequirements(@PathVariable String advisor, @PathVariable String branch, HttpServletResponse response) {
		
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
		response.setContentType("application/pdf");

		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(advisor);
		advisor = new String(decodedByte);
		
		System.out.println(advisor + "," + branch );

		try {
			if (advisor.equalsIgnoreCase("ALL") || advisor.equalsIgnoreCase("undefined")) {
				advisor = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			return jasperReportService.pendingRequirements(advisor, branch, "%", "%");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/report/grantStmtBranch/{branch}/{year}/{month}/{code}/{status}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] grantStmtBranch(@PathVariable String branch, @PathVariable String year, @PathVariable String month,
			@PathVariable String code, @PathVariable String status, HttpServletResponse response) {
		System.out.println(branch + "," + year + "," + month + "," + code);
		
		response.setHeader("Content-Disposition", "inline; filename=grant_stmt.pdf");
		response.setContentType("application/pdf");
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(year);
		year = new String(decodedByte);

		decodedByte = decoder.decode(month);
		month = new String(decodedByte);

		decodedByte = decoder.decode(code);
		code = new String(decodedByte);
		
		decodedByte = decoder.decode(status);
		status = new String(decodedByte);

		try {

			if (month.equalsIgnoreCase("ALL") || month.equalsIgnoreCase("undefined")) {
				month = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}
			
			if (status.equalsIgnoreCase("ALL") || status.equalsIgnoreCase("undefined")) {
				status = "%";
			}

			return jasperReportService.grantStmtBranch(branch, year, month, code, status);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/report/policyAcknowledgement/{branch}/{year}/{month}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] policyAcknowledgement(@PathVariable String branch, @PathVariable String year,
			@PathVariable String month, HttpServletResponse response) {
		System.out.println(branch + "," + year + "," + month);
		
		response.setHeader("Content-Disposition", "inline; filename=policy_ack.pdf");
		response.setContentType("application/pdf");
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(month);
		month = new String(decodedByte);

		decodedByte = decoder.decode(year);
		year = new String(decodedByte);
		
		try {

			System.out.println("before" + month);
			if (month.equalsIgnoreCase("ALL") || month.equalsIgnoreCase("undefined")) {
				month = "%";
				System.out.println("after" + month);

			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}

			return jasperReportService.policyAcknowledgement(branch, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value = "/report/salesPerfSummary/{fromDate}/{toDate}/{branch}/{frequency}/{product}/{so}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] salesPerfSummary(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String branch,
			@PathVariable String frequency, @PathVariable String product, @PathVariable String so, HttpServletResponse response) {
		System.out.println(fromDate + "," + toDate + "," + branch + "," + frequency + ","
				+ product + "," + so);
		
		response.setHeader("Content-Disposition", "inline; filename=performance_summ.pdf");
		response.setContentType("application/pdf");
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		
		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);
		
		decodedByte = decoder.decode(so);
		so = new String(decodedByte);
		
		DateFormat inputFormat = new SimpleDateFormat(
			        "E MMM dd yyyy HH:mm:ss");
		
		try {
			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";

			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";

			}

			if (so.equalsIgnoreCase("ALL") || so.equalsIgnoreCase("undefined")) {
				so = "%";

			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";

			}
			
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;

			return jasperReportService.salesPerfSummary(fromDate, toDate, branch, frequency, product, so);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/report/unitIsPerfSummary/{fromDate}/{toDate}/{branch}/{unl}/{type}/{frequency}/{product}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] unitIsPerfSummary(@PathVariable String fromDate, @PathVariable String toDate,
			@PathVariable String branch,@PathVariable String unl, @PathVariable String type, @PathVariable String frequency,
			@PathVariable String product, HttpServletResponse response) {
		System.out.println(fromDate + "," + toDate + "," + branch + "," + unl + "," + type
				+ "," + frequency + "," + product);
		
		response.setHeader("Content-Disposition", "inline; filename=unit_performance_summ.pdf");
		response.setContentType("application/pdf");
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);

		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);
		
		decodedByte = decoder.decode(unl);
		unl = new String(decodedByte);
		
		decodedByte = decoder.decode(type);
		type = new String(decodedByte);
		
		DateFormat inputFormat = new SimpleDateFormat(
			        "E MMM dd yyyy HH:mm:ss");
		
		try {
			if (unl.equalsIgnoreCase("ALL") || unl.equalsIgnoreCase("undefined")) {
				unl = "%";
			}

			if (type.equalsIgnoreCase("ALL") || type.equalsIgnoreCase("undefined")) {
				type = "%";
			}

			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";
			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}
			
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;

			return jasperReportService.unitIsPerfSummary(fromDate, toDate, branch, unl, type, frequency,
					product);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/report/salesPerfDetail/{fromDate}/{toDate}/{code}/{branch}/{product}/{frequency}", method = RequestMethod.GET, produces = "application/pdf")
	public byte[] salesPerfDetail(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable String code,
			@PathVariable String branch,@PathVariable String product, @PathVariable String frequency
			, HttpServletResponse response) {
		System.out.println(fromDate + "," + toDate + "," + code + "," + branch + ","
				+ product + "," + frequency);
		
		response.setHeader("Content-Disposition", "inline; filename=performance_detail.pdf");
		response.setContentType("application/pdf");
		
		Decoder decoder = Base64.getDecoder();

		byte[] decodedByte = null;

		decodedByte = decoder.decode(fromDate);
		fromDate = new String(decodedByte);

		decodedByte = decoder.decode(toDate);
		toDate = new String(decodedByte);
		
		decodedByte = decoder.decode(branch);
		branch = new String(decodedByte);

		decodedByte = decoder.decode(frequency);
		frequency = new String(decodedByte);
		
		decodedByte = decoder.decode(product);
		product = new String(decodedByte);

		decodedByte = decoder.decode(code);
		code = new String(decodedByte);
		
		DateFormat inputFormat = new SimpleDateFormat(
			        "E MMM dd yyyy HH:mm:ss");
		
		try {
			if (code.equalsIgnoreCase("ALL") || code.equalsIgnoreCase("undefined")) {
				code = "%";
			}

			if (product.equalsIgnoreCase("ALL") || product.equalsIgnoreCase("undefined")) {
				product = "%";
			}

			if (frequency.equalsIgnoreCase("ALL") || frequency.equalsIgnoreCase("undefined")) {
				frequency = "%";
			}

			if (branch.equalsIgnoreCase("ALL") || branch.equalsIgnoreCase("undefined")) {
				branch = "%";
			}
			
			Date date1 = inputFormat.parse(fromDate);
			Date date2 = inputFormat.parse(toDate);
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			String output1 = outputFormat.format(date1);
			String output2 = outputFormat.format(date2);
			
			fromDate=output1;
			toDate=output2;

			return jasperReportService.salesPerfDetail(fromDate, toDate, code, branch, product, frequency);
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
