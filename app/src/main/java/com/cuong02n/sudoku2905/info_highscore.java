package com.cuong02n.sudoku2905;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class info_highscore extends AppCompatActivity {
    String[] infoplayername = new String[21];
    long[] infoplayertime = new long[21];
    //name-time
    SharedPreferences sharedPreferences;
    Dialog dialog;
    EditText playername;
    TextView STT;
    Button ok;
    String namenow;
    TextView txttmp;
    ImageButton back;
    TextView[] playernameHighscore = new TextView[21];
    TextView[] timeHighscore = new TextView[21];
    //size-hardmode-time-name
    //x9-veryeasy-201232-cuong
    //size-hardmode-stt-time-name
    //size-hardmode-stt
    int positionnow = 21;
    int maxposition = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();


        setContentView(R.layout.info_highscore);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txttmp = findViewById(R.id.txttmp);
        sharedPreferences = getSharedPreferences("highscore", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = getIntent();
        int size = intent.getIntExtra("size", 4);
        long time = intent.getLongExtra("totaltime", -1);
        String hardmode = intent.getStringExtra("hardmode");
        txttmp.setText("" + size + "x" + size + "  " + hardmode.toUpperCase());
        txttmp.setGravity(Gravity.CENTER);
        for (int i = 1; i <= 20; i++) {
            String c1 = "playernameHighscore" + i;
            String c2 = "timeHighscore" + i;
            int Resid1 = getResources().getIdentifier(c1, "id", getPackageName());
            int Resid2 = getResources().getIdentifier(c2, "id", getPackageName());
            playernameHighscore[i] = findViewById(Resid1);
            timeHighscore[i] = findViewById(Resid2);
        }
        if (time != -1) {//vừa chơi xong
            for (int i = 1; i <= 20; i++) {
                infoplayername[i] = sharedPreferences.getString("x" + size + "-" + hardmode + "-" + i + "-name", "");
                infoplayertime[i] = sharedPreferences.getLong("x" + size + "-" + hardmode + "-" + i + "-time", -1);
                if (infoplayertime[i] == -1) {
                    maxposition = i - 1;
                    positionnow = i;
                    break;
                }
            }
            for (int i = 1; i <= 20; i++) {
                if (maxposition == 0) {
                    positionnow = 1;
                    maxposition = 1;
                }
                if (maxposition < i) {
                    break;
                }
                if (time < infoplayertime[i]) {
                    positionnow = i;
                    editor.remove("x" + size + "-" + hardmode + "-20-time");
                    editor.remove("x" + size + "-" + hardmode + "-20-name");
                    editor.apply();

                    for (int j = 19; j >= positionnow; j--) {
                        editor.putString("x" + size + "-" + hardmode + "-" + (j + 1) + "-name", infoplayername[j]);
                        editor.putLong("x" + size + "-" + hardmode + "-" + (j + 1) + "-time", infoplayertime[j]);
                        editor.apply();
                        infoplayername[j + 1] = infoplayername[j];
                        infoplayertime[j + 1] = infoplayertime[j];
                    }
                    break;
                }
            }
            infoplayername[positionnow] = "Player anonymous";
            infoplayertime[positionnow] = time;
            dialog = new Dialog(info_highscore.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.inputnamehighscore);
            dialog.show();
            ok = dialog.findViewById(R.id.ok);
            playername = dialog.findViewById(R.id.playername);
            STT = dialog.findViewById(R.id.STT);
            STT.setText("#" + positionnow);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    namenow = playername.getText().toString();
                    if (namenow.length() >= 2) {
                        editor.putLong("x" + size + "-" + hardmode + "-" + (positionnow) + "-time", time);
                        editor.putString("x" + size + "-" + hardmode + "-" + (positionnow) + "-name", namenow);
                        editor.apply();
                        infoplayertime[positionnow] = time;
                        infoplayername[positionnow] = namenow;
                        dialog.cancel();
                        showlist();
                    } else {
                        Toast.makeText(info_highscore.this, "Name not Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            for (int i = 1; i <= 20; i++) {
                infoplayername[i] = sharedPreferences.getString("x" + size + "-" + hardmode + "-" + i + "-name", "");
                infoplayertime[i] = sharedPreferences.getLong("x" + size + "-" + hardmode + "-" + i + "-time", -1);
                if (infoplayertime[i] == -1) {
                    break;
                }
            }
            showlist();
        }
        showlist();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(info_highscore.this, Highscore.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        super.onBackPressed();
    }

    String converttime(long time) {
        if (time > 0 && time < 60000) {
            if (time <= 9999) {
                return "0" + time / 1000 + "." + time % 1000;
            } else {
                return "" + time / 1000 + "." + time % 1000;
            }
        } else if (time >= 60000 && time < 3600000) {
            if (time <= 599999) {
                return "0" + time / 60000 + ":" + converttime(time % 60000);
            } else {
                return "" + time / 60000 + ":" + converttime(time % 60000);
            }
        } else if (time >= 3600000 && time < 86400000) {
            if (time <= 35999999) {
                return "0" + time / 3600000 + ":" + converttime(time % 3600000);
            } else {
                return "" + time / 3600000 + ":" + converttime(time % 3600000);
            }
        } else {
            return "" + time / 86400000 + "d " + converttime(time % 86400000);
        }
    }

    void showlist() {
        for (int i = 1; i <= 20; i++) {
            if (infoplayertime[i] > 1000) {
                playernameHighscore[i].setText(infoplayername[i]);
                timeHighscore[i].setText(converttime(infoplayertime[i]));
            }
        }
    }
}
