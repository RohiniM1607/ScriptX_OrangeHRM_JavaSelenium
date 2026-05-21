@Reshma
Feature: Reshma_19May2026_ScriptX_OrangeHRM File for ContactDetails

  Background:
    Given Employee is on OrangeHRM Contact Details page
    When Employee enters username and password for contact details
      | username   | password      |
      | Renukkka R | RenukkkaR@123 |
    And Employee clicks on Contact Details login button
    And the Employee is on the Contact Details Dashboard page

  @ReshmaContactDetails

  Scenario: Update contact details and add attachment successfully
    When the Employee navigates to Contact Details page
    And Employee updates contact details from test data
    And Employee clicks on the Contact Details Save button
    Then the Contact details should be updated successfully
    When Employee clicks on Add Attachment icon
    And Employee uploads attachment from test data
    And Employee clicks on Save Attachment button
    Then the Attachment should be uploaded successfully