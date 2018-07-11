package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	public String getLocation(String userCode) throws Exception {
		String userLocList = null;
		
		userLocList = jdbcTemplate.query("SELECT LOC_CODE FROM marksys.rms_users where USER_ID = '"+userCode+"'", new ResultSetExtractor<String>() {

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

}
