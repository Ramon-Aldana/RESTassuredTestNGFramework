package httpmethods;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

/*
 * given()
 * 		content type, set cookies, authentication, parameters, headers
 * 
 * when()
 * 		get, put, post, delete
 * 
 * then() | and()
 * 		validate status code, extract response, extract headers, extract cookies, extract response body
 */

public class HTTPRequests {
	int id;
	
	@Test(priority=0)
	void getUsers() {
		given()
			.headers("Test","getUsers")
		.when()
			.get("https://reqres.in/api/users?page=1")
		.then()
			.statusCode(200)
			.body("page",equalTo(1))
			.log().all();
	}
	
	@Test (priority=1)
	void createOrphanUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "ramon");
		data.put("job","jobseeker");
		
		given()
		.headers("Test","createOrphanUser")
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	@Test (priority=2)
	void createAdoptedUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "ramon");
		data.put("job","jobseeker");
		
		Response response = given()
				.headers("Test","createAdoptedUser")
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users");
			
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		id = Integer.parseInt(jsonPathEvaluator.get("id").toString());
	}
	
	@Test (priority=3, dependsOnMethods = {"createAdoptedUser"})
	void updateAdoptedUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "eduardo");
		data.put("job","employed");
		
		given()
		.headers("Test","updateAdoptedUser")
			.contentType("application/json")
			.body(data)
		.when()
			.put(String.format("https://reqres.in/api/users/%s", id))
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test (priority=4, dependsOnMethods = {"updateAdoptedUser"})
	void deleteAdoptedUser() {
	
		given()
		.headers("Test","deleteAdoptedUser")
			.contentType("application/json")
		.when()
			.delete(String.format("https://reqres.in/api/users/%s", id))
		.then()
			.statusCode(204)
			.log().all();
	}
}
