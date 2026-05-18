Feature: Reshma_18May2026_ScriptX_OrangeHRM File for ContactDetails

  Background:
    Given Employee is on OrangeHRM Contact Details login page
    When Employee enters valid credentials for contact details
      | username   | password      |
      | Renukkka R | RenukkkaR@123 |
    And Employee clicks on the login button
    And the Employee is on the OrangeHRM Dashboard page

  @ReshmaContact
  Scenario: Update contact details with valid data
    When Employee navigates to Contact Details page
    And Employee updates contact details from excel sheet "ContactDetails" and row 1
    And Employee clicks on Contact Details Save button
    Then Contact details should be updated successfully