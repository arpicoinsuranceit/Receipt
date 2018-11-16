package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.BranchDao;
import org.arpico.groupit.receipt.dao.rowmapper.BranchRowMapper;
import org.arpico.groupit.receipt.model.BranchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDaoImpl implements BranchDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BranchModel> getBranchs(String userCode) throws Exception {
		
		String query = "select l.loc_code, l.loc_name from rms_locations l inner join rms_users u on l.sbu_code=u.sbu_code and u.loc_code = l.loc_code\r\n" + 
				"    where u.sbu_code='450' and u.user_id = '"+userCode+"' and u.active='1'";
		
		List<BranchModel> branchModels = jdbcTemplate.query(query, new BranchRowMapper());
		return branchModels;
	}

	@Override
	public List<BranchModel> getAllBranchs() throws Exception {
		List<BranchModel> branchModels = jdbcTemplate.query("SELECT l.loc_code, l.loc_name FROM rms_locations l where l.sbucod='450' ", new BranchRowMapper());
		return branchModels;
	}
	
	@Override
	public String getBranchName(String branchCode) throws Exception {
		String brnCode = jdbcTemplate
				.queryForObject("select r.locnam from rms_locations r where SBU_CODE = '450' and loccod = '" + branchCode
						+ "' ", String.class);
		
		return brnCode;
	}

}
