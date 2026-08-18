Feature: Reshma_15May2026_ScriptX_OrangeHRM File for Salary

  Background:
    Given Employee is on OrangeHRM login page
    When Employee enters valid username and password
      | username   | password      |
      | Renukkka R | RenukkkaR@123 |
    And Employee clicks on login button
    And the Employee is on the Dashboard page

  @ReshmaSalary @Reshma
  Scenario: Add salary component details from CSV file
    When Employee navigates to Salary page
    And Employee adds salary details from CSV
    Then All salary records should be saved successfully