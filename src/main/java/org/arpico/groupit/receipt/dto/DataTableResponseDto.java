package org.arpico.groupit.receipt.dto;

import java.util.Arrays;

public class DataTableResponseDto {

	private Object[] data;
	private Integer draw;
	private Integer recordsFiltered;
	private Integer recordsTotal;

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	@Override
	public String toString() {
		return "DataTableResponseDto [data=" + Arrays.toString(data) + ", draw=" + draw + ", recordsFiltered="
				+ recordsFiltered + ", recordsTotal=" + recordsTotal + "]";
	}

}
