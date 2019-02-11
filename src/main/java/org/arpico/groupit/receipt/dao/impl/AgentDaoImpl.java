package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.rowmapper.AgentFullRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.AgentMasterDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.AgentRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.AgnInqAgnListRowMapper;
import org.arpico.groupit.receipt.model.AgentMasterDetailsModel;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.AgnInqAgnListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgentDaoImpl implements AgentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AgentModel> findAgentLikeAgentCode(Integer agentCode, String sql) throws Exception {

		String query = "SELECT agncod, shrtnm, loccod FROM inagentmast where sbucod = '450' " + sql
				+ " and agncod like '" + agentCode + "%' and agnsta IN ('ACT','INA') order by agncod";

		 System.out.println(query);

		return jdbcTemplate.query(query, new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByCode(String agentCode) {
		return jdbcTemplate
				.query(" SELECT agncod, shrtnm, loccod FROM inagentmast " + "    where sbucod = '450' and agncod = '"
						+ agentCode + "' and agnsta IN ('ACT','INA') " + "    order by agncod", new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByCodeAll(String advcod) throws Exception {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "    where sbucod = '450' and agncod = '" + advcod + "' order by agncod", new AgentRowMapper());
	}

	@Override
	public AgentModel findPropAgent(String agentCode) throws Exception {
		return jdbcTemplate.queryForObject("SELECT agncod, prnnam, loccod ,agncls FROM inagentmast "
				+ "where sbucod = '450' and agncod = '" + agentCode + "' ;", new AgentFullRowMapper());
	}

	@Override
	public List<AgentModel> getAllAgents() throws Exception {
		return jdbcTemplate.query("SELECT agncod, prnnam, loccod ,agncls FROM inagentmast "
				+ "where sbucod = '450' and agnsta in ('ACT','INA')", new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByLocations(String locCodes) throws Exception {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "where sbucod = '450' and loccod in  (" + locCodes + ") and agnsta in ('ACT','INA') order by agncod;",
				new AgentRowMapper());
	}

	@Override
	public List<AgnInqAgnListModel> getAgnInqList(String locCodes, Integer page, Integer offset,String sql) throws Exception {
		return jdbcTemplate.query("select agncod, agnnam, agnnic, agnsta, sliirg, "
				+ "if(agncls = 'IC', unlcod, bancod) as supvid, agndob, subdcd, agnrdt "
				+ "from inagentmast where sbucod = '450' "+ sql+ " and loccod in (" + locCodes + ") limit " + page + ", " + offset
				+ ";", new AgnInqAgnListRowMapper());
	}

	@Override
	public Integer getAgnInqListCount(String locCodes,String sql) throws Exception {
		return jdbcTemplate.queryForObject(
				"select count(*)  from inagentmast where sbucod = '450' and loccod in (" + locCodes + ") "+ sql,
				Integer.class);
	}

	@Override
	public AgentMasterDetailsModel getAgentMasterdetails(Integer agnCode) throws Exception {
		return jdbcTemplate.queryForObject(
				"select a.agncod, concat(a.loccod, ' | ' , l.loc_name) as loccod , concat(r.rgncod, ' | ' , r.rgnnam) as rgncod, "
						+ "concat(z.zoncod , ' | ', z.zonnam) as zoncod, a.agntit, a.agnnam, a.midnam, a.lasnam, a.shrtnm, a.agnsta, a.agnsex, "
						+ "a.agnrdt, a.agnrem, a.agnlcm as grntsta , a.appdat, a.orcrem, a.agncls as descignation , if(a.subdcd = 'IC', a.unlcod, a.bancod) as superisor,  "
						+ "a.misapp, a.maprm, a.subtyp as agnnature , a.agnnic, a.agnepf, a.sliirg,a.agnmst, a.agndob, a.agnorc, 'not set yest' as type, a.subdcd, a.cntper, "
						+ "a.cntofn, a.cntrsn, a.cnttlx, a.cntfax, a.cntmob, a.cnteml, a.agnad1,a.agnad2,a.agncty, a.agnofn, a.agnrsn, a.agntlx, a.agnfax, "
						+ "a.agnmob, a.agnweb, a.agneml, a.efcdat ,(select creaby usr from smeditlogs l where l.sbucod=a.sbucod and l.logkey=a.agncod and l.creaby <> a.creaby order by creadt desc "  
						+ "limit 1)  entusr, (select creaby appusr from smeditlogs l where l.sbucod=a.sbucod and l.logkey=a.agncod and l.creaby = a.creaby order by creadt desc "  
						+ "limit 1)  appusr from inagentmast a "
						+ "	inner join rms_locations l on a.sbucod = l.sbu_code and a.loccod = l.loc_code  "
						+ "    inner join inregion r on l.sbu_code = r.sbucod and l.rgncod = r.rgncod "
						+ "    inner join inzonemast z on r.sbucod = z.sbucod and r.zoncod = z.zoncod "
						+ "    where a.sbucod = 450 and a.agncod = '" + agnCode + "'",
				new AgentMasterDetailsRowMapper());
	}

}
