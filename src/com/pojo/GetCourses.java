package com.pojo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;

public class GetCourses {
	
	
	public static void main(String[] args) {
		
		BodyElements response=given().queryParam("access_token", "ya29.a0Ael9sCNiAiIeJP7-JLaAt8M1Ua8qQgmC1ruaRKVtr_0F_XTP1VyY1l5EmPYpDBUpTN8pYfdfNvq0j2epui-H4a4liJXy-d3CbflfcXcK4MDhSkhzirAbVnIwci7KfzThljA7FrfOFwriHlaZkBm2XWPAk8pX6QaCgYKAQcSARASFQF4udJhXvh0M7UtEXTkDimM4E0sPg0165").expect().defaultParser(Parser.JSON).
		when().
		get("https://rahulshettyacademy.com/getCourse.php").as(BodyElements.class);
		
		System.out.println("Welcome Line Added");
		System.out.println("New Functionality added");
		
//		reading the response of the elements
		System.out.println(response.getInstructor());
		System.out.println(response.getExpertise());
		System.out.println(response.getLinkedIn());
//		Reading the response a nested json by indexing
		System.out.println(response.getCourses().getApi().get(1).getCourseTitle());
		
//		Fetching the price whose coursetitle is soap ui webservice testing in API course without indexing and can be changed dynamically
		List<Api> api=response.getCourses().getApi();
		for(int i=0;i<api.size();i++) {
			if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(api.get(i).getPrice());;
				break;
			}
		}
		
//		Fetching prices for all the  web automation courses and validating the response.
		String[] expectedcourses= {"Selenium Webdriver Java","Cypress","Protractor"};
		ArrayList<String> actualcourses=new ArrayList<String>();
		
		List<WebAutomation> webcourses=response.getCourses().getWebAutomation();
		for(int j=0;j<webcourses.size();j++) {
			
			System.out.println(webcourses.get(j).getCourseTitle());
			actualcourses.add(webcourses.get(j).getCourseTitle());
		}
		
//		Converting Array to ArrayList as we can compare array and arraylist
		List<String> expectedList=Arrays.asList(expectedcourses);

		Assert.assertTrue(actualcourses.equals(expectedList));
		
		
	}
	

}
