package com.duynn.sqa1_n8_yc21_shopmanager.servlet;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.LocalDateTime;
import java.util.stream.DoubleStream;

public class main {
    private static Logger logger = LogManager.getLogger(main.class);
    public static void main(String[] args) throws InterruptedException {
        // Create a new instance of the Firefox driver
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        WebDriverManager.edgedriver().setup();
//        WebDriver driver = new EdgeDriver();
//
//        //Launch the Online Store Website
//        driver.get("https://www.google.com/");
//
//        // Print a Log In message to the screen
//        System.out.println(driver.getTitle());
//
//        //Wait for 5 Sec
//        Thread.sleep(5);
//
//        // Close the driver
//        driver.quit();

        String filename = "C:/tmp/test.log";
        System.setProperty("logFilename", filename);
        logger.info("Test main");
        logger.error("Test main error");
        logger.debug("Test main debug");
    }
    static double add(double... operands) {
        return DoubleStream.of(operands)
                .sum();
    }

    static double multiply(double... operands) {
        return DoubleStream.of(operands)
                .reduce(1, (a, b) -> a * b);
    }
}
