package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {

    @Test
    public void test1(){
        //How to use excelUtil file ?
        //it accepts two arguments
        //Argument 1: location of the file(path)
        //Argument 2: sheet that we want to open
        ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");

        //method for returning list of map
        List<Map<String, String>> dataList = vyTrackFile.getDataList();

        for (Map<String, String> rowmap : dataList) {
            System.out.println(rowmap);
        }
    }
}
