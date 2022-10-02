package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        //save baseURL inside this variable so that we don't need to type each http method.
        baseURI = "http://52.90.77.197:8000";

        String dbUrl = "jdbc:oracle:thin:@52.90.77.197:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
        //DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterAll
    public static void tearDown(){
        //DBUtils.destroy();
    }
}
