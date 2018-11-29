package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.springframework.http.ResponseEntity;

public interface WorkflowService {

	List<PromisesGridDto> getPromisesList(String token, Integer page, Integer offset) throws Exception;

	Integer getLength(String token) throws Exception;

	ResponseEntity<Object> savePromise(PromisesGridDto promise, String token) throws Exception;

	ResponseEntity<Object> settlePromise(PromisesGridDto promise, String token) throws Exception;

	ResponseEntity<Object> getPolicyDetails(String polnum, String pprnum) throws Exception;

}
