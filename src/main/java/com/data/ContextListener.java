package com.data;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener {

	private static Logger log = Logger.getLogger(ContextListener.class);

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String fullPath = "D:\\Log4j\\log4j.properties";
		Properties logProperties = new Properties();

		try {

			logProperties.load(new FileInputStream(fullPath));
			PropertyConfigurator.configure(logProperties);

			log = Logger.getLogger(ContextListener.class);
			log.info("log4j properties loading");

		} catch (Exception e) {
			log.info("Cannot load log4j.properties");

		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("contextDestroyed");

	}

}
