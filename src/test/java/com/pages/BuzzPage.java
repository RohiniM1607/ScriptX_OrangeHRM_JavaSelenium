package com.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuzzPage extends BasePage {

	@FindBy(xpath = "//span[text()='Buzz']")
	WebElement buzzMenu;

	@FindBy(xpath = "//textarea[contains(@placeholder,'What')]")
	WebElement postTextArea;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement postBtn;

	@FindBy(xpath = "//div[contains(@class,'orangehrm-buzz-post-body')]")
	List<WebElement> posts;
	
	@FindBy(xpath = "(//i[contains(@class,'bi-three-dots')])[1]")
	WebElement postOptions;

	@FindBy(xpath = "//p[text()='Delete Post']")
	WebElement deletePostOption;

	@FindBy(xpath = "//button[contains(.,'Yes, Delete')]")
	WebElement confirmDeleteBtn;
	
	@FindBy(xpath = "//p[text()='Edit Post']")
	WebElement editPostOption;

	@FindBy(xpath = "//*[@id=\"Buzz Newsfeed\"]/div[2]/div/div/div/form/div[1]/div[2]/div/textarea")
	WebElement editTextArea;

	@FindBy(xpath = "//*[@id=\"Buzz Newsfeed\"]/div[2]/div/div/div/form/div[3]/button")
	WebElement savePostBtn;

	public void clickByJS(WebElement element) {

		helper.waitForElement(element);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true)", element);

		js.executeScript("arguments[0].click()", element);
	}

	public void clickBuzzMenu() {

		clickByJS(buzzMenu);
	}

	public void enterBuzzContent(String content) {

		helper.waitForElement(postTextArea);

		postTextArea.clear();

		postTextArea.sendKeys(content);
	}

	public void clickPostButton() {

		clickByJS(postBtn);
	}

	public boolean isPostDisplayed(String expectedPost) {

		try {

			Thread.sleep(3000);

			for (WebElement post : posts) {

				if (post.getText().contains(expectedPost)) {

					return true;
				}
			}

		} catch (Exception e) {

			return false;
		}

		return false;
	}

	public boolean isPostButtonEnabled() {

		return postBtn.isEnabled();
	}
	
	public int getPostCount() {

	    return posts.size();
	}
	
	public void clickPostOptions() {

	    helper.waitForElement(postOptions);

	    clickByJS(postOptions);
	}

	public void clickDeletePost() {

	    helper.waitForElement(deletePostOption);

	    clickByJS(deletePostOption);
	}

	public void clickConfirmDelete() {

	    helper.waitForElement(confirmDeleteBtn);

	    clickByJS(confirmDeleteBtn);
	}

	public void deleteLatestPost() {

	    clickPostOptions();

	    clickDeletePost();

	    clickConfirmDelete();
	}
	
	public void clickEditPost() {

	    helper.waitForElement(editPostOption);

	    clickByJS(editPostOption);
	}

	public void updatePostContent(String updatedContent) {

	    helper.waitForElement(editTextArea);

	    editTextArea.clear();

	    editTextArea.sendKeys(updatedContent);
	}

	public void clickSavePost() {

	    helper.waitForElement(savePostBtn);

	    clickByJS(savePostBtn);
	}

	public void editLatestPost(String updatedContent) {

	    clickPostOptions();

	    clickEditPost();

	    updatePostContent(updatedContent);

	    clickSavePost();
	}
}
