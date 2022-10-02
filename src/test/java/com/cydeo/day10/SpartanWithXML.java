package com.cydeo.day10;

import com.cydeo.day8.SpartanWithAuthTests;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithXML extends SpartanWithAuthTests {

    @DisplayName("GET request to /api/students and verify xml")
    @Test
    public void getSpartanXml() {
        //we will ask for xml response
        //assert status code 200
        //assert content type is xml
        //verify first spartan is Meade

        given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType("application/xml;charset=UTF-8") //we verify we go xml through header
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"))
                .log().all();
    }

    @DisplayName("GET request /api/students with xmlPath")
    @Test
    public void testXmlPath(){

        Response response = given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans");

        //get response xml body/payload and save inside the xmlPath object
        XmlPath xmlPath = response.xmlPath();
        String name = xmlPath.getString("List.item[0].name");
        System.out.println(name);

        //get me the third spartan id's
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println(id);

        //how to get all names and save into list of string
        List<String> names = xmlPath.getList("List.item.name");
        System.out.println(names);
    }
}
