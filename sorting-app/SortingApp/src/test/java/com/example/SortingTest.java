package com.example;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import org.junit.runners.Parameterized.Parameters;

import com.example.Sorting;

@RunWith(Parameterized.class)
public class SortingTest {

    private int value;
    private int expected;

    public SortingTest(int value, int expected) {
        this.value = value;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] values = array.clone();
        int[] expectedValues = array.clone();

        Sorting.sort(values);
        Arrays.sort(expectedValues);

        Object[][] matriz = new Object[array.length][2];
        for(int i = 0; i < array.length; i++) {
            matriz[i][0] = values[i];
            matriz[i][1] = expectedValues[i];
        }

        return Arrays.asList(matriz);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullCase() {
        Sorting.sort(null);
    }
    
    @Test
    public void testGeneralCase() {
        assertEquals(value, expected);
    }

    @Test
    public void testOtherCases() {
        // Test with an empty array
        int[] empty = {};
        Sorting.sort(empty);  // Should not throw an exception

        // Test with a single-element array
        int[] singleElement = {1};
        Sorting.sort(singleElement);  // Should not throw an exception
        assertEquals(singleElement[0], 1);
    }
}
