package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.RefTypeDao;
import org.arpico.groupit.receipt.model.RefTypeModel;
import org.arpico.groupit.receipt.service.RefTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RefTypeServiceImpl implements RefTypeService{
	
	@Autowired
	private RefTypeDao refTypeDao;

	@Override
	public List<String> getAll() throws Exception {
		List<RefTypeModel> refTypeModels=(List<RefTypeModel>) refTypeDao.findAll();
		
		List<String> refTypeDtos=new ArrayList<>();
		
		if(refTypeModels != null) {
			
			refTypeModels.forEach(rf ->{
				
				refTypeDtos.add(rf.getRefTypeName());
				
			});
			
		}
		
		return refTypeDtos;
	}

}
