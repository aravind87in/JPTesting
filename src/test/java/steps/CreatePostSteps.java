package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;
import org.junit.Assert;

public class CreatePostSteps {
	private Response response;
	private String bodyStringValue;
	private JsonPath jsonPathEvaluator;
	private ResponseBody body;
	private String userId;
	private String title;
	private String requestBody;

	@When("^the user \"([^\"]*)\" sends a request to \"([^\"]*)\" with \"([^\"]*)\", \"([^\"]*)\"$")
	public void the_user_sends_a_request_to_with(String userId, String url, String title, String requestBody)
	 {
//Using this to assert the response json values
		this.userId = userId;
		this.title = title;
		this.requestBody = requestBody;
	//Post request creation	
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("title", title);
		requestParams.put("userId", userId);
		requestParams.put("body", requestBody);
		request.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		request.body(requestParams.toString());

		// Post the request and check the response
		response = request.post(url);
		body = response.getBody();
		bodyStringValue = body.asString();
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("^the user should get response code (\\d+)$")
	public void the_user_should_get_response_code(int responseCode) 
	{
		response.then().assertThat().statusCode(responseCode);
	}

	@Then("^the newly created post as response$")
	public void the_newly_created_post_as_response() {
		Assert.assertTrue(((String) jsonPathEvaluator.get("title")).equalsIgnoreCase(title));
		Assert.assertTrue(((String) jsonPathEvaluator.get("body")).equalsIgnoreCase(requestBody));
		Assert.assertTrue(((String) jsonPathEvaluator.get("userId")).equalsIgnoreCase(userId));
		//Newly created post Id is always 101
		Assert.assertEquals(jsonPathEvaluator.getInt("id"), 101);
	}

}
