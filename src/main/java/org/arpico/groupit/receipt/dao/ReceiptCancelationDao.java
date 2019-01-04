package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptCancelationDao extends CrudRepository<CanceledReceiptModel, String>{
	
	public List<CanceledReceiptModel> findByStatusAndReceiptNo(String status,String receiptNo)throws Exception;

}
