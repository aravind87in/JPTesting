package steps;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUsersSteps {
	String responseBody;
	Response res;
	String bodyStringValue;
	JsonPath jsonPathEvaluator;
	ResponseBody body;

	@When("^I get the users using \"([^\"]*)\"$")
	public void i_get_the_users_using(String url) throws Throwable {
		res = given().when().get(url).then().statusCode(HttpStatus.SC_OK).extract().response();
		body = res.getBody();
		bodyStringValue = body.asString();
		jsonPathEvaluator = res.jsonPath();

	}

	@Then("^I should get all the (\\d+) users$")
	public void i_should_get_all_the_users(int size) throws Throwable {
		res.then().statusCode(HttpStatus.SC_OK).assertThat().body("size()", is(size));

	}

	@Then("^I should get all the details of user with id (\\d+)$")
	public void i_should_get_all_the_details_of_user_with_id(int id) throws Throwable {
		res.then().statusCode(HttpStatus.SC_OK).assertThat().body("size()", is(1));
		Assert.assertTrue(((String) jsonPathEvaluator.get("name[0]")).equalsIgnoreCase("Ervin Howell"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("email[0]")).equalsIgnoreCase("Shanna@melissa.tv"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("address.street[0]")).equalsIgnoreCase("Victor Plains"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("address.suite[0]")).equalsIgnoreCase("Suite 879"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("address.city[0]")).equalsIgnoreCase("Wisokyburgh"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("address.zipcode[0]")).equalsIgnoreCase("90566-7771"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("address.geo.lat[0]")).equalsIgnoreCase("-43.9509"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("address.geo.lng[0]")).equalsIgnoreCase("-34.4618"));

		Assert.assertTrue(((String) jsonPathEvaluator.get("phone[0]")).equalsIgnoreCase("010-692-6593 x09125"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("website[0]")).equalsIgnoreCase("anastasia.net"));

		Assert.assertTrue(((String) jsonPathEvaluator.get("company.name[0]")).equalsIgnoreCase("Deckow-Crist"));
		Assert.assertTrue(((String) jsonPathEvaluator.get("company.catchPhrase[0]"))
				.equalsIgnoreCase("Proactive didactic contingency"));
		Assert.assertTrue(
				((String) jsonPathEvaluator.get("company.bs[0]")).equalsIgnoreCase("synergize scalable supply-chains"));

	}
}
