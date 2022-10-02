package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseUrl = "http://52.90.77.197:8000";


    @Test
    public void test1() {
        //Given Accept type application/json
        //When user send GET request to api/spartans end point
        //Then status code must 200
        //And response Content Type must be application/json
        //And response body should include spartan result

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type for response object
        System.out.println("response.contentType() = " + response.contentType());

        //printing whole result body
        response.prettyPrint();

        //how to do API testing them?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");
    }

    @DisplayName("Get one spartan /api/spartans/3 and verify")
    @Test
    public void test2() {
        //Given accept is application/json
        //When user sends a get request to /api/spartans/3
        //Then status code should be 200
        //And content type should be application/json
        //And json body should contain Fidole

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }


    @Test
    public void test3() {
        //Given no headers provided
        //When Users sends GET request to /api/hello
        //Then response status code should be 200
        //And Content type header should be "text/plain;charset=UTF-8"
        //And header should contain date
        //And Content-Length should be 17
        //And body should be "Hello from Sparta"

        Response response = RestAssured.when().get(baseUrl + "/api/hello");
        //verify status code 200
        Assertions.assertEquals(200, response.statusCode());
        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());
        //verify we have headers named date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        //verify Content-Length should be 17
        Assertions.assertEquals("17", response.header("Content-Length"));
        //verify body should be "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }

}
