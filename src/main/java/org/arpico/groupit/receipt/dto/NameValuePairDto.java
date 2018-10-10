package org.arpico.groupit.receipt.dto;

public class NameValuePairDto {

	
	private String name;
	private String value;
	private String count;
	
	public NameValuePairDto() {};

	public NameValuePairDto(String name, String value, String count) {
		super();
		this.name = name;
		this.value = value;
		this.count = count;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "NameValuePairDto [name=" + name + ", value=" + value + ", count=" + count + "]";
	}

}
