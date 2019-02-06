package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.ExpenseDao;
import org.arpico.groupit.receipt.dto.ExpenseDto;
import org.arpico.groupit.receipt.model.ExpenseModel;
import org.arpico.groupit.receipt.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseDao expenseDao;

	@Override
	public List<ExpenseDto> getExpense() throws Exception {

		List<ExpenseModel> expenseModels = expenseDao.getExpenceModels();

		List<ExpenseDto> expenseDtos = new ArrayList<>();

		expenseModels.forEach(e -> {
			expenseDtos.add(getExpenceDto(e));
		});

		return expenseDtos;
	}

	 ExpenseDto getExpenceDto(ExpenseModel e) {
		ExpenseDto expenseDto = new ExpenseDto();
		expenseDto.setExpenseId(e.getExpenceId());
		expenseDto.setDescription(e.getDescription());
		expenseDto.setAmount(e.getAmount());
		return expenseDto;
	}

	@Override
	public ExpenseDto findByCode(String itemCode) throws Exception {
		List<ExpenseModel> expenseModels = expenseDao.getExpenceModel(itemCode);
		ExpenseDto expenseDto = null;
		if (expenseModels != null && expenseModels.size() > 0) {
			expenseDto = getExpenceDto(expenseModels.get(0));
		}

		return expenseDto;
	}

}
