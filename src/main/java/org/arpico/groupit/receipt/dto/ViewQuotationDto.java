package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;

public class ViewQuotationDto {
	private String productCode;
	private String productName;
	private Integer quoDetailId;
	private Integer quotationId;
	private String quotationDate;
	private MainLifeDto _mainlife;
	private SpouseDto _spouse;
	private ArrayList<ChildrenDto> _children;
	private PlanDto _plan;
	private ArrayList<QuoBenfDto> _mainLifeBenefits;
	private ArrayList<QuoBenfDto> _spouseBenefits;
	private ArrayList<QuoChildBenefDto> _childrenBenefits;
	
	public MainLifeDto get_mainlife() {
		return _mainlife;
	}
	public void set_mainlife(MainLifeDto _mainlife) {
		this._mainlife = _mainlife;
	}
	public SpouseDto get_spouse() {
		return _spouse;
	}
	public void set_spouse(SpouseDto _spouse) {
		this._spouse = _spouse;
	}
	public ArrayList<ChildrenDto> get_children() {
		return _children;
	}
	public void set_children(ArrayList<ChildrenDto> _children) {
		this._children = _children;
	}
	public PlanDto get_plan() {
		return _plan;
	}
	public void set_plan(PlanDto _plan) {
		this._plan = _plan;
	}
	public ArrayList<QuoBenfDto> get_mainLifeBenefits() {
		return _mainLifeBenefits;
	}
	public void set_mainLifeBenefits(ArrayList<QuoBenfDto> _mainLifeBenefits) {
		this._mainLifeBenefits = _mainLifeBenefits;
	}
	public ArrayList<QuoBenfDto> get_spouseBenefits() {
		return _spouseBenefits;
	}
	public void set_spouseBenefits(ArrayList<QuoBenfDto> _spouseBenefits) {
		this._spouseBenefits = _spouseBenefits;
	}
	public ArrayList<QuoChildBenefDto> get_childrenBenefits() {
		return _childrenBenefits;
	}
	public void set_childrenBenefits(ArrayList<QuoChildBenefDto> _childrenBenefits) {
		this._childrenBenefits = _childrenBenefits;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getQuoDetailId() {
		return quoDetailId;
	}
	public void setQuoDetailId(Integer quoDetailId) {
		this.quoDetailId = quoDetailId;
	}
	public String getQuotationDate() {
		return quotationDate;
	}
	public void setQuotationDate(String quotationDate) {
		this.quotationDate = quotationDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	}
	@Override
	public String toString() {
		return "ViewQuotationDto [productCode=" + productCode + ", productName=" + productName + ", quoDetailId="
				+ quoDetailId + ", quotationId=" + quotationId + ", quotationDate=" + quotationDate + ", _mainlife="
				+ _mainlife + ", _spouse=" + _spouse + ", _children=" + _children + ", _plan=" + _plan
				+ ", _mainLifeBenefits=" + _mainLifeBenefits + ", _spouseBenefits=" + _spouseBenefits
				+ ", _childrenBenefits=" + _childrenBenefits + "]";
	}
	
	
	
	
}
