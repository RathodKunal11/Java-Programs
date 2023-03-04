package Requests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;


public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json").body(payload.Addbook(isbn,aisle))
		.when()
		.post("Library/Addbook.php").then()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String id = js.get("ID");
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//Array-->Collection of element
		//Multidimensional Array-->Collection of array
		
		return new Object[][] {{"fdfjb","785"},{"vdhjd","554"},{"fyueh","425"}};
	}
	
	
	
	
	
	
	
	
	

}
