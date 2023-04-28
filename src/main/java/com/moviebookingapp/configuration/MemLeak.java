//package com.moviebookingapp.configuration;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//public class MemLeak extends ServletContextEvent {
//
//    public MemLeak(ServletContext source) {
//        super(source);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            try {
//                DriverManager.deregisterDriver(driver);
//                logger.info(String.format("deregistering jdbc driver: %s", driver));
//            } catch (SQLException e) {
//                logger.info(String.format("Error deregistering driver %s", driver), e);
//            }
//        }
//        try { Thread.sleep(2000L); } catch (Exception e) {} // Use this thread sleep
//    }
//}
//
//
