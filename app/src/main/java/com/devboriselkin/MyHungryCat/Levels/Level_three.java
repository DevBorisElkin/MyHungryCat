package com.devboriselkin.MyHungryCat.Levels;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.ProgressBarAnimation;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_three extends AppCompatActivity {

    ImageView lid, trashBin, chest, carDoor, emptyBottle1, emptyBottle2, tunaCanOpened, bittenApple, bigRottenFish, fish, goodApple, bigFish, rottenApple, rottenFish, crap, bananaSkin, clock, bone, restart, fish2, toMenu;
    static Dialog dialog;
    TextView text1, text2;
    Button back, ok;
    public static float xOld, yOld;
    public int check;
    public int rottenFoodLimit=-1000;
    public int rottenFoodConsumed=0;
    public int lidCatOverlap=0;
    ImageView cat, poison, icon, trbOpened;
    int catClickCounter=0;
    float dX, dY;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;




    ImageView itemSpawn;
    ImageView spawnedItem;
    Random random=new Random();
    int rand=0;

    public static final int FISH_INCREMENT=300;
    public static final int BIG_FISH_INCREMENT=600;
    public static final int ROTTEN_FISH_INCREMENT=-300;
    public static final int ROTTEN_BIG_FISH_INCREMENT = -600;
    public static final int ROTTEN_APPLE_INCREMENT = -200;
    public static final int BEST_APPLE_INCREMENT=450;
    public static final int BITTEN_APPLE_INCREMENT=200;
    public static final int CRAP_INCREMENT=-200;
    public static final int CRAP_BAD_INCREMENT=-400;
    //public static final int

    public static final int FISH_ITEM_CODE=1;
    public static final int BIG_FISH_ITEM_CODE=9;
    public static final int POISON_ITEM_CODE=2;
    public static final int LID_ITEM_CODE=3;
    public static final int CHECK_VICTORY_CODE=4;
    public static final int DOOR_MOVE_CODE = 5;
    public static final int EMPTY_BOTTLE_ITEM_CODE=6;
    public static final int EMPTY_TUNA_CAN_ITEM_CODE=7;
    public static final int BANANA_SKIN_ITEM_CODE=8;
    public static final int BONE_FISH_ITEM_CODE=10;
    public static final int FOOD_ITEM_CODE =11;
    public static final int DIRTY_CLOCK_ITEM_CODE =12;

    public static final int TRB_SPAWN_CODE=555;
    public static final int BOX_SPAWN_CODE=777;

    ControlSubThread controlSubThread;
    Handler handler, handler2;

    boolean alive=true;
    CardView cardView;
    boolean fishPoisoned=false;
    boolean pushedBarrel=false;
    public int chestClickCounter=0;
    public int doorPushCounter=1;
    public int tooHighCounter =0;
    int foodWasEaten;
    boolean endWasNotLaunched=true;
    boolean endWasNotLaunched2=true;
    SharedPreferences mySharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.level_three);
        initUI();
    }

    private void initUI() {
        mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_CODE){
            makeInfoDialogWindow(getString(R.string.sesf),getString(R.string.aegrg),getString(R.string.xdv));
        }else if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE){

            makeInfoDialogWindow(getString(R.string.jthyrtge),getString(R.string.jhgre),getString(R.string.nbvcf));
            musicPlayer.stopBackgroundMusic();
            musicPlayer.releaseMusicInMenu();
            musicPlayer.startInGameMusic();
        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            //nothing here
        }


        //toMenu=findViewById(R.id.to_menu);
        //toMenu.setOnClickListener(to_menu());
        restart=findViewById(R.id.restart);
        restart.setOnClickListener(restartLevel());
        fish2=findViewById(R.id.fish2);

        crap=findViewById(R.id.crap);
        goodApple=findViewById(R.id.good_apple);
        trbOpened=findViewById(R.id.trb_opened);
        itemSpawn=findViewById(R.id.item_spawn);
        carDoor=findViewById(R.id.car_door);
        carDoor.setOnClickListener(doorPush());
        progressBar=findViewById(R.id.progressBar);
        Drawable draw=getResources().getDrawable(R.drawable.custom_progressbar);
        progressBar.setProgressDrawable(draw);
        constraintLayout=findViewById(R.id.constrainL);

        emptyBottle2=findViewById(R.id.emptyBottle2);

        bittenApple=findViewById(R.id.bittenApple);
        bigRottenFish =findViewById(R.id.spoiledBigFish);
        cat=findViewById(R.id.cat);

        cat.setOnClickListener(catClicked());

        crap.setOnTouchListener(touch(crap,CRAP_INCREMENT, FOOD_ITEM_CODE));
        fish2.setOnTouchListener(touch(fish2,FISH_INCREMENT, FISH_ITEM_CODE));
        goodApple.setOnTouchListener(touch(goodApple,BEST_APPLE_INCREMENT, FOOD_ITEM_CODE));
        bittenApple.setOnTouchListener(touch(bittenApple,BITTEN_APPLE_INCREMENT, FOOD_ITEM_CODE));
        emptyBottle2.setOnTouchListener(touch(emptyBottle2,0, EMPTY_BOTTLE_ITEM_CODE));

        bigRottenFish.setOnTouchListener(touch(bigRottenFish, ROTTEN_BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE));

        lid=findViewById(R.id.trb_lid);
        chest=findViewById(R.id.chest);
        chest.setOnClickListener(chestClick());
        lid.setOnTouchListener(touch(lid,0,LID_ITEM_CODE));
        trashBin=findViewById(R.id.trb_falling);
        trashBin.setOnClickListener(pushBin());
        trashBin.setOnLongClickListener(longPushBin());
        handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.arg1==1)
                {
                    levelOver(true);
                }
                return false;
            }
        });
    }

    @NonNull
    private View.OnClickListener catClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catClickCounter<=2){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.tjhrtg),getString(R.string.nbvc));
                    randomCatMeowSound();
                } else if(catClickCounter>=3&&catClickCounter<5){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.iuytr),getString(R.string.nbvcd));
                    randomCatMeowSound();
                }else{
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.uytrfwvebeg),getString(R.string.rrrrgfrwef));
                    randomCatMeowSound();
                }
                catClickCounter++;
            }
        };
    }

    @NonNull
    private View.OnClickListener to_menu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitToMenu();
            }
        };
    }

    private void exitToMenu() {
        dialog=new Dialog(Level_three.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText(R.string.gdde);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText(R.string.erfw);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Level_three.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText(R.string.rtgerf);
        text2.setText(R.string.gfrwed);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    @NonNull
    private View.OnClickListener restartLevel() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_three.this, Level_three.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                checkThreadsBeforeExit();
                startActivity(intent);
            }
        };
    }

    @NonNull
    private View.OnClickListener doorPush() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doorPushCounter<10){
                    carDoor.setX(carDoor.getX()+doorPushCounter);
                    MainActivity.soundPlayer.makeMovingDoorSound();
                    if(doorPushCounter<10) doorPushCounter++;
                }else if(doorPushCounter==10){
                    controlSubThread=new ControlSubThread(15,0, DOOR_MOVE_CODE);
                    controlSubThread.start();
                    MainActivity.soundPlayer.makeMoveBinSound();
                    doorPushCounter++;
                }

            }
        };
    }

    @NonNull
    private View.OnClickListener chestClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chestClickCounter==0){
                    //makeInfoDialogWindow("Pay attention","Looks like this old moisty chest is locked. Try to open it somehow", "I'll try");
                    MainActivity.soundPlayer.makeWoodHitSound_3();
                }else if(chestClickCounter>0&&chestClickCounter<4){
                    woodHitSound();
                    //makeText("knock");
                }else if(chestClickCounter==4){
                    redraw(chest, getResources().getDrawable(R.drawable.dirty_chest_broken_one));
                    woodHitSound();
                }else if(chestClickCounter>4&&chestClickCounter<6){
                    woodHitSound();
                    //makeText("knock");
                }else if(chestClickCounter==6){
                    woodHitSound();
                    redraw(chest, getResources().getDrawable(R.drawable.dirty_chest_broken_three));
                }else if(chestClickCounter==7){
                    woodHitSound();
                    // makeText("knock");
                }else if(chestClickCounter==8){
                    MainActivity.soundPlayer.makeChestBrokenSound();
                    redraw(chest, getResources().getDrawable(R.drawable.dirty_chest_broken_two));
                    launchRandom2(chest.getX()-50,chest.getY()+10,chest.getX()-80,chest.getY()+25,chest.getX()-90,chest.getY()+60,chest.getX()-110,chest.getY()+20, BOX_SPAWN_CODE);
//                    addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),chest.getX()-50, chest.getY()+10, 80, 120);
//                    addItemProgrammatically(rottenApple, itemSpawn, ROTTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.rotten_apple2),chest.getX()-50, chest.getY()+10, 60, 60);
//                    addItemProgrammatically(bigRottenFish, itemSpawn, ROTTEN_BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_spoiled_fish),chest.getX()-50, chest.getY()+10, 80, 120);
//                    addItemProgrammatically(bigFish, itemSpawn, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish),chest.getX()-50, chest.getY()+10, 80, 120);
//                    addItemProgrammatically(bittenApple, itemSpawn, BITTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.apple),chest.getX()-50, chest.getY()+10, 60, 60);
//                    addItemProgrammatically(goodApple, itemSpawn, BEST_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.best_apple),chest.getX()-50, chest.getY()+10, 64, 64);
//                    addItemProgrammatically(rottenFish, itemSpawn, ROTTEN_FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish_spoiled),chest.getX()-50, chest.getY()+10, 80, 120);
//                    addItemProgrammatically(tunaCanOpened, itemSpawn, 0, EMPTY_TUNA_CAN_ITEM_CODE, getDrawable(R.drawable.tuna_can_opened_dirty),chest.getX()-50, chest.getY()+10, 80, 105);
//                    addItemProgrammatically(emptyBottle1, itemSpawn, 0, EMPTY_BOTTLE_ITEM_CODE, getDrawable(R.drawable.empty_bottle),chest.getX()-50, chest.getY()+10, 120, 105);
//                    addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),chest.getX()-50, chest.getY()+10, 70, 70);
//                    addItemProgrammatically(crap, itemSpawn, CRAP_BAD_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap3),chest.getX()-50, chest.getY()+10, 70, 70);
//                    addItemProgrammatically(bananaSkin, itemSpawn, 0, BANANA_SKIN_ITEM_CODE, getDrawable(R.drawable.banana_skin),chest.getX()-50, chest.getY()+10, 100, 100);
//                    addItemProgrammatically(clock, itemSpawn, 0, DIRTY_CLOCK_ITEM_CODE, getDrawable(R.drawable.dirty_clock),chest.getX()-50, chest.getY()+10, 80, 80);
//                    addItemProgrammatically(bone, itemSpawn, 0, BONE_FISH_ITEM_CODE, getDrawable(R.drawable.fish_bone),chest.getX()-50, chest.getY()+10, 80, 120);
//                    addItemProgrammatically(bone, itemSpawn, 0, BONE_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish_bone),chest.getX()-50, chest.getY()+10, 80, 120);
                }
                chestClickCounter++;
            }
        };
    }
    // for now is deprecated, but, can be useful.
