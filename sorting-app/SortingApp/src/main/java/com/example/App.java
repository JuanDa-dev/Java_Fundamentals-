package com.example;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        int[] numbers = new int[args.length]; 

        for (int i = 0; i < args.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);
        }

        Sorting.sort(numbers);

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

        System.out.println();
    }
}
