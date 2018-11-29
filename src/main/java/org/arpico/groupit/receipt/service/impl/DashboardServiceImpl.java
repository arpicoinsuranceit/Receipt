package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.arpico.groupit.receipt.dao.DashboardDao;
import org.arpico.groupit.receipt.dto.DashboardPieDto;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.dto.NameSeriesDto;
import org.arpico.groupit.receipt.dto.NameValueDto;
import org.arpico.groupit.receipt.dto.NameValuePairDto;
import org.arpico.groupit.receipt.model.DashboardCashFlowSummeryModel;
import org.arpico.groupit.receipt.model.DashboardDetailsModel;
import org.arpico.groupit.receipt.model.DashboardGridModel;
import org.arpico.groupit.receipt.model.DashboardPieModel;
import org.arpico.groupit.receipt.model.PayModeGridModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private JwtDecoder jwtDecoder;

	@Autowired
	private DashboardDao dashboardDao;

	@Override
	public DashboardPieDto getDashboardPie(Date to, Date from, String token) throws Exception {

		Date toInTran = to;
		Calendar c = Calendar.getInstance();
		c.setTime(to);
		c.add(Calendar.DATE, 1);
		toInTran = c.getTime();

		DashboardPieDto dashboardPieDto = new DashboardPieDto();

		String user = jwtDecoder.generate(token);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		List<NameValuePairDto> dtos = new ArrayList<>();

		dtos.add(new NameValuePairDto("New Business", "0", "0"));
		dtos.add(new NameValuePairDto("Proposal", "0", "0"));
		dtos.add(new NameValuePairDto("Policy", "0", "0"));
		dtos.add(new NameValuePairDto("Misc. INV", "0", "0"));
		dtos.add(new NameValuePairDto("Misc. GL", "0", "0"));

		dashboardPieDto.setNameValues(dtos);

		Double total = 0.00;

		String toDate = format.format(to);
		String toDateInTran = format.format(toInTran);
		String fromDate = format.format(from);

		List<DashboardPieModel> translist = dashboardDao.getFromInTransaction(toDateInTran, fromDate, user);

		for (DashboardPieModel e : translist) {
			switch (e.getDocCode()) {
			case "RCNB":
				NameValuePairDto dto1 = dtos.get(0);
				dto1.setCount(e.getCount().toString());
				dto1.setValue(e.getAmount().toString());

				dtos.set(0, dto1);

				break;
			case "RCPP":
				NameValuePairDto dto2 = dtos.get(1);
				dto2.setCount(e.getCount().toString());
				dto2.setValue(e.getAmount().toString());

				dtos.set(1, dto2);
				break;
			case "RCPL":
				NameValuePairDto dto3 = dtos.get(2);
				dto3.setCount(e.getCount().toString());
				dto3.setValue(e.getAmount().toString());

				dtos.set(2, dto3);
				break;

			default:
				break;
			}

			total += e.getAmount();
		}

		List<DashboardPieModel> recmList = dashboardDao.getFromRecm(toDate, fromDate, user);

		if (recmList != null) {
			DashboardPieModel dashboardPieModel = recmList.get(0);

			NameValuePairDto dto2 = dtos.get(4);
			dto2.setCount(dashboardPieModel.getCount().toString());
			dto2.setValue(dashboardPieModel.getAmount().toString());

			dtos.set(4, dto2);

			total += dashboardPieModel.getAmount();

		}

		List<DashboardPieModel> txnmList = dashboardDao.getFromDocTxnm(toDate, fromDate, user);

		if (txnmList != null) {
			DashboardPieModel dashboardPieModel = txnmList.get(0);

			NameValuePairDto dto2 = dtos.get(3);
			dto2.setCount(dashboardPieModel.getCount().toString());
			dto2.setValue(dashboardPieModel.getAmount().toString());

			dtos.set(3, dto2);

			total += dashboardPieModel.getAmount();
		}

		dashboardPieDto.setTotal(total);

		return dashboardPieDto;
	}

	@Override
	public List<NameSeriesDto> getDashboardNameSeries(Date to, Date from, String token, String type) throws Exception {
		List<NameSeriesDto> nameSeriesDtos = new ArrayList<>();
		List<String> datesInRange = new ArrayList<>();

		String[] receiptModels = { "New Business", "Proposal", "Policy", "Misc. INV", "Misc. GL" };

		// Date toInTran = to;
		Calendar c = Calendar.getInstance();
		c.setTime(to);
		c.add(Calendar.DATE, 1);
		// toInTran = c.getTime();

		///////////////////// Date Convert to String///////////////////////////
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = format.format(to);
		// String toDateInTran = format.format(toInTran);
		String fromDate = format.format(from);
		String user = jwtDecoder.generate(token);

		//////////////////// Get Date List and Sql for Dao /////////////////

		SimpleDateFormat sdf = null;
		String sql = "";
		String sql2 = "";

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(from);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(to);

		if (type.equalsIgnoreCase("d")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (calendar.before(endCalendar) || calendar.equals(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(sdf.format(result));
				calendar.add(Calendar.DATE, 1);
			}
			sql = ", year(creadt), month(creadt), day(creadt)";
			sql2 = ", year(CRE_DATE), month(CRE_DATE), day(CRE_DATE)";

		} else if (type.equalsIgnoreCase("m")) {
			sdf = new SimpleDateFormat("yyyy-MM");

			while (calendar.before(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(sdf.format(result));
				calendar.add(Calendar.MONTH, 1);
			}
			sql = ", year(creadt), month(creadt)";
			sql2 = ", year(CRE_DATE), month(CRE_DATE)";

		} else if (type.equalsIgnoreCase("y")) {
			sdf = new SimpleDateFormat("yyyy");

			while (calendar.before(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(sdf.format(result));
				calendar.add(Calendar.YEAR, 1);
			}
			sql = ", year(creadt)";
			sql2 = ", year(CRE_DATE)";
		}

		List<DashboardGridModel> models = dashboardDao.getFromInTransactionsGrid(toDate, fromDate, user, sql);
		List<DashboardGridModel> modelsRecm = dashboardDao.getFromRecmGrid(toDate, fromDate, user, sql2);
		List<DashboardGridModel> modelsTxnm = dashboardDao.getFromTxnmGrid(toDate, fromDate, user, sql2);
		//////////////////////////// initialize Dates to
		//////////////////////////// nameSeriesDtos////////////////////////////////////

		datesInRange.forEach(e -> {
			NameSeriesDto nameSeriesDto = new NameSeriesDto();
			nameSeriesDto.setName(e);

			List<NameValueDto> nameValueDtos = new ArrayList<>();

			for (int i = 0; i < 5; i++) {
				NameValueDto nameValueDto = new NameValueDto();
				nameValueDto.setName(receiptModels[i]);
				nameValueDto.setValue("0.00");
				nameValueDtos.add(nameValueDto);

				for (DashboardGridModel model : models) {

					String date = getDate(model, type);



					switch (i) {
					case 0:

						if (date.equals(e) && model.getDocCode().equals("RCNB")) {
							nameValueDto.setValue(model.getAmount().toString());
						}

						break;
					case 1:

						if (date.equals(e) && model.getDocCode().equals("RCPP")) {
							nameValueDto.setValue(model.getAmount().toString());
						}

						break;
					case 2:

						if (date.equals(e) && model.getDocCode().equals("RCPL")) {
							nameValueDto.setValue(model.getAmount().toString());
						}

						break;
					default:
						break;
					}
				}

				switch (i) {
				case 3:
					modelsTxnm.forEach(element -> {
						if (getDate(element, type).equalsIgnoreCase(e)
								&& element.getDocCode().equalsIgnoreCase("OIIS")) {
							nameValueDto.setValue(element.getAmount().toString());
						}
					});
					break;

				case 4:
					modelsRecm.forEach(element -> {
						if (getDate(element, type).equalsIgnoreCase(e)
								&& element.getDocCode().equalsIgnoreCase("GLRC")) {
							nameValueDto.setValue(element.getAmount().toString());
						}
					});
					break;

				default:
					break;
				}

			}

			nameSeriesDto.setSeries(nameValueDtos);
			nameSeriesDtos.add(nameSeriesDto);
		});

		return nameSeriesDtos;
	}

	private String getDate(DashboardGridModel model, String type) {

		String date = "";

		String day = model.getDay();
		day = day.length() < 2 ? "0" + day : day;

		String month = model.getMonth();
		month = month.length() < 2 ? "0" + month : month;

		String year = model.getYear();

		if (type.equalsIgnoreCase("d")) {
			date = year + "-" + month + "-" + day;
		} else if (type.equalsIgnoreCase("m")) {
			date = year + "-" + month;
		} else if (type.equalsIgnoreCase("y")) {
			date = year;
		}
		return date;
	}

	@Override
	public List<LastReceiptSummeryDto> getDetails(String token, Date toDate, Date fromDate, String type)
			throws Exception {
		String user = jwtDecoder.generate(token);

		// Date toDateInTran = toDate;
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.DATE, 1);
		// toDateInTran = c.getTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String to = format.format(toDate);
		String from = format.format(fromDate);
		// String toInTran = format.format(toDateInTran);

		List<LastReceiptSummeryDto> dtos = new ArrayList<>();

		List<DashboardDetailsModel> dashboardDetailsModels = null;

		switch (type) {
		case "RCNB":

			dashboardDetailsModels = dashboardDao.getDashDetailsInTrans(to, from, user, type);

			break;
		case "RCPP":

			dashboardDetailsModels = dashboardDao.getDashDetailsInTrans(to, from, user, type);

			break;
		case "RCPL":

			dashboardDetailsModels = dashboardDao.getDashDetailsInTrans(to, from, user, type);

			break;
		case "GLRC":

			dashboardDetailsModels = dashboardDao.getDashDetailsRecm(to, from, user, type);

			break;
		case "OIIS":

			dashboardDetailsModels = dashboardDao.getDashDetailsTxnm(to, from, user, type);

			break;

		default:
			break;
		}

		if (dashboardDetailsModels != null) {
			dashboardDetailsModels.forEach(e -> {
				dtos.add(getLastReceipt(e));
			});
		}

		return dtos;
	}

	private LastReceiptSummeryDto getLastReceipt(DashboardDetailsModel e) {
		LastReceiptSummeryDto dto = new LastReceiptSummeryDto();

		dto.setAmount(e.getAmount());
		dto.setDoccod(e.getDocCode());
		dto.setRemark(e.getRemark());
		dto.setDocNo(e.getDocNumber());
		dto.setCreadt(new SimpleDateFormat("yyyy-MM-dd").format(e.getCreateDate()));

		return dto;
	}

	@Override
	public List<NameValuePairDto> getCashFlowDateils(Date toDate, Date fromDate, String token) throws Exception {

		List<NameValuePairDto> dtos = new ArrayList<>();
		dtos.add(new NameValuePairDto("CS", "0", "0"));
		dtos.add(new NameValuePairDto("CR", "0", "0"));
		dtos.add(new NameValuePairDto("CQ", "0", "0"));
		dtos.add(new NameValuePairDto("DD", "0", "0"));

		String user = jwtDecoder.generate(token);

		// Date toDateInTran = toDate;
		Calendar c = Calendar.getInstance();
		c.setTime(toDate);
		c.add(Calendar.DATE, 1);
		// toDateInTran = c.getTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String to = format.format(toDate);
		String from = format.format(fromDate);
		// String toInTran = format.format(toDateInTran);

		List<DashboardCashFlowSummeryModel> inTranModels = dashboardDao.getCashFlowInTrans(user, to, from);
		List<DashboardCashFlowSummeryModel> inRecmModels = dashboardDao.getCashFlowRecm(user, to, from);
		List<DashboardCashFlowSummeryModel> inTxnmModels = dashboardDao.getCashFlowTxnm(user, to, from);

		inTxnmModels.forEach(System.out::println);

		inTranModels.forEach(e -> {
			if (e.getPayMode().equalsIgnoreCase("CS")) {

				NameValuePairDto dto = dtos.get(0);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(0, dto);

			}
			if (e.getPayMode().equalsIgnoreCase("CR")) {
				NameValuePairDto dto = dtos.get(1);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(1, dto);
			}
			if (e.getPayMode().equalsIgnoreCase("CQ")) {
				NameValuePairDto dto = dtos.get(2);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(2, dto);
			}
			if (e.getPayMode().equalsIgnoreCase("DD")) {
				NameValuePairDto dto = dtos.get(3);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(3, dto);
			}

		});

		inRecmModels.forEach(e -> {



			if (e.getPayMode().equalsIgnoreCase("CS")) {



				NameValuePairDto dto = dtos.get(0);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(0, dto);

			}
			if (e.getPayMode().equalsIgnoreCase("CR")) {
				NameValuePairDto dto = dtos.get(1);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(1, dto);
			}
			if (e.getPayMode().equalsIgnoreCase("CQ")) {
				NameValuePairDto dto = dtos.get(2);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(2, dto);
			}
			if (e.getPayMode().equalsIgnoreCase("DD")) {
				NameValuePairDto dto = dtos.get(3);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(3, dto);
			}

		});

		inTxnmModels.forEach(e -> {
			if (e.getPayMode().equalsIgnoreCase("02.CASH")) {

				NameValuePairDto dto = dtos.get(0);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(0, dto);

			}
			if (e.getPayMode().equalsIgnoreCase("03. CREDIT CARD")) {
				NameValuePairDto dto = dtos.get(1);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(1, dto);
			}
			if (e.getPayMode().equalsIgnoreCase("01.CHEQUE") || e.getPayMode().equalsIgnoreCase("04.CHEQUE")) {
				NameValuePairDto dto = dtos.get(2);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(2, dto);
			}
			if (e.getPayMode().equalsIgnoreCase("Direct deposit")) {
				NameValuePairDto dto = dtos.get(3);

				Integer count = Integer.parseInt(dto.getCount()) + e.getCount();
				Double amount = Double.parseDouble(dto.getValue()) + e.getAmount();

				dto.setCount(count.toString());
				dto.setValue(amount.toString());

				dtos.set(3, dto);
			}

		});

		return dtos;
	}

	@Override
	public List<LastReceiptSummeryDto> getCashFlowDateilGrid(String type, Date to, Date from, String token)
			throws Exception {

		// Date toInTran = to;
		Calendar c = Calendar.getInstance();
		c.setTime(to);
		c.add(Calendar.DATE, 1);
		// toInTran = c.getTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = format.format(to);
		// String toDateInTran = format.format(toInTran);
		String fromDate = format.format(from);

		/* String payModeInTran = ""; */
		String payModeTxnm = "";

		switch (type) {
		case "CS":
			payModeTxnm = "'02.CASH'";
			break;
		case "CR":
			payModeTxnm = "'03. CREDIT CARD'";
			break;
		case "CQ":
			payModeTxnm = "'01.CHEQUE', '04.CHEQUE'";
			break;
		case "DD":
			payModeTxnm = "'Direct deposit'";
			break;

		default:
			break;
		}

		List<LastReceiptSummeryDto> dtos = new ArrayList<>();

		String user = jwtDecoder.generate(token);
		List<DashboardDetailsModel> inTransModels = dashboardDao.getCashFlowGridInTrans(toDate, fromDate, user, type);
		List<DashboardDetailsModel> txnmModels = dashboardDao.getCashFlowGridTxnm(toDate, fromDate, user, payModeTxnm);
		List<DashboardDetailsModel> recmModels = dashboardDao.getCashFlowGridRecm(toDate, fromDate, user, type);

		if (inTransModels != null) {
			inTransModels.forEach(e -> {
				dtos.add(getLastReceipt(e));
			});
		}
		if (txnmModels != null) {
			txnmModels.forEach(e -> {
				dtos.add(getLastReceipt(e));
			});
		}
		if (recmModels != null) {
			recmModels.forEach(e -> {
				dtos.add(getLastReceipt(e));
			});
		}

		return dtos;
	}

	@Override
	public List<NameSeriesDto> getDashboardPayMode(Date to, Date from, String token, String type) throws Exception {
		List<NameSeriesDto> nameSeriesDtos = new ArrayList<>();
		List<String> datesInRange = new ArrayList<>();

		String[] receiptModels = { "Cash", "Cheque", "Direct Deposit", "Credit Card" };

		// Date toInTran = to;
		Calendar c = Calendar.getInstance();
		c.setTime(to);
		c.add(Calendar.DATE, 1);
		// toInTran = c.getTime();

		///////////////////// Date Convert to String///////////////////////////
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = format.format(to);
		// String toDateInTran = format.format(toInTran);
		String fromDate = format.format(from);
		String user = jwtDecoder.generate(token);

		//////////////////// Get Date List and Sql for Dao /////////////////

		SimpleDateFormat sdf = null;
		String sql = "";
		String sql2 = "";

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(from);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(to);

		if (type.equalsIgnoreCase("d")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");

			while (calendar.before(endCalendar) || calendar.equals(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(sdf.format(result));
				calendar.add(Calendar.DATE, 1);
			}
			sql = ", year(creadt), month(creadt), day(creadt)";
			sql2 = ", year(CRE_DATE), month(CRE_DATE), day(CRE_DATE)";

		} else if (type.equalsIgnoreCase("m")) {
			sdf = new SimpleDateFormat("yyyy-MM");

			while (calendar.before(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(sdf.format(result));
				calendar.add(Calendar.MONTH, 1);
			}
			sql = ", year(creadt), month(creadt)";
			sql2 = ", year(CRE_DATE), month(CRE_DATE)";

		} else if (type.equalsIgnoreCase("y")) {
			sdf = new SimpleDateFormat("yyyy");

			while (calendar.before(endCalendar)) {
				Date result = calendar.getTime();
				datesInRange.add(sdf.format(result));
				calendar.add(Calendar.YEAR, 1);
			}
			sql = ", year(creadt)";
			sql2 = ", year(CRE_DATE)";
		}

		List<PayModeGridModel> models = dashboardDao.getPayModeFromInTransactionsGrid(toDate, fromDate, user, sql);
		List<PayModeGridModel> modelsRecm = dashboardDao.getPayModeFromFromRecmGrid(toDate, fromDate, user, sql2);
		List<PayModeGridModel> modelsTxnm = dashboardDao.getPayModeFromTxnmGrid(toDate, fromDate, user, sql2);
		//////////////////////////// initialize Dates to
		//////////////////////////// nameSeriesDtos////////////////////////////////////

		datesInRange.forEach(e -> {
			NameSeriesDto seriesDto = new NameSeriesDto();

			seriesDto.setName(e);

			Double cash = 0.0;
			Double cheque = 0.0;
			Double directDep = 0.0;
			Double credit = 0.0;

			for (PayModeGridModel model : models) {
				if (e.equals(getDate(model, type))) {
					switch (model.getPayMode()) {

					case "CS":
						cash = cash + model.getAmount();
						break;
					case "CQ":
						cheque = cheque + model.getAmount();
						break;
					case "DD":
						directDep = directDep + model.getAmount();
						break;
					case "CR":
						credit = credit + model.getAmount();
						break;

					default:
						break;
					}
				}
			}

			for (PayModeGridModel model : modelsRecm) {
				if (e.equals(getDate(model, type))) {
					if (model.getPayMode().equalsIgnoreCase("CS")) {
						cash = cash + model.getAmount();
					}
					if (model.getPayMode().equalsIgnoreCase("CR")) {
						credit = credit + model.getAmount();
					}
					if (model.getPayMode().equalsIgnoreCase("DD")) {
						directDep = directDep + model.getAmount();
					}
					if (model.getPayMode().equalsIgnoreCase("CQ")) {
						cheque = cheque + model.getAmount();
					}

				}
			}

			for (PayModeGridModel model : modelsTxnm) {
				if (e.equals(getDate(model, type))) {
					if (model.getPayMode().equalsIgnoreCase("02.CASH")) {
						cash = cash + model.getAmount();
					}
					if (model.getPayMode().equalsIgnoreCase("03. CREDIT CARD")) {
						credit = credit + model.getAmount();
					}
					if (model.getPayMode().equalsIgnoreCase("Direct deposit")) {
						directDep = directDep + model.getAmount();
					}
					if (model.getPayMode().equalsIgnoreCase("01.CHEQUE")
							|| model.getPayMode().equalsIgnoreCase("04.CHEQUE")) {
						cheque = cheque + model.getAmount();
					}

				}
			}

			List<NameValueDto> dtos = new ArrayList<>();

			for (int i = 0; i < receiptModels.length; i++) {

				NameValueDto dto = new NameValueDto();
				dto.setName(receiptModels[i]);

				switch (i) {
				case 0:
					dto.setValue(cash.toString());
					break;
				case 1:
					dto.setValue(cheque.toString());
					break;
				case 2:
					dto.setValue(directDep.toString());
					break;
				case 3:
					dto.setValue(credit.toString());
					break;
				default:
					break;
				}

				dtos.add(dto);
			}

			seriesDto.setSeries(dtos);
			nameSeriesDtos.add(seriesDto);

		});

		return nameSeriesDtos;
	}

	private String getDate(PayModeGridModel model, String type) {
		String date = "";

		String day = model.getDay();
		day = day.length() < 2 ? "0" + day : day;

		String month = model.getMonth();
		month = month.length() < 2 ? "0" + month : month;

		String year = model.getYear();

		if (type.equalsIgnoreCase("d")) {
			date = year + "-" + month + "-" + day;
		} else if (type.equalsIgnoreCase("m")) {
			date = year + "-" + month;
		} else if (type.equalsIgnoreCase("y")) {
			date = year;
		}
		return date;
	}

	
	
}
