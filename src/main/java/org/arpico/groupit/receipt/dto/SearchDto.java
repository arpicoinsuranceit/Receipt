package org.arpico.groupit.receipt.dto;

public class SearchDto {

	private String custName;
	private String nic;
	private String polnum;
	private String pprnum;
	private String quonum;
	private String product;
	private Integer seqNum;

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getPolnum() {
		return polnum;
	}

	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}

	public String getPprnum() {
		return pprnum;
	}

	public void setPprnum(String pprnum) {
		this.pprnum = pprnum;
	}

	public String getQuonum() {
		return quonum;
	}

	public void setQuonum(String quonum) {
		this.quonum = quonum;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	@Override
	public String toString() {
		return "SearchDto [custName=" + custName + ", nic=" + nic + ", polnum=" + polnum + ", pprnum=" + pprnum
				+ ", quonum=" + quonum + ", product=" + product + ", seqNum=" + seqNum + "]";
	}
}
