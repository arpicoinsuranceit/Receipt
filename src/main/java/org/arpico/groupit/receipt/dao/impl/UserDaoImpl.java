package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<String> getUserLocations(String userCode) throws Exception {

		List<String> location = jdbcTemplate
				.query("select r.LOC_CODE from rms_users r where SBU_CODE = '450' and USER_ID = '" + userCode + "' "
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

		if (location == null) {

			location = jdbcTemplate.query(
					"SELECT IF(ac.frmval = 'AAA' AND tovalu = 'ZZZ', ac.vlsta, ac.frmval) dashpara FROM smaccesscontrol ac inner join rms_users u on ac.sbucod=u.SBU_CODE and ac.userid=u.USER_ID WHERE "
							+ "ac.sbucod = '450' AND ac.userid = '" + userCode
							+ "' and u.active='1' order by CRE_DATE DESC limit 1",
					new ResultSetExtractor<List<String>>() {
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
		}

		return location;
	}

	@Override
	public String getUserEmail(String userCode) throws Exception {
		String email = jdbcTemplate
				.queryForObject("select r.EMAIL from rms_users r where SBU_CODE = '450' and USER_ID = '" + userCode
						+ "' " + "and active = 1 ", String.class);

		return email;
	}

	@Override
	public String getUserFullName(String userCode) throws Exception {
		String name = jdbcTemplate
				.queryForObject("select r.USER_NAME from rms_users r where SBU_CODE = '450' and USER_ID = '" + userCode
						+ "' " + "and active = 1 limit 1", String.class);

		return name;
	}

}
