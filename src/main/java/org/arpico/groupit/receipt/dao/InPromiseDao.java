package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.InPromisesModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface InPromiseDao extends CrudRepository<InPromisesModel, Integer> {

	List<InPromisesModel> findAllBySbuCodeAndActiveOrderByCreateDateDesc(String sbuCode, Integer active,
			Pageable pageable) throws Exception;

	List<InPromisesModel> findAllBySbuCodeAndLocCodeInAndActiveOrderByCreateDateDesc(String sbuCode,
			List<String> locCode, Integer active, Pageable pageable) throws Exception;

	List<InPromisesModel> findAllBySbuCodeAndActiveOrderByCreateDateDesc(String sbuCode, Integer active)
			throws Exception;

	List<InPromisesModel> findAllBySbuCodeAndLocCodeInAndActiveOrderByCreateDateDesc(String sbuCode,
			List<String> locCode, Integer active) throws Exception;

	Integer countBySbuCodeAndActive(String sbuCode, Integer active);

	Integer countBySbuCodeAndLocCodeInAndActive(String sbuCode, List<String> locCode, Integer active);

	List<InPromisesModel> findAllBySbuCodeAndActiveAndPprno(String sbuCode, Integer active, String pprno)
			throws Exception;

}
