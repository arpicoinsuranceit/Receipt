package org.arpico.groupit.receipt.util;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DaoParameters {

	public String getParaForIn(String paraList) {
		String[] paras = paraList.split(",");
		String returnPara ="";
		for (String para : paras) {
			returnPara = returnPara+"'"+para+"',";
		}
		return returnPara.substring(0, (returnPara.length()-1));
	}
	
	
	public String getParaForIn(String[] paras) {
		String returnPara ="";
		for (String para : paras) {
			returnPara = returnPara+"'"+para+"',";
		}
		return returnPara.substring(0, (returnPara.length()-1));
	}
	
	public String getParaForIn(List<String> paras) {
		String returnPara ="";
		for (String para : paras) {
			returnPara = returnPara+"'"+para+"',";
		}
		return returnPara.substring(0, (returnPara.length()-1));
	}
	
}
