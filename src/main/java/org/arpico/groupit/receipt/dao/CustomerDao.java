package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<CustomerModel, String>{

}
