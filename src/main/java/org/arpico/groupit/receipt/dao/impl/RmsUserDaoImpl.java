package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public List<String> getLocation(String userCode) throws Exception {
		//String userLocList = null;
		
		List<String> location = jdbcTemplate.query("select r.LOC_CODE from rms_users r where SBU_CODE = '450' and USER_ID = '" + userCode + "' "
				+ "and active = 1", new ResultSetExtractor<List<String>>() {
			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> userLocListTemp = new ArrayList<>();
				while (rs.next()) {
					String loccode = rs.getString("LOC_CODE");
					userLocListTemp.add(loccode);
				}
				return userLocListTemp;
			}
		});
		return location;
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
