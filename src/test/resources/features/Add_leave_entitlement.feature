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
      |              | CAN - Personal | 5           |

  @myl @Invalid
  Scenario Outline: Add Leave entitlement with invalid employee name
    When the Admin enters invalid "<Employee name>"
    Then the "<Error message>" should be displayed

    Examples:
      | Employee name | Error message    |
      | Myl           | No Records Found |

  @myl
  Scenario: Entering exceeding entitlement value
    When the admin enters details in input fields
      | EmployeeName | LeaveType      | InvalidEntitlementValue |
      | Ravi M B     | CAN - Personal | 10000                   |
    And clicks on the Save button
    Then the invalid message is displayed
