
package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.CodeTransferModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeTransferDao extends JpaRepository<CodeTransferModel, Integer>{
	
	public List<CodeTransferModel> findByStatusAndCreateBy(String status,String createBy)throws Exception;
	
	public List<CodeTransferModel> findByStatusAndCreateBy(String status,String createBy, Pageable pageable)throws Exception;
	
	public List<CodeTransferModel> findByStatusAndLocCodeIn(String status,List<String> locCode)throws Exception;
	
	public List<CodeTransferModel> findByStatusAndPprNum(String status,String pprNum)throws Exception;

}
//package org.arpico.groupit.receipt.dao;
//
//import java.util.List;
//
//import org.arpico.groupit.receipt.model.CodeTransferModel;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface CodeTransferDao extends JpaRepository<CodeTransferModel, Integer>{
//	
//	public List<CodeTransferModel> findByStatusAndCreateBy(String status,String createBy)throws Exception;
//	
//	public List<CodeTransferModel> findByStatusAndLocCodeIn(String status,List<String> locCode)throws Exception;
//	
//	
//
//}
//>>>>>>> feature-re1.0.2
