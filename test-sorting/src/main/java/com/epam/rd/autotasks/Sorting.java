package com.epam.rd.autotasks;

public class Sorting {
    public void sort(int[] array) {
        if (array == null) throw new IllegalArgumentException();
        if (array.length <= 1) return;

        int temp = 0;
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
