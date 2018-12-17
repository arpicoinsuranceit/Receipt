package org.arpico.groupit.receipt.service;

public interface JasperReportService {

	byte[] receiptRegisterReport(String fromDate, String toDate, String userCode)throws Exception;

	byte[] lapsedSummeryReport(String fromDate, String toDate, String branch) throws Exception;

	byte[] premiumDueReport(String agent, String branch) throws Exception;

	byte[] paymentHistory(String polnum) throws Exception;
	
}
