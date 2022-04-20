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

	JsonPath path;
	String userToken = "";
	String ownerId = "";

	@Before
	public void before() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}

	@Test
	public void verifyRegistration() {
		String email = "sim65@gmail.com";
		String password = "password1";

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("email", email);
		jsonObject.put("password", password);

		request.body(jsonObject);

		Response response = request.post("/register");

		Assert.assertEquals(200, response.statusCode());

		JsonPath path = response.jsonPath();

		String respEmail = path.getString("email");
		String respPassword = path.getString("password");

		Assert.assertEquals(200, response.statusCode());

	}

	@Test
	public void verifyUserCanLogin() {

		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("login", "sim65@gmail.com");
		jsonObject.put("password", "password1");
		request.body(jsonObject);
		Response response = request.post("/login");
		JsonPath path = response.jsonPath();
		Assert.assertEquals(200, response.statusCode());

		userToken = path.getString("user-token");

		path.getString("objectId");

	}

	@Test
	public void verifyUserUpdated() {

		RequestSpecification request = RestAssured.given();
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("user-token", ownerId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", "sim44@gmail.com");
		jsonObject.put("password", "password1");
		request.body(jsonObject);
		response = request.put(ownerId);

	}
}
