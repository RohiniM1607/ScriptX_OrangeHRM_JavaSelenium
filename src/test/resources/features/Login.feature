Feature: Login Functionality

  Background:
    Given user is on OrangeHRM login page

#Login with default credentials
  @ValidLogin
  Scenario: Login with valid default admin credentials
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page

  @InvalidLogin
  Scenario: Login with invalid username and password
    When user enters invalid username and password
    And user clicks on login button
    Then user should see invalid credentials error message
    And user should remain on login page

  @EmptyLogin
  Scenario: Login with empty username and password
    When user enters empty username and password
    And user clicks on login button
    Then user should see required field validation message

  @InvalidPassword
  Scenario: Login with valid username and invalid password
    When user enters valid username and invalid password
    And user clicks on login button
    Then user should see invalid credentials error message

  @InvalidUsername
  Scenario: Login with invalid username and valid password
    When user enters invalid username and valid password
    And user clicks on login button
    Then user should see invalid credentials error message

  @Logout
  Scenario: Verify logout functionality after successful login
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
    And user should be logged out successfully
    
#Login with created employee credentials
	@EmployeeValidLogin
  Scenario: Login with valid Employee credentials
    When user enters valid employee_username and employee_password
    And user clicks on login button
    Then user should be navigated to dashboard page
 