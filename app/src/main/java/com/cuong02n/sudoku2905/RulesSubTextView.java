package com.cuong02n.sudoku2905;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class RulesSubTextView {
    public static int[] makeSubTextView(int size, int i, int j, int k) {
        int[] attrs = new int[5];
        //attrs 0 : height
        //attrs[1] width
        //attrs[2] margin Top
        //attrs[3] margin Start
        //attrs[4] textSize
        switch (size) {
            case 4:
                attrs[0] = 38;//height = 38dp
                attrs[1] = 38;//width
                attrs[2] = 76 * i + (k - 1) / 2 * 38;//margintop
                attrs[3] = 76 * j + (k - 1) % 2 * 38;
                attrs[4] = 14;
                break;
            case 5:
                attrs[0] = 18;
                attrs[1] = 18;
                attrs[4] = 11;
                if (k == 1) {
                    attrs[2] = 61 * i + 9;
                    attrs[3] = 61 * j + 2;
                } else if (k == 2) {
                    attrs[2] = 61 * i + 9;
                    attrs[3] = 61 * j + 20;
                } else if (k == 3) {
                    attrs[2] = 61 * i + 9;
                    attrs[3] = 61 * j + 39;
                } else if (k == 4) {
                    attrs[2] = 61 * i + 34;
                    attrs[3] = 61 * j + 9;
                } else {
                    attrs[2] = 61 * i + 34;
                    attrs[3] = 61 * j + 34;
                }
                break;
            case 6:
                attrs[0] = 15;
                attrs[1] = 15;
                attrs[4] = 11;
                if (k == 1 || k == 2 || k == 3) {
                    attrs[2] = 51 * i + 8;
                    attrs[3] = 51 * j + (k - 1) * 15 + 3;
                } else {
                    attrs[2] = 51 * i + 28;
                    attrs[3] = 51 * j + (k - 4) * 15 + 3;
                }
                break;
            case 7:
                attrs[0] = 12;
                attrs[1] = 12;
                attrs[4] = 10;
                if (k <= 3) {
                    attrs[2] = 44 * i + 4;
                    attrs[3] = 44 * j + (k - 1) * 12 + 4;
                } else if (k <= 6) {
                    attrs[2] = 44 * i + 16;
                    attrs[3] = 44 * j + (k - 4) * 12 + 4;
                } else {
                    attrs[2] = 44 * i + 28;
                    attrs[3] = 44 * j + 16;
                }
                break;
            case 8:
                attrs[0] = 10;
                attrs[1] = 10;
                attrs[4] = 9;
                if (k <= 3) {
                    attrs[2] = 38 * i + 3;
                    attrs[3] = 38 * j + (k - 1) * 11 + 3;

                } else if (k <= 6) {
                    attrs[2] = 38 * i + 14;
                    attrs[3] = 38 * j + (k - 4) * 11 + 3;

                } else if (k == 7) {
                    attrs[2] = 38 * i + 25;
                    attrs[3] = 38 * j + 7;
                } else {
                    attrs[2] = 38 * i + 25;
                    attrs[3] = 38 * j + 21;
                }
                break;
            case 9:
                attrs[0] = 8;
                attrs[1] = 8;
                attrs[4] = 7;
                attrs[2] = 34 * i + 4 + 9 * ((k - 1) / 3);
                attrs[3] = 34 * j + 4 + 9 * ((k - 1) % 3);
                break;
            case 10:
                attrs[4] = 7;
                if (k <= 4) {
                    attrs[0] = 8;
                    attrs[1] = 6;
                } else {
                    attrs[0] = 8;
                    attrs[1] = 8;
                }
                if (k <= 4) {
                    attrs[2] = 30 * i + 3;
                    attrs[3] = 30 * j + 3 + 6 * (k - 1);
                } else if (k <= 7) {
                    attrs[2] = 30 * i + 11;
                    attrs[3] = 30 * j + 3 + 8 * (k - 5);
                } else {
                    attrs[2] = 30 * i + 19;
                    attrs[3] = 30 * j + 3 + 8 * (k - 8);
                }
                break;
            case 11:

                break;
            case 12:
                attrs[0] = 7;
                attrs[1] = 5;
                attrs[4] = 6;
                attrs[2] = 25 * i + (k - 1) / 4 * 7;
                attrs[3] = 25 * j + 2 + (k - 1) % 4 * 5;
                break;
            case 13:
                attrs[0] = 5;
                attrs[1] = 5;
                attrs[4] = 4;
                attrs[2] = 24 * i + 2 + (k - 1) / 4 * 5;
                if (k == 13) {
                    attrs[3] = 10 + 24 * j;
                } else {
                    attrs[3] = 24 * j + 2 + (k - 1) % 4 * 5;
                }
                break;
            case 14:
                attrs[0] = 5;
                attrs[1] = 5;
                attrs[4] = 4;
                attrs[2] = 22 * i + 1 + (k - 1) / 4 * 5;
                if (k <= 12) {
                    attrs[3] = 22 * j + 1 + (k - 1) % 4 * 5;
                } else {
                    attrs[3] = 22 * j + (k - 13) * 7 + 5;
                }
                break;
            case 15:
                attrs[0] = 5;
                attrs[1] = 5;
                attrs[4] = 4;
                attrs[2] = 21 * i + (k - 1) / 4 * 5;
                if (k <= 12) {
                    attrs[3] = 21 * j + (k - 1) % 4 * 5;
                } else {
                    attrs[3] = 21 * j + (k - 13) * 6 + 2;
                }
                break;
            case 16:
                attrs[0] = 5;
                attrs[1] = 5;
                attrs[4] = 4;
                attrs[2] = 20 * i + (k - 1) / 4 * 5;
                attrs[3] = 20 * j + (k - 1) % 4 * 5;
                if (i == 0 && k <= 4) {
                    attrs[2]++;
                }
                if (i == 15 && k >= 13) {
                    attrs[2]--;
                }
                if (j == 0 && (k % 4 == 1)) {
                    attrs[3]++;
                }
                if (j == 15 && (k % 4 == 0)) {
                    attrs[3]--;
                }
                break;
            case 17:
            case 18:
                for (int l = 0; l < 5; l++) {
                    attrs[i] = 0;
                }
                break;
            default:
                break;
        }
        return attrs;
    }

    @NonNull
    public static TextView addSubTextView(Activity activity, @NonNull RelativeLayout relativeLayoutpar, int size, int i, int j, int k) {
        boolean accepted = true;
        if (size >16) {
            accepted = false;
        }
        if (accepted) {
            int[] attrs = makeSubTextView(size, i, j, k);
            TextView v = new TextView(activity);
            float height = activity.getResources().getDimension(activity.getResources().getIdentifier("dp" + attrs[0], "dimen", activity.getPackageName()));
            float width = activity.getResources().getDimension(activity.getResources().getIdentifier("dp" + attrs[1], "dimen", activity.getPackageName()));
            float marginTop = activity.getResources().getDimension(activity.getResources().getIdentifier("dp" + attrs[2], "dimen", activity.getPackageName()));
            float marginStart = activity.getResources().getDimension(activity.getResources().getIdentifier("dp" + attrs[3], "dimen", activity.getPackageName()));
            float textSize = activity.getResources().getDimension(activity.getResources().getIdentifier("sp" + attrs[4], "dimen", activity.getPackageName()));
            float density = activity.getResources().getDisplayMetrics().density;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) width, (int) height);
            layoutParams.setMargins((int) marginStart, (int) marginTop, 0, 0);
            v.setGravity(Gravity.CENTER);
            v.setTextColor(Color.BLACK);
            float tmp = 0.0f;
            v.setTextSize(TypedValue.COMPLEX_UNIT_SP, (textSize + tmp) / density);
            relativeLayoutpar.addView(v, layoutParams);
//        Toast.makeText(activity, "da tao dc", Toast.LENGTH_SHORT).show();
            return v;
        }
        return null;
    }
}
