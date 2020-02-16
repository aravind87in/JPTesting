@create
Feature: Create posts

  Scenario Outline: Create a post
    When the user "<UserId>" sends a request to "https://jsonplaceholder.typicode.com/posts" with "<title>", "<Body>"
    Then the user should get response code 201
    And the newly created post as response

    Examples: 
      | title | Body        | UserId | 
      | Mr.A  | Test Post   |      2 |    
      | Mr.B  |             |        |    
      |       | Test Post 2 |        |    
      |       |             |      3 |  
      |       |             |        |
