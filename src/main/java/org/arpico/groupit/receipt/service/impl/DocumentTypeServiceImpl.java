package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.DocumentTypeDao;
import org.arpico.groupit.receipt.dto.DocumentTypeDto;
import org.arpico.groupit.receipt.model.DocumentTypeModel;
import org.arpico.groupit.receipt.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService{
	
	@Autowired
	private DocumentTypeDao documentTypeDao;

	@Override
	public List<DocumentTypeDto> getAllDocumentType() throws Exception {
		return null;
	}

	@Override
	public List<DocumentTypeDto> findByParent(Integer parent) throws Exception {
		List<DocumentTypeModel> documentTypeModels=documentTypeDao.findByParent(parent);
		
		List<DocumentTypeDto> documentTypeDtos=new ArrayList<>();
		
		if(documentTypeModels != null) {
			documentTypeModels.forEach(dt ->{
				if(dt.isActive()) {
					DocumentTypeDto documentTypeDto=new DocumentTypeDto();
					
					documentTypeDto.setActive(dt.isActive());
					documentTypeDto.setCreateBy(dt.getCreateBy());
					documentTypeDto.setCreateDate(dt.getCreateDate());
					documentTypeDto.setDocName(dt.getDocName());
					documentTypeDto.setDocTypeId(dt.getDocTypeId());
					documentTypeDto.setModifyBy(dt.getModifyBy());
					//documentTypeDto.setModifyDate(documentTypeModel.getModifyDate());
					
					documentTypeDtos.add(documentTypeDto);
				}
			});
		}
		
		
		return documentTypeDtos;
				
	}

}
