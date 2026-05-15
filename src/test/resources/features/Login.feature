Feature: Login Functionality

  @ValidLogin
  Scenario: Login with valid default admin credentials

    Given user is on OrangeHRM login page
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
   