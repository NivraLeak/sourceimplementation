Feature: User2

  Create a user

  Scenario: create account
    Given I have registered
    When I press register
    Then my account will have been created