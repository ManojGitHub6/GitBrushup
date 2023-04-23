package com.LibraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="PlaceData")
	public void addBook(String name, String Address)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace(name,Address)).when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= new JsonPath(response);
		String id =js.get("id");
		System.out.println(id);
	}
	
	@DataProvider(name="PlaceData")
	public Object[][] getData() {
//		array is collection of elements
//		multi-dimensional array is collection of arrays
		return new Object[][] {{"Manoj QA Office","#2-23,Hitech city, Hyd"},
			{"Raju QA Store","#99/22-1,Bangle Street,New Delhi"},
			{"Nick Walter QA Hub","#12-11/1,East Road,Chennai"}};
	}

}
