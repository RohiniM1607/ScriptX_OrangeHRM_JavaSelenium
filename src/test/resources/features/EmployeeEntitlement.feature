@Rohini @EmployeeEntitlements
Feature: Rohini_19May2026_OrangeHRM_EmployeeEntitlement

  Description:
  This feature verifies whether Admin can search employee leave entitlements
  in OrangeHRM using employee name, leave type, and leave period.

  Background:
    Given user is on OrangeHRM login page
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
    Given user is on OrangeHRM Leave Entitlements page

  @High @PropertyFile @DataTable @CSV
  Scenario: Search employee entitlement by employee name leave type and leave period
    When user searches employee entitlement
      | fieldName    |
      | employeeName |
      | leaveType    |
      | leavePeriod  |
    Then employee entitlement search result should be displayed

  @Medium @ScenarioOutline
  Scenario Outline: Search entitlement with invalid employee name
    When user searches entitlement with invalid employee name "<employeeName>"
    Then invalid employee name validation should be displayed

    Examples:
      | employeeName |
      | InvalidEmp   |
      | TestUser123  |

  @Medium @ExcelDataProvider
  Scenario: Search entitlement without employee name
    When user searches entitlement without employee name
    Then required employee name validation should be displayed