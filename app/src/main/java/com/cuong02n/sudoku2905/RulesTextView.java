package com.cuong02n.sudoku2905;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class RulesTextView {
    public static int[] makeTextView(int size, int i, int j) {
        int[] attrs = new int[5];
        //attrs 0 : height
        //attrs[1] width
        //attrs[2] margin Top
        //attrs[3] margin Start
        //attrs[4] textSize
        switch (size) {
            case 4:
                attrs[0] = 77;//height = 38dp
                attrs[1] = 77;//width
                attrs[2] = 76 * i;//margintop
                attrs[3] = 76 * j;
                attrs[4] = 27;
                break;
            case 5:
                attrs[0] = 62;
                attrs[1] = 62;
                attrs[4] = 23;
                attrs[2] = 61 * i;
                attrs[3] = 61 * j;
                break;
            case 6:
                attrs[0] = 52;
                attrs[1] = 52;
                attrs[4] = 22;
                attrs[2] = 51 * i;
                attrs[3] = 51 * j;
                break;
            case 7:
                attrs[0] = 45;
                attrs[1] = 45;
                attrs[4] = 21;
                attrs[2] = 44 * i;
                attrs[3] = 44 * j;
                break;
            case 8:
                attrs[0] = 39;
                attrs[1] = 39;
                attrs[4] = 20;
                attrs[2] = 38 * i;
                attrs[3] = 38 * j;
                break;
            case 9:
                attrs[0] = 35;
                attrs[1] = 35;
                attrs[4] = 20;
                attrs[2] = 34 * i;
                attrs[3] = 34 * j;
                break;
            case 10:
                attrs[0] = 31;
                attrs[1] = 31;
                attrs[2] = 30 * i;
                attrs[3] = 30 * j;
                attrs[4] = 17;
                break;
            case 11:
                attrs[0] = 29;
                attrs[1] = 29;
                attrs[4] = 16;
                attrs[2] = 28 * i;
                attrs[3] = 28 * j;
                break;
            case 12:
                attrs[0] = 26;
                attrs[1] = 26;
                attrs[2] = 25 * i;
                attrs[3] = 25 * j;
                attrs[4] = 16;
                break;
            case 13:
                attrs[0] = 25;
                attrs[1] = 25;
                attrs[4] = 15;
                attrs[2] = 24 * i;
                attrs[3] = 24 * j;
                break;
            case 14:
                attrs[0] = 23;
                attrs[1] = 23;
                attrs[4] = 14;
                attrs[2] = 22 * i;
                attrs[3] = 22 * j;
                break;
            case 15:
                attrs[0] = 22;
                attrs[1] = 22;
                attrs[4] = 14;
                attrs[2] = 21 * i;
                attrs[3] = 21 * j;
                break;
            case 16:
                attrs[0] = 21;
                attrs[1] = 21;
                attrs[4] = 14;
                attrs[2] = 20 * i;
                attrs[3] = 20 * j;
                break;
            case 17:
                attrs[0] = 19;
                attrs[1] = 19;
                attrs[4] = 13;
                attrs[2] = 18 * i;
                attrs[3] = 18 * j;
                break;
            case 18:
                attrs[0] = 18;
                attrs[1] = 18;
                attrs[4] = 13;
                attrs[2] = 17 * i;
                attrs[3] = 17 * j;
                break;
            case 20:
                attrs[0] = 17;
                attrs[1] = 17;
                attrs[4] = 11;
                attrs[2] = 16 * i;
                attrs[3] = 16 * j;
            default:
                break;
        }
        return attrs;
    }

    @NonNull
    public static TextView addTextView(Activity activity, @NonNull RelativeLayout relativeLayoutpar, int size, int i, int j) {
        int[] attrs = makeTextView(size, i, j);
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
}
