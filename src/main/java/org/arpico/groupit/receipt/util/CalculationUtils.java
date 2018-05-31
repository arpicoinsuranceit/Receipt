package org.arpico.groupit.receipt.util;

public class CalculationUtils {

	public String getPaytrm(String get_frequance) {
		switch ( get_frequance) {
		 case "Monthly" :
	        	return "12";
	        case "Quartaly" :
	        	return "4";
	        case "Half Yearly" :
	        	return "2";
	        case "Yearly" :
	        	return "1";
	        case "Single Premium" :
	        	return "12";
	        default :
	        	return "-1";
		}
	}

}
