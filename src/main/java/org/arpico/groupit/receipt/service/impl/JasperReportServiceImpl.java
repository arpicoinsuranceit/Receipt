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

//		Resource resource = new ClassPathResource("mcfpr.jrxml");
//		File file = resource.getFile();
//		
//		//System.out.println(file.getPath());
//		//System.out.println(file.getName());
//		//System.out.println(file.getCanonicalPath());

		// String OUT_PUT = "D:\\performance_detail.pdf";
		//String REPORT = "mcfpr.jrxml";
		
		
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
		
		//System.out.println(fromDate + " -fromDate " + toDate + " -toDate " + branch + " -branch " );
		//System.out.println(branch);

		Map<String, Object> params = new HashMap<>();
		params.put("sbucod", "450");
		params.put("sdate", fromDate);
		params.put("edate", toDate);
		params.put("loccod", branch);
		params.put("zoncod", "%");

//		Resource resource = new ClassPathResource("mcfpr.jrxml");
//		File file = resource.getFile();
//		
//		//System.out.println(file.getPath());
//		//System.out.println(file.getName());
//		//System.out.println(file.getCanonicalPath());

		// String OUT_PUT = "D:\\performance_detail.pdf";
		//String REPORT = "mcfpr.jrxml";
		
		
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
		

//		Resource resource = new ClassPathResource("mcfpr.jrxml");
//		File file = resource.getFile();
//		
//		//System.out.println(file.getPath());
//		//System.out.println(file.getName());
//		//System.out.println(file.getCanonicalPath());

		// String OUT_PUT = "D:\\performance_detail.pdf";
		//String REPORT = "mcfpr.jrxml";
		
		
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

//		Resource resource = new ClassPathResource("mcfpr.jrxml");
//		File file = resource.getFile();
//		
//		//System.out.println(file.getPath());
//		//System.out.println(file.getName());
//		//System.out.println(file.getCanonicalPath());

		// String OUT_PUT = "D:\\performance_detail.pdf";
		//String REPORT = "mcfpr.jrxml";
		
		
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

}
