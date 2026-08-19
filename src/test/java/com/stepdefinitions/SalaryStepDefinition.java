package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.SalaryActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalaryStepDefinition {

    private static final Logger log = LogManager.getLogger(SalaryStepDefinition.class);

    DashBoardActions dashBoardActions;
    SalaryActions salaryActions;

    @When("Employee navigates to Salary page")
    public void employee_navigates_to_salary_page() {
        log.info("Navigating to Salary page");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToSalary();
        salaryActions = new SalaryActions();
        log.info("Successfully landed on Salary page");
    }

    @And("Employee adds salary details from CSV")
    public void employee_adds_salary_details_from_csv() {
        List<Map<String, String>> records = salaryActions.readSalaryCSV();
        log.info("Total salary records read from CSV: {}", records.size());

        for (Map<String, String> record : records) {
            // This will now show actual values instead of null
            log.info("Processing — SalaryComponent: {}, PayGrade: {}, PayFrequency: {}, Currency: {}, Amount: {}, Comments: {}",
                    record.get("SalaryComponent"),
                    record.get("PayGrade"),
                    record.get("PayFrequency"),
                    record.get("Currency"),
                    record.get("Amount"),
                    record.get("Comments"));

            salaryActions.clickAddButton();
            salaryActions.fillSalaryDetails(record);
            salaryActions.clickSave();

            String message = salaryActions.getSuccessMessage();
            log.info("Success message: {}", message);
            Assert.assertEquals(message, "Success",
                    "Salary save failed for: " + record.get("SalaryComponent"));
        }
    }

    @Then("All salary records should be saved successfully")
    public void all_salary_records_should_be_saved_successfully() {
        // Per-record assertions already done in the step above.
        // This step marks overall scenario completion.
        log.info("All salary records from CSV processed and saved successfully");
    }
}