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
    | leaveType      | from date  | To date    |
    | CAN - Personal | 2026-09-10 | 2026-09-10 |
    | CAN - Vacation | 2026-11-12 | 2026-11-12 |

@Subha
Scenario Outline: Apply leave without selecting leave type
  When the user navigates to Apply Leave without selecting leave type
  And selects "<from date>" "<To date>"
  And click on save button
  Then the required field error message should display

Examples:
    | from date  | To date    |
    | 2026-10-01 | 2026-10-01 |

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
    | CAN - Personal | 2026-11-03 | 2026-11-03 |
    | CAN - Vacation | 2026-12-11 | 2026-12-11 |
