package Requests;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		 
		JsonPath js=new JsonPath(payload.CoursesPrice());
		
		//Print No of courses returned by API
		
		int count=js.getInt("courses.size()");
		
		System.out.println(count);
		
		//Print purchase amount
		
		int TotalAmount = js.getInt("dashboard.purchaseAmount");
		
		System.out.println(TotalAmount);
		
		//Get title of first course
		
		String Firsttitle = js.getString("courses[0].title");
		
		System.out.println(Firsttitle);
		
		//Print All courses titles and their respective prices
		
		for(int i=0;i<count;i++) {
			System.out.println(js.get("courses["+i+"].title"));
			System.out.println(js.get("courses["+i+"].price"));
			System.out.println(js.get("courses["+i+"].copies"));
		}
		
		
		
	}

}
