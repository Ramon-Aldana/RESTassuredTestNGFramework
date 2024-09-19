package endpoints;

import static io.restassured.RestAssured.*;

import payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User payload) {
		return given().log().uri().log().body()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.post_url)
				.then()
					.extract()
					.response();
			
	}
	
	public static Response readUser(String username) {
		return given().log().uri()
					.pathParam("username", username)
				.when()
					.get(Routes.get_url)
				.then()
					.extract()
					.response();
			
	}
	
	public static Response updateUser(String username, User payload) {
		return given().log().uri().log().body()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", username)
					.body(payload)
				.when()
					.put(Routes.update_url)
				.then()
					.extract()
					.response();
			
	}
	
	public static Response deleteUser(String username) {
		return given().log().uri()
					.pathParam("username", username)
				.when()
					.delete(Routes.delete_url)
				.then()
					.extract()
					.response();
			
	}
}
