package com.cuong02n.sudoku2905;

public class CheckSudoku {

    static boolean checkcountSudoku(int[][] A, int size) {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (A[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt == size * size;
    }

    static boolean checkrowisSudoku(int[][] A, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (A[i][j] == 0) {
                    return false;
                }

                for (int k = j + 1; k < size; k++) {
                    if (A[i][j] == A[i][k]) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    static boolean checkcolumnisSudoku(int[][] A, int size) {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (A[i][j] == 0) {
                    return false;
                }
                for (int k = i + 1; k < size; k++) {
                    if (A[i][j] == A[k][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean checksquareisSudoku(int[][] A, int size) {
        switch (size) {
            case 4:
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 2; k++) {
                            for (int l = 0; l < 2; l++) {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 2; n++) {
                                        if ((A[2 * i + k][2 * j + l] == A[2 * i + m][2 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            case 5:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (!checknumsquare(i, j, A, size)) {
                            return false;
                        }
                    }
                }
                return true;
            case 6:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 2; k++) {
                            for (int l = 0; l < 3; l++) {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 3; n++) {
                                        if ((A[2 * i + k][3 * j + l] == A[2 * i + m][3 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            case 7:
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (!checknumsquare(i, j, A, size)) {
                            return false;
                        }
                    }
                }
                return true;
            case 8:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 2; k++) {
                            for (int l = 0; l < 4; l++) {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 4; n++) {
                                        if ((A[2 * i + k][4 * j + l] == A[2 * i + m][4 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            case 9:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                for (int m = 0; m < 3; m++) {
                                    for (int n = 0; n < 3; n++) {
                                        if ((A[3 * i + k][3 * j + l] == A[3 * i + m][3 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            case 10:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 2; k++) {
                            for (int l = 0; l < 5; l++) {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 5; n++) {
                                        if ((A[2 * i + k][5 * j + l] == A[2 * i + m][5 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 11:
                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (!checknumsquare(i, j, A, size)) {
                            return false;
                        }
                    }
                }
                return true;
            case 12:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 4; l++) {
                                for (int m = 0; m < 3; m++) {
                                    for (int n = 0; n < 4; n++) {
                                        if ((A[3 * i + k][4 * j + l] == A[3 * i + m][4 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 13:

                break;
            case 14:
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < 2; k++) {
                            for (int l = 0; l < 7; l++) {
                                for (int m = 0; m < 2; m++) {
                                    for (int n = 0; n < 7; n++) {
                                        if ((A[2 * i + k][7 * j + l] == A[2 * i + m][7 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 15:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 5; l++) {
                                for (int m = 0; m < 3; m++) {
                                    for (int n = 0; n < 5; n++) {
                                        if ((A[3 * i + k][5 * j + l] == A[3 * i + m][5 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 16:
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 4; l++) {
                                for (int m = 0; m < 4; m++) {
                                    for (int n = 0; n < 4; n++) {
                                        if ((A[4 * i + k][4 * j + l] == A[4 * i + m][4 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            case 17:
                for (int i = 0; i < 17; i++) {
                    for (int j = 0; j < 17; j++) {
                        if (!checknumsquare(i, j, A, size)) {
                            return false;
                        }
                    }
                }
                return true;
            case 18:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 6; l++) {
                                for (int m = 0; m < 3; m++) {
                                    for (int n = 0; n < 6; n++) {
                                        if ((A[3 * i + k][6 * j + l] == A[3 * i + m][6 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 20:
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                for (int m = 0; m < 4; m++) {
                                    for (int n = 0; n < 5; n++) {
                                        if ((A[4 * i + k][5 * j + l] == A[4 * i + m][5 * j + n]) && ((k != m) || (l != n))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

    static boolean isSudoku(int[][] A, int size) {
        if (!checkcountSudoku(A, size)) {
            return false;
        }
        return checkcolumnisSudoku(A, size) && checkrowisSudoku(A, size) && checksquareisSudoku(A, size);
    }

    public static boolean checknumrow(int u, int v, int[][] current, int size) {//hang u cot v
        for (int j = 0; j < size; j++) {
            if (current[u][j] == current[u][v] && j != v) {
                return false;
            }
        }
        return true;
    }

    public static boolean checknumcolumn(int u, int v, int[][] current, int size) {
        for (int i = 0; i < size; i++) {
            if (current[i][v] == current[u][v] && i != u) {
                return false;
            }
        }
        return true;
    }

    public static boolean checknumsquare(int u, int v, int[][] current, int size) {
        switch (size) {
            case 4:
                for (int i = u / 2 * 2; i < u / 2 * 2 + 2; i++) {
                    for (int j = v / 2 * 2; j < v / 2 * 2 + 2; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                break;
            case 5:
                return checkfive(u, v, current);
            case 6:
                for (int i = u / 2 * 2; i < u / 2 * 2 + 2; i++) {
                    for (int j = v / 3 * 3; j < v / 3 * 3 + 3; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                return true;
            case 7:
                return checkseven(u, v, current);
            case 8:
                for (int i = u / 2 * 2; i < u / 2 * 2 + 2; i++) {
                    for (int j = v / 4 * 4; j < v / 4 * 4 + 4; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                return true;
            case 9:
                for (int i = u / 3 * 3; i < u / 3 * 3 + 3; i++) {
                    for (int j = v / 3 * 3; j < v / 3 * 3 + 3; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                return true;
            case 10:
                for (int i = u / 2 * 2; i < u / 2 * 2 + 2; i++) {
                    for (int j = v / 5 * 5; j < v / 5 * 5 + 5; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                return true;
            case 11:
                return check11(u, v, current);
            case 12:
                for (int i = u / 3 * 3; i < u / 3 * 3 + 3; i++) {
                    for (int j = v / 4 * 4; j < v / 4 * 4 + 4; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                break;
            case 13:
                return check13(u, v, current);
            case 14:
                for (int i = u / 2 * 2; i < u / 2 * 2 + 2; i++) {
                    for (int j = v / 7 * 7; j < v / 7 * 7 + 7; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                break;
            case 15:
                for (int i = u / 3 * 3; i < u / 3 * 3 + 3; i++) {
                    for (int j = v / 5 * 5; j < v / 5 * 5 + 5; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                break;
            case 16:
                for (int i = u / 4 * 4; i < u / 4 * 4 + 4; i++) {
                    for (int j = v / 4 * 4; j < v / 4 * 4 + 4; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                return true;
            case 17:
                return check17(u, v, current);
            case 18:
                for (int i = u / 3 * 3; i < u / 3 * 3 + 3; i++) {
                    for (int j = v / 6 * 6; j < v / 6 * 6 + 6; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                break;
            case 20:
                for (int i = u / 4 * 4; i < u / 4 * 4 + 4; i++) {
                    for (int j = v / 5 * 5; j < v / 5 * 5 + 5; j++) {
                        if (current[u][v] == current[i][j] && (i != u || j != v)) {
                            return false;
                        }
                    }
                }
                return true;
            default:
                break;
        }
        return true;
    }

    public static boolean checknum(int i, int j, int[][] current, int size) {

        return checknumcolumn(i, j, current, size) && checknumrow(i, j, current, size) && checknumsquare(i, j, current, size);

    }

    public static boolean checkfive(int u, int v, int[][] current) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (makesq5[i][j] == makesq5[u][v]) {
                    if (current[i][j] == current[u][v] && (i != u || j != v)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkseven(int i, int j, int[][] current) {
        for (int k = 0; k < 7; k++) {
            for (int l = 0; l < 7; l++) {
                if (makesq7[k][l] == makesq7[i][j]) {
                    if (current[i][j] == current[k][l] && ((i != k) || (j != l))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static boolean check11(int i, int j, int[][] current) {
        for (int k = 0; k < 11; k++) {
            for (int l = 0; l < 11; l++) {
                if (makesq11[k][l] == makesq11[i][j]) {
                    if (current[i][j] == current[k][l] && ((i != k) || (j != l))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean check17(int i, int j, int[][] current) {
        for (int k = 0; k < 17; k++) {
            for (int l = 0; l < 17; l++) {
                if (makesq17[k][l] == makesq17[i][j]) {
                    if (current[i][j] == current[k][l] && ((i != k) || (j != l))) {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    public static boolean check13(int i, int j, int[][] current) {
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 13; l++) {
                if (makesq13[k][l] == makesq13[i][j]) {
                    if (current[i][j] == current[k][l] && ((i != k) || (j != l))) {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    static int[][] makesq7 = {
            {0, 0, 0, 1, 2, 2, 2},
            {0, 0, 0, 1, 3, 2, 2},
            {0, 1, 1, 1, 3, 2, 2},
            {1, 1, 3, 3, 3, 5, 5},
            {4, 4, 3, 5, 5, 5, 6},
            {4, 4, 3, 5, 6, 6, 6},
            {4, 4, 4, 5, 6, 6, 6},
    };
    static int[][] makesq5 = {
            {0, 0, 0, 1, 1},
            {0, 0, 2, 1, 1},
            {3, 2, 2, 2, 1},
            {3, 3, 2, 4, 4},
            {3, 3, 4, 4, 4}
    };
    static int[][] makesq17 = {
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3},
            {0, 0, 0, 0, 5, 5, 1, 1, 1, 6, 6, 6, 2, 2, 3, 3, 3},
            {4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 3, 3, 3},
            {4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 3, 3},
            {4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 8, 8, 8, 9, 9},
            {4, 4, 4, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9},
            {10, 4, 4, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9},
            {10, 10, 10, 10, 7, 7, 7, 12, 12, 8, 8, 8, 9, 9, 9, 9, 9},
            {10, 10, 10, 10, 7, 7, 12, 12, 12, 12, 12, 12, 9, 9, 9, 9, 9},
            {10, 10, 10, 10, 11, 11, 11, 12, 12, 12, 12, 12, 16, 16, 16, 16, 16},
            {10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 16, 16, 16, 16, 16},
            {13, 13, 13, 13, 11, 11, 11, 11, 14, 14, 14, 15, 15, 16, 16, 16, 16},
            {13, 13, 13, 13, 11, 11, 11, 14, 14, 14, 14, 15, 15, 15, 16, 16, 16},
            {13, 13, 13, 13, 11, 11, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15},
            {13, 13, 13, 13, 13, 11, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15}
    };
    static  int[][] makesq13={
            {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3},
            {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3},
            {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3},
            {0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 4, 4, 4},
            {5, 5, 5, 5, 1, 1, 6, 2, 4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5, 6, 6, 6, 4, 4, 4, 4, 4},
            {5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7},
            {8, 8, 8, 8, 8, 6, 6, 6, 7, 7, 7, 7, 7},
            {8, 8, 8, 8, 8, 9, 6, 10, 10, 7, 7, 7, 7},
            {8, 8, 8, 11, 9, 9, 9, 10, 10, 12, 12, 12, 12},
            {11, 11, 11, 11, 9, 9, 9, 10, 10, 10, 12, 12, 12},
            {11, 11, 11, 11, 9, 9, 9, 10, 10, 10, 12, 12, 12},
            {11, 11, 11, 11, 9, 9, 9, 10, 10, 10, 12, 12, 12}
    };
    static int[][] makesq11={
            {0,0,0,0,1,1,1,1,2,2,2},
            {0,0,0,0,1,1,1,1,2,2,2},
            {0,0,0,4,4,4,1,1,2,2,2},
            {3,3,3,4,4,4,4,1,2,2,5},
            {3,3,3,4,4,4,4,5,5,5,5},
            {3,3,3,6,6,6,7,7,7,5,5},
            {3,3,6,6,6,6,7,7,7,5,5},
            {8,8,6,6,6,6,7,7,7,5,5},
            {8,8,8,9,9,9,7,7,10,10,10},
            {8,8,8,9,9,9,9,10,10,10,10},
            {8,8,8,9,9,9,9,10,10,10,10}
    };
}
