
package com.devboriselkin.MyHungryCat.MenuFolder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.devboriselkin.MyHungryCat.Levels.BonusLevelOne;
import com.devboriselkin.MyHungryCat.Levels.Level_9_test_activity;
import com.devboriselkin.MyHungryCat.Levels.Level_five;
import com.devboriselkin.MyHungryCat.Levels.Level_four;
import com.devboriselkin.MyHungryCat.Levels.Level_one;
import com.devboriselkin.MyHungryCat.Levels.Level_six;
import com.devboriselkin.MyHungryCat.Levels.Level_three;
import com.devboriselkin.MyHungryCat.Levels.Level_two;
import com.devboriselkin.MyHungryCat.Media.MusicPlayer;
import com.devboriselkin.MyHungryCat.Media.SoundPlayer;
import com.devboriselkin.MyHungryCat.R;


public class MainActivity extends AppCompatActivity {
    public static String KEY="ggf";
    public static String KEY2="ggffawfgcc";
    public static String FIRST_LAUNCH_KEY ="ggfdff";
    Button newGame, chooseLevel, history, achievements, credits;
    static Dialog dialog, dialogInfo;
    TextView textInfo;
    Button infoButton;
    TextView text1, text2;
    Button back, ok, level1, level2, level3, level4, level5, level6, level7, level8, level9, level10, level11, level12,  close;
    ImageView allLevelsAchievement, allDeathsAchievement, massKillAchievement, sheepAchievement;
    TextView death_achievement_name, textmassKill, textSheep;
    ImageView makeSound;
    ProgressBar progressBar;
    Button bonusLevel;
    TextView achievementsCounter;
    TextView achievementsAmount;
    int currentAmountOfAchievements;
    Button progress_button, progress_button2;
    int check;
    int a;
    float dX, dY;
    int rand;
    Random random=new Random();
    float xOld, yOld;
    public static Context context;
    ImageView cat, poison, fish, icon;
    CardView cardView;
    View v;

    boolean achievemt1recieved=false;
    boolean achievemt2recieved=false;
    boolean achievemt3recieved=false;
    boolean achievemt4recieved=false;

    public static final int FISH_ITEM_CODE=1;

    public static MusicPlayer musicPlayer;
    public static SoundPlayer soundPlayer;
    public static final int IN_GAME_MUSIC_WAS_TURNED_ON_CODE=2;
    public static final int IN_GAME_MUSIC_RESTART_LEVEL_CODE=3;
    public static final int CASUAL_NOTIF_START_LEVEL_CODE =4;
    public static final int CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE=5;
    public static final int CHOOSE_MENU_CODE=6;
    public static final int OPEN_LEVELS_DIALOG=7;


