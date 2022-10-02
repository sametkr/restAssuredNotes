package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){
        Response response = get("/countries");

        //get the second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();

        //to use jsonpath we assign response to JsonPath
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> countryIDs = jsonPath.getList("items.country_id");
        System.out.println("countryIDs = " + countryIDs);

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
                                                                 //it means like each, there is if condition between curly braces
        System.out.println(countryNameWithRegionId2);
    }

    @DisplayName("GET request /employees with query param")
    @Test
    public void test2(){
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();

        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> employeeMoreThan10000 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeeMoreThan1000 = " + employeeMoreThan10000);

        //get the max salary first_name
        String maxSalaryFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String maxSalaryFirstName2 = response.path("items.max {it.salary}.first_name");
        System.out.println("maxSalaryFirstName = " + maxSalaryFirstName);
        System.out.println("maxSalaryFirstName2 = " + maxSalaryFirstName2);
    }
}
