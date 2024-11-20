package com.cuong02n.sudoku2905;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import java.util.Calendar;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Vector;

public class activity_play extends AppCompatActivity implements S {
    private final int MAX_UNDO_DEQUE = 50;
    private boolean isloaded = false;
    private RelativeLayout layoutParText;
    private RelativeLayout layourParSubText;
    private final Activity main = activity_play.this;
    private String code = "";
    private String codenow = "";
    private final String TAG = "Sudoku_2905";
    private RequestConfiguration r;
    private RewardedAd rewardedAd1;
    private boolean completed = false;
    Handler handler = new Handler();
    Handler hand = new Handler();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Vector<Integer> B = new Vector<>(0, 1);
    private int size;
    private boolean[][] row;
    private boolean[][] column;
    private boolean[][] square;
    private int[][] current;
    private int[][] A;//tmp
    private boolean[][] fixed;
    private TextView[][][] subTextView;
    private int[][][] subTextViewnote;
    private TextView[][] textView;
    private Button[] button;
    private boolean[][] iswrong;
    private int[][] result;
    private Vector<Integer>[][] suff;
    private char[] previous = new char[3];
    private String c;
    private String hardmode;
    private int resId;
    private ConstraintLayout root;
    private ImageButton remove;
    private ImageButton back;
    private ImageButton undo;
    private ImageButton hint;
    private ImageButton note;
    private ImageButton noteall;
    private ImageButton clear;
    private ImageButton clearnote;
    private ImageButton setting;
    private TextView infohardmode;
    private TextView timeview;

    private int I = -1;
    private int J = -1;
    private int hintav = 1;
    private long time1;
    private long time2;
    private long timenow;
    private boolean noteisOn;
    private Calendar calendar;
    private Deque<infomation> queueundo;

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

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        calendar = Calendar.getInstance();
        time1 = calendar.getTimeInMillis();
        intent = getIntent();
        size = intent.getIntExtra("size", -1);

        queueundo = new LinkedList<>();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        switch (size) {
            case 4:
                setContentView(R.layout.activity_4x4);
                hintav = 1;
                break;
            case 5:
                setContentView(R.layout.activity_5x5);
                hintav = 1;
                break;
            case 6:
                setContentView(R.layout.activity_6x6);
                hintav = 2;
                break;
            case 7:
                setContentView(R.layout.activity_7x7);
                hintav = 2;
                break;
            case 8:
                setContentView(R.layout.activity_8x8);
                hintav = 3;
                break;
            case 9:
                setContentView(R.layout.activity_9x9);
                hintav = 3;
                break;
            case 10:
                setContentView(R.layout.activity_10x10);
                hintav = 3;
                break;
            case 11:
                setContentView(R.layout.activity_11x11);
                hintav = 3;
                break;
            case 12:
                setContentView(R.layout.activity_12x12);
                hintav=3;
                break;
            case 13:
                setContentView(R.layout.activity_13x13);
                hintav=3;
                break;
            case 14:
                setContentView(R.layout.activity_14x14);
                hintav=4;
                break;
            case 15:
                setContentView(R.layout.activity_15x15);
                hintav=4;
                break;
            case 16:
                setContentView(R.layout.activity_16x16);
                hintav = 4;
                break;
            case 17:
                setContentView(R.layout.activity_17x17);
                hintav = 4;
                break;
            case 18:
                setContentView(R.layout.activity_18x18);
                hintav = 5;
                break;
//            case 17:
//                setContentView(R.layout.activity_19x19);
//                hintav = 4;
//                break;
            case 20:
                setContentView(R.layout.activity_20x20);
                hintav = 5;
                break;
            default:
                break;
        }

