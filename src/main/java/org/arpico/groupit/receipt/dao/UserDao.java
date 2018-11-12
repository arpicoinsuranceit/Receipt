package org.arpico.groupit.receipt.dao;

public interface UserDao {

	public String getUserLocations(String userCode) throws Exception;
	
	public String getUserEmail(String userCode) throws Exception;

	String getUserFullName(String userCode) throws Exception;
	
}
