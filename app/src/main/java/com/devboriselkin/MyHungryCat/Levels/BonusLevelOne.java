package com.devboriselkin.MyHungryCat.Levels;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class BonusLevelOne extends AppCompatActivity {
    float dX, dY, tunaCanX, tunaCanY, canOpenerX, canOpenerY;
    public static float xOld, yOld;
    int canOpenerWidth=204;
    int canOpenerHeight=128;
    int tunaCanHeight =128;
    int tunaCanWidth =155;
    int poisonCharges=2;
    ImageView catImage, fishImage1, fishImage2, fishImage3, boxImage, moleImage, mouseImage, canOpenerImage, keyImage, tunaFishImage1, fishImage;
    ImageView crapImg, item_spawn2, bredImg, cheeseImg;
    ImageView newCrapImg;
    ImageView fishImagePoison1, fishImagePoison2,fishImagePoison3 ,fishImagePoison4;
    ImageView item_spawn1, tunaCanImage, emeraldImage, sleepingDrugImage, poisonImg, goldBagImg;
    ImageView icon;
    ImageView buttonRestart;

    public static final int CRAP_WIDTH=90;
    public static final int CRAP_HEIGHT=90;
    public static final int BIG_FISH_WIDTH=130;
    public static final int BIG_FISH_HEIGHT=90;
    public static final int FISH_WIDTH=90;
    public static final int FISH_HEIGHT=70;
    public static final int BRED_WIDTH_AND_HEIGTH=70;
    public static final int TUNA_CAN_WIDTH=120;
    public static final int TUNA_CAN_HEIGTH=90;

    List<ImageView> listOfImages=new ArrayList<>();
    List<Integer>listOfIdFishes=new ArrayList<>();
    int TunaCanListIndex;
    int CanOpenerListIndex;


    float activeItemX, activeItemY, passiveItemX, passiveItemY;
    int activeItemWidth, activeItemHeight, passiveItemWidth, passiveItemHeight;

    Handler handler;
    boolean mousePoisoned=false;
    boolean tunaCanCreated =false;
    boolean alive=true;
    boolean moleAlive=true;
    boolean asleep=false;
    ProgressBar progressBar;
    boolean keyDestroyed=false;
    boolean unopened=true;
    boolean locked=true;
    int check=0;
    Random random=new Random();
    int rand;
    CardView cardView;
    int catClickCounter=0;
    ConstraintLayout constraintLayout;
    ControlSubThread controlSubThread;
    ControlSubThread controlSubThread2;
    int littleFishIncrement=2;
    int bigFishIncrement=4;
    int catEatenCrapCount=0;
    public static final int FISH_ITEM_CODE=1;
    public static final int KEY_ITEM_CODE=2;
    public static final int CAN_OPENER_ITEM_CODE=3;
    public static final int CRAP_ITEM_CODE=4;
    public static final int TUNA_CAN_ITEM_CODE=5;
    public static final int EMERALD_ITEM_CODE=6;
    public static final int SLEEPING_DRUG_ITEM_CODE=7;
    public static final int SlEEP_FISH_ITEM_CODE=8;
    public static final int BOX_MOVE_CODE=9;
    public static final int CAT_MOVE_CODE=10;
    public static final int MOUSE_ENTITY_CODE=11;
    public static final int BRED_ITEM_CODE=12;
    public static final int CHEESE_ITEM_CODE=13;
    public static final int POISON_ITEM_CODE=14;
    public static final int POISONED_FISH_ITEM_CODE=15;
    public static final int BAG_OF_GOLD_ITEM_CODE=16;


    String publicText1, publicText2;
    static Dialog dialog;
    TextView text1, text2;
    Button back, test, end, endB, btnOK, ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.bonus_level_one);


        constraintLayout=findViewById(R.id.layout);
        progressBar=findViewById(R.id.progressBar);
        catImage = findViewById(R.id.cat_img);
        catImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catClickCounter<=2){
                    makeInfoDialogWindow("Cat","I'm hungry!!!","As always...");
                } else{
                    makeInfoDialogWindow("Cat", "If be honest, I think, you can bring me that sneaky little mouse, if you do this, I'll be so happy!","Sounds impossible");
                }
                catClickCounter++;
            }
        });

        handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                if(msg.arg1==1)
                {
                    levelOver(false);
                }
                return false;
            }
        });

        fishImage1 = findViewById(R.id.fish1_img);
        fishImage2 = findViewById(R.id.fish2_img);
        fishImage3=findViewById(R.id.fish3_img);
        boxImage = findViewById(R.id.box_img);
        boxImage.setOnClickListener(BoxTouch());
        moleImage = findViewById(R.id.mole_img);
        mouseImage = findViewById(R.id.mouse_img);
        canOpenerImage = new ImageView(this);
        keyImage = new ImageView(this);
        buttonRestart=findViewById(R.id.restart);
        buttonRestart.setOnClickListener(restartLevel());

        crapImg=findViewById(R.id.crap);
        item_spawn2 =findViewById(R.id.item_spawn_2);

        tunaCanImage=new ImageView(this);
        item_spawn1=findViewById(R.id.item_spawn_1);

        fishImage1.setOnTouchListener(touch(fishImage1,littleFishIncrement,FISH_ITEM_CODE));
        fishImage2.setOnTouchListener(touch(fishImage2,littleFishIncrement,FISH_ITEM_CODE));
        fishImage3.setOnTouchListener(touch(fishImage3,littleFishIncrement,FISH_ITEM_CODE));
        listOfImages.add(fishImage1);
        listOfImages.add(fishImage2);
        listOfImages.add(fishImage3);
        listOfIdFishes.add(0);
        listOfIdFishes.add(1);
        listOfIdFishes.add(2);


        // keyImage.setOnTouchListener(touch(keyImage,0,KEY_ITEM_CODE));
        //canOpenerImage.setOnTouchListener(touch(canOpenerImage,0, CAN_OPENER_ITEM_CODE));



            dialog=new Dialog(this);
            dialog.setContentView(R.layout.game_popup_menu1);
            dialog.setOnKeyListener(dialogOnBackPressed());
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(dialogOnBackPressed());
            back = dialog.findViewById(R.id.buttonBack);
            test = dialog.findViewById(R.id.buttonOkay);
            text1=dialog.findViewById(R.id.text1);
            text2=dialog.findViewById(R.id.text2);
            text1.setText("It's a bonus level!");
            text2.setText("Here's a bonus level. I used images for objects I first found in the internet. That level is here to show you, what kind of raw game it was back in the day when I made an idea to create it.");
            ImageView imageView=new ImageView(getApplicationContext());
            test.setOnClickListener(close(imageView));
            back.setOnClickListener(getToMain());
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


            checkMusic();

    }
    private void checkMusic() {
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
        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            //nothing here
        }
    }

    @NonNull
    private View.OnClickListener restartLevel() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BonusLevelOne.this, BonusLevelOne.class);
                checkThreadsBeforeExit();
                startActivity(intent);
            }
        };
    }

    @NonNull
    private View.OnClickListener BoxTouch() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locked){
                    makeInfoDialogWindow("Oops", "It looks like this chest is locked", "okay");
                }
            }
        };
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
            //imageView.setRotationX(180f);
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
            //imageView.setRotationX(-360f);
            switch (itemCode){
                case FISH_ITEM_CODE:
                    fishOverlapAction(imageView, increment);
                    break;
                case KEY_ITEM_CODE:
                    keyOverlapAction(imageView);
                    break;
                case CAN_OPENER_ITEM_CODE:
//                      canOpenerX=event.getRawX()+dX;
//                      canOpenerY=event.getRawY()+dY;
                    canOpenerX=imageView.getX();
                    canOpenerY=imageView.getY();
                    listOfImages.get(CanOpenerListIndex).setX(imageView.getX());
                    listOfImages.get(CanOpenerListIndex).setY(imageView.getY());
                    canOpenerOverlapAction(imageView);
                    break;
                case CRAP_ITEM_CODE:
                    crapOverlapAction(imageView, -2);
                    break;
                case SLEEPING_DRUG_ITEM_CODE:
                    sleepingDrugOverlapAction(imageView);
                    break;
                case TUNA_CAN_ITEM_CODE:
//                      tunaCanX=event.getRawX()+dX;
//                      tunaCanY=event.getRawY()+dY;
                    tunaCanX=imageView.getX();
                    tunaCanY=imageView.getY();
                    listOfImages.get(TunaCanListIndex).setX(imageView.getX());
                    listOfImages.get(TunaCanListIndex).setY(imageView.getY());
                    tunaCanOverlapAction(imageView);
                    break;
                case EMERALD_ITEM_CODE:
                    emeraldOverlapAction(imageView);
                    break;
                case SlEEP_FISH_ITEM_CODE:
                    //Пока что удалена из игры
                    break;
                case MOUSE_ENTITY_CODE:
                    mouseOverlapAction(imageView);
                    break;
                case BRED_ITEM_CODE:
                    bredOverlapAction(imageView);
                    break;
                case CHEESE_ITEM_CODE:
                    cheeseOverlapAction(imageView);
                    break;
                case POISON_ITEM_CODE:
                    poisonOverlapAction(imageView);
                    break;
                case POISONED_FISH_ITEM_CODE:
                    poisonedFishOverlapAction(imageView);
                    break;
                case BAG_OF_GOLD_ITEM_CODE:
                    bagOfGoldOverlapAction(imageView);
                    break;
                default:makeText("wtf");
            }
        } else{return false;}
        return true;

    }

    private void fishOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(catImage, imageView)&&alive){
            setInvisible(imageView);
            progressBarIncrement(increment);
            checkVictory();
        } else if(wereOverlapped(mouseImage, imageView)&&!asleep){
            imageView.setVisibility(View.INVISIBLE);

            mouseConsumedSomething();

        } else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchange(imageView);
        }
    }

    private void mouseConsumedSomething() {
        rand=random.nextInt(100);
        int randomSpawn, randomSpawn2;
        randomSpawn=random.nextInt(150);
        randomSpawn2=random.nextInt(150);
        if(rand<=4){
            addItemProgrammatically(emeraldImage, crapImg,0, EMERALD_ITEM_CODE, getResources().getDrawable(R.drawable.img19), 75,75);
        }else{
            addItemProgrammatically(newCrapImg, crapImg,-2, CRAP_ITEM_CODE, getResources().getDrawable(R.drawable.crap2), CRAP_HEIGHT, CRAP_WIDTH);
        }
    }
    private void mouseConsumedCheese() {
        rand=random.nextInt(100);
        if(rand<=50){
            addItemProgrammatically(emeraldImage, crapImg,0, EMERALD_ITEM_CODE, getResources().getDrawable(R.drawable.img19), 75,75);
        }else{
            addItemProgrammatically(newCrapImg, crapImg,-2, CRAP_ITEM_CODE, getResources().getDrawable(R.drawable.crap2), CRAP_HEIGHT, CRAP_WIDTH);
        }
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

    private void moleExchange(ImageView imageView) {
        dialog=new Dialog(this);

        String moleText="do ya want to exchange this preeescious stuff for something else?";

        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText("I think: no");
        test=dialog.findViewById(R.id.buttonOkay);
        test.setText("Yeah, sure.");
        back.setOnClickListener(close(imageView));
        test.setOnClickListener(moleExchangeConfirmed(imageView));
        text1.setText("Mole:");
        text2.setText(moleText);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void moleExchangeEmerald(ImageView imageView, int itemCode) {
        dialog=new Dialog(this);

        String moleText="Wow, is this img19? For this one I can give you something equal!";

        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText("I think: no");
        test=dialog.findViewById(R.id.buttonOkay);
        test.setText("Yeah, sure.");
        back.setOnClickListener(close(imageView));
        test.setOnClickListener(moleExchangeConfirmedForEmerald(imageView));
        text1.setText("Mole:");
        text2.setText(moleText);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void moleRefuse(ImageView imageView, int itemCode) {
        publicText1="Mole";
        if(itemCode==CRAP_ITEM_CODE){
            publicText2="You know, I'm not dealing with this shit man";
        } else{
            publicText2="I just don't want it";
        }
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.game_popup1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        test=dialog.findViewById(R.id.buttonOkay);
        test.setOnClickListener(close(imageView));
        text1.setText(publicText1);
        text2.setText(publicText2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        getBack(imageView);
    }

    private static void getBack(ImageView imageView) {
        imageView.setX(xOld);
        imageView.setY(yOld);
    }

    @NonNull
    private View.OnClickListener moleExchangeConfirmed(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float spawnMoleX=moleImage.getX()+210;
                float spawnMoleY=moleImage.getY()+40;
                int difference=70;
                imageView.setVisibility(View.INVISIBLE);
                imageView.setX(constraintLayout.getWidth()+2000);
                dialog.dismiss();
                //в оригинале граница 100
                rand=random.nextInt(95);

                if(rand>=0&&rand<5){
                    addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),spawnMoleX, spawnMoleY, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
                }else if(rand>=5&&rand<20){
                    addItemProgrammatically(fishImage, item_spawn2, littleFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img18),spawnMoleX,spawnMoleY, FISH_HEIGHT, FISH_WIDTH);
                }else if(rand>=20&&rand<35){
                    addItemProgrammatically(canOpenerImage, item_spawn1,0,CAN_OPENER_ITEM_CODE, getResources().getDrawable(R.drawable.img24), spawnMoleX,spawnMoleY, 80, 120);
                    canOpenerX=spawnMoleX;
                    canOpenerY=spawnMoleY;
                }else if(rand>=35&&rand<50){
                    addItemProgrammatically(keyImage, item_spawn1,0,KEY_ITEM_CODE, getResources().getDrawable(R.drawable.img9), spawnMoleX,spawnMoleY, 60,60);
                }else if(rand>=50&&rand<55){
                    addItemProgrammatically(keyImage, item_spawn1,0,KEY_ITEM_CODE, getResources().getDrawable(R.drawable.img9), spawnMoleX,spawnMoleY,60,60);
                    addItemProgrammatically(tunaFishImage1, item_spawn2,bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1), spawnMoleX,spawnMoleY+difference, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
                }else if(rand>=55&&rand<=80){
                    addItemProgrammatically(newCrapImg, item_spawn1 ,-2, CRAP_ITEM_CODE, getResources().getDrawable(R.drawable.crap2), spawnMoleX, spawnMoleY,CRAP_HEIGHT, CRAP_WIDTH);
                }else if(rand==81){
                    addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),spawnMoleX,spawnMoleY, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
                    addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),spawnMoleX,spawnMoleY+difference, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
                } else if(rand>=82&&rand<=90){
                    addItemProgrammatically(fishImage, item_spawn2, littleFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img18),spawnMoleX,spawnMoleY, FISH_HEIGHT, FISH_WIDTH);
                    addItemProgrammatically(fishImage, item_spawn2, littleFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img18),spawnMoleX,spawnMoleY+difference, FISH_HEIGHT, FISH_WIDTH);
                }
                else{
                    addItemProgrammatically(newCrapImg, item_spawn1 ,-2, CRAP_ITEM_CODE, getResources().getDrawable(R.drawable.crap2), spawnMoleX, spawnMoleY,CRAP_HEIGHT, CRAP_WIDTH);
                    addItemProgrammatically(newCrapImg, item_spawn2 ,-2, CRAP_ITEM_CODE, getResources().getDrawable(R.drawable.crap2),spawnMoleX, spawnMoleY+difference,CRAP_HEIGHT, CRAP_WIDTH);
                    // 2 дерьма
                }
            }
        };
    }
    @NonNull
    private View.OnClickListener moleExchangeConfirmedForEmerald(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float spawnMoleX=moleImage.getX()+210;
                float spawnMoleY=moleImage.getY();
                int difference=70;
                imageView.setVisibility(View.INVISIBLE);
                imageView.setX(constraintLayout.getWidth()+2000);
                dialog.dismiss();
                //в оригинале граница 100
                rand=random.nextInt(100);
                // Здесь будет снатворное
                addItemProgrammatically(sleepingDrugImage, item_spawn2, 0,SLEEPING_DRUG_ITEM_CODE, getResources().getDrawable(R.drawable.img4),spawnMoleX, spawnMoleY, 100,80);
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
        listOfImages.add(image);
        if(itemCode==CAN_OPENER_ITEM_CODE){
            addTo=listOfImages.size()-1;
            CanOpenerListIndex=addTo;
        } else if(itemCode==TUNA_CAN_ITEM_CODE){
            addTo=listOfImages.size()-1;
            TunaCanListIndex=addTo;
        } else if(itemCode==FISH_ITEM_CODE){
            addTo=listOfImages.size()-1;
            listOfIdFishes.add(addTo);
        }
        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);


    }
    private void addItemProgrammatically(ImageView image, ImageView bindTo, int increment, int itemCode, Drawable drawable, int height, int width) {
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
    }
    public void bringItemToMap(ImageView imageView, float x, float y){
        imageView.setX(x);
        imageView.setY(y);
    }

    private void canOpenerOverlapAction(ImageView imageView) {
        if(wereOverlappedForProgrammaticObjects(TUNA_CAN_ITEM_CODE, CAN_OPENER_ITEM_CODE)){
            tunaCanImage=listOfImages.get(TunaCanListIndex);
            tunaCanImage.setVisibility(View.INVISIBLE);
            canOpenedResult();
        }
        else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchange(imageView);
        }
        else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "Perhaps you can use it to open different food containers", "Thanks for information!");
            getBack(imageView);
        }
    }

    private void tunaCanOverlapAction(ImageView imageView) {
        if(wereOverlappedForProgrammaticObjects(CAN_OPENER_ITEM_CODE, TUNA_CAN_ITEM_CODE)){
            setInvisible(imageView);
            unopened=false;
            canOpenedResult();
        }else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchange(imageView);
        }
        else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "Ohh, there's gotta be some delicious fishes in there!", "Okay.");
            getBack(imageView);
        }
    }

    private void canOpenedResult() {
        passiveItemX=listOfImages.get(TunaCanListIndex).getX();
        passiveItemY=listOfImages.get(TunaCanListIndex).getY();
        tunaCanImage.setX(constraintLayout.getWidth()+2000);
        unopened=false;
        rand=random.nextInt(14);
        if(rand<5){
            addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),passiveItemX, passiveItemY, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
            makeText("Only one, bad luck");
        }
        else if(rand>=5&&rand<=8){
            addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),passiveItemX, passiveItemY, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
            addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),passiveItemX, passiveItemY+45, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
            makeText("You found 2 fishes");
        }
        else if(rand>=9&&rand<=12){
            addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),passiveItemX, passiveItemY, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
            addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),passiveItemX, passiveItemY+45, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
            addItemProgrammatically(tunaFishImage1, item_spawn2, bigFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img1),passiveItemX, passiveItemY+70, BIG_FISH_HEIGHT, BIG_FISH_WIDTH);
            makeText("It's 3 at a time!");
        }else{
            makeText("It's empty");
        }
    }

    private void crapOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(catImage, imageView)){
            setInvisible(imageView);
            progressBarIncrement(increment);
            catEatenCrapCount++;
            if(catEatenCrapCount==2||catEatenCrapCount==3){
                redraw(catImage, getResources().getDrawable(R.drawable.img22));
                makeInfoDialogWindow("Cat", "please, STOP IT!", "Hmm..");
            }
            if(catEatenCrapCount==4){
                redraw(catImage, getResources().getDrawable(R.drawable.img23));
                makeInfoDialogWindow("Cat", "You know what? I'm done with this shit!", "WTF?");
                //soundPlayer.makeMeowSound();
                controlSubThread2=new ControlSubThread(10,500, CAT_MOVE_CODE);
                controlSubThread2.start();
            }
            if(catEatenCrapCount>=5){
                redraw(catImage, getResources().getDrawable(R.drawable.img8));
                alive=false;
                checkVictory();
                //soundPlayer.makeMeowSound();


            }
        }else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleRefuse(imageView, CRAP_ITEM_CODE);
        }
    }
    private void sleepingDrugOverlapAction(ImageView imageView) {
        if(wereOverlapped(mouseImage, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            redraw(mouseImage,getResources().getDrawable(R.drawable.img10));
            mouseImage.setOnTouchListener(touch(mouseImage,15, MOUSE_ENTITY_CODE ));
            asleep=true;
        }else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            makeInfoDialogWindow("Mole", "You want to bring it back, but i don't want to..", "I got scummed!");
            getBack(imageView);
        }else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "That's spooky!", "Don't worry, it's not for you.");
            getBack(imageView);
        }
    }
    private void emeraldOverlapAction(ImageView imageView) {
        if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchangeEmerald(imageView, EMERALD_ITEM_CODE);
        }else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "How beautiful! Try to sell it and bring something delicious to me!", "mm, okay.");
            getBack(imageView);
        }
    }
    private void mouseOverlapAction(ImageView imageView) {
        if(wereOverlapped(catImage, imageView)&&alive&&!mousePoisoned){
            mouseImage.setVisibility(View.INVISIBLE);
            progressBar.incrementProgressBy(15);
            checkVictory();
        }else if(wereOverlapped(catImage, imageView)&&alive&&mousePoisoned){
            mouseImage.setVisibility(View.INVISIBLE);
            progressBar.incrementProgressBy(-25);
            alive=false;
            redraw(catImage, getResources().getDrawable(R.drawable.img8));
            checkVictory();
        }else if(wereOverlapped(moleImage, imageView)){
            makeInfoDialogWindow("Mole", "Yak, this is disgusting, take it away from me!", "But it's just a mouse.. What's wrong?");
            getBack(imageView);
        }
    }
    private void bredOverlapAction(ImageView imageView) {
        if(wereOverlapped(mouseImage, imageView)&&!asleep){
            imageView.setVisibility(View.INVISIBLE);
            mouseConsumedSomething();
        }else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            makeInfoDialogWindow("Mole", "Sorry, but this piece is too little for trade", "Dang it!");
            getBack(imageView);
        }else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "I don't eat bread, it's too dry for my stomach, I feel like you can bait mouse with that", "I'll try");
            getBack(imageView);
        }
    }
    private void cheeseOverlapAction(ImageView imageView) {
        if(wereOverlapped(mouseImage, imageView)&&!asleep){
            imageView.setVisibility(View.INVISIBLE);
            mouseConsumedCheese();
        } else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchange(imageView);
        } else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "Personally, I don't like img30, but you can try your luck with mouse, or give it to the img11..", "Okay.");
            getBack(imageView);
        }
    }
    private void poisonOverlapAction(ImageView imageView) {
        if(wereOverlapped(fishImage1, imageView)){
            poisonChargeCheck(imageView, fishImage1);
        }else if(wereOverlapped(fishImage2, imageView)){
            poisonChargeCheck(imageView, fishImage2);
        }else if(wereOverlapped(fishImage3, imageView)){
            poisonChargeCheck(imageView, fishImage3);
        }else if(wereOverlapped(fishImagePoison1, imageView)){
            poisonChargeCheck(imageView, fishImagePoison1);
        }else if(wereOverlapped(fishImagePoison2, imageView)){
            poisonChargeCheck(imageView, fishImagePoison2);
        } else if(wereOverlapped(fishImagePoison3, imageView)){
            poisonChargeCheck(imageView, fishImagePoison3);
        }  else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchange(imageView);
        } else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "That is really dangerous! Make sure not to img14 yourself!", "Okay.");
            getBack(imageView);
        }
    }

    private void poisonChargeCheck(ImageView imageView, ImageView imageView2) {
        if(poisonCharges>0){
            poisonCharges--;
            redraw(imageView2, getResources().getDrawable(R.drawable.img6));
            imageView2.setOnTouchListener(touch(imageView2, 0, POISONED_FISH_ITEM_CODE));
            if(poisonCharges<=0){
                imageView.setVisibility(View.INVISIBLE);
            }
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    //TODO: Сделать нормальный яд и отравленые объекты.
    private void poisonedFishOverlapAction(ImageView imageView) {
        if(wereOverlapped(mouseImage, imageView)&&!asleep&&!mousePoisoned){
            imageView.setVisibility(View.INVISIBLE);
            redraw(mouseImage, getResources().getDrawable(R.drawable.img7));
            mouseImage.setOnTouchListener(touch(mouseImage,15, MOUSE_ENTITY_CODE ));
            mousePoisoned=true;
        } else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            float spawnMoleX=moleImage.getX()+210;
            float spawnMoleY=moleImage.getY();
            imageView.setVisibility(View.INVISIBLE);
            moleAlive=false;
            redraw(moleImage, getResources().getDrawable(R.drawable.mole_dead));
            addItemProgrammatically(goldBagImg,item_spawn1, 0, BAG_OF_GOLD_ITEM_CODE, getResources().getDrawable(R.drawable.img29),spawnMoleX,spawnMoleY, 120, 120);
            makeInfoDialogWindow("Sad to admit", "It looks like all treasures img11 had are buried undeground, it's impossible to reach them. All you found, is just a bag of gold, but there's no point of it without a img11.", "Dang it!");
        } else if(wereOverlapped(catImage, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            progressBar.incrementProgressBy(-25);
            alive=false;
            redraw(catImage, getResources().getDrawable(R.drawable.img8));
            checkVictory();

        }
    }

    private void bagOfGoldOverlapAction(ImageView imageView) {

        if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "I don't need it, I'm just hungry! You're so desperate!", ":(");
            getBack(imageView);
        }
    }


    private void keyOverlapAction(ImageView imageView) {
        if(wereOverlapped(boxImage, imageView)){
            setInvisible(imageView);
            keyDestroyed=true;
            rand=random.nextInt(135);
            int randomSpawn, randomSpawn2;
            if(rand<30){
                makeText("It's empty");
            }else if(rand>=30&&rand<=65){
                makeText("Just something");
                addItemProgrammatically(fishImage, item_spawn2, littleFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img18),boxImage.getX()-50,boxImage.getY(), FISH_HEIGHT, FISH_WIDTH);
                addItemProgrammatically(fishImage, item_spawn2, littleFishIncrement,FISH_ITEM_CODE, getResources().getDrawable(R.drawable.img18),boxImage.getX()-50,boxImage.getY()+50, FISH_HEIGHT, FISH_WIDTH);
            } else if(rand>65&&rand<=90){
                addItemProgrammatically(tunaCanImage, item_spawn1, 0, TUNA_CAN_ITEM_CODE, getResources().getDrawable(R.drawable.img2), boxImage.getX()-45, boxImage.getY(), TUNA_CAN_HEIGTH, TUNA_CAN_WIDTH);
                listOfImages.get(TunaCanListIndex).setX(boxImage.getX()-45);
                listOfImages.get(TunaCanListIndex).setY(boxImage.getY());
                makeText("Just a tuna can");
                tunaCanCreated=true;
            }
            else if(rand>90&&rand<=100){
                addItemProgrammatically(tunaCanImage, item_spawn1, 0, TUNA_CAN_ITEM_CODE, getResources().getDrawable(R.drawable.img2), boxImage.getX()-45, boxImage.getY(), TUNA_CAN_HEIGTH, TUNA_CAN_WIDTH);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-60, boxImage.getY()+30, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-85, boxImage.getY()+50, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-60, boxImage.getY()+60, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-85, boxImage.getY()+80, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                listOfImages.get(TunaCanListIndex).setX(boxImage.getX()-45);
                listOfImages.get(TunaCanListIndex).setY(boxImage.getY());
                makeText("Tuna and free img25");
                tunaCanCreated=true;
            } else if(rand>100&&rand<=108){
                makeText("lot's of img25");
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);
                randomSpawn=random.nextInt(200);randomSpawn2=random.nextInt(150);
                addItemProgrammatically(bredImg, item_spawn1, 0, BRED_ITEM_CODE, getResources().getDrawable(R.drawable.img25), boxImage.getX()-randomSpawn, boxImage.getY()+randomSpawn2, BRED_WIDTH_AND_HEIGTH, BRED_WIDTH_AND_HEIGTH);


            } else if(rand>=109&&rand<=113){
                makeText("wow, it's cheese!");
                addItemProgrammatically(cheeseImg, item_spawn1, 5, CHEESE_ITEM_CODE, getResources().getDrawable(R.drawable.img30), boxImage.getX()-60, boxImage.getY()+60,70, 90);
            } else{
                makeText("You found poison!");
                addItemProgrammatically(poisonImg, item_spawn1, 0 ,POISON_ITEM_CODE, getResources().getDrawable(R.drawable.img14),boxImage.getX()-60, boxImage.getY()+60, 100, 70);
                int j=0;
                for(int i=0; i<listOfIdFishes.size(); i++){
                    j=listOfIdFishes.get(i);
                }
                fishImagePoison1=listOfImages.get(j-2);
                fishImagePoison2=listOfImages.get(j-1);
                fishImagePoison3=listOfImages.get(j);


            }
