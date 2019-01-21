package org.arpico.groupit.receipt.controller;

import java.util.List;
import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dto.MedicalRequirementsDto;
import org.arpico.groupit.receipt.dto.NomineeDto;
import org.arpico.groupit.receipt.dto.PensionSheduleDto;
import org.arpico.groupit.receipt.dto.SaveUnderwriteDto;
import org.arpico.groupit.receipt.dto.SheduleDto;
import org.arpico.groupit.receipt.dto.SurrenderValsDto;
import org.arpico.groupit.receipt.dto.UnderwriteDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.BranchUnderwriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public UnderwriteDto getProposalsToUnderwrite (@PathVariable("token") String token,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize) throws Exception{
		//System.out.println("loadProposalToUnderwrite");
		
		String userCode=new JwtDecoder().generate(token);
		return branchUnderwriteService.getProposalToUnderwrite(userCode,pageIndex,pageSize);
		
	}
	
	@RequestMapping(value = "/loadProposalDetails/{proposalNo}/{seqNo}", method = RequestMethod.GET)
	public InProposalsModel getProposalDetails (@PathVariable("proposalNo") String proposalNo,@PathVariable("seqNo") String seqNo) throws Exception{
		//System.out.println("getProposalDetails");
		//System.out.println(proposalNo);
		//System.out.println(proposalNo);
		
		return branchUnderwriteService.getInProposalDetails(Integer.valueOf(proposalNo), Integer.valueOf(seqNo));

	}
	
	@RequestMapping(value = "/getQuotationDetails/{seqNo}/{qId}", method = RequestMethod.GET)
	public ViewQuotationDto getQuotationDetails (@PathVariable("seqNo") String seqNo,@PathVariable("qId") String qId)throws Exception{
		//System.out.println("getQuotationDetails");
		
		return quotationClient.getQuotation(Integer.valueOf(seqNo), Integer.valueOf(qId));
		
	}
	
	@RequestMapping(value = "/getQuotationSeqnum/{qdId}", method = RequestMethod.GET)
	public Integer getQuotationDetails (@PathVariable("qdId") String qdId)throws Exception{
		//System.out.println("getQuotationSeqnum");
		
		return quotationClient.getQuotationSeqnum(Integer.valueOf(qdId));
		
	}
	
	@RequestMapping(value = "/getPensionShedule/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<PensionSheduleDto> getPensionShedule (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId)throws Exception{
		//System.out.println("getPensionShedule");
		
		return quotationClient.getPensionShedule(seqNo, qId);
		
	}
	
	@RequestMapping(value = "/getShedule/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<SheduleDto> getShedule (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId)throws Exception{
		//System.out.println("getShedule");
		
		return quotationClient.getShedule(seqNo, qId);
		
	}
	
	@RequestMapping(value = "/getSurrenderVals/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<SurrenderValsDto> getSurrenderVals (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId)throws Exception{
		//System.out.println("getSurrenderVals");
		
		return quotationClient.getSurrenderVals(seqNo, qId);
		
	}
	
	@RequestMapping(value = "/getNominee/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<NomineeDto> getNominee (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId)throws Exception{
		//System.out.println("getNominee");
		
		return quotationClient.getNominee(seqNo, qId);
		
	}
	
	@RequestMapping(value = "/getMedicals/{seqNo}/{qId}", method = RequestMethod.GET)
	public List<MedicalRequirementsDto> getMedicals(@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId)throws Exception{
		//System.out.println("getMedicals");
		
		return quotationClient.getMedicals(seqNo, qId);
		
	}
	
	@RequestMapping(value = "/getQuotationDetailFromSeqNo/{seqNo}/{qId}", method = RequestMethod.GET)
	public Integer getQuotationDetailFromSeqNo (@PathVariable("seqNo") Integer seqNo,@PathVariable("qId") Integer qId)throws Exception{
		//System.out.println("getQuotationDetailFromSeqNo");
		
		return quotationClient.getQuotationDetailFromSeqNo(seqNo, qId);
		
	}
	
	@RequestMapping(value = "/checkNicValidation/{nic}/{gender}/{age}/{seqNo}/{qId}", method = RequestMethod.GET)
	public String checkNicValidation (@PathVariable("nic") String nic,@PathVariable("gender") String gender,@PathVariable("age") String age,@PathVariable("seqNo") String seqNo, @PathVariable("qId") String qId)
			throws Exception{
		//System.out.println("checkNicValidation");
		
		return quotationClient.checkNicValidation(nic, gender, age, seqNo, qId);
		
	}
	
	@RequestMapping(value = "/loadProposalFamDetails/{proposalNo}/{seqNo}", method = RequestMethod.GET)
	public List<InPropFamDetailsModel> getProposalFamDetails (@PathVariable("proposalNo") String proposalNo,@PathVariable("seqNo") String seqNo) throws Exception{

		return branchUnderwriteService.getInProposalFamDetails(Integer.valueOf(proposalNo), Integer.valueOf(seqNo));

	}
	
	@RequestMapping(value = "/loadProposalNomDetails/{proposalNo}/{seqNo}", method = RequestMethod.GET)
	public List<InPropNomDetailsModel> getProposalNomDetails (@PathVariable("proposalNo") String proposalNo,@PathVariable("seqNo") String seqNo) throws Exception{

		return branchUnderwriteService.getInProposalNomineeDetails(Integer.valueOf(proposalNo), Integer.valueOf(seqNo));

	}
	
	@RequestMapping(value = "/saveUnderwrite", method = RequestMethod.POST)
	public ResponseEntity<Object> saveUnderwrite(@RequestBody SaveUnderwriteDto saveUnderwriteDto) {
		System.out.println(saveUnderwriteDto.toString());
		 
		try {
			return branchUnderwriteService.saveUnderwrite(saveUnderwriteDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
