Feature: Skill

  Skill basic functions

  Scenario: Get list of skills by tag id
    Given I have selected tag 1
    When I press skills
    Then I get the whole list of skills to choose