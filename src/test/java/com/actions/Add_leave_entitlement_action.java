package com.actions;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.Add_leave_entitlement_page;
import com.utilities.ConfigReader;
import com.utilities.HelperClass;

public class Add_leave_entitlement_action {

	Add_leave_entitlement_page ep = new Add_leave_entitlement_page();
	HelperClass helper = new HelperClass();
	WebDriverWait wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
	Actions actions = new Actions(helper.getDriver());
	ConfigReader testData = new ConfigReader("testData.properties");

	public void leavePage() {
		helper.clickElement(ep.leave_page);
	}

	public void addEntitlement() {
		helper.clickElement(ep.entitlement_link);
		helper.clickElement(ep.add_entitlement);
	}

	public void leaveType_field() {
		String expectedType = testData.getData("expectedType");
		helper.clickElement(ep.leave_type);
		wait.until(ExpectedConditions.visibilityOf(ep.empOption));
		wait.until(ExpectedConditions.visibilityOfAllElements(ep.leaveTypeOptions));

		for (WebElement option : ep.leaveTypeOptions) {
			String actualText = option.getText().trim();
			System.out.println("[LeaveType] Found option: " + actualText);
			if (actualText.equalsIgnoreCase(expectedType)) {
				option.click();
				break;
			}
		}

	}

	public void entitlement_field() {
		String entitlement = testData.getData("entitlement");
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

	public void Name_field() {
		helper.clickElement(ep.employee_name);
		String employeeName = testData.getData("employeeName");
		ep.employee_name.sendKeys(employeeName);

		wait.until(ExpectedConditions.visibilityOf(ep.empOption));
		wait.until(ExpectedConditions.invisibilityOf(ep.searching));

		wait.until(ExpectedConditions.visibilityOfAllElements(ep.employeeSuggestions));

		boolean matched = false;
		List<WebElement> suggestions = ep.employeeSuggestions;
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().contains(employeeName.toLowerCase())) {
				suggestion.click();
				actions.sendKeys(Keys.ARROW_DOWN).perform();
				actions.sendKeys(Keys.ENTER).perform();
				matched = true;
				break;
			}
		}
	}

	public void blankField(String Name, String expectedType, String entitlement) {
		helper.clickElement(ep.leave_type);
		wait.until(ExpectedConditions.visibilityOf(ep.empOption));
		wait.until(ExpectedConditions.visibilityOfAllElements(ep.leaveTypeOptions));
		
		for (WebElement option : ep.leaveTypeOptions) {
			String actualText = option.getText();
			System.out.println("[LeaveType] Found option: " + actualText);
			if (actualText.equalsIgnoreCase(expectedType)) {
				option.click();
				break;
			}
		}
		helper.clickElement(ep.entitlement);
		ep.entitlement.sendKeys(entitlement);
	}
}
