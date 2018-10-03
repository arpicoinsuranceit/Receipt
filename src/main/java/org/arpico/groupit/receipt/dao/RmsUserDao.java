package org.arpico.groupit.receipt.dao;

public interface RmsUserDao {

	String getLocation (String userCode) throws Exception;
	
	String getName (String userCode) throws Exception;
}
