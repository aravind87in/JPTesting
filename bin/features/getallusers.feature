@getusers
Feature: Get list of users

  Scenario: Get all users
    When I get the users using "https://jsonplaceholder.typicode.com/users"
    Then I should get all the 10 users

  Scenario: Get specific user details
    When I get the users using "https://jsonplaceholder.typicode.com/users?id=2"
    Then I should get all the details of user with id 2
