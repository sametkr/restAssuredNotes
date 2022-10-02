package com.cydeo.day11;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ParametrizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 6, 7, 9, 10, 5})
    public void testMultipleNumber(int number) {
        System.out.println(number);
        Assertions.assertTrue(number > 5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"john", "jackson", "bob", "TJ"})
    public void testMultipleNames(String name) {
        System.out.println("name = " + name);
    }


    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036})
    public void sendingZipCodes(int zipCode) {
        given().baseUri("https://api.zippopotam.us")
                .pathParam("zipcode", zipCode)
                .when().get("/us/{zipcode}")
                .then().statusCode(200);
    }

}
