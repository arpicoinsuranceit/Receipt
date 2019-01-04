package org.arpico.groupit.receipt.dto;

public class SMSDto {
	
	private String smsType;
	private String rcptNo;
	private String docCode;
	private String userCode;
	
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getRcptNo() {
		return rcptNo;
	}
	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Override
	public String toString() {
		return "SMSDto [smsType=" + smsType + ", rcptNo=" + rcptNo + ", docCode=" + docCode + ", userCode=" + userCode
				+ "]";
	}
	
	
	

}

