package com.cuong02n.sudoku2905;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.Collections;
import java.util.Vector;

public class Solve extends AppCompatActivity implements S {
    RelativeLayout layoutParText;
    boolean solved = false;
    private int I = -1;
    private int J = -1;
    private int size = 4;
    ImageButton back;
    RelativeLayout top1;
    ImageButton undo, hint, noteall, clearnote, solve, clear, remove;
    private final Activity main = Solve.this;
    private boolean isloaded = false;
    RewardedAd rewardedAd1;
    ConstraintLayout root;
    private final String TAG = "asdasdasd";
    private Vector<Integer>[][] suff;
    Vector<Integer> B = new Vector<>(0, 1);
    private boolean[][] row;
    private boolean[][] column;
    private boolean[][] square;
    private int[][] A;
    private boolean[][] fixed;
    private TextView[][] textView;
    private Button[] button;
    private boolean[][] iswrong;
    private int[][] result;
    int[][] current;
    String c = "";
    int resId = 0;
    private int[][] debai;
    private final int[][][] makesq = {
            {},
            {},
            {},
            {},
            {
                    {0, 0, 1, 1},
                    {0, 0, 1, 1},
                    {2, 2, 3, 3},
                    {2, 2, 3, 3}},

            {
                    {1, 1, 1, 0, 0},
                    {1, 1, 2, 0, 0},
                    {3, 2, 2, 2, 0},
                    {3, 3, 2, 4, 4},
                    {3, 3, 4, 4, 4}},
            {
                    {0, 0, 0, 1, 1, 1},
                    {0, 0, 0, 1, 1, 1},
                    {2, 2, 2, 3, 3, 3},
                    {2, 2, 2, 3, 3, 3},
                    {4, 4, 4, 5, 5, 5},
                    {4, 4, 4, 5, 5, 5}
            },
            {
                    {0, 0, 0, 1, 2, 2, 2},
                    {0, 0, 0, 1, 3, 2, 2},
                    {0, 1, 1, 1, 3, 2, 2},
                    {1, 1, 3, 3, 3, 5, 5},
                    {4, 4, 3, 5, 5, 5, 6},
                    {4, 4, 3, 5, 6, 6, 6},
                    {4, 4, 4, 5, 6, 6, 6},
            },
            {
                    {0, 0, 0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1},
                    {2, 2, 2, 2, 3, 3, 3, 3},
                    {2, 2, 2, 2, 3, 3, 3, 3},
                    {4, 4, 4, 4, 5, 5, 5, 5},
                    {4, 4, 4, 4, 5, 5, 5, 5},
                    {6, 6, 6, 6, 7, 7, 7, 7},
                    {6, 6, 6, 6, 7, 7, 7, 7}
            },
            {
                    {0, 0, 0, 1, 1, 1, 2, 2, 2},
                    {0, 0, 0, 1, 1, 1, 2, 2, 2},
                    {0, 0, 0, 1, 1, 1, 2, 2, 2},
                    {3, 3, 3, 4, 4, 4, 5, 5, 5},
                    {3, 3, 3, 4, 4, 4, 5, 5, 5},
                    {3, 3, 3, 4, 4, 4, 5, 5, 5},
                    {6, 6, 6, 7, 7, 7, 8, 8, 8},
                    {6, 6, 6, 7, 7, 7, 8, 8, 8},
                    {6, 6, 6, 7, 7, 7, 8, 8, 8}
            },
            {
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                    {2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                    {4, 4, 4, 4, 4, 5, 5, 5, 5, 5},
                    {4, 4, 4, 4, 4, 5, 5, 5, 5, 5},
                    {6, 6, 6, 6, 6, 7, 7, 7, 7, 7},
                    {6, 6, 6, 6, 6, 7, 7, 7, 7, 7},
                    {8, 8, 8, 8, 8, 9, 9, 9, 9, 9},
                    {8, 8, 8, 8, 8, 9, 9, 9, 9, 9}
            },
            {
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2},
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2},
                    {0, 0, 0, 4, 4, 4, 1, 1, 2, 2, 2},
                    {3, 3, 3, 4, 4, 4, 4, 1, 2, 2, 5},
                    {3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5},
                    {3, 3, 3, 6, 6, 6, 7, 7, 7, 5, 5},
                    {3, 3, 6, 6, 6, 6, 7, 7, 7, 5, 5},
                    {8, 8, 6, 6, 6, 6, 7, 7, 7, 5, 5},
                    {8, 8, 8, 9, 9, 9, 7, 7, 10, 10, 10},
                    {8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10},
                    {8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10}
            },
            {
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2},
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2},
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2},
                    {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5},
                    {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5},
                    {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5},
                    {6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8},
                    {6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8},
                    {6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8},
                    {9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11},
                    {9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11},
                    {9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11}
            },
            {
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
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3},
                    {2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3},
                    {4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5},
                    {4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5},
                    {6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7},
                    {6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7},
                    {8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9},
                    {8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9},
                    {10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11},
                    {10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11},
                    {12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13},
                    {12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13}
            },
            {
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                    {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5},
                    {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5},
                    {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5},
                    {6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8},
                    {6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8},
                    {6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8},
                    {9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14}
            },
            {
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},
                    {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3},
                    {4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7},
                    {4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7},
                    {4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7},
                    {4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7},
                    {8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11},
                    {8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11},
                    {8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11},
                    {8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11},
                    {12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15},
                    {12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15},
                    {12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15},
                    {12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15}
            },
            {
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
            },
            {
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2},
                    {3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5},
                    {3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5},
                    {3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5},
                    {6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8},
                    {6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8},
                    {6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8},
                    {9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11},
                    {9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11},
                    {9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11},
                    {12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14},
                    {12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14},
                    {12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14},
                    {15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17},
                    {15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17},
                    {15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17}
            },
            {},//19
            {
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                    {4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7},
                    {4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7},
                    {4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7},
                    {4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7},
                    {8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15},
                    {12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15},
                    {16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19},
                    {16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19},
                    {16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19},
                    {16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19}
            }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        size = i.getIntExtra("size", 4);
        suff = new Vector[size][size];
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        switch (size) {
            case 4:
                setContentView(R.layout.activity_4x4);
                break;
            case 5:
                setContentView(R.layout.activity_5x5);
                break;
            case 6:
                setContentView(R.layout.activity_6x6);
                break;
            case 7:
                setContentView(R.layout.activity_7x7);
                break;
            case 8:
                setContentView(R.layout.activity_8x8);
                break;
            case 9:
                setContentView(R.layout.activity_9x9);
                break;
            case 10:
                setContentView(R.layout.activity_10x10);
                break;
            case 11:
                setContentView(R.layout.activity_11x11);
                break;
            case 12:
                setContentView(R.layout.activity_12x12);
                break;
            case 13:
                setContentView(R.layout.activity_13x13);
                break;
            case 14:
                setContentView(R.layout.activity_14x14);
                break;
            case 15:
                setContentView(R.layout.activity_15x15);
                break;
            case 16:
                setContentView(R.layout.activity_16x16);
                break;
            case 18:
                setContentView(R.layout.activity_18x18);
                break;
            case 20:
                setContentView(R.layout.activity_20x20);
                break;
            default:
                break;
        }
        debai = new int[size][size];
        current = new int[size][size];
        row = new boolean[size][size + 1];
        column = new boolean[size][size + 1];
        square = new boolean[size][size + 1];
        A = new int[size][size];
        fixed = new boolean[size][size];
        textView = new TextView[size][size];
        button = new Button[size + 1];
        iswrong = new boolean[size][size];
        result = new int[size][size];
        root = findViewById(R.id.root);
        root.setOnClickListener(view -> {
            I = -1;
            J = -1;
            for (int i1 = 0; i1 < size; i1++) {
                for (int j = 0; j < size; j++) {
                    if (iswrong[i1][j]) {
                        textView[i1][j].setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    } else {
                        textView[i1][j].setBackgroundColor(Color.parseColor("#EFA3A3"));
                    }
                }
            }
            updateText();
        });
        layoutParText = findViewById(R.id.layoutoto);
        layoutParText = findViewById(R.id.layoutoto);
        undo = findViewById(R.id.undo_);
        ((ViewGroup) undo.getParent()).removeView(undo);
        hint = findViewById(R.id.hint_);
        ((ViewGroup) hint.getParent()).removeView(hint);
        solve = findViewById(R.id.note_);
        solve.setImageResource(R.drawable.solve);
        clearnote = findViewById(R.id.clearnote_);
        ((ViewGroup) clearnote.getParent()).removeView(clearnote);
        noteall = findViewById(R.id.noteall_);
        ((ViewGroup) noteall.getParent()).removeView(noteall);

        generated_adView();

        loadAdReward();

        sethack();

        set_back_button();

        set_hint_button();

        set_note_button();

        find_TextView();

        generated_Sudoku();

        set_text_click_n_change_listener();

        set_button_click_listener();

        set_remove_button();

        set_undo_button();

        set_clear_button();

        set_note_all_button();

        set_clear_note_button();

        updateText();
        solve.setOnClickListener(view -> {
            if (checkit()) {
                try {
                    b1();
                    solveSudoku(0, 0);
                } catch (Exception ignore) {
                }
                if (solved = false) {
                    Toast.makeText(main, "Invalid", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(main, "Invalid", Toast.LENGTH_SHORT).show();
            }
        });

        top1 = findViewById(R.id.top1_);
        top1.setVisibility(View.INVISIBLE);
    }

    public boolean checkit() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (iswrong[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void solveSudoku(int i, int j) {
        if (A[i][j] != 0) {
            if (j == size - 1) {
                if (i == size - 1) {
                    solution();
                    solved = true;
                    int A = 1 / 0;
                } else {
                    solveSudoku(i + 1, 0);
                }
            } else {
                solveSudoku(i, j + 1);
            }
        } else {
            for (int m = 0; m < size; m++) {
                int u = suff[i][j].elementAt(m);
                if (checkfree(u, i, j)) {
                    A[i][j] = u;
                    row[i][u] = false;
                    column[j][u] = false;
                    square[makesq[size][i][j]][u] = false;
                    if (j == size - 1) {
                        if (i == size - 1) {
                            solution();
                            solved = true;
                            int A = 1 / 0;
                        } else {
                            solveSudoku(i + 1, 0);
                        }
                    } else {
                        solveSudoku(i, j + 1);
                    }
                    A[i][j] = 0;
                    row[i][u] = true;
                    column[j][u] = true;
                    square[makesq[size][i][j]][u] = true;
                }
            }
        }
    }

    @Override
    public void generated_Sudoku() {

    }

    @Override
    public void generated_adView() {
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void set_undo_button() {
    }

    @Override
    public void set_remove_button() {
        remove = findViewById(R.id.remove_);
        remove.setOnClickListener(view -> {
            if (I >= 0 && J >= 0) {
                A[I][J] = 0;
                textView[I][J].setText("");
                textView[I][J].setTextColor(Color.parseColor("#44000000"));
                fixed[I][J] = false;
                updateText();
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!fixed[i][j]) {
                        textView[i][j].setText("");
                        A[i][j] = 0;
                    }
                }
            }
            updateText();
        });

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void set_button_click_listener() {
        for (int m = 1; m <= size; m++) {
            c = "button" + m + "_";
            resId = getResources().getIdentifier(c, "id", main.getPackageName());
            button[m] = findViewById(resId);
            button[m].setTextColor(Color.parseColor("#FF000000"));
            int finalM = m;
            button[m].setOnClickListener(view -> {
                if (I >= 0 && J >= 0) {
                    fixed[I][J] = true;
                    textView[I][J].setText("" + makechar(finalM));
                    textView[I][J].setTextColor(Color.parseColor("#FFFF0000"));
                    A[I][J] = finalM;
                    debai[I][J]=finalM;
                    updateText();
                }
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (!fixed[i][j]) {
                            A[i][j] = 0;
                            textView[i][j].setText("");
                        }
                    }
                }
                updateText();
            });
        }
    }

    @Override
    public void set_text_click_n_change_listener() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int finalI = i;
                int finalJ = j;
                textView[finalI][finalJ].setOnClickListener(view -> {
                    I = finalI;
                    J = finalJ;
                    updateText();
                });

            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void find_TextView() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                textView[i][j] = findViewById(main.getResources().getIdentifier("o" + (i * size + j) + "_", "id", main.getPackageName()));
                textView[i][j].setGravity(Gravity.CENTER);
                textView[i][j].setTextColor(Color.parseColor("#44000000"));
            }
        }
    }


    @Override
    public void set_note_button() {

    }

    @Override
    public void set_hint_button() {

    }

    @Override
    public void set_back_button() {
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());
    }

    @Override
    public void set_clear_note_button() {

    }

    @Override
    public void set_note_all_button() {

    }

    @Override
    public void set_clear_button() {
        clear = findViewById(R.id.clear_);
        clear.setOnClickListener(view -> {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    fixed[i][j] = false;
                    textView[i][j].setText("");
                    A[i][j] = 0;
                    debai[i][j] = 0;
                    textView[i][j].setTextColor(Color.parseColor("#44000000"));
                }
            }
        });
    }

    @Override
    public void showRewardedAd() {

    }

    @Override
    public void loadAdReward() {
        if (!isloaded) {
            MobileAds.initialize(main, initializationStatus -> isloaded = true);
        }
        AdRequest adRequest1 = new AdRequest.Builder().build();
        RewardedAd.load(main,
                String.valueOf(R.string.adrewarded), adRequest1, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, "" + loadAdError.getMessage() + " failed to load");
                        rewardedAd1 = null;
                        super.onAdFailedToLoad(loadAdError);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        Log.d(TAG, "" + rewardedAd + " loaded");
                        rewardedAd1 = rewardedAd;
                    }
                });
    }

    @Override
    public void b1() {
        solved = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = 0;
                if (fixed[i][j]) {
                    debai[i][j] = A[i][j];
                } else {
                    debai[i][j] = 0;
                }
            }
        }


        for (int i = 0; i < size; i++) {
            for (int u = 1; u <= size; u++) {
                row[i][u] = true;
                column[i][u] = true;
                for (int j = 0; j < size; j++) {
                    square[makesq[size][i][j]][u] = true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = debai[i][j];
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                iswrong[i][j] = debai[i][j] != 0 && !CheckSudoku.checknum(i, j, A, size);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fixed[i][j]) {
                    row[i][A[i][j]] = false;
                    column[j][A[i][j]] = false;
                    square[makesq[size][i][j]][A[i][j]] = false;
                } else {
                    textView[i][j].setText("");
                    A[i][j] = 0;
                    debai[i][j] = 0;
                }
            }
        }
        B.clear();
        for (int i = 1; i <= size; i++) {
            B.addElement(i);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                suff[i][j] = (Vector<Integer>) B.clone();
                Collections.shuffle(suff[i][j]);
            }
        }
        updateText();
    }

    @Override
    public boolean checkfree(int u, int i, int j) {
        return row[i][u] && column[j][u] && square[makesq[size][i][j]][u];
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void solution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = A[i][j];
                textView[i][j].setText("" + makechar(result[i][j]));
            }
        }
    }


    @Override
    public String printtime(long time) {
        return "null";
    }

    @Override
    public void updateText() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                textView[i][j].setBackgroundColor(Color.TRANSPARENT);
                iswrong[i][j] = debai[i][j] != 0 && !CheckSudoku.checknum(i, j, debai, size);
                if (iswrong[i][j]) {                                              // sai
                    textView[i][j].setBackgroundColor(getResources().getColor(R.color.wrong));
                } else {
                    if (I >= 0 && J >= 0) {// đúng
                        if (i == I || j == J || makesq[size][i][j] == makesq[size][I][J]) {//  cùng hàng , cột , ô vuông
                            textView[i][j].setBackgroundColor(getResources().getColor(R.color.o_mau_nhat));
                        }
                        if (i == I && j == J) {//                                           là ô I J
                            textView[i][j].setBackgroundColor(getResources().getColor(R.color.o_mau_dam));
                        }
                        if (A[i][j] == A[I][J] && (i != I && j != J) && (A[I][J] != 0)) {             // trùng với ô I J
                            textView[i][j].setBackgroundColor(getResources().getColor(R.color.o_trung_voi_I_J));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void complete1() {

    }

    @Override
    public void setnoteAll() {

    }

    @Override
    public int makenumber(char m) {
        switch (m) {
            case '\u0000':
                return 0;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            case 'G':
                return 16;
            case 'H':
                return 17;
            case 'I':
                return 18;
            case 'J':
                return 19;
            case 'K':
                return 20;
        }
        return m - 48;
    }

    @Override
    public char makechar(int m) {
        switch (m) {
            case 0:
                return '\u0000';
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            case 16:
                return 'G';
            case 17:
                return 'H';
            case 18:
                return 'I';
            case 19:
                return 'J';
            case 20:
                return 'K';
        }
        return (char) (m + 48);
    }

    @Override
    public void sethack() {

    }

    @Override
    public void putundo(int m) {

    }

    @Override
    public void setundo() {

    }

    @Override
    public void setting() {

    }
//    void loc() {
//        String c = "\n A \n";
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c += "\t" + A[i][j];
//            }
//            c += "\n";
//        }
//        c += "\n";
//        c += "debai \n";
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c += "\t" + debai[i][j];
//            }
//            c += "\n";
//        }
//        c += "\n";
//
//        c += "fixed \n";
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (fixed[i][j]) {
//                    c += "\t 1";
//                } else {
//                    c += "\t 0";
//                }
//            }
//            c += "\n";
//        }
//        c += "\n";
//
//        c += "iswrong \n";
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                c += "\t" + (iswrong[i][j]?0:1);
//            }
//            c += "\n";
//        }
//        c += "\n";
//        Log.d(TAG, "loc: " + c);
//    }
}