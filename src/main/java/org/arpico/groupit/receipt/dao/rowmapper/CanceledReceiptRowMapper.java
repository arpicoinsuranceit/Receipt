package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.springframework.jdbc.core.RowMapper;

public class CanceledReceiptRowMapper implements RowMapper<CanceledReceiptModel>{

	@Override
	public CanceledReceiptModel mapRow(ResultSet rs, int row) throws SQLException {
		CanceledReceiptModel receiptModel = new CanceledReceiptModel();
		
		receiptModel.setSbuCode(rs.getString("sbucod"));
		receiptModel.setLocCode(rs.getString("loccod"));
		receiptModel.setPolNum(rs.getString("polnum"));
		receiptModel.setPprNum(rs.getString("pprnum"));
		receiptModel.setReason(rs.getString("reason"));
		receiptModel.setReceiptNo(rs.getString("docnum"));
		receiptModel.setRequestBy(rs.getString("rqstby"));
		receiptModel.setRequestDate(rs.getDate("rqstdt"));
		receiptModel.setStatus(rs.getString("status"));
		receiptModel.setAmount(rs.getDouble("amount"));
		receiptModel.setDocCode(rs.getString("doccod"));
		
		
		return receiptModel;
	}

}
