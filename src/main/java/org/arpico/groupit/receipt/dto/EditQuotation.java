package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;

public class EditQuotation {
	private MainLife _mainlife;
	private Spouse _spouse;
	private ArrayList<Children> _children;
	private Plan _plan;
	private ArrayList<QuoBenf> _mainLifeBenefits;
	private ArrayList<QuoBenf> _spouseBenefits;
	private ArrayList<QuoBenf> _childrenBenefits;
	
	public MainLife get_mainlife() {
		return _mainlife;
	}
	public void set_mainlife(MainLife _mainlife) {
		this._mainlife = _mainlife;
	}
	public Spouse get_spouse() {
		return _spouse;
	}
	public void set_spouse(Spouse _spouse) {
		this._spouse = _spouse;
	}
	public ArrayList<Children> get_children() {
		return _children;
	}
	public void set_children(ArrayList<Children> _children) {
		this._children = _children;
	}
	public Plan get_plan() {
		return _plan;
	}
	public void set_plan(Plan _plan) {
		this._plan = _plan;
	}
	public ArrayList<QuoBenf> get_mainLifeBenefits() {
		return _mainLifeBenefits;
	}
	public void set_mainLifeBenefits(ArrayList<QuoBenf> _mainLifeBenefits) {
		this._mainLifeBenefits = _mainLifeBenefits;
	}
	public ArrayList<QuoBenf> get_spouseBenefits() {
		return _spouseBenefits;
	}
	public void set_spouseBenefits(ArrayList<QuoBenf> _spouseBenefits) {
		this._spouseBenefits = _spouseBenefits;
	}
	public ArrayList<QuoBenf> get_childrenBenefits() {
		return _childrenBenefits;
	}
	public void set_childrenBenefits(ArrayList<QuoBenf> _childrenBenefits) {
		this._childrenBenefits = _childrenBenefits;
	}
	
	
	
	
}
