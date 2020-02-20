package com.samirovic.business;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class HamcrestTest {

    @Test
    public void test() {
        List<Integer> scores = Arrays.asList(120, 113, 94);

        assertThat(scores, hasSize(3));
        assertThat(scores, hasItem(120));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(190)));

        // String
        assertThat("", isEmptyString());
        assertThat(null, isEmptyOrNullString());

        // Array
        Integer[] marks = {12, 5, 70};
        assertThat(marks, arrayWithSize(3));
        assertThat(marks, arrayContainingInAnyOrder(5, 12, 70));
    }
}
