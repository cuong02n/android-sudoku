package com.cuong02n.sudoku2905;

import java.util.Collections;
import java.util.Vector;

public class makerandom {
    static int[][] number = {
            //kho nhat -->de nhat
            {0, 0, 0, 0, 0, 0, 0},//0
            {0, 0, 0, 0, 0, 0, 0},//1
            {0, 0, 0, 0, 0, 0, 0},//2
            {0, 0, 0, 0, 0, 0, 0},//3
            {2, 3, 4, 5, 6, 7, 8},//4
            {3, 5, 6, 8, 10, 11, 13},//5
            {8, 9, 10, 12, 14, 16, 18},//6
            {9, 11, 12, 14, 16, 18, 21},//7
            {11, 14, 18, 21, 24, 27, 30},//8
            {20, 25, 29, 33, 37, 41, 45},//9
            {25, 30, 35, 45, 55, 60, 65},//10
            {30, 35, 40, 45, 55, 60, 70},//11
            {35, 40, 45, 50, 55, 65, 72},//12
            {35, 40, 45, 50, 55, 65, 75},//13
            {50, 55, 60, 70, 80, 90, 110},//14
            {75, 82, 90, 100, 110, 120, 130},//15
            {90, 110, 120, 130, 140, 150, 165},//16
            {90, 110, 120, 130, 140, 150, 160},//17
            {90, 110, 120, 130, 140, 160, 180},//18
            {},//19
            {100, 110, 130, 150, 170, 190, 210}//20
    };

    static void makerandomveryeasy(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][6]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][6]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }

    static void makerandomeasy(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][5]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][5]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }

    static void makerandommedium(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][4]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][4]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }

    static void makerandomhard(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][3]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][3]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }

    static void makerandomveryhard(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][2]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][2]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }

    static void makerandomexpert(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][1]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][1]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }

    static void makerandomimpossible(boolean[][] A, int size) {
        Vector<Boolean> rand = new Vector<>();
        for (int i = 0; i < number[size][0]; i++) {
            rand.addElement(true);
        }
        for (int i = number[size][0]; i < size * size; i++) {
            rand.addElement(false);
        }
        Collections.shuffle(rand);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = rand.elementAt(size * i + j);
            }
        }
    }
}

