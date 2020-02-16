@getposts
Feature: Get Posts

  Scenario: Get all the posts
    Given there are 100 posts
    When I get the posts using "https://jsonplaceholder.typicode.com/posts"
    Then I should get all the 100 posts

  Scenario: Get one post by the post Id
    Given there is a post with id 2
    When I get it using "https://jsonplaceholder.typicode.com/posts?id=2"
    Then I should get this post
      """
      
      [{
        "userId": 1,
        "id": 2,
        "title": "qui est esse",
        "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
      }]

      """

  Scenario: Get all posts from a user
    Getting posts from User with id 1 
         
    When I get all the posts from that user using "https://jsonplaceholder.typicode.com/posts?userId=1"
    Then I should get 10 posts

  Scenario: Get all comments for a post Id
    A post by Id 1 exists and has 5 comments
    When I get all the comments on this post using "https://jsonplaceholder.typicode.com/comments?postId=1"
    Then I should get all the 5 comments on that post
