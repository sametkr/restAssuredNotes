package com.cydeo.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1() {
        //MatcherAssert.assertThat(5+5, Matchers.is(10));
        /** matchers has 2 overloaded version
        first that accept actual value
        second that accept another matchers
        below examples is method is accepting another matchers equal to make it readable**/
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, is(equalTo(10)));

        /** number comparison
        greaterThan()
        greaterThanOrEqualTo()
        lessThan()
        lessThanOrEqualTo() **/
        assertThat(5+5, is(greaterThan(9)));
    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){
        String text = "EU8 is learning Hamcrest";

        //checking for equality is same as numbers
        assertThat(text, is("EU8 is learning Hamcrest"));
        assertThat(text, equalTo("EU8 is learning Hamcrest"));
        assertThat(text, is(equalTo("EU8 is learning Hamcrest")));

        //check if this text starts with EU8
        assertThat(text, startsWith("EU8"));
        //now do it in case-insensitive manner
        assertThat(text, startsWithIgnoringCase("eu8"));
        //ends-with
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEARNING"));


        String str = "   ";
        //check if above str is blank
        assertThat(str, blankString());
        //check if the trimmed str is empty string
        assertThat(str.trim(), is(emptyString()));
    }

    @DisplayName("Hamcrest for collection")
    @Test
    public void testCollection(){
        List<Integer> listOfInteger = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfInteger, hasSize(10));
        //check if this list hasItem 77
        assertThat(listOfInteger, hasItem(77));
        //check if this list hasItems 77, 54, 23
        assertThat(listOfInteger, hasItems(77,54,23));

        //check if all numbers greater than 0
        assertThat(listOfInteger, everyItem(greaterThan(0)));
    }
}
