package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;

public class QuoChildBenefDto {
	private ChildDto child;
	private ArrayList<QuoBenfDto> benfs;
	public ChildDto getChild() {
		return child;
	}
	public void setChild(ChildDto child) {
		this.child = child;
	}
	public ArrayList<QuoBenfDto> getBenfs() {
		return benfs;
	}
	public void setBenfs(ArrayList<QuoBenfDto> benfs) {
		this.benfs = benfs;
	}
	
	

}
