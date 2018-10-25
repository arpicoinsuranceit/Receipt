package org.arpico.groupit.receipt.service;

import java.util.List;
import org.arpico.groupit.receipt.dto.DocumentTypeDto;

public interface DocumentTypeService {

	public List<DocumentTypeDto> getAllDocumentType()throws Exception;
	
}
