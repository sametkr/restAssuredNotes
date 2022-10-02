package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingWithJsonPath {

    @BeforeAll
    public static void init() {
        //save baseURL inside this variable so that we don't need to type each http method.
        baseURI = "http://api.cybertektraining.com";
    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 32881 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */

        Response response= given().accept(ContentType.JSON)
                .pathParam("id",32881)
                .when().get("/students/{id}");

        JsonPath jsonPath = response.jsonPath();
        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip", response.header("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals("Vera", jsonPath.getString("firstName"));
        assertEquals(14, jsonPath.getInt("batch"));
        assertEquals(12, jsonPath.getInt("section"));
        assertEquals("aaa@gmail.com", jsonPath.getString("contact.emailAddress"));
        assertEquals("Cybertek", jsonPath.getString("company.companyName"));
        assertEquals("IL", jsonPath.getString("company.address.state"));
        assertEquals(60606, jsonPath.getInt("company.address.zipCode"));





    }
}
