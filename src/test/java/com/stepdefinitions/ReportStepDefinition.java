package com.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.ReportActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportStepDefinition {

    private static final Logger log = LogManager.getLogger(ReportStepDefinition.class);

    DashBoardActions dashBoardActions;
    ReportToActions reportToActions;

    @When("Employee navigates to Report-To page")
    public void employee_navigates_to_report_to_page() {
        log.info("Navigating to Report-To page");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToReportTo();
        reportToActions = new ReportToActions();
        log.info("Successfully landed on Report-To page");
    }

    // ── Supervisor steps ────────────────────────────────────────────────

    @And("Employee clicks on Add Supervisor button")
    public void employee_clicks_on_add_supervisor_button() {
        log.info("Clicking Add button in Assigned Supervisors section");
        reportToActions.clickAddSupervisor();
    }

    @And("Employee fills supervisor details with {string} and {string}")
    public void employee_fills_supervisor_details_with(
            String supervisorName, String reportingMethod) {
        log.info("Filling supervisor — Name: {}, ReportingMethod: {}",
                supervisorName, reportingMethod);
        reportToActions.fillSupervisorDetails(supervisorName, reportingMethod);
    }

    @And("Employee clicks Save in Supervisor section")
    public void employee_clicks_save_in_supervisor_section() {
        log.info("Clicking Save in Supervisor section");
        reportToActions.clickSaveSupervisor();
    }

    @Then("Supervisor record should be saved successfully")
    public void supervisor_record_should_be_saved_successfully() {
        log.info("Verifying success message after saving supervisor");
        String actual = reportToActions.getSuccessMessage();
        log.info("Success message: {}", actual);
        Assert.assertEquals(actual, "Success",
                "Supervisor record was not saved. Got: " + actual);
        log.info("Supervisor saved successfully");
    }

    // ── Subordinate steps ───────────────────────────────────────────────

    @And("Employee clicks on Add Subordinate button")
    public void employee_clicks_on_add_subordinate_button() {
        log.info("Clicking Add button in Assigned Subordinates section");
        reportToActions.clickAddSubordinate();
    }

    @And("Employee fills subordinate details with {string} and {string}")
    public void employee_fills_subordinate_details_with(
            String subordinateName, String reportingMethod) {
        log.info("Filling subordinate — Name: {}, ReportingMethod: {}",
                subordinateName, reportingMethod);
        reportToActions.fillSubordinateDetails(subordinateName, reportingMethod);
    }

    @And("Employee clicks Save in Subordinate section")
    public void employee_clicks_save_in_subordinate_section() {
        log.info("Clicking Save in Subordinate section");
        reportToActions.clickSaveSubordinate();
    }

    @Then("Subordinate record should be saved successfully")
    public void subordinate_record_should_be_saved_successfully() {
        log.info("Verifying success message after saving subordinate");
        String actual = reportToActions.getSuccessMessage();
        log.info("Success message: {}", actual);
        Assert.assertEquals(actual, "Success",
                "Subordinate record was not saved. Got: " + actual);
        log.info("Subordinate saved successfully");
    }
}