package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.MedicalRequirementsDto;
import org.arpico.groupit.receipt.dto.NotRelChequeDto;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.dto.ShortPremiumDto;
import org.arpico.groupit.receipt.dto.WorkFlowPolicyGridDto;
import org.springframework.http.ResponseEntity;

public interface WorkflowService {

	List<PromisesGridDto> getPromisesList(String token, Integer page, Integer offset) throws Exception;

	Integer getLength(String token) throws Exception;

	ResponseEntity<Object> savePromise(PromisesGridDto promise, String token) throws Exception;

	ResponseEntity<Object> settlePromise(PromisesGridDto promise, String token) throws Exception;

	ResponseEntity<Object> getPolicyDetails(String polnum, String pprnum) throws Exception;

	List<PromisesGridDto> getPolicies(String token, Integer page, Integer offset, String type) throws Exception;

	ResponseEntity<Object> getPaymentHistory(String polnum, String pprnum) throws Exception;

	ResponseEntity<Object> getReceiptHistory(String polnum, String pprnum) throws Exception;

	List<WorkFlowPolicyGridDto> getPendingActPolicies(String token) throws Exception;

	List<ShortPremiumDto> findShortPremium(String userCode, Integer page, Integer offset)  throws Exception;

	Integer findShortPremiumCount(String userCode) throws Exception;

	List<ShortPremiumDto> findPendingReq(String userCode, Integer page, Integer offset) throws Exception;

	Integer findPendingReqCount(String userCode) throws Exception;

	List<MedicalRequirementsDto> getPendingReqDetails(String userCode, Integer pprno)  throws Exception;

	List<WorkFlowPolicyGridDto> getPendingLapsPolicies(String token, String type, Integer date1, Integer date2) throws Exception;

	List<NotRelChequeDto> getNotRelCheqye(String token) throws Exception;

}
