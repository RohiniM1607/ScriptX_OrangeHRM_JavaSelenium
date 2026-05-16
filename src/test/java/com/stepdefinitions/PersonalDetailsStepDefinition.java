package com.stepdefinitions;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import com.actions.DashBoardActions;
import com.actions.LoginActions;
import com.actions.PersonalDetailsActions;
import com.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonalDetailsStepDefinition {

    LoginActions loginActions;
    DashBoardActions dashBoardActions;
    PersonalDetailsActions personalDetailsActions;
    LoginPage loginPage;

    @Given("Employee is on OrangeHRM login page")
    public void employee_is_on_orangehrm_login_page() {
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

    @When("Employee enters valid username and password")
    public void employee_enters_valid_username_and_password(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("username").trim();
        String password = credentials.get(0).get("password").trim();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("Employee clicks on login button")
    public void employee_clicks_on_login_button() {
        loginActions.clickLogin();
    }

    @Given("the Employee is on the Dashboard page")
    public void the_employee_is_on_the_dashboard_page() {
        dashBoardActions = new DashBoardActions();
        Assert.assertTrue(dashBoardActions.isDashboardDisplayed(), "Dashboard is not displayed!");
    }

    @When("Employee navigates to My Info page")
    public void employee_navigates_to_my_info_page() {
        dashBoardActions.navigateToMyInfo();
        personalDetailsActions = new PersonalDetailsActions();
    }

    @And("Employee updates personal details with following data")
    public void employee_updates_personal_details_with_following_data(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        personalDetailsActions.updatePersonalDetails(data);
    }

    @And("Employee clicks on Save button")
    public void employee_clicks_on_save_button() {
        personalDetailsActions.clickSave();
    }

    @Then("Personal details should be updated successfully")
    public void personal_details_should_be_updated_successfully() {
        String message = personalDetailsActions.getSuccessMessage();
        Assert.assertEquals(message, "Success", "Success message mismatch!");
    }
}