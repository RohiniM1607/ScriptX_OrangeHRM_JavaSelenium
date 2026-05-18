Feature: Mylambigai_13May2026_ScriptX_OrangeHRM File for Add leave entitlement
To add leave Entitlement for Admin/Employee so that they can take leave 

  Background:
    Given Admin is on the Leave page
    And Admin clicks on Entitlements and Admin selects Add Entitlements

  @myl
  Scenario: Add leave entitlement successfully
    When Admin enters valid employee leave entitlement details
    And clicks on the Save button
    Then the Updating Entitlement pop-up should be displayed and  leave entitlement should be added successfully

  Scenario: Add leave entitlement  without mandatory fields
    When Admin leaves the employee name field empty”
    And clicks on the Save button
    Then the error message should be displayed
    
