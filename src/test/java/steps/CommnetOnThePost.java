package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CommnetOnThePost {
	private Response response;
	private String bodyStringValue;
	private JsonPath jsonPathEvaluator;
	private ResponseBody body;

	private String baseURL = "https://jsonplaceholder.typicode.com";
	private String name;
	private String email;
	private int postId;
	private String comment;

	@When("^I get all comments using \"([^\"]*)\"$")
	public void i_get_all_comments_using(String url) {
		response = given().when().get(baseURL + url).then().statusCode(HttpStatus.SC_OK).extract().response();
	}

	@Then("^I should get (\\d+) comments$")
	public void i_should_get_comments(int size) {
		response.then().assertThat().body("size()", is(size));
	}

	@When("^I get all the comments on post (\\d+) using \"([^\"]*)\"$")
	public void i_get_all_the_comments_on_post_using(int postId, String url) {
		response = given().when().get(baseURL + url).then().statusCode(HttpStatus.SC_OK).extract().response();
	}

	@When("^the user with name \"([^\"]*)\" and email address \"([^\"]*)\" comments on post id (\\d+) as \"([^\"]*)\"$")
	public void the_user_with_name_and_email_address_comments_on_post_id_as(String name, String emailAddress,
			int postId, String commentBody) {
		this.name = name;
		this.email = emailAddress;
		this.postId = postId;
		this.comment = commentBody;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("email", emailAddress);
		requestParams.put("body", commentBody);
		requestParams.put("postId", postId);
		request.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		request.body(requestParams.toString());

		// Post the request and check the response
		response = request.post(baseURL+"/comments");
		body = response.getBody();
		bodyStringValue = body.asString();
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("^the comment should be created$")
	public void the_comment_should_be_created()
	{
		Assert.assertTrue(((String) jsonPathEvaluator.get("name")).equalsIgnoreCase(name));
		Assert.assertTrue(((String) jsonPathEvaluator.get("body")).equalsIgnoreCase(comment));
		Assert.assertTrue(((String) jsonPathEvaluator.get("email")).equalsIgnoreCase(email));
		Assert.assertEquals(jsonPathEvaluator.getInt("postId"), 2);

	}

	@Then("^the user should get (\\d+) as response$")
	public void the_user_should_get_as_response(int statusCode) {
		response.then().assertThat().statusCode(statusCode);

	}

}
