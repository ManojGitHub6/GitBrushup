package com.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import com.files.Payload;
import io.restassured.RestAssured;

public class AddPlace {

	public static void main(String[] args) {
//		Validate ADD Place API is working or not
		
//		Given - All input details - query param, path param, header, body.
//		When - Submit API - resource & Http method
//		Then - Validate Response status code,body,header.
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace1()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).
		header("Server", "Apache/2.4.41 (Ubuntu)");
		
//		ADD place --> Update Place with New Address --> Get Place to validate if new address is updated

	}

}