    public static final String LEVEL_2_UNLOCK_KEY="two_aejbfshvrjg";
    public static final String LEVEL_3_UNLOCK_KEY="three_awdhawdawdd";
    public static final String LEVEL_4_UNLOCK_KEY="four_awdawdawdawdaw";
    public static final String LEVEL_5_UNLOCK_KEY="five_awdawdawgr6uj";
    public static final String LEVEL_6_UNLOCK_KEY="six_oiutyyrewddc";
    public static final String ALL_LEVELS_ARE_PASSED_KEY="all_kkiytgehbf";
    public static final String CAT_KILLED_LEVEL_1="catKilled1_awudhiagfseg";
    public static final String CAT_KILLED_LEVEL_2="catKilled2_tyyhgfdcccdd";
    public static final String CAT_KILLED_LEVEL_3="catKilled3_zxczdfzghjko";
    public static final String CAT_KILLED_LEVEL_4="catKilled4_yuitrtyesgsv";
    public static final String CAT_KILLED_LEVEL_5="catKilled5_rsgdrgwegewg";
    public static final String CAT_KILLED_LEVEL_6="catKilled6_xcvfdbxfbxfb";
    public static final String MASS_KILL_COMPLETED="killMass_awodaiwfhiae";
    public static final String CAT_MUTANT="mutant_werawedsafhjiuytfghy";
    SharedPreferences mySharedPref;
    Intent intent;


    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_main);
        checkMusic();
        initUI();
        initAchievements();
    }

    private void initAchievements() {

        int massKill;
        mySharedPref=getSharedPreferences(KEY, MODE_PRIVATE);
        a=mySharedPref.getInt(ALL_LEVELS_ARE_PASSED_KEY, 123);
        if(a==123){
        }else if(a==77){
            achievemt1recieved=true;
            currentAmountOfAchievements++;
        }
        int d1, d2, d3, d4, d5, d6;
        d1=mySharedPref.getInt(CAT_KILLED_LEVEL_1, 0);
        d2=mySharedPref.getInt(CAT_KILLED_LEVEL_2, 0);
        d3=mySharedPref.getInt(CAT_KILLED_LEVEL_3, 0);
        d4=mySharedPref.getInt(CAT_KILLED_LEVEL_4, 0);
        d5=mySharedPref.getInt(CAT_KILLED_LEVEL_5, 0);
        d6=mySharedPref.getInt(CAT_KILLED_LEVEL_6, 0);
        if(d1==1&&d2==1&&d3==1&&d4==1&&d5==1&&d6==1){
            achievemt2recieved=true;
            currentAmountOfAchievements++;
        }
        massKill=mySharedPref.getInt(MASS_KILL_COMPLETED, 123);
        if(massKill==123){
        }else if(massKill==888){
            achievemt3recieved=true;
            currentAmountOfAchievements++;
        }
        int mutant=mySharedPref.getInt(CAT_MUTANT, 0);
        if(mutant==0){
        }else if(mutant==1){
            achievemt4recieved=true;
            currentAmountOfAchievements++;
        }


    }

    private void checkMusic() {
        musicLaunch();
        Intent intent=getIntent();
        if(intent==null){

        }
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check== IN_GAME_MUSIC_WAS_TURNED_ON_CODE){
            //musicPlayer.stopInGameMusic();
        }else if(check==OPEN_LEVELS_DIALOG){
            levelsDialog();
        }
    }

    private void firstLaunchNotification() {
        mySharedPref=getSharedPreferences(KEY2, MODE_PRIVATE);
        a=mySharedPref.getInt(FIRST_LAUNCH_KEY, 333);
        if(a==333){
            makeInfoDialogWindow2(getString(R.string.welcome),getString(R.string.greeting),getString(R.string.okay_okay));
            mySharedPref=getSharedPreferences(KEY2, MODE_PRIVATE);
            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(FIRST_LAUNCH_KEY, 123);
            mEditor.apply();
        }
    }

    private void initUI() {
        context=MainActivity.this;
        firstLaunchNotification();
        newGame=findViewById(R.id.new_game);
        newGame.setOnClickListener(startNewGame());
        chooseLevel=findViewById(R.id.choose_level);
        chooseLevel.setOnClickListener(goToLevels());
        v=findViewById(android.R.id.content);
        cat=findViewById(R.id.cat);
        cat.setOnClickListener(catClicked());
        fish=findViewById(R.id.fish);
        history=findViewById(R.id.game_history);
        fish.setOnTouchListener(touch(fish, FISH_ITEM_CODE));
        achievements=findViewById(R.id.achievements);
        achievements.setOnClickListener(launchAchievements());
        credits=findViewById(R.id.credits);
        credits.setOnClickListener(openCredits());



        progress_button=findViewById(R.id.progress_button);
        progress_button.setOnClickListener(getAllProgress());
        progress_button2=findViewById(R.id.progress_button2);
        progress_button2.setOnClickListener(test2());
        progress_button.setVisibility(View.INVISIBLE);
        progress_button2.setVisibility(View.INVISIBLE);


        checkHistoryPageAvailable();
    }

    @NonNull
    private View.OnClickListener test2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeInfoDialogWindow2(getString(R.string.welcome),getString(R.string.greeting),getString(R.string.okay_okay));
            }
        };
    }

    @NonNull
    private View.OnClickListener openCredits() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.credits_popup);
                makeSound=dialog.findViewById(R.id.make_sound);
                makeSound.setOnClickListener(makeRandomSound());

                back=dialog.findViewById(R.id.close);
                back.setOnClickListener(close());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        };
    }

    @NonNull
    private View.OnClickListener makeRandomSound() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPlayer.makeRandomSound();
            }
        };
    }

    @NonNull
    private View.OnClickListener launchAchievements() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setCanceledOnTouchOutside(false);
                //dialog.setOnKeyListener(dialogOnBackPressed());
                dialog.setContentView(R.layout.achievements_popup);
                allLevelsAchievement=dialog.findViewById(R.id.all_levels_achievement);
                allDeathsAchievement=dialog.findViewById(R.id.all_deaths_achievement);
                massKillAchievement=dialog.findViewById(R.id.mass_killer_achievement);
                sheepAchievement=dialog.findViewById(R.id.mutant_achievement);
                textSheep=dialog.findViewById(R.id.text_achievement_cat_mutant);
                textmassKill=dialog.findViewById(R.id.text_achievement_mass_killer);
                death_achievement_name=dialog.findViewById(R.id.text_achievement_death);


                if(achievemt1recieved){
                    allLevelsAchievement.setImageDrawable(getResources().getDrawable(R.drawable.all_levels));
                }
                if(achievemt2recieved){
                    allDeathsAchievement.setImageDrawable(getResources().getDrawable(R.drawable.all_deaths));
                    death_achievement_name.setText(R.string.murder);
                }
                if(achievemt3recieved){
                    massKillAchievement.setImageDrawable(getResources().getDrawable(R.drawable.mass_killer));
                    textmassKill.setText(R.string.blow_up);
                }
                if(achievemt4recieved){
                    sheepAchievement.setImageDrawable(getResources().getDrawable(R.drawable.woolf_in_sheep_clothing));
                    textSheep.setText(R.string.find_monster);
                }
                progressBar=dialog.findViewById(R.id.progressBar);
                achievementsCounter=dialog.findViewById(R.id.achievements_counter);
                progressBar.setProgress(currentAmountOfAchievements);
                achievementsCounter.setText(" "+currentAmountOfAchievements);



                back=dialog.findViewById(R.id.buttonOkay);
                back.setOnClickListener(dialog_dismiss());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        };
    }

    private void checkHistoryPageAvailable() {
        mySharedPref=getSharedPreferences(KEY, MODE_PRIVATE);
        a=mySharedPref.getInt(ALL_LEVELS_ARE_PASSED_KEY, 123);
        if(a==123){
            history.setOnClickListener(passAllLevels());
            history.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
        }else if(a==77){
            history.setBackground(getResources().getDrawable(R.drawable.button_circle_3));
            history.setOnClickListener(loadHistoryPage());
        }
    }

    @NonNull
    private View.OnClickListener getAllProgress() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
                SharedPreferences.Editor mEditor=mySharedPref.edit();
                mEditor.putInt(MainActivity.LEVEL_2_UNLOCK_KEY, 22);
                mEditor.putInt(MainActivity.LEVEL_3_UNLOCK_KEY, 33);
                mEditor.putInt(MainActivity.LEVEL_4_UNLOCK_KEY, 44);
                mEditor.putInt(MainActivity.LEVEL_5_UNLOCK_KEY, 55);
                mEditor.putInt(MainActivity.LEVEL_6_UNLOCK_KEY, 66);
                mEditor.putInt(MainActivity.ALL_LEVELS_ARE_PASSED_KEY, 77);
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_1,1);
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_2,1);
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_3,1);
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_4,1);
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_5,1);
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_6,1);
                mEditor.putInt(MainActivity.MASS_KILL_COMPLETED, 888);
                mEditor.putInt(MainActivity.CAT_MUTANT, 1);
                mEditor.apply();
                checkHistoryPageAvailable();
            }
        };
    }

    @NonNull
    private View.OnClickListener catClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomCatMeowSound();
            }
        };
    }

    @NonNull
    private View.OnClickListener loadHistoryPage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setCanceledOnTouchOutside(false);
                //dialog.setOnKeyListener(dialogOnBackPressed());
                dialog.setContentView(R.layout.history_popup);
                achievementsAmount = dialog.findViewById(R.id.achievements_amount);
                bonusLevel = dialog.findViewById(R.id.bonusLevelButton);
                achievementsAmount.setText(" "+currentAmountOfAchievements);
                if(currentAmountOfAchievements>=3){
                    achievementsAmount.setTextColor(Color.BLUE);
                    bonusLevel.setBackground(getResources().getDrawable(R.drawable.blue_button_style));
                    bonusLevel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(MainActivity.this, BonusLevelOne.class);
                            intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                            startActivity(intent);
                        }
                    });
                }else{
                    bonusLevel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            makeText(getString(R.string.awdawdd));
                        }
                    });
                }
                back=dialog.findViewById(R.id.buttonOkay);
                back.setOnClickListener(dialog_dismiss());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        };
    }

    @NonNull
    private View.OnClickListener dialog_dismiss() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        };
    }

    @NonNull
    private View.OnClickListener passAllLevels() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeLittleInfoWindow(getString(R.string.tgrfv),getString(R.string.tyhrgrfs));
            }
        };
    }




    private void musicLaunch() {
        if(musicPlayer!=null){
            musicPlayer.release();
            musicPlayer=null;
        }
        if(soundPlayer!=null){
            soundPlayer.release();
            soundPlayer=null;
        }
        musicPlayer =new MusicPlayer(this, R.raw.bensound_beyondtheline, R.raw.tranquility, R.raw.forest_sounds);
        musicPlayer.initMusicInMenu();
        musicPlayer.initMusicInGame();
        soundPlayer=new SoundPlayer(this,R.raw.cat_sad1, R.raw.cat_sad2, R.raw.cat_sad3, R.raw.meow, R.raw.level_win, R.raw.fail, R.raw.poisoning, R.raw.eating1, R.raw.eating2, R.raw.eating3, R.raw.eating4, R.raw.cat_before_death, R.raw.super_angry, R.raw.cat_feeling_bad, R.raw.cat_loud,//15
                R.raw.purring, R.raw.cash_register, R.raw.graffiti, R.raw.moving_bin, R.raw.moving_door, R.raw.bin_pushed, R.raw.hb1,R.raw.hb2,R.raw.hb3,R.raw.hb4,
                R.raw.total_victory, R.raw.wh1, R.raw.wh2_plus_dwarf_hit, R.raw.wh3,
                R.raw.wh4,R.raw.wh5, R.raw.wh6, R.raw.totally_broken, R.raw.long_eating, R.raw.mole3, R.raw.mole5, R.raw.coin_drop, R.raw.item_sold, R.raw.mouse, R.raw.boom,
                R.raw.chest_opening, R.raw.locked_crate, R.raw.rope_burning, R.raw. rope_breaking, R.raw.hitting_wall_loud, R.raw.hitting_chest, R.raw.brick, R.raw.squirrel, R.raw.rabbit, R.raw.fox, R.raw.owl,
                R.raw.wc1, R.raw.wc2, R.raw. wc3, R.raw.tree_fall, R.raw.bush_cut, R.raw.cashier_opening, R.raw.bark);
        musicPlayer.startBackgroundMusic();
    }

