package org.arpico.groupit.receipt.model;

public class SmSequenceModel {
	
	private Long incrementBy;
	private Long maxValue;
	private Long minValue;
	private Long currentValue;
	private String cycle;
	
	public Long getIncrementBy() {
		return incrementBy;
	}
	public void setIncrementBy(Long incrementBy) {
		this.incrementBy = incrementBy;
	}
	public Long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
	public Long getMinValue() {
		return minValue;
	}
	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}
	public Long getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
}
