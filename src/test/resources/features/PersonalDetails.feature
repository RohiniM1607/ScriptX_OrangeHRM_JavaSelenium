@Reshma
Feature: Reshma_15May2026_ScriptX_OrangeHRM File for PersonalDetails
  Background:
    Given Employee is on OrangeHRM login page
    When Employee enters valid username and password
      | username  | password       |
      | Renukkka R | RenukkkaR@123 |
    And Employee clicks on login button
    And the Employee is on the Dashboard page

  @Reshmavalid
  Scenario: Update personal details with valid data
    When Employee navigates to My Info page
    And Employee updates personal details with following data
      | LicenseExpiryDate | Nationality | MaritalStatus | Gender | BloodType | TestField |
      | 2026-06-12        | Indian      | Single        | Female | O+        | 234       |
    And Employee clicks on Save button
    Then Personal details should be updated successfully

  @ReshmaProfilePicture
  Scenario: Upload profile picture successfully
    When Employee navigates to Profile Picture page
    And Employee uploads a profile picture with "\\src\\test\\resources\\profile.jpg"
    And Employee clicks on the Save button
    Then Profile picture should be uploaded successfully with "Success"