//    public static void launchSounds(){
//        soundPlayer.release();
//        soundPlayer=null;
//
//        soundPlayer=new SoundPlayer(context,R.raw.cat_sad1, R.raw.cat_sad2, R.raw.cat_sad3, R.raw.meow, R.raw.level_win, R.raw.fail, R.raw.poisoning, R.raw.eating1, R.raw.eating2, R.raw.eating3, R.raw.eating4, R.raw.cat_before_death, R.raw.super_angry, R.raw.cat_feeling_bad, R.raw.cat_loud,//15
//                R.raw.purring, R.raw.cash_register, R.raw.graffiti, R.raw.moving_bin, R.raw.moving_door, R.raw.bin_pushed, R.raw.hb1,R.raw.hb2,R.raw.hb3,R.raw.hb4, R.raw.total_victory, R.raw.wh1, R.raw.wh2_plus_dwarf_hit, R.raw.wh3, R.raw.wh4,R.raw.wh5, R.raw.wh6, R.raw.totally_broken, R.raw.long_eating);//19
//    }
    public static void releaseMusicInMenu(){
        if(musicPlayer!=null){
            musicPlayer.releaseMusicInMenu();
        }
    }

    @NonNull
    private View.OnClickListener goToLevels() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, LevelsMenu.class);  old
