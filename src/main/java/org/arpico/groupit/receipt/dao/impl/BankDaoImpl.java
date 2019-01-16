package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.BankDao;
import org.arpico.groupit.receipt.dao.rowmapper.BankRowMapper;
import org.arpico.groupit.receipt.model.BankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BankDaoImpl implements BankDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BankModel> getBankList(String dataSql) throws Exception {
		
		//System.out.println("select * from smbank " + dataSql + " group by BANCOD");
		
		return (List<BankModel>) jdbcTemplate.query("select * from smbank " + dataSql + " group by BANCOD;", new BankRowMapper());
	}

	@Override
	public BankModel getBankById(String bankId) throws Exception {
		return (BankModel) jdbcTemplate.queryForObject("select * from smbank where SBUCOD = '450' and BANCOD='"+bankId+"' group by BANCOD", new BankRowMapper());
	}
	
	

}
