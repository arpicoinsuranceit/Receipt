package org.arpico.groupit.receipt.controller;

import java.util.List;

import org.arpico.groupit.receipt.dto.CourierDetailsHelperDto;
import org.arpico.groupit.receipt.dto.CourierDto;
import org.arpico.groupit.receipt.dto.DepartmentDto;
import org.arpico.groupit.receipt.dto.DocumentTypeDto;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentCourierDto;
import org.arpico.groupit.receipt.dto.SubDepartmentDto;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.CourierService;
import org.arpico.groupit.receipt.service.DepartmentCourierService;
import org.arpico.groupit.receipt.service.DepartmentService;
import org.arpico.groupit.receipt.service.DocumentTypeService;
import org.arpico.groupit.receipt.service.RefTypeService;
import org.arpico.groupit.receipt.service.SubDepartmentDocumentCourierService;
import org.arpico.groupit.receipt.service.SubDepartmentDocumentService;
import org.arpico.groupit.receipt.service.SubDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CourierController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SubDepartmentService subDepartmentService;
	
	@Autowired
	private SubDepartmentDocumentService subDepartmentDocumentService;
	
	@Autowired
	private CourierService courierService;
	
	@Autowired
	private SubDepartmentDocumentCourierService subDepartmentDocumentCourierService;
	
	@Autowired
	private DepartmentCourierService departmentCourierService;
	
	@Autowired
	private RefTypeService refTypeService;
	
	@Autowired
	private DocumentTypeService documentTypeService;
	
	@RequestMapping(value="/courier/department",method=RequestMethod.GET)
	public List<DepartmentDto> getAllDepartment() throws Exception{
		
		return departmentService.getAllDepartments();
		
	}
	
	@RequestMapping(value="/courier/subdepartment/{depId}",method=RequestMethod.GET)
	public List<SubDepartmentDto> getSubDepartment(@PathVariable("depId")Integer depId) throws Exception{
		
		return subDepartmentService.findByDepartment(depId);
		
	}
	
	@RequestMapping(value="/courier/subdepartmentdocument/{subDepId}/{isHOUser}",method=RequestMethod.GET)
	public List<DocumentTypeDto> getSubDepartmentDocument(@PathVariable("subDepId")Integer subDepId,@PathVariable("isHOUser")Boolean isHOUser) throws Exception{
		
		return subDepartmentDocumentService.findBySubDepartment(subDepId,isHOUser);
		
	}
	
	@RequestMapping(value="/courier/childdocument/{parent}",method=RequestMethod.GET)
	public List<DocumentTypeDto> getChildDocumentFromParent(@PathVariable("parent")Integer parent) throws Exception{
		
		return documentTypeService.findByParent(parent);
		
	}
	
	@RequestMapping(value="/courier/branchcourier/{token:.+}/{isHoUser}",method=RequestMethod.GET)
	public List<CourierDto> getAllPendingCourier(@PathVariable String token,@PathVariable Boolean isHoUser) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusAndBranchCodeIn(userCode,isHoUser);
		
	}
	
	@RequestMapping(value="/courier/receivingcourier/{token:.+}/{isHoUser}",method=RequestMethod.GET)
	public List<CourierDto> getAllReceivingCourier(@PathVariable String token,@PathVariable Boolean isHoUser) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusAndToBranchIn(userCode,isHoUser,"SEND");
		
	}
	
	@RequestMapping(value="/courier/receivedcourier/{token:.+}/{isHoUser}",method=RequestMethod.GET)
	public List<CourierDto> getAllReceivedCourier(@PathVariable String token,@PathVariable Boolean isHoUser) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusAndToBranchIn(userCode,isHoUser,"RECEIVED");
		
	}
	
	@RequestMapping(value="/courier/completedcourier/{token:.+}/{isHoUser}",method=RequestMethod.GET)
	public List<CourierDto> getAllCompletedCourier(@PathVariable String token,@PathVariable Boolean isHoUser) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusAndToBranchIn(userCode,isHoUser,"COMPLETED");
		
	}
	
	@RequestMapping(value="/courier/branch/{token:.+}",method=RequestMethod.GET)
	public List<String> getBranches(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.getBranches(userCode);
		
	}
	
	@RequestMapping(value="/courier/reftype",method=RequestMethod.GET)
	public List<String> getRefTypes() throws Exception{
		
		return refTypeService.getAll();
		
	}
	
	@RequestMapping(value="/courier/save",method=RequestMethod.POST)
	public String saveCourier(@RequestParam("depId") Integer depId,@RequestParam("subDepId") Integer subDepId,@RequestParam("docId") Integer docId,@RequestParam("branch") String branch,
			@RequestParam("refType") String refType,@RequestParam("refNo") String refNo,@RequestParam("remark") String remark,@RequestParam("token") String usertoken,@RequestParam("isHOUser") Boolean isHOUser) throws Exception{
		
		System.out.println(depId);
		System.out.println(subDepId);
		System.out.println(docId);
		System.out.println(refType);
		System.out.println(refNo);
		System.out.println(remark);
		System.out.println(usertoken);
		System.out.println(branch);
		System.out.println(isHOUser);
		
		String userCode=new JwtDecoder().generate(usertoken);
		
		SubDepartmentDocumentCourierDto sddcd=new SubDepartmentDocumentCourierDto();
		
		sddcd.setReferenceType(refType);
		sddcd.setSubDepartmentDocumentId(docId);
		sddcd.setRemark(remark);
		sddcd.setReferenceNo(refNo);
		sddcd.setBranchCode(branch);
		sddcd.setCreateBy(userCode);
		
		return subDepartmentDocumentCourierService.saveSubDepDocCourier(sddcd,depId,subDepId,isHOUser);
		
		//return null;
		
	}
	
	@RequestMapping(value="/courier/courierdetails/{couId}",method=RequestMethod.GET)
	public CourierDetailsHelperDto getCourierDetails(@PathVariable Integer couId) throws Exception{
		
		return departmentCourierService.getCourierDetailsByCourier(couId);
		
	}
	
	@RequestMapping(value="/courier/changestatus/{couId}/{status}",method=RequestMethod.GET)
	public String changeCourierStatus(@PathVariable Integer couId,@PathVariable String status) throws Exception{
		
		return courierService.updateStatus(couId, status);
		
	}
	
	@RequestMapping(value="/courier/sendCourier",method=RequestMethod.POST)
	public String sendCouriers(@RequestBody CourierDetailsHelperDto courierDetailsHelperDto) throws Exception{
		System.out.println(courierDetailsHelperDto.toString());
		return courierService.sendCourier(courierDetailsHelperDto);
		
	}
	
	@RequestMapping(value="/courier/receiveCourier/{token:.+}",method=RequestMethod.POST)
	public String receiveCouriers(@PathVariable String token,@RequestBody CourierDetailsHelperDto courierDetailsHelperDto) throws Exception{
		System.out.println(courierDetailsHelperDto.toString());
		String userCode=new JwtDecoder().generate(token);
		return courierService.receiveCourier(courierDetailsHelperDto, userCode);
		
	}
	
	@RequestMapping(value="/courier/receiveCourierDocument/{token:.+}",method=RequestMethod.POST)
	public String receiveCourierDocument(@PathVariable String token,@RequestBody CourierDetailsHelperDto courierDetailsHelperDto) throws Exception{
		System.out.println(courierDetailsHelperDto.toString());
		String userCode=new JwtDecoder().generate(token);
		return courierService.receiveCourierDocument(courierDetailsHelperDto, userCode);
		
	}
	
	@RequestMapping(value="/courier/branchothercourier/{token:.+}",method=RequestMethod.GET)
	public List<CourierDto> getAllOtherCourier(@PathVariable String token) throws Exception{
		
		String userCode=new JwtDecoder().generate(token);
		return courierService.findByCourierStatusNotInAndBranchCodeIn(userCode);
		
	}

}
