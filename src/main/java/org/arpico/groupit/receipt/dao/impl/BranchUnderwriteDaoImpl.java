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
	public List<InProposalUnderwriteModel> findProposalToUnderwrite(String locodes, Integer limit, Integer offset,
			boolean isHO) {
		
		if(isHO) {
			return jdbcTemplate.query("select p.pprnum,p.prpseq,p.polnum,p.cscode,p.ppdnam,p.advcod, p.loccod as brncod,p.loccod,p.ppdnic,p.prdcod "
					+ " FROM inproposals p inner join inagentmast a on p.sbucod = a.sbucod and p.advcod = a.agncod"
					+ " where  p.sbucod = '450'  and p.pprsta = 'L0' order by p.creadt desc LIMIT "+limit+" OFFSET "+offset+";",
					new InProposalUnderwriteRowMapper());
		}else {
			return jdbcTemplate.query("select p.pprnum,p.prpseq,p.polnum,p.cscode,p.ppdnam,p.advcod, p.loccod as brncod,p.loccod,p.ppdnic,p.prdcod "
					+ "FROM inproposals p inner join inagentmast a on p.sbucod = a.sbucod and p.advcod = a.agncod"
					+ " where p.sbucod = '450' and p.loccod in ("+locodes+") and  p.pprsta = 'L0' order by p.creadt desc LIMIT "+limit+" OFFSET "+offset+";",
					new InProposalUnderwriteRowMapper());
		}
		
		
		
	}

	@Override
	public Integer findProposalCount(boolean isHO,String locodes) {
		Integer propCount = null;
		if(isHO) {
			propCount = jdbcTemplate.queryForObject("select count(*) FROM inproposals where sbucod = '450' and pprsta = 'L0' ", Integer.class);
			
			return propCount;
		}else {
			propCount = jdbcTemplate.queryForObject("select count(*) FROM inproposals where sbucod = '450' and loccod in ("+locodes+") and pprsta = 'L0' ", Integer.class);
			
			return propCount;
		}
	}

	@Override
	public List<String> findLocCodesZonalBranch(String zoneCodes) {
		List<String> userLocList = null;
		
		userLocList = jdbcTemplate.query("select l.loccod from rms_locations l inner join inregion r on l.sbucod=r.sbucod and l.rgncod=r.rgncod  " + 
				"where r.sbucod='450' and r.zoncod in ("+zoneCodes+") ", new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> userLocListTemp = new ArrayList<>();
				while(rs.next()) {
					String loccode=rs.getString("loccod");
					userLocListTemp.add(loccode);
				}
				return userLocListTemp;
			}
		});
		
		return userLocList;
	}
	

}
