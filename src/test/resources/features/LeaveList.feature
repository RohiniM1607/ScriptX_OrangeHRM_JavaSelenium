@Rohini @LeaveList
Feature: Rohini_01Jun2026_OrangeHRM_LeaveList

	Description:
		This feature verifies whether Admin can search and filter employee leave requests.

  Background:
    Given user is on OrangeHRM login page
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
    Given user is on Leave List page

  @View_All_Leave
  Scenario: View all employee leave requests
    When user clicks Search button
    Then all employee leave requests should be displayed
    
  @Search_Employee
  Scenario Outline: Search leave request by employee name
    When user searches leave request using "<Employee Name>"
    Then leave requests of "<Employee Name>" should be displayed

    Examples:
      | Employee Name  |
      | Orange Test |
      | Ranga Akunuri |

  