
package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.CanceledReceiptDto;
import org.arpico.groupit.receipt.dto.CodeTransferDto;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.dto.WorkFlowPolicyGridDto;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.arpico.groupit.receipt.service.ReceiptCancelationService;
import org.arpico.groupit.receipt.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private ReceiptCancelationService receiptCancelationService;

	@Autowired
	private CodeTransferService codeTransferService;

	@RequestMapping(value = "/getAllPromises/{token:.+}/{page}/{offset}", method = RequestMethod.GET)
	public List<PromisesGridDto> getAllPromises(@PathVariable String token, @PathVariable Integer page,
			@PathVariable Integer offset) throws Exception {

		return workflowService.getPromisesList(token, page, offset);

	}

	@GetMapping(value = "/getPaginatorLength/{token:.+}")
	public Integer getPaginatorLength(@PathVariable String token) throws Exception {
		return workflowService.getLength(token);
	}

	@PostMapping(value = "/addpromise/{token:.+}")
	public ResponseEntity<Object> getPaginatorLength(@PathVariable String token, @RequestBody PromisesGridDto promise)
			throws Exception {

		System.out.println(token);
		System.out.println(promise);
		try {
			return workflowService.savePromise(promise, token);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/settlepromise/{token:.+}")
	public ResponseEntity<Object> settlePromise(@PathVariable String token, @RequestBody PromisesGridDto promise)
			throws Exception {

		return workflowService.settlePromise(promise, token);

	}

	@GetMapping(value = "/getPolicyDetails/{polnum}/{pprnum}")
	public ResponseEntity<Object> settlePromise(@PathVariable String polnum, @PathVariable String pprnum)
			throws Exception {

		return workflowService.getPolicyDetails(polnum, pprnum);

	}

	@GetMapping(value = "/getPaymentHistory/{polnum}/{pprnum}")
	public ResponseEntity<Object> getPaymentHistory(@PathVariable String polnum, @PathVariable String pprnum)
			throws Exception {

		return workflowService.getPaymentHistory(polnum, pprnum);

	}

	@GetMapping(value = "/getReceiptHistory/{polnum}/{pprnum}")
	public ResponseEntity<Object> getReceiptHistory(@PathVariable String polnum, @PathVariable String pprnum)
			throws Exception {

		return workflowService.getReceiptHistory(polnum, pprnum);

	}

	@RequestMapping(value = "/getPendingRequest/{token:.+}/{page}/{offset}", method = RequestMethod.GET)
	public List<CanceledReceiptDto> getPendingRequest(@PathVariable("token") String token, @PathVariable Integer page,
			@PathVariable Integer offset) throws Exception {

		return receiptCancelationService.findPendingRequest(token, page, offset);

	}

	@RequestMapping(value = "/getPendingRequestLength/{token:.+}", method = RequestMethod.GET)
	public Integer getPendingRequest(@PathVariable("token") String token) throws Exception {

		return receiptCancelationService.findPendingRequestLength(token);

	}

	@RequestMapping(value = "/code_transfer/getPendingCodeTransfersPrp/{token:.+}/{page}/{offset}", method = RequestMethod.GET)
	public List<CodeTransferDto> getPendingCodeTransfersPrp(@PathVariable("token") String token,
			@PathVariable Integer page, @PathVariable Integer offset) throws Exception {
		return codeTransferService.getPendingCodeTransferPrp(token, page, offset);
	}

	@RequestMapping(value = "/getPendingActPolicies/{token:.+}/{page}/{offset}", method = RequestMethod.GET)
	public List<WorkFlowPolicyGridDto> getPendingActPolicies(@PathVariable("token") String token,
			@PathVariable Integer page, @PathVariable Integer offset) throws Exception {
		return workflowService.getPendingActPolicies(token, page, offset);
	}

}
