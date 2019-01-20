package com.devboriselkin.MyHungryCat.Levels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_9_test_activity extends AppCompatActivity {
    int check;
    Button wh1, wh2, wh3, wh4, wh5, wh6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_9_test_activity);

        initUI();

    }

    private void initUI() {
        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE){
            MainActivity.musicPlayer.stopBackgroundMusic();
            //MainActivity.musicPlayer.startInGameMusic();

        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            //just nothing occurs
        }

        wh1 = findViewById(R.id.wh1);
        wh2 = findViewById(R.id.wh2);
        wh3 = findViewById(R.id.wh3);
        wh4 = findViewById(R.id.wh4);
        wh5 = findViewById(R.id.wh5);
        wh6 = findViewById(R.id.wh6);

//        wh1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.soundPlayer.makeWoodHitSound_1();
//            }
//        });
//        wh2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.soundPlayer.makeWoodHitSound_2();
//            }
//        });
//        wh3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.soundPlayer.makeWoodHitSound_3();
//
//            }
//        });
//        wh4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.soundPlayer.makeWoodHitSound_4();
//            }
//        });
//        wh5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               MainActivity.soundPlayer.makeWoodHitSound_5();
//            }
//        });
//        wh6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.soundPlayer.makeWoodHitSound_6();
//            }
//        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Level_9_test_activity.this, MainActivity.class);
        intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
        startActivity(intent);
    }


}
