package com.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.actions.BuzzActions;
import com.actions.LoginActions;
import com.utilities.CSVReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuzzStepDefinition {

	LoginActions login = new LoginActions();

	BuzzActions buzz = new BuzzActions();

	CSVReader csv = new CSVReader();

	String postContent;
	int beforeCount;
	int afterCount;
	boolean postDeleted;

	@Given("admin is logged into OrangeHRM Buzz page")
	public void admin_is_logged_into_orangehrm_buzz_page() {

		login.enterValidCredentials();

		login.clickLogin();

		buzz.navigateToBuzz();
	}

	@When("admin enters valid buzz content")
	public void admin_enters_valid_buzz_content() throws IOException {

		String[] data = csv.getCSVData("src/test/resources/testdata/BuzzData.csv");

		postContent = data[0];

		buzz.enterBuzzContent(postContent);
	}

	@When("admin clicks the post button")
	public void admin_clicks_the_post_button() {

		buzz.clickPostButton();
	}

	@Then("the buzz post should be published successfully")
	public void the_buzz_post_should_be_published_successfully() {

		Assert.assertTrue(buzz.verifyPostCreated(postContent), "Buzz post creation failed");
	}

	@When("admin leaves the buzz content empty")
	public void admin_leaves_the_buzz_content_empty() {

		beforeCount = buzz.getBuzzPostCount();

		buzz.enterBuzzContent("");
	}

	@Then("no new buzz post should be created")
	public void no_new_buzz_post_should_be_created() {

		afterCount = buzz.getBuzzPostCount();

		Assert.assertEquals(afterCount, beforeCount, "Empty post should not be created");
	}

	@When("admin publishes a buzz post")
	public void admin_publishes_a_buzz_post() throws IOException {

		String[] data = csv.getCSVData("src/test/resources/testdata/BuzzData.csv");

		postContent = data[0];

		buzz.enterBuzzContent(postContent);

		buzz.clickPostButton();
	}

	@Then("the post should be visible in the buzz feed")
	public void the_post_should_be_visible_in_the_buzz_feed() {

		Assert.assertTrue(buzz.verifyPostCreated(postContent), "Post not found in feed");
	}

	@When("admin creates and deletes a buzz post")
	public void admin_creates_and_deletes_a_buzz_post() throws IOException {

		String[] data = csv.getCSVData("src/test/resources/testdata/BuzzData.csv");

		postContent = data[0] + System.currentTimeMillis();

		buzz.enterBuzzContent(postContent);

		buzz.clickPostButton();

		Assert.assertTrue(buzz.verifyPostCreated(postContent), "Post creation failed");

		buzz.deleteLatestBuzzPost();

		postDeleted = true;
	}

	@Then("the buzz post should be removed from the feed")
	public void the_buzz_post_should_be_removed_from_the_feed() {

		Assert.assertFalse(buzz.verifyPostCreated(postContent), "Deleted post is still visible");
	}
}