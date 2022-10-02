package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){
        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when().get("api/spartans")
                //.then().time(lessThanOrEqualTo(1500L))
                .then().time(both(greaterThan(500L)).and(lessThanOrEqualTo(1500L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());
    }
}
