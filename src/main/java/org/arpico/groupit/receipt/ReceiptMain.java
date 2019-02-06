package org.arpico.groupit.receipt;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ReceiptMain extends SpringBootServletInitializer{

	private static Logger logger = LogManager.getLogger(ReceiptMain.class);
	
	public static void main (String args[]) {
		
		logger.info("Starting Receipt ...");;
		
		SpringApplication.run(ReceiptMain.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ReceiptMain.class);
	}
}
