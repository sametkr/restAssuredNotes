package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one Spartan for update with Map")
    @Test
    public void PUTRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "BruceWayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 9005555111L);

        given().contentType(ContentType.JSON) //sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 112)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after update, make sure updated field changed, or the new info matching with requestBody that we send
        Spartan spartan = given().accept(ContentType.JSON)
                .and().pathParam("id", 112)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().as(Spartan.class);

        assertThat(putRequestMap.get("phone"), is(spartan.getPhone()));
    }

    @DisplayName("PATCH request to one Spartan for update with Map")
    @Test
    public void PATCHRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 9005222111L);

        given().contentType(ContentType.JSON) //sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 112)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after update, make sure updated field changed, or the new info matching with requestBody that we send
        Spartan spartan = given().accept(ContentType.JSON)
                .and().pathParam("id", 112)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().as(Spartan.class);

        assertThat(putRequestMap.get("phone"), is(spartan.getPhone()));
    }

    @DisplayName("Delete one Spartan")
    @Test
    public void deleteSpartan(){
        int idToDelete = 114;

        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 404
        given().accept(ContentType.JSON)
                .and().pathParam("id", idToDelete)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);
    }



}
