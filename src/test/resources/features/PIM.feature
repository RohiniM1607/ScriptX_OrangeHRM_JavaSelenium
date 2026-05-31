@Jagadeep_K_C
Feature: Jagadeep_15-05-2026_ScriptX_OrangeHRm_PIM_Functionality

  Description: This feature tests create and search of employee

  @CreateEmployee
  Scenario: Create employee with multiple data combinations

    Given admin is logged into OrangeHRM
    When admin creates employees with following data
      | firstName | lastName | employeeId | result   |
      | Jagadeep  | KC        | 9011       | success  |
      | Rohit     | Kumar     | 9012       | success  |
      | Admin     | 1         | 1982       | failed   |
      | Employee  | 1         | 6734       | failed   |
      | Anand     | Kumar     | 9013       | success  |
      |            |           |            | required |
      | Jaga@123  | K%C       | 9014       | failed   |

  @SearchByEmployeeName
  Scenario: Search employee by employee name

    Given admin is logged into OrangeHRM search page
    When admin searches employee by employee name
    Then employee search result should be displayed

  @SearchByEmployeeId
  Scenario: Search employee by employee ID

    Given admin is logged into OrangeHRM search page
    When admin searches employee by employee ID
    Then employee search result should be displayed

  @InvalidEmployeeName
  Scenario: Search employee with invalid employee name

    Given admin is logged into OrangeHRM search page
    When admin searches employee with invalid employee name
    Then no employee records should be displayed

  @InvalidEmployeeId
  Scenario: Search employee with invalid employee ID

    Given admin is logged into OrangeHRM search page
    When admin searches employee with invalid employee ID
    Then no employee records should be displayed
