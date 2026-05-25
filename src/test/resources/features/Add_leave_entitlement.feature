Feature: Mylambigai_13May2026_ScriptX_OrangeHRM File for Add leave entitlement
To add leave Entitlement for Admin/Employee so that they can take leave 

  Background:
    Given Admin is on the Leave page
    And Admin clicks on Entitlements and Admin selects Add Entitlements

  @myl @Valid
  Scenario: Add leave entitlement successfully
    When Admin enters valid employee leave entitlement details
    And clicks on the Save button
    Then the Updating Entitlement pop-up should be displayed and  leave entitlement should be added successfully

@myl @Invalid
  Scenario Outline: Add leave entitlement without mandatory fields
    When Admin enters "<EmployeeName>", "<LeaveType>", "<Entitlement>" in input field
    And clicks on the Save button
    Then the error message should be displayed

Examples:
    | EmployeeName | LeaveType      | Entitlement |
    |                          | CAN - Personal | 5               | 
    
