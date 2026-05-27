package com.actions;

import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pages.Search_user_page;
import com.utilities.ConfigReader;
import com.utilities.HelperClass;

public class Search_user_action {

    Search_user_page sp = new Search_user_page();
    HelperClass helper = new HelperClass();
    WebDriverWait wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
    Actions actions = new Actions(helper.getDriver());
    ConfigReader testData = new ConfigReader("testData.properties");

    public void clickAdminMenu() {
        sp.Admin_page.click();
    }

    public void enterUsername(String UserName) {
    	wait.until(ExpectedConditions.visibilityOf(sp.system_user));
        helper.clickElement(sp.username_field);
        sp.username_field.sendKeys(UserName);
    }

    public void clickSearch() {
    	 sp.search_btn.click();
    }

    public void verifyUsernameResult(String UserName) {
        wait.until(ExpectedConditions.visibilityOf(sp.username_record));
        Assert.assertEquals(UserName, sp.username_record.getText());
    }

    public void selectUserRole() {
    	String role = testData.getData("role");
    	sp.userRole.click();
    	wait.until(ExpectedConditions.visibilityOfAllElements(sp.userRoleOptions));
    	for (WebElement option : sp.userRoleOptions) {
            if (option.getText().equalsIgnoreCase(role)) {
                option.click();
                break;
            }
    	}
    }

    public void verifyUserRoleResult() {
        String expectedRole = testData.getData("role");
        wait.until(ExpectedConditions.visibilityOf(sp.userrole_record));
        Assert.assertEquals(expectedRole, sp.userrole_record.getText());
    }

    public void enterEmployeeName(String employeeName) {
        helper.clickElement(sp.employeeName);
        sp.employeeName.sendKeys(employeeName);
        wait.until(ExpectedConditions.visibilityOf(sp.listbox));
        wait.until(ExpectedConditions.visibilityOfAllElements(sp.employeeSuggestions));
        for (WebElement option : sp.employeeSuggestions) {
            if (option.getText().trim().equalsIgnoreCase(employeeName.trim())) {
                option.click();
                break;
            }
        }
    }

    public void verifyEmployeeNameResult(String employeeName) {
        wait.until(ExpectedConditions.visibilityOf(sp.employeeName_record));
        Assert.assertEquals(employeeName, sp.employeeName_record.getText());
    }
}
