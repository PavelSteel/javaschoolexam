package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;
import java.util.stream.Collectors;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        boolean mark = false;
        int[][] arr;
        int size = inputNumbers.size();
        int count = 0;
        int rows = 1;
        int columns = 1;

        while (count < size) {
            count += rows;
            rows++;
            columns += 2;
        }
        rows = rows - 1;
        columns = columns - 2;

        if (size == count) {
            mark = true;
        }
        try {
            if (mark) {
                List<Integer> sorted = inputNumbers.stream().sorted().collect(Collectors.toList());

                arr = new int[rows][columns];

                int center = columns / 2;
                int sInd = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j <= i; j++) {
                        arr[i][center - i + j * 2] = sorted.get(sInd++);
                    }
                }

                for (int[] a : arr)
                {
                    for (int b : a)
                        System.out.print(b + "   ");
                    System.out.println();
                }
            }else throw new CannotBuildPyramidException();

        } catch (Exception e) {
            throw new CannotBuildPyramidException();
        }

        return arr;
    }


}
