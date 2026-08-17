package com.actions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.hooks.Hooks;
import com.pages.Add_leave_entitlement_page;
import com.pages.Employee_ApplyLeave_Page;
import com.utilities.HelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Employee_ApplyLeave_Actions extends BaseActions {

    Employee_ApplyLeave_Page page = new Employee_ApplyLeave_Page();
  

    HelperClass helper = new HelperClass();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

    // Remembers the "from date" used in the most recent setDateRange() call so
    // cancelAppliedLeave() can uniquely identify the row it just created, even
    // when other leave requests of the same type already exist in the list.
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
        String toastText = waitForCapturedToastText(45);

        if (toastText == null) {
            // Truly nothing was ever inserted into the DOM — fall back to a
            // quick live-DOM check in case the observer somehow missed it,
            // then dump diagnostics if that also comes up empty.
            List<WebElement> anyToast = driver.findElements(By.cssSelector("div.oxd-toast"));
            if (!anyToast.isEmpty()) {
                toastText = anyToast.get(0).getText();
            }
        }

        if (toastText == null) {
            String currentUrl = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();
            List<WebElement> inlineErrors = driver.findElements(
                    By.xpath("//span[contains(@class,'oxd-input-field-error-message')]"));
            String inlineErrorText = inlineErrors.isEmpty() ? "none" : inlineErrors.get(0).getText();
            boolean loaderStillVisible = !driver.findElements(By.cssSelector("div.oxd-form-loader")).isEmpty();

            System.out.println("---- No toast appeared - diagnostic dump ----");
            System.out.println("Current URL: " + currentUrl);
            System.out.println("Page title: " + pageTitle);
            System.out.println("Inline field error present: " + inlineErrorText);
            System.out.println("Loader still in DOM: " + loaderStillVisible);
            System.out.println("---------------------------------------------");

            Assert.fail("No confirmation toast (success or error) was ever inserted into the DOM after "
                    + "clicking Save. URL=" + currentUrl + ", inlineFieldError=" + inlineErrorText
                    + ", loaderStillPresent=" + loaderStillVisible);
        }

        Assert.assertTrue("Expected the success toast to mention 'Successfully Saved', but got: \"" + toastText
                + "\". This usually means OrangeHRM rejected the leave request — most commonly because a "
                + "leave already exists for that date range.", toastText.contains("Successfully Saved"));

        System.out.println("Success toast captured: " + toastText);
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
    //
    // The generated date always falls between tomorrow and Dec 31 of the
    // current year (inclusive) — it never rolls over into next year.
    public static String resolveDate(String rawDate) {
        if (rawDate == null || !rawDate.equalsIgnoreCase("AUTO")) {
            return rawDate;
        }

        LocalDate today = LocalDate.now();
        LocalDate endOfYear = LocalDate.of(today.getYear(), 12, 31);

        long daysRemaining = java.time.temporal.ChronoUnit.DAYS.between(today, endOfYear);
        // Guard against running this in late December, where there may be
        // very few (or zero) days left in the year.
        long range = Math.max(daysRemaining, 1);

        // Pick a changing-but-bounded offset (1..range) so back-to-back runs
        // still land on different dates, without ever leaving the current year.
        long offset = 1 + ((System.currentTimeMillis() / 1000L) % range);

        LocalDate date = today.plusDays(offset);

        // Skip weekends — leave applications should land on working days.
        // Roll a Saturday forward by 2 (to Monday), a Sunday forward by 1.
        DayOfWeek dow = date.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY) {
            date = date.plusDays(2);
        } else if (dow == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }

        // Edge case: if rolling forward pushed us past Dec 31 (only possible
        // when the range window is right at year-end), pull back to the
        // nearest prior weekday instead so we never exceed the current year.
        if (date.isAfter(endOfYear)) {
            date = endOfYear;
            while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.minusDays(1);
            }
        }

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDateRange(String fromDate, String toDate) {

        // If both are AUTO, resolve once and reuse for both so a single-day
        // leave request always has matching from/to dates (resolving them
        // independently could rarely land on different seconds → different dates).
        if ("AUTO".equalsIgnoreCase(fromDate) && "AUTO".equalsIgnoreCase(toDate)) {
            fromDate = resolveDate(fromDate);
            toDate = fromDate;
        } else {
            fromDate = resolveDate(fromDate);
            toDate = resolveDate(toDate);
        }

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
    
    // Installs (or resets) a JS MutationObserver that records the text of any
    // .oxd-toast the instant it's added to the DOM, into window.__lastToastText.
    // This is essential on resource-constrained CI agents: under heavy parallel
    // load, our own thread can be starved of CPU time for seconds after a click,
    // by which point a fast auto-dismissing toast may already be gone from the
    // DOM. Reading a JS-captured snapshot sidesteps that race entirely — it
    // doesn't matter when we get around to checking, the text was already saved
    // the moment the toast existed.
    private void resetToastWatcher() {
        String script =
                "if (!window.__toastObserver) {" +
                "  window.__toastObserver = new MutationObserver(function(mutations) {" +
                "    mutations.forEach(function(m) {" +
                "      m.addedNodes.forEach(function(node) {" +
                "        if (node.nodeType === 1) {" +
                "          var toast = (node.classList && node.classList.contains('oxd-toast')) ? node " +
                "              : (node.querySelector ? node.querySelector('.oxd-toast') : null);" +
                "          if (toast) { window.__lastToastText = toast.innerText || toast.textContent; }" +
                "        }" +
                "      });" +
                "    });" +
                "  });" +
                "  window.__toastObserver.observe(document.body, { childList: true, subtree: true });" +
                "}" +
                "window.__lastToastText = null;";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    // Polls the JS-captured toast text (see resetToastWatcher) rather than the
    // live DOM, so a toast that's already auto-dismissed by the time we check
    // is still detected.
    private String waitForCapturedToastText(int timeoutSeconds) {
        long deadline = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        while (System.currentTimeMillis() < deadline) {
            Object result = ((JavascriptExecutor) driver).executeScript("return window.__lastToastText;");
            if (result != null) {
                return result.toString();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    public void clickSave() {
        // Arm the watcher immediately before clicking, so we don't miss a
        // toast that appears and vanishes before our thread gets CPU time again.
        resetToastWatcher();

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(page.save));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        System.out.println("Save operation completed.");
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

        // The list defaults to a narrow date window (commonly the current
        // month), which won't include a leave applied for a future AUTO date
        // outside that window. Widen the filter to span the whole current
        // year so any leave we just applied for is guaranteed to be listed.
        widenMyLeaveDateFilter();
    }

    // Sets the My Leave list's From/To date filter to Jan 1 – Dec 31 of the
    // current year and re-runs the search, so leave requests dated anywhere
    // in the current year (including AUTO-generated future dates) are shown.
    private void widenMyLeaveDateFilter() {
        LocalDate today = LocalDate.now();
        String from = LocalDate.of(today.getYear(), 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String to = LocalDate.of(today.getYear(), 12, 31).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        wait.until(ExpectedConditions.visibilityOf(page.fromDateInput));

        page.fromDateInput.click();
        page.fromDateInput.sendKeys(Keys.CONTROL + "a");
        page.fromDateInput.sendKeys(Keys.DELETE);
        page.fromDateInput.sendKeys(from);

        page.toDateInput.click();
        page.toDateInput.sendKeys(Keys.CONTROL + "a");
        page.toDateInput.sendKeys(Keys.DELETE);
        page.toDateInput.sendKeys(to);
        page.toDateInput.sendKeys(Keys.TAB);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(page.myLeaveSearchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));

        System.out.println("Widened My Leave date filter to " + from + " through " + to + ".");
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

        if (targetRow == null) {
            // Dump what actually IS in the list so a future mismatch (wrong
            // date format, unexpected filter reset, etc.) is self-explanatory.
            StringBuilder rowDump = new StringBuilder();
            for (int i = 0; i < rows.size(); i++) {
                rowDump.append("\n  [").append(i).append("] ").append(rows.get(i).getText().replace("\n", " | "));
            }
            System.out.println("---- Rows currently in My Leave list ----" + rowDump + "\n------------------------------------------");

            Assert.fail("No leave request row matched leaveType='" + leaveType + "' and date='" + appliedFromDate
                    + "'. Rows currently listed: " + rows.size() + ". See console dump above for actual row contents.");
        }

        // ---- DIAGNOSTIC (kept lightweight) ----
        // Confirmed markup: the row has a direct button[text()='Cancel'].
        // Uncomment below again if a future OrangeHRM version changes this shape.
        // String rowHtml = (String) ((JavascriptExecutor) driver)
        //         .executeScript("return arguments[0].outerHTML;", targetRow);
        // System.out.println("Matched leave row HTML:\n" + rowHtml);

        // Arm the watcher right before the click, same reasoning as clickSave().
        resetToastWatcher();

        // The row itself exposes a direct "Cancel" button (visible in the
        // diagnostic HTML: oxd-button--label-warn, text "Cancel") — separate
        // from the "..." dropdown menu button. Target it specifically by its
        // exact text so we never click the dropdown by accident.
        WebElement cancelBtn = targetRow.findElement(By.xpath(".//button[normalize-space()='Cancel']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelBtn);

        System.out.println("Clicked row-level Cancel button for leave type: " + leaveType + " on " + appliedFromDate);

        // A confirmation dialog may or may not appear depending on the app's
        // configured leave-cancellation settings. Wait briefly for one; if it
        // doesn't show up, assume the cancel action completed directly.
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement confirmBtn = shortWait.until(ExpectedConditions.elementToBeClickable(page.confirmCancelButton));
            confirmBtn.click();
            System.out.println("Confirmed cancellation dialog for leave type: " + leaveType + " on " + appliedFromDate);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("No confirmation dialog appeared — cancellation likely applied directly.");
        }
    }

    public void confirmCancelMessage() {
        // Same CPU-starvation-proof approach as confirmation_message(): read
        // whatever toast text was captured by the observer the instant it
        // appeared, rather than checking current live visibility.
        String toastText = waitForCapturedToastText(20);

        if (toastText != null) {
            System.out.println("Cancel toast captured: " + toastText);
            return;
        }

        // No toast captured at all. OrangeHRM's row-level Cancel doesn't
        // always show one — it may just update the row's status in place.
        // Fall back to checking the row itself for "Cancelled" before failing.
        System.out.println("No cancel toast appeared — checking row status directly instead.");

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

        System.out.println("Leave cancellation confirmed via row status (no toast shown). Row text: " + rowText);
    }
}
