@Rohini @CreateUserCredential
Feature: Rohini_14May2026_OrangeHRM_Create User Credential
  Description:
  This feature verifies whether Admin can create user login credentials
  for ESS employee and Admin user in OrangeHRM.

  Background:
    Given user is on OrangeHRM login page
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
    Given user is on OrangeHRM Admin User Management page
   
  @Rohini @CreateCredential @Valid
  Scenario Outline: Create user login credential
    When user clicks on Add button
    And user enters user credential details "<role>" "<employeeName>" "<status>" "<username>" "<password>" "<confirmPassword>"
    And user clicks on Save button
    Then user credential should be created successfully

    Examples:
      | role  | employeeName | status  | username   | password      | confirmPassword |
      | ESS   | Employee 1   | Enabled | Employee@1 | Employee1@123 | Employee1@123   |
      | Admin | Admin 1      | Enabled | Admin@1    | Admin1@123    | Admin1@123      |
      | ESS   | Employee 2   | Enabled | Employee@2 | Employee2@123 | Employee2@123   |
      | Admin | Admin 2      | Enabled | Admin@2    | Admin2@123    | Admin2@123      |
      
  @Rohini @CreateCredential @Invalid
  Scenario: Create user without mandatory fields 
  When user clicks on Add button 
  And user clicks on Save button without entering mandatory fields 
  Then required validation message should be displayed for mandatory fields
  
 @Rohini @CreateCredential @Duplicate_UserName
    Scenario Outline: Create user with duplicate username
    When user clicks on Add button
    And user enters user credential details by duplicate username "<role>" "<employeeName>" "<status>" "<username>" "<password>" "<confirmPassword>"
    Then required validation message should be displayed for duplicate username
     Examples:
     | role  | employeeName | status  | username   | password      | confirmPassword |
     | Admin |  Admin 1      | Enabled | Admin    | Admin1@123    | Admin1@123      |
     | Admin |  Admin 2      | Enabled | Admin    | Admin2@123    | Admin2@123      |
     | ESS |  Employee 1      | Enabled | Admin    | Employee1@123    | Employee1@123      |
     | ESS |  Employee 2      | Enabled | Admin    | Employee2@123    | Employee2@123      |
