Feature: Mylambigai_28May2026_OrangeHRM_Search user based on different fields
To serach user by User name, User role, Employee name

  Background:
    Given the admin on the Home page
    And click on the Admin menu

@myl
  Scenario Outline: Search user by User name
    When the Admin enter "<UserName>" in the input field
    And click on search button
    Then the Admin can see the searched "<UserName>" details

    Examples:
      | UserName |
      | Essaki   |

@myl
  Scenario: Search user by user role
    When the Admin select the particular user role
    And click on search button
    Then the admin can see the result based on the selected user role

@myl
  Scenario Outline: Search user by Employee name
    When the admin enters "<employeeName>"  in the input field
    And click on search button
    Then the Admin can see the result of enterd "<employeeName>"

    Examples:
      | employeeName |
      | Ravi M B     |
