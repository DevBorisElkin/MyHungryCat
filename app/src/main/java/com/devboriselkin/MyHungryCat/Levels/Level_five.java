package com.devboriselkin.MyHungryCat.Levels;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.devboriselkin.MyHungryCat.Brick_item;
import com.devboriselkin.MyHungryCat.Media.MusicPlayer;
import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_five extends AppCompatActivity {
    static ImageView cat, bricks, brick1, brick2, chestLocked, chestUnlocked, plate, mole,
            minersHat, itemSpawn, wall, hammer, brick3, brick4, brick5, brick6, brick7, brick8, brick9,
            brick10, brick11, canOpener, crane, crane2, box, restart, tunaCan, fish, lighter, dynamite, pizza;
    Brick_item brick_item1, brick_item2, brick_item3, brick_item4, brick_item5, brick_item6, brick_item7, brick_item8, brick_item9, brick_item10, brick_item11;
    static ConstraintLayout constraintLayout;
    Button checking;
    Handler handler;
    static double d;
    static int y, x;
    static Dialog dialog;
    TextView text1, text2;
    Button back, ok;
    int check;
    int tooHighCounter=0;
    int platformClickedCounter=0;
    boolean chest2locked=true;
    int wallHitCounter=0;
    int chestHitCounter=0;
    int catClickCounter=0;
    boolean hammer_works=true;
    boolean lockedChest_isCurrentlyLocked=true;
    boolean lockedChestClosed=true;
    boolean boxNotHanging=false;
    List<Brick_item>bricksList=new ArrayList<>();
    TextView checkTXT, checkBrick1, checkBrick2;
    int createdBricks=2;
    ControlSubThread controlSubThread, controlSubThread2;
    boolean fallProcessWasNotLaunched=true;
    boolean boomWasNotActivated=true;
    List<ImageView>tunaCanList=new ArrayList<>();
    List<ImageView>dynamiteList=new ArrayList<>();
    CardView cardView;
    boolean tunaCanWorks=true;
    boolean testBurningisOff=true;
    SharedPreferences mySharedPref;
    boolean infected=false;


    //////////////////////

    public static float xOld, yOld;
    float dX, dY;
    ImageView spawnedItem, icon;
    Random random=new Random();
    int rand=0;



    public static final int HAMMER_ITEM_CODE=1;
    public static final int MINERS_HAT_ITEM_CODE=2;
    public static final int BRICK_ITEM_CODE=3;
    public static final int CAN_OPENER_ITEM_CODE=4;
    public static final int FALLING_BOX_MOVING_CODE=5;
    public static final int TUNA_CAN_ITEM_CODE=6;
    public static final int TUNA_FISH_ITEM_CODE=7;
    public static final int LIGHTER_ITEM_CODE=8;
    public static final int DYNAMITE_ITEM_CODE=9;
    public static final int DYNAMITE_MOVING_CODE=10;
    public static final int PIZZA_ITEM_CODE=11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.level_five);
        initUI();
    }

    private void initUI() {
        initViews();
        checkMusic();
        setListeners();
    }
    private void initViews() {
        mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
        MainActivity.soundPlayer.makeRopeBurningSound();
        MainActivity.soundPlayer.stopRopeBurningSound();
        restart=findViewById(R.id.restart);
        restart.setOnClickListener(restartLevel());
        checking=findViewById(R.id.check2);
        checkTXT=findViewById(R.id.check);
        wall=findViewById(R.id.theWall);
        cat=findViewById(R.id.cat);
        bricks=findViewById(R.id.bricks);
        brick1=findViewById(R.id.brick);
        brick2=findViewById(R.id.brick2);
        brick_item1=new Brick_item(brick1, 200, false,1);
        brick_item2=new Brick_item(brick2, 200, false,2);
        brick_item3=new Brick_item(brick3, 200, false,3);
        brick_item4=new Brick_item(brick4, 200, false,4);
        brick_item5=new Brick_item(brick5, 0, false, 5);
        brick_item6=new Brick_item(brick6, 0, false, 6);
        brick_item7=new Brick_item(brick7, 0, false, 7);
        brick_item8=new Brick_item(brick8, 0, false, 8);
        brick_item9=new Brick_item(brick9, 0, false, 9);
        brick_item10=new Brick_item(brick10, 0, false, 10);
        brick_item11=new Brick_item(brick11, 0, false, 11);




        mole=findViewById(R.id.mole);
        chestLocked=findViewById(R.id.boxLocked);
        chestUnlocked=findViewById(R.id.boxUnlocked);
        plate=findViewById(R.id.plate);
        itemSpawn=findViewById(R.id.itemSpawn);
        constraintLayout=findViewById(R.id.constraint_layout);
        crane=findViewById(R.id.crane);
        crane2=findViewById(R.id.crane2);
        box=findViewById(R.id.box);

        bricksList.add(brick_item1);
        bricksList.add(brick_item2);
    }

    private void setListeners() {
        chestUnlocked.setOnClickListener(chestOpened());
        brick1.setOnTouchListener(touchForBricks(brick1, brick_item1));
        brick2.setOnTouchListener(touchForBricks(brick2, brick_item2));
        chestLocked.setOnClickListener(lockedChestClicked());
        cat.setOnClickListener(catClicked());
        checkTXT.setOnClickListener(checkTXTlistener());
        checkTXT.setVisibility(View.INVISIBLE);
//        crane.setOnClickListener(craneClicked());
//        plate.setOnClickListener(craneClicked());
//        crane2.setOnClickListener(craneClicked());
        checking.setOnClickListener(checkButton());
        checking.setVisibility(View.INVISIBLE);
        box.setOnClickListener(boxClicked());
        mole.setOnClickListener(moleClicked());
    }

    @NonNull
    private View.OnClickListener checkButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatingThread();
            }
        };
    }

    @NonNull
    private View.OnClickListener checkTXTlistener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(testBurningisOff){
                    MainActivity.soundPlayer.makeRopeBurningSound();
                    //testBurningisOff=false;
                }else {
                    MainActivity.soundPlayer.stopRopeBurningSound();
                    testBurningisOff=true;
                }

            }
        };
    }

    @NonNull
    private View.OnClickListener moleClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeInfoDialogWindow(getString(R.string.mole), getString(R.string.erew), getString(R.string.xcvx));
                makeMoleRandomSound();
            }
        };
    }

    @NonNull
    private View.OnClickListener boxClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fallProcessWasNotLaunched){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.eres),getString(R.string.awdgs));
                    randomCatMeowSound();
                }
            }
        };
    }


    @NonNull
    private View.OnClickListener catClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(catClickCounter==0){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rthrth),getString(R.string.yrtgerfw));
                    randomCatMeowSound();
                }else if(!boxNotHanging&&catClickCounter==2){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.thyrtg),getString(R.string.tyhrtg));
                    randomCatMeowSound();
                }else if(catClickCounter==3){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.ythtr), getString(R.string.dfvdfv));
                    randomCatMeowSound();
                }else if(catClickCounter>=4&&catClickCounter<=7){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rtgrtg),getString(R.string.gfbgbfb));
                    randomCatMeowSound();
                }
                else if(catClickCounter>=8&&catClickCounter<=10){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rbvcvfd),getString(R.string.tyjty));
                    randomCatMeowSound();
                }
                else if(catClickCounter==11){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.wrrf),getString(R.string.fgnwefwf));
                    randomCatMeowSound();
                }
                else if(catClickCounter==12){
                    mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
                    int mutant=mySharedPref.getInt(MainActivity.CAT_MUTANT, 0);
                    if(mutant==0){
                        makeText2();
                    }

                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rtbrbeb),getString(R.string.bvdvwv));
                    MainActivity.soundPlayer.makeBarkSound();
                    redraw(cat, getResources().getDrawable(R.drawable.sad_mutant));
                    infected=true;

                    mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
                    SharedPreferences.Editor mEditor=mySharedPref.edit();
                    mEditor.putInt(MainActivity.CAT_MUTANT, 1);
                    mEditor.apply();

                }
                else if(catClickCounter>=13){
                    MainActivity.soundPlayer.makeBarkSound();
                }
                catClickCounter++;

            }
        };
    }

    @NonNull
    private View.OnClickListener craneClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(platformClickedCounter<1){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.gbbe), getString(R.string.wefwefb));
                }
                platformClickedCounter++;
            }
        };
    }

    @NonNull
    private View.OnClickListener chestOpened() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chest2locked){
                    redraw(chestUnlocked, getResources().getDrawable(R.drawable.construction_box_opened));
                    chest2locked=false;
                    addBrickProgrammatically(brick3, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick), chestUnlocked.getX()+110, chestUnlocked.getY()+120, brick1.getHeight(), brick1.getWidth(), brick_item3);
                    addBrickProgrammatically(brick4, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick), chestUnlocked.getX()+20, chestUnlocked.getY()+90, brick1.getHeight(), brick1.getWidth(), brick_item4);
                    addDynamiteProgrammatically(dynamite, itemSpawn, DYNAMITE_ITEM_CODE, getResources().getDrawable(R.drawable.dynamite),chestUnlocked.getX()+5, chestUnlocked.getY()+90, 80,80);
                    addItemProgrammatically(minersHat, itemSpawn, MINERS_HAT_ITEM_CODE, getResources().getDrawable(R.drawable.mining_helmet), chestUnlocked.getX()+70, chestUnlocked.getY()+100, 50, 60);
                    MainActivity.soundPlayer.makeChestOpeningSound();

                }

            }
        };
    }
    @NonNull
    private View.OnClickListener lockedChestClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!lockedChest_isCurrentlyLocked&&lockedChestClosed){
                    redraw(chestLocked, getResources().getDrawable(R.drawable.construction_box_opened));
                    addBrickProgrammatically(brick5, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick), chestLocked.getX()+90, chestLocked.getY()+120, brick1.getHeight(), brick1.getWidth(), brick_item5);
                    addBrickProgrammatically(brick6, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick), chestLocked.getX()+30, chestLocked.getY()+160, brick1.getHeight(), brick1.getWidth(), brick_item6);
                    addBrickProgrammatically(brick7, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick), chestLocked.getX()+140, chestLocked.getY()+140, brick1.getHeight(), brick1.getWidth(), brick_item7);
                    addItemProgrammatically(lighter, itemSpawn, LIGHTER_ITEM_CODE, getResources().getDrawable(R.drawable.light),chestLocked.getX()+120, chestLocked.getY()+120, 60,40);
                    MainActivity.soundPlayer.makeChestOpeningSound();
                    lockedChestClosed=false;
                }else if(lockedChest_isCurrentlyLocked){
                    MainActivity.soundPlayer.makeChestLockedSound();
                }
            }
        };
    }

    private void checkMusic() {
        mole.setX(mole.getX()-130);
        box.setY(box.getY()-3);
        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_CODE){
            //nothing
        }else if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE){
            musicPlayer.stopBackgroundMusic();
            musicPlayer.releaseMusicInMenu();
            musicPlayer.startInGameMusic();
            makeInfoDialogWindow(getString(R.string.bdbe), getString(R.string.rerzbreb),getString(R.string.ergergs));
        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            //nothing here
        }
    }



    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
    private static View.OnClickListener close2(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getBack(imageView);
            }
        };
    }
    public void makeYesNoDialog(String title, String text, String buttonPositive, String buttonNegative, ImageView imageView, int Case){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.yes_no_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.btnBack);
        back.setText(buttonNegative);
        back.setOnClickListener(close2(imageView));
        ok=dialog.findViewById(R.id.btnOk);
        ok.setText(buttonPositive);
        if(Case==1){
            ok.setOnClickListener(okAction(dialog, imageView));
        }else if(Case==2){
            ok.setOnClickListener(moleExchange(imageView));
        }

        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @NonNull
    private View.OnClickListener moleExchange(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boomWasNotActivated=false;
                imageView.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                addItemProgrammatically(pizza, itemSpawn,PIZZA_ITEM_CODE,getResources().getDrawable(R.drawable.pizza),mole.getX()+255, mole.getY()+30, 100,100);
            }
        };
    }

    public void makeYesNoDialog2(String title, String text, String buttonPositive, String buttonNegative, ImageView imageView){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.yes_no_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.btnBack);
        back.setText(buttonNegative);
        back.setOnClickListener(close2(imageView));
        ok=dialog.findViewById(R.id.btnOk);
        ok.setText(buttonPositive);
        ok.setOnClickListener(okAction2(dialog, imageView));
        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @NonNull
    private View.OnClickListener okAction2(final Dialog dialog, final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                imageView.setVisibility(View.INVISIBLE);
                addItemProgrammatically(canOpener, itemSpawn, CAN_OPENER_ITEM_CODE, getResources().getDrawable(R.drawable.can_opener_little), mole.getX()+220, mole.getY()+30, 80,100);
            }
        };
    }

    @NonNull
    private View.OnClickListener okAction(final Dialog dialog, final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                makeInfoDialogWindow(getString(R.string.mole),getString(R.string.tbtbxcv),getString(R.string.wefwgxb));
                makeMoleRandomSound();
                imageView.setVisibility(View.INVISIBLE);
                redraw(mole, getResources().getDrawable(R.drawable.mole_ultimate));
                addItemProgrammatically(hammer, itemSpawn, HAMMER_ITEM_CODE, getResources().getDrawable(R.drawable.hammer), mole.getX()+255, mole.getY()+30, 130,100);
            }
        };
    }

    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void onBackPressed() {
        exitToMenu();
    }
    private void exitToMenu() {
        dialog=new Dialog(Level_five.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText(R.string.ergers);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText(R.string.bgrbegb);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThreadsBeforeExit();
                Intent intent = new Intent(Level_five.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText(R.string.tumuk);
        text2.setText(R.string.awdawd);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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
            if(itemCode==BRICK_ITEM_CODE){

            }
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            if(event.getRawY()+dY<constraintLayout.getHeight()/4) {
                getBack(imageView);
                if(tooHighCounter ==0){
                    makeText(getString(R.string.ukyuktjhdgsd));
                    tooHighCounter++;
                }

            }
            switch (itemCode){
                case HAMMER_ITEM_CODE:
                    hammerOverlapAction(imageView);
                    break;
                case MINERS_HAT_ITEM_CODE:
                    minersHatOverlapAction(imageView);
                    break;
                case CAN_OPENER_ITEM_CODE:
                   canOpenerOverlapAction(imageView);
                    break;
                case TUNA_CAN_ITEM_CODE:
                    tunaCanOverlapAction(imageView);
                    break;
                case TUNA_FISH_ITEM_CODE:
                    tunaFishOverlapAction(imageView);
                    break;
                case LIGHTER_ITEM_CODE:
                    lighterOverlapAction(imageView);
                    break;

                case DYNAMITE_ITEM_CODE:
                    dynamiteOverlapAction(imageView);
                    break;
                case PIZZA_ITEM_CODE:
                    pizzaOverlapAction(imageView);
                    break;



                default:makeText("wtf");
            }
        } else{return false;}
        return true;
    }

    private void pizzaOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole),getString(R.string.qeqwe),getString(R.string.tyj));
            makeMoleRandomSound();
        }else if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            redraw(cat, getResources().getDrawable(R.drawable.poisoned_cat));
            levelOver3();
        }
    }

    private void dynamiteOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.gbgbgdb),getString(R.string.sefsegb));
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole),getString(R.string.tbthergre),getString(R.string.rtberb),getString(R.string.dbe),imageView,2);
            makeMoleRandomSound();
        }
    }

    private void lighterOverlapAction(ImageView imageView) {
        if(dynamiteList.size()>0){
            if(wereOverlappedDynamiteWithLighter(imageView)&&boomWasNotActivated){
                MainActivity.soundPlayer.makeRopeBurningSound();
                // MainActivity.soundPlayer.makeFailureSound();
                boomWasNotActivated=false;
                imageView.setX(imageView.getX()-50);
                controlSubThread2 = new ControlSubThread(0,0, DYNAMITE_MOVING_CODE);
                controlSubThread2.start();

            }
        }
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole), getString(R.string.erge), getString(R.string.etbergeb));
            makeMoleRandomSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ergergewgeg),getString(R.string.betbetb));
            randomCatMeowSound();
        }
    }

    private void tunaFishOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole),getString(R.string.ffds),getString(R.string.yukyk));
            makeMoleRandomSound();
        }else if(wereOverlapped(cat, imageView)){
            redraw(imageView,getResources().getDrawable(R.drawable.big_fish_bone));
            imageView.setX(imageView.getX()-50);
            levelOver(true);
        }
    }

    private void tunaCanOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.vvsdvsdfv),getString(R.string.grnrn));
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole),getString(R.string.trbrtrtb),getString(R.string.gregerg));
            makeMoleRandomSound();
        }
    }

    private void canOpenerOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qqqww), getString(R.string.dfbdfb));
            randomCatMeowSound();
        }
         if(tunaCanList.size()>0){
             if(wereOverlappedCanOpenerWithTunaCan(imageView)&&tunaCanWorks){
                 tunaCanWorks=false;
                 redraw(tunaCanList.get(0),getResources().getDrawable(R.drawable.tuna_can_opened));
                // 50 80
                 tunaCanList.get(0).getLayoutParams().height=70;
                 tunaCanList.get(0).getLayoutParams().width=90;

                 addItemProgrammatically(fish, itemSpawn, TUNA_FISH_ITEM_CODE, getResources().getDrawable(R.drawable.big_fish),tunaCanList.get(0).getX(),tunaCanList.get(0).getY(), 80, 120);
                 MainActivity.soundPlayer.makeWoodHitSound_1();
             }
         }
    }
    private boolean wereOverlappedCanOpenerWithTunaCan( View viewActive) {
        float gbX=tunaCanList.get(0).getX();
        float gbY=tunaCanList.get(0).getY();
        int gbWidth=tunaCanList.get(0).getWidth();
        int gbHeight=tunaCanList.get(0).getHeight();
        int viewHeightDivided=gbHeight/4;
        int viewWidthDivided=gbWidth/4;
        if(viewActive.getY()+viewActive.getHeight()>gbY+viewHeightDivided&&viewActive.getY()<gbY+gbHeight-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>gbX+viewWidthDivided&&viewActive.getX()<gbX+gbWidth-viewWidthDivided){return true;}else {return false;}
    }
    private boolean wereOverlappedDynamiteWithLighter( View viewActive) {
        float gbX=dynamiteList.get(0).getX();
        float gbY=dynamiteList.get(0).getY();
        int gbWidth=dynamiteList.get(0).getWidth();
        int gbHeight=dynamiteList.get(0).getHeight();
        int viewHeightDivided=gbHeight/4;
        int viewWidthDivided=gbWidth/4;
        if(viewActive.getY()+viewActive.getHeight()>gbY+viewHeightDivided&&viewActive.getY()<gbY+gbHeight-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>gbX+viewWidthDivided&&viewActive.getX()<gbX+gbWidth-viewWidthDivided){return true;}else {return false;}
    }

    public View.OnTouchListener touchForBricks(final ImageView imageView, final Brick_item brick_item) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MovementUltimateForBricks(event, imageView, brick_item);
            }
        };
    }
    @Nullable
    private Boolean MovementUltimateForBricks(MotionEvent event, ImageView imageView, Brick_item brick_item) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {

            dX=brick_item.getImageView().getX()-event.getRawX();
            dY=brick_item.getImageView().getY()-event.getRawY();
            xOld=brick_item.getImageView().getX();
            yOld=brick_item.getImageView().getY();
        } else if(event.getAction()==MotionEvent.ACTION_MOVE){
            brick_item.getImageView().animate()
                    .x(event.getRawX()+dX)
                    .y(event.getRawY()+dY)
                    .setDuration(0)
                    .start();
            if(wereOverlapped(plate, brick_item.getImageView())){
                brick_item.setOnPlate(true);
                makeBrickSound(brick_item);
            }else{
                brick_item.setOnPlate(false);
                makeBrickSound(brick_item);
            }
            checkPlateOverload();

        }else if(event.getAction()==MotionEvent.ACTION_UP){
            if(event.getRawY()+dY<constraintLayout.getHeight()/4) {
                getBack(brick_item.getImageView());
                if(tooHighCounter ==0){
                    makeText(getString(R.string.fvsf));
                    tooHighCounter++;
                } }
            if(wereOverlapped(cat, imageView)){
                getBack(imageView);
                makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rthryt),getString(R.string.cvsdvs));
                randomCatMeowSound();
            }else if(wereOverlapped(mole, imageView)){
                getBack(imageView);
                makeInfoDialogWindow(getString(R.string.mole),getString(R.string.cgbdbdfb),getString(R.string.rynhtyh));
                makeMoleRandomSound();
            }
        } else{return false;}
        return true;
    }

    private void makeBrickSound(Brick_item brick_item) {
        if(brick_item.isStartAction()){
            brick_item.setStartAction(false);
            brick_item.setSoundWasOutted(true);
            brick_item.setSoundWasEmitted(false);
        } else if(brick_item.isOnPlate()&&!brick_item.soundWasEmitted){
            brick_item.setSoundWasEmitted(true);
            MainActivity.soundPlayer.makeBrickSound();
            brick_item.setSoundWasOutted(false);
        }else if(!brick_item.isOnPlate()&&!brick_item.soundWasOutted){
            brick_item.setSoundWasOutted(true);
            MainActivity.soundPlayer.makeBrickSound();
            brick_item.setSoundWasEmitted(false);
        }

    }

    private void brickOverlapAction(ImageView imageView) {

    }

    private void minersHatOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.cat), getString(R.string.iuytrfghj),getString(R.string.xfvfdv), getString(R.string.awda), imageView,1);
            randomCatMeowSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ngbfvdc), getString(R.string.sefsef));
            randomCatMeowSound();
        }
    }

    private void hammerOverlapAction(ImageView imageView) {
        if(wereOverlapped(wall, imageView)&&hammer_works){
            wallWasHitAction(imageView);
        }else if(wereOverlapped(chestLocked, imageView)&&hammer_works){
            chestHitAction();
        }else if(wereOverlapped(wall, imageView)&&!hammer_works){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.ebebe), getString(R.string.rbebebb));
            randomCatMeowSound();
        }else if (wereOverlapped(cat, imageView)&&hammer_works){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ebetbtrbrgtb),getString(R.string.ergegerg));
            randomCatMeowSound();
        }else if (wereOverlapped(cat, imageView)&&!hammer_works){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.btrbsdvsd),":(");
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)&&hammer_works){
            makeYesNoDialog2(getString(R.string.mole),getString(R.string.tberbes),getString(R.string.rtb),getString(R.string.gvcde), imageView);
            makeMoleRandomSound();
        }else if(wereOverlapped(mole, imageView)&&!hammer_works){
            makeYesNoDialog2(getString(R.string.mole),getString(R.string.gbvvv),getString(R.string.fe),getString(R.string.gbrb), imageView);
            makeMoleRandomSound();
        }
    }

    private void chestHitAction() {
        if(chestHitCounter==3){
            redraw(chestLocked, getResources().getDrawable(R.drawable.construction_box_locked_b1));
            MainActivity.soundPlayer.makeHittingChestSound();
        }else if(chestHitCounter==6){
            redraw(chestLocked, getResources().getDrawable(R.drawable.construction_box_locked_b2));
            MainActivity.soundPlayer.makeHittingChestSound();
        }else if(chestHitCounter==8){
            redraw(chestLocked, getResources().getDrawable(R.drawable.construction_box_locked_b3));
            MainActivity.soundPlayer.makeHittingChestSound();
            lockedChest_isCurrentlyLocked=false;
        }else if(lockedChest_isCurrentlyLocked){
            MainActivity.soundPlayer.makeHittingChestSound();
            //just make "knock"
        }
        chestHitCounter++;
    }



    private void wallWasHitAction(ImageView imageView) {
         if(wallHitCounter==3){
             addBrickProgrammatically(brick8, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick_broken), wall.getX()+wall.getWidth()-50, wall.getY()+wall.getHeight()-45, brick1.getHeight(), brick1.getWidth()/2+5, brick_item8);
             MainActivity.soundPlayer.makeHittingWallSound();
            redraw(wall, getResources().getDrawable(R.drawable.wall_b1));
        }else if(wallHitCounter==5){
             MainActivity.soundPlayer.makeHittingWallSound();
            redraw(wall, getResources().getDrawable(R.drawable.wall_b2));
            redraw(imageView, getResources().getDrawable(R.drawable.hammer_damaged));
             addBrickProgrammatically(brick9, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick_broken), wall.getX()+wall.getWidth()-70, wall.getY()+wall.getHeight()-30, brick1.getHeight(), brick1.getWidth()/2+5, brick_item9);

         }else if(wallHitCounter==7){
             MainActivity.soundPlayer.makeHittingWallSound();
             redraw(wall, getResources().getDrawable(R.drawable.wall_b3));
             addBrickProgrammatically(brick10, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick_broken), wall.getX()+wall.getWidth()-50, wall.getY()+wall.getHeight()-50, brick1.getHeight(), brick1.getWidth()/2+5, brick_item10);

         }else if(wallHitCounter==10){
             MainActivity.soundPlayer.makeHittingWallSound();
             redraw(wall, getResources().getDrawable(R.drawable.wall_b4));
             redraw(imageView, getResources().getDrawable(R.drawable.hammer_broken));
             addBrickProgrammatically(brick11, itemSpawn, BRICK_ITEM_CODE, getResources().getDrawable(R.drawable.brick_broken), wall.getX()+wall.getWidth()+30, wall.getY()+wall.getHeight()-60, brick1.getHeight(), brick1.getWidth()/2+5, brick_item11);

             hammer_works=false;
         }else{
             MainActivity.soundPlayer.makeHittingWallSound();
         }
         wallHitCounter++;
    }

    public static void getBack(ImageView imageView) {
        imageView.animate().x(xOld).y(yOld).setDuration(0).start();
    }
    private void makeText(String a) {
        Toast.makeText(getBaseContext(), a,Toast.LENGTH_SHORT ).show();
    }
    private boolean wereOverlapped(View view, View viewActive) {
        int viewHeightDivided=view.getHeight()/4;
        int viewWidthDivided=view.getWidth()/4;
        if(viewActive.getY()+viewActive.getHeight()>view.getY()+viewHeightDivided&&viewActive.getY()<view.getY()+view.getHeight()-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>view.getX()+viewWidthDivided&&viewActive.getX()<view.getX()+view.getWidth()-viewWidthDivided){return true;}else {return false;}
    }
    private void addItemProgrammatically(ImageView image, ImageView bindTo, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        int addTo;
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) bindTo.getLayoutParams();
            image = new ImageView(this);
        image.setOnTouchListener(touch(image, itemCode));
        image.setImageDrawable(drawable);
        ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newParams.startToEnd=params.startToEnd;
        newParams.width=width;
        newParams.height=height;
        newParams.topToTop=params.topToTop;
        constraintLayout.addView(image, -1, newParams);
        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);
    }
    private void addDynamiteProgrammatically(ImageView image, ImageView bindTo, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        int addTo;
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) bindTo.getLayoutParams();
            image=new ImageView(this);
            dynamiteList.add(image);
        image.setOnTouchListener(touch(image, itemCode));
        image.setImageDrawable(drawable);
        ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newParams.startToEnd=params.startToEnd;
        newParams.width=width;
        newParams.height=height;
        newParams.topToTop=params.topToTop;
        constraintLayout.addView(image, -1, newParams);
        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);
    }
    private void addTunaCanProgrammatically(ImageView image, ImageView bindTo, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        int addTo;
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) bindTo.getLayoutParams();
            image = new ImageView(this);
            tunaCanList.add(image);
        image.setOnTouchListener(touch(image, itemCode));
        image.setImageDrawable(drawable);
        ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newParams.startToEnd=params.startToEnd;
        newParams.width=width;
        newParams.height=height;
        newParams.topToTop=params.topToTop;
        constraintLayout.addView(image, -1, newParams);
        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);
    }
    private void addBrickProgrammatically(ImageView image, ImageView bindTo, int itemCode, Drawable drawable, float x, float y, int height, int width, Brick_item brick_item) {
        int addTo;
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) bindTo.getLayoutParams();
        image = new ImageView(this);
        if(createdBricks==2){
            brick_item3=new Brick_item(image, 0, false, 3);
            image.setOnTouchListener(touchForBricks(image, brick_item3));
        }else if(createdBricks==3){
            brick_item4=new Brick_item(image, 0, false, 4);
            image.setOnTouchListener(touchForBricks(image, brick_item4));
        }else if(createdBricks==4){
            brick_item5=new Brick_item(image, 0, false, 5);
            image.setOnTouchListener(touchForBricks(image, brick_item5));
        }else if(createdBricks==5){
            brick_item6=new Brick_item(image, 0, false, 6);
            image.setOnTouchListener(touchForBricks(image, brick_item6));
        }else if(createdBricks==6){
            brick_item7=new Brick_item(image, 0, false, 7);
            image.setOnTouchListener(touchForBricks(image, brick_item7));
        }
        else if(createdBricks==7){
            brick_item8=new Brick_item(image, 0, false, 8);
            image.setOnTouchListener(touchForBricks(image, brick_item8));
        }
        else if(createdBricks==8){
            brick_item9=new Brick_item(image, 0, false, 9);
            image.setOnTouchListener(touchForBricks(image, brick_item9));
        }
        else if(createdBricks==9){
            brick_item10=new Brick_item(image, 0, false, 10);
            image.setOnTouchListener(touchForBricks(image, brick_item10));
        }
        else if(createdBricks==10){
            brick_item11=new Brick_item(image, 0, false, 11);
            image.setOnTouchListener(touchForBricks(image, brick_item11));
        }
        createdBricks++;

        image.setImageDrawable(drawable);
        ConstraintLayout.LayoutParams newParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newParams.startToEnd=params.startToEnd;
        newParams.width=width;
        newParams.height=height;
        newParams.topToTop=params.topToTop;
        constraintLayout.addView(image, -1, newParams);
        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);
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
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayer !=null){
            musicPlayer.resumeInGameMusic();
        }
    }

    public void checkPlateOverload(){
        if(brick_item1.isOnPlate()&&brick_item2.isOnPlate()&&brick_item3.isOnPlate()&&brick_item4.isOnPlate()&&brick_item5.isOnPlate()&&brick_item6.isOnPlate()&&brick_item7.isOnPlate()&&brick_item8.isOnPlate()&&brick_item9.isOnPlate()&&brick_item10.isOnPlate()&&brick_item11.isOnPlate()){
            checkTXT.setText("True");
            creatingThread();
        }else{
            checkTXT.setText("False");
        }
    }
