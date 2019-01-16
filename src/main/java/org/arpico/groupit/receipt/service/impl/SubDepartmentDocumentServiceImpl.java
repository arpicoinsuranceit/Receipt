package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.SubDepartmentDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentDao;
import org.arpico.groupit.receipt.dto.DocumentTypeDto;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentDto;
import org.arpico.groupit.receipt.model.DocumentTypeModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentModel;
import org.arpico.groupit.receipt.model.SubDepartmentModel;
import org.arpico.groupit.receipt.service.SubDepartmentDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SubDepartmentDocumentServiceImpl implements SubDepartmentDocumentService{
	
	@Autowired
	private SubDepartmentDocumentDao subDepartmentDocumentDao;
	
	@Autowired
	private SubDepartmentDao subDepartmentDao;

	@Override
	public List<SubDepartmentDocumentDto> getAllSubDepartmentDocs() throws Exception {
		return null;
	}

	@Override
	public List<DocumentTypeDto> findBySubDepartment(Integer subDepId,Boolean isHOUser) throws Exception {
		
		SubDepartmentModel subDepartmentModel=subDepartmentDao.findOne(subDepId);
		////System.out.println(subDepartmentModel.toString());
		List<SubDepartmentDocumentModel> subDepartmentDocumentModels=subDepartmentDocumentDao.findBySubDepartment(subDepartmentModel);
		////System.out.println(subDepartmentDocumentModels.toString());
		List<DocumentTypeDto> documentTypeDtos=new ArrayList<>();
		
		subDepartmentDocumentModels.forEach(sdd -> {
			
			DocumentTypeModel documentTypeModel=sdd.getDocumentType();
			
			if(documentTypeModel.isActive()) {
				if(documentTypeModel.getParent() == 0) {
					if(isHOUser) {
						if(documentTypeModel.getDocTypeRef().equals("HO")) {
							DocumentTypeDto documentTypeDto=new DocumentTypeDto();
							
							documentTypeDto.setActive(documentTypeModel.isActive());
							documentTypeDto.setCreateBy(documentTypeModel.getCreateBy());
							documentTypeDto.setCreateDate(documentTypeModel.getCreateDate());
							documentTypeDto.setDocName(documentTypeModel.getDocName());
							documentTypeDto.setDocTypeId(documentTypeModel.getDocTypeId());
							documentTypeDto.setModifyBy(documentTypeModel.getModifyBy());
							//documentTypeDto.setModifyDate(documentTypeModel.getModifyDate());
							
							documentTypeDtos.add(documentTypeDto);
						}
					}else {
						if(documentTypeModel.getDocTypeRef().equals("BRANCH")) {
							DocumentTypeDto documentTypeDto=new DocumentTypeDto();
							
							documentTypeDto.setActive(documentTypeModel.isActive());
							documentTypeDto.setCreateBy(documentTypeModel.getCreateBy());
							documentTypeDto.setCreateDate(documentTypeModel.getCreateDate());
							documentTypeDto.setDocName(documentTypeModel.getDocName());
							documentTypeDto.setDocTypeId(documentTypeModel.getDocTypeId());
							documentTypeDto.setModifyBy(documentTypeModel.getModifyBy());
							//documentTypeDto.setModifyDate(documentTypeModel.getModifyDate());
							
							documentTypeDtos.add(documentTypeDto);
						}
					}
					
				}
				
			}
			
			
			
			
		});
		
		return documentTypeDtos;
	}

}
