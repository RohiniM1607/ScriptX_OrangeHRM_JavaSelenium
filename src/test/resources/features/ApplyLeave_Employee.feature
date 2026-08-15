Feature: Updated_Subhashree_14Aug2026_ScriptX_OrangeHRM File for ApplyLeave_Employee

Description: Employee is applying the leave on the OrangeHRM Application

Background:
  Given Employee login with valid credentials
  And Employee is on the dashboard page

@Subha
Scenario Outline: Apply leave successfully
  When the user navigates to Apply Leave and selects "<leaveType>" leave type
  And selects "<from date>" "<To date>"
  And click on save button
  Then the success message should display

Examples:
    | leaveType      | from date  | To date  |
    | CAN - Personal |   AUTO     | AUTO     |
    | CAN - Vacation |   AUTO     | AUTO     |

@Subha
Scenario Outline: Apply leave without selecting leave type
  When the user navigates to Apply Leave without selecting leave type
  And selects "<from date>" "<To date>"
  And click on save button
  Then the required field error message should display

Examples:
    | from date  | To date    |
    | AUTO       | AUTO       |
      

@Subha
Scenario Outline: Cancel the applied leave
  When the user navigates to Apply Leave and selects "<leaveType>" leave type
  And selects "<from date>" "<To date>"
  And click on save button
  Then the success message should display
  When the user navigates to My Leave list
  And cancels the applied leave for "<leaveType>"
  Then the leave cancelled message should display

Examples:
    | leaveType      | from date  | To date    |
    | CAN - Personal | AUTO       | AUTO       |
   
