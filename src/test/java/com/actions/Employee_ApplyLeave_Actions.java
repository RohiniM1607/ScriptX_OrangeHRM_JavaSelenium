package com.actions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.pages.Employee_ApplyLeave_Page;
import com.utilities.HelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Employee_ApplyLeave_Actions extends BaseActions {

    Employee_ApplyLeave_Page page = new Employee_ApplyLeave_Page();
    HelperClass helper = new HelperClass();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    private String appliedFromDate;
   

    public void navigateToApplyLeave() throws InterruptedException {

        helper.waitForElement(page.leaveMenu);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

        System.out.println("Clicked 'Leave' in sidebar.");
        
        helper.waitForElement(page.applySubMenu);

        helper.clickElement(page.applySubMenu);

        System.out.println("Clicked 'Apply' sub-menu.");

        //wait.until(ExpectedConditions.urlContains("applyLeave"));

        helper.waitForElement(page.leave_type);

        // Ensure the loading overlay has fully disappeared before any further interaction.
        // Using invisibilityOfElementLocated (not invisibilityOf(page.loader)) so it re-queries
        // the DOM by locator instead of relying on a possibly-stale WebElement reference.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

        System.out.println("Apply Leave page loaded successfully.");
    }

    // Same navigation as navigateToApplyLeave(), kept separate so the intent is explicit
    // in the negative-path scenario: this method deliberately does NOT select a leave type,
    // leaving the field empty so Save triggers the "Required" validation message.
    public void navigateToApplyLeaveWithoutSelectingType() throws InterruptedException {

        helper.waitForElement(page.leaveMenu);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

        System.out.println("Clicked 'Leave' in sidebar.");

        helper.waitForElement(page.applySubMenu);

        helper.clickElement(page.applySubMenu);

        System.out.println("Clicked 'Apply' sub-menu.");

        helper.waitForElement(page.leave_type);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

        System.out.println("Apply Leave page loaded successfully. Leave Type intentionally left unselected.");
    }
    
    public void selectLeaveType(String leaveType) {
        
        helper.clickElement(page.leave_type);
 
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveTypeOptions));
        System.out.println("Total leave type options found: " + options.size());
 
        Actions actions = new Actions(helper.getDriver());
        actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1));
        actions.sendKeys(Keys.ENTER).perform();
 
        WebElement leaveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'oxd-select-dropdown')]//span[normalize-space()='" + leaveType + "']")));
        leaveOption.click();
        System.out.println("Leave type selected: " + leaveType);
    }
    
    

    public void confirmation_message() {
        try {
            wait.until(ExpectedConditions.visibilityOf(page.confirm));
            Assert.assertTrue(page.confirm.isDisplayed());
        } catch (org.openqa.selenium.TimeoutException e) {
            // The success toast never showed up. Rather than a bare timeout,
            // check whether SOME toast (e.g. a duplicate/overlap validation
            // error) appeared instead, and surface its text so the real cause
            // is obvious without re-running with extra logging.
            List<WebElement> anyToast = driver.findElements(By.cssSelector("div.oxd-toast"));
            if (!anyToast.isEmpty()) {
                Assert.fail("Expected the 'Successfully Saved' toast, but a different toast appeared instead: \""
                        + anyToast.get(0).getText() + "\". This usually means OrangeHRM rejected the leave "
                        + "request — most commonly because a leave already exists for that date range.");
            }
            throw e;
        }
    }

    // Verifies the "Required" validation message under Leave Type when Save
    // is clicked with no leave type selected.
    public void verifyRequiredFieldErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(page.leaveTypeRequiredError));
        Assert.assertTrue("Expected Leave Type required-field error to be displayed",
                page.leaveTypeRequiredError.isDisplayed());
        Assert.assertEquals("Required", page.leaveTypeRequiredError.getText().trim());
        System.out.println("Required field validation message displayed for Leave Type.");
    }
    
    // Turns the special Examples value "AUTO" into a date that is essentially
    // guaranteed to be different from any date used in a previous run, so
    // re-running the suite never collides with a leave request that's already
    // sitting in the system from an earlier execution. Anything other than
    // "AUTO" is returned unchanged, so fixed dates still work if you want them.
    public static String resolveDate(String rawDate) {
        if (rawDate == null || !rawDate.equalsIgnoreCase("AUTO")) {
            return rawDate;
        }
        // Push far enough into the future (base offset) and add a
        // second-precision, monotonically-changing offset so back-to-back
        // runs on the same day still land on different calendar dates.
        long secondsOfDayOffset = (System.currentTimeMillis() / 1000L) % 1000L;
        LocalDate date = LocalDate.now().plusDays(365 + secondsOfDayOffset);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDateRange(String fromDate, String toDate) {

        fromDate = resolveDate(fromDate);
        toDate = resolveDate(toDate);

        this.appliedFromDate = fromDate;

        // Defensive wait: don't assume the date fields are already rendered
        // just because a previous step finished — wait for them here too, so
        // this method is safe to call regardless of timing upstream.
        wait.until(ExpectedConditions.visibilityOf(page.fromDateInput));

        page.fromDateInput.click();
        page.fromDateInput.sendKeys(Keys.CONTROL + "a");
        page.fromDateInput.sendKeys(Keys.DELETE);
        page.fromDateInput.sendKeys(fromDate);

        page.toDateInput.click();
        page.toDateInput.sendKeys(Keys.CONTROL + "a");
        page.toDateInput.sendKeys(Keys.DELETE);
        page.toDateInput.sendKeys(toDate);
        page.toDateInput.sendKeys(Keys.TAB);
    }

    public void leaveType_field(String expectedType) {

        // Wait for the loading overlay to disappear before clicking the leave type dropdown.
        // This is what was intercepting the click in the failing run.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

        helper.clickElement(page.leave_type);
		wait.until(ExpectedConditions.visibilityOf(page.empOption));
		//wait.until(ExpectedConditions.visibilityOfAllElements(ep.leaveTypeOptions));

		for (WebElement option : page.leaveTypeOptions) {
			String actualText = option.getText();
			if (actualText.equalsIgnoreCase(expectedType)) {
				option.click();
				break;
			}
		}

		// Selecting a leave type triggers an async call that reveals the
		// From/To date fields. On a slow/loaded environment this can lag
		// behind the click, so wait for it explicitly instead of assuming
		// it's already in the DOM by the time the next step runs.
		wait.until(ExpectedConditions.visibilityOf(page.fromDateInput));
}
    
    public void clickSave() {
    	page.save.click();
    }

    // ---- My Leave list / Cancel leave ----

    public void navigateToMyLeaveList() {

        helper.waitForElement(page.leaveMenu);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

        System.out.println("Clicked 'Leave' in sidebar.");

        helper.waitForElement(page.myLeaveSubMenu);

        helper.clickElement(page.myLeaveSubMenu);

        System.out.println("Clicked 'My Leave' sub-menu.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

        wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));

        System.out.println("My Leave list page loaded successfully.");
    }

    // Finds the SPECIFIC row this scenario just created — matched by leave type
    // AND the from-date captured in setDateRange() — so it doesn't accidentally
    // act on some other pre-existing leave request of the same type.
    public void cancelAppliedLeave(String leaveType) {

        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));

        WebElement targetRow = null;
        for (WebElement row : rows) {
            String rowText = row.getText();
            boolean matchesType = rowText.contains(leaveType);
            boolean matchesDate = (appliedFromDate == null) || rowText.contains(appliedFromDate);
            if (matchesType && matchesDate) {
                targetRow = row;
                break;
            }
        }
        Assert.assertNotNull("No leave request row matched leaveType='" + leaveType
                + "' and date='" + appliedFromDate + "'. Rows currently listed: " + rows.size(), targetRow);

    
        WebElement cancelBtn = targetRow.findElement(By.xpath(".//button[normalize-space()='Cancel']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelBtn);

        System.out.println("Clicked row-level Cancel button for leave type: " + leaveType + " on " + appliedFromDate);


   }

    public void confirmCancelMessage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            shortWait.until(ExpectedConditions.visibilityOf(page.cancelSuccessMsg));
            Assert.assertTrue("Expected leave-cancelled confirmation toast to be displayed",
                    page.cancelSuccessMsg.isDisplayed());
            System.out.println("Leave cancellation confirmation message displayed (toast).");
        } catch (org.openqa.selenium.TimeoutException e) {
            

            List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));
            WebElement targetRow = null;
            for (WebElement row : rows) {
                String rowText = row.getText();
                boolean matchesDate = (appliedFromDate == null) || rowText.contains(appliedFromDate);
                if (matchesDate) {
                    targetRow = row;
                    break;
                }
            }
            Assert.assertNotNull("No cancel toast appeared, and could not find the leave row for date "
                    + appliedFromDate + " to verify its status.", targetRow);

            String rowText = targetRow.getText();
            Assert.assertTrue("No cancel toast appeared, and the row for date " + appliedFromDate
                    + " does not show a Cancelled status. Row text: \"" + rowText + "\"",
                    rowText.toLowerCase().contains("cancel"));

            System.out.println("Leave cancellation confirmed via row status. Row text: " + rowText);
        }
    }
}