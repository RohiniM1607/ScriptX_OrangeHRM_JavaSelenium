package com.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import com.hooks.Hooks;
import com.pages.Employee_ApplyLeave_Page;
import com.utilities.HelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Employee_ApplyLeave_Actions extends BaseActions {

    private final Employee_ApplyLeave_Page page = new Employee_ApplyLeave_Page();

    private final HelperClass helper = new HelperClass(driver);

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
   

    public void navigateToApplyLeave() throws InterruptedException {

   
    	

        helper.waitForElement(page.leaveMenu);

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", page.leaveMenu);

        System.out.println("Clicked 'Leave' in sidebar.");
        

        helper.waitForElement(page.applySubMenu);

        helper.clickElement(page.applySubMenu);

        System.out.println("Clicked 'Apply' sub-menu.");

        wait.until(ExpectedConditions.urlContains("applyLeave"));

        helper.waitForElement(page.leaveTypeDropdown);

        System.out.println("Apply Leave page loaded successfully.");
    }
    public void selectLeaveType(String leaveType) {
        
 
        helper.clickElement(page.leaveTypeDropdown);
 
        List<WebElement> options = wait.until(
            ExpectedConditions.visibilityOfAllElements(page.leaveTypeOptions)
        );
        System.out.println("Total leave type options found: " + options.size());
 
        Actions actions = new Actions(Hooks.driver);
        actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1));
        actions.sendKeys(Keys.ENTER).perform();
 
        WebElement leaveOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'oxd-select-dropdown')]//span[normalize-space()='" + leaveType + "']")
        ));
        leaveOption.click();
        System.out.println("Leave type selected: " + leaveType);
    }
    
    

    public String getLeaveBalanceText() {

        helper.waitForElement(page.leaveBalanceText);

        String balance = page.leaveBalanceText.getText().trim();

        System.out.println("Leave Balance Displayed: " + balance);

        return balance;
    }
}