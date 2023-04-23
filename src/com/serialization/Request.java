package com.serialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import com.pojo.Response;

public class Request {
	
	// Script for serialization & Deserialization
//	serialization in terms of rest assured is converting the Java Object to Json request payload.
//	Deserialization is the process of converting the json response to Java Object.

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

		RestAssured.baseURI="https://rahulshettyacademy.com";
		com.serialization.Response rbody=given().queryParam("key", "qaclick123").header("Content_Type","application/json").
		body(rb).when().post("maps/api/place/add/json").as(com.serialization.Response.class); 
		
		System.out.println(rbody);
		System.out.println(rbody.getId());
		System.out.println(rbody.getPlace_id());
		System.out.println(rbody.getReference());
		System.out.println(rbody.getScope());
		System.out.println(rbody.getStatus());
		
	}
	

}
