package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.arpico.groupit.receipt.dao.BankDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.BankDto;
import org.arpico.groupit.receipt.model.BankModel;
import org.arpico.groupit.receipt.service.BankService;
import org.arpico.groupit.receipt.util.DaoParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<BankDto> getBanksByUser(String userCode) throws Exception {

		List<String> locationsTemp = userDao.getUserLocations(userCode);
		
		String [] data = new String[locationsTemp.size()];
		
		int count = 0;
		for (String string : locationsTemp) {
			data[count] = string;
			count ++;
		}
		
		String location = new DaoParameters().getParaForIn(data);
		String dataSql = "where SBUCOD = '450' ";
		//if (!locationsTemp.contains("HO")) {
			dataSql += " and loccod in (" + location + ")";
		//}
		
		List<BankModel> bankModels = bankDao.getBankList(dataSql);
		
		//System.out.println(dataSql);
		List<BankDto> bankDtos = new ArrayList<>();

		//System.out.println(bankModels.size());

		for (BankModel bankModel : bankModels) {
			BankDto bankDto = getBankDto(bankModel);
			bankDtos.add(bankDto);
		}

		return bankDtos;
	}

	private BankDto getBankDto(BankModel bankModel) {
		BankDto bankDto = new BankDto();

		bankDto.setBankCode(bankModel.getBankCode());
		bankDto.setBankName(bankModel.getBankName());

		return bankDto;
	}

	@Override
	public boolean findBankById(String bankId) {
		try {
			BankModel bankModel = bankDao.getBankById(bankId);
			if (bankModel != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
