@Jagadeep_K_C
Feature: Jagadeep_13-05-2026_ScriptX_OrangeHRm_Buzz_feature

  Background:
    Given admin is logged into OrangeHRM Buzz page

  Scenario: Create a buzz post successfully
    When admin enters valid buzz content
    And admin clicks the post button
    Then the buzz post should be published successfully

  Scenario: Verify empty buzz post is not allowed
    When admin leaves the buzz content empty
    And admin clicks the post button
    Then no new buzz post should be created

  Scenario: Verify newly published post appears in feed
    When admin publishes a buzz post
    Then the post should be visible in the buzz feed
    
  Scenario: Delete a buzz post successfully
    When admin creates and deletes a buzz post
    Then the buzz post should be removed from the feed