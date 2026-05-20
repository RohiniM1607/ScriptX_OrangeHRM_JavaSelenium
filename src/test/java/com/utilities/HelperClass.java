package com.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class HelperClass {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HelperClass.class);

    public void setupBrowser(String url) {

        logger.info("Launching Chrome browser");
        driver = new ChromeDriver();

        logger.info("Maximize the Browser window");
        driver.manage().window().maximize();

        logger.info("Applying implicit wait");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger.info("Opening URL: " + url);
        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitForElement(WebElement element) {

        logger.info("Waiting for element visibility");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {

        logger.info("Waiting for element to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementLocated(By locator) {

        logger.info("Waiting for element located by: " + locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterText(WebElement element, String value) {

        logger.info("Entering text: " + value);
        waitForElement(element);
        element.clear();
        element.sendKeys(value);
    }

    public void clickElement(WebElement element) {

        logger.info("Clicking on element");
        waitForElementToBeClickable(element);
        element.click();
    }

    public void takeScreenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed() && driver != null) {

            try {
                logger.error("Scenario failed: " + scenario.getName());
                logger.info("Capturing screenshot for failed scenario");

                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", scenario.getName());

                File folder = new File("target/screenshots");

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String screenshotName = scenario.getName().replaceAll(" ", "_");
                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destination = new File(folder, screenshotName + ".png");

                FileUtils.copyFile(source, destination);

                logger.info("Screenshot saved at: " + destination.getAbsolutePath());

            } catch (IOException e) {
                logger.error("Failed to save screenshot", e);
                e.printStackTrace();
            }
        }
    }

    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            logger.error("Scenario failed. Taking screenshot.");
            takeScreenshotOnFailure(scenario);
        } else {
            logger.info("Scenario passed: " + scenario.getName());
        }

        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            driver = null;
        }
    }
}