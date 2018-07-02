package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.BenefictDetailsDao;
import org.arpico.groupit.receipt.dao.rowmapper.InProdAddBenefictCustomRowMapper;
import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BenefictDetailsDaoImpl implements BenefictDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<InPropAddBenefitModel> getBenefictByProduct(String product) throws Exception {

		List<InPropAddBenefitModel> addBenefitModels = jdbcTemplate.query(
				"SELECT pb.sbucod, rm.ridcod,rm.ridnam, pb.disord, pb.prmonl, rm.agemin, rm.agemax, pb.grdord, pb.grprid, rm.medgrd, rm.ridtyp, " + 
				"CASE " + 
				"	WHEN pb.blkmai != 'Y' THEN 'main' " + 
				"    WHEN pb.alwfam = 'Y' THEN 'children' " + 
				"    WHEN pb.alwspo = 'Y' THEN 'spouse' " + 
				"    ELSE '' " + 
				"END instyp " + 
				"FROM inridermaster rm " + 
				"INNER JOIN " + 
				"    inprodbenefit pb ON rm.sbucod = pb.sbucod AND rm.ridcod = pb.ridcod " + 
				"WHERE " + 
				"    rm.sbucod = '450' " + 
				"        AND pb.prdcod = '"+product+"' " + 
				"        AND pb.ridcod NOT IN ('HRB','HRBS','HRBC','SUHRB','SUHRBS','SUHRBC') " + 
				"ORDER BY pb.grdord;",
				new InProdAddBenefictCustomRowMapper());

		return addBenefitModels;
	}

}
