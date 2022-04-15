package com.duynn.sqa1_n8_yc21_shopmanager.servlet;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;



import static org.junit.jupiter.api.Assertions.*;

class mainTest {
    private static Logger logger;
    static {
        String filename = "log/test.log";
        System.setProperty("logFilename", filename);
        logger = LogManager.getLogger(mainTest.class);
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            logger.info("Test " + description.getDisplayName() + " failed");
        }

        @Override
        protected void succeeded(Description description) {
            logger.info("Test " + description.getDisplayName() + " succeeded");
        }
    };

    @Test
    void testMain() {

        logger.info("Test main");
        logger.error("Test main error");
        logger.debug("Test main debug");
//        assertEquals(1, 1);
    }
}
//    private static String watchedLog;
//
//    @Rule
//    public TestWatcher watchman= new TestWatcher() {
//        @Override
//        protected void failed(Throwable e, Description description) {
//            watchedLog+= description + "\n";
//        }
//
//        @Override
//        protected void succeeded(Description description) {
//            watchedLog+= description + " " + "success!\n";
//        }
//    };
//
//
//    @Test
//    void add() {
//        assertEquals(4, main.add(2, 2));
//        System.out.println(watchedLog);
//    }
//
//    @Test
//    void multiply() {
//        assertAll(() -> assertEquals(4, main.multiply(2, 2)),
//                () -> assertEquals(-4, main.multiply(2, -2)),
//                () -> assertEquals(4, main.multiply(-2, -2)),
//                () -> assertEquals(0, main.multiply(1, 0)));
//        System.out.println(watchedLog);
//    }
//}