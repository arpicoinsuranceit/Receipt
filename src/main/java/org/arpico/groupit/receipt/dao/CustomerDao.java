package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<CustomerModel, String>{

}
