package org.arpico.groupit.receipt.dto;

import java.util.List;

public class DashboardPieDto {

	private List<NameValuePairDto> nameValues;
	private Double total;

	public List<NameValuePairDto> getNameValues() {
		return nameValues;
	}

	public void setNameValues(List<NameValuePairDto> nameValues) {
		this.nameValues = nameValues;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
