package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.PromisesGridDto;

public interface WorkflowService {

	List<PromisesGridDto> getPromisesList(String token) throws Exception;
}
