package org.arpico.groupit.receipt.dao;

import java.util.List;

public interface UserDao {

	public List<String> getUserLocations(String userCode) throws Exception;
	
	public String getUserEmail(String userCode) throws Exception;

	String getUserFullName(String userCode) throws Exception;
	
}
