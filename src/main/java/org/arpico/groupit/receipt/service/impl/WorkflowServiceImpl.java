package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dao.InPromiseDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.PromisesGridDto;
import org.arpico.groupit.receipt.model.InPromisesModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.WorkflowService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<PromisesGridDto> getPromisesList(String token, Integer page, Integer offset) throws Exception {
		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		List<InPromisesModel> promisesModels = null;

		List<PromisesGridDto> promisesGridDtos = new ArrayList<>();

		if (branches.contains("HO")) {
			promisesModels = inPromiseDao.findAllBySbuCodeAndActiveOrderByCreateDateDesc("450", 1, new PageRequest(page, offset));
		} else {
			promisesModels = inPromiseDao.findAllBySbuCodeAndLocCodeInAndActiveOrderByCreateDateDesc("450", branches, 1,
					new PageRequest(page, offset));
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
		dto.setAmount(e.getAmount());
		return dto;
	}

	@Override
	public Integer getLength(String token) throws Exception {
		String userCode = decoder.generate(token);

		List<String> branches = userDao.getUserLocations(userCode);

		Integer count = 0;

		if (branches.contains("HO")) {
			count = inPromiseDao.countBySbuCodeAndActive("450", 1);
		} else {
			count = inPromiseDao.countBySbuCodeAndLocCodeInAndActive("450", branches, 1);
		}

		return count;
	}

	@Override
	public ResponseEntity<Object> savePromise(PromisesGridDto promise, String token) throws Exception {
		try {
			String userCode = decoder.generate(token);
			String branch = decoder.generateLoc(token);

			InPromisesModel model = getInPromiseModel(userCode, branch, promise);

			List<InPromisesModel> inPromisesModels = inPromiseDao
					.findAllBySbuCodeAndActiveAndPprno(AppConstant.SBU_CODE, 1, promise.getPprNum());

			for (InPromisesModel inPromisesModel : inPromisesModels) {
				inPromisesModel.setActive(0);
				inPromisesModel.setUpdateBy(userCode);
				inPromisesModel.setUpdateDate(new Date());
			}

			inPromiseDao.save(inPromisesModels);

			inPromiseDao.save(model);

			return new ResponseEntity<Object>("200", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private InPromisesModel getInPromiseModel(String userCode, String branch, PromisesGridDto promise) {
		InPromisesModel model = new InPromisesModel();
		model.setActive(1);
		model.setAmount(promise.getAmount());
		model.setCustName(promise.getCustName());
		model.setCustNic(promise.getCustNic());
		model.setDueDate(promise.getDueDate());
		model.setLocCode(branch);
		model.setPhoneNo(promise.getPhoneNum());
		model.setPolicyNo(promise.getPolNum());
		model.setPprno(promise.getPprNum());
		model.setSbuCode(AppConstant.SBU_CODE);
		model.setSettleDate(promise.getPromiseDate());
		
		model.setCreateBy(userCode);
		model.setCreateDate(new Date());

		return model;
	}

	@Override
	public ResponseEntity<Object> settlePromise(PromisesGridDto promise, String token) throws Exception {
		InPromisesModel inPromisesModel = inPromiseDao.findOne(promise.getId());
		
		if(inPromisesModel!= null) {
		
			inPromisesModel.setActive(0);
			inPromisesModel.setUpdateBy(decoder.generate(token));
			inPromisesModel.setUpdateDate(new Date());
			
			inPromiseDao.save(inPromisesModel);
			
			return new ResponseEntity<Object>("200", HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Object>("404", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Object> getPolicyDetails(String polnum, String pprnum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