//                intent.putExtra(KEY, CHOOSE_MENU_CODE);
//                startActivity(intent);
                levelsDialog();
            }
        };
    }

    @NonNull
    private View.OnClickListener startNewGame() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPref=getSharedPreferences(KEY, MODE_PRIVATE);
                a=mySharedPref.getInt(LEVEL_2_UNLOCK_KEY, 123);
                if(a==123){
                    Intent intent=new Intent(MainActivity.this, Level_one.class);
                    intent.putExtra(KEY,CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                    startActivity(intent);
                }else if(a==22){
                    makeYesNoDialog(getString(R.string.jyuhgvf),getString(R.string.yujtrgrfsdfrgt),getString(R.string.rthgrfsd),getString(R.string.tyrtgefsdc));
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
    public void onBackPressed() {
        dialog=new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.game_popup_menu2);
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        text1.setText(R.string.ujtrhgtefdfrgth);
        text2.setText(R.string.rdfsvdwewef);
        back=dialog.findViewById(R.id.btnBack);
        ok=dialog.findViewById(R.id.btnOk);
        icon=dialog.findViewById(R.id.img1);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.sad_cat_big2));
        back.setText(R.string.ergewrgerg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setText(R.string.wefwefewf);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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

    public void makeInfoDialogWindow(String title, String text, String buttonPositive){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.game_popup1);
       // dialog.setCanceledOnTouchOutside(false);
       // dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonOkay);
        back.setText(buttonPositive);
        //ImageView imageView=new ImageView(getApplicationContext());
        back.setOnClickListener(close());
        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void makeInfoDialogWindow2(String title, String text, String buttonPositive){
        dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.game_popup_228);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonOkay);
        back.setText(buttonPositive);
        //ImageView imageView=new ImageView(getApplicationContext());
        back.setOnClickListener(close());
        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    @NonNull
    private DialogInterface.OnKeyListener dialogOnBackPressed() {
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true; }
                return false;
            }
        };
    }
    @NonNull
    private static View.OnClickListener close() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }
    @NonNull
    private static View.OnClickListener close2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.dismiss();
            }
        };
    }
    public void makeLittleInfoWindow(String text, String ButtonText){
        dialogInfo=new Dialog(this);
        dialogInfo.setContentView(R.layout.info_popup);
        dialogInfo.setCanceledOnTouchOutside(false);
        dialogInfo.setOnKeyListener(dialogOnBackPressed());
        textInfo = dialogInfo.findViewById(R.id.text1);
        infoButton=dialogInfo.findViewById(R.id.button1);
        infoButton.setText(ButtonText);
        infoButton.setOnClickListener(close2());
        textInfo.setText(text);
        dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogInfo.show();
    }
    public void levelsDialog(){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.menu_levels_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        back=dialog.findViewById(R.id.close);
        back.setOnClickListener(close());
        TextView header;

        level1=dialog.findViewById(R.id.level_1);
        level2=dialog.findViewById(R.id.level_2);
        level3=dialog.findViewById(R.id.level_3);
        level4=dialog.findViewById(R.id.level_4);
        level5=dialog.findViewById(R.id.level_5);
        level6=dialog.findViewById(R.id.level_6);
        header=dialog.findViewById(R.id.header);
        level7=dialog.findViewById(R.id.level_7);
        level8=dialog.findViewById(R.id.level_8);
        level9=dialog.findViewById(R.id.level_9);
        level10=dialog.findViewById(R.id.level_10);
        level11=dialog.findViewById(R.id.level_11);
        level12=dialog.findViewById(R.id.level_12);
        level11.setVisibility(View.INVISIBLE);
        level12.setVisibility(View.INVISIBLE);

        level7.setOnClickListener(levelInDevelopment());
        level8.setOnClickListener(levelInDevelopment());
        level9.setOnClickListener(levelInDevelopment());
        level10.setOnClickListener(levelInDevelopment());

        level1.setOnClickListener(loadLevel(1));
        level2.setOnClickListener(loadLevel(2));
        level3.setOnClickListener(loadLevel(3));
        level4.setOnClickListener(loadLevel(4));
        level5.setOnClickListener(loadLevel(5));
        level6.setOnClickListener(loadLevel(6));

        mySharedPref=getSharedPreferences(KEY, MODE_PRIVATE);
        a=mySharedPref.getInt(LEVEL_2_UNLOCK_KEY, 123);
        if(a==123){
            level2.setOnClickListener(levelLocked());
            level2.setBackground(getResources().getDrawable(R.drawable.button_circle_3_locked));
        }else if(a==22){
            level2.setOnClickListener(loadLevel(2));
        }

        a=mySharedPref.getInt(LEVEL_3_UNLOCK_KEY, 123);
        if(a==123){
            level3.setOnClickListener(levelLocked());
            level3.setBackground(getResources().getDrawable(R.drawable.button_circle_3_locked));
        }else if(a==33){
            level3.setOnClickListener(loadLevel(3));
        }

        a=mySharedPref.getInt(LEVEL_4_UNLOCK_KEY, 123);
        if(a==123){
            level4.setOnClickListener(levelLocked());
            level4.setBackground(getResources().getDrawable(R.drawable.button_circle_3_locked));
        }else if(a==44){
            level4.setOnClickListener(loadLevel(4));
        }

        a=mySharedPref.getInt(LEVEL_5_UNLOCK_KEY, 123);
        if(a==123){
            level5.setOnClickListener(levelLocked());
            level5.setBackground(getResources().getDrawable(R.drawable.button_circle_3_locked));
        }else if(a==55){
            level5.setOnClickListener(loadLevel(5));
        }

        a=mySharedPref.getInt(LEVEL_6_UNLOCK_KEY, 123);
        if(a==123){
            level6.setOnClickListener(levelLocked());
            level6.setBackground(getResources().getDrawable(R.drawable.button_circle_3_locked));
        }else if(a==66){
            level6.setOnClickListener(loadLevel(6));
        }

        a=mySharedPref.getInt(ALL_LEVELS_ARE_PASSED_KEY, 123);
        if(a==123){
            //nothing
        }else if(a==77){
            header.setText(R.string.hrtgerfrgthrgtrfd);
        }
        //////////////////////////////////////////////////////////////////////////////
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @NonNull
    private View.OnClickListener levelInDevelopment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //makeLittleInfoWindow("This level is in development.","Okay");
            }
        };
    }

    @NonNull
    private View.OnClickListener levelLocked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //makeLittleInfoWindow("You have to unlock this level first","Okay");
                //Snackbar.make(v,"awd",Snackbar.LENGTH_LONG).show();
            }
        };
    }

    @NonNull
    private View.OnClickListener loadLevel(final int level) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (level){
                    case 1:
                        intent=new Intent(MainActivity.this, Level_one.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(MainActivity.this, Level_two.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 3:
                        intent=new Intent(MainActivity.this, Level_three.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(MainActivity.this, Level_four.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 5:
                        intent=new Intent(MainActivity.this, Level_five.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 6:
                        intent=new Intent(MainActivity.this, Level_six.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    case 9:
                        intent=new Intent(MainActivity.this, Level_9_test_activity.class);
                        intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                        startActivity(intent);
                        break;
                    default:
                }
            }
        };
    }
    public View.OnTouchListener touch(final ImageView imageView, final int itemCode) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MovementUltimate(event, imageView, itemCode);
            }
        };
    }
    @Nullable
    private Boolean MovementUltimate(MotionEvent event, ImageView imageView, int itemCode) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            dX= imageView.getX()-event.getRawX();
            dY= imageView.getY()-event.getRawY();
            xOld=imageView.getX();
            yOld=imageView.getY();
        } else if(event.getAction()==MotionEvent.ACTION_MOVE){
            imageView.animate()
                    .x(event.getRawX()+dX)
                    .y(event.getRawY()+dY)
                    .setDuration(0)
                    .start();
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            switch (itemCode){
                case FISH_ITEM_CODE:
                    fishOverlapAction(imageView);
                    break;

                default:makeText("wtf");
            }
        } else{return false;}
        return true;
    }

    private void fishOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            soundPlayer.makeLongEatingSound();
        }
    }
    private boolean wereOverlapped(View view, View viewActive) {
        int viewHeightDivided=view.getHeight()/4;
        int viewWidthDivided=view.getWidth()/4;
        if(viewActive.getY()+viewActive.getHeight()>view.getY()+viewHeightDivided&&viewActive.getY()<view.getY()+view.getHeight()-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>view.getX()+viewWidthDivided&&viewActive.getX()<view.getX()+view.getWidth()-viewWidthDivided){return true;}else {return false;}
    }

    private void makeText(String a) {
        Toast.makeText(getBaseContext(), a,Toast.LENGTH_SHORT ).show();
    }

    public void makeYesNoDialog(String title, String text, String buttonPositive, String buttonNegative){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.yes_no_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.btnBack);
        back.setText(buttonNegative);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.btnOk);
        ok.setText(buttonPositive);
        ok.setOnClickListener(cleanProgress());

        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @NonNull
    private View.OnClickListener cleanProgress() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
                SharedPreferences.Editor mEditor=mySharedPref.edit();
                mEditor.clear();
                mEditor.apply();

                Intent intent=new Intent(MainActivity.this, Level_one.class);
                intent.putExtra(KEY,CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE);
                startActivity(intent);
            }
        };
    }
    public void randomCatMeowSound(){
        rand=random.nextInt(100);
        if(rand<=25){
            soundPlayer.makeMeowSound1();
        }else if(rand>25&&rand<=50){
            soundPlayer.makeMeowSound2();
        }else if(rand>50&&rand<75){
            soundPlayer.makeMeowSound3();
        }else{
            soundPlayer.makeMeowSound4();
        }
    }
}





