//////////////////////////////////////////////////
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


            if(moveCode==FALLING_BOX_MOVING_CODE){
                fallAction();
            }else if(moveCode==DYNAMITE_MOVING_CODE){
                boomIsComingAction();
            }


        }
    }

    private void boomIsComingAction() {
        //handledBurningSound();
        sleep(littleInterval);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f1));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f2));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f3));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f4));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_5));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f5));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f6));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f6_12));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f6_13));
        sleep(580);
        handledRedraw(dynamiteList.get(0),getResources().getDrawable(R.drawable.dynamite_f7));
        sleep(580);
        handledLevelOver3();
        destroy();
    }

    private void handledBurningSound() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.soundPlayer.makeRopeBurningSound();
                }
            });

    }

    public void handledRedraw(final ImageView imageView, final Drawable drawable){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                redraw(imageView, drawable);
            }
        });
    }
    public void handledLevelOver(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                levelOver(false);
            }
        });
    }

    public void handledLevelOver3(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    boomLevelOver();
                }
            });

    }

    private void sleep(int littleInterval) {
        try {
            Thread.sleep(littleInterval);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println(
                    "Thread was interrupted, Failed to complete operation");
        }
    }

    private void fallAction() {
        sleep(littleInterval);
        box.setTranslationY(box.getTranslationY()+1);
        if(box.getY()>=crane.getY()+crane.getHeight()-90){
            boxOpeningAction();
            running=false;
            destroy();
        }
    }
  }
  //////////////////////////////////////////////////////////////////////////////////////////////
    private void checkThreadsBeforeExit() {
        if(controlSubThread!=null){
            controlSubThread.stop();
            controlSubThread.destroy();
        }if(controlSubThread2!=null){
            controlSubThread2.stop();
            controlSubThread2.destroy();
        }
    }
    public void boxOpeningAction(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                redraw(box, getResources().getDrawable(R.drawable.imbalanced_box_opened));
                box.getLayoutParams().height=150;
                box.getLayoutParams().width=125;
                box.setY(box.getY()+11);
               addTunaCanProgrammatically(tunaCan, itemSpawn, TUNA_CAN_ITEM_CODE,getResources().getDrawable(R.drawable. tuna_can), box.getX()+30, box.getY()+60, 50, 80);
            }
        });
    }
    private void creatingThread() {
        if(fallProcessWasNotLaunched){
            fallProcessWasNotLaunched=false;
            MainActivity.soundPlayer.makeRopeBreakingSound();
            controlSubThread=new ControlSubThread(1, 0, FALLING_BOX_MOVING_CODE);
            controlSubThread.start();
        }
    }
    @NonNull
    private View.OnClickListener restartLevel() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_five.this, Level_five.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                checkThreadsBeforeExit();
                startActivity(intent);
            }
        };
    }
    public void levelOver(Boolean win){
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
        back.setText(R.string.uyjuk);
        back.setOnClickListener(toMainMenu());

        if(win){
            mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.LEVEL_6_UNLOCK_KEY, 66);
            mEditor.apply();

            MainActivity.soundPlayer.makeVictorySound();
            text1.setText(R.string.ebebr);
            text2.setText(R.string.cbfcfbfb);
            ok.setText(R.string.qwfvbv);
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_boss));
            ok.setOnClickListener(toLevelSix());
        }else{
            int d1, d2, d3, d4, d5, d6;
            d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
            d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
            d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
            d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
            d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
            d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
            if(d1==1&&d2==1&&d3==1&&d4==1&&d5==0&&d6==1){
                makeText2();
            }

            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_5, 1);
            mEditor.apply();


            MainActivity.soundPlayer.makeFailureSound();
            text1.setText(R.string.gnhgbfrgbgnh);
            text2.setText(R.string.nhbgfdefghnj);
            ok.setText(R.string.awefsfbgfb);
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
    public void boomLevelOver(){
        checkThreadsBeforeExit();
        int d1, d2, d3, d4, d5, d6;
        d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
        d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
        d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
        d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
        d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
        d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
        if(d1==1&&d2==1&&d3==1&&d4==1&&d5==0&&d6==1){
            makeText2();
        }

        SharedPreferences.Editor mEditor=mySharedPref.edit();
        mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_5, 1);
        mEditor.apply();
       // MainActivity.soundPlayer.stopRopeBurningSound();
        MainActivity.musicPlayer.stopInGameMusic();
        checkThreadsBeforeExit();
        dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        dialog.setContentView(R.layout.explosion_popup);
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        ok=dialog.findViewById(R.id.btnOk);
        back=dialog.findViewById(R.id.btnBack);
        //icon=dialog.findViewById(R.id.img1);
        //back.setText("Back to menu");
        back.setOnClickListener(toMainMenu());
            MainActivity.soundPlayer.makeExplosionSound();
