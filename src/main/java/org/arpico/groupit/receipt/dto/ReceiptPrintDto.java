package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;
import java.util.Date;

public class ReceiptPrintDto {

	private ArrayList<InventoryDetailsDto> inventoryDtl;

	private Integer cusCode;
	private String cusName;
	private String cusAddress1;
	private String cusAddress2;
	private String cusAddress3;
	private String payMode;
	private Double amt;
	private String amtInWord;
	private String docCode;
	private Integer docNum;
	private Date rctDate;
	private String location;
	private Integer quoNum;
	private Integer qdId;
	private Integer polNum;
	private Integer propNum;
	private Double settlement;
	private Integer chqNo;
	private Integer bankCode;
	private String chqDate;
	private Integer agtCode;
	private String agtName;
	private String userName;
	private String rctStatus;
	private String cusTitle;
	private String remark;

	public Integer getCusCode() {
		return cusCode;
	}

	public void setCusCode(Integer cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusAddress1() {
		return cusAddress1;
	}

	public void setCusAddress1(String cusAddress1) {
		this.cusAddress1 = cusAddress1;
	}

	public String getCusAddress2() {
		return cusAddress2;
	}

	public void setCusAddress2(String cusAddress2) {
		this.cusAddress2 = cusAddress2;
	}

	public String getCusAddress3() {
		return cusAddress3;
	}

	public void setCusAddress3(String cusAddress3) {
		this.cusAddress3 = cusAddress3;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getAmtInWord() {
		return amtInWord;
	}

	public void setAmtInWord(String amtInWord) {
		this.amtInWord = amtInWord;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public Integer getDocNum() {
		return docNum;
	}

	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}

	public Date getRctDate() {
		return rctDate;
	}

	public void setRctDate(Date rctDate) {
		this.rctDate = rctDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getQuoNum() {
		return quoNum;
	}

	public void setQuoNum(Integer quoNum) {
		this.quoNum = quoNum;
	}

	public Integer getQdId() {
		return qdId;
	}

	public void setQdId(Integer qdId) {
		this.qdId = qdId;
	}

	public Integer getPolNum() {
		return polNum;
	}

	public void setPolNum(Integer polNum) {
		this.polNum = polNum;
	}

	public Integer getPropNum() {
		return propNum;
	}

	public void setPropNum(Integer propNum) {
		this.propNum = propNum;
	}

	public Double getSettlement() {
		return settlement;
	}

	public void setSettlement(Double settlement) {
		this.settlement = settlement;
	}

	public Integer getChqNo() {
		return chqNo;
	}

	public void setChqNo(Integer chqNo) {
		this.chqNo = chqNo;
	}

	public Integer getBankCode() {
		return bankCode;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public String getChqDate() {
		return chqDate;
	}

	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}

	public Integer getAgtCode() {
		return agtCode;
	}

	public void setAgtCode(Integer agtCode) {
		this.agtCode = agtCode;
	}

	public String getAgtName() {
		return agtName;
	}

	public void setAgtName(String agtName) {
		this.agtName = agtName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRctStatus() {
		return rctStatus;
	}

	public void setRctStatus(String rctStatus) {
		this.rctStatus = rctStatus;
	}

	public String getCusTitle() {
		return cusTitle;
	}

	public void setCusTitle(String cusTitle) {
		this.cusTitle = cusTitle;
	}

	public ArrayList<InventoryDetailsDto> getInventoryDtl() {
		return inventoryDtl;
	}

	public void setInventoryDtl(ArrayList<InventoryDetailsDto> inventoryDtl) {
		this.inventoryDtl = inventoryDtl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
