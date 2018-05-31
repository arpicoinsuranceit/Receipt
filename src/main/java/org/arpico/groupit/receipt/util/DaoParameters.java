package org.arpico.groupit.receipt.util;

public class DaoParameters {

	public String getParaForIn(String paraList) {
		String[] paras = paraList.split(",");
		String returnPara ="";
		for (String para : paras) {
			returnPara = returnPara+"'"+para+"',";
		}
		return returnPara.substring(0, (returnPara.length()-1));
	}
	
}
