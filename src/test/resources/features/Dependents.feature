Feature: Reshma_15May2026_ScriptX_OrangeHRM File for Dependents

  Background:
    Given Employee launches OrangeHRM login page
    When Employee enters valid "username" and "password" using employee credentials
      | username   | password      |
      | Renukkka R | RenukkkaR@123 |
    And Employee clicks on login link
    And Employee is on Dashboard page

  @ReshmaDependents @Reshma
  Scenario Outline: Add dependent details with valid data
    When Employee navigates to Dependents page
    And Employee clicks on Add icon in Dependents section
    And Employee fills dependent details with "<Name>" "<Relationship>" and "<DateOfBirth>"
    And Employee clicks on Save button in Dependents section
    Then Dependent should be saved successfully

    Examples:
      | Name          | Relationship | DateOfBirth |
      | Priya Sharma  | child        | 1990-07-25  |
      | Ravi Verma    | child        | 1985-11-05  |
      
  @ReshmaDependents @Reshma
  Scenario: Upload a valid attachment in Dependents section
  When Employee moves to Dependents page
  And Employee clicks on Add button in the Attachments section
  And Employee uploads attachment with details
    | FileName            | Comment          |
    | testfile2.txt       | Dependent proof  |
  And Employee clicks on Save button in Attachments section
  Then Attachment should be uploaded successfully
  
  @ReshmaDependents @Reshma
  Scenario: Upload an invalid file size in Dependents Attachments section
  When Employee navigates to the Dependents page
  And Employee clicks on Add button in the Attachments
  And Employee uploads invalid attachment details
    | FileName       | ErrorMessage             |
    | Large_file.txt | Attachment Size Exceeded |
  Then An error message should be displayed
