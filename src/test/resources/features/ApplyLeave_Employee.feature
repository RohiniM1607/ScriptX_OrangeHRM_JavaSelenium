Feature:  Subhashree_13May2026_ScriptX_OrangeHRM File for ApplyLeave_Employee

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
    |leaveType      |from date  |To date    |
    |CAN - Personal |2026-03-06 |2026-03-06 |