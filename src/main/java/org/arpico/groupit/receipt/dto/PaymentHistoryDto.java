package org.arpico.groupit.receipt.dto;

import java.util.Date;

public class PaymentHistoryDto {
	private Integer txnyer;
	private Integer txnmth;
	private Date txndat;
	private Double setamt;
	private Double dueamt;
	private Date duedat;
	private Double outstd;
	private String remark;

	public Integer getTxnyer() {
		return txnyer;
	}

	public void setTxnyer(Integer txnyer) {
		this.txnyer = txnyer;
	}

	public Integer getTxnmth() {
		return txnmth;
	}

	public void setTxnmth(Integer txnmth) {
		this.txnmth = txnmth;
	}

	public Date getTxndat() {
		return txndat;
	}

	public void setTxndat(Date txndat) {
		this.txndat = txndat;
	}

	public Double getSetamt() {
		return setamt;
	}

	public void setSetamt(Double setamt) {
		this.setamt = setamt;
	}

	public Double getDueamt() {
		return dueamt;
	}

	public void setDueamt(Double dueamt) {
		this.dueamt = dueamt;
	}

	public Date getDuedat() {
		return duedat;
	}

	public void setDuedat(Date duedat) {
		this.duedat = duedat;
	}

	public Double getOutstd() {
		return outstd;
	}

	public void setOutstd(Double outstd) {
		this.outstd = outstd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PaymentHistoryModel [txnyer=" + txnyer + ", txnmth=" + txnmth + ", txndat=" + txndat + ", setamt="
				+ setamt + ", dueamt=" + dueamt + ", duedat=" + duedat + ", outstd=" + outstd + ", remark=" + remark
				+ "]";
	}
}
