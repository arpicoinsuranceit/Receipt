package org.arpico.groupit.receipt.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dto.DashboardPieDto;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.NameSeriesDto;
import org.arpico.groupit.receipt.dto.NameValuePairDto;
import org.arpico.groupit.receipt.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(value = "/dashboarddiv1/{toDate}/{fromDate}/{token:.+}", method = RequestMethod.GET)
	public DashboardPieDto dailyRep(@PathVariable String toDate, @PathVariable String fromDate,
			@PathVariable String token) throws Exception {

		//System.out.println(1);

		//System.out.println(token);
		//System.out.println(toDate);
		//System.out.println(fromDate);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

		Date to = null;

		try {
			to = sdf.parse(toDate);
		} catch (Exception e) {
			try {
				to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				to = new Date(Long.parseLong(toDate));
			}
		}

		Date from = null;
		try {
			from = sdf.parse(fromDate);
		} catch (Exception e) {
			try {
				from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				from = new Date(Long.parseLong(toDate));
			}
		}

		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		to = addDate(to);
		from = addDate(from);
		
		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		

		DashboardPieDto dashboardPieDto = dashboardService.getDashboardPie(to, from, token);

		return dashboardPieDto;
	}

	@RequestMapping(value = "/dashboarddiv2/{toDate}/{fromDate}/{token:.+}/{type}", method = RequestMethod.GET)
	public List<NameSeriesDto> dateVsAmount(@PathVariable String toDate, @PathVariable String fromDate,
			@PathVariable String token, @PathVariable String type) throws Exception {
		//System.out.println(2);
		//System.out.println(token);
		//System.out.println(toDate);
		//System.out.println(fromDate);
		//System.out.println(type);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

		Date to = null;

		try {
			to = sdf.parse(toDate);
		} catch (Exception e) {
			try {
				to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				try {
					to = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss").parse(toDate);
				}catch (Exception exp) {
					to = new Date(Long.parseLong(toDate));
				}
			}
		}

		Date from = null;
		try {
			from = sdf.parse(fromDate);
		} catch (Exception e) {
			try {
				from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(fromDate);
			}catch (Exception ex) {
				try {
					from = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss").parse(fromDate);
				}catch (Exception exp) {
					from = new Date(Long.parseLong(fromDate));
				}
				
			}
		}

		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		to = addDate(to);
		from = addDate(from);
		
		//System.out.println("from" + from);
		//System.out.println("to" + to);
		

		List<NameSeriesDto> dashboardPieDto = dashboardService.getDashboardNameSeries(to, from, token, type);

		return dashboardPieDto;
	}

	@RequestMapping(value = "/dashboarddetails/{type}/{toDate}/{fromDate}/{token:.+}", method = RequestMethod.GET)
	public List<LastReceiptSummeryDto> getDetails(@PathVariable String type, @PathVariable String toDate,
			@PathVariable String fromDate, @PathVariable String token) throws Exception {
		//System.out.println(3);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

		//System.out.println(toDate);
		//System.out.println(fromDate);
		//System.out.println(token);
		//System.out.println(type);

		Date to = null;

		try {
			to = sdf.parse(toDate);
		} catch (Exception e) {
			try {
				to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				to = new Date(Long.parseLong(toDate));
			}
		}

		Date from = null;
		try {
			from = sdf.parse(fromDate);
		} catch (Exception e) {
			try {
				from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				from = new Date(Long.parseLong(toDate));
			}
		}

		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		to = addDate(to);
		from = addDate(from);
		
		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
//System.out.println(type);
		return dashboardService.getDetails(token, to, from, type);

	}

	@RequestMapping(value = "/getCashFlowDetails/{toDate}/{fromDate}/{token:.+}", method = RequestMethod.GET)
	public List<NameValuePairDto> getCashFlowDetails(@PathVariable String toDate, @PathVariable String fromDate,
			@PathVariable String token) throws Exception {
		//System.out.println(4);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

		//System.out.println(toDate);
		//System.out.println(fromDate);
		//System.out.println(token);

		Date to = null;

		try {
			to = sdf.parse(toDate);
		} catch (Exception e) {
			try {
				to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				to = new Date(Long.parseLong(toDate));
			}
		}

		Date from = null;
		try {
			from = sdf.parse(fromDate);
		} catch (Exception e) {
			try {
				from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				from = new Date(Long.parseLong(toDate));
			}
		}

		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		to = addDate(to);
		from = addDate(from);
		
		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		return dashboardService.getCashFlowDateils(to, from, token);

	}

	@RequestMapping(value = "/getCashFlowDetailGrid/{type}/{toDate}/{fromDate}/{token:.+}", method = RequestMethod.GET)
	public List<LastReceiptSummeryDto> getCashFlowDetailGrid(@PathVariable String type, @PathVariable String toDate,
			@PathVariable String fromDate, @PathVariable String token) throws Exception {
		//System.out.println(5);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

		//System.out.println(toDate);
		//System.out.println(fromDate);
		//System.out.println(token);

		Date to = null;

		try {
			to = sdf.parse(toDate);
		} catch (Exception e) {
			try {
				to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				to = new Date(Long.parseLong(toDate));
			}
		}

		Date from = null;
		try {
			from = sdf.parse(fromDate);
		} catch (Exception e) {
			try {
				from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				from = new Date(Long.parseLong(toDate));
			}
		}

		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		to = addDate(to);
		from = addDate(from);
		
		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		return dashboardService.getCashFlowDateilGrid(type, to, from, token);

	}

	@RequestMapping(value = "/datevspaymode/{toDate}/{fromDate}/{token:.+}/{type}", method = RequestMethod.GET)
	public List<NameSeriesDto> dateVsPayMode(@PathVariable String toDate, @PathVariable String fromDate,
			@PathVariable String token, @PathVariable String type) throws Exception {
		//System.out.println(6);
		//System.out.println(token);
		//System.out.println(toDate);
		//System.out.println(fromDate);
		//System.out.println(type);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

		Date to = null;

		try {
			to = sdf.parse(toDate);
		} catch (Exception e) {
			try {
				to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				to = new Date(Long.parseLong(toDate));
			}
		}

		//System.out.println(fromDate);
		Date from = null;
		try {
			from = sdf.parse(fromDate);
		} catch (Exception e) {
			try {
				from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(toDate);
			}catch (Exception ex) {
				from = new Date(Long.parseLong(toDate));
			}
			
		}

		//System.out.println("from" + from);
		//System.out.println("to" + to);
		
		to = addDate(to);
		from = addDate(from);
		
		//System.out.println("from" + from);
		//System.out.println("to" + to);
		

		List<NameSeriesDto> dashboardPieDto = dashboardService.getDashboardPayMode(to, from, token, type);

		return dashboardPieDto;
	}

	public Date addDate(Date dt) {

//		Calendar c = Calendar.getInstance();
//		c.setTime(dt);
//		c.add(Calendar.DATE, 1);
//		dt = c.getTime();
//		
//		long milis = new Date().getTime() - dt.getTime();
//		
//		if(milis < 0) {
//			dt = new Date();
//		}

		return dt;
	}

}