        row = new boolean[size][size + 1];
        column = new boolean[size][size + 1];
        square = new boolean[size][size + 1];
        current = new int[size][size];
        A = new int[size][size];
        fixed = new boolean[size][size];
        subTextView = new TextView[size][size][size + 1];
        subTextViewnote = new int[size][size][size + 1];
        textView = new TextView[size][size];
        button = new Button[size + 1];
        iswrong = new boolean[size][size];
        result = new int[size][size];
        suff = new Vector[size][size];

        root = findViewById(R.id.root);
        infohardmode = findViewById(R.id.infohardmode);
        layoutParText = findViewById(R.id.layoutoto);
        layourParSubText = findViewById(R.id.layoutocon);
        timeview = findViewById(R.id.time);
        sharedPreferences = getSharedPreferences("progress", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        hardmode = sharedPreferences.getString("hardmode", "Error");
        infohardmode.setText(hardmode.toUpperCase());
        editor.putInt("size", size);


        generated_adView();

        loadAdReward();

        sethack();

        set_back_button();

        set_hint_button();

        noteisOn = false;
        set_note_button();

        find_TextView();

        b1();

        switch (hardmode) {
            case "veryeasy":
                makerandom.makerandomveryeasy(fixed, size);
                break;
            case "easy":
                makerandom.makerandomeasy(fixed, size);
                break;
            case "medium":
                makerandom.makerandommedium(fixed, size);
                break;
            case "hard":
                makerandom.makerandomhard(fixed, size);
                break;
            case "veryhard":
                makerandom.makerandomveryhard(fixed, size);
                break;
            case "expert":
                makerandom.makerandomexpert(fixed, size);
                break;
            case "impossible":
                makerandom.makerandomimpossible(fixed, size);
                break;
            default:
                break;
        }

        try {
            solveSudoku(0, 0);
        } catch (Exception ignored) {
        }

        generated_Sudoku();

        editor.remove("timenow");
        editor.apply();

        set_text_click_n_change_listener();

        set_button_click_listener();

        set_remove_button();

        set_undo_button();

        set_clear_button();

        set_note_all_button();

        set_clear_note_button();
        timeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText();
                Toast.makeText(main, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        setting();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void generated_Sudoku() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!fixed[i][j]) {// chưa fix(chưa cố định), chữ đen
                    textView[i][j].setTextColor(Color.parseColor("#ff000000"));
                    textView[i][j].setText("");
                    current[i][j] = 0;
                } else {// đã fix , đã cố định , chữ màu xanh
                    textView[i][j].setText("" + makechar(result[i][j]));
                    textView[i][j].setTextColor(Color.parseColor("#4FB353"));
                    current[i][j] = result[i][j];
                }
            }
        }
    }

    @Override
    public void generated_adView() {
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void set_undo_button() {
        undo = findViewById(R.id.undo_);
        undo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                setundo();
            }
        });
    }

    @Override
    public void set_remove_button() {
        remove = findViewById(R.id.remove_);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!noteisOn) {
                    if (I >= 0 && J >= 0) {
                        if (!fixed[I][J] && current[I][J] != 0) {
                            textView[I][J].setText("");
                            current[I][J] = 0;
                            iswrong[I][J] = false;
                            for (int i = 0; i < size; i++) {
                                for (int j = 0; j < size; j++) {
                                    iswrong[i][j] = !CheckSudoku.checknum(i, j, current, size) && current[i][j] != 0;
                                }
                            }
                            for (int i = 0; i < size; i++) {
                                for (int j = 0; j < size; j++) {
                                    if (!iswrong[i][j]) {
                                        if (i != I && j != J && (makesq[size][i][j] != makesq[size][I][J])) {
                                            textView[i][j].setBackgroundColor(500061);
                                        } else if (i == I && j == J) {
                                            textView[i][j].setBackgroundColor(Color.parseColor("#FF40C4FF"));
                                        } else {
                                            textView[i][j].setBackgroundColor(Color.parseColor("#B4DEF1"));
                                        }
                                    }
                                    if (!iswrong[i][j] && current[i][j] == current[I][J] && (i != I || j != J) && current[i][j] != 0) {
                                        textView[i][j].setBackgroundColor(Color.parseColor("#82D0F3"));
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (I >= 0 && J >= 0) {
                        for (int k = 1; k <= size; k++) {
                            deletesubText(I, J, k);
                        }
                        if (current[I][J] != 0) {
                            current[I][J] = 0;
                            textView[I][J].setText("");
                        }
                    }
                }
            }
        });
    }

    @Override
    public void set_button_click_listener() {
        for (int m = 1; m <= size; m++) {
            c = "button" + m + "_";
            resId = getResources().getIdentifier(c, "id", main.getPackageName());
            button[m] = findViewById(resId);
            button[m].setTextColor(Color.parseColor("#FF000000"));
            int finalM = m;
            button[m].setOnClickListener(new View.OnClickListener() {//set on click 9 ô số
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    codenow = codenow + "" + finalM;
                    if (codenow.length() >= code.length()) {
                        codenow = codenow.substring(codenow.length() - code.length());
                    }
                    if (code.equals(codenow)) {
                        hintav += 500;
                        hint.setEnabled(true);
                    }

                    putundo(finalM);

                    if (!noteisOn) {
                        if (I >= 0 && J >= 0) {
                            if (!fixed[I][J] && finalM != current[I][J]) {// chưa fix
                                textView[I][J].setText("" + makechar(finalM));
                                current[I][J] = finalM;
                                for (int i = 0; i < size; i++) {
                                    for (int j = 0; j < size; j++) {
                                        updateText();
                                    }
                                }
                            }
                            for (int k = 1; k <= size; k++) {
                                deletesubText(I, J, k);
                            }
                        }
                    } else {// note is on
                        if (I >= 0 && J >= 0 && !fixed[I][J]) {
                            if (current[I][J] != 0) {
                                iswrong[I][J] = false;
                                current[I][J] = 0;
                                textView[I][J].setText("");
                            }
                            if (size <= 16) {
                                if (subTextViewnote[I][J][finalM] == 0) {
                                    subTextView[I][J][finalM] = RulesSubTextView.addSubTextView(main, layourParSubText, size, I, J, finalM);
                                    subTextView[I][J][finalM].setText("" + makechar(finalM));
                                    subTextViewnote[I][J][finalM] = finalM;
                                } else {
                                    deletesubText(I, J, finalM);
                                }
                            }
                        }
                    }
                }//end onclick button
            });//end Listener button
        }
    }

    @Override
    public void set_text_click_n_change_listener() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int finalI = i;
                int finalJ = j;

                textView[finalI][finalJ].setOnClickListener(new View.OnClickListener() {// 81 cái ô
                    @Override
                    public void onClick(View view) {
                        I = finalI;
                        J = finalJ;
                        updateText();
                    }
                });
                textView[finalI][finalJ].addTextChangedListener(new TextWatcher() {

                    private int tmp = 0;

                    @Override
                    public void beforeTextChanged(CharSequence charSequence/*kí tự cũ*/, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence/* kí tự mới rồi */, int i, int i1, int i2) {
                        if (charSequence.length() != 0) {
                            tmp = makenumber(charSequence.charAt(0));
                        } else {
                            tmp = 0;
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (I >= 0 && J >= 0) {
                            current[I][J] = tmp;
                        }
                        if (CheckSudoku.isSudoku(current, size)) {
                            completed = true;
                            calendar = Calendar.getInstance();
                            time2 = calendar.getTimeInMillis();
                            long time = (time2 - time1);
                            long timez = time / 1000;
                            handler.removeCallbacksAndMessages(null);
                            queueundo.clear();
                            timeview.setText(printtime(timez));
                            editor.clear();
                            editor.apply();
                            editor.putInt("size", -1);
                            editor.apply();
                            for (int k = 0; k < size; k++) {
                                for (int l = 0; l < size; l++) {
                                    current[k][l] = 0;
                                    result[k][l] = 0;
                                }
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    complete1();
                                }
                            }, 100L * size * 2 + 300);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    complete1();
                                }
                            }, 100L * size * 4 + 600);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(main, Highscore.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("size", size);
                                    intent.putExtra("hardmode", hardmode);
                                    intent.putExtra("completed", 1);
                                    intent.putExtra("totaltime", time);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.enter, R.anim.exit);
                                }
                            }, 100L * size * 6 + 900);
                        }
                        for (int k = 1; k <= size; k++) {
                            deletesubText(finalI, finalJ, k);
                        }
                        updateText();
                    }
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
                textView[i][j].setTextColor(Color.parseColor("#FF000000"));
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!fixed[i][j]) {// chưa fix(chưa cố định), chữ đen
                    textView[i][j].setTextColor(Color.parseColor("#ff000000"));
                    textView[i][j].setText("");
                    current[i][j] = 0;
                } else {// đã fix , đã cố định , chữ màu xanh
                    textView[i][j].setText("" + makechar(result[i][j]));
                    textView[i][j].setTextColor(Color.parseColor("#4FB353"));
                    current[i][j] = result[i][j];
                }
            }
        }
    }

    @Override
    public void set_note_button() {
        note = findViewById(R.id.note_);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size <= 16) {
                    noteisOn = !noteisOn;
                    if (noteisOn) {
                        note.setImageResource(R.drawable.noteon);
                        Toast.makeText(main, "Note mod : on", Toast.LENGTH_SHORT).show();
                    } else {
                        note.setImageResource(R.drawable.noteoff);
                        Toast.makeText(main, "Note mod : off", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(main, String.valueOf(R.string.no_note_from17), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void set_hint_button() {
        hint = findViewById(R.id.hint_);
        hint.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (hintav > 0) {
                    if (I >= 0 && J >= 0 && !fixed[I][J]) {
                        hintav = hintav - 1;
                        switch (hintav) {
                            case 4:
                                hint.setImageResource(R.drawable.hint4);
                                break;
                            case 3:
                                hint.setImageResource(R.drawable.hint3);
                                break;
                            case 2:
                                hint.setImageResource(R.drawable.hint2);
                                break;
                            case 1:
                                hint.setImageResource(R.drawable.hint1);
                                break;
                            case 0:
                                hint.setImageResource(R.drawable.hint0);
                                break;
                            default:
                                hint.setImageResource(R.drawable.hintinfinity);
                                break;
                        }
                        current[I][J] = result[I][J];
                        fixed[I][J] = true;
                        textView[I][J].setText("" + makechar(result[I][J]));
                        textView[I][J].setTextColor(Color.parseColor("#4FB353"));
                    }
                } else {
                    showRewardedAd();
                }
            }
        });
    }

    @Override
    public void set_back_button() {
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void set_clear_note_button() {
        clearnote = findViewById(R.id.clearnote_);
        clearnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        for (int k = 1; k <= size; k++) {
                            deletesubText(i, j, k);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void set_note_all_button() {
        noteall = findViewById(R.id.noteall_);
        noteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size <= 16) {
                    showRewardedAd();
//                setnoteAll();
                } else {
                    Toast.makeText(main, String.valueOf(R.string.no_note_from17), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void set_clear_button() {
        clear = findViewById(R.id.clear_);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (I >= 0 && J >= 0) {
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (!fixed[i][j]) {// chua fix
                                if (current[i][j] != 0) {
                                    current[i][j] = 0;
                                    textView[i][j].setText("");
                                }
                                textView[i][j].setText("");

                            }
                            for (int k = 1; k < size + 1; k++) {
                                deletesubText(i, j, k);

                            }
                        }
                    }
                    queueundo.clear();
                    updateText();
                }
            }
        });
    }

    private void deletesubText(int i, int j, int k) {
        if (size <= 16) {
            if (subTextViewnote[i][j][k] != 0) {
                subTextViewnote[i][j][k] = 0;
                ((ViewGroup) subTextView[i][j][k].getParent()).removeView(subTextView[i][j][k]);
                subTextView[i][j][k] = null;
            }
        }
    }

    @Override
    public void showRewardedAd() {
        if (rewardedAd1 != null) {
            rewardedAd1.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    Log.d(TAG, "onAdClicked: ");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Log.d(TAG, "onAdDismissedFullScreenContent: ");
                    rewardedAd1 = null;
                    loadAdReward();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    Log.d(TAG, "onAdFailedToShowFullScreenContent: ");
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    Log.d(TAG, "onAdImpression: ");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    Log.d(TAG, "onAdShowedFullScreenContent: ");
                }
            });
            rewardedAd1.show(this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    Log.d(TAG, "onUserEarnedReward: thanhcongroi");
                    setnoteAll();
                }
            });
        } else {
            Log.d(TAG, "van la null: ");
        }
    }

    @Override
    public void loadAdReward() {
        if (!isloaded) {
            MobileAds.initialize(main, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                    isloaded = true;
                }
            });
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
        for (int i = 0; i < size; i++) {//cho fixed[][] full =false;
            for (int j = 0; j < size; j++) {
                fixed[i][j] = false;
                current[i][j] = 0;
                iswrong[i][j] = false;
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
        for (int i = 1; i <= size; i++) {
            B.addElement(i);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                suff[i][j] = (Vector<Integer>) B.clone();
                Collections.shuffle(suff[i][j]);
            }
        }
    }

    @Override
    public boolean checkfree(int u, int i, int j) {
        return row[i][u] && column[j][u] && square[makesq[size][i][j]][u];
    }

    @Override
    public void solution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = A[i][j];
            }
        }
    }

    @Override
    public void solveSudoku(int i, int j) {
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

    @Override
    public String printtime(long time) {
        long time1 = time;
        String t = "";
        if (time1 >= 86400) {
            long ngay = time1 / 86400;
            t = t + ngay + "d";
            long gio = (time1 - ngay * 86400) / 3600;
            if (gio == 0) {
                t = t + "00:";
            } else if (gio <= 9) {
                t = t + "0" + gio + ":";
            } else {
                t = t + gio + ":";
            }
            long phut = (time1 - ngay * 86400 - gio * 3600) / 60;
            if (phut == 0) {
                t = t + "00:";
            } else if (phut <= 9) {
                t = t + "0" + phut + ":";
            } else {
                t = t + phut + ":";
            }
            long giay = (time1 - ngay * 86400 - gio * 3600 - phut * 60);
            if (giay == 0) {
                t = t + "00";
            } else if (giay <= 9) {
                t = t + "0" + giay;
            } else {
                t = t + giay;
            }
        } else if (time1 >= 3600) {
            long ngay = time1 / 86400;
            long gio = (time1 - ngay * 86400) / 3600;
            if (gio == 0) {
                t = t + "00:";
            } else if (gio <= 9) {
                t = t + "0" + gio + ":";
            } else {
                t = t + gio + ":";
            }
            long phut = (time1 - ngay * 86400 - gio * 3600) / 60;
            if (phut == 0) {
                t = t + "00:";
            } else if (phut <= 9) {
                t = t + "0" + phut + ":";
            } else {
                t = t + phut + ":";
            }
            long giay = (time1 - ngay * 86400 - gio * 3600 - phut * 60);
            if (giay == 0) {
                t = t + "00";
            } else if (giay <= 9) {
                t = t + "0" + giay;
            } else {
                t = t + giay;
            }
        } else if (time1 >= 60) {
            long ngay = time1 / 86400;
            long gio = (time1 - ngay * 86400) / 3600;
            long phut = (time1 - ngay * 86400 - gio * 3600) / 60;
            if (phut == 0) {
                t = t + "00:";
            } else if (phut <= 9) {
                t = t + "0" + phut + ":";
            } else {
                t = t + phut + ":";
            }
            long giay = (time1 - ngay * 86400 - gio * 3600 - phut * 60);
            if (giay == 0) {
                t = t + "00";
            } else if (giay <= 9) {
                t = t + "0" + giay;
            } else {
                t = t + giay;
            }
        } else {
            long ngay = time1 / 86400;
            long gio = (time1 - ngay * 86400) / 3600;
            long phut = (time1 - ngay * 86400 - gio * 3600) / 60;
            if (phut == 0) {
                t = t + "00:";
            } else if (phut <= 9) {
                t = t + "0" + phut + ":";
            } else {
                t = t + phut + ":";
            }
            long giay = (time1 - ngay * 86400 - gio * 3600 - phut * 60);
            if (giay == 0) {
                t = t + "00";
            } else if (giay <= 9) {
                t = t + "0" + giay;
            } else {
                t = t + giay;
            }
        }
        return t;
    }

    @Override
    public void updateText() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                textView[i][j].setBackgroundColor(Color.TRANSPARENT);
                iswrong[i][j] = current[i][j] != 0 && !CheckSudoku.checknum(i, j, current, size);

                if (iswrong[i][j]) {                                              // sai
                    textView[i][j].setBackgroundColor(getResources().getColor(R.color.wrong));
                } else {                                                          // đúng
                    if (i == I || j == J || makesq[size][i][j] == makesq[size][I][J]) {//  cùng hàng , cột , ô vuông
                        textView[i][j].setBackgroundColor(getResources().getColor(R.color.o_mau_nhat));
                    }
                    if (i == I && j == J) {//                                           là ô I J
                        textView[i][j].setBackgroundColor(getResources().getColor(R.color.o_mau_dam));
                    }
                    if (current[i][j] == current[I][J] && (i != I && j != J) && (current[I][J] != 0)) {             // trùng với ô I J
                        textView[i][j].setBackgroundColor(getResources().getColor(R.color.o_trung_voi_I_J));
                    }
                }
            }
        }
    }

    @Override
    public void complete1() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                textView[i][j].setEnabled(false);
                textView[i][j].setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
            button[i + 1].setEnabled(false);
        }
        undo.setEnabled(false);
        remove.setEnabled(false);
        hint.setEnabled(false);
        note.setEnabled(false);
        Handler zxc = new Handler();
        zxc.postDelayed(new Runnable() {
            int zzzz = 0;

            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (i + j == zzzz) {
                            textView[i][j].setBackgroundColor(Color.parseColor("#FF40C4FF"));
                        } else if (i + j == zzzz + 1 || i + j == zzzz - 1) {
                            textView[i][j].setBackgroundColor(Color.parseColor("#82D0F3"));
                        }
                    }
                }
                zzzz++;
                if (zzzz == 2 * size - 1) {
                    return;
                } else {
                    zxc.postDelayed(this, 100);
                }
            }
        }, 300);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setnoteAll() {
        if (size <= 16) {
            int[][] tmp = current.clone();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (current[i][j] == 0) {
                        for (int k = 1; k <= size; k++) {
                            tmp[i][j] = k;
                            if (CheckSudoku.checknum(i, j, tmp, size)) {
                                if (subTextView[i][j][k] == null) {
                                    subTextView[i][j][k] = RulesSubTextView.addSubTextView(main, layourParSubText, size, i, j, k);
                                }
                                subTextView[i][j][k].setText("" + makechar(k));
                                subTextViewnote[i][j][k] = k;

                            } else {
                                deletesubText(i, j, k);
                            }
                            tmp[i][j] = 0;
                        }
                    }
                }
            }
        }
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
        code = "";
        for (int i = 1; i <= size; i++) {
            code = code + "" + i;
        }
        for (int i = size / 2 + 1; i <= size; i++) {
            code = code + "" + i;
        }
        for (int i = 1; i <= size / 2; i++) {
            code = code + "" + i;
        }
    }

    @Override
    public void putundo(int m) {
        if (!fixed[I][J]) {
            if (current[I][J] != m) {
                queueundo.addLast(new infomation(I, J, current[I][J]));
            }
            if (queueundo.size() > MAX_UNDO_DEQUE) {
                queueundo.removeFirst();
            }

        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setundo() {
        infomation t = queueundo.peekLast();
        if (t != null) {
            int x = t.getX();
            int y = t.getY();
            int curr = t.getCurrent();
            I = x;
            J = y;
            queueundo.removeLast();
            if (!fixed[I][J]) {
                if (current[I][J] != curr) {
                    textView[I][J].setText("" + makechar(curr));
                    current[I][J] = curr;
                } else {
                    setundo();
                }
            } else {
                setundo();
            }
        } else {
            Toast.makeText(main, "No thing in Undo queue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                calendar = Calendar.getInstance();
                time2 = calendar.getTimeInMillis();
                timenow = (time2 - time1);
                if (sharedPreferences.getLong("timenow", 0) > timenow) {
                    Toast.makeText(main, "TIME NOT INVALID , PLEASE SET TIME TO DEFAULT AND PLAY NEW GAME", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            current[i][j] = 0;
                            fixed[i][j] = false;

                        }
                        button[i + 1].setEnabled(false);
                    }
                    handler.removeCallbacksAndMessages(null);
                    queueundo.clear();
                    editor.clear();
                    editor.apply();
                    editor.putInt("size", -1);

                    editor.apply();
                    for (int k = 0; k < size; k++) {
                        for (int l = 0; l < size; l++) {
                            current[k][l] = 0;
                            result[k][l] = 0;
                        }
                    }
                    size = -1;
                } else {
                    editor.putLong("timenow", timenow);
                    editor.apply();
                    timeview.setText(printtime(timenow / 1000));
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        hand.removeCallbacksAndMessages(null);
        if (!completed) {
            sharedPreferences = getSharedPreferences("progress", MODE_PRIVATE);
            calendar = Calendar.getInstance();
            time2 = calendar.getTimeInMillis();
            long time = time2 - time1;
            editor.putLong("time", time);
            editor.putString("hardmode", hardmode);
            editor.putInt("hint", hintav);
            editor.putInt("I", I);
            editor.putInt("J", J);
            editor.putInt("size", size);
            editor.putInt("totalstep", queueundo.size());
            editor.apply();
            for (int l = 0; l < size; l++) {
                for (int k = 0; k < size; k++) {
                    String x = "current-" + l + "-" + k;
                    editor.putInt(x, current[l][k]);
                    String y = "result-" + l + "-" + k;
                    editor.putInt(y, result[l][k]);
                    String z = "fixed-" + l + "-" + k;
                    editor.putBoolean(z, fixed[l][k]);
                    editor.apply();
                }
            }
            if (queueundo.size() > 0) {
                int side = queueundo.size();
                for (int i = 0; i < side; i++) {
                    infomation t = queueundo.pollFirst();
                    editor.putString("inf-" + i, t.getX() + "-" + t.getY() + "-" + t.getCurrent());
                    editor.apply();
                }
            }
            super.onDestroy();
        } else {
            editor.clear();
            editor.apply();
            super.onDestroy();
        }
    }

    @Override
    public void setting() {
//        setting=findViewById(R.id.setting);
//        setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ColorPicker cp = new ColorPicker(main);
//                cp.show();
//                Button okColor = cp.findViewById(R.id.okColorButton);
//                okColor.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(activity_play.this, ""+cp.getColor(), Toast.LENGTH_SHORT).show();
//                        cp.dismiss();
//                    }
//                });
//            }
//        });
    }
}