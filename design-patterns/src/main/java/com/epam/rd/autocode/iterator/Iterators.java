package com.epam.rd.autocode.iterator;

import java.util.*;

public class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 2;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 2];
                index++;
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 3;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 3];
                index++;
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 5;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 5];
                index++;
                return value;
            }
        };
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return () -> new Iterator<String>() {
            private int colIndex = 0;
            private int rowIndex = 0;
    
            @Override
            public boolean hasNext() {
                return colIndex < columns.length && rowIndex < rows.length;
            }
    
            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String value = columns[colIndex] + rows[rowIndex];
                rowIndex++;
                if (rowIndex == rows.length) {
                    rowIndex = 0;
                    colIndex++;
                }
                return value;
            }
        };
    }
}
