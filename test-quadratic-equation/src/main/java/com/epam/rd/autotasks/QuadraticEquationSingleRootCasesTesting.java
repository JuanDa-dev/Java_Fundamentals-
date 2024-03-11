package com.epam.rd.autotasks;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;
    private String expected;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = String.valueOf(expected);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {1, -4, 4, 2.0}, {2, -4, 2, 1.0}, {3, -6, 3, 1.0}, 
            {1, -2, 1, 1.0}, {4, -8, 4, 1.0}, {1, -6, 9, 3.0}
        });
    }

    @Test
    public void testSingleRootCase() {
        assertEquals(expected, quadraticEquation.solve(a, b, c));
    }

}
