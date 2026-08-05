Feature: Reshma_21May2026_ScriptX_OrangeHRM File for EmergencyContacts

  Background:
    Given Employee launches the OrangeHRM login
    When Employee enters the valid "username" and "password"
      | username   | password      |
      | Renukkka R | RenukkkaR@123 |
    And the Employee clicks on the login link
    And Employee is on the Dashboard page

  @ReshmaEmergencyContactAdd @Reshma
  Scenario: Add emergency contact with valid data
    When Employee navigates to Emergency Contacts page
    And Employee clicks on the Add Emergency Contact icon
    And Employee fills emergency contact details from "Sheet1"
    And Employee clicks on Save Emergency Contact button
    Then the Emergency Contact should be saved successfully

  @ReshmaEmergencyContactAttachment @Reshma
  Scenario: Add attachment in Emergency Contacts page
    When Employee navigates to Emergency Contacts page
    And Employee clicks on the Add Attachment icon in Emergency Contacts
    And Employee uploads an attachment in "Emergency Contacts"
    And Employee clicks on Save Attachment button in Emergency Contacts
    Then the Attachment in Emergency Contacts should be saved successfully