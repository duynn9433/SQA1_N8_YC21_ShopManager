package com.duynn.sqa1_n8_yc21_shopmanager.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTestWatcher implements TestWatcher {
    protected static Logger logger;
    static {
        String filename = "log/test.log";
        System.setProperty("logFilename", filename);
        logger = LogManager.getLogger(MyTestWatcher.class);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        String filename = "log/test.log";
        System.setProperty("logFilename", filename);
        TestWatcher.super.testSuccessful(context);
        logger.info("Test succeeded: " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String filename = "log/test.log";
        System.setProperty("logFilename", filename);
        TestWatcher.super.testFailed(context, cause);
        logger.error("Test failed: " + context.getDisplayName(), cause);
    }

    protected void setLogger(Class className) {
        logger = LogManager.getLogger(className);
    }
}

