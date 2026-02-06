package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    // Create a Logger for this class
    private static final Logger logger = LogManager.getLogger(Log.class);

    // Log info messages
    public static void info(String message) {
        logger.info(message);
    }

    // Log debug messages
    public static void debug(String message) {
        logger.debug(message);
    }

    // Log warning messages
    public static void warn(String message) {
        logger.warn(message);
    }

    // Log error messages
    public static void error(String message) {
        logger.error(message);
    }
}
