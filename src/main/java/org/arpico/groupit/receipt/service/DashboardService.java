package org.arpico.groupit.receipt.service;

import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dto.DashboardPieDto;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.NameSeriesDto;
import org.arpico.groupit.receipt.dto.NameValuePairDto;

public interface DashboardService {

	DashboardPieDto getDashboardPie(Date to, Date from, String token) throws Exception;

	List<NameSeriesDto> getDashboardNameSeries(Date to, Date from, String token, String type) throws Exception;

	List<LastReceiptSummeryDto> getDetails(String token, Date to, Date from, String type)  throws Exception;

	List<NameValuePairDto> getCashFlowDateils(Date to, Date from, String token) throws Exception;

	List<LastReceiptSummeryDto> getCashFlowDateilGrid(String type, Date to, Date from, String token) throws Exception;

	List<NameSeriesDto> getDashboardPayMode(Date to, Date from, String token, String type) throws Exception;


	
}
