
Feature: ProjectApplicants2

  Go to applicant profile

  Scenario: see applicant by project
    Given the author user is in the preview of the postulates
    When you select the box for each applicant
    Then it will redirect you to the applicant's profile