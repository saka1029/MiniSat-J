package minisatj.core;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    public static final Logger logger = Logger.getLogger("minisat");

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tFT%1$tT.%1$tL %4$s %5$s %6$s%n");
    }
    
    public static boolean isInfoLoggable() { return logger.isLoggable(Level.INFO); }
    public static void info(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    public static boolean isFineLoggable() { return logger.isLoggable(Level.FINE); }
    public static void fine(String format, Object... args) {
        logger.fine(String.format(format, args));
    }

    public static boolean isWarnLoggable() { return logger.isLoggable(Level.WARNING); }
    public static void warn(String format, Object... args) {
        logger.warning(String.format(format, args));
    }

    public static boolean isErrorLoggable() { return logger.isLoggable(Level.SEVERE); }
    public static void error(String format, Object... args) {
        logger.severe(String.format(format, args));
    }
}
