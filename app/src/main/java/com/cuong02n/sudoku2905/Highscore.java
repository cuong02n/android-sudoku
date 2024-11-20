package com.cuong02n.sudoku2905;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class Highscore extends AppCompatActivity {
    public static final int MAX_SIZE=20;
    ImageButton veryeasy, easy, medium, hard, veryhard, expert, impossible;
    ImageButton buttonhighscorenext;
    ImageButton buttonhighscoreback;
    ImageButton back;
    Button buttoninfohighscore;
    int currentsize = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_highscore);

        Intent intent = getIntent();
        currentsize = intent.getIntExtra("size", 4);
        String hardmode = intent.getStringExtra("hardmode");
        int complete = intent.getIntExtra("completed", 0);
        long time = intent.getLongExtra("totaltime", -1);
        buttoninfohighscore = findViewById(R.id.buttoninfohighscore);
        buttonhighscorenext = findViewById(R.id.buttonhighscorenext);
        buttonhighscoreback = findViewById(R.id.buttonhighscoreback);
        veryeasy = findViewById(R.id.highscoreveryeasy);
        easy = findViewById(R.id.highscoreeasy);
        medium = findViewById(R.id.highscoremedium);
        hard = findViewById(R.id.highscorehard);
        veryhard = findViewById(R.id.highscoreveryhard);
        expert = findViewById(R.id.highscoreexpert);
        impossible = findViewById(R.id.highscoreimpossible);
        buttoninfohighscore.setText(currentsize + " x " + currentsize);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Highscore.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        PopupMenu popupMenu = new PopupMenu(Highscore.this, buttoninfohighscore);
        popupMenu.getMenuInflater().inflate(R.menu.menu_, popupMenu.getMenu());
        buttoninfohighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu4x4:
                        currentsize = 4;
                        buttoninfohighscore.setText("4 x 4");
                        break;
                    case R.id.menu5x5:
                        currentsize = 5;
                        buttoninfohighscore.setText("5 x 5");
                        break;
                    case R.id.menu6x6:
                        currentsize = 6;
                        buttoninfohighscore.setText("6 x 6");
                        break;
                    case R.id.menu7x7:
                        currentsize = 7;
                        buttoninfohighscore.setText("7 x 7");
                        break;
                    case R.id.menu8x8:
                        currentsize = 8;
                        buttoninfohighscore.setText("8 x 8");
                        break;
                    case R.id.menu9x9:
                        currentsize = 9;
                        buttoninfohighscore.setText("9 x 9");
                        break;
                    case R.id.menu10x10:
                        currentsize = 10;
                        buttoninfohighscore.setText("10 x 10");
                        break;
                    case R.id.menu11x11:
                        currentsize = 11;
                        buttoninfohighscore.setText("11 x 11");
                        break;
                    case R.id.menu12x12:
                        currentsize = 12;
                        buttoninfohighscore.setText("12 x 12");
                        break;
                    case R.id.menu13x13:
                        currentsize = 13;
                        buttoninfohighscore.setText("13 x 13");
                        break;
                    case R.id.menu14x14:
                        currentsize = 14;
                        buttoninfohighscore.setText("14 x 14");
                        break;
                    case R.id.menu15x15:
                        currentsize = 15;
                        buttoninfohighscore.setText("15 x 15");
                        break;
                    case R.id.menu16x16:
                        currentsize = 16;
                        buttoninfohighscore.setText("16 x 16");
                        break;
                    case R.id.menu17x17:
                        currentsize = 17;
                        buttoninfohighscore.setText("17 x 17");
                        break;
                    case R.id.menu18x18:
                        currentsize = 17;
                        buttoninfohighscore.setText("18 x 18");
                        break;
                    case R.id.menu19x19:
                        currentsize = 17;
                        buttoninfohighscore.setText("19 x 19");
                        break;
                    case R.id.menu20x20:
                        currentsize = 17;
                        buttoninfohighscore.setText("20 x 20");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        buttonhighscoreback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentsize == 4) {
                    currentsize = MAX_SIZE;
                } else {
                    currentsize--;
                }
                buttoninfohighscore.setText(currentsize + " x " + currentsize);
            }
        });

        buttonhighscorenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentsize == MAX_SIZE) {
                    currentsize = 4;
                } else {
                    currentsize++;
                }
                buttoninfohighscore.setText(currentsize + " x " + currentsize);
            }
        });

        veryeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "veryeasy");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "easy");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "medium");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "hard");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });
        veryhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "veryhard");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });
        expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "expert");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });
        impossible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Highscore.this, info_highscore.class);
                intent.putExtra("hardmode", "impossible");
                intent.putExtra("size", currentsize);
                startActivity(intent);
            }
        });

        if (complete == 1) {
            Intent intent1 = new Intent(Highscore.this, info_highscore.class);
            switch (hardmode) {
                case "veryeasy":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "veryeasy");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                case "easy":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "easy");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                case "medium":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "medium");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                case "hard":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "hard");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                case "veryhard":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "veryhard");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                case "expert":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "expert");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                case "impossible":
                    intent1.putExtra("totaltime", time);
                    intent1.putExtra("hardmode", "impossible");
                    intent1.putExtra("size", currentsize);
                    startActivity(intent1);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Highscore.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}