//    private void launchRandom(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
//        rand=random.nextInt(100);
//        float x1Coordinate=x1;
//        float y1Coordinate=y1;
//        float x2Coordinate=x2;
//        float y2Coordinate=y2;
//        float x3Coordinate=x3;
//        float y3Coordinate=y3;
//        float x4Coordinate=x4;
//        float y4Coordinate=y4;
//
//        if(rand>=0&&rand<3){
//            addItemProgrammatically(goodApple, itemSpawn, BEST_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.best_apple),x2Coordinate, y2Coordinate, 64, 64);
//            addItemProgrammatically(bigFish, itemSpawn, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish),x4Coordinate,y4Coordinate, 80, 120);
//        } else if(rand>=3&&rand<6){
//            addItemProgrammatically(bigFish, itemSpawn, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish),x1Coordinate, y1Coordinate, 80, 120);
//            addItemProgrammatically(tunaCanOpened, itemSpawn, 0, EMPTY_TUNA_CAN_ITEM_CODE, getDrawable(R.drawable.tuna_can_opened_dirty),x2Coordinate, y2Coordinate, 70, 95);
//            addItemProgrammatically(bittenApple, itemSpawn, BITTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.apple),x3Coordinate, y3Coordinate, 60, 60);
//        }else if(rand>=6&&rand<10){
//            addItemProgrammatically(clock, itemSpawn, 0, DIRTY_CLOCK_ITEM_CODE, getDrawable(R.drawable.dirty_clock),x1Coordinate, y1Coordinate, 80, 80);
//            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x2Coordinate, y2Coordinate, 80, 120);
//            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x3Coordinate, y3Coordinate, 80, 120);
//        }else if(rand>=10&&rand<14){
//            addItemProgrammatically(crap, itemSpawn, CRAP_BAD_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap3),x1Coordinate, y1Coordinate, 70, 70);
//            addItemProgrammatically(rottenFish, itemSpawn, ROTTEN_FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish_spoiled),x2Coordinate, y2Coordinate, 80, 120);
//        }else if(rand>=14&&rand<20){
//
//            addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),x1Coordinate, y1Coordinate, 70, 70);
//            addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),x2Coordinate, y2Coordinate, 70, 70);
//            addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),x3Coordinate, y3Coordinate, 70, 70);
//            addItemProgrammatically(crap, itemSpawn, CRAP_BAD_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap3),x4Coordinate, y4Coordinate, 70, 70);
//            makeText("Shitty luck");
//        }else if(rand>=20&&rand<25){
//            addItemProgrammatically(tunaCanOpened, itemSpawn, 0, EMPTY_TUNA_CAN_ITEM_CODE, getDrawable(R.drawable.tuna_can_opened_dirty),x1Coordinate, y1Coordinate, 70, 95);
//            addItemProgrammatically(emptyBottle1, itemSpawn, 0, EMPTY_BOTTLE_ITEM_CODE, getDrawable(R.drawable.empty_bottle),x2Coordinate, y2Coordinate, 120, 105);
//        }else if(rand>=25&&rand<30){
//            addItemProgrammatically(bone, itemSpawn, 0, BONE_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish_bone),x1Coordinate, y1Coordinate, 80, 120);
//            addItemProgrammatically(bigFish, itemSpawn, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish),x2Coordinate, y2Coordinate, 80, 120);
//            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x3Coordinate, y3Coordinate, 80, 120);
//        }else if(rand>=30&&rand<34){
//            addItemProgrammatically(bananaSkin, itemSpawn, 0, BANANA_SKIN_ITEM_CODE, getDrawable(R.drawable.banana_skin),x1Coordinate, y1Coordinate, 100, 100);
//            addItemProgrammatically(goodApple, itemSpawn, BEST_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.best_apple),x2Coordinate, y2Coordinate, 64, 64);
//            addItemProgrammatically(bittenApple, itemSpawn, BITTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.apple),x3Coordinate, y3Coordinate, 60, 60);
//        }else if(rand>=34&&rand<40){
//            addItemProgrammatically(crap, itemSpawn, CRAP_BAD_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap3),y1Coordinate, y1Coordinate, 70, 70);
//            addItemProgrammatically(emptyBottle1, itemSpawn, 0, EMPTY_BOTTLE_ITEM_CODE, getDrawable(R.drawable.empty_bottle_reverse1),x2Coordinate, y2Coordinate, 120, 105);
//        }else if(rand>=40&&rand<51){
//            addItemProgrammatically(rottenFish, itemSpawn, ROTTEN_FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish_spoiled),x1Coordinate, y1Coordinate, 80, 120);
//        }else if(rand>=51&&rand<60){
//            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x1Coordinate, y1Coordinate, 80, 120);
//            addItemProgrammatically(rottenFish, itemSpawn, ROTTEN_FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish_spoiled),x2Coordinate, y2Coordinate, 80, 120);
//            addItemProgrammatically(bittenApple, itemSpawn, BITTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.apple),x3Coordinate, y3Coordinate, 60, 60);
//        }else if(rand>=60&&rand<70){
//            addItemProgrammatically(rottenApple, itemSpawn, ROTTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.rotten_apple2),x1Coordinate, y1Coordinate, 60, 60);
//            addItemProgrammatically(bigRottenFish, itemSpawn, ROTTEN_BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_spoiled_fish),x2Coordinate, y2Coordinate, 80, 120);
////            addItemProgrammatically(bigFish, itemSpawn, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getDrawable(R.drawable.big_fish),x3Coordinate, y3Coordinate, 80, 120);
//        } else if(rand>=70&&rand<76){
//            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x1Coordinate, y1Coordinate, 80, 120);
//            addItemProgrammatically(bittenApple, itemSpawn, BITTEN_APPLE_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.apple),x3Coordinate, y3Coordinate, 60, 60);
//            //addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x2Coordinate, y2Coordinate, 80, 120);
////            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x3Coordinate, y3Coordinate, 80, 120);
//        }else if(rand>=76&&rand<86){
//            addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),x1Coordinate, y1Coordinate, 70, 70);
//            addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),x2Coordinate, y2Coordinate, 70, 70);
//
//        }else if(rand>=86&&rand<90){
//            addItemProgrammatically(crap, itemSpawn, CRAP_BAD_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap3),x1Coordinate, y1Coordinate, 70, 70);
//            addItemProgrammatically(crap, itemSpawn, CRAP_BAD_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap3),x2Coordinate, y2Coordinate, 70, 70);
//
//
//        }else{
//            addItemProgrammatically(crap, itemSpawn, CRAP_INCREMENT, FOOD_ITEM_CODE, getDrawable(R.drawable.crap2),x1Coordinate, y1Coordinate, 70, 70);
//            addItemProgrammatically(emptyBottle1, itemSpawn, 0, EMPTY_BOTTLE_ITEM_CODE, getDrawable(R.drawable.empty_bottle_reverse1),x2Coordinate, y2Coordinate, 120, 105);
//            addItemProgrammatically(bananaSkin, itemSpawn, 0, BANANA_SKIN_ITEM_CODE, getDrawable(R.drawable.banana_skin),x3Coordinate, y3Coordinate, 100, 100);
//            addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getDrawable(R.drawable.fish),x4Coordinate, y4Coordinate, 80, 120);
//        }
//    }
    private void launchRandom2(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int spawn_code) {
        float x1Coordinate=x1;
        float y1Coordinate=y1;
        float x2Coordinate=x2;
        float y2Coordinate=y2;
        float x3Coordinate=x3;
        float y3Coordinate=y3;
        float x4Coordinate=x4;
        float y4Coordinate=y4;

        switch (spawn_code) {
            case TRB_SPAWN_CODE:
                addItemProgrammatically(tunaCanOpened, itemSpawn, 0, EMPTY_TUNA_CAN_ITEM_CODE, getResources().getDrawable(R.drawable.tuna_can_opened_dirty),x2Coordinate, y2Coordinate, 70, 95);
                addItemProgrammatically(bigFish, itemSpawn, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE, getResources().getDrawable(R.drawable.big_fish),x4Coordinate,y4Coordinate, 80, 120);
                break;
            case BOX_SPAWN_CODE:
                addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getResources().getDrawable(R.drawable.fish),x3Coordinate, y3Coordinate, 80, 120);
                addItemProgrammatically(fish, itemSpawn, FISH_INCREMENT, FISH_ITEM_CODE, getResources().getDrawable(R.drawable.fish),x2Coordinate, y2Coordinate, 80, 120);
                addItemProgrammatically(rottenFish, itemSpawn, ROTTEN_FISH_INCREMENT, FISH_ITEM_CODE, getResources().getDrawable(R.drawable.fish_spoiled),x1Coordinate, y1Coordinate, 80, 120);
                break;
            default:
                makeText("wtf");
        }
    }

    @NonNull
    private View.OnClickListener pushBin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pushedBarrel){
                    hitBinSound();
                }
            }
        };
    }
    @NonNull
    private View.OnLongClickListener longPushBin() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!pushedBarrel){
                    redraw(trashBin, getResources().getDrawable(R.drawable.trash_bin_pushed));
                    int height = 240;
                    int width=550;
                    float x=trashBin.getTranslationX();
                    float y=trashBin.getTranslationY();
                    trashBin.getLayoutParams().height = height;
                    trashBin.getLayoutParams().width = width;
                    trashBin.setTranslationX(x+230);
                    trashBin.setTranslationY(y+50);
                    trashBin.requestLayout();
                    MainActivity.soundPlayer.makeBinPushedSound();
                    launchRandom2(trashBin.getX(), trashBin.getY()+105,trashBin.getX()+20,
                            trashBin.getY()+115,trashBin.getX()+40, trashBin.getY()+125,trashBin.getX()+60, trashBin.getY()+110, TRB_SPAWN_CODE);
                }
                pushedBarrel=true;
                return true;}
        };
    }

    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);

    }
    public View.OnTouchListener touch(final ImageView imageView, final int foodIncrement, final int itemCode) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MovementUltimate(event, imageView, foodIncrement, itemCode);
            }
        };
    }
    @Nullable
    private Boolean MovementUltimate(MotionEvent event, ImageView imageView, int increment, int itemCode) {
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
            if(event.getRawY()+dY<constraintLayout.getHeight()/3) {
                getBack(imageView);
                if(tooHighCounter ==0){
                    makeText(getString(R.string.gferwde));
                    tooHighCounter++;
                }

            }
            switch (itemCode){
                case LID_ITEM_CODE:
                    lidOverlapAction(imageView);
                    break;
                case FISH_ITEM_CODE:
                    fishOverlapAction(imageView, increment);
                    break;
                case BIG_FISH_ITEM_CODE:
                    bigFishOverlapAction(imageView, increment);
                    break;
                case EMPTY_TUNA_CAN_ITEM_CODE:
                    emptyTunaCanOverlapAction(imageView);
                    break;
                case EMPTY_BOTTLE_ITEM_CODE:
                    emptyBottleOverlapAction(imageView);
                    break;
                case BANANA_SKIN_ITEM_CODE:
                    bananaSkinOverlapAction(imageView);
                    break;
                case BONE_FISH_ITEM_CODE:
                    boneFishOverlapAction(imageView);
                    break;
                case FOOD_ITEM_CODE:
                    foodOverlapAction(imageView, increment);
                    break;
                case DIRTY_CLOCK_ITEM_CODE:
                    dirtyCockOverlapAction(imageView);
                    break;


                default:makeText("wtf");
            }
        } else{return false;}
        return true;
    }

    private void dirtyCockOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.ddeferfe), getString(R.string.gfdwed) );
            randomCatMeowSound();
        }
    }


    private void bananaSkinOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.dfsdadv), getString(R.string.awdesfxv));
            getBack(imageView);
            randomCatMeowSound();
        }
    }

    private void makeText(String a) {
        Toast.makeText(getBaseContext(), a,Toast.LENGTH_SHORT ).show();
    }
    public void lidOverlapAction(ImageView imageView){
        if(wereOverlapped(cat, imageView)&&lidCatOverlap==0){
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.awfesbdfgf), getString(R.string.qwd));
            randomCatMeowSound();
            lidCatOverlap++;
        }else if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.grf),getString(R.string.bewv));
            randomCatMeowSound();
        } else if(wereOverlapped(trbOpened, imageView)){
            //for now nothing here
        }
    }
    private void fishOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(cat, imageView)){
            //imageView.setVisibility(View.INVISIBLE);
            redraw(imageView,getResources().getDrawable(R.drawable.fish_bone));
            imageView.setX(cat.getX()-90);
            imageView.setOnTouchListener(touch(imageView, 0 ,BONE_FISH_ITEM_CODE));
            changeProgress(increment);
            catEatingSound();
            checkCatDeath(increment);
            checkVictory(increment);
        }
    }
    private void bigFishOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(cat, imageView)){
            //imageView.setVisibility(View.INVISIBLE);
            redraw(imageView, getResources().getDrawable(R.drawable.big_fish_bone));
            imageView.setX(cat.getX()-90);
            imageView.setOnTouchListener(touch(imageView,0, BONE_FISH_ITEM_CODE));
            changeProgress(increment);
            MainActivity.soundPlayer.makeLongEatingSound();
            checkCatDeath(increment);
            checkVictory(increment);
        }
    }
    private void boneFishOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.dbdfv), getString(R.string.aewa));
            getBack(imageView);
            randomCatMeowSound();
        }
    }
    private void foodOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            changeProgress(increment);
            catEatingSound();
            checkCatDeath(increment);
            checkVictory(increment);
        }
    }

    private void checkCatDeathOld(int increment) {
        if(increment<0){
            rottenFoodConsumed+=increment;
            if(rottenFoodConsumed<-400&&rottenFoodConsumed>=-700){
                redraw(cat, getResources().getDrawable(R.drawable.cat_feeling_bad));
            }
            if(rottenFoodConsumed<-1200){
                redraw(cat, getResources().getDrawable(R.drawable.cat_close_to_death2));
            }
            if(rottenFoodConsumed<=rottenFoodLimit){
                alive=false;
                redraw(cat, getResources().getDrawable(R.drawable.poisoned_cat));
                levelOver(false);
            }
        }
    }
    private void checkCatDeath(int increment) {
        if(increment<0){
            rottenFoodConsumed+=increment;
            if(rottenFoodConsumed<=-200&&rottenFoodConsumed>=-400){
                redraw(cat, getResources().getDrawable(R.drawable.cat_feeling_bad));
            }
            if(rottenFoodConsumed<=-400){
                redraw(cat, getResources().getDrawable(R.drawable.cat_close_to_death2));
            }
            if(rottenFoodConsumed<=rottenFoodLimit){
                alive=false;
                redraw(cat, getResources().getDrawable(R.drawable.poisoned_cat));
                levelOver(false);
            }
        }
    }

    private void changeProgress(int progress) {
        int from = progressBar.getProgress();
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, from, from + progress);
        anim.setDuration(1000);
        foodWasEaten+=progress;
        progressBar.startAnimation(anim);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(progressBar.getProgress()!=foodWasEaten){
                    progressBar.setProgress(foodWasEaten);
                    checkVictory2();
                }
            }
        }).start();
    }
    public void checkVictory2() {
        if (progressBar.getProgress() >= 2000&&endWasNotLaunched) {
            endWasNotLaunched=false;
            Message msg = new Message();
            msg.arg1=1;
            handler2.sendMessage(msg);
        }
    }

    private void emptyTunaCanOverlapAction(ImageView imageView){
        if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.fgnfgb), getString(R.string.dfbdwe));
            getBack(imageView);
            randomCatMeowSound();
        }
    }
    private void emptyBottleOverlapAction(ImageView imageView){
        if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.ewwe), getString(R.string.bbcdd));
            getBack(imageView);
            randomCatMeowSound();
        }
    }

    private boolean wereOverlapped(View view, View viewActive) {
        int viewHeightDivided=view.getHeight()/4;
        int viewWidthDivided=view.getWidth()/4;
        if(viewActive.getY()+viewActive.getHeight()>view.getY()+viewHeightDivided&&viewActive.getY()<view.getY()+view.getHeight()-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>view.getX()+viewWidthDivided&&viewActive.getX()<view.getX()+view.getWidth()-viewWidthDivided){return true;}else {return false;}
    }
    public void checkVictory(int increment){
        if(progressBar.getProgress()+increment>=2000){
            //checkThreadsBeforeExit();
            levelOver(true);
        } else if(!alive){
            //checkThreadsBeforeExit();
            levelOver(false);
        }
    }
    public void makeInfoDialogWindow(String title, String text, String buttonPositive){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.game_popup1);
        //dialog.setCanceledOnTouchOutside(false);
        //dialog.setOnKeyListener(dialogOnBackPressed());
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
    private void progressBarIncrement(int diff) {
        progressBar.incrementProgressBy(diff);
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
                //getBack(imageView);
            }
        };
    }
    private void getBack(ImageView imageView) {
        imageView.animate().x(xOld).y(yOld).setDuration(0).start();
    }
    public void levelOver(Boolean win){
        if(endWasNotLaunched2){
            endWasNotLaunched2=false;
            checkThreadsBeforeExit();
            dialog=new Dialog(this);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(dialogOnBackPressed());
            dialog.setContentView(R.layout.game_popup_menu2);
            text1=dialog.findViewById(R.id.text1);
            text2=dialog.findViewById(R.id.text2);
            ok=dialog.findViewById(R.id.btnOk);
            back=dialog.findViewById(R.id.btnBack);
            icon=dialog.findViewById(R.id.img1);
            back.setText(R.string.ythtyh);
            back.setOnClickListener(toMainMenu());

            if(win){
                SharedPreferences.Editor mEditor=mySharedPref.edit();
                mEditor.putInt(MainActivity.LEVEL_4_UNLOCK_KEY, 44);
                mEditor.apply();

                MainActivity.soundPlayer.makeVictorySound();
                text1.setText(R.string.fgbfgb);
                text2.setText(R.string.wfewef);
                ok.setText(R.string.cgbfs);
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_boss));
                ok.setOnClickListener(toLevelFour());
            }else{
                int d1, d2, d3, d4, d5, d6;
                d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
                d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
                d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
                d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
                d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
                d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
                if(d1==1&&d2==1&&d3==0&&d4==1&&d5==1&&d6==1){
                    makeText2();
                }

                SharedPreferences.Editor mEditor=mySharedPref.edit();
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_3, 1);
                mEditor.apply();

                MainActivity.soundPlayer.makeFailureSound();
                text1.setText(R.string.uyjtyt);
                text2.setText(R.string.aaas);
                ok.setText(R.string.cgbcg);
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));
                ok.setOnClickListener(startOver());
                cardView=dialog.findViewById(R.id.cardView);
                cardView.setCardBackgroundColor(getResources().getColor(R.color.pinkNotif));
                ok.setBackground(getResources().getDrawable(R.drawable.pink_button));
                back.setBackground(getResources().getDrawable(R.drawable.pink_button));


            }
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
    @NonNull
    private View.OnClickListener toMainMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_three.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener startOver() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_three.this, Level_three.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener toLevelFour() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Level_three.this, Level_four.class);
