package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dto.NomineeDto;
import org.arpico.groupit.receipt.dto.PensionSheduleDto;
import org.arpico.groupit.receipt.dto.SaveUnderwriteDto;
import org.arpico.groupit.receipt.dto.SheduleDto;
import org.arpico.groupit.receipt.dto.SurrenderValsDto;
import org.arpico.groupit.receipt.dto.UnderwriteDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.BranchUnderwriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BranchUnderwriteController {

	@Autowired
	private BranchUnderwriteService branchUnderwriteService;
	
	@Autowired
	private QuotationClient quotationClient;
	
	@RequestMapping(value = "/loadProposalToUnderwrite/{token:.+}/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public UnderwriteDto getProposalsToUnderwrite (@PathVariable("token") String token,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize){
		System.out.println("loadProposalToUnderwrite");
		
		try {
			String userCode=new JwtDecoder().generate(token);
			return branchUnderwriteService.getProposalToUnderwrite(userCode,pageIndex,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/loadProposalDetails/{proposalNo}/{seqNo}", method = RequestMethod.GET)
	public InProposalsModel getProposalDetails (@PathVariable("proposalNo") String proposalNo,@PathVariable("seqNo") String seqNo){
		System.out.println("getProposalDetails");
		
		try {
			return branchUnderwriteService.getInProposalDetails(Integer.valueOf(proposalNo), Integer.valueOf(seqNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getQuotationDetails/{seqNo}/{qId}", method = RequestMethod.GET)
	public ViewQuotationDto getQuotationDetails (@PathVariable("seqNo") String seqNo,@PathVariable("qId") String qId){
		System.out.println("getQuotationDetails");
		
		try {
			return quotationClient.getQuotation(Integer.valueOf(seqNo), Integer.valueOf(qId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getPensionShedule/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<PensionSheduleDto> getPensionShedule (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId){
		System.out.println("getPensionShedule");
		
		try {
			return quotationClient.getPensionShedule(seqNo, qId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getShedule/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<SheduleDto> getShedule (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId){
		System.out.println("getShedule");
		
		try {
			return quotationClient.getShedule(seqNo, qId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getSurrenderVals/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<SurrenderValsDto> getSurrenderVals (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId){
		System.out.println("getSurrenderVals");
		
		try {
			return quotationClient.getSurrenderVals(seqNo, qId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getNominee/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<NomineeDto> getNominee (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId){
		System.out.println("getNominee");
		
		try {
			return quotationClient.getNominee(seqNo, qId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getQuotationDetailFromSeqNo/{seqNo}/{qId}", method = RequestMethod.GET)
	public Integer getQuotationDetailFromSeqNo (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId){
		System.out.println("getQuotationDetailFromSeqNo");
		
		try {
			return quotationClient.getQuotationDetailFromSeqNo(seqNo, qId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/checkNicValidation/{nic}/{gender}/{age}/{seqNo}/{qId}", method = RequestMethod.GET)
	public String checkNicValidation (@PathVariable("nic") String nic,@PathVariable("gender") String gender,@PathVariable("age") String age,@PathVariable("seqNo") String seqNo, @PathVariable("qId") String qId){
		System.out.println("checkNicValidation");
		
		try {
			return quotationClient.checkNicValidation(nic, gender, age, seqNo, qId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/saveUnderwrite", method = RequestMethod.POST)
	public ResponseEntity<Object> saveUnderwrite(@RequestBody SaveUnderwriteDto saveUnderwriteDto) {
		System.out.println(saveUnderwriteDto.toString());
		 
		try {
			return branchUnderwriteService.saveUnderwrite(saveUnderwriteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
