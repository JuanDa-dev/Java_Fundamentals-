package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if (a == 0) throw new IllegalArgumentException();
        String x1 = String.valueOf((-b - Math.sqrt(Math.pow(b, 2) - 4*a*c)) / (2*a));
        String x2 = String.valueOf((-b + Math.sqrt(Math.pow(b, 2) - 4*a*c)) / (2*a));

        return x1.compareTo(x2) != 0 && x1.compareTo("NaN") != 0 && x2.compareTo("NaN") != 0
            ? x1 + " " + x2 
            : x1.compareTo(x2) == 0 && x1.compareTo("NaN") == 0
                ? "no roots" 
                : x2.compareTo("NaN") == 0 
                    ? x1 
                    : x2;
    }

}
