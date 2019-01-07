package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<DepartmentModel, Integer>{

}
