package org.arpico.groupit.receipt.dao;

import java.util.List;

public interface RmsUserDao {

	List<String> getLocation (String userCode) throws Exception;
	
	String getName (String userCode) throws Exception;
}
