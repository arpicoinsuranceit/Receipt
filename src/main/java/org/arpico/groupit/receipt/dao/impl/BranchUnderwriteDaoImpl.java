package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.rowmapper.InProposalUnderwriteRowMapper;
import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class BranchUnderwriteDaoImpl implements BranchUnderwriteDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<String> findLocCodes(String usercode) {
		List<String> userLocList = null;
		
		userLocList = jdbcTemplate.query("SELECT LOC_CODE FROM rms_users where USER_ID = '"+usercode+"' and SBU_CODE='450' and active='1'", new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> userLocListTemp = new ArrayList<>();
				while(rs.next()) {
					String loccode=rs.getString("LOC_CODE");
					userLocListTemp.add(loccode);
				}
				return userLocListTemp;
			}
		});
		
		return userLocList;
	}

	@Override
	public List<InProposalUnderwriteModel> findProposalToUnderwrite(String locodes) {
		return jdbcTemplate.query("select pprnum,prpseq,polnum,cscode,ppdnam,advcod,brncod,loccod,ppdnic FROM inproposals where pprsta = 'L0' and sbucod = '450' and loccod in ("+locodes+") order by creadt desc;",
				new InProposalUnderwriteRowMapper());
	}

}