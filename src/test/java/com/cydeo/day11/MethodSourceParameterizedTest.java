package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MethodSourceParameterizedTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name) {
        System.out.println("name = " + name);
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParamTest(Map<String, String> userInfo){
        System.out.println(userInfo);
    }


    public static List<String> getNames() {
        //you can get value from anywhere almost anytype and return to your test
        //DB, Excel, APIs
        List<String> nameList = Arrays.asList("John", "Doe", "Jason", "Jackson", "Cucumber");

        return nameList;
    }

    public static List<Map<String, String>> getExcelData() {
        ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");

        return vyTrackFile.getDataList();
    }

}
