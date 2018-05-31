package org.arpico.groupit.receipt.dao;

import org.arpico.groupit.receipt.model.SmSequenceModel;

public interface SmSequenceDao{

	public SmSequenceModel getSequence (String serial) throws Exception;

	public void updateCurrentNumber(long current_number, String serial);
}
