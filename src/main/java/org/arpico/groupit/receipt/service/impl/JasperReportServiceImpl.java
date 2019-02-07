package org.arpico.groupit.receipt.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.arpico.groupit.receipt.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@Transactional
public class JasperReportServiceImpl implements JasperReportService{
	
	@Autowired
	private DataSource dataSource;


	@Override
	public byte[] receiptRegisterReport(String fromDate, String toDate, String userCode) throws Exception {
		
		//System.out.println(fromDate + " -fromDate " + toDate + " -toDate " + userCode + " -userCode " );

		Map<String, Object> params = new HashMap<>();
		params.put("psbucod", "450");
		params.put("pfdate", fromDate);
		params.put("ptdate", toDate);
		params.put("creaby", userCode);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("receipt_register.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] lapsedSummeryReport(String fromDate, String toDate, String branch) throws Exception {
		
		branch=branch.replaceAll(",$", "");

		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("loccod", branch);
		params.put("zoncod", "%");
		
		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("daly_lap_sum.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}
	
	@Override
	public byte[] premiumDueReport(String agent,String branch) throws Exception {
		
		branch=branch.replaceAll(",$", "");
		agent=agent.replaceAll(",$", "");
		
		//System.out.println(agent + " -agent " + branch + " -branch " );

		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("loccod", branch);
		params.put("agncod", agent);
		params.put("rgncod", "%");
		params.put("zoncod", "%");
		
		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("Premium_due_year.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] paymentHistory(String polnum) throws Exception {
		//System.out.println(polnum + " -polnum ");

		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("polnum", polnum);
		
		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("pay_his.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] detailsOfPolicies(String fromDate, String toDate, String ic, String ul, String branch, String zone,
			String region, String sp) throws Exception {
		
		System.out.println(fromDate + " -fromDate" + toDate +" -toDate"+ branch + " -branch" + region + " -region" + zone +" -zone"+ sp + " -sp" + ic + " -ic" + ul + " -ul");
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("loccod", branch);
		params.put("rgncod", region);
		params.put("zoncod", zone);
		params.put("sinprm", sp);
		params.put("agncod", ic);
		params.put("unlcod", ul);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("detai_pol_list.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] mcfpReport(String fromDate, String toDate, String advisor, String branch) throws Exception {
		System.out.println(fromDate + " -fromDate " + toDate + " -toDate " + advisor + " -advisor " + branch + " -branch");
		
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("agncod", advisor);
		params.put("loccod", branch);
		
		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("mcfpr.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] proposalRegister(String fromDate, String toDate, String zone, String region, String branch,
			String unl, String frequency) throws Exception {
		
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("zoncod", zone);
		params.put("rgncod", region);
		params.put("loccod", branch);
		params.put("agncod", unl);
		params.put("catcod", frequency);

		
		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("prop_register.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] pendingRequirements(String advisor, String branch, String zone, String region) throws Exception {
	
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("agncod", advisor);
		params.put("loccod", branch);
		params.put("rgncod", region);
		params.put("zoncod", zone);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("Pend_req_advi.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] grantStmtBranch(String branch, String year, String month, String code , String status) throws Exception {
		
		System.out.println(branch + " -branch" + year +" -year"+ month + " -month" + code + " -code" + status +" -status");
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("year", year);
		params.put("month", month);
		params.put("agnsta", status);
		params.put("agncod", code);
		params.put("loccod", branch);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("comm_agnt_bra.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] policyAcknowledgement(String branch, String year, String month) throws Exception {

		System.out.println(branch + " -branch" + year +" -year"+ month + " -month" );
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("year", year);
		params.put("loccod", branch);
		params.put("period", month);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("biz_gra_sub.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] salesPerfDetail(String fromDate, String toDate, String code, String branch, String product,
			String frequency) throws Exception {

		System.out.println(branch + " -branch" + fromDate +" -fromDate"+ toDate + " -toDate" + code + " -code" + frequency + " -frequency" +  product + " -product");
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("zoncod", "%");
		params.put("rgncod", "%");
		params.put("loccod", branch);
		params.put("prdcod", product);
		params.put("catcod", frequency);
		params.put("agncod", code);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("performance_detail.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] unitIsPerfSummary(String fromDate, String toDate, String branch, String unl, String type,
			String frequency, String product) throws Exception {

		System.out.println(branch + " -branch" + fromDate +" -fromDate"+ toDate + " -toDate" + branch + " -branch" + unl + " -unl" + type + " -type" + frequency + " -frequency" +  product + " -product");
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("zoncod", "%");
		params.put("rgncod", "%");
		params.put("loccod", branch);
		params.put("prdcod", product);
		params.put("catcod", frequency);
		params.put("agncod", unl);
		params.put("subtyp", type);
		params.put("unlcod", unl);
		
		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("performance_summ_unl.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

	@Override
	public byte[] salesPerfSummary(String fromDate, String toDate, String branch, String frequency, String product,
			String so) throws Exception {

		System.out.println(branch + " -branch" + fromDate +" -fromDate"+ toDate + " -toDate" + branch + " -branch" + so + " -so" + frequency + " -frequency" +  product + " -product");
		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("zoncod", "%");
		params.put("rgncod", "%");
		params.put("loccod", branch);
		params.put("prdcod", product);
		params.put("catcod", frequency);
		params.put("sinprm", so);

		JasperReport jr = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			try {
				InputStream input = new ClassPathResource("performance_brn.jrxml").getInputStream();
				JasperDesign jasperDesign = JRXmlLoader.load(input);
				jr = JasperCompileManager.compileReport(jasperDesign);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Exception...");
			}

			try {
				JasperPrint jp;

				jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

				JRPdfExporter export = new JRPdfExporter();
				export.setExporterInput(new SimpleExporterInput(jp));
				export.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
				export.exportReport();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return baos.toByteArray();
	}

}
