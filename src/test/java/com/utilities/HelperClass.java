package com.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class HelperClass {

	public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private static final Logger logger = LogManager.getLogger(HelperClass.class);

	public void setupBrowser(String url, String headless) {
		ChromeOptions options = new ChromeOptions();
		logger.info("Launching Chrome browser in headless mode");

		//options.addArguments("--headless=new");
		options.addArguments("--window-size=1920,1080");

		driver.set(new ChromeDriver(options));
		if (!headless.equalsIgnoreCase("true")) {
			logger.info("Maximize the Browser window");
			driver.get().manage().window().maximize();
		}

		logger.info("Applying implicit wait");
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Opening URL: " + url);
		driver.get().get(url);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void waitForElement(WebElement element) {
		logger.info("Waiting for element visibility");
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		logger.info("Waiting for element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitForElementLocated(By locator) {
		logger.info("Waiting for element located by: " + locator);
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20));
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
		if (scenario.isFailed() && driver.get() != null) {
			try {
				logger.error("Scenario failed: " + scenario.getName());
				logger.info("Capturing screenshot for failed scenario");

				byte[] screenshotBytes = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshotBytes, "image/png", scenario.getName());
				File folder = new File("target/screenshots");

				if (!folder.exists()) {
					folder.mkdirs();
				}

				String screenshotName = scenario.getName().replaceAll(" ", "_");
				File source = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
				String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
				File destination = new File(folder,screenshotName + "_" + timeStamp + ".png");
				FileUtils.copyFile(source, destination);
				logger.info("Screenshot saved at: " + destination.getAbsolutePath());
			} 
			catch (IOException e) {
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

		if (driver.get() != null) {

			logger.info("Closing browser");

			driver.get().quit();

			// CHANGED:
			// remove thread local instance

			driver.remove();
		}
	}
}
