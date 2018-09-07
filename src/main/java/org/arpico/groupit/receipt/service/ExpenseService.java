package org.arpico.groupit.receipt.service;

import java.util.List;

import org.arpico.groupit.receipt.dto.ExpenseDto;

public interface ExpenseService {

	List<ExpenseDto> getExpense() throws Exception;
}
