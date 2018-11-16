package org.arpico.groupit.receipt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.RmsItemMasterCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.RmsItemmasterRowMapper;
import org.arpico.groupit.receipt.model.RmsItemMasterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RmsItemMasterCustomDaoImpl implements RmsItemMasterCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public RmsItemMasterModel findbyId(String itemCode) throws Exception {

		List<Object> args = new ArrayList<>();
		args.add(itemCode);

		String sql = "select ITEM_CODE, PLU_CODE, ITM_DESC, UNIT, UNIT_PRICE, AVG_PRICE, glgrup, ITM_GROUP from rms_itmmaster "
				+ "where SBU_CODE = '450' and ITEM_CODE = '"
				+ itemCode + "' limit 1";

		RmsItemMasterModel itemMasterModel = jdbcTemplate.queryForObject(sql, new RmsItemmasterRowMapper());
		return itemMasterModel;
	}

}
