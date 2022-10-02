package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanSpecTest extends SpartanNewBase {
    //all tests in this class will use admin credentials
    //all test in this class will expect json result from response
    //all test in this class response status code is 200
    //all test in this class response content type header is json

    @Test
    public void test1(){
        given().spec(requestSpec)
                .when().get("/spartans")
                .then().spec(responseSpec);
    }

    @Test
    public void test2(){
        given().spec(requestSpec)
                .pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec);
    }

    @Test
    public void test3(){
        given().spec(requestSpec)
                .and().queryParams("nameContains","j",
                        "gender", "female")
                .when().get("/spartans/search")
                .then().spec(responseSpec)
                .body("numberOfElements", is(6));
    }

}
