Feature: Add job category

Background:
Given the User on the Home page
  And click on the Admin menu
  And User click on  Job button  and navigates to the Job categories page
  And click on Add button
  
  Scenario: Add valid new job category
  When the user enters Job name 
  And click on save button
  Then the added Job displayed
