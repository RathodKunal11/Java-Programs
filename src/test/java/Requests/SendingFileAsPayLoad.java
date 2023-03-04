package Requests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.google.common.io.Files;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SendingFileAsPayLoad {
	
	@Test
	public void AddPlacewithFilePayload() throws IOException {
		

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String(java.nio.file.Files.readAllBytes(Paths.get("F:\\software testing training\\API Testing\\Udemy API Testing\\SendingFileAsPayload.JSON")))
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		JsonPath json=new JsonPath(response);
		String placeId = json.getString("place_id");
		
		System.out.println(placeId);
	}

}
