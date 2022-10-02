package com.cydeo.utilities;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanUtils {

    //Create one SpartanUtil class
    //create a static method that returns Map<String,Object>
    //use faker library(add as a depedency) to assign each time different information
    //for name,gender,phone number
    //then use your method for creating spartan as a map,dynamically.

    public static Map<String, Object> creatingSpartan() {

        Map<String, Object> creatingSpartan = new LinkedHashMap<>();
        Faker faker = new Faker();

        creatingSpartan.put("name", faker.name().firstName());
        creatingSpartan.put("gender", faker.demographic().sex());
        creatingSpartan.put("phone", faker.numerify("##########"));

        return creatingSpartan;
    }


}
