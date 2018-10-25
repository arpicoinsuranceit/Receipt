package org.arpico.groupit.receipt.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.arpico.groupit.receipt.dto.DocumentTypeDto;
import org.arpico.groupit.receipt.service.DocumentTypeService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService{

	@Override
	public List<DocumentTypeDto> getAllDocumentType() throws Exception {
		return null;
	}

}
