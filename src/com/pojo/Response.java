package com.pojo;

import static io.restassured.RestAssured.*;

import io.restassured.parsing.Parser;

public class Response {

	public static void main(String[] args) {
		
		GetCourse gc= new GetCourse();
		
		
		GetCourse gcr=given().queryParam("key","qaclick123").queryParam("place_id","9fe1bdb818a1e596b85efa8496316424")
		.expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/maps/api/place/get/json").as(GetCourse.class);
		
		System.out.println(gcr.getTypes());
		System.out.println(gcr.getLocation().getLatitude());
		System.out.println(gcr.getLocation().getLongitude());

	}

}
