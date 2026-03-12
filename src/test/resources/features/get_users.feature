Feature: Get Users feature - Author Gyanendra
  Scenario: Validate user list page 2
    Given I send a GET request to "api/users?page=2"
    Then the response status code should be 200
    And the response should match JSON schema "schemas/users-list-schema.json"