//                tunaCanX=boxImage.getX()-45;
//                tunaCanY=boxImage.getY();
            redraw(boxImage, getResources().getDrawable(R.drawable.img28));
            controlSubThread=new ControlSubThread(30, 2000, 9);
            controlSubThread.start();
            locked=false;
        }else if(wereOverlapped(moleImage, imageView)&&moleAlive){
            moleExchange(imageView);
        }
        else if(wereOverlapped(catImage, imageView)){
            makeInfoDialogWindow("Cat", "Even fool knows that keys are made to unlock locks!","Yeah.");
            getBack(imageView);
        }
    }

    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    private void progressBarIncrement(int diff) {
        progressBar.incrementProgressBy(diff);
    }
    public void setInvisible(ImageView imageView) {
        imageView.setVisibility(View.INVISIBLE);
    }


    private boolean wereOverlappedOld(View view, View viewActive) {
        if(viewActive.getY()+viewActive.getHeight()>view.getY()&&viewActive.getY()<view.getY()+view.getHeight()  //Имбо код для вычисления, было ли касание Сам написал, немножко горжусь :D
                &&viewActive.getX()+viewActive.getWidth()>view.getX()&&viewActive.getX()<view.getX()+view.getWidth()){return true;}else {return false;}
    }  //Этот метод устарел. Он возвращал true если объект касался другого своим принтом, даже краешком. Новый метод возвращает true если объект касается другого ближе к центру.
    private boolean wereOverlapped(View view, View viewActive) {
        int viewHeightDivided=view.getHeight()/4;
        int viewWidthDivided=view.getWidth()/4;
        if(viewActive.getY()+viewActive.getHeight()>view.getY()+viewHeightDivided&&viewActive.getY()<view.getY()+view.getHeight()-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>view.getX()+viewWidthDivided&&viewActive.getX()<view.getX()+view.getWidth()-viewWidthDivided){return true;}else {return false;}
    }
    private boolean wereOverlapped2(int tt) {
        if (tt == 1) {
            if(canOpenerY+canOpenerHeight>tunaCanY&&canOpenerY<tunaCanY+tunaCanHeight
                    &&canOpenerX+canOpenerWidth>tunaCanX&&canOpenerX<tunaCanX+ tunaCanWidth){return true;}else {return false;}
        } else if(tt==2){
            if(tunaCanY+tunaCanHeight>canOpenerY&&tunaCanY<canOpenerY+canOpenerHeight
                    &&tunaCanX+tunaCanWidth>canOpenerX&&tunaCanX<canOpenerX+ canOpenerWidth){return true;}else {return false;}
        }
        return false;
    }
    private boolean wereOverlappedForProgrammaticObjects(int passive, int active) {
        int passiveHeightDivided;
        int passiveWidthDivided;

        if (passive==TUNA_CAN_ITEM_CODE&&active == CAN_OPENER_ITEM_CODE) {
            if(tunaCanCreated) {
                passiveHeightDivided = listOfImages.get(TunaCanListIndex).getHeight() / 4;
                passiveWidthDivided = listOfImages.get(TunaCanListIndex).getWidth() / 4;
                activeItemY = listOfImages.get(CanOpenerListIndex).getY();
                activeItemX = listOfImages.get(CanOpenerListIndex).getX();
                activeItemHeight = listOfImages.get(CanOpenerListIndex).getHeight();
                activeItemWidth = listOfImages.get(CanOpenerListIndex).getWidth();
                passiveItemX = listOfImages.get(TunaCanListIndex).getX();
                passiveItemY = listOfImages.get(TunaCanListIndex).getY();
                passiveItemHeight = listOfImages.get(TunaCanListIndex).getHeight();
                passiveItemWidth = listOfImages.get(TunaCanListIndex).getWidth();
                if (activeItemY + activeItemHeight > passiveItemY + passiveHeightDivided && activeItemY < passiveItemY + passiveItemHeight - passiveHeightDivided
                        && activeItemX + activeItemWidth > passiveItemX + passiveWidthDivided && activeItemX < passiveItemX + passiveItemWidth - passiveWidthDivided) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if(passive==CAN_OPENER_ITEM_CODE&&active==TUNA_CAN_ITEM_CODE){
            passiveHeightDivided=listOfImages.get(CanOpenerListIndex).getHeight()/4;
            passiveWidthDivided=listOfImages.get(CanOpenerListIndex).getWidth()/4;
            activeItemY=listOfImages.get(TunaCanListIndex).getY();
            activeItemX=listOfImages.get(TunaCanListIndex).getX();
            activeItemHeight=listOfImages.get(TunaCanListIndex).getHeight();
            activeItemWidth=listOfImages.get(TunaCanListIndex).getWidth();
            passiveItemX=listOfImages.get(CanOpenerListIndex).getX();
            passiveItemY=listOfImages.get(CanOpenerListIndex).getY();
            if(activeItemY+activeItemHeight>passiveItemY+passiveHeightDivided&&activeItemY<passiveItemY+passiveItemHeight-passiveHeightDivided
                    &&activeItemX+activeItemWidth>passiveItemX+passiveWidthDivided&&activeItemX<passiveItemX+ passiveItemWidth-passiveWidthDivided){return true;}else {return false;}
        }
        return false;
    }

    private void makeText(String a) {
        Toast.makeText(getBaseContext(), a,Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onBackPressed() {
        dialog=new Dialog(BonusLevelOne.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText("Cancel");
        back.setOnClickListener(close2());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText("Go to menu");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BonusLevelOne.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText("Go to main menu?");
        text2.setText("Progress of current level will be lost.");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void checkThreadsBeforeExit() {
        if(controlSubThread2!=null){
            controlSubThread2.stop();
            controlSubThread2.destroy();
        }
        if(controlSubThread!=null){
            controlSubThread.stop();
            controlSubThread.destroy();
        }
    }

    @NonNull
    private DialogInterface.OnKeyListener dialogOnBackPressed() {
        return new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        };
    }
    @NonNull
    private static View.OnClickListener close(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getBack(imageView);
            }
        };
    }
    @NonNull
    private static View.OnClickListener close2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        };
    }
    private View.OnClickListener getToMain() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BonusLevelOne.this, MainActivity.class);
                startActivity(intent);
            }
        };

    }
    public void checkVictory(){
        if(progressBar.getProgress()>=13){
            checkThreadsBeforeExit();
            levelOver(true);
        } else if(!alive){
            checkThreadsBeforeExit();
            levelOver(false);
        }
    }
    public void levelOver(Boolean win){
        dialog=new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        dialog.setContentView(R.layout.game_popup_menu2);
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        end=dialog.findViewById(R.id.btnOk);
        endB=dialog.findViewById(R.id.btnBack);
        icon=dialog.findViewById(R.id.img1);
        endB.setText("Back to weather");
        endB.setOnClickListener(weather());

        if(win){
            text1.setText("Like a Boss!");
            text2.setText("You have satisfied the cat and passed old level 2. Hooray!");
            end.setText("To main menu");
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_boss));
            end.setOnClickListener(toLevelTwo());
        }else{
            text1.setText("The cat is dead");
            text2.setText("Probably you have to repeat from the beginning");
            end.setText("Start over");
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));
            end.setOnClickListener(restartLevel());
            cardView=dialog.findViewById(R.id.cardView);

            cardView.setCardBackgroundColor(getResources().getColor(R.color.pinkNotif));
            end.setBackground(getResources().getDrawable(R.drawable.pink_button));
            endB.setBackground(getResources().getDrawable(R.drawable.pink_button));


        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    @NonNull
    private View.OnClickListener weather() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BonusLevelOne.this,MainActivity.class);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener toLevelTwo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BonusLevelOne.this, MainActivity.class);
                //intent.putExtra(Fragment_feedback.KEY, 1);
                startActivity(intent);
            }
        };
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
        ImageView imageView=new ImageView(getApplicationContext());
        back.setOnClickListener(close(imageView));
        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
































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
                switch (moveCode) {
                    case BOX_MOVE_CODE:
                        boxMoveAction();
                        break;
                    case CAT_MOVE_CODE:
                        catMoveAction();
                        break;
                    default:
                }
            }
        }

        private void boxMoveAction() {
            try {
                Thread.sleep(littleInterval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            boxImage.setTranslationX(boxImage.getTranslationX()+1);
            if(boxImage.getX()==constraintLayout.getWidth()){
                running=false;
            }
        }
        private void catMoveAction() {
            try {
                Thread.sleep(littleInterval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            catImage.setTranslationX(catImage.getTranslationX()+1);
            if(catImage.getX()>constraintLayout.getWidth()){
                checkThreadsBeforeExit();
                msg.arg1=1;
                handler.sendMessage(msg);

            }
        }
    }




}

