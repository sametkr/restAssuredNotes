package com.cydeo.day3;

//static import'

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath {

    @BeforeAll
    public static void init() {
        //save baseURL inside this variable so that we don't need to type each http method.
        baseURI = "http://52.90.77.197:8000";
    }

    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1() {
      /*Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936*/

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //verify each json key has specific value
        //or we can store each value as a variable
        assertEquals(10, (Integer) response.path("id"));
        assertEquals("Lorenza", response.path("name"));
        assertEquals("Female", response.path("gender"));
        assertEquals(3312820936l, (Long) response.path("phone"));
    }

    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("api/spartans");

        //response.prettyPrint();

        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        String name = response.path("name[0]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        //save names inside the list of string
        List<String> allSpartans = response.path("name");
        //print each name one by one
        for (String eachSpartan : allSpartans) {
            System.out.println(eachSpartan);
        }
    }
}
