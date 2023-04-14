Feature: See contries
  AS
  user
  I WANT TO
  see species
  SO THAT
  see list species
  @Species
  Scenario: Success species list
    Given a user that wants see list species
    When the user sends request
    Then the user gets a species list