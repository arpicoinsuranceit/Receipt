package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.CodeTransferModel;
import org.springframework.data.repository.CrudRepository;

public interface CodeTransferDao extends CrudRepository<CodeTransferModel, Integer>{
	
	public List<CodeTransferModel> findByStatusAndCreateBy(String status,String createBy)throws Exception;
	
	public List<CodeTransferModel> findByStatusAndLocCodeIn(String status,List<String> locCode)throws Exception;
	
	public List<CodeTransferModel> findByStatusAndPprNum(String status,String pprNum)throws Exception;

}
