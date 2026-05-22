@Reshma
Feature: Reshma_19May2026_ScriptX_OrangeHRM File for ProfilePicture

  Background:
    Given Employee is on OrangeHRM Profile Picture login page
    When Employee enters valid username and password for profile
      | username    | password       |
      | Renukkka R  | RenukkkaR@123  |
    And Employee clicks on login
    And the Employee is on the OrangeHRM Dashboard page
    
  @ReshmaProfilePicture 
  Scenario: Upload profile picture successfully
    When Employee navigates to Profile Picture page
    And Employee uploads a profile picture with "\\src\\test\\resources\\profile.jpg"
    And Employee clicks on the Save button
    Then Profile picture should be uploaded successfully with "Success"
