package com.ideas2it.dvdstore.logger;

import org.apache.log4j.Logger;

import com.ideas2it.dvdstore.common.Constants;

/**
 * <p>
 * The {@code DvdStoreLogger} is used to catch the 
 * exception details during the dvd store operation
 * </p>
 * 
 * @version 1
 * @author Balamurugan M
 */
 
public class DvdStoreLogger {
   
    private static Logger logger 
        = Logger.getLogger(DvdStoreLogger.class.getName());
        
    public void debug(String message) {
        logger.debug(message);
    }
    
    public void info(String message) {
        logger.info(message);
    }
    
    public void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
    
    public static void error(String message, Exception exception) {
        logger.error(message, exception);
    }
        
    public void fatal(String message, Exception exception) {
        logger.fatal(message, exception);
    }
    
}

