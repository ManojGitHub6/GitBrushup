package com.serialization;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import com.pojo.Response;

public class SpecBuilder {
	
	// Script for serialization & Deserialization

	public static void main(String[] args) {
		
		RequestBody rb=new RequestBody();
		rb.setAccuracy(40);
		rb.setName("Manoj XX QA Store");
		rb.setPhone_number("(+91) 9832235542");
		rb.setAddress("29, side layout, cohen 09");
		List<String> mylist =new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		rb.setTypes(mylist);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		rb.setLocation(loc);
		
		rb.setWebsite("http://google.com");
		rb.setLanguage("French-IN");
		
//		To set all the inputs such as baseuri,queryparam, headers so that it can be reused for other requests
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
		addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

//		To set all the expected response specification like status code, content-type etc.,
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification request=given().spec(req).
		body(rb);
		
		com.serialization.Response rbody=request.when().post("maps/api/place/add/json").
		then().spec(response).extract().response().as(com.serialization.Response.class); 
		
		System.out.println(rbody);
		System.out.println(rbody.getId());
		System.out.println(rbody.getPlace_id());
		System.out.println(rbody.getReference());
		System.out.println(rbody.getScope());
		System.out.println(rbody.getStatus());
		
	}
	

}
