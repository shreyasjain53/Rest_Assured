package com.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //given()
import static org.hamcrest.Matchers.*;  //equalTo()

import files.PayLoad;

public class FirstClass {

	public static void main(String[] args) {

		/*
		 * Rest asssured works on 3 pronciples 1. given() - we will append with all
		 * input body 2. when() - We will specify the http request type 3. then() -
		 * validate the response code and body
		 */

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// To log in console we need to use .log().all()

		//Add a place
	String response = 	given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayLoad.addPlace())
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
	// Json path with take argument as string 
	JsonPath js = new JsonPath(response); 
	
	//It will extract the value of key called "place_id" from Json response
	String place_id = js.getString("place_id");
	
	System.out.println(place_id);
	
	}
	
	
	
}