//                intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_CODE);
//                startActivity(intent);
                Intent intent=new Intent(Level_three.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.OPEN_LEVELS_DIALOG);
                startActivity(intent);
            }
        };
    }
    private void addItemProgrammatically(ImageView image, ImageView bindTo, int increment, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        int addTo;
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) bindTo.getLayoutParams();
        image = new ImageView(this);
        image.setOnTouchListener(touch(image,increment, itemCode));
        image.setImageDrawable(drawable);
        ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newParams.startToEnd=params.startToEnd;
        newParams.width=width;
        newParams.height=height;
        newParams.topToTop=params.topToTop;
        constraintLayout.addView(image, -1, newParams);
//        listOfImages.add(image);
//        if(itemCode==CAN_OPENER_ITEM_CODE){
//            addTo=listOfImages.size()-1;
//            CanOpenerListIndex=addTo;
//        } else if(itemCode==TUNA_CAN_ITEM_CODE){
//            addTo=listOfImages.size()-1;
//            TunaCanListIndex=addTo;
//        } else if(itemCode==FISH_ITEM_CODE){
//            addTo=listOfImages.size()-1;
//            listOfIdFishes.add(addTo);
//        }
        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);
    }
    private void checkThreadsBeforeExit() {
        if(controlSubThread!=null){
            controlSubThread.stop();
            controlSubThread.destroy();
        }
    }
    @Override
    public void onBackPressed() {
        exitToMenu();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (musicPlayer != null) {
            musicPlayer.stopInGameMusic();
            if (isFinishing()) {
                musicPlayer.stopInGameMusic();
            }
        }
        if(controlSubThread!=null){
            controlSubThread.stop();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayer !=null){
            musicPlayer.resumeInGameMusic();
        }
        if(controlSubThread!=null){
            controlSubThread.resume();
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class ControlSubThread implements Runnable {
        private Thread worker;
        boolean running=false;
        boolean waited=false;
        Message msg = new Message();
        private int littleInterval, startingInterval, moveCode;
        public ControlSubThread(int littleInterval, int startingInterval, int moveCode) {
            this.littleInterval = littleInterval;
            this.startingInterval=startingInterval;
            this.moveCode=moveCode;
        }
        public void start() {
            worker = new Thread(this);
            running=true;
            worker.start();
        }
        public void stop() {
            running=false;
        }
        public void destroy(){
            worker.interrupt();
//            checkVictory();
        }
        public void resume() {
            running=true;
            run();
        }
        public void run() {
            while (running) {
                if(!waited){
                    try {
                        Thread.sleep(startingInterval);
                        waited=true;
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        System.out.println(
                                "Thread was interrupted, Failed to complete operation"); }
                }


                if(moveCode==CHECK_VICTORY_CODE){
                    victoryCheckAction();
                }else if(moveCode==DOOR_MOVE_CODE){
                    doorMoveAction();
                }


            }
        }

        private void victoryCheckAction() {
            msg.arg1=1;
            handler.sendMessage(msg);
        }

        private void doorMoveAction() {
            try {
                Thread.sleep(littleInterval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            carDoor.setTranslationX(carDoor.getTranslationX()+1);
            if(carDoor.getX()==constraintLayout.getWidth()){
                running=false;
            }
        }
    }
    public void randomCatMeowSound(){
        rand=random.nextInt(100);
        if(rand<=25){
            MainActivity.soundPlayer.makeMeowSound1();
        }else if(rand>25&&rand<=50){
            MainActivity.soundPlayer.makeMeowSound2();
        }else if(rand>50&&rand<75){
            MainActivity.soundPlayer.makeMeowSound3();
        }else{
            MainActivity.soundPlayer.makeMeowSound4();
        }
    }
    private void catEatingSound() {
        rand=random.nextInt(100);
        if(rand<=25){
            MainActivity.soundPlayer.makeCatEaingSound1();
        }else if(rand>25&&rand<=50){
            MainActivity.soundPlayer.makeCatEaingSound2();
        }else if(rand>50&&rand<75){
            MainActivity.soundPlayer.makeCatEaingSound3();
        }else{
            MainActivity.soundPlayer.makeCatEaingSound4();
        }
    }
    private void woodHitSound2() {
        MainActivity.soundPlayer.makeWoodHitSound_3();
    }
    private void woodHitSound() {
        rand=random.nextInt(100);
        if(rand<=16){
            MainActivity.soundPlayer.makeWoodHitSound_1();
        }else if(rand>16&&rand<=32){
            MainActivity.soundPlayer.makeWoodHitSound_2();
        }else if(rand>32&&rand<=48){
            MainActivity.soundPlayer.makeWoodHitSound_3();
        }else if(rand>48&&rand<=64){
            MainActivity.soundPlayer.makeWoodHitSound_4();
        }else if(rand>65&&rand<80){
            MainActivity.soundPlayer.makeWoodHitSound_5();
        }else{
            MainActivity.soundPlayer.makeWoodHitSound_6();
        }
    }
    private void hitBinSound() {
        rand=random.nextInt(100);
        if(rand<=25){
            MainActivity.soundPlayer.makeHitBinSound1();
        }else if(rand>25&&rand<=50){
            MainActivity.soundPlayer.makeHitBinSound2();
        }else if(rand>50&&rand<75){
            MainActivity.soundPlayer.makeHitBinSound3();
        }else{
            MainActivity.soundPlayer.makeHitBinSound4();
        }
    }
    private void hitBinSound2() {
        MainActivity.soundPlayer.makeHitBinSound4();
    }
    private void makeText2() {
        Toast.makeText(getBaseContext(), R.string.hhrrr,Toast.LENGTH_LONG ).show();
    }
}

