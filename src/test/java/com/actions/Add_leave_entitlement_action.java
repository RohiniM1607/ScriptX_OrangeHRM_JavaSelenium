package com.actions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hooks.Hooks;
import com.pages.Add_leave_entitlement_page;
import com.utilities.HelperClass;

public class Add_leave_entitlement_action {

	Add_leave_entitlement_page ep = new Add_leave_entitlement_page();
	HelperClass helper=new HelperClass();
	Hooks hooks = new Hooks();

	public void leavePage() {

		ep.leave_page.click();
	}

	public void addEntitlement() {
		ep.entitlement_link.click();
		ep.add_entitlement.click();
	}

	public void user_details(String name, String entitlement) {

		WebDriverWait wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(10));
		Actions actions = new Actions(helper.getDriver());
		ep.employee_name.click();
		ep.employee_name.sendKeys(name);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role=\"listbox\"]")));
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();
		// wait.until(ExpectedConditions.elementToBeClickable(ep.leave_type));
		ep.leave_type.click();
		for (int i = 0; i < 4; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform();
		}

		actions.sendKeys(Keys.ENTER).perform();

		ep.entitlement.sendKeys(entitlement);
	}

	public void save() {
		ep.save.click();
	}

	public void confirmation_message() {
		Assert.assertTrue(ep.confirm.isDisplayed());
		ep.confirm.click();
	}

}
