package jsonresponse;
import org.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;






public class parsingMethods {
	//@Test
	void assertJSONBody() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/books")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("book[3].title",equalTo("To Kill a Mockingbird"))
			.log().body();
	}

	//@Test
	void assertJSONPathResponse() {
		
		Response response = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/books")
		.then()
			.extract().response();
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		Assert.assertEquals(response.jsonPath().getString("book[3].title"),"To Kill a Mockingbird");
	 
		//response.jsonPath().getList("book.title").forEach((value) -> System.out.println("Title: " + value));
		
	}
	
	//@Test
	void assertJSONObject() {
		
		Response response = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/books")
		.then()
			.extract().response();
		
		JSONObject jo = new JSONObject(response.toString());
		
	}
	@Test
	void assertXMLBody() {
		
		given()
			//.contentType(ContentType.JSON)
		.when()
			.get("https://mp309a818d36187653c6.free.beeceptor.com/api/Traveler")
		.then()
			.statusCode(200)
			.body("Travelers.Travelerinformation[0].id", equalTo("1"))
			.log().headers();
	}
	
	@Test
	void assertXMLPathResponse() {
		
		Response response = given()
			.contentType(ContentType.XML)
		.when()
			.get("https://mp309a818d36187653c6.free.beeceptor.com/api/Traveler")
		.then()
			.extract().response();
		
		Assert.assertEquals(response.xmlPath().get("Travelers.Travelerinformation[0].id").toString(), "1");
	}

	@Test
	void assertXMLPath() {
		
		Response response = given()
			.contentType(ContentType.XML)
		.when()
			.get("https://mp309a818d36187653c6.free.beeceptor.com/api/Traveler")
		.then()
			.extract().response();
		
		XmlPath xmlObj = new XmlPath(response.asString());
		Assert.assertEquals(xmlObj.getList("Travelers.Travelerinformation").size(), 20);
		
		int items = xmlObj.get("Travelers.Travelerinformation.findAll { it.id == '1' }.size()");
		Assert.assertEquals(items,1);
		
		String emal = xmlObj.getString("**.find { it.name == 'Emily Davis' }.email");
		Assert.assertEquals(emal,"emilydavis@example.com");
				 
	}
}