//            text1.setText("The cat is dead");
//            text2.setText("Probably you have to repeat from the beginning");
//            ok.setText("stat over");
            //icon.setImageDrawable(getDrawable(R.drawable.ic_block));
            ok.setOnClickListener(startOver());
            cardView=dialog.findViewById(R.id.cardView);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.pinkNotif));
            ok.setBackground(getResources().getDrawable(R.drawable.pink_button));
            back.setBackground(getResources().getDrawable(R.drawable.pink_button));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void levelOver3(){
        checkThreadsBeforeExit();
        int d1, d2, d3, d4, d5, d6;
        d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
        d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
        d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
        d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
        d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
        d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
        if(d1==1&&d2==1&&d3==1&&d4==1&&d5==0&&d6==1){
            makeText2();
        }

        SharedPreferences.Editor mEditor=mySharedPref.edit();
        mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_5, 1);
        mEditor.apply();



        dialog=new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        dialog.setContentView(R.layout.game_popup_menu2);
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        ok=dialog.findViewById(R.id.btnOk);
        back=dialog.findViewById(R.id.btnBack);
        icon=dialog.findViewById(R.id.img1);
        back.setText(R.string.loikuhygtf);
        MainActivity.soundPlayer.makeFailureSound();
        back.setOnClickListener(toMainMenu());
            text1.setText(R.string.nbvcfe);
            text2.setText(R.string.bgvfbgnhmj);
            ok.setText(R.string.oiuyt);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));
        ok.setOnClickListener(startOver());
        cardView=dialog.findViewById(R.id.cardView);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.pinkNotif));
        ok.setBackground(getResources().getDrawable(R.drawable.pink_button));
        back.setBackground(getResources().getDrawable(R.drawable.pink_button));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    @NonNull
    private View.OnClickListener startOver() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.musicPlayer.resumeInGameMusic();
                Intent intent=new Intent(Level_five.this, Level_five.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener toMainMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThreadsBeforeExit();
                Intent intent=new Intent(Level_five.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener toLevelSix() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThreadsBeforeExit();
                Intent intent=new Intent(Level_five.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.OPEN_LEVELS_DIALOG);
                startActivity(intent);
            }
        };
    }
    public void randomCatMeowSound(){
        if(infected){
            MainActivity.soundPlayer.makeBarkSound();
        }else{
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
    public void makeMoleRandomSound(){
        rand=random.nextInt(100);
        if(rand<=50){
            MainActivity.soundPlayer.makeMoleSound1();
        }else{
            MainActivity.soundPlayer.makeMoleSound2();
        }
    }
    private void makeText2() {
        Toast.makeText(getBaseContext(), R.string.bvddf,Toast.LENGTH_LONG ).show();
    }
}
