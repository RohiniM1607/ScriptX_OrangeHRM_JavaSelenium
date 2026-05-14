package com.hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
	public void tearDown() {
		driver.quit();
	}
}