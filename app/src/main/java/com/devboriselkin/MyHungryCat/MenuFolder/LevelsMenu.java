package com.devboriselkin.MyHungryCat.MenuFolder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.devboriselkin.MyHungryCat.Levels.Level_9_test_activity;
import com.devboriselkin.MyHungryCat.Levels.Level_one;
import com.devboriselkin.MyHungryCat.Levels.Level_four;
import com.devboriselkin.MyHungryCat.Levels.Level_three;
import com.devboriselkin.MyHungryCat.Levels.Level_two;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class LevelsMenu extends AppCompatActivity {
Button level1, level2, level3, level4, level5, level6, level9;
    Intent intent;
    int check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_levels_menu);

        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CHOOSE_MENU_CODE){
            musicPlayer.resumeBackgroundMusic();
        }

        level1=findViewById(R.id.level_1);
        level2=findViewById(R.id.level_2);
        level3=findViewById(R.id.level_3);
        level4=findViewById(R.id.level_4);
        level5=findViewById(R.id.level_5);
        level9=findViewById(R.id.level_9);

        level1.setOnClickListener(loadLevel(1));
        level2.setOnClickListener(loadLevel(2));
        level3.setOnClickListener(loadLevel(3));
        level4.setOnClickListener(loadLevel(4));
        level5.setOnClickListener(loadLevel(5));
        level9.setOnClickListener(loadLevel(9));


    }

    @NonNull
    private View.OnClickListener loadLevel(final int level) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (level){
                    case 1:
                        intent=new Intent(LevelsMenu.this, Level_one.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(LevelsMenu.this, Level_two.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 3:
                        intent=new Intent(LevelsMenu.this, Level_three.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(LevelsMenu.this, Level_four.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 5:
                        intent=new Intent(LevelsMenu.this, Level_three.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 6:
                        intent=new Intent(LevelsMenu.this, Level_three.class);
                        intent.putExtra(MainActivity.KEY, 1);
                        startActivity(intent);
                        break;
                    case 9:
                        intent=new Intent(LevelsMenu.this, Level_9_test_activity.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                        default:
                }
            }
        };
    }

    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (musicPlayer != null) {
            musicPlayer.stopBackgroundMusic();
            if (isFinishing()) {
                musicPlayer.stopBackgroundMusic();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayer !=null){
            musicPlayer.resumeBackgroundMusic();
        }
    }

}
