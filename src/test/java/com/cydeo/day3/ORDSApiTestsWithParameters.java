package com.cydeo.day3;

//static import'

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init() {
        //save baseURL inside this variable so that we don't need to type each http method.
        baseURI = "http://52.90.77.197:1000/ords/hr";
    }

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1() {
        /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
        */

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        //System.out.println(response.getHeader("Content-Type"));  extra if we don't have content type method
        assertTrue(response.body().asString().contains("United States of America"));
    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        //Send a GET request to employees and get only employees who works as a IT_PROG

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));
    }

}
