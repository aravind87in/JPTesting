@comments
Feature: Comment on the posts

  Scenario: Get all comments
    When I get all comments using "/comments"
    Then I should get 500 comments

  Scenario: Get all comments on Post 1
    When I get all the comments on post 1 using "/comments?postId=1"
    Then I should get 5 comments


  Scenario: Comment on post 2
    When the user with name "Test" and email address "test@email.com" comments on post id 2 as "This is a great post"
    Then the comment should be created
    And the user should get 201 as response
