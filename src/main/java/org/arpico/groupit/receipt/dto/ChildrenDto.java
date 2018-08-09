package org.arpico.groupit.receipt.dto;

public class ChildrenDto {
	private boolean _cActive;
	private String _cName;
	private String _cDob;
	private Integer _cAge;
	private String _cNic;
	private String _cTitle;
	private boolean _cSuhrbc;
	private boolean _cCibc;
	//private boolean _cHrbc;
	private boolean _cHbc;
	private boolean _cHrbic;
	private boolean _cHrbfc;
	
	public boolean is_cActive() {
		return _cActive;
	}
	public void set_cActive(boolean _cActive) {
		this._cActive = _cActive;
	}
	public String get_cName() {
		return _cName;
	}
	public void set_cName(String _cName) {
		this._cName = _cName;
	}
	public String get_cDob() {
		return _cDob;
	}
	public void set_cDob(String _cDob) {
		this._cDob = _cDob;
	}
	public Integer get_cAge() {
		return _cAge;
	}
	public void set_cAge(Integer _cAge) {
		this._cAge = _cAge;
	}
	public String get_cNic() {
		return _cNic;
	}
	public void set_cNic(String _cNic) {
		this._cNic = _cNic;
	}
	public String get_cTitle() {
		return _cTitle;
	}
	public void set_cTitle(String _cTitle) {
		this._cTitle = _cTitle;
	}
	public boolean is_cSuhrbc() {
		return _cSuhrbc;
	}
	public void set_cSuhrbc(boolean _cSuhrbc) {
		this._cSuhrbc = _cSuhrbc;
	}
	public boolean is_cCibc() {
		return _cCibc;
	}
	public void set_cCibc(boolean _cCibc) {
		this._cCibc = _cCibc;
	}
	/*public boolean is_cHrbc() {
		return _cHrbc;
	}
	public void set_cHrbc(boolean _cHrbc) {
		this._cHrbc = _cHrbc;
	}
	*/
	public boolean is_cHbc() {
		return _cHbc;
	}
	public void set_cHbc(boolean _cHbc) {
		this._cHbc = _cHbc;
	}
	
	public boolean is_cHrbic() {
		return _cHrbic;
	}
	public void set_cHrbic(boolean _cHrbic) {
		this._cHrbic = _cHrbic;
	}
	public boolean is_cHrbfc() {
		return _cHrbfc;
	}
	public void set_cHrbfc(boolean _cHrbfc) {
		this._cHrbfc = _cHrbfc;
	}
	@Override
	public String toString() {
		return "ChildrenDto [_cActive=" + _cActive + ", _cName=" + _cName + ", _cDob=" + _cDob + ", _cAge=" + _cAge
				+ ", _cNic=" + _cNic + ", _cTitle=" + _cTitle + ", _cSuhrbc=" + _cSuhrbc + ", _cCibc=" + _cCibc
				+ ", _cHbc=" + _cHbc + ", _cHrbic=" + _cHrbic + ", _cHrbfc=" + _cHrbfc + "]";
	}
	
}
