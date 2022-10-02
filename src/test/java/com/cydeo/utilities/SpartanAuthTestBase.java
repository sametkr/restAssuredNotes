package com.cydeo.utilities;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init(){
        baseURI = "http://52.90.77.197:7000/";
    }
}
