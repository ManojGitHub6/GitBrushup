package com.testing;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexPrice {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.coursePrice());
//		No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
//		Fetching Purchase Amount
		int p_amount=js.getInt("dashboard.purchaseAmount");
		System.out.println(p_amount);
		
//		Fetching FirstCourse Title
		String firstCourseTitle=js.get("courses[0].title");
		System.out.println(firstCourseTitle);
		
//		Print all courses titles and prices
		for(int i=0;i<count;i++) {
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.get("courses["+i+"].price").toString());
		
		}
		
//		no of copies sold by RPA Course
		
		for(int i=0;i<count;i++) {
			String title=js.get("courses["+i+"].title");
			if(title.equalsIgnoreCase("Selenium Python")) {
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
		}
		
//		Verify if Sum of all Course prices matches with Purchase Amount
		
		int sum=0;
		for (int j=0;j<count;j++) {
		int price=js.getInt("courses["+j+"].price");
		int copies=js.getInt("courses["+j+"].copies");
		
		sum=price*copies+sum;
		
		}
		System.out.println(sum);
		
		if(sum==p_amount) {
			System.out.println("sum is equal to p_amount");
		}
		else
			System.out.println("sum is not equal to p_amount");
	}

}
