package Requests;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class AddPlaceRequest {
	public static void main(String[] args) {
		
	
	//validate if Add Place API is working as expected
	//given-all input details
	//When-submit the API
	//Then -validate the response
	
	
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath json=new JsonPath(response);
		String placeId = json.getString("place_id");
		
		System.out.println(placeId);
		
	
	//Update Place
		
		String newaddress="70 Summer walk, USA";
	
		
		given().log().all().queryParam("key","qaclick123")
		.header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
	//Get Place
		String res = given().log().all()
		.queryParam("key","qaclick123").queryParam("place_id",placeId)
		.when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath jsn=new JsonPath(res);
		String actualAddress = jsn.getString("address");
		System.out.println(actualAddress);
		

		

		
	

}
}
