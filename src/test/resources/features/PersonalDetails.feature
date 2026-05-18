 Feature: Reshma_13May2026_ScriptX_OrangeHRM File for PersonalDetails

  Background:
    Given Employee is on OrangeHRM login page
    When Employee enters valid username and password
    |username     |   password      |
    |Renukkka R   | RenukkkaR@123   |
    And Employee clicks on login button
    And the Employee is on the Dashboard page

  @Reshma
  Scenario: Update personal details with valid data
    When Employee navigates to My Info page
    And Employee updates personal details with following data
  | LicenseExpiryDate | Nationality | MaritalStatus | Gender | BloodType | TestField |
  | 2026-06-12        | Indian      | Single        | Female | O+        | 234       |
    And Employee clicks on Save button
    Then Personal details should be updated successfully
    
  @InvalidReshma
  Scenario: Edit personal details without saving changes
    When Employee moves to My Info page
    And Employee edit personal details with following data
  | LicenseExpiryDate | Nationality | MaritalStatus | Gender | BloodType | TestField |
  | 2026-06-10        | Chinese     | Single        | Female | A+        | 223       |
    And Employee forgot to click on Save button
    Then Personal details should not saved successfully
