package org.arpico.groupit.receipt.util;

import java.util.Date;


public class AppConstant {
	public static final String SECRET_KEY = "INsuRANCE%SECRet#KEY123";
	public static final String SBU_CODE = "450";
	public static final boolean FALSE = false;
	public static final String NO_VALUE = "";
	public static final Integer ZERO = 0;
	public static final Date DATE = new Date();
	public static final String EMPTY_STRING = "";
	public static final String NULL = null;
	public static final Double ZERO_FOR_DECIMAL = 0.0000;
	public static final Double ZERO_TWO_DECIMAL = 0.00;
	public static final String DOC_CODE_QUOT = "QUOT";
	public static final String SYSTEM_CREATE = "system";
	
	public static final String URI_GET_QUO_DETAILS = "http://10.10.10.120:8084/Quotation/getQuoDetail";
	public static final String URI_GET_QUO_SHEDULES = "http://10.10.10.120:8084/Quotation/getShedule";
	public static final String URI_GET_QUO_MEDILEIS = "http://10.10.10.120:8084/Quotation/getMediDetails";
	public static final String URI_QUOTATION_AVAILABLE = "http://10.10.10.120:8084/Quotation/isavailable";
	public static final String URI_QUOTATION_SURRENDER_VALS = "http://10.10.10.120:8084/Quotation/getSurrenderVals";
	public static final String URI_QUOTATION_PENSION_SHEDULE = "http://10.10.10.120:8084/Quotation/getPensionShedule";
	public static final String URI_QUOTATION_NOMINEE_DETAILS = "http://10.10.10.120:8084/Quotation/getNominee";
	public static final String URI_UPDATE_STATUS = "http://10.10.10.120:8084/Quotation/updateStatus";
	public static final String URI_QUOTATION_DETAILS_FROM_SEQNO = "http://10.10.10.120:8084/Quotation/getQuotationDetailFromSeqNo";
	
	public static final String DOC_CODE_FOR_RECEIPT = "RCPP";
	public static final String POLICY_STATUS_PLISU = "PLISU";
	
	
}
