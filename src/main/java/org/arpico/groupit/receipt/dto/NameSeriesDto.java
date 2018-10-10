package org.arpico.groupit.receipt.dto;

import java.util.List;

public class NameSeriesDto {

	private String name;
	private List<NameValueDto> series;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NameValueDto> getSeries() {
		return series;
	}

	public void setSeries(List<NameValueDto> series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "NameSeriesDto [name=" + name + ", series=" + series + "]";
	}

}
