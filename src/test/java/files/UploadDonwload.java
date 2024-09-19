package files;


import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

public class UploadDonwload {

	@Test
	void SingleFileUpload() {
		
		File upFile = new File(".\\testfile0.txt");
		
		given()
			.contentType(ContentType.MULTIPART)
			.multiPart("file",upFile)
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("testfile0.txt"))
			.log().all();
	}
	
	@Test
	void MultiFileUpload() {
		
		File upFile1 = new File(".\\testfile1.txt");
		File upFile2 = new File(".\\testfile2.txt");
		
		given()
			.contentType(ContentType.MULTIPART)
			.multiPart("files",upFile1)
			.multiPart("files",upFile2)
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("testfile1.txt"))
			.body("[1].fileName", equalTo("testfile2.txt"))
			.log().all();
	}
}
