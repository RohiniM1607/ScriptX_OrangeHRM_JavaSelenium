@SubhaEmployeeEntitlement
Feature: Subhashree_17Aug_2026_ScriptX_OrangeHRM File for Employee Leave Entitlement

  Background:
    Given Employee login with valid credentials
    And Employee is on the dashboard page
    When user navigates to Leave Entitlements page

  Scenario Outline: Verify leave entitlement details for a valid leave type
    When user selects leave type "<LeaveType>"
    And user clicks search entitlements button
    Then entitlement records should be displayed

    Examples:
      | LeaveType       |
      | CAN - Personal  |
      | CAN - Vacation  |


  Scenario Outline: Verify Total Days displayed without selecting leave type
    When user selects leave period "<LeavePeriod>"
    And user clicks search entitlements button
    Then Total Days text should be visible

    Examples:
      | LeavePeriod              |
      | 2026-01-01 - 2026-31-12  |