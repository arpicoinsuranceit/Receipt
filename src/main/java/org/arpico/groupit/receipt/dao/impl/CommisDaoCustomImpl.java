package org.arpico.groupit.receipt.dao.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.CommisDaoCustom;
import org.arpico.groupit.receipt.dao.rowmapper.CommisRowMapper;
import org.arpico.groupit.receipt.model.CommisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommisDaoCustomImpl implements CommisDaoCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CommisModel getCommis(Integer comYear, String prodCode, Integer term, Date date) throws Exception {
		List<Object> args = new ArrayList<>();
		
		String sql = "SELECT " + "    comper, comsin " + "FROM "
				+ "    inproductcom " + "WHERE " + "    sbucod = '450' AND comyer = " + comYear + " "
				+ "        AND prdcod = '" + prodCode + "' " + "        AND frmtrm <= " + term + " "
				+ "        AND toterm >= " + term + " " + "        AND '" + date + "' BETWEEN todate AND frmdat";
		
		System.out.println(sql);
		
		List<CommisModel> commisModels = jdbcTemplate.query(sql,
				new CommisRowMapper(), args.toArray());

		CommisModel commisModel = null;

		if (commisModels != null && !commisModels.isEmpty()) {
			commisModel = commisModels.get(0);
		}

		return commisModel;
	}

}
