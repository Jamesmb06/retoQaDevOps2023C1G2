Feature: See object list
  AS
  user
  I WANT TO
  see object of pharmacy
  SO THAT
  see object of pharmacy list

  @Listpharmacy
  Scenario: Success object pharmacy list
    Given a user that wants see object of pharmacy
    When the user sends requests
    Then the user gets a object pharmacy list