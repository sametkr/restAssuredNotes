package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBaseAccessControl extends SpartanAuthTestBase {

    @DisplayName("RBAC")
    @Test
    public void spartanRBAC(){
        //Admin should be able to take all CRUD (create, read, update, delete)
        //admin post
        Map<String, Object> spartan = SpartanUtils.creatingSpartan();
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().body(spartan)
                .when().post("/api/spartans")
                .then().statusCode(201);

        //admin get
        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("api/spartans")
                .then().statusCode(200);

        //admin patch
        Map<String, Object> patchValues = new LinkedHashMap<>();
        patchValues.put("gender", "Female");
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().body(patchValues)
                .and().pathParam("id", 90)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

        //admin delete
        int idToDelete = 123;
        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //Editor should be able to take all CRUD other than DELETE
        //Editor post
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .and().auth().basic("editor", "editor")
                .and().body(spartan)
                .when().post("/api/spartans")
                .then().statusCode(201);

        //Editor get
        given().accept(ContentType.JSON)
                .and().auth().basic("editor", "editor")
                .when().get("api/spartans")
                .then().statusCode(200);

        //Editor patch
        patchValues.put("gender", "Female");
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .and().auth().basic("editor", "editor")
                .and().body(patchValues)
                .and().pathParam("id", 90)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

        //Editor delete
        given().accept(ContentType.JSON)
                .and().auth().basic("editor", "editor")
                .and().pathParam("id", 112)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(403);


        //User should be able to only READ data not update,delete,create (POST,PUT,PATCH,DELETE)

    }

}

/*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able to take all CRUD (create, read, update, delete)
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all
     */