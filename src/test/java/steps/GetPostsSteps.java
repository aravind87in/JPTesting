package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class GetPostsSteps {
	Response res;
	String responseBody;
	String bodyStringValue;
	JsonPath jsonPathEvaluator;
	ResponseBody body;

	@Given("^there are (\\d+) posts$")
	public void there_are_posts(int numOfPosts) {

		// This step is for readability, according to the documentation the number of
		// posts do not change, and there are 100 posts
	}

	@When("^I get the posts using \"([^\"]*)\"$")
	public void i_get_the_posts_using(String url) {
		res = given().when().get(url).then().statusCode(HttpStatus.SC_OK).extract().response();

	}

	@Then("^I should get all the (\\d+) posts$")
	public void i_should_get_all_the_posts(int size) {
		res.then().statusCode(HttpStatus.SC_OK).assertThat().body("size()", is(size));
	}

	@Given("^there is a post with id (\\d+)$")
	public void there_is_a_post_with_id(int postId) {
		// Post with id 2 exists

	}

	@When("^I get it using \"([^\"]*)\"$")
	public void i_get_it_using(String url) {
		res = given().when().get(url).then().statusCode(HttpStatus.SC_OK).extract().response();
		body = res.getBody();
		bodyStringValue = body.asString();
		jsonPathEvaluator = res.jsonPath();
	}

	@Then("^I should get this post$")
	public void i_should_get_this_post(String responseJson) {

		res.then().statusCode(HttpStatus.SC_OK).assertThat().body("size()", is(1));
		Assert.assertTrue(((String) jsonPathEvaluator.get("title[0]")).equalsIgnoreCase("qui est esse"));
		// Assert.assertTrue(((String) jsonPathEvaluator.get("body[0]")).contains("est
		// rerum tempore vitae\r\n" +
		// "sequi sint nihil reprehenderit dolor beatae ea dolores neque\r\n" +
		// "fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\r\n" +
		// "qui aperiam non debitis possimus qui neque nisi nulla"));

	}

	@When("^I get all the posts from that user using \"([^\"]*)\"$")
	public void i_get_all_the_posts_from_that_user_using(String url) {
		res = given().when().get(url).then().statusCode(HttpStatus.SC_OK).extract().response();

	}

	@Then("^I should get (\\d+) posts$")
	public void i_should_get_posts(int size) {
		res.then().assertThat().body("size()", is(size));
	}

	@When("^I get all the comments on this post using \"([^\"]*)\"$")
	public void i_get_all_the_comments_on_this_post_using(String url) {
		res = given().when().get(url).then().statusCode(HttpStatus.SC_OK).extract().response();

	}

	@Then("^I should get all the (\\d+) comments on that post$")
	public void i_should_get_all_the_comments_on_that_post(int size) {
		res.then().assertThat().body("size()", is(size));
	}

}
