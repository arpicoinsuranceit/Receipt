package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptCancelationDao extends CrudRepository<CanceledReceiptModel, String>{

}
