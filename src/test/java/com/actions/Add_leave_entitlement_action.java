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
	WebDriverWait wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(10));
	Actions actions = new Actions(helper.getDriver());

	public void leavePage() {
		ep.leave_page.click();
	}

	public void addEntitlement() {
		ep.entitlement_link.click();
		ep.add_entitlement.click();
	}

	public void leaveType_field() {

	    ep.leave_type.click();

	    WebDriverWait wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(ep.empOption));
	    ep.leaveType_listBox.click();
	}
	public void entitlement_field(String entitlement) {
		ep.entitlement.sendKeys(entitlement);
	}

	public void save() {
		ep.save.click();
	}

	public void confirmation_message() {
		Assert.assertTrue(ep.confirm.isDisplayed());
		ep.confirm.click();
	}
	
	public void nameField_Error_message() {
		Assert.assertTrue(ep.name_require_field.isDisplayed());
	}

	public void Name_field(String name) {
		ep.employee_name.click();
		ep.employee_name.sendKeys(name);
		wait.until(ExpectedConditions.visibilityOf(ep.empOption));
		wait.until(ExpectedConditions.invisibilityOf(ep.searching));
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();
		
	}

}
