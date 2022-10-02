package com.cydeo.day12;

import com.cydeo.utilities.BookitTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class BookitSpecTest extends BookitTestBase {

    @Test
    public void test1(){
        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type
        given().spec(userReqSpec("teacher"))
                .when().get("/api/users/me")
                .then().spec(getDynamicResSpec(200))
                .spec(userCheck("Wilton", "Amiss"));
    }

    @Test
    public void test2(){
        //send a get request to /api/users/me endpoint as a student-member
        //verify status code and content type
        given().spec(studentMemberReqSpec)
                .when().get("/api/users/me")
                .then().spec(responseSpec);
    }
}
