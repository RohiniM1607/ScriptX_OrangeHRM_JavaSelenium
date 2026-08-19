package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class JobCategoryPage extends BasePage {
		
		public JobCategoryPage() {
			super();
		}
		
		@FindBy(xpath= "//a[@class=\"oxd-main-menu-item active\"]")
		WebElement admin;
		
		@FindBy(xpath = "//span[@class=\"oxd-topbar-body-nav-tab-item\"][text()='Job ']")
		WebElement job;
		
		@FindBy(xpath = "//div[@class=\"oxd-table-card\"]//div[@role=\"cell\"][2]")
		WebElement jobNameList;
		
		@FindBy(xpath= "//a[@role=\"menuitem\"][text()=\"Job Categories\"]")
		WebElement  jobCategory;
		
		@FindBy(xpath = "//button[@type=\"button\"][@class=\"oxd-button oxd-button--medium oxd-button--secondary\"]")
		WebElement addButton;
		
		@FindBy(xpath = "(//input[@class=\"oxd-input oxd-input--active\"])[2]")
		WebElement inputField;
		
		@FindBy(xpath = "//button[@type=\"submit\"]")
		WebElement saveButton;
}
