Feature: ProjectApplicants

  Manage to see the applicants to a project

  Scenario: see project applicants
    Given Given the author user is in the box of his projects
    When you select the project
    Then you can select the option to view the applicants