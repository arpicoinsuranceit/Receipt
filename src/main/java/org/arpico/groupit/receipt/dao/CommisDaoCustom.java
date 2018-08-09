package org.arpico.groupit.receipt.dao;

import java.util.Date;

import org.arpico.groupit.receipt.model.CommisModel;

public interface CommisDaoCustom {

	CommisModel getCommis(Integer comYear, String prodCode, Integer term, Date date) throws Exception;

}
