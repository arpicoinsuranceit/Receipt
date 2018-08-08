package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dto.NomineeDto;
import org.arpico.groupit.receipt.dto.PensionSheduleDto;
import org.arpico.groupit.receipt.dto.SaveUnderwriteDto;
import org.arpico.groupit.receipt.dto.SheduleDto;
import org.arpico.groupit.receipt.dto.SurrenderValsDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;
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
	
	@RequestMapping(value = "/loadProposalToUnderwrite/{token:.+}", method = RequestMethod.GET)
	public List<InProposalUnderwriteModel> getProposalsToUnderwrite (@PathVariable("token") String token){
		System.out.println("loadProposalToUnderwrite");
		
		try {
			String userCode=new JwtDecoder().generate(token);
			return branchUnderwriteService.getProposalToUnderwrite(userCode);
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
	
	@RequestMapping(value = "/getQuotationDetails/{qdId}/{qId}", method = RequestMethod.GET)
	public ViewQuotationDto getQuotationDetails (@PathVariable("qdId") String qdId,@PathVariable("qId") String qId){
		System.out.println("getQuotationDetails");
		
		try {
			return quotationClient.getQuotation(Integer.valueOf(qdId), Integer.valueOf(qId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getPensionShedule/{qdId}", method = RequestMethod.GET)
	public List<PensionSheduleDto> getPensionShedule (@PathVariable("qdId") Integer qdId){
		System.out.println("getPensionShedule");
		
		try {
			return quotationClient.getPensionShedule(qdId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getShedule/{qdId}", method = RequestMethod.GET)
	public List<SheduleDto> getShedule (@PathVariable("qdId") Integer qdId){
		System.out.println("getShedule");
		
		try {
			return quotationClient.getShedule(qdId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getSurrenderVals/{qdId}", method = RequestMethod.GET)
	public List<SurrenderValsDto> getSurrenderVals (@PathVariable("qdId") Integer qdId){
		System.out.println("getSurrenderVals");
		
		try {
			return quotationClient.getSurrenderVals(qdId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value = "/getNominee/{qdId}", method = RequestMethod.GET)
	public List<NomineeDto> getNominee (@PathVariable("qdId") Integer qdId){
		System.out.println("getNominee");
		
		try {
			return quotationClient.getNominee(qdId);
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
