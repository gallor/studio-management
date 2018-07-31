package com.gallor.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// Loop through all drivers

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = (Driver) drivers.nextElement();
			if (driver.getClass().getClassLoader() == cl) {
				// This driver was registered by the webapp's ClassLoader, so deregister it:
				try {
					System.out.println("Deregistering JDBC driver "  + driver);
					DriverManager.deregisterDriver(driver);
				} catch (SQLException ex) {
					System.out.println("Error deregistering JDBC driver " + driver + " " + ex);
				}
			} else {
				// driver was not registered by the webapp's ClassLoader and may be in use elsewhere
				System.out.println("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader " + driver);
			}
		}
		
	}
	
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
	}

}
