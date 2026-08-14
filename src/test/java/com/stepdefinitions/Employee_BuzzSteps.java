package com.stepdefinitions;

import java.io.IOException;
import java.util.UUID;

import org.testng.Assert;

import com.actions.Employee_BuzzActions;
import com.utilities.CSVReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Employee_BuzzSteps {

    Employee_BuzzActions employeeBuzz =
            new Employee_BuzzActions();

    CSVReader csv =
            new CSVReader();

    String postContent;
    String updatedContent;


    // =========================================================
    // Generate Unique Content
    // =========================================================

    private String uniqueContent(
            String scenarioPrefix,
            String baseText) {

        return scenarioPrefix
                + "_"
                + baseText
                + "_"
                + UUID.randomUUID()
                         .toString()
                         .substring(0, 8);
    }


    // =========================================================
    // Background
    // =========================================================

    @Given("Employee navigates to the Buzz page")
    public void employee_navigates_to_the_buzz_page() {

        employeeBuzz.navigateToBuzz();
    }


    // =========================================================
    // Create Post
    // =========================================================

    @When("employee adds a new buzz post")
    public void employee_adds_a_new_buzz_post()
            throws IOException {

        String[] data =
                csv.getCSVData(
                        "src/test/resources/testdata/EmployeeBuzzData.csv"
                );

        postContent =
                uniqueContent(
                        "BuzzPost",
                        data[0]
                );

        System.out.println(
                Thread.currentThread().getName()
                + " | Creating post: "
                + postContent
        );

        employeeBuzz.enterBuzzContent(postContent);

        employeeBuzz.clickPostButton();
    }


    // =========================================================
    // Verify Created
    // =========================================================

    @Then("the employee buzz post should be added successfully")
    public void the_employee_buzz_post_should_be_added_successfully() {

        boolean result =
                employeeBuzz.verifyPostCreated(postContent);

        Assert.assertTrue(
                result,
                "Employee buzz post was not added: "
                        + postContent
        );
    }


    // =========================================================
    // Edit
    // =========================================================

    @When("employee edits the buzz post")
    public void employee_edits_the_buzz_post()
            throws IOException {

        String[] data =
                csv.getCSVData(
                        "src/test/resources/testdata/EmployeeBuzzData.csv"
                );

        updatedContent =
                uniqueContent(
                        "UpdatedPost",
                        data[1]
                );

        System.out.println(
                Thread.currentThread().getName()
                + " | Editing post: "
                + postContent
        );

        System.out.println(
                Thread.currentThread().getName()
                + " | Updated content: "
                + updatedContent
        );

        employeeBuzz.editBuzzPost(
                postContent,
                updatedContent
        );
    }


    // =========================================================
    // Verify Updated
    // =========================================================

    @Then("the employee buzz post should be updated successfully")
    public void the_employee_buzz_post_should_be_updated_successfully() {

        boolean result =
                employeeBuzz.verifyPostUpdated(
                        postContent,
                        updatedContent
                );

        Assert.assertTrue(
                result,
                "Employee buzz post was not updated. "
                        + "Old content: "
                        + postContent
                        + " | Updated content: "
                        + updatedContent
        );
    }


    // =========================================================
    // Delete
    // =========================================================

    @When("employee deletes the buzz post")
    public void employee_deletes_the_buzz_post() {

        System.out.println(
                Thread.currentThread().getName()
                + " | Deleting post: "
                + postContent
        );

        employeeBuzz.deleteBuzzPost(
                postContent
        );
    }


    // =========================================================
    // Verify Deleted
    // =========================================================

    @Then("the employee buzz post should be deleted successfully")
    public void the_employee_buzz_post_should_be_deleted_successfully() {

        boolean result =
                employeeBuzz.verifyPostDeleted(
                        postContent
                );

        Assert.assertTrue(
                result,
                "Employee buzz post is still visible after deletion: "
                        + postContent
        );
    }
}