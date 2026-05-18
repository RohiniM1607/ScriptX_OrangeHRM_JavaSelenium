Feature: Search user based on different fields

To serach user by User name, User role, Employee name

Background:
Given the admin on the Home page 
And click on the Admin menu

Scenario: Search user by User name
When the Admin enter user name in the input field 
And click on search button
Then the Admin can see the searched username details

Scenario: Search user by user role
When the Admin select the particular user role 
And click on search button
Then the admin can see the result based on the selected user role

Scenario: Search user by Employee name
When the admin enters employee name  in the input field
And click on search button
Then the Admin can see the result of enterd employee name