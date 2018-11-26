package org.arpico.groupit.receipt.controller;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dto.CodeTransferGridDto;
import org.arpico.groupit.receipt.service.impl.CodeTransferServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TestController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		
		List<CodeTransferGridDto> codeTransferGrid = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			CodeTransferGridDto dto = new CodeTransferGridDto();
			dto.setCrAgn("000" + i);
			dto.setCrBranch("MA" + i);
			dto.setNewAgn("0011" + i);
			dto.setNewBranch("AB" + i);
			dto.setNo("0000" + i);
			
			codeTransferGrid.add(dto);
		}
		
		
		try {
			new CodeTransferServiceImpl().sendMail("prp", null, "anjana.t", codeTransferGrid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "work";
	}
}
