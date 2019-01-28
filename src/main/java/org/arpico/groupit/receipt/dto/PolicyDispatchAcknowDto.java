package org.arpico.groupit.receipt.dto;


public class PolicyDispatchAcknowDto {
	private PolicyDispatchDto dispatch;
	private HelthCareDto care;
	
	public PolicyDispatchDto getDispatch() {
		return dispatch;
	}
	public void setDispatch(PolicyDispatchDto dispatch) {
		this.dispatch = dispatch;
	}
	public HelthCareDto getCare() {
		return care;
	}
	public void setCare(HelthCareDto care) {
		this.care = care;
	}
	@Override
	public String toString() {
		return "PolicyDispatchAcknowDto [dispatch=" + dispatch + ", care=" + care + "]";
	}
	
}
