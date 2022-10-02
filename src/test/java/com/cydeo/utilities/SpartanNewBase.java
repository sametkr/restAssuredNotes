package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    public static RequestSpecification requestSpec;
    public static RequestSpecification userSpec;
    public static RequestSpecification adminSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init() {
        baseURI = "http://52.90.77.197";
        port = 7000;
        basePath = "/api";

        requestSpec = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .log().all();

        userSpec = given().accept(ContentType.JSON)
                .and().auth().basic("user", "user")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and().contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }

    @AfterAll
    public static void close() {
        //reset the info we set above, comes from RestAssured
        reset();
    }
}
