package com.actions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
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

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
   

    public void navigateToApplyLeave() throws InterruptedException {

        helper.waitForElement(page.leaveMenu);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

        System.out.println("Clicked 'Leave' in sidebar.");
        
        helper.waitForElement(page.applySubMenu);

        helper.clickElement(page.applySubMenu);

        System.out.println("Clicked 'Apply' sub-menu.");

        //wait.until(ExpectedConditions.urlContains("applyLeave"));

        helper.waitForElement(page.leave_type);

        System.out.println("Apply Leave page loaded successfully.");
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
		wait.until(ExpectedConditions.visibilityOf(page.confirm));
		Assert.assertTrue(page.confirm.isDisplayed());
		//page.confirm.click();
	}
    
    public void setDateRange(String fromDate, String toDate) {

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
}
    
    public void clickSave() {
    	page.save.click();
    }
}