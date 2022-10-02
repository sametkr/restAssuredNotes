package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONtoJavaTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap() {
        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map // we are able to do it with dependency: jackson or gson
        Map<String, Object> jsonMap = response.as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        //after we got the map, we can use hamcrest or junit to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("Meta"));
    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){
        Response response = get("/api/spartans")
                .then().statusCode(200)
                .extract().response();

        List<Map<String, Object>> allSpartans = response.as(List.class);

        System.out.println(allSpartans.get(1).get("name"));

        Map<String, Object> spartan3 = allSpartans.get(2);
        System.out.println(spartan3);
    }
}
