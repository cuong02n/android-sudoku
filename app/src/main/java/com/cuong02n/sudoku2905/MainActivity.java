package com.cuong02n.sudoku2905;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int size = 4;
    Button Newgame;
    Button Continue;
    ImageButton Aboutus_privacy;
    ImageButton Exit;
    ImageButton Highscore;
    ImageButton Settings;
    Button Solve;
    SharedPreferences sharedPreferences;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("progress", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Settings = findViewById(R.id.setting);
        Settings.setOnClickListener(view -> Toast.makeText(MainActivity.this, "" + getString(R.string.featureisunderdevelopment), Toast.LENGTH_SHORT).show());
        Solve = findViewById(R.id.Solve);
        Newgame = findViewById(R.id.Newgame);
        Continue = findViewById(R.id.Continue);
        Highscore = findViewById(R.id.Highscore);
        Aboutus_privacy = findViewById(R.id.Aboutus_privacy);
        Exit=findViewById(R.id.Exit);
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, Solve);
        popupMenu.getMenuInflater().inflate(R.menu.menu_, popupMenu.getMenu());

        Solve.setOnClickListener(view -> {
            popupMenu.show();
            Intent i = new Intent(MainActivity.this, Solve.class);
            size = sharedPreferences.getInt("size", 4);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.menu4x4:
                        size = 4;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu5x5:
                        size = 5;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu6x6:
                        size = 6;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu7x7:
                        size = 7;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu8x8:
                        size = 8;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu9x9:
                        size = 9;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu10x10:
                        size = 10;
                        if(!isOndeveloping()) {
                        i.putExtra("size", size);
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                    }
                        break;
                    case R.id.menu11x11:
                        size = 11;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu12x12:
                        size = 12;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu13x13:
                        size = 13;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu14x14:
                        size = 14;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu15x15:
                        size = 15;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu16x16:
                        size = 16;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu17x17:
                        size = 17;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu18x18:
                        size = 18;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu19x19:
                        size = 19;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu20x20:
                        size = 20;
                        if(!isOndeveloping()) {
                            i.putExtra("size", size);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Is on Developing", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
                return false;
            });
        });

        Newgame.setOnClickListener(view -> {
            Intent newgame = new Intent(MainActivity.this, Newgame.class);
            startActivity(newgame);
        });
        Continue.setOnClickListener(view -> {
            if (sharedPreferences.getInt("size", -1) > 0) {
                Intent continue_ = new Intent(MainActivity.this, Continue_.class);
                startActivity(continue_);
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.newgamefirst_thencontinue), Toast.LENGTH_SHORT).show();
            }
        });
        Highscore.setOnClickListener(view -> {
            Intent highscore = new Intent(MainActivity.this, Highscore.class);
            startActivity(highscore);
        });
        Aboutus_privacy.setOnClickListener(view -> {
            Intent highscore = new Intent(MainActivity.this, about_us_privacy_policy.class);
            startActivity(highscore);
        });
        Exit.setOnClickListener(view -> {
            switch (countexit){
                case 0:
                    countexit++;
                    Toast.makeText(this, ""+String.valueOf(R.string.confirm_exit), Toast.LENGTH_SHORT).show();
                    timeexit1= Calendar.getInstance().getTimeInMillis();
                    break;
                case 1:
                    if(Calendar.getInstance().getTimeInMillis()-timeexit1<5000){
                         finish();
                    }else{
                        countexit=0;
                        Toast.makeText(this, String.valueOf(R.string.confirm_exit), Toast.LENGTH_SHORT).show();
                        timeexit1=Calendar.getInstance().getTimeInMillis();
                    }
            }
        });
    }
    static int countexit=0;
    static long timeexit1;
    public boolean isOndeveloping(){
        return size == 17 || size == 13 || size == 19 || size == 11;
    }
}
