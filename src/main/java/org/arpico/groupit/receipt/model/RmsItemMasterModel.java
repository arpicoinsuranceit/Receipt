package org.arpico.groupit.receipt.model;

public class RmsItemMasterModel {

	private String itemCode;
	private String itemName;
	private String pluCode;
	private String unit;
	private Double unitPrice;
	private Double avgPrice;
	private String glGroup;
	private String itmGroup;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPluCode() {
		return pluCode;
	}

	public void setPluCode(String pluCode) {
		this.pluCode = pluCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getGlGroup() {
		return glGroup;
	}

	public void setGlGroup(String glGroup) {
		this.glGroup = glGroup;
	}

	public String getItmGroup() {
		return itmGroup;
	}

	public void setItmGroup(String itmGroup) {
		this.itmGroup = itmGroup;
	}

	@Override
	public String toString() {
		return "RmsItemMasterModel [itemCode=" + itemCode + ", itemName=" + itemName + ", pluCode=" + pluCode
				+ ", unit=" + unit + ", unitPrice=" + unitPrice + ", avgPrice=" + avgPrice + ", glGroup=" + glGroup
				+ ", itmGroup=" + itmGroup + "]";
	}

	

}
