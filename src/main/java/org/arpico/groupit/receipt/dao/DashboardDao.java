package org.arpico.groupit.receipt.dao;

import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.model.DashboardCashFlowSummeryModel;
import org.arpico.groupit.receipt.model.DashboardDetailsModel;
import org.arpico.groupit.receipt.model.DashboardGridModel;
import org.arpico.groupit.receipt.model.DashboardPieModel;

public interface DashboardDao {

	public List<DashboardPieModel> getFromInTransaction(String to, String from, String user) throws Exception;

	public List<DashboardPieModel> getFromRecm(String to, String from, String user) throws Exception;

	public List<DashboardPieModel> getFromDocTxnm(String to, String from, String user) throws Exception;

	public List<DashboardGridModel> getFromInTransactionsGrid(String toDate, String fromDate, String user, String sql)
			throws Exception;

	public List<DashboardGridModel> getFromRecmGrid(String toDate, String fromDate, String user, String sql)
			throws Exception;

	public List<DashboardGridModel> getFromTxnmGrid(String toDate, String fromDate, String user, String sql)
			throws Exception;

	public List<DashboardDetailsModel> getDashDetailsInTrans(String toDate, String fromDate, String user, String type)
			throws Exception;

	public List<DashboardDetailsModel> getDashDetailsRecm(String toDate, String fromDate, String user, String type)
			throws Exception;

	public List<DashboardDetailsModel> getDashDetailsTxnm(String toDate, String fromDate, String user, String type)
			throws Exception;

	public List<DashboardCashFlowSummeryModel> getCashFlowInTrans(String user, String to, String from) throws Exception;

	public List<DashboardCashFlowSummeryModel> getCashFlowRecm(String user, String to, String from) throws Exception;

	public List<DashboardCashFlowSummeryModel> getCashFlowTxnm(String user, String to, String from) throws Exception;

	public List<DashboardDetailsModel> getCashFlowGridInTrans(String to, String from, String user, String type) throws Exception;

	public List<DashboardDetailsModel> getCashFlowGridTxnm(String toDate, String fromDate, String user, String type) throws Exception;

	public List<DashboardDetailsModel> getCashFlowGridRecm(String toDate, String fromDate, String user, String type) throws Exception;
}
