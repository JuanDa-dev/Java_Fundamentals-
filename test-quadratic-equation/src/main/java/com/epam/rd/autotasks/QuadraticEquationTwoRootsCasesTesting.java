package com.epam.rd.autotasks;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;
    private String expected;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = String.valueOf(expected);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {1, -5, 6, "2.0 3.0"}, {2, -3, -2, "-0.5 2.0"}, {3, -7, 2, "0.3333333333333333 2.0"}, 
            {1, -4, -5, "-1.0 5.0"}, {4, -8, 3, "0.5 1.5"}, {1, -9, 20, "4.0 5.0"}
        });
    }

    @Test
    public void testTwoRootsCase() {
        String[] roots = expected.split(" ");
        String anotherExpectedForm = roots[1] + " " + roots[0];
        String solution = quadraticEquation.solve(a, b, c);
        assertTrue(expected.compareTo(solution) == 0 || anotherExpectedForm.compareTo(solution) == 0);
    }

}


