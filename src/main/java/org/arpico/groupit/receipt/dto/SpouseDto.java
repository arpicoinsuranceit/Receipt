package org.arpico.groupit.receipt.dto;

public class SpouseDto {
	
	private boolean _sActive;
	private String _sTitle;
	private String _sName;
	private String _sNic;
	private String _sGender;
	private String _sDob;
	private String _sAge;
	private String _sSmoking;
	private String _sOccupation;
	private String _sCustCode;
	private String occuCode;
	
	public boolean is_sActive() {
		return _sActive;
	}
	public void set_sActive(boolean _sActive) {
		this._sActive = _sActive;
	}
	public String get_sTitle() {
		return _sTitle;
	}
	public void set_sTitle(String _sTitle) {
		this._sTitle = _sTitle;
	}
	public String get_sName() {
		return _sName;
	}
	public void set_sName(String _sName) {
		this._sName = _sName;
	}
	public String get_sNic() {
		return _sNic;
	}
	public void set_sNic(String _sNic) {
		this._sNic = _sNic;
	}
	public String get_sGender() {
		return _sGender;
	}
	public void set_sGender(String _sGender) {
		this._sGender = _sGender;
	}
	public String get_sDob() {
		return _sDob;
	}
	public void set_sDob(String _sDob) {
		this._sDob = _sDob;
	}
	public String get_sAge() {
		return _sAge;
	}
	public void set_sAge(String _sAge) {
		this._sAge = _sAge;
	}
	public String get_sSmoking() {
		return _sSmoking;
	}
	public void set_sSmoking(String _sSmoking) {
		this._sSmoking = _sSmoking;
	}
	public String get_sOccupation() {
		return _sOccupation;
	}
	public void set_sOccupation(String _sOccupation) {
		this._sOccupation = _sOccupation;
	}
	public String get_sCustCode() {
		return _sCustCode;
	}
	public void set_sCustCode(String _sCustCode) {
		this._sCustCode = _sCustCode;
	}
	public String getOccuCode() {
		return occuCode;
	}
	public void setOccuCode(String occuCode) {
		this.occuCode = occuCode;
	}
	
	@Override
	public String toString() {
		return "SpouseDto [_sActive=" + _sActive + ", _sTitle=" + _sTitle + ", _sName=" + _sName + ", _sNic=" + _sNic
				+ ", _sGender=" + _sGender + ", _sDob=" + _sDob + ", _sAge=" + _sAge + ", _sSmoking=" + _sSmoking
				+ ", _sOccupation=" + _sOccupation + ", _sCustCode=" + _sCustCode + ", occuCode=" + occuCode + "]";
	}
}
