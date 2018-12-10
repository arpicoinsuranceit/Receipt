package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.PaymentHistoryModel;
import org.springframework.jdbc.core.RowMapper;

public class PaymentHistoryRowMapper implements RowMapper<PaymentHistoryModel>{

	@Override
	public PaymentHistoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaymentHistoryModel model = new PaymentHistoryModel();
		
		model.setDueamt(rs.getDouble("dueamt"));
		model.setDuedat(rs.getDate("duedat"));
		model.setOutstd(rs.getDouble("outstd"));
		model.setRemark(rs.getString("remark"));
		model.setSetamt(rs.getDouble("setamt"));
		model.setTxndat(rs.getDate("txndat"));
		model.setTxnmth(rs.getInt("txnmth"));
		model.setTxnyer(rs.getInt("txnyer"));
		
		
		return model;
	}
	

}
