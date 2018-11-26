package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPromisesModel;
import org.springframework.data.repository.CrudRepository;

public interface InPromiseDao extends CrudRepository<InPromisesModel, Integer>{
	
	List<InPromisesModel> findAllBySbuCodeAndActive(String sbuCode,Integer active) throws Exception;
	
	List<InPromisesModel> findAllBySbuCodeAndLocCodeInAndActive(String sbuCode,List<String> locCode,Integer active) throws Exception;

}
