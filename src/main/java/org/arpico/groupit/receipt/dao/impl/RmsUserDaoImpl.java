package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.arpico.groupit.receipt.dao.RmsUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class RmsUserDaoImpl implements RmsUserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getLocation(String userCode) throws Exception {
		String userLocList = null;
		
		userLocList = jdbcTemplate.query("SELECT LOC_CODE FROM rms_users where USER_ID = '"+userCode+"' and SBU_CODE='450' and active=1 ", new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				String userLocListTemp = null;
				if(rs.next()) {
					
					return rs.getString("LOC_CODE");
				}
				return userLocListTemp;
			}
		});
		return userLocList;
	}
	
	@Override
	public String getName(String userCode) throws Exception {
		String userLocList = null;
		
		userLocList = jdbcTemplate.query("SELECT USER_NAME FROM rms_users where USER_ID = '"+userCode+"' and SBU_CODE='450' and active=1 ", new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				String userLocListTemp = null;
				if(rs.next()) {
					
					return rs.getString("USER_NAME");
				}
				return userLocListTemp;
			}
		});
		return userLocList;
	}
	

}
