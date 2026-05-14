package com.hooks;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.utilities.ConfigReader;

public class Hooks {

	public static WebDriver driver;

	ConfigReader config = new ConfigReader("config.properties");

	@Before
	public void setup() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(config.getData("url"));
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("target/screenshots/" + scenario.getName() + ".png");
			FileUtils.copyFile(src, dest);
		}

		driver.quit();
	}
}