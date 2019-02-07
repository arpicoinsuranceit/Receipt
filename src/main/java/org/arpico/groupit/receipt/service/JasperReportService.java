package org.arpico.groupit.receipt.service;

public interface JasperReportService {

	byte[] receiptRegisterReport(String fromDate, String toDate, String userCode)throws Exception;

	byte[] lapsedSummeryReport(String fromDate, String toDate, String branch) throws Exception;

	byte[] premiumDueReport(String agent, String branch) throws Exception;

	byte[] paymentHistory(String polnum) throws Exception;

	byte[] detailsOfPolicies(String fromDate, String toDate, String ic, String ul, String branch, String string,
			String string2, String sp) throws Exception;

	byte[] mcfpReport(String fromDate, String toDate, String advisor, String branch) throws Exception;

	byte[] proposalRegister(String fromDate, String toDate, String string, String string2, String branch, String unl,
			String frequency) throws Exception;

	byte[] pendingRequirements(String advisor, String branch, String string, String string2)throws Exception;

	byte[] grantStmtBranch(String branch, String year, String month, String code, String status) throws Exception;

	byte[] policyAcknowledgement(String branch, String year, String month) throws Exception;

	byte[] salesPerfDetail(String fromDate, String toDate, String code, String branch, String product,
			String frequency) throws Exception;

	byte[] unitIsPerfSummary(String fromDate, String toDate, String branch, String unl, String type, String frequency,
			String product) throws Exception;

	byte[] salesPerfSummary(String fromDate, String toDate, String branch, String frequency, String product, String so) throws Exception;
	
}
