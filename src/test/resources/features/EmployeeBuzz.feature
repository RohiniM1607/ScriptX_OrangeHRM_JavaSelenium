@Shree
Feature: Shree_14-08-2026_ScriptX_OrangeHRM_EmployeeBuzz_feature

  Background:
    Given Employee login with valid credentials
    And Employee is on the dashboard page
    And Employee navigates to the Buzz page

  Scenario: Add a new employee buzz post
    When employee adds a new buzz post
    Then the employee buzz post should be added successfully

  Scenario: Edit an existing employee buzz post
    When employee adds a new buzz post
    And employee edits the buzz post
    Then the employee buzz post should be updated successfully

  Scenario: Delete an employee buzz post
    When employee adds a new buzz post
    And employee deletes the buzz post
    Then the employee buzz post should be deleted successfully