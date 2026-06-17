Feature: Employee Leave List

 Background:
  Given Employee login with valid credentials
  And Employee is on the dashboard page

  Scenario: Verify leave status for applied leave
    When user navigates to My Leave page
    And user searches leave with the following details
      | leaveType          | expectedStatus    |
      | CAN - Personal     | Scheduled (1.00)  |
    Then leave status should match expected status

  Scenario: Verify remaining leave balance
    When user searches leave balance with the following details
      | leaveType         |
      | CAN - Personal    |
    Then remaining leave balance should be greater than 0
    
