package requestbody;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;



public class HTTPPayloads {

	//@Test(priority=1)
	void postHashMap() {
		given()
			.contentType("application/json")
			.body(BodyGenerator.NewHashMapStudent())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("John Doe"))
			.body("age", equalTo(20))
			.body("courses[0].course_id",equalTo(101))
			.log().all();
	}
	
	//@Test(priority=2)
	void posJSONObject() {
		given()
			.contentType("application/json")
			.body(BodyGenerator.NewJSONObjectStudent().toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("John Doe"))
			.body("age", equalTo(20))
			.body("courses[0].course_id",equalTo(101))
			.log().all();
	}
	
	//@Test(priority=3)
	void posPOJOClass() {
		given()
			.contentType("application/json")
			.body(BodyGenerator.NewPOJOStudent())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("John Doe")) 
			.body("age", equalTo(20))
			.body("courses[0].course_id",equalTo(101))
			.log().all();
	}
	
	@Test(priority=4)
	void posJSONFile() throws IOException {
		given()
			.contentType("application/json")
			.body(BodyGenerator.NewJsonFileStudent().toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("John Doe"))
			.body("age", equalTo(20))
			.body("courses[0].course_id",equalTo(101))
			.log().all();
	}
}
