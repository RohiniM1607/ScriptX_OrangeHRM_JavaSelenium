package com.stepdefinitions;

import com.actions.LoginActions;
import com.actions.Search_user_action;

import io.cucumber.java.en.*;

public class Search_user_Steps {

    LoginActions la;
    Search_user_action sa = new Search_user_action();

    @Given("the admin on the Home page")
    public void the_admin_on_the_home_page() {
        la = new LoginActions();
        la.enterValidCredentials();
        la.clickLogin();
    }

    @And("click on the Admin menu")
    public void click_on_the_admin_menu() {
        sa.clickAdminMenu();
    }

    @When("the Admin enter {string} in the input field")
    public void the_admin_enter_in_the_input_field(String username) {
    	sa.enterUsername(username);
    }

    @And("click on search button")
    public void click_on_search_button() {
        sa.clickSearch();
    }

    @Then("the Admin can see the searched username details")
    public void the_admin_can_see_the_searched_username_details( ) {
        
    }
    @Then("the Admin can see the searched {string} details")
    public void the_admin_can_see_the_searched_details(String UsersName) {
    	sa.verifyUsernameResult(UsersName);
    }

    @When("the Admin select the particular user role")
    public void the_admin_select_the_particular_user_role() {
        sa.selectUserRole();
    }

    @Then("the admin can see the result based on the selected user role")
    public void the_admin_can_see_the_result_based_on_the_selected_user_role() {
        sa.verifyUserRoleResult();
    }

    @When("the admin enters {string}  in the input field")
    public void the_admin_enters_in_the_input_field(String employeeName) {
    	sa.enterEmployeeName(employeeName);
    }

    @Then("the Admin can see the result of enterd {string}")
    public void the_admin_can_see_the_result_of_enterd(String employeeName) {
    	sa.verifyEmployeeNameResult(employeeName);
    }
}
