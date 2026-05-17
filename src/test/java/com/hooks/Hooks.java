package com.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.utilities.ConfigReader;
import com.utilities.HelperClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private static final Logger logger = LogManager.getLogger(Hooks.class);

    ConfigReader config = new ConfigReader("config.properties");
    HelperClass helper = new HelperClass();

    @Before
    public void setup(Scenario scenario) {
    	logger.info("Starting Scenario: " + scenario.getName());
        helper.setupBrowser(config.getData("url"));
    }

    @After
    public void tearDown(Scenario scenario) {
    	logger.info("Ending Scenario: " + scenario.getName());
        helper.tearDown(scenario);
    }
}