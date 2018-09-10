package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.BranchDao;
import org.arpico.groupit.receipt.dto.BranchDto;
import org.arpico.groupit.receipt.model.BranchModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	@Override
	public List<BranchDto> getBranches(String token) throws Exception {
		
		List<BranchDto> branchDtos = new ArrayList<>();
		
		String userCode =  jwtDecoder.generate(token);
		
		List<BranchModel> branchModels = branchDao.getBranchs(userCode);
		
		branchModels.forEach(e -> {
			branchDtos.add(getBranchDto(e));
		});
		
		return branchDtos;
	}

	private BranchDto getBranchDto(BranchModel e) {
		BranchDto branchDto = new BranchDto();
		branchDto.setDescription(e.getDescription());
		branchDto.setId(e.getId());
		return branchDto;
	}

}
