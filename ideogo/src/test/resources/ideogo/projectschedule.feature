Feature: ProjectScheduleFeature1

  Manage to see the schedule of a project

  Scenario: see the schedule of my project

    Given I have  selected my project 2
    When I press see schedule
    Then I get the  specific schedule of my project