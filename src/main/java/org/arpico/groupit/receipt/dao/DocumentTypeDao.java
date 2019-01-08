package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.DocumentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeDao extends JpaRepository<DocumentTypeModel, Integer>{
	
	public List<DocumentTypeModel> findByDocName(String docName)throws Exception; 
	
	public List<DocumentTypeModel> findByDocTypeCodeAndParent(String docTypeCode,Integer parent)throws Exception; 
	
	public List<DocumentTypeModel> findByDocNameAndParent(String docName,Integer parent)throws Exception; 
	
	public List<DocumentTypeModel> findByParent(Integer parent)throws Exception; 

}
