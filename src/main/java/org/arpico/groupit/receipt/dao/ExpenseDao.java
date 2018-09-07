package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.ExpenseModel;

public interface ExpenseDao {

	List<ExpenseModel> getExpenceModels() throws Exception;
}
