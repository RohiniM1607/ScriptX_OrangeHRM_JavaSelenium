Feature: Feature: Subhashree_13May2026_ScriptX_OrangeHRM File for ApplyLeave_Employee

Description: Employee is applying the leave on the OrangeHRM Application

Background:
  Given Employee login with valid credentials
  And Employee is on the dashboard page

@Subha
Scenario: Verify leave balance reflects after admin sets entitlement
  When the user navigates to Apply Leave and selects "<leaveType>" leave type
  Then correct leave balance should display for "<leaveType>"
Examples:
    | leaveType      |
    | CAN - Vacation |