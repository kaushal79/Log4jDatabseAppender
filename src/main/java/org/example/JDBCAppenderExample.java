package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCAppenderExample {
    private static final Logger logger = LogManager.getLogger(JDBCAppenderExample.class);

    public static void main(String[] args)
    {
        logger.info("JDBCAppender Example ");
        try {
            logger.debug("Demo Statement");
        } catch (Exception e) {
            logger.error("Runtime error", e);
        }
        System.out.println("Done");
    }

}