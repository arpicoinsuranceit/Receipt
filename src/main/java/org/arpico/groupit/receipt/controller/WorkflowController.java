
package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.CanceledReceiptDto;
import org.arpico.groupit.receipt.dto.CodeTransferDto;
import org.arpico.groupit.receipt.dto.CourierDto;
import org.arpico.groupit.receipt.dto.MedicalRequirementsDto;
import org.arpico.groupit.receipt.dto.NotRelChequeDto;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.dto.ShortPremiumDto;
import org.arpico.groupit.receipt.dto.WorkFlowPolicyGridDto;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.CodeTransferService;
import org.arpico.groupit.receipt.service.CourierService;
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
	
	@Autowired
	private CourierService courierService;

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

	@RequestMapping(value = "/getPendingActPolicies/{token:.+}", method = RequestMethod.GET)
	public List<WorkFlowPolicyGridDto> getPendingActPolicies(@PathVariable("token") String token) throws Exception {
		return workflowService.getPendingActPolicies(token);
	}
	
	@RequestMapping(value="/branchcourier/{token:.+}/{page}/{offset}",method=RequestMethod.GET)
	public List<CourierDto> getAllPendingCourier(@PathVariable String token,@PathVariable Integer page,@PathVariable Integer offset) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusAndBranchCodeIn(userCode,page, offset);
		
	}
	
	@RequestMapping(value="/branchcouriercount/{token:.+}",method=RequestMethod.GET)
	public Integer getAllPendingCourierCount(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.getAllPendingCourierCount(userCode);
		
	}
	
	@RequestMapping(value="/receivingcourier/{token:.+}/{page}/{offset}",method=RequestMethod.GET)
	public List<CourierDto> getAllReceivingCourier(@PathVariable String token,@PathVariable Integer page,@PathVariable Integer offset) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusAndToBranchIn(userCode,page,offset);
		
	}
	
	@RequestMapping(value="/receivingcouriercount/{token:.+}",method=RequestMethod.GET)
	public Integer getAllReceivingCourierCount(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.getAllReceivingCourierCount(userCode);
		
	}
	
	@RequestMapping(value="/shortpremium/{token:.+}/{page}/{offset}",method=RequestMethod.GET)
	public List<ShortPremiumDto> getAllShortPremium(@PathVariable String token,@PathVariable Integer page,@PathVariable Integer offset) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return workflowService.findShortPremium(userCode,page,offset);
		
	}
	
	@RequestMapping(value="/shortpremiumcount/{token:.+}",method=RequestMethod.GET)
	public Integer getAllShortPremiumCount(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return workflowService.findShortPremiumCount(userCode);
		
	}
	
	@RequestMapping(value="/pendingreq/{token:.+}/{page}/{offset}",method=RequestMethod.GET)
	public List<ShortPremiumDto> getAllPendingReq(@PathVariable String token,@PathVariable Integer page,@PathVariable Integer offset) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return workflowService.findPendingReq(userCode,page,offset);
		
	}
	
	@RequestMapping(value="/pendingreqcount/{token:.+}",method=RequestMethod.GET)
	public Integer getAllPendingReqCount(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return workflowService.findPendingReqCount(userCode);
		
	}
	
	@RequestMapping(value="/getPendingReqDetails/{token:.+}/{pprno}",method=RequestMethod.GET)
	public List<MedicalRequirementsDto> getPendingReqDetails(@PathVariable String token, @PathVariable Integer pprno) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return workflowService.getPendingReqDetails(userCode, pprno);
		
	}
	
	@RequestMapping(value = "/getPendingLapsPolicies/{token:.+}/{type}/{date1}/{date2}", method = RequestMethod.GET)
	public List<WorkFlowPolicyGridDto> getPendingLapsPolicies(@PathVariable("token") String token, @PathVariable("type") String type,
			@PathVariable("date1") Integer date1, @PathVariable("date2") Integer date2) throws Exception {
		return workflowService.getPendingLapsPolicies(token, type, date1, date2);
	}
	
	@RequestMapping(value = "/getNotRelCheqye/{token:.+}", method = RequestMethod.GET)
	public List<NotRelChequeDto> getNotRelCheqye(@PathVariable("token") String token) throws Exception {
		return workflowService.getNotRelCheqye(token);
	}

}
