package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.InPromiseDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.model.InPromisesModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private InPromiseDao inPromiseDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtDecoder decoder;

	@Override
	public List<PromisesGridDto> getPromisesList(String token) throws Exception {
		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		List<InPromisesModel> promisesModels = null;

		List<PromisesGridDto> promisesGridDtos = new ArrayList<>();

		if (branches.contains("HO")) {
			promisesModels = inPromiseDao.findAllBySbuCodeAndActive("450", 1);
		} else {
			promisesModels = inPromiseDao.findAllBySbuCodeAndLocCodeInAndActive("450", branches, 1);
		}

		if (promisesModels != null && !promisesModels.isEmpty()) {
			promisesModels.forEach(e -> {
				promisesGridDtos.add(getPromisesGridDto(e));
			});

		} else {

		}

		return promisesGridDtos;
	}

	private PromisesGridDto getPromisesGridDto(InPromisesModel e) {
		PromisesGridDto dto = new PromisesGridDto();
		dto.setId(e.getId());
		dto.setCustName(e.getCustName());
		dto.setCustNic(e.getCustNic());
		dto.setDueDate(e.getDueDate());
		dto.setPhoneNum(e.getPhoneNo());
		dto.setPolNum(e.getPolicyNo());
		dto.setPprNum(e.getPprno());
		dto.setPromiseDate(e.getSettleDate());
		return dto;
	}

}
