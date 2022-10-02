package com.cydeo.utilities;

import static io.restassured.RestAssured.*;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class BookitTestBase {

    public static RequestSpecification teacherReqSpec;
    public static RequestSpecification studentMemberReqSpec;
    public static RequestSpecification studentLeaderReqSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("qa2_api_url");

        teacherReqSpec = given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole("teacher"))
                .log().all();

        studentMemberReqSpec = given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole("student-member"))
                .log().all();

        studentLeaderReqSpec = given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole("student-leader"))
                .log().all();

        responseSpec = expect().statusCode(200)
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }

    public static ResponseSpecification getDynamicResSpec(int statusCode){
        return expect().statusCode(statusCode)
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }

    public static ResponseSpecification userCheck(String firstName, String lastName){
        return expect().statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", Matchers.is(firstName))
                .body("lastName", Matchers.is(lastName));
    }

    //teacher, student-member, student-leader
    public static RequestSpecification userReqSpec(String role){

       return given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole(role))
                .log().all();
    }


    public static String getTokenByRole(String role) {
        String email, pass;

        switch (role) {
            case "teacher":
                email = ConfigurationReader.getProperty("teacher_email");
                pass = ConfigurationReader.getProperty("teacher_password");
                break;
            case "student-member":
                email = ConfigurationReader.getProperty("team_member_email");
                pass = ConfigurationReader.getProperty("team_member_password");
                break;
            case "student-leader":
                email = ConfigurationReader.getProperty("team_leader_email");
                pass = ConfigurationReader.getProperty("team_leader_password");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }

        String accessToken = given()
                .accept(ContentType.JSON)
                .queryParams("email", email, "password", pass)
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        return "Bearer " + accessToken;
    }
}
