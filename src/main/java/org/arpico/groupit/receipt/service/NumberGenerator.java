package org.arpico.groupit.receipt.service;

public interface NumberGenerator {
	
	public String[] generateNewId(String sbuCode, String locationCode, String serialId, String instatus) throws Exception;

}
