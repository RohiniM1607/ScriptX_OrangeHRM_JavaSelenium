@Reshma
Feature: Reshma_19May2026_ScriptX_OrangeHRM File for ContactDetails

  Background:
    Given Employee is on OrangeHRM login page
    When Employee enters valid username and password
      | username   | password      |
      | Renukkka R | RenukkkaR@123 |
    And Employee clicks on login button
    And the Employee is on the Dashboard page

  @ReshmaContactDetails
  Scenario: Update contact details with valid test data
    When Employee navigates to Contact Details page
    And Employee updates contact details from test data
    And Employee clicks on the Contact Details Save button
    Then the Contact details should be updated successfully

  @ReshmaContactAttachment
  Scenario: Upload attachment on Contact Details page
    When Employee navigates to Contact Details page
    And Employee clicks on Add Attachment icon
    And Employee uploads attachment from test data
    And Employee clicks on Save Attachment button
    Then the Attachment should be uploaded successfully