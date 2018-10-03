package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.BranchModel;

public interface BranchDao {

	List<BranchModel> getBranchs(String userCode) throws Exception;

	List<BranchModel> getAllBranchs() throws Exception;
}
