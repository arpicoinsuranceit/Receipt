package org.arpico.groupit.receipt.dto;

public class InventoryDetailsDto {

	private Integer itemCod;
	private String itemName;
	private Integer qty;
	private Double untPrice;
	private Double untPriceTot;
	private Integer qtyTot;
	private Double subTot;
	private String subTotInWrd;

	public Integer getItemCod() {
		return itemCod;
	}

	public void setItemCod(Integer itemCod) {
		this.itemCod = itemCod;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getUntPrice() {
		return untPrice;
	}

	public void setUntPrice(Double untPrice) {
		this.untPrice = untPrice;
	}

	public Double getUntPriceTot() {
		return untPriceTot;
	}

	public void setUntPriceTot(Double untPriceTot) {
		this.untPriceTot = untPriceTot;
	}

	public Integer getQtyTot() {
		return qtyTot;
	}

	public void setQtyTot(Integer qtyTot) {
		this.qtyTot = qtyTot;
	}

	public Double getSubTot() {
		return subTot;
	}

	public void setSubTot(Double subTot) {
		this.subTot = subTot;
	}

	public String getSubTotInWrd() {
		return subTotInWrd;
	}

	public void setSubTotInWrd(String subTotInWrd) {
		this.subTotInWrd = subTotInWrd;
	}

	@Override
	public String toString() {
		return "InventoryDetailsDto [itemCod=" + itemCod + ", itemName=" + itemName + ", qty=" + qty + ", untPrice="
				+ untPrice + ", untPriceTot=" + untPriceTot + ", qtyTot=" + qtyTot + ", subTot=" + subTot
				+ ", subTotInWrd=" + subTotInWrd + "]";
	}

}
