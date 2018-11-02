package org.arpico.groupit.receipt.dto;

import java.util.ArrayList;

public class SubDepartmentHelperDto {
	
	private String sudDepName;
	private ArrayList<SubDepartmentDocumentCourierHelperDto> subDepartmentDocumentCourierDtos;
	
	public String getSudDepName() {
		return sudDepName;
	}
	public void setSudDepName(String sudDepName) {
		this.sudDepName = sudDepName;
	}
	public ArrayList<SubDepartmentDocumentCourierHelperDto> getSubDepartmentDocumentCourierDtos() {
		return subDepartmentDocumentCourierDtos;
	}
	public void setSubDepartmentDocumentCourierDtos(
			ArrayList<SubDepartmentDocumentCourierHelperDto> subDepartmentDocumentCourierDtos) {
		this.subDepartmentDocumentCourierDtos = subDepartmentDocumentCourierDtos;
	}
	@Override
	public String toString() {
		return "SubDepartmentHelperDto [sudDepName=" + sudDepName + ", subDepartmentDocumentCourierDtos="
				+ subDepartmentDocumentCourierDtos + "]";
	}
	
	
	

}
