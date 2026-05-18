Feature: Jagadeep_15-05-2026_ScriptX_OrangeHRm_Create_Employee_Functionality

  @CreateEmployee
  Scenario Outline: Create employee with multiple data combinations

    Given admin is logged into OrangeHRM
    When admin enters employee details "<firstName>" "<lastName>" "<employeeId>"
    And admin clicks save button
    Then employee result should be "<result>"

    Examples:
      | firstName | lastName | employeeId | result    |
      | Jagadee   | KC       | 1001       | success   |
      | Rohi      | Kumar    | 1002       | success   |
      | Anan      |          | 1003       | required  |
      | @Jagadee  | %KC      | 1004       | success   |
      |            |          |            | required  |