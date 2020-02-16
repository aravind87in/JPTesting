package steps;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class PostJsonPost {

	private static String payload = "{\n" + "  \"userId\": \"10\",\n" + "  \"title\": \"Test by Aravind\",\n"
			+ "  \"body\": \"Time will tick for all, Hope you count every minute\"\n" + "}";

	@Test
	public void postJsonPayload() {

		Response r = given().contentType(ContentType.JSON).body(payload).post("https://jsonplaceholder.typicode.com/posts")
				.then().statusCode(201).extract().response();
		
		String body = r.getBody().asString();
    	System.out.println(body);
	}
		}