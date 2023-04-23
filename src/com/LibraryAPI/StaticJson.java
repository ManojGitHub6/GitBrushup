package com.LibraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.files.Payload;

public class StaticJson {

//	Using Json file to input into body in request.
//	To read a json file first we need to read content of the file to string i.e., convert content of file into byte 
//	Byte data to string 
	
	@Test
	public void addPlace() throws IOException {
			
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("E:\\API Testing\\Automation API Testing\\AddPlace.json")))).when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
				
		JsonPath js = new JsonPath(response);
		String name=js.get("name");
		System.out.println(name);
	}
}
