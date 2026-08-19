package com.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportPage extends BasePage {

    // ── Supervisor section ──────────────────────────────────────────────

    // Add button inside Assigned Supervisors section
    @FindBy(xpath = "//h6[normalize-space()='Assigned Supervisors']/following::button[normalize-space()='Add'][1]")
    public WebElement addSupervisorButton;

    // Supervisor name autocomplete input
    @FindBy(xpath = "//h6[normalize-space()='Assigned Supervisors']/following::input[@placeholder='Type for hints...'][1]")
    public WebElement supervisorNameInput;

    // Supervisor reporting method dropdown
    @FindBy(xpath = "//h6[normalize-space()='Assigned Supervisors']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement supervisorReportingMethodDropdown;

    // Save button scoped to Supervisor form
    @FindBy(xpath = "//h6[normalize-space()='Assigned Supervisors']/following::button[@type='submit'][1]")
    public WebElement supervisorSaveButton;
    
    // Autocomplete suggestion dropdown list items
    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> autoCompleteOptions;

    // Reporting method dropdown listbox
    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement dropdownListbox;

    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> dropdownOptions;

    // Success toast message
    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]//p[contains(@class,'oxd-text')]")
    public WebElement successMessage;
}