package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.AgentMasterDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class AgentMasterDetailsRowMapper implements RowMapper<AgentMasterDetailsModel> {

	@Override
	public AgentMasterDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgentMasterDetailsModel model = new AgentMasterDetailsModel();

		model.setAgnad1(rs.getString("agnad1"));
		model.setAgnad2(rs.getString("agnad2"));
		model.setAgncod(rs.getInt("agncod"));
		model.setAgncty(rs.getString("agncty"));
		model.setAgndob(rs.getDate("agndob"));
		model.setAgneml(rs.getString("agneml"));
		model.setAgnepf(rs.getString("agnepf"));
		model.setAgnfax(rs.getString("agnfax"));
		model.setAgnmob(rs.getString("agnmob"));
		model.setAgnmst(rs.getString("agnmst"));
		model.setAgnnam(rs.getString("agnnam"));
		model.setAgnnature(rs.getString("agnnature"));
		model.setAgnnic(rs.getString("agnnic"));
		model.setAgnofn(rs.getString("agnofn"));
		model.setAgnorc(rs.getString("agnorc"));
		model.setAgnrdt(rs.getDate("agnrdt"));
		model.setAgnrem(rs.getString("agnrem"));
		model.setAgnrsn(rs.getString("agnrsn"));
		model.setAgnsex(rs.getString("agnsex"));
		model.setAgnsta(rs.getString("agnsta"));
		model.setAgntit(rs.getString("agntit"));
		model.setAgntlx(rs.getString("agntlx"));
		model.setAgnweb(rs.getString("agnweb"));
		model.setAppdat(rs.getDate("appdat"));
		model.setCnteml(rs.getString("cnteml"));
		model.setCntfax(rs.getString("cntfax"));
		model.setCntmob(rs.getString("cntmob"));
		model.setCntofn(rs.getString("cntofn"));
		model.setCntper(rs.getString("cntper"));
		model.setCntrsn(rs.getString("cntrsn"));
		model.setCnttlx(rs.getString("cnttlx"));
		model.setDescignation(rs.getString("descignation"));
		model.setEfcdat(rs.getDate("efcdat"));
		model.setGrntsta(rs.getString("grntsta"));
		model.setLasnam(rs.getString("lasnam"));
		model.setLoccod(rs.getString("loccod"));
		model.setMaprm(rs.getString("maprm"));
		model.setMidnam(rs.getString("midnam"));
		model.setMisapp(rs.getString("misapp"));
		model.setOrcrem(rs.getString("orcrem"));
		model.setRgncod(rs.getString("rgncod"));
		model.setShrtnm(rs.getString("shrtnm"));
		model.setSliirg(rs.getString("sliirg"));
		model.setSubdcd(rs.getString("subdcd"));
		model.setSuperisor(rs.getString("superisor"));
		model.setType(rs.getString("type"));
		model.setZoncod(rs.getString("zoncod"));
		model.setAppBy(rs.getString("appusr"));
		model.setEntBy(rs.getString("entusr"));
		
		return model;
	}

}
