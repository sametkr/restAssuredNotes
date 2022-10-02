package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.client.HttpResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init() {
        //save baseURL inside this variable so that we don't need to type each http method.
        RestAssured.baseURI = "http://52.90.77.197:8000";
    }

    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1(){
    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");

        //verify status code is 406
        assertEquals(406, response.statusCode());
        //verify content type
        assertEquals("application/xml;charset=UTF-8", response.contentType());
    }
}
