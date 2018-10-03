package org.arpico.groupit.receipt.dto;

public class ResponseDto {
	
	private String code;
	private String message;
	private String status;
	private byte[] data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDto [code=" + code + ", message=" + message + ", status=" + status + ", data=" + data + "]";
	}

	

}
