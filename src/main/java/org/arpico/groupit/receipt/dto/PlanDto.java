package org.arpico.groupit.receipt.dto;

public class PlanDto {
	private Integer _term;
	private String _frequance;
	private Double _bsa;
	private Double contribution;
	private Double _interestRate;
	private String _payingterm;
	private Double _msfb;
	private Double _bsaTotal;
	private String _nomineeName;
	private String _nomineedob;
	private Integer _nomineeAge;
	private String _nomoneeRelation;
	private Integer pensionPaingTerm;
	private Integer retAge;
	private Integer age;
	private Double policyFee;
	private Double adminFee;
	private Double tax;
	private Double grsprm;
	private Double invPos;
	private Double lifePos;
	private Double sumatRiskMain;
	private Double sumatRiskSpouse;
	
	
	public Integer get_term() {
		return _term;
	}
	public void set_term(Integer _term) {
		this._term = _term;
	}
	public String get_frequance() {
		return _frequance;
	}
	public void set_frequance(String _frequance) {
		this._frequance = _frequance;
	}
	public Double get_bsa() {
		return _bsa;
	}
	public void set_bsa(Double _bsa) {
		this._bsa = _bsa;
	}
	public Double getContribution() {
		return contribution;
	}
	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}
	public Double get_interestRate() {
		return _interestRate;
	}
	public void set_interestRate(Double _intrate) {
		this._interestRate = _intrate;
	}
	public String get_payingterm() {
		return _payingterm;
	}
	public void set_payingterm(String _payingterm) {
		this._payingterm = _payingterm;
	}
	public Double get_msfb() {
		return _msfb;
	}
	public void set_msfb(Double _msfb) {
		this._msfb = _msfb;
	}
	public Double get_bsaTotal() {
		return _bsaTotal;
	}
	public void set_bsaTotal(Double _bsaTotal) {
		this._bsaTotal = _bsaTotal;
	}
	public String get_nomineeName() {
		return _nomineeName;
	}
	public void set_nomineeName(String _nomineeName) {
		this._nomineeName = _nomineeName;
	}
	public String get_nomineedob() {
		return _nomineedob;
	}
	public void set_nomineedob(String _nomineedob) {
		this._nomineedob = _nomineedob;
	}
	public Integer get_nomineeAge() {
		return _nomineeAge;
	}
	public void set_nomineeAge(Integer _nomineeAge) {
		this._nomineeAge = _nomineeAge;
	}
	public String get_nomoneeRelation() {
		return _nomoneeRelation;
	}
	public void set_nomoneeRelation(String _nomoneeRelation) {
		this._nomoneeRelation = _nomoneeRelation;
	}
	public Integer getPensionPaingTerm() {
		return pensionPaingTerm;
	}
	public void setPensionPaingTerm(Integer pensionPaingTerm) {
		this.pensionPaingTerm = pensionPaingTerm;
	}
	public Integer getRetAge() {
		return retAge;
	}
	public void setRetAge(Integer retAge) {
		this.retAge = retAge;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getPolicyFee() {
		return policyFee;
	}
	public void setPolicyFee(Double policyFee) {
		this.policyFee = policyFee;
	}
	public Double getAdminFee() {
		return adminFee;
	}
	public void setAdminFee(Double adminFee) {
		this.adminFee = adminFee;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getGrsprm() {
		return grsprm;
	}
	public void setGrsprm(Double grsprm) {
		this.grsprm = grsprm;
	}
	public Double getInvPos() {
		return invPos;
	}
	public void setInvPos(Double invPos) {
		this.invPos = invPos;
	}
	public Double getLifePos() {
		return lifePos;
	}
	public void setLifePos(Double lifePos) {
		this.lifePos = lifePos;
	}
	public Double getSumatRiskMain() {
		return sumatRiskMain;
	}
	public void setSumatRiskMain(Double sumatRiskMain) {
		this.sumatRiskMain = sumatRiskMain;
	}
	public Double getSumatRiskSpouse() {
		return sumatRiskSpouse;
	}
	public void setSumatRiskSpouse(Double sumatRiskSpouse) {
		this.sumatRiskSpouse = sumatRiskSpouse;
	}
	@Override
	public String toString() {
		return "PlanDto [_term=" + _term + ", _frequance=" + _frequance + ", _bsa=" + _bsa + ", contribution="
				+ contribution + ", _interestRate=" + _interestRate + ", _payingterm=" + _payingterm + ", _msfb="
				+ _msfb + ", _bsaTotal=" + _bsaTotal + ", _nomineeName=" + _nomineeName + ", _nomineedob=" + _nomineedob
				+ ", _nomineeAge=" + _nomineeAge + ", _nomoneeRelation=" + _nomoneeRelation + ", pensionPaingTerm="
				+ pensionPaingTerm + ", retAge=" + retAge + ", age=" + age + ", policyFee=" + policyFee + ", adminFee="
				+ adminFee + ", tax=" + tax + ", grsprm=" + grsprm + ", invPos=" + invPos + ", lifePos=" + lifePos
				+ ", sumatRiskMain=" + sumatRiskMain + ", sumatRiskSpouse=" + sumatRiskSpouse + "]";
	}
}
