package com.actions;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pages.Add_leave_entitlement_page;
import com.utilities.HelperClass;

public class Add_leave_entitlement_action {

	Add_leave_entitlement_page ep = new Add_leave_entitlement_page();
	HelperClass helper = new HelperClass();
	WebDriverWait wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
	Actions actions = new Actions(helper.getDriver());
	JavascriptExecutor js = (JavascriptExecutor) helper.getDriver();

	public void leavePage() {
		helper.clickElement(ep.leave_page);
	}

	public void addEntitlement() {
		helper.clickElement(ep.entitlement_link);
		helper.clickElement(ep.add_entitlement);
	}

	public void leaveType_field(String expectedType) {
		helper.clickElement(ep.leave_type);
		wait.until(ExpectedConditions.visibilityOf(ep.empOption));
		wait.until(ExpectedConditions.visibilityOfAllElements(ep.leaveTypeOptions));

		for (WebElement option : ep.leaveTypeOptions) {
			String actualText = option.getText();
			if (actualText.equalsIgnoreCase(expectedType)) {
				option.click();
				break;
			}
		}

	}

	public void entitlement_field(String entitlement) {
		helper.clickElement(ep.entitlement);
		ep.entitlement.sendKeys(entitlement);
	}

	public void save() {
		helper.clickElement(ep.save);
	}

	public void confirmation_message() {
		wait.until(ExpectedConditions.visibilityOf(ep.confirm));
		Assert.assertTrue(ep.confirm.isDisplayed());
		ep.confirm.click();
	}

	public void nameField_Error_message() {
		wait.until(ExpectedConditions.visibilityOf(ep.name_require_field));
		Assert.assertTrue(ep.name_require_field.isDisplayed());
	}

	public void employeeNameField(String employeeName) {
	    wait.until(ExpectedConditions.elementToBeClickable(ep.employee_name));
	    ep.employee_name.clear();
	    ep.employee_name.sendKeys(employeeName);
	    wait.until(ExpectedConditions.visibilityOfAllElements(ep.employeeSuggestions));
	    wait.until(ExpectedConditions.invisibilityOf(ep.searching));

	    for (WebElement suggestion : ep.employeeSuggestions) {
	        String actualName = suggestion.getText().trim();
	        System.out.println("Suggestion : " + actualName);
	        if (actualName.toLowerCase().contains(employeeName.toLowerCase())) {
	            wait.until(ExpectedConditions.elementToBeClickable(suggestion));
	        	System.out.println("Get mathch!");
	        	 //js.executeScript("arguments[0].click();", suggestion);
	        	suggestion.click();
//	        	 ep.employee_name.sendKeys(Keys.ARROW_DOWN);
//	        	 suggestion.sendKeys(Keys.ENTER);
	            break;
	        }
	    }

	    wait.until(ExpectedConditions.invisibilityOf(ep.suggestionBox));
	}

	public void invalidEmployeeName(String empName) {
		ep.employee_name.sendKeys(empName);
		wait.until(ExpectedConditions.visibilityOfAllElements(ep.employeeSuggestions));
	    wait.until(ExpectedConditions.invisibilityOf(ep.searching));
	}
	
	public void invalidNameMsg(String errorMsg) {
	    wait.until(ExpectedConditions.visibilityOf(ep.noRecordFound));
	    Assert.assertEquals(errorMsg, ep.noRecordFound.getText().trim());
	}

	public void EntitlementExceedErrorMessage() {
		Boolean present = wait.until(ExpectedConditions.visibilityOf(ep.entitlement_exceed_err)).isDisplayed();
		Assert.assertTrue(present);
	}
}
