package requests;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
public class HTTPRequests {

	//@Test
	void setPathAndQUery() {
		given()
			.pathParam("apiPath", "api")
			.pathParam("usersPath", "users")
			.queryParam("page",2)
			.log().all()
		.when()
			.get("https://reqres.in/{apiPath}/{usersPath}")
		.then()
			//.statusCode(200)
			.log().all();
	}
	
	//@Test
	void testHeadersAndCookies() {
		given()
			.pathParam("getPath", "get")
		.when()
			.get("https://postman-echo.com/{getPath}")
		.then()
			.cookie("sails.sid", notNullValue())
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	//@Test
	void getHeadersAndCookies() {
		Response response = given()
			.pathParam("getPath", "get")
		.when()
			.get("https://postman-echo.com/{getPath}")
		.then()
		    .extract()
		    .response();
		
		Headers resheaders = response.getHeaders();
		Map<String,String> cookies = response.getCookies();
		Map<String,String> headers = new HashMap<String,String>();
		
		
		for(Header hd:resheaders) {headers.put(hd.getName(), hd.getValue());}
		
		System.out.println("HEADERS"); 
		System.out.println("==============================================="); 
		headers.forEach((key, value) -> System.out.println(key + " " + value));
		System.out.println("COOKIES");
		System.out.println("==============================================="); 
		cookies.forEach((key, value) -> System.out.println(key + " " + value));
	}
	
	@Test
	void logHeadersAndCookies() {
		given()
			.pathParam("getPath", "get")
		.when()
			.get("https://postman-echo.com/{getPath}")
		.then()
		    .log().cookies()
		    .log().headers();

	}
}
