/* Homework 4
 * Name: Le Thi Diem My
 * Student ID: 220050
 */
//package com.gradescope.cs201;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sudoku_hw4 {
    static boolean[][][] existed_matrix;
    static boolean[][] existed_row;
    static boolean[][] existed_column;

    public static int[] find_possible_values(int[][] matrix, int x, int y) {
        List<Integer> existed_values = new ArrayList<>();
        List<Integer> possible_values = new ArrayList<>();

        // Check for row
        for (int i = 0; i < 9; i++) {
            if (matrix[i][y] != 0) {
                existed_values.add(matrix[i][y]);
            }
        }

        // Check for column
        for (int j = 0; j < 9; j++) {
            if (matrix[x][j] != 0) {
                existed_values.add(matrix[x][j]);
            }
        }

        // Check for sub_matrix 3x3
        int sub_matrix_x = 3 * (x / 3);
        int sub_matrix_y = 3 * (y / 3);
        for (int i = sub_matrix_x; i < sub_matrix_x + 3; i++) {
            for (int j = sub_matrix_y; j < sub_matrix_y + 3; j++) {
                if (matrix[i][j] != 0) {
                    existed_values.add(matrix[i][j]);
                }
            }
        }

        for (int k = 1; k <= 9; k++) {
            if (!existed_values.contains(k)) {
                possible_values.add(k);
            }
        }

        int[] possibleValues = new int[possible_values.size()];
        for (int i = 0; i < possible_values.size(); i++) {
            possibleValues[i] = possible_values.get(i);
        }

        return possibleValues;
    }

    public static void solve(int[][] matrix) {
        existed_matrix = new boolean[3][3][9];
        existed_row = new boolean[9][9];
        existed_column = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = matrix[i][j];
                if (num != 0) {
                    existed_matrix[i / 3][j / 3][num - 1] = true;
                    existed_row[i][num - 1] = true;
                    existed_column[j][num - 1] = true;
                }
            }
        }
        solve_sudoku(matrix, 0, 0);
    }

    private static boolean solve_sudoku(int[][] matrix, int x, int y) {
        if (x == 9) {
            // Print the solution
            for (int i = 0; i < 9; i++) {
                System.out.println(Arrays.toString(matrix[i]));
            }
            return true;
        }
        int nextX;
        int nextY;        
        if (y == 8) {
            nextX = x + 1;
            nextY = 0;
        } else {
            nextX = x;
            nextY = y + 1;
        }
        if (matrix[x][y] != 0) {
            return solve_sudoku(matrix, nextX, nextY);
        }

        for (int z : find_possible_values(matrix, x, y)) {
            if (!existed_column[y][z - 1] && !existed_row[x][z - 1] && !existed_matrix[x / 3][y / 3][z - 1]) {
                existed_column[y][z - 1] = true;
                existed_row[x][z - 1] = true;
                existed_matrix[x / 3][y / 3][z - 1] = true;
                matrix[x][y] = z;

                if (solve_sudoku(matrix, nextX, nextY)) {
                    return true;
                }

                existed_column[y][z - 1] = false;
                existed_row[x][z - 1] = false;
                existed_matrix[x / 3][y / 3][z - 1] = false;
                matrix[x][y] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 5, 0, 0, 0, 2, 0, 0, 6},
                {0, 8, 0, 0, 0, 6, 7, 0, 3},
                {0, 0, 0, 7, 4, 0, 0, 0, 8},
                {6, 7, 0, 9, 8, 0, 0, 0, 0},
                {0, 3, 1, 0, 5, 0, 6, 0, 9},
                {0, 0, 0, 0, 0, 3, 8, 2, 0},
                {0, 0, 0, 0, 0, 0, 1, 8, 0},
                {0, 0, 0, 3, 0, 0, 0, 0, 2},
                {9, 2, 0, 5, 0, 0, 0, 7, 0}};

        int[] possible_values = find_possible_values(matrix, 3, 5);
        System.out.println(Arrays.toString(possible_values));

        solve(matrix);
    }
}