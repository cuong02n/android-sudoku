package com.cuong02n.sudoku2905;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class Newgame extends AppCompatActivity {
    Button Newgameveryeasy;
    Button Newgameeasy;
    Button Newgamemedium;
    Button Newgamehard;
    Button Newgameveryhard;
    Button Newgameexpert;
    Button Newgameimpossible;
    ImageButton buttonnewgameback;
    ImageButton buttonnewgamenext;
    ImageButton back;
    Button buttoninfonewgame;
    SharedPreferences sharedPreferences;
    int size = 4;
    ArrayList<String> listsize = new ArrayList<>();
    ProgressBar progressBar;
    Intent startnewgame;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("progress", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_newgame);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        for (int i = 4; i <= Highscore.MAX_SIZE; i++) {
            listsize.add(i + " x " + i);
        }

        buttonnewgameback = findViewById(R.id.buttonnewgameback);
        buttonnewgamenext = findViewById(R.id.buttonnewgamenext);
        buttoninfonewgame = findViewById(R.id.buttoninfonewgame);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        PopupMenu popupMenu = new PopupMenu(Newgame.this, buttoninfonewgame);
        popupMenu.getMenuInflater().inflate(R.menu.menu_, popupMenu.getMenu());
        buttoninfonewgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });
        size = sharedPreferences.getInt("size", 4);
        buttoninfonewgame.setText(size + " x " + size);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu4x4:
                        size = 4;
                        buttoninfonewgame.setText("4 x 4");
                        break;
                    case R.id.menu5x5:
                        size = 5;
                        buttoninfonewgame.setText("5 x 5");
                        break;
                    case R.id.menu6x6:
                        size = 6;
                        buttoninfonewgame.setText("6 x 6");
                        break;
                    case R.id.menu7x7:
                        size = 7;
                        buttoninfonewgame.setText("7 x 7");
                        break;
                    case R.id.menu8x8:
                        size = 8;
                        buttoninfonewgame.setText("8 x 8");
                        break;
                    case R.id.menu9x9:
                        size = 9;
                        buttoninfonewgame.setText("9 x 9");
                        break;
                    case R.id.menu10x10:
                        size = 10;
                        buttoninfonewgame.setText("10 x 10");
                        break;
                    case R.id.menu11x11:
                        size = 11;
                        buttoninfonewgame.setText("11 x 11");
                        break;
                    case R.id.menu12x12:
                        size = 12;
                        buttoninfonewgame.setText("12 x 12");
                        break;
                    case R.id.menu13x13:
                        size = 13;
                        buttoninfonewgame.setText("13 x 13");
                        break;
                    case R.id.menu14x14:
                        size = 14;
                        buttoninfonewgame.setText("14 x 14");
                        break;
                    case R.id.menu15x15:
                        size = 15;
                        buttoninfonewgame.setText("15 x 15");
                        break;
                    case R.id.menu16x16:
                        size = 16;
                        buttoninfonewgame.setText("16 x 16");
                        break;
                    case R.id.menu17x17:
                        size =17;
                        buttoninfonewgame.setText("17 x 17");
                        break;
                    case R.id.menu18x18:
                        size =18;
                        buttoninfonewgame.setText("18 x 18");
                        break;
                    case R.id.menu19x19:
                        size =19;
                        buttoninfonewgame.setText("19 x 19");
                        break;
                    case R.id.menu20x20:
                        size =20;
                        buttoninfonewgame.setText("20 x 20");
                        break;
                    default:
                        break;
                }
                return false;
            }

        });

        buttonnewgameback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size == 4) {
                    size = Highscore.MAX_SIZE;
                } else {
                    size--;
                }
                buttoninfonewgame.setText(size + " x " + size);
            }
        });
        buttonnewgamenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (size == Highscore.MAX_SIZE) {
                    size = 4;
                } else {
                    size++;
                }
                buttoninfonewgame.setText(size + " x " + size);
            }
        });

        Newgameveryeasy = findViewById(R.id.buttonnewgame1);
        Newgameeasy = findViewById(R.id.buttonnewgame2);
        Newgamemedium = findViewById(R.id.buttonnewgame3);
        Newgamehard = findViewById(R.id.buttonnewgame4);
        Newgameveryhard = findViewById(R.id.buttonnewgame5);
        Newgameexpert = findViewById(R.id.buttonnewgame6);
        Newgameimpossible = findViewById(R.id.buttonnewgame7);
        
        startnewgame= new Intent(Newgame.this,activity_play.class);

        Newgameveryeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "veryeasy");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Newgameeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "easy");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Newgamemedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "medium");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Newgamehard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "hard");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Newgameveryhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "veryhard");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Newgameexpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "expert");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Newgameimpossible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(1);
                progressBar.setVisibility(View.VISIBLE);
                editor.putString("hardmode", "impossible");
                editor.commit();
                if(!isOndeveloping()) {
                    startnewgame.putExtra("size", size);
                    startActivity(startnewgame);
                }else {
                    Toast.makeText(Newgame.this, "On developing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressBar.setVisibility(View.INVISIBLE);
    }
    public boolean isOndeveloping(){
        if(size==17||size==13||size==19||size==11){
            progressBar.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }
}
