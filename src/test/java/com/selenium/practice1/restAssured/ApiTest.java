package com.selenium.practice1.restAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class ApiTest {
	Response response;

	String applicationId = "10B6B2A1-F3A1-7CBD-FFA6-8EC43F365300";
	String restApiKey = "0A8394C7-45FD-4427-8782-73FCC75954BC";
	String CloudAPIKey = "5 AF5AAFA-FA9C-4710-9549-E791C534CA4F";

	String userToken = "";
	String ownerId = "";

	@Before
	public void before() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}

	// CHANGE EMAIL BEFORE running test IN VERIFY REGISTRATION TEST AND LOGIN TEST
	@Test
	public void verifyRegistration() {
		String email = "s14@gmail.com";
		String password = "password1";

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("email", email);
		jsonObject.put("password", password);

		request.body(jsonObject);

		Response response = request.post("/register");
		JsonPath jsonPath = response.jsonPath();

		Assert.assertEquals(200, response.statusCode());

		String getEmail = jsonPath.getString("email");
		String getpassword = jsonPath.getString("password");

		Assert.assertEquals(200, response.statusCode());
		System.out.println("User successfully registered :" + email + "   " + password);

	}

	@Test
	public void verifyUserCanLogin() {

		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("login", "s14@gmail.com");
		jsonObject.put("password", "password1");
		request.body(jsonObject);
		Response response = request.post("/login");
		JsonPath jpath = response.jsonPath();
		Assert.assertEquals(200, response.statusCode());
		userToken = jpath.getString("user-token");
		ownerId = jpath.getString("ownerId");
		System.out.println("User token and id is : " + userToken + " & " + ownerId);
	}

	@Test
	public void verifyUserUpdated() {

		RequestSpecification request = RestAssured.given();
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("user-token", ownerId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", "sim44@gmail.com");
		jsonObject.put("password", "password2");

		request.body(jsonObject);
		response = request.put(ownerId);

	}

}
