package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//static import implementation
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRGetRequests {

    //BeforeAll is annotation equals to @BeforeClass in testNG, we use static method name
    @BeforeAll
    public static void init(){
        //save baseURL inside this variable so that we don't need to type each http method.
        RestAssured.baseURI = "http://52.90.77.197:1000/ords/hr";
    }

    @DisplayName("Get request to /regions")
    @Test
    public void test1(){
        Response response = get("/regions");

        System.out.println(response.statusCode());

    }

    @Test
    public void test2(){
        //Given accept type is application/json
        //When user sends get request to /regions/2
        //Then response status code must be 200
        //And content type equals to application/json
        //And response body contains Americas

        Response response = given().accept(ContentType.JSON).when().get("/regions/2");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Americas"));
    }
}
