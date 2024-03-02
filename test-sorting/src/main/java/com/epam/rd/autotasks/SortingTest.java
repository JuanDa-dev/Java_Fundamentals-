package com.epam.rd.autotasks;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class SortingTest {

    Sorting sorting = new Sorting();

    @Test (expected = IllegalArgumentException.class)
    public void testNullCase(){
        int[] array = null;
        sorting.sort(array);
    }

    @Test
    public void testEmptyCase(){
        int[] array = {};
        sorting.sort(array);
        assertEquals(0, array.length);
    }

    @Test
    public void testSingleElementArrayCase() {
        int[] array = {5};
        int pos0 = array[0];
        sorting.sort(array);
        assertEquals(1, array.length);
        assertEquals(pos0, array[0]);
    }

    @Test
    public void testSortedArraysCase() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array2 = array.clone();
        sorting.sort(array);
        assertArrayEquals(array2, array);
    }

    @Test
    public void testOtherCases() {
        int[] array = {4, 5, 8, 1, 2, 3, 9, 7, 6, 0};
        int[] expected = array.clone();
        sorting.sort(array);
        Arrays.sort(expected);
        assertArrayEquals(array, expected);
    }

}
