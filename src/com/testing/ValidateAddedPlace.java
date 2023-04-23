package com.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import com.files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ValidateAddedPlace {

	public static void main(String[] args) {

//		Validate ADD Place API is working or not
		
//		Given - All input details - query param, path param, header, body.
//		When - Submit API - resource & Http method
//		Then - Validate Response status code,body,header.

//		ADD place --> Update Place with New Address --> Get Place to validate if new address is updated
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace1()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).
		header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response); // to parse json 
		String placeId = js.getString("place_id");
		System.out.println(placeId);
	
//		Update Place
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"70 Summer walk, Madhapur\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("maps/api/place/update/json").
		then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
//		Get Place
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").
		queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		
		
		
		
	}

}
