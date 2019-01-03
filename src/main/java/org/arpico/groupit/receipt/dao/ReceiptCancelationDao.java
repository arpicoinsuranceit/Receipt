package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptCancelationDao extends JpaRepository<CanceledReceiptModel, String>{

}
