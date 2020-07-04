Feature: User

  I want to find a user on my project

  Scenario: find a user
    Given  I have selected the user id 1
    When  I press search for user
    Then  I get the user