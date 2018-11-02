package org.arpico.groupit.receipt.dto;

public class EmailResponseDto {
	private String code;
	private String message;
	private String status;

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

	@Override
	public String toString() {
		return "EmailResponseDto [code=" + code + ", message=" + message + ", status=" + status + "]";
	}
	
	
}
