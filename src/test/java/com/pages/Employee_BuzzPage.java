package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Employee_BuzzPage extends BasePage {

	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	WebElement dashboardHeader;
	@FindBy(xpath = "//span[normalize-space()='Buzz']")
	WebElement buzzMenu;
	@FindBy(xpath = "//textarea[contains(@placeholder,'What')]")
	WebElement postTextArea;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement postBtn;
	@FindBy(xpath = "//div[contains(@class,'orangehrm-buzz-post-body')]")
	List<WebElement> posts;

	private WebDriverWait getWait() {

		return new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private void jsClick(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		js.executeScript("arguments[0].click();", element);
	}

	// =========================================================
	// NORMAL CLICK + JS FALLBACK
	// =========================================================

	private void safeClick(WebElement element) {

		try {

			getWait().until(ExpectedConditions.elementToBeClickable(element));

			element.click();

		} catch (Exception e) {

			jsClick(element);
		}
	}

	// =========================================================
	// DASHBOARD
	// =========================================================

	public boolean isDashboardDisplayed() {

		getWait().until(ExpectedConditions.visibilityOf(dashboardHeader));

		return dashboardHeader.isDisplayed();
	}

	// =========================================================
	// BUZZ NAVIGATION
	// =========================================================

	public void clickBuzzMenu() {

		getWait().until(ExpectedConditions.visibilityOf(buzzMenu));

		safeClick(buzzMenu);
	}

	// =========================================================
	// ENTER NEW POST
	// =========================================================

	public void enterBuzzContent(String content) {

		WebDriverWait wait = getWait();

		WebElement textarea = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@placeholder,'What')]")));

		textarea.click();

		textarea.sendKeys(Keys.CONTROL, "a");

		textarea.sendKeys(Keys.BACK_SPACE);

		textarea.sendKeys(content);
	}

	// =========================================================
	// CLICK ADD POST
	// =========================================================

	public void clickPostButton() {

		WebDriverWait wait = getWait();

		WebElement button = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[contains(@placeholder,'What')]"
						+ "/ancestor::*[self::div or self::form]" + "//button[@type='submit']")));

		safeClick(button);
	}

	// =========================================================
	// VERIFY POST CREATED
	// =========================================================

	public boolean isPostDisplayed(String expectedPost) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			return wait.until(driver -> {

				List<WebElement> currentPosts = driver
						.findElements(By.xpath("//div[contains(@class,'orangehrm-buzz-post-body')]"));

				for (WebElement post : currentPosts) {

					try {

						if (post.isDisplayed() && post.getText().contains(expectedPost)) {

							return true;
						}

					} catch (StaleElementReferenceException e) {

						// DOM refreshed, retry
					}
				}

				return false;
			});

		} catch (Exception e) {

			return false;
		}
	}

	// =========================================================
	// VERIFY UPDATED POST
	// =========================================================
	public boolean isPostUpdated(String oldContent, String updatedContent) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {

			return wait.until(driver -> {

				List<WebElement> currentPosts = driver
						.findElements(By.xpath("//div[contains(@class,'orangehrm-buzz-post-body')]"));

				for (WebElement post : currentPosts) {

					try {

						if (!post.isDisplayed()) {
							continue;
						}

						String postText = post.getText();

						System.out.println("Checking post text: " + postText);

						if (postText.contains(updatedContent)) {

							System.out.println("UPDATED CONTENT FOUND: " + updatedContent);

							return true;
						}

					} catch (StaleElementReferenceException e) {

						// DOM refreshed, retry
					}
				}

				return false;
			});

		} catch (Exception e) {

			System.out.println("Updated content was not found: " + updatedContent);

			return false;
		}
	}

	// =========================================================
	// GET POST COUNT
	// =========================================================

	public int getPostCount() {

		return driver.findElements(By.xpath("//div[contains(@class,'orangehrm-buzz-post-body')]")).size();
	}

	// =========================================================
	// XPATH LITERAL
	// =========================================================

	private String xpathLiteral(String text) {

		if (!text.contains("'")) {

			return "'" + text + "'";
		}

		if (!text.contains("\"")) {

			return "\"" + text + "\"";
		}

		String[] parts = text.split("'");

		StringBuilder result = new StringBuilder("concat(");

		for (int i = 0; i < parts.length; i++) {

			if (i > 0) {

				result.append(", \"'\", ");
			}

			result.append("'").append(parts[i]).append("'");
		}

		result.append(")");

		return result.toString();
	}

	// =========================================================
	// FIND SPECIFIC POST
	// =========================================================

	private WebElement locatePostContainer(String content) {

		String contentLiteral = xpathLiteral(content);

		/*
		 * Find the post body containing our unique content.
		 *
		 * Then move upward to the nearest post container.
		 */

		String xpath = "//div[contains(@class,'orangehrm-buzz-post-body')]" + "[contains(.," + contentLiteral + ")]"
				+ "/ancestor::div[contains(@class,'oxd-grid-item')][1]";

		return getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	// =========================================================
	// OPEN THREE DOT MENU
	// =========================================================

	private void openPostMenu(String content) {

		WebElement postContainer = locatePostContainer(content);

		/*
		 * IMPORTANT:
		 *
		 * Find the three-dot button ONLY inside the identified post.
		 */

		WebElement optionsButton = postContainer.findElement(By.xpath(".//button[contains(@class,'oxd-icon-button')]"));

		safeClick(optionsButton);

		/*
		 * Menu is rendered outside the post container.
		 */

		getWait().until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[normalize-space()='Edit Post' " + "or normalize-space()='Delete Post']")));
	}

	// =========================================================
	// EDIT SPECIFIC POST
	// =========================================================

	public void editSpecificPost(String content, String updatedContent) {

		System.out.println("Opening edit for post: " + content);

		// ---------------------------------------------
		// 1. Locate correct post
		// ---------------------------------------------

		WebElement postContainer = locatePostContainer(content);

		// ---------------------------------------------
		// 2. Click three-dot button
		// ---------------------------------------------

		WebElement optionsButton = postContainer.findElement(By.xpath(".//button[contains(@class,'oxd-icon-button')]"));

		safeClick(optionsButton);

		// ---------------------------------------------
		// 3. Click Edit Post
		// ---------------------------------------------

		WebElement editOption = getWait()
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Edit Post']")));

		safeClick(editOption);

		// ---------------------------------------------
		// 4. Wait for EDIT DIALOG
		// ---------------------------------------------

		WebElement editDialog = getWait().until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@class,'orangehrm-dialog-modal')]")));

		System.out.println("Edit dialog opened");

		// ---------------------------------------------
		// 5. Find textarea INSIDE edit dialog
		// ---------------------------------------------

		WebElement editTextarea = getWait().until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'orangehrm-dialog-modal')]" + "//textarea")));

		// ---------------------------------------------
		// 6. Clear existing content
		// ---------------------------------------------

		editTextarea.click();

		editTextarea.sendKeys(Keys.CONTROL, "a");

		editTextarea.sendKeys(Keys.BACK_SPACE);

		/*
		 * Extra JavaScript clear.
		 *
		 * This handles cases where Selenium clear() or Ctrl+A does not properly update
		 * Angular.
		 */

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript(
				"arguments[0].value = '';" + "arguments[0].dispatchEvent(" + "new Event('input', { bubbles: true })"
						+ ");" + "arguments[0].dispatchEvent(" + "new Event('change', { bubbles: true })" + ");",
				editTextarea);

		// ---------------------------------------------
		// 7. Enter updated content
		// ---------------------------------------------

		editTextarea.sendKeys(updatedContent);

		System.out.println("Entered updated content: " + updatedContent);

		// ---------------------------------------------
		// 8. Find POST button INSIDE EDIT DIALOG
		// ---------------------------------------------

		WebElement saveButton = getWait().until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'orangehrm-dialog-modal')]"
						+ "//button[" + "@type='submit'" + " and " + "normalize-space()='Post'" + "]")));

		// ---------------------------------------------
		// 9. Wait until button is enabled
		// ---------------------------------------------

		getWait().until(driver -> {

			try {

				return saveButton.isDisplayed() && saveButton.isEnabled();

			} catch (StaleElementReferenceException e) {

				return false;
			}
		});

		System.out.println("Edit Post button is enabled");

		// ---------------------------------------------
		// 10. Click Post button
		// ---------------------------------------------

		try {

			saveButton.click();

		} catch (Exception e) {

			System.out.println("Normal click failed. Using JS click.");

			jsClick(saveButton);
		}

		// ---------------------------------------------
		// 11. Wait until edit dialog disappears
		// ---------------------------------------------

		getWait().until(ExpectedConditions.invisibilityOf(editDialog));

		System.out.println("Edit completed successfully");
	}

	// =========================================================
	// DELETE SPECIFIC POST
	// =========================================================

	public void deleteSpecificPost(String content) {

		// ---------------------------------------------
		// 1. Locate correct post
		// ---------------------------------------------

		WebElement postContainer = locatePostContainer(content);

		// ---------------------------------------------
		// 2. Find three-dot button inside same post
		// ---------------------------------------------

		WebElement optionsButton = postContainer.findElement(By.xpath(".//button[contains(@class,'oxd-icon-button')]"));

		safeClick(optionsButton);

		// ---------------------------------------------
		// 3. Click Delete Post
		// ---------------------------------------------

		WebElement deleteOption = getWait()
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Delete Post']")));

		safeClick(deleteOption);

		// ---------------------------------------------
		// 4. Confirm Delete
		// ---------------------------------------------

		WebElement confirmButton = getWait().until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(normalize-space(),'Yes, Delete')]")));

		safeClick(confirmButton);

		// ---------------------------------------------
		// 5. Wait until old post disappears
		// ---------------------------------------------

		String contentLiteral = xpathLiteral(content);

		By deletedPost = By
				.xpath("//div[contains(@class,'orangehrm-buzz-post-body')]" + "[contains(.," + contentLiteral + ")]");

		getWait().until(ExpectedConditions.invisibilityOfElementLocated(deletedPost));
	}

	// =========================================================
	// VERIFY DELETED
	// =========================================================

	public boolean isPostDeleted(String content) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

			String contentLiteral = xpathLiteral(content);

			By deletedPost = By.xpath(
					"//div[contains(@class,'orangehrm-buzz-post-body')]" + "[contains(.," + contentLiteral + ")]");

			return wait.until(driver -> {

				List<WebElement> elements = driver.findElements(deletedPost);

				for (WebElement element : elements) {

					try {

						if (element.isDisplayed()) {

							return false;
						}

					} catch (StaleElementReferenceException e) {

						// Element disappeared
					}
				}

				return true;
			});

		} catch (Exception e) {

			return true;
		}
	}
}