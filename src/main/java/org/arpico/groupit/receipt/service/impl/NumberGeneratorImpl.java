package org.arpico.groupit.receipt.service.impl;

import org.arpico.groupit.receipt.dao.SmSequenceDao;
import org.arpico.groupit.receipt.model.SmSequenceModel;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NumberGeneratorImpl implements NumberGenerator {

	@Autowired
	private SmSequenceDao smSequenceDao;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public String[] generateNewId(String sbuCode, String locationCode, String serialId, String instatus)
			throws Exception {

		long increment_by = 0;
		long minimum_number = 0;
		long maximum_number = 0;
		long current_number = 0;
		String cycle = "N";

		int result = 0;
		String responce[] = new String[2];

		SmSequenceModel smSequenceModel = smSequenceDao.getSequence(serialId);

		if (smSequenceModel != null) {

			increment_by = smSequenceModel.getIncrementBy();
			minimum_number = smSequenceModel.getMinValue();
			maximum_number = smSequenceModel.getMaxValue();
			current_number = smSequenceModel.getCurrentValue();
			cycle = smSequenceModel.getCycle();

			current_number += increment_by; // Increment number

			if (current_number < minimum_number) {
				result = -2; // current number is less than minimum number;
				responce[0] = "Error";
				responce[1] = String.valueOf(result);

			} else if (current_number > maximum_number) {

				if (cycle.equalsIgnoreCase("Y")) {
					current_number = minimum_number;
					result = 1;
				} else {
					result = -3; // current number is greater than the maximum number;
					responce[0] = "Error";
					responce[1] = String.valueOf(result);
				}

			} else {
				result = 1;
			}

		} else {
			result = -1; // squence object cannot be found
			responce[0] = "Error";
			responce[1] = String.valueOf(result);
		}

		if (result == 1) {
			smSequenceDao.updateCurrentNumber(current_number, serialId);
			responce[0] = "Success";
			responce[1] = Long.toString(current_number);
		}
		return responce;
	}

}