//                int d1, d2, d3, d4, d5, d6;
//                int massKill;
//                mySharedPref=getSharedPreferences(KEY, MODE_PRIVATE);
//                a=mySharedPref.getInt(ALL_LEVELS_ARE_PASSED_KEY, 123);
//                if(a==123){
//                }else if(a==77){
//                    allLevelsAchievement.setImageDrawable(getDrawable(R.drawable.all_levels));
//                }
//
//                d1=mySharedPref.getInt(CAT_KILLED_LEVEL_1, 0);
//                d2=mySharedPref.getInt(CAT_KILLED_LEVEL_2, 0);
//                d3=mySharedPref.getInt(CAT_KILLED_LEVEL_3, 0);
//                d4=mySharedPref.getInt(CAT_KILLED_LEVEL_4, 0);
//                d5=mySharedPref.getInt(CAT_KILLED_LEVEL_5, 0);
//                d6=mySharedPref.getInt(CAT_KILLED_LEVEL_6, 0);
//
//                if(d1==1&&d2==1&&d3==1&&d4==1&&d5==1&&d6==1){
//                    allDeathsAchievement.setImageDrawable(getDrawable(R.drawable.all_deaths));
//                    death_achievement_name.setText("Murder cat in every level");
//
//                }
//
//                massKill=mySharedPref.getInt(MASS_KILL_COMPLETED, 123);
//                if(massKill==123){
//
//                }else if(massKill==888){
//                    massKillAchievement.setImageDrawable(getDrawable(R.drawable.mass_killer));
//                    textmassKill.setText("Blow up more than 2 creatures at a time");
//                }
//
//                int mutant=mySharedPref.getInt(CAT_MUTANT, 0);
//                if(mutant==0){
//
//                }else if(mutant==1){
//                    sheepAchievement.setImageDrawable(getDrawable(R.drawable.woolf_in_sheep_clothing));
//                    textSheep.setText("Find a true monster");
//                }
//
//                progressBar=dialog.findViewById(R.id.progressBar);
//                achievementsCounter=dialog.findViewById(R.id.achievements_counter);

