package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\": 2}")
                .when().get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country id
        System.out.println("response.path(\"items[0].id\") = " + response.path("items[0].country_id"));

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print "http://52.90.77.197:1000/ords/hr/countries/CA"
        String href = response.path("items[2].links[0].href");
        System.out.println("href = " + href);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionIDs = response.path("items.region_id");
        for (Integer eachRegionID : allRegionIDs) {
            assertEquals(2, eachRegionID);
        }
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
        //make sure we have only IT_PROG as a job id
        List<String> allJobIDs = response.path("items.job_id");
        for (String eachJobID : allJobIDs) {
            assertEquals("IT_PROG", eachJobID);
        }
    }
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test3() {
        //Send a GET request to employees and get only employees who works as a IT_PROG

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //print each name of IT_PROGs
        List<String> allITNames = response.path("items.first_name");

        for (String eachName : allITNames) {
            System.out.println(eachName);
        }



    }
}
