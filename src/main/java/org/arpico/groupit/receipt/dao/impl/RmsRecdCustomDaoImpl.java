package org.arpico.groupit.receipt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.arpico.groupit.receipt.dao.RmsRecdCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.MisGlItemRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.RmsDocTxndRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.RmsRecdRowMapper;
import org.arpico.groupit.receipt.model.MisGlItemModel;
import org.arpico.groupit.receipt.model.RmsDocTxndModel;
import org.arpico.groupit.receipt.model.RmsRecdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class RmsRecdCustomDaoImpl implements RmsRecdCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<MisGlItemModel> glItemModels(String docCode, Integer docNo) throws Exception {
//		return jdbcTemplate.query("select t.DOCNUM, t.AMOUNT, t.REMARK, c.DESCRI, t.INTERID, t.DIMM04 "
//				+ "from gltrantemp t, glcharofaccs c where t.INTERID = c.INTERID and " + "t.DOCCOD = '" + docCode
//				+ "' and t.DOCNUM = '" + docNo + "'", new MisGlItemRowMapper());

		return jdbcTemplate.query(
				"select t.DOCNUM, t.AMOUNT, t.REMARK, c.DESCRI, t.INTERID, t.DIMM04 from gltrantemp t inner join glcharofaccs c "
						+ "	on  t.SBUCOD=c.SBUCOD and t.LOCCOD=c.LOCCOD and t.INTERID = c.INTERID  where t.SBUCOD='450' and  t.DOCCOD = '"
						+ docCode + "' and t.DOCNUM = '" + docNo + "'",
				new MisGlItemRowMapper());
	}

	@Override
	public String getPayMode(String docCode, Integer docNo) throws Exception {
		String paymode = null;

		paymode = jdbcTemplate.query(
				"select PAY_MODE from rms_recd where SBU_CODE='450' and  DOC_CODE = '" + docCode + "' and DOC_NO = '" + docNo + "' limit 1",
				new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
						String paymode = "";
						if (rs.next()) {
							paymode = rs.getString("PAY_MODE");
						}
						return paymode;
					}
				});

		return paymode;
	}
	
	@Override
	public List<RmsRecdModel> getRmsRecdModels(String docCode, Integer docNum) throws Exception {
		List<RmsRecdModel> models = jdbcTemplate.query("select * from rms_recd WHERE SBU_CODE = '450' and DOC_CODE = '"+docCode+"' and DOC_NO = '"+docNum+"'", new RmsRecdRowMapper());
		return models;
	}

}
