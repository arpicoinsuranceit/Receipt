package org.arpico.groupit.receipt.service;

public interface JasperReportService {

	byte[] receiptRegisterReport(String fromDate, String toDate, String userCode)throws Exception;
	
}
