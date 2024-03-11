package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {0, 0, 1}, {0, 4, 5}, {0, -6, 10}, 
            {0, 2, 3}, {0, 4, 1}, {0, -2, 2}
        });
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNoRootsCase() {
        quadraticEquation.solve(a, b, c);
    }

}

