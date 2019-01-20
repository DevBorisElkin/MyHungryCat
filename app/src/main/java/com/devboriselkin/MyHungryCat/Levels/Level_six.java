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
import android.support.v4.app.INotificationSideChannel;
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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_six extends AppCompatActivity {


    public static float xOld, yOld;
    public int check;

    static ImageView tree1, tree2,pineCone1,pineCone2,pineCone3 ,squirrel , fox,
            rabbit, owl, mole, cashier, chest1, chest2, cat, plant, goldBag, itemSpawn, key, carrot, bush, squirrel_falling, stomp, sign, table;
    static ImageView item1, item2, icon;
    CardView cardView;
    static ImageView restart;

    SharedPreferences mySharedPref;

    ConstraintLayout constraintLayout;

    float dX, dY;
    int tree1CutCounter=0;
    int tree2CutCounter=0;
    int tree2ClickCounter =0;
    int tree2HammerHitCounter =0;
    int tree1HammerHitCounter =0;
    int moleTouchCounter=0;
    int squirrelRecievedPineCones=0;
    int foxRecievedPineCones=0;
    int moneyAmount=0;
    int rand;
    Random random = new Random();
    int catClickCounter=0;

    boolean tree2IsStaying=true;
    boolean tree1IsStaying=true;
    boolean bushWasNotCut=true;
    boolean squirrelRightFellDown=false;
    boolean squirrelLeftFellDown=false;
    boolean carrotSleepy=false;
    boolean rabbitSleepy=false;
    boolean wasNotSpawned=true;
    boolean wasNotSet=true;
    boolean catAlive=true;
    boolean foxAlive=true;
    boolean owlAlive=true;
    boolean rabbitAlive=true;
    boolean cashRegisterOpened=false;



    public static final int CasePineConeToSquirrel=1;
    public static final int CasePineConeToMole=2;
    public static final int CasePineConeToFox=3;
    public static final int CasePlantToTheMole=4;
    public static final int CasePlantToTheFox=5;
    public static final int CasePlantToTheOwl=6;
    public static final int CaseBootToMole=7;
    public static final int CaseBottleToMole=8;
    public static final int CaseCandyToRabbit=9;
    public static final int CaseDwarfToMole=10;
    public static final int CaseBananaSkinToRabbit=11;
    public static final int CaseAxeToMole=12;
    public static final int CaseAxeToOwl=13;
    public static final int CaseAxeToFox=14;
    public static final int CaseSquirrelToFox=15;
    public static final int CaseHammerToOwl=16;
    public static final int CaseRabbitToFox=17;
    public static final int CaseHammerToFox=18;
    public static final int CaseDwarfToOwl=19;
    public static final int CaseCandyToSquirrel=20;
    public static final int CaseCutterToOwl=21;
    public static final int CaseCandyToFox=23;





    public static final int CaseSleepingDrugToMole=22;


    public static final int SHOP_CASE_OWL=1;
    public static final int SHOP_CASE_MOLE=2;
    public static final int SHOP_CASE_FOX=3;
    public static final int SHOP_CASE_SQUIRREL =4;



    public static final int CONE_FALLING_CODE_1=1;
    public static final int CONE_FALLING_CODE_2=2;
    public static final int PINE_CONE_ITEM_CODE =3;
    public static final int PLANT_ITEM_CODE=4;
    public static final int COIN_BAG_ITEM_CODE=5;
    public static final int COIN_ONE_ITEM_CODE =6;
    public static final int COIN_TWO_ITEM_CODE =7;
    public static final int COIN_FIVE_ITEM_CODE =8;
    public static final int COIN_TEN_ITEM_CODE =9;
    public static final int KEY_ONE_ITEM_CODE =10;
    public static final int BOOT_ITEM_CODE=11;
    public static final int BOTTLE_ITEM_CODE=12;
    public static final int KEY_FALLING_CODE=13;
    public static final int CANDY_ITEM_CODE=14;
    public static final int KEY_TWO_ITEM_CODE=15;
    public static final int DWARF_ITEM_CODE=16;
    public static final int BANANA_SKIN_ITEM_CODE =17;
    public static final int AXE_ITEM_CODE=18;
    public static final int MOUSE_ITEM_CODE=19;
    public static final int CARROT_ITEM_CODE=20;
    public static final int CARROT_MOVE_CODE=21;
    public static final int CUTTER_ITEM_CODE=22;
    public static final int PLASTIC_CARD_ITEM_CODE=23;
    public static final int SQUIRREL_FALLING_CODE=24;
    public static final int SQUIRREL_ITEM_CODE=25;
    public static final int HAMMER_ITEM_CODE=26;
    public static final int SQUIRREL_RIGHT_FALLING_CODE=27;
    public static final int SLEEPING_DRUG_ITEM_CODE =28;
    public static final int RABBIT_ITEM_CODE=29;
    public static final int FOX_MOVE_CODE=30;
    public static final int DYNAMITE_ITEM_CODE=31;
    public static final int LIGHTER_ITEM_CODE=32;
    public static final int BOOM_IS_COMING_CODE=33;
    public static final int CHICKEN_ITEM_CODE=34;

////////////////////////////////////////////////
    //SHOP
    public static final int CANDY_PRICE=4;
    public static final int GREY_KEY_PRICE=7;
    public static final int AXE_PRICE=13;
    public static final int MOUSE_PRICE=50;
    public static final int CARROT_PRICE=3;
    public static final int SLEEPING_DRUG_PRICE=14;
    public static final int DYNAMITE_PRICE=60;
    public static final int HAMMER_PRICE=16;
    public static final int CUTTER_PRICE=29;
    public static final int LIGHT_PRICE=15;
    public static final int CHICKEN_PRICE=11;

    public int Candy_amount=2;
    public int Grey_key_amount=1;
    public int Axe_amount=1;
    public int Mouse_amount=1;
    public int Carrot_amount=1;
    public int Sleeping_drug_amount=1;
    public int Dynamite_amount =1;
    public int Light_amount=1;
    public int Cutter_amount=1;
    public int Hammer_amount =1;
    public int ChickenAmount = 1;


///////////////////////////////////////////////////////


    ControlSubThread controlSubThread1;
    ControlSubThread controlSubThread2;
    ControlSubThread controlSubThread3_keyFalling;
    ControlSubThread controlSubThread4_carrotFalling;
    ControlSubThread controlSubThread5_falling_squirrel;
    ControlSubThread controlSubThread5_falling_squirrel_right;
    ControlSubThread controlSubThread6_fox_moving;
    ControlSubThread controlSubThread7_boom_is_coming;
    //

    static Dialog dialog;
    LinearLayout coinCounterLayout;
    TextView text1, text2, moneyCountTXT, price1, price2, price3, price4, left_count_1_txt, left_count_2_txt, left_count_3_txt, left_count_4_txt;
    Button btn_buy1, btn_buy2, btn_buy3, btn_buy4, btn_closeShop;


    TextView currentMoneyTxt;
    Button back, ok;


    List<ImageView> goldenBagList=new ArrayList<>();
    List<ImageView> dynamiteList=new ArrayList<>();
    List<ImageView> itemsToDisappear= new ArrayList<>();
    List<ImageView> blowImageList=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.level_six);
        initUI();
    }

    private void initUI() {
        initViews();
        checkMusic();
        setListeners();
        makeInfoForBeginning(getString(R.string.nhbgf),getString(R.string.jhgf) +
               getString(R.string.kjhgftyu),
               getString(R.string.uytr));

    }
    private void initViews() {
        restart=findViewById(R.id.restart);
        key=findViewById(R.id.key);
        tree1=findViewById(R.id.tree1);
        tree2=findViewById(R.id.tree2);
        squirrel=findViewById(R.id.squirrel);
        pineCone1=findViewById(R.id.pinecone1);
        pineCone2=findViewById(R.id.pinecone2);
        pineCone3=findViewById(R.id.pinecone3);
        constraintLayout=findViewById(R.id.layout);
        fox=findViewById(R.id.fox);
        mole=findViewById(R.id.mole);
        cat=findViewById(R.id.cat);
        owl=findViewById(R.id.owl);
        rabbit=findViewById(R.id.rabbit);
        chest1=findViewById(R.id.chest1);
        chest2=findViewById(R.id.chest2);
        plant=findViewById(R.id.plant);
        cashier=findViewById(R.id.cashier);
        coinCounterLayout=findViewById(R.id.coin_counter);
        moneyCountTXT=findViewById(R.id.moneyCount);
        itemSpawn=findViewById(R.id.itemSpawn);
        item1=findViewById(R.id.coin5);
        item2=findViewById(R.id.somethingBehindTree2);
        carrot=findViewById(R.id.carrot);
        bush=findViewById(R.id.bush);
        squirrel_falling=findViewById(R.id.squirrel_falling);
        stomp=findViewById(R.id.stomp);
        sign=findViewById(R.id.sign);
        table=findViewById(R.id.table);
        /////////////////
        itemsToDisappear.add(key);
       //itemsToDisappear.add(tree1);
       //itemsToDisappear.add(tree2);
        itemsToDisappear.add(squirrel);
        itemsToDisappear.add(pineCone1);
        itemsToDisappear.add(pineCone2);
        itemsToDisappear.add(pineCone3);
     //   itemsToDisappear.add(fox);
        itemsToDisappear.add(mole);
       // itemsToDisappear.add(cat);
      // itemsToDisappear.add(owl);
      // itemsToDisappear.add(rabbit);
      //  itemsToDisappear.add(chest1);
      //  itemsToDisappear.add(chest2);
        itemsToDisappear.add(plant);
        itemsToDisappear.add(cashier);
        itemsToDisappear.add(carrot);
       // itemsToDisappear.add(bush);
        itemsToDisappear.add(squirrel_falling);
      //  itemsToDisappear.add(stomp);
    //   itemsToDisappear.add(sign);
    //   itemsToDisappear.add(table);
        mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
    }
    private void checkMusic() {

        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_CODE){

        }else if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE){
            musicPlayer.stopBackgroundMusic();
            musicPlayer.releaseMusicInMenu();
            musicPlayer.initForestMusic();
            musicPlayer.startForestMusic();
        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            MainActivity.musicPlayer.release();
            musicPlayer.initForestMusic();
            musicPlayer.startForestMusic();
        }

    }

    private void setListeners() {
        coinCounterLayout.setVisibility(View.INVISIBLE);
        tree1.setOnClickListener(treeOneWasClicked());
        tree2.setOnClickListener(treeTwoWasClicked());
        pineCone1.setOnTouchListener(touch(pineCone1, PINE_CONE_ITEM_CODE));
        plant.setOnTouchListener(touch(plant, PLANT_ITEM_CODE));
        mole.setOnClickListener(moleWasTouched());;
        restart.setOnClickListener(restartLevel());
        fox.setOnClickListener(foxClocked());
        owl.setOnClickListener(owlWasTouched());
        rabbit.setOnClickListener(rabbitWasClicked());
        squirrel.setOnClickListener(squirrelWasTouched());
        cat.setOnClickListener(catClicked());
        cashier.setOnClickListener(cashRegisterClicked());
        bush.setOnClickListener(BushClicked());
        chest2.setOnClickListener(chestClicked());
        chest1.setOnClickListener(chestClicked());
    }

    @NonNull
    private View.OnClickListener restartLevel() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Level_six.this, Level_six.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                checkThreadsBeforeExit();
                startActivity(intent);
            }
        };
    }

    @NonNull
    private View.OnClickListener chestClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.soundPlayer.makeWoodCutSound2();
            }
        };
    }

    @NonNull
    private View.OnClickListener BushClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bushWasNotCut){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.mnbvfd),getString(R.string.tredv));
                    randomCatMeowSound();
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener cashRegisterClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cashRegisterOpened){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.hgfdg),getString(R.string.dvsrgs));
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
                if(catAlive){
                    randomCatMeowSound();
                    if(catClickCounter==0){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ftbdbd),getString(R.string.fbgwfe));
                    }else if(catClickCounter==1){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.gbfgbdwr),getString(R.string.nedgsgegs));
                    }else if(catClickCounter==2){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.bberbb),getString(R.string.nfgbvsfv));
                    }else if(catClickCounter==3){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ergerg),getString(R.string.ergergegrg));
                    }else if(catClickCounter==4){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.bfgnfg),getString(R.string.ergbv));
                    }else if(catClickCounter==5){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.cbnfgnf),getString(R.string.kujy));
                    }else if(catClickCounter==6){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.mnbvr),getString(R.string.nbvcb));
                    }else if(catClickCounter==7){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ggryu),getString(R.string.awdawaa));
                    }else if(catClickCounter==8){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.www),getString(R.string.wwe));
                    }else if(catClickCounter==9){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.wwfg),getString(R.string.srvsv));
                    }else if(catClickCounter==10){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.nhgt),getString(R.string.hfyh));
                    }else if(catClickCounter>10){
                        makeInfoDialogWindow(getString(R.string.cat),getString(R.string.juyhrtg),getString(R.string.tgrf));
                    }
                    catClickCounter++;
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener squirrelWasTouched() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(squirrel.getVisibility()==View.VISIBLE&&!squirrelRightFellDown){
                    MainActivity.soundPlayer.makeSquirrelSound();
                    makeInfoDialogWindow(getString(R.string.nbv),getString(R.string.fgbdfvs),getString(R.string.gngn));
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener rabbitWasClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rabbitSleepy){
                    MainActivity.soundPlayer.makeRabbitSound();
                    makeInfoDialogWindow(getString(R.string.iyujyhrg),getString(R.string.nbgvfcd),getString(R.string.mngbv));
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener owlWasTouched() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(owlAlive){
                    MainActivity.soundPlayer.makeOwlSound();
                    makeInfoDialogWindow(getString(R.string.nbgd),getString(R.string.dbdfb),getString(R.string.bdgbdfg));
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener foxClocked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foxAlive){
                    MainActivity.soundPlayer.makeFoxSound();
                    makeInfoDialogWindow(getString(R.string.trhtegr),getString(R.string.dfbdbdbe),getString(R.string.fnnf));
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
                case PINE_CONE_ITEM_CODE:
                    pineConeOverlapAction(imageView);
                    break;
                case PLANT_ITEM_CODE:
                    plantOverlapAction(imageView);
                    break;
                case COIN_ONE_ITEM_CODE:
                    coinOverlapAction(imageView, COIN_ONE_ITEM_CODE);
                    break;
                case COIN_TWO_ITEM_CODE:
                    coinOverlapAction(imageView, COIN_TWO_ITEM_CODE);
                    break;
                case COIN_FIVE_ITEM_CODE:
                    coinOverlapAction(imageView, COIN_FIVE_ITEM_CODE);
                    break;
                case COIN_TEN_ITEM_CODE:
                    coinOverlapAction(imageView, COIN_TEN_ITEM_CODE);
                    break;
                case KEY_ONE_ITEM_CODE:
                    keyOverlapAction(imageView);
                    break;
                case BOOT_ITEM_CODE:
                    bootOverlapAction(imageView);
                    break;
                case BOTTLE_ITEM_CODE:
                    bottleOverlapAction(imageView);
                    break;
                case COIN_BAG_ITEM_CODE:
                    coinBagOverlapAction(imageView);
                    break;
                case CANDY_ITEM_CODE:
                    candyOverlapAction(imageView);
                    break;
                case KEY_TWO_ITEM_CODE:
                   keyTwoOverlapAction(imageView);
                    break;
                case DWARF_ITEM_CODE:
                    dwarfOverlapAction(imageView);
                    break;
                case BANANA_SKIN_ITEM_CODE:
                    bananaSkinOverlapAction(imageView);
                    break;
                case AXE_ITEM_CODE:
                    axeOverlapAction(imageView);
                    break;
                case MOUSE_ITEM_CODE:
                    mouseOverlapAction(imageView);
                    break;
                case CARROT_ITEM_CODE:
                    carrotOverlapAction(imageView);
                    break;
                case CUTTER_ITEM_CODE:
                    cutterOverlapAction(imageView);
                    break;
                case PLASTIC_CARD_ITEM_CODE:
                   plasticCardOverlapAction(imageView);
                    break;
                case SQUIRREL_ITEM_CODE:
                    squirrelOverlapAction(imageView);
                    break;
                case HAMMER_ITEM_CODE:
                    hammerOverlapAction(imageView);
                    break;
                case SLEEPING_DRUG_ITEM_CODE:
                    drugOverlapAction(imageView);
                    break;
                case RABBIT_ITEM_CODE:
                    rabbitOverlapAction(imageView);
                    break;
                case DYNAMITE_ITEM_CODE:
                    dynamiteOverlapAction(imageView);
                    break;
                case LIGHTER_ITEM_CODE:
                    lighterOverlapAction(imageView);
                    break;
                case CHICKEN_ITEM_CODE:
                    chickenOverlapAction(imageView);
                    break;
                default:makeText("wtf");
            }
        } else{return false;}
        return true;
    }

    private void chickenOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            redraw(cat, getResources().getDrawable(R.drawable.poisoned_cat));
            levelOver2(false);
        }else if(wereOverlapped(mole, imageView)){
            makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.awdawdawdfawd),"Hmm");
            getBack(imageView);
            makeMoleRandomSound();
        }else if(wereOverlapped(owl, imageView)){
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.trhth),getString(R.string.bdfbdfb));
            getBack(imageView);
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.nbve),getString(R.string.bgbg),getString(R.string.yerg));
            MainActivity.soundPlayer.makeFoxSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.bdbdb),getString(R.string.rynrnrt),getString(R.string.dbdbdb));
            MainActivity.soundPlayer.makeRabbitSound();
        }
    }

    private void lighterOverlapAction(ImageView imageView) {
        if(dynamiteList.size()>0){
            if(wereOverlapped(dynamiteList.get(0), imageView)){
                imageView.setX(dynamiteList.get(0).getX()-60);
                imageView.setY(dynamiteList.get(0).getY()+5);
                controlSubThread7_boom_is_coming=new ControlSubThread(1,0,BOOM_IS_COMING_CODE, dynamiteList.get(0));
                controlSubThread7_boom_is_coming.start();

        } }
        if(wereOverlapped(mole, imageView)){
            makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.tyjtyhfh),getString(R.string.fhnfnf));
            makeMoleRandomSound();
            getBack(imageView);
        }else if(wereOverlapped(owl, imageView)){
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.fgnfgn),getString(R.string.fgbndbd));
            MainActivity.soundPlayer.makeOwlSound();
            getBack(imageView);
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.drdrgd),getString(R.string.drgdrg),getString(R.string.srgsfgs));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void dynamiteOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.dbdtbd),getString(R.string.rthrthr));
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)){
            makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.bfbfb),getString(R.string.fgbfgbw));
            getBack(imageView);
            makeMoleRandomSound();
        }else if(wereOverlapped(owl, imageView)){
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.rgerg),getString(R.string.dbdbd));
            getBack(imageView);
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.ddsd),getString(R.string.dbdb),getString(R.string.gbfgb));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void rabbitOverlapAction(ImageView imageView) {
        if(wereOverlapped(fox, imageView)){
         makeYesNoDialog(getString(R.string.fox),getString(R.string.gnffgnb),getString(R.string.sf),getString(R.string.mk),imageView, CaseRabbitToFox);
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.mhfn),getString(R.string.efwe));
            randomCatMeowSound();
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeInfoDialogWindow(getString(R.string.dfbdf),getString(R.string.awd),getString(R.string.hhht));
            getBack(imageView);
        }else if(wereOverlapped(mole, imageView)){
            makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.tyryr),getString(R.string.tetfr));
            makeMoleRandomSound();
            getBack(imageView);
        }else if(wereOverlapped(owl, imageView)){
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.dfbd),getString(R.string.fddf));
            MainActivity.soundPlayer.makeOwlSound();
            getBack(imageView);
        }
    }

    private void drugOverlapAction(ImageView imageView) {
        if(wereOverlapped(carrot, imageView)&&!carrotSleepy){
            carrotSleepy=true;
            redraw(carrot,getResources().getDrawable(R.drawable.sleepy_carrot));
            imageView.setY(carrot.getY()+20);
            imageView.setX(carrot.getX()-80);
            MainActivity.soundPlayer.makePoisoningSound();
        }else if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.ergergv),getString(R.string.dvsdvs),getString(R.string.dfrgwgb),imageView, CaseSleepingDrugToMole);
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.yutyrtf),getString(R.string.dfvsfvs));
            randomCatMeowSound();
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.efbeb),getString(R.string.bvsdc));
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.qwev),getString(R.string.okyjhrgfvsc),getString(R.string.gevfcd));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void hammerOverlapAction(ImageView imageView) {
        if(wereOverlapped(tree1, imageView)&&tree1IsStaying){
            tree1HammerHitCounter++;
            MainActivity.soundPlayer.makeHittingChestSound();
            if(tree1HammerHitCounter ==6){
                squirrelRightFellDown=true;
                controlSubThread5_falling_squirrel_right=new ControlSubThread(1,0,SQUIRREL_RIGHT_FALLING_CODE, squirrel);
                controlSubThread5_falling_squirrel_right.start();

            }
        }else if(wereOverlapped(tree2, imageView)&&tree2IsStaying){
            tree2HammerHitCounter++;
            MainActivity.soundPlayer.makeHittingChestSound();
            if(tree2HammerHitCounter ==8){
                squirrelLeftFellDown=true;
                controlSubThread5_falling_squirrel=new ControlSubThread(1,0,SQUIRREL_FALLING_CODE, squirrel_falling);
                controlSubThread5_falling_squirrel.start();
            }
        }else if(wereOverlapped(owl, imageView)){
            makeYesNoDialog(getString(R.string.owl),getString(R.string.ebegebe),getString(R.string.regege),getString(R.string.wefwefgw),imageView,CaseHammerToOwl);
        }else if(wereOverlapped(fox, imageView)){
            makeYesNoDialog(getString(R.string.rergergerg),getString(R.string.egergergerg),getString(R.string.ergegeggerg),getString(R.string.ergegergv),imageView, CaseHammerToFox);
        }else if(wereOverlapped(mole, imageView)){
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.drgergeg),getString(R.string.ergerger));
            getBack(imageView);
            makeMoleRandomSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            makeInfoDialogWindow(getString(R.string.ergergsv),getString(R.string.sdvqgh),getString(R.string.hbg));
            getBack(imageView);
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeInfoDialogWindow(getString(R.string.werewr),getString(R.string.bvc),getString(R.string.olkj));
            getBack(imageView);
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.uytrfgb),getString(R.string.ae));
            randomCatMeowSound();
        }
    }

    private void squirrelOverlapAction(ImageView imageView) {
        if(wereOverlapped(fox, imageView)){
            makeYesNoDialog(getString(R.string.ergg),getString(R.string.sfsf),getString(R.string.beb),getString(R.string.bbfb), imageView, CaseSquirrelToFox);
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeInfoDialogWindow(getString(R.string.etge),getString(R.string.gbbee),getString(R.string.rbgvfc));
            getBack(imageView);
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(mole, imageView)){
            makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.brgedvc),getString(R.string.ergergw));
            getBack(imageView);
            makeMoleRandomSound();
        }else if(wereOverlapped(owl, imageView)){
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.eege),getString(R.string.fdvdfv));
            getBack(imageView);
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.fbeb),getString(R.string.adqwd),getString(R.string.rerbeb));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.egreg),getString(R.string.oiuy));
            randomCatMeowSound();
        }
    }

    private void plasticCardOverlapAction(ImageView imageView) {
        if(wereOverlapped(cashier, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            redraw(cashier, getResources().getDrawable(R.drawable.cashier_opened));
            cashRegisterOpened=true;
            MainActivity.soundPlayer.makeCashierOpeningSound();
            for(int i=0;i<6;i++){
                rand=random.nextInt(20);
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),cashier.getX()+cashier.getWidth()/3+rand, cashier.getY()+cashier.getHeight()/2+rand, 63,63);
            }
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.bvcf),getString(R.string.okiuyjh));
            makeMoleRandomSound();
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.yjug),getString(R.string.qweqwe));
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.oijh),getString(R.string.etvf),getString(R.string.brgbrb));
            MainActivity.soundPlayer.makeFoxSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.poiu),getString(R.string.ergerga), getString(R.string.bvvv));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeInfoDialogWindow(getString(R.string.bfbfbf),getString(R.string.erbe),getString(R.string.bbgbg));
            getBack(imageView);
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rrwegv),getString(R.string.hnjkl));
            randomCatMeowSound();
        }
    }

    private void cutterOverlapAction(ImageView imageView) {
        if(wereOverlapped(bush, imageView)&&bushWasNotCut){
            bushWasNotCut=false;
            redraw(bush, getResources().getDrawable(R.drawable.bush_cut));
            MainActivity.soundPlayer.makeBushCutSound();
            addItemProgrammatically(itemSpawn, itemSpawn, PLASTIC_CARD_ITEM_CODE, getResources().getDrawable(R.drawable.plastic_card),bush.getX()+bush.getWidth()/2, bush.getY()+bush.getHeight()/2,62,60);
        }else if(wereOverlapped(owl, imageView)){
            makeYesNoDialog(getString(R.string.owl),getString(R.string.bgbgfff),getString(R.string.rgr),getString(R.string.erfefe), imageView, CaseCutterToOwl);
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.rgbrb),getString(R.string.sdfsd));
            makeMoleRandomSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.bgbge),getString(R.string.sdvsd), getString(R.string.gbbrds));
            MainActivity.soundPlayer.makeFoxSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.bgbgb),getString(R.string.aaaaff), getString(R.string.dbdfbdb));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeInfoDialogWindow(getString(R.string.tgtgtg),getString(R.string.dfvdfvdfv),getString(R.string.fddfbdfb));
            getBack(imageView);
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.vvvbvbvb),getString(R.string.rbtrb));
            randomCatMeowSound();
        }
    }

    private void carrotOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.tgtgt),getString(R.string.gbg));
            randomCatMeowSound();
        }else if(wereOverlapped(rabbit, imageView)){
            rabbitGotCarrot(imageView);
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.sdfsdffr),getString(R.string.sssdf));
            makeMoleRandomSound();
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.yjuiol),getString(R.string.iokj));
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.awdawdg),getString(R.string.vdfvdf),getString(R.string.qwedwf));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void rabbitGotCarrot(final ImageView imageView) {
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.carrot_to_rabbit_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());


        btn_buy1=dialog.findViewById(R.id.btn1);
        btn_buy2=dialog.findViewById(R.id.btn2);

        btn_buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                if(carrotSleepy){
                    redraw(rabbit, getResources().getDrawable(R.drawable.rabbit_sleepy));
                    rabbit.setOnTouchListener(touch(rabbit, RABBIT_ITEM_CODE));
                    MainActivity.soundPlayer.makeCatEaingSound2();
                    rabbitSleepy=true;
                }
            }
        });
        btn_buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                rand=random.nextInt(20);
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),rabbit.getX()+rabbit.getWidth()+20+rand, rabbit.getY()+10+rand, 61,61);
                MainActivity.soundPlayer.makeRabbitSound();
            }
        });
        btn_closeShop=dialog.findViewById(R.id.close_shop);
        btn_closeShop.setOnClickListener(closeShop(imageView));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void mouseOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            levelOver(true);
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.brbrb),":(");
            makeMoleRandomSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.tbfvddvfb),getString(R.string.fdvdfvwwe),getString(R.string.kuyjhg));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void axeOverlapAction(ImageView imageView) {
        if(wereOverlapped(tree1, imageView)&&tree1IsStaying){
            if(tree1CutCounter ==3){
                woodCutSound();
                redraw(tree1, getResources().getDrawable(R.drawable.tree_c1));
            }else if(tree1CutCounter ==6){
                woodCutSound();
                redraw(tree1, getResources().getDrawable(R.drawable.tree_c2));
            }else if(tree1CutCounter ==9){
                woodCutSound();
                redraw(tree1, getResources().getDrawable(R.drawable.tree_c3));
            }else if(tree1CutCounter ==12){
                MainActivity.soundPlayer.makeTreeFallSound();
                tree1IsStaying=false;
                redraw(tree1, getResources().getDrawable(R.drawable.tree_c4));
                if(!squirrelRightFellDown){
                    squirrel.setVisibility(View.INVISIBLE);
                }
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),tree1.getX()+tree1.getWidth()/2-20,tree1.getY()+tree1.getHeight()/2+20,61,61);
            }else{
                woodCutSound();
            }
            tree1CutCounter++;
        }else if(wereOverlapped(tree2, imageView)&&tree2IsStaying){
            if(tree2CutCounter==3){
                woodCutSound();
                redraw(tree2, getResources().getDrawable(R.drawable.tree2_c1));
            }else if(tree2CutCounter==6){
                woodCutSound();
                redraw(tree2, getResources().getDrawable(R.drawable.tree2_c2));
            }else if(tree2CutCounter==9){
                woodCutSound();
                redraw(tree2, getResources().getDrawable(R.drawable.tree2_c3));
            }else if(tree2CutCounter==12){
                MainActivity.soundPlayer.makeTreeFallSound();
                redraw(tree2, getResources().getDrawable(R.drawable.tree2_c4));
                tree2IsStaying=false;
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),tree2.getX()+tree2.getWidth()/2+10, tree2.getY()+tree2.getHeight()/3+tree2.getHeight()/4, 55,55);
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),tree2.getX()+tree2.getWidth()/2, tree2.getY()+tree2.getHeight()/3+tree2.getHeight()/4+20, 58,58);
            }else{
                woodCutSound();
            }
            tree2CutCounter++;
        }else if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.trgrtgrt),getString(R.string.ffdvdfv),getString(R.string.dfvdfvdfvbf),imageView, CaseAxeToMole);
        }else if(wereOverlapped(rabbit, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.dvdfvd),getString(R.string.gbhnjikol),getString(R.string.fgbvcd));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(squirrel, imageView)&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.dvdvd),getString(R.string.fbdbdbdb),getString(R.string.bbbb));
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(owl, imageView)){
            makeYesNoDialog(getString(R.string.owl),getString(R.string.rtgyhujik),getString(R.string.adbfb),getString(R.string.bbrtbrtb),imageView,CaseAxeToOwl);
        }else if(wereOverlapped(fox, imageView)){
            makeYesNoDialog(getString(R.string.bgbgfb),getString(R.string.bdbdbs),getString(R.string.dfvdfvdvdv),getString(R.string.rgrnrtn),imageView,CaseAxeToFox);
        }
    }

    private void bananaSkinOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.ererre),getString(R.string.hnhnhn));
            makeMoleRandomSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            makeYesNoDialog(getString(R.string.vbfb),getString(R.string.ggbrbrb),getString(R.string.gbgbgb),getString(R.string.fdvdfvd),imageView, CaseBananaSkinToRabbit);
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.ergefvd),getString(R.string.bbbdb));
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.tgtg),getString(R.string.sdvdsfvdv),getString(R.string.bbbdfb));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void dwarfOverlapAction(ImageView imageView) {
        if (wereOverlapped(mole, imageView)) {
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.erger),getString(R.string.sdfdf),getString(R.string.erfgn),imageView, CaseDwarfToMole);
        }else if(wereOverlapped(owl, imageView)){
            makeYesNoDialog(getString(R.string.owl),getString(R.string.bbgbge),getString(R.string.awaw),getString(R.string.efsdrgdrg), imageView, CaseDwarfToOwl);
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.rtgrtgrt),getString(R.string.rthrthrt));
            MainActivity.soundPlayer.makeFoxSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.ergergerg),getString(R.string.qweqdqwd),getString(R.string.fgbfgbfgbfgb));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.fvdfvdfv),getString(R.string.dfbdfbdfbf),getString(R.string.wefwegbdv));
            MainActivity.soundPlayer.makeSquirrelSound();
        }
    }

    private void keyTwoOverlapAction(ImageView imageView) {
        if(wereOverlapped(chest1, imageView)){
            MainActivity.soundPlayer.makeChestOpeningSound();
            imageView.setVisibility(View.INVISIBLE);
            redraw(chest1,getResources().getDrawable(R.drawable.forest_box_opened));
            addItemProgrammatically(itemSpawn, itemSpawn, DWARF_ITEM_CODE, getResources().getDrawable(R.drawable.dwarf_dirty2),chest1.getX()+5, chest1.getY()+chest1.getHeight()-10, 95,70);
            addItemProgrammatically(itemSpawn, itemSpawn, BANANA_SKIN_ITEM_CODE, getResources().getDrawable(R.drawable.banana_skin),chest1.getX()+65, chest1.getY()+chest1.getHeight()+10, 85,100);
        }else if(wereOverlapped(owl, imageView)){
            MainActivity.soundPlayer.makeOwlSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.ggrr),getString(R.string.ggrree));
        }else if(wereOverlapped(mole, imageView)){
            makeMoleRandomSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.uikyujh),getString(R.string.rgdgerbb));
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.ergerer),getString(R.string.ergergergerg),getString(R.string.bbdfbfdbdfb));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.brtyju),getString(R.string.dfbdfbdfb),getString(R.string.rgrgtt));
            MainActivity.soundPlayer.makeFoxSound();
        }
    }

    private void candyOverlapAction(ImageView imageView) {
        if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            makeYesNoDialog(getString(R.string.ytrty),getString(R.string.hgfcvb),getString(R.string.juyuj), getString(R.string.bgbgbcdc), imageView, CaseCandyToRabbit);
        }else if(wereOverlapped(fox, imageView)){
            makeYesNoDialog(getString(R.string.bggbg),getString(R.string.awawff),getString(R.string.bgbgbvf),getString(R.string.weferf),imageView,CaseCandyToFox);
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeYesNoDialog(getString(R.string.vbvrfgtbfv),getString(R.string.ergergbfb),getString(R.string.rthrtsc),getString(R.string.vbcbwef),imageView,CaseCandyToSquirrel);
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.bfdvhmhn),getString(R.string.rbgvdf));
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.hyrtgfr),getString(R.string.hrgvf));
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.grbvdfc),getString(R.string.fvvgfb));
            makeMoleRandomSound();
        }
    }

    private void coinBagOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
          makeShopDialog(SHOP_CASE_MOLE, imageView);
          makeMoleRandomSound();

        } else if(wereOverlapped(owl, imageView)){
            MainActivity.soundPlayer.makeOwlSound();
            makeShopDialog(SHOP_CASE_OWL, imageView);
        } else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeShopDialog(SHOP_CASE_SQUIRREL, imageView);
        }else if(wereOverlapped(fox, imageView)){
            MainActivity.soundPlayer.makeFoxSound();
            makeShopDialog(SHOP_CASE_FOX, imageView);
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.rtgedcd),getString(R.string.hnbcvfg));
            randomCatMeowSound();
        }else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.wyje),getString(R.string.wefnfdrg),getString(R.string.rtgvfd));
            MainActivity.soundPlayer.makeRabbitSound();
        }
    }

    private void makeShopDialog(int SHOP_CASE, final ImageView imageView) {
        if(SHOP_CASE==SHOP_CASE_OWL){
                dialog=new Dialog(this);
                dialog.setContentView(R.layout.shop_owl_popup);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setOnKeyListener(dialogOnBackPressed());


                price1=dialog.findViewById(R.id.price1);
                price2=dialog.findViewById(R.id.price2);
                price3=dialog.findViewById(R.id.price3);
                price4=dialog.findViewById(R.id.price4);
                price1.setText("  "+CANDY_PRICE);
                price2.setText("  "+GREY_KEY_PRICE);
                price3.setText(AXE_PRICE+"");
                price4.setText(MOUSE_PRICE+"");

                left_count_1_txt=dialog.findViewById(R.id.left_count1);
                left_count_2_txt=dialog.findViewById(R.id.left_count2);
                left_count_3_txt=dialog.findViewById(R.id.left_count3);
                left_count_4_txt=dialog.findViewById(R.id.left_count4);

                left_count_1_txt.setText(Candy_amount+" X ");
                left_count_2_txt.setText(Grey_key_amount+" X ");
                left_count_3_txt.setText(Axe_amount+" X ");
                left_count_4_txt.setText(Mouse_amount+" X ");


                btn_buy1=dialog.findViewById(R.id.buttonBuy1);
                if(Candy_amount==0)btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                btn_buy2=dialog.findViewById(R.id.buttonBuy2);
                if(Grey_key_amount==0)btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                btn_buy3=dialog.findViewById(R.id.buttonBuy3);
                if(Axe_amount==0)btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                btn_buy4=dialog.findViewById(R.id.buttonBuy4);
                if(Mouse_amount==0)btn_buy4.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));

                final ScrollView scrollView=dialog.findViewById(R.id.scrollView);
                ImageView top=dialog.findViewById(R.id.top);
                ImageView bottom=dialog.findViewById(R.id.bottom);

                top.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scrollView.smoothScrollTo(0,0);
                    }
                });
                bottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scrollView.smoothScrollTo(0,scrollView.getHeight());
                    }
                });


                currentMoneyTxt = dialog.findViewById(R.id.currentMoneyTxt);
                currentMoneyTxt.setText(moneyAmount+"");

                btn_buy1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(moneyAmount>=CANDY_PRICE){
                            if(Candy_amount>0){
                                moneyAmount-=CANDY_PRICE;
                                rand=random.nextInt(30);
                                addItemProgrammatically(itemSpawn, itemSpawn,  CANDY_ITEM_CODE, getResources().getDrawable(R.drawable.candy), owl.getX()+owl.getWidth()+10+rand, owl.getY()+rand, 75, 75);
                                currentMoneyTxt.setText(moneyAmount+"");
                                moneyCountTXT.setText(moneyAmount+"");
                                Candy_amount--;
                                if(Candy_amount==0){
                                    btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                                }
                                left_count_1_txt.setText(Candy_amount+" X ");
                                MainActivity.soundPlayer.makeCashRegisterSound();
                            }
                        }
                    }
                });
                btn_buy2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(moneyAmount>=GREY_KEY_PRICE){
                            if(Grey_key_amount>0){
                                moneyAmount-=GREY_KEY_PRICE;
                                rand=random.nextInt(30);
                                addItemProgrammatically(itemSpawn, itemSpawn, KEY_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.key2), owl.getX()+owl.getWidth()+10+rand, owl.getY()+rand, 60,60);
                                currentMoneyTxt.setText(moneyAmount+"");
                                moneyCountTXT.setText(moneyAmount+"");
                                Grey_key_amount--;
                                if(Grey_key_amount==0){
                                    btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                                }
                                left_count_2_txt.setText(Grey_key_amount+" X ");
                                MainActivity.soundPlayer.makeCashRegisterSound();
                            }


                        }
                    }
                });
                btn_buy3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(moneyAmount>=AXE_PRICE){
                            if(Axe_amount>0){
                                moneyAmount-=AXE_PRICE;
                                addItemProgrammatically(itemSpawn, itemSpawn, AXE_ITEM_CODE, getResources().getDrawable(R.drawable.axe), owl.getX()+owl.getWidth()+20, owl.getY()+20, 155, 90);
                                currentMoneyTxt.setText(moneyAmount+"");
                                moneyCountTXT.setText(moneyAmount+"");
                                Axe_amount--;
                                if(Axe_amount==0){
                                    btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                                }
                                left_count_3_txt.setText(Axe_amount+" X ");
                                MainActivity.soundPlayer.makeCashRegisterSound();
                            }


                        }
                    }
                });
                btn_buy4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(moneyAmount>=MOUSE_PRICE){
                            if(Mouse_amount>0){
                                moneyAmount-=MOUSE_PRICE;
                                addItemProgrammatically(itemSpawn, itemSpawn, MOUSE_ITEM_CODE, getResources().getDrawable(R.drawable.mouse_dead), owl.getX()+owl.getWidth()+20, owl.getY()+20, 80, 70);
                                currentMoneyTxt.setText(moneyAmount+"");
                                moneyCountTXT.setText(moneyAmount+"");
                                Mouse_amount--;
                                if(Mouse_amount==0){
                                    btn_buy4.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                                }
                                left_count_4_txt.setText(Mouse_amount+" X ");
                                MainActivity.soundPlayer.makeCashRegisterSound();
                            }


                        }
                    }
                });


                btn_closeShop=dialog.findViewById(R.id.close_shop);
                btn_closeShop.setOnClickListener(closeShop(imageView));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


        }else if(SHOP_CASE==SHOP_CASE_SQUIRREL){
            dialog=new Dialog(this);
            dialog.setContentView(R.layout.shop_squirrel_popup);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(dialogOnBackPressed());

            final TextView whatSquirrelSays=dialog.findViewById(R.id.text2);
            if(Carrot_amount==0){
                whatSquirrelSays.setText(R.string.dfvdfvjythrtg);
            }
            price1=dialog.findViewById(R.id.price1);
            price1.setText("  "+CARROT_PRICE);
            left_count_1_txt=dialog.findViewById(R.id.left_count1);
            left_count_1_txt.setText(Carrot_amount+" X ");
            btn_buy1=dialog.findViewById(R.id.buttonBuy1);
            if(Carrot_amount==0)btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
            btn_closeShop=dialog.findViewById(R.id.close_shop);
            btn_closeShop.setOnClickListener(closeShop(imageView));
            currentMoneyTxt = dialog.findViewById(R.id.currentMoneyTxt);
            currentMoneyTxt.setText(moneyAmount+"");
            btn_buy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=CARROT_PRICE){
                        if(Carrot_amount>0){
                            moneyAmount-=CARROT_PRICE;
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            Carrot_amount--;
                            btn_closeShop.setOnClickListener(closeShop2(imageView));
                            whatSquirrelSays.setText(R.string.tgfvfh);
                            if(Carrot_amount==0){
                                btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_1_txt.setText(Carrot_amount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }
                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }else if(SHOP_CASE==SHOP_CASE_FOX){
            dialog=new Dialog(this);
            dialog.setContentView(R.layout.shop_fox_popup);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(dialogOnBackPressed());

            price1=dialog.findViewById(R.id.price1);
            price2=dialog.findViewById(R.id.price2);
            price3=dialog.findViewById(R.id.price3);
            price1.setText(""+SLEEPING_DRUG_PRICE);
            price2.setText(""+DYNAMITE_PRICE);
            price3.setText(""+CHICKEN_PRICE);


            left_count_1_txt=dialog.findViewById(R.id.left_count1);
            left_count_2_txt=dialog.findViewById(R.id.left_count2);
            left_count_3_txt=dialog.findViewById(R.id.left_count3);



            left_count_1_txt.setText(Sleeping_drug_amount+" X ");
            left_count_2_txt.setText(Dynamite_amount +" X ");
            left_count_3_txt.setText(ChickenAmount+ " X ");


            btn_buy1=dialog.findViewById(R.id.buttonBuy1);
            if(Sleeping_drug_amount==0)btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
            btn_buy2=dialog.findViewById(R.id.buttonBuy2);
            if(Dynamite_amount==0)btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
            btn_buy3=dialog.findViewById(R.id.buttonBuy3);
            if(ChickenAmount==0)btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));



            final ScrollView scrollView=dialog.findViewById(R.id.scrollView);

            currentMoneyTxt = dialog.findViewById(R.id.currentMoneyTxt);
            currentMoneyTxt.setText(moneyAmount+"");

            btn_buy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=SLEEPING_DRUG_PRICE){
                        if(Sleeping_drug_amount>0){
                            moneyAmount-=SLEEPING_DRUG_PRICE;
                            rand=random.nextInt(30);
                            addItemProgrammatically(itemSpawn, itemSpawn,  SLEEPING_DRUG_ITEM_CODE, getResources().getDrawable(R.drawable.sleeping_drug), fox.getX()+fox.getWidth()+10+rand, fox.getY()+50+rand, 100, 75);
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            Sleeping_drug_amount--;
                            if(Sleeping_drug_amount==0){
                                btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_1_txt.setText(Sleeping_drug_amount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }
                    }
                }
            });
            btn_buy2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=DYNAMITE_PRICE){
                        if(Dynamite_amount>0){
                            moneyAmount-=DYNAMITE_PRICE;
                            rand=random.nextInt(30);
                             addDynamiteProgrammatically(itemSpawn,itemSpawn,0,DYNAMITE_ITEM_CODE, getResources().getDrawable(R.drawable.dynamite),fox.getX()+fox.getWidth()+10+rand, fox.getY()+50+rand);
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            Dynamite_amount--;
                            if(Dynamite_amount==0){
                                btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_2_txt.setText(Dynamite_amount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }


                    }
                }
            });
            btn_buy3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=CHICKEN_PRICE){
                        if(ChickenAmount>0){
                            moneyAmount-=CHICKEN_PRICE;
                            rand=random.nextInt(30);
                            addItemProgrammatically(itemSpawn,itemSpawn,CHICKEN_ITEM_CODE, getResources().getDrawable(R.drawable.chicken_leg),fox.getX()+fox.getWidth()+10+rand, fox.getY()+50+rand, 70, 70);
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            ChickenAmount--;
                            if(ChickenAmount==0){
                                btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_3_txt.setText(ChickenAmount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }


                    }
                }
            });

            btn_closeShop=dialog.findViewById(R.id.close_shop);
            btn_closeShop.setOnClickListener(closeShop(imageView));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }else if(SHOP_CASE==SHOP_CASE_MOLE){
            dialog=new Dialog(this);
            dialog.setContentView(R.layout.shop_mole_popup);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(dialogOnBackPressed());

            price1=dialog.findViewById(R.id.price1);
            price2=dialog.findViewById(R.id.price2);
            price3=dialog.findViewById(R.id.price3);
            price1.setText(""+HAMMER_PRICE);
            price2.setText(""+CUTTER_PRICE);
            price3.setText(""+LIGHT_PRICE);


            left_count_1_txt=dialog.findViewById(R.id.left_count1);
            left_count_2_txt=dialog.findViewById(R.id.left_count2);
            left_count_3_txt=dialog.findViewById(R.id.left_count3);

            left_count_1_txt.setText(Hammer_amount+" X ");
            left_count_2_txt.setText(Cutter_amount+" X ");
            left_count_3_txt.setText(Light_amount+" X ");


            btn_buy1=dialog.findViewById(R.id.buttonBuy1);
            if(Hammer_amount==0)btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
            btn_buy2=dialog.findViewById(R.id.buttonBuy2);
            if(Cutter_amount==0)btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
            btn_buy3=dialog.findViewById(R.id.buttonBuy3);
            if(Light_amount==0)btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));


            final ScrollView scrollView=dialog.findViewById(R.id.scrollView);
            ImageView top=dialog.findViewById(R.id.top);
            ImageView bottom=dialog.findViewById(R.id.bottom);

            top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scrollView.smoothScrollTo(0,0);
                }
            });
            bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scrollView.smoothScrollTo(0,scrollView.getHeight());
                }
            });


            currentMoneyTxt = dialog.findViewById(R.id.currentMoneyTxt);
            currentMoneyTxt.setText(moneyAmount+"");

            btn_buy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=HAMMER_PRICE){
                        if(Hammer_amount>0){
                            moneyAmount-=HAMMER_PRICE;
                            rand=random.nextInt(30);
                            addItemProgrammatically(itemSpawn, itemSpawn,  HAMMER_ITEM_CODE, getResources().getDrawable(R.drawable.hammer), mole.getX()-75, mole.getY()+20, 140, 95);
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            Hammer_amount--;
                            if(Hammer_amount==0){
                                btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_1_txt.setText(Hammer_amount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }
                    }
                }
            });
            btn_buy2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=CUTTER_PRICE){
                        if(Cutter_amount>0){
                            moneyAmount-=CUTTER_PRICE;
                            rand=random.nextInt(30);
                            addItemProgrammatically(itemSpawn, itemSpawn, CUTTER_ITEM_CODE, getResources().getDrawable(R.drawable.cutter), mole.getX()-75, mole.getY()+20, 80,140);
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            Cutter_amount--;
                            if(Cutter_amount==0){
                                btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_2_txt.setText(Cutter_amount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }


                    }
                }
            });
            btn_buy3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moneyAmount>=LIGHT_PRICE){
                        if(Light_amount>0){
                            moneyAmount-=LIGHT_PRICE;
                            addItemProgrammatically(itemSpawn, itemSpawn, LIGHTER_ITEM_CODE, getResources().getDrawable(R.drawable.light), mole.getX()-75, mole.getY()+20, 60,40);
                            currentMoneyTxt.setText(moneyAmount+"");
                            moneyCountTXT.setText(moneyAmount+"");
                            Light_amount--;
                            if(Light_amount==0){
                                btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_owl_unavailable));
                            }
                            left_count_3_txt.setText(Light_amount+" X ");
                            MainActivity.soundPlayer.makeCashRegisterSound();
                        }


                    }
                }
            });
            btn_closeShop=dialog.findViewById(R.id.close_shop);
            btn_closeShop.setOnClickListener(closeShop(imageView));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

    private void bottleOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.tyhgas),getString(R.string.bgbr),getString(R.string.iuuyy), imageView, CaseBottleToMole);
        }else if(wereOverlapped(squirrel, imageView)&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.rtgtgt),getString(R.string.hnhdc),getString(R.string.gdbdfgw));
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.fgbfgbe),":(");
            MainActivity.soundPlayer.makeOwlSound();
        }
        else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.dgdrgd),getString(R.string.ddgd),getString(R.string.fbfgbs));
            MainActivity.soundPlayer.makeFoxSound();
        }
        else if(wereOverlapped(rabbit, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.fdgrfgthyj),getString(R.string.hgfdfgh),getString(R.string.dcvfgbhngf));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.gdthhy),getString(R.string.nfsdv));
            randomCatMeowSound();
        }
    }

    private void bootOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.fgbwef),getString(R.string.hnrd),getString(R.string.ns),imageView,CaseBootToMole);

        }else if(wereOverlapped(squirrel, imageView)&&squirrel.getVisibility()==View.VISIBLE){
            MainActivity.soundPlayer.makeSquirrelSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.vnbfv),getString(R.string.rtwef),getString(R.string.nfgn));
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.nhbf),":(");
            MainActivity.soundPlayer.makeOwlSound();
        }
        else if(wereOverlapped(fox, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.bgbre),getString(R.string.bdf),getString(R.string.rthrthet));
            MainActivity.soundPlayer.makeFoxSound();
        }
        else if(wereOverlapped(rabbit, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.dbdrgrgd),getString(R.string.eregergerge),getString(R.string.tethsdfn));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.ththrh),getString(R.string.trhrthrthrth));
            randomCatMeowSound();
        }
    }

    private void keyOverlapAction(ImageView imageView) {
        if(wereOverlapped(chest2, imageView)){
            MainActivity.soundPlayer.makeChestOpeningSound();
            imageView.setVisibility(View.INVISIBLE);
            redraw(chest2, getResources().getDrawable(R.drawable.forest_box_opened));
            addItemProgrammatically(itemSpawn, itemSpawn, BOOT_ITEM_CODE, getResources().getDrawable(R.drawable.old_boot), chest2.getX()+10, chest2.getY()+chest2.getHeight()+10, 80, 85);
            addItemProgrammatically(itemSpawn, itemSpawn, BOTTLE_ITEM_CODE, getResources().getDrawable(R.drawable.empty_bottle),chest2.getX()+50, chest2.getY()+chest2.getHeight()+15, 87, 90);
        }else if(wereOverlapped(cat, imageView)){
            randomCatMeowSound();
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.bghn),getString(R.string.awgt));
        }else if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole_two),getString(R.string.nhbv),getString(R.string.ertge));
            makeMoleRandomSound();
        }
        else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.fng),getString(R.string.adawdgrg));
            MainActivity.soundPlayer.makeOwlSound();
        }else if(wereOverlapped(squirrel, imageView)&&!squirrelRightFellDown&&squirrel.getVisibility()==View.VISIBLE){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.gngfnf),getString(R.string.ergegergrgg),getString(R.string.nhgfdcergh));
            MainActivity.soundPlayer.makeSquirrelSound();
        }
    }

    private void plantOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.sefsyj),
                    getString(R.string.dbdnff),getString(R.string.thrhe),imageView, CasePlantToTheMole);
        }else if(wereOverlapped(fox, imageView)){
            makeYesNoDialog(getString(R.string.etrthrhtr),getString(R.string.ergergerhrth),getString(R.string.tndbcfb), getString(R.string.thdthrthrt),imageView, CasePlantToTheFox);
        }else if(wereOverlapped(owl, imageView)){
            makeYesNoDialog(getString(R.string.owl),getString(R.string.thrthethgerg),getString(R.string.dgdgdgdg),getString(R.string.dfbvbn),imageView,CasePlantToTheOwl);
        }else if(wereOverlapped(rabbit, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.dgbddrgsf),getString(R.string.trthrthtrh),getString(R.string.gfnfsef));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(wereOverlapped(squirrel, imageView)&&squirrel.getVisibility()==View.VISIBLE){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.ngf),getString(R.string.zdfn),getString(R.string.fnsf));
            MainActivity.soundPlayer.makeSquirrelSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.dddbf),getString(R.string.awdthtrgrf));
            randomCatMeowSound();
        }
    }

    private void pineConeOverlapAction(ImageView imageView) {
        if(wereOverlapped(squirrel, imageView)&&squirrelRecievedPineCones==0&&squirrel.getVisibility()==View.VISIBLE&&!squirrelRightFellDown){
            MainActivity.soundPlayer.makeSquirrelSound();
            makeYesNoDialog(getString(R.string.ergergad), getString(R.string.htegwggfngf),
                    getString(R.string.uykjtyh),getString(R.string.tbetbt), imageView, CasePineConeToSquirrel);
        }else if(wereOverlapped(squirrel, imageView)&&squirrel.getVisibility()==View.VISIBLE&&!squirrelRightFellDown){
            MainActivity.soundPlayer.makeSquirrelSound();
            imageView.setVisibility(View.INVISIBLE);
            squirrelRecievedPineCones++;
            if(squirrelRecievedPineCones==3){
            //addItemProgrammatically(itemSpawn, itemSpawn, KEY_ONE_ITEM_CODE, getDrawable(R.drawable.key),squirrel.getX()-40, squirrel.getY()+squirrel.getHeight(),60,60);\
                key.setX(squirrel.getX()-40);
                key.setY(squirrel.getY()+squirrel.getHeight());
                controlSubThread3_keyFalling=new ControlSubThread(1,0,KEY_FALLING_CODE, key);
                controlSubThread3_keyFalling.start();
            }else if(squirrelRecievedPineCones>3){

            }
        }else if(wereOverlapped(mole, imageView)){
            makeYesNoDialog(getString(R.string.mole_two),getString(R.string.bvbvb),getString(R.string.cvcvcvc),getString(R.string.dfdfdf),imageView, CasePineConeToMole);
        }else if(wereOverlapped(fox, imageView)&&foxRecievedPineCones==0){
            makeYesNoDialog(getString(R.string.rthrthrth),getString(R.string.etehegerg),
                    getString(R.string.ndbffg), getString(R.string.awdawdawd),imageView, CasePineConeToFox);
        }else if(wereOverlapped(fox, imageView)&&foxRecievedPineCones>3){

        }else if(wereOverlapped(fox, imageView)&&foxRecievedPineCones<3){
            foxSale(imageView, CasePineConeToFox);
            MainActivity.soundPlayer.makeFoxSound();
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.egwgergerg), getString(R.string.xvdnrhetgfs));
            randomCatMeowSound();
        }else if(wereOverlapped(owl, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.owl),getString(R.string.yjrhegrge),getString(R.string.thdhehde));
            MainActivity.soundPlayer.makeOwlSound();
        }
        else if(wereOverlapped(rabbit, imageView)&&!rabbitSleepy){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.rgdgdbfgd),getString(R.string.rgergrege),getString(R.string.eemhgf));
            MainActivity.soundPlayer.makeRabbitSound();
        }
    }


    public static void action(){
        pineCone2.setX(tree2.getX()+tree2.getWidth());
        pineCone2.setY(0-pineCone2.getHeight());
        pineCone3.setX(tree2.getX()+tree2.getWidth()-85);
        pineCone3.setY(0-pineCone3.getHeight());
        key.setY(0-key.getHeight()-10);
        item1.setVisibility(View.INVISIBLE);
        item2.setVisibility(View.INVISIBLE);
        carrot.setY(0-carrot.getHeight()-10);
        squirrel_falling.setY(0-squirrel_falling.getHeight()-10);
        squirrel_falling.setX(tree2.getX()+tree2.getWidth()-85);
    }

    @NonNull
    private View.OnClickListener treeTwoWasClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tree2IsStaying){
                    if(tree2ClickCounter ==4){
                        woodHitSound();
                        controlSubThread1=new ControlSubThread(1,0, CONE_FALLING_CODE_1);
                        controlSubThread1.start();
                    }else if(tree2ClickCounter ==8){
                        woodHitSound();
                        controlSubThread2=new ControlSubThread(1,0, CONE_FALLING_CODE_2);
                        controlSubThread2.start();
                    }else{
                        woodHitSound();
                    }
                    tree2ClickCounter++;
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener treeOneWasClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                woodHitSound();
            }
        };
    }
    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void makeInfoForBeginning(String title, String text, String buttonPositive){
        dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.game_popup2);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonOkay);
        back.setText(buttonPositive);
        back.setOnClickListener(close2());
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
    private static View.OnClickListener close2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                action();
            }
        };
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
        if(itemCode==KEY_ONE_ITEM_CODE){
            //do nothing
        }else{
            image.setOnTouchListener(touch(image, itemCode));
        }
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
        itemsToDisappear.add(image);
        if(itemCode==KEY_ONE_ITEM_CODE){
            controlSubThread3_keyFalling=new ControlSubThread(1,0,KEY_FALLING_CODE, image);
            controlSubThread3_keyFalling.start();
        }
    }
    @NonNull
    private View.OnClickListener moleWasTouched() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleTouchCounter==0&&goldenBagList.size()==0){
                    addGoldBagProgrammatically(goldBag, itemSpawn, 0, COIN_BAG_ITEM_CODE, getResources().getDrawable(R.drawable.bag_of_gold),mole.getX()-75, mole.getY()+20);
                    makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.ytredfvbnhj),getString(R.string.drdgd));
                    coinCounterLayout.setVisibility(View.VISIBLE);
                    moleTouchCounter++;
                    makeMoleRandomSound();
                }else {
                    makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.brdmnegfsfbgf), getString(R.string.thrjttrhgsf));
                    makeMoleRandomSound();
                }
            }
        };
    }
    private void addGoldBagProgrammatically(ImageView image, ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y) {
        image = actionAddingProgrammatcally(bindTo, itemCode, drawable, x, y);
        goldenBagList.add(0, image);
        itemsToDisappear.add(image);
    }
    private void addDynamiteProgrammatically(ImageView image, ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y) {
        image = actionAddingProgrammatcally(bindTo, itemCode, drawable, x, y);
        dynamiteList.add(0, image);
        itemsToDisappear.add(image);
    }
    private void addBlowingItemProgrammatically(ImageView image, ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y) {
        image = actionAddingProgrammatcally(bindTo, itemCode, drawable, x, y);
        blowImageList.add(0, image);
        image.setVisibility(View.INVISIBLE);

    }

    @NonNull
    private ImageView actionAddingProgrammatcally(ImageView bindTo, int itemCode, Drawable drawable, float x, float y) {
        ImageView image;
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
        newParams.width=90;
        newParams.height=90;
        newParams.topToTop=params.topToTop;
        constraintLayout.addView(image, -1, newParams);

        image.setTranslationX(x);
        image.setTranslationY(y);
        image.setVisibility(View.VISIBLE);
        return image;
    }
    public void makeInfoDialogWindow(String title, String text, String buttonPositive){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.game_popup1);
        dialog.setCanceledOnTouchOutside(true);
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
    private static View.OnClickListener close() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }
    @NonNull
    private static View.OnClickListener closeShop(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getBack(imageView);
            }
        };
    }

    @NonNull
    private View.OnClickListener closeShop2(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getBack(imageView);
                carrot.setX(squirrel.getX()-40);
                carrot.setY(squirrel.getY()+squirrel.getHeight());
                controlSubThread4_carrotFalling=new ControlSubThread(1,0, CARROT_MOVE_CODE, carrot);
                controlSubThread4_carrotFalling.start();
            }
        };
    }

    public void makeYesNoDialog(String title, String text, String buttonPositive, String buttonNegative, final ImageView imageView, int Case){
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


        if(Case==CasePineConeToSquirrel&&squirrelRecievedPineCones==0){
            MainActivity.soundPlayer.makeSquirrelSound();
            ok.setOnClickListener(okAction(dialog, imageView));
        }
        else if(Case==CasePineConeToMole||Case==CaseBottleToMole||Case==CaseBootToMole||Case==CasePlantToTheMole||Case==CaseDwarfToMole||Case==CaseAxeToMole||Case==CaseSleepingDrugToMole){
            makeMoleRandomSound();
            ok.setOnClickListener(moleSale(imageView, Case));
        }else if(Case==CasePineConeToFox||Case==CasePlantToTheFox||Case==CaseAxeToFox||Case==CaseSquirrelToFox||Case==CaseHammerToFox||Case==CaseCandyToFox){
            MainActivity.soundPlayer.makeFoxSound();
            ok.setOnClickListener(foxSaleListener(imageView, Case));
        }else if(Case==CasePlantToTheOwl||Case==CaseAxeToOwl||Case==CaseHammerToOwl||Case==CaseDwarfToOwl||Case==CaseCutterToOwl){
            MainActivity.soundPlayer.makeOwlSound();
            ok.setOnClickListener(owlSaleListener(imageView, Case));
        }else if(Case==CaseCandyToRabbit||Case==CaseBananaSkinToRabbit){
            ok.setOnClickListener(rabbitSaleListener(imageView, Case));
            MainActivity.soundPlayer.makeRabbitSound();
        }else if(Case==CaseRabbitToFox){
            ok.setOnClickListener(rabbitWasSold(imageView, Case));
            MainActivity.soundPlayer.makeFoxSound();
        }else if(Case==CaseCandyToSquirrel){
            ok.setOnClickListener(squirrelBoughtCandy(imageView));
            MainActivity.soundPlayer.makeSquirrelSound();
        }


        text1.setText(title);
        text2.setText(text);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @NonNull
    private View.OnClickListener squirrelBoughtCandy(final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),squirrel.getX()-30, squirrel.getY()+squirrel.getHeight()+30, 58,58);
                addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),squirrel.getX()-50, squirrel.getY()+squirrel.getHeight()+15, 58,58);
                MainActivity.soundPlayer.makeItemSoldSound();
            }
        };
    }

    @NonNull
    private View.OnClickListener rabbitWasSold(final ImageView imageView, final int Case) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                controlSubThread6_fox_moving=new ControlSubThread(19,0,FOX_MOVE_CODE,fox);
                controlSubThread6_fox_moving.start();
                foxSale(imageView, Case);
                MainActivity.soundPlayer.makeItemSoldSound();
            }
        };
    }

    @NonNull
    private View.OnClickListener rabbitSaleListener(final ImageView imageView, final int Case) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rabbitSale(imageView, Case);
            }
        };
    }



    @NonNull
    private View.OnClickListener foxSaleListener(final ImageView imageView, final int Case) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foxSale(imageView, Case);
            }
        };
    }
    @NonNull
    private View.OnClickListener owlSaleListener(final ImageView imageView, final int Case) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                owlSale(imageView, Case);
            }
        };
    }

    private void owlSale(ImageView imageView, int Case) {
        if(Case==CasePlantToTheOwl){
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            owlGiveMoney(Case);
        }else if(Case==CaseAxeToOwl){
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            rand=random.nextInt(30);
            addItemProgrammatically(itemSpawn, itemSpawn,  CANDY_ITEM_CODE, getResources().getDrawable(R.drawable.candy), owl.getX()+owl.getWidth()+10+rand, owl.getY()+rand, 75, 75);
            rand=random.nextInt(30);
            addItemProgrammatically(itemSpawn, itemSpawn,  CANDY_ITEM_CODE, getResources().getDrawable(R.drawable.candy), owl.getX()+owl.getWidth()+10+rand, owl.getY()+rand, 75, 75);
            MainActivity.soundPlayer.makeItemSoldSound();
        }else if(Case==CaseHammerToOwl){
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            owlGiveMoney(Case);
        }else{
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            owlGiveMoney(Case);
        }
    }

    private void owlGiveMoney(int Case) {
        if(Case==CasePlantToTheOwl){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),owl.getX()+owl.getWidth()+30, owl.getY()+owl.getHeight()-30, 58,58);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),owl.getX()+owl.getWidth()+50, owl.getY()+owl.getHeight()-30, 58,58);
        }else if(Case==CaseHammerToOwl){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),owl.getX()+owl.getWidth()+50, owl.getY()+owl.getHeight()-30, 63,63);
        }else if(Case==CaseDwarfToOwl){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),owl.getX()+owl.getWidth()+50, owl.getY()+owl.getHeight()-30, 63,63);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),owl.getX()+owl.getWidth()+70, owl.getY()+owl.getHeight()-10, 55,55);
        }else if(Case==CaseCutterToOwl){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),owl.getX()+owl.getWidth()+50, owl.getY()+owl.getHeight()-30, 63,63);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),owl.getX()+owl.getWidth()+70, owl.getY()+owl.getHeight()-10, 61,61);
        }
        MainActivity.soundPlayer.makeItemSoldSound();
    }
    private void rabbitSale(ImageView imageView, int Case) {
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            rabbitGiveMoney(Case);

    }

    private void rabbitGiveMoney(int Case) {
        rand=random.nextInt(30);
        if(Case==CaseCandyToRabbit){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),rabbit.getX()+rabbit.getWidth()+20+rand, rabbit.getY()+10+rand, 61,61);
        }else if(Case==CaseBananaSkinToRabbit){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),rabbit.getX()+rabbit.getWidth()+20+rand, rabbit.getY()+10+rand, 55,55);
        }
        MainActivity.soundPlayer.makeItemSoldSound();
    }

    private void foxSale(ImageView imageView, int Case) {
        if(Case==CasePineConeToFox){
            if(foxRecievedPineCones==0){
                dialog.dismiss();
            }
            imageView.setVisibility(View.INVISIBLE);
            foxRecievedPineCones++;
            if(foxRecievedPineCones==3){
                FoxGiveMoney(CasePineConeToFox);
            }
        }else if(Case==CasePlantToTheFox){
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            FoxGiveMoney(Case);
        }else if(Case==CaseAxeToFox){
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            FoxGiveMoney(Case);
        }else if(Case==CaseSquirrelToFox){
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            FoxGiveMoney(Case);
        }else if(Case==CaseRabbitToFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),fox.getX()+fox.getWidth()+50, fox.getY()+20, 63,63);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),fox.getX()+fox.getWidth()+70, fox.getY()+30, 63,63);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),fox.getX()+fox.getWidth()+20, fox.getY()+50, 63,63);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),fox.getX()+fox.getWidth()+90, fox.getY()+25, 63,63);
        }else{
            imageView.setVisibility(View.INVISIBLE);
            dialog.dismiss();
            FoxGiveMoney(Case);
        }
        MainActivity.soundPlayer.makeItemSoldSound();

    }

    private void FoxGiveMoney(int Case) {
        if(Case==CasePineConeToFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+50, fox.getY()+50, 58,58);
        }else if(Case==CasePlantToTheFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+50, fox.getY()+50, 58,58);
        }else if(Case==CaseAxeToFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+50, fox.getY()+20, 58,58);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+80, fox.getY()+50, 58,58);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+20, fox.getY()+80, 58,58);
        }else if(Case==CaseSquirrelToFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),fox.getX()+fox.getWidth()+20, fox.getY()+80, 61,61);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+50, fox.getY()+20, 58,58);
        }else if(Case==CaseHammerToFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),fox.getX()+fox.getWidth()+20, fox.getY()+80, 61,61);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+50, fox.getY()+20, 58,58);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),fox.getX()+fox.getWidth()+70, fox.getY()+50, 55,55);
        }else if(Case==CaseCandyToFox){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),fox.getX()+fox.getWidth()+50, fox.getY()+20, 58,58);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),fox.getX()+fox.getWidth()+70, fox.getY()+50, 55,55);
        }
    }
    private void moleGiveMoney(int Case) {
        if(Case==CasePineConeToMole){
            rand=random.nextInt(30);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),mole.getX()-55-rand, mole.getY()+50, 55,55);
        }else if(Case==CasePlantToTheMole){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),mole.getX()-55, mole.getY()+50, 55,55);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),mole.getX()-85, mole.getY()+60, 58,58);
        }else if(Case==CaseBootToMole){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),mole.getX()-105, mole.getY()+60, 58,58);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),mole.getX()-85, mole.getY()+80, 58,58);
        }else if(Case==CaseBottleToMole){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TWO_ITEM_CODE, getResources().getDrawable(R.drawable.coin_two),mole.getX()-75, mole.getY()+40, 58,58);
        }else if(Case==CaseDwarfToMole){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),mole.getX()-55, mole.getY()+50, 63,63);
        }else if(Case==CaseAxeToMole){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),mole.getX()-55, mole.getY()+50, 61,61);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_FIVE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_five),mole.getX()-25, mole.getY()+30, 61,61);
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_ONE_ITEM_CODE, getResources().getDrawable(R.drawable.coin_one),mole.getX()-75, mole.getY()+40, 55,55);
        }else if(Case==CaseSleepingDrugToMole){
            addItemProgrammatically(itemSpawn, itemSpawn, COIN_TEN_ITEM_CODE, getResources().getDrawable(R.drawable.coin_ten),mole.getX()-55, mole.getY()+50, 63,63);
        }
        MainActivity.soundPlayer.makeItemSoldSound();


    }

    @NonNull
    private View.OnClickListener moleSale(final ImageView imageView, final int Case) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goldenBagList.size()==0){
                    addGoldBagProgrammatically(goldBag, itemSpawn, 0, COIN_BAG_ITEM_CODE, getResources().getDrawable(R.drawable.bag_of_gold),mole.getX()-75, mole.getY()+20);
                    coinCounterLayout.setVisibility(View.VISIBLE);
                }
                dialog.dismiss();
                imageView.setVisibility(View.INVISIBLE);
                moleGiveMoney(Case);
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
    @NonNull
    private View.OnClickListener okAction(final Dialog dialog, final ImageView imageView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                makeInfoDialogWindow(getString(R.string.drgdgs),getString(R.string.nged),getString(R.string.ethergeg));
                imageView.setVisibility(View.INVISIBLE);
                squirrelRecievedPineCones++;
            }
        };
    }

    private void coinOverlapAction(ImageView imageView, int itemCode) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.utyrterf), getString(R.string.vscdfdgfb));
            //randomCatMeowSound();
        }
        if(goldenBagList.size()>0){
            if(wereOverlappedGoldenBagWithCoin(goldBag, imageView)){
                imageView.setVisibility(View.INVISIBLE);
                switch (itemCode){
                    case COIN_ONE_ITEM_CODE:
                        moneyCounterChange(1);
                        break;
                    case COIN_TWO_ITEM_CODE:
                        moneyCounterChange(2);
                        break;
                    case COIN_FIVE_ITEM_CODE:
                        moneyCounterChange(5);
                        break;
                    case COIN_TEN_ITEM_CODE:
                        moneyCounterChange(10);
                        break;
                        default:makeText("wtf");
                }

                MainActivity.soundPlayer.makeCoinDropSound();
                //redrawBag();
            }else if(wereOverlapped(mole, imageView)){
                getBack(imageView);
                makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.rgergergerg), getString(R.string.trhtg));
                makeMoleRandomSound();
            }
        }else if(wereOverlapped(mole, imageView)){
            if(moleTouchCounter==0){
                addGoldBagProgrammatically(goldBag, itemSpawn, 0, COIN_BAG_ITEM_CODE, getResources().getDrawable(R.drawable.bag_of_gold),mole.getX()-75, mole.getY()+20);
                coinCounterLayout.setVisibility(View.VISIBLE);
                makeInfoDialogWindow(getString(R.string.mole_two), getString(R.string.thrng),getString(R.string.tberbebb));
                coinCounterLayout.setVisibility(View.VISIBLE);
                moleTouchCounter++;
                makeMoleRandomSound();
                getBack(imageView);
            }
        }

    }
    private boolean wereOverlappedGoldenBagWithCoin(View view, View viewActive) {
        float gbX=goldenBagList.get(0).getX();
        float gbY=goldenBagList.get(0).getY();
        int gbWidth=goldenBagList.get(0).getWidth();
        int gbHeight=goldenBagList.get(0).getHeight();
        int viewHeightDivided=gbHeight/4;
        int viewWidthDivided=gbWidth/4;
        if(viewActive.getY()+viewActive.getHeight()>gbY+viewHeightDivided&&viewActive.getY()<gbY+gbHeight-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>gbX+viewWidthDivided&&viewActive.getX()<gbX+gbWidth-viewWidthDivided){return true;}else {return false;}
    }
    public void moneyCounterChange(int change){
        MainActivity.soundPlayer.makeCoinDropSound();
        moneyAmount+=change;
        moneyCountTXT.setText(moneyAmount+"");
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
        back.setText(R.string.yujkyujdv);
        back.setOnClickListener(toMainMenu());

        if(win){
            int a;

            a=mySharedPref.getInt(MainActivity.ALL_LEVELS_ARE_PASSED_KEY, 0);
            if(a==0){
                makeText2();
            }

            a=mySharedPref.getInt(MainActivity.ALL_LEVELS_ARE_PASSED_KEY, 123);
            if(a==123){
                MainActivity.soundPlayer.makeTotalWinSound();
            }else{
                MainActivity.soundPlayer.makeVictorySound();
            }

            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.ALL_LEVELS_ARE_PASSED_KEY, 77);
            mEditor.apply();



            text1.setText(R.string.tddb);
            text2.setText(R.string.awdng);
            ok.setText(R.string.gfbdsc);
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
            if(d1==1&&d2==1&&d3==1&&d4==1&&d5==1&&d6==0){
                makeText2();
            }


            int i=mySharedPref.getInt(MainActivity.MASS_KILL_COMPLETED, 0);
            if(i==0){
                makeText2();
            }



            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_6, 1);
            mEditor.putInt(MainActivity.MASS_KILL_COMPLETED, 888);
            mEditor.apply();








            MainActivity.soundPlayer.makeFailureSound();
            text1.setText(R.string.jyhrtgefvgnhjm);
            text2.setText(R.string.dbvsbdb);
            ok.setText(R.string.mnhbgg);
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
    public void levelOver2(Boolean win){
        int d1, d2, d3, d4, d5, d6;
        d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
        d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
        d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
        d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
        d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
        d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
        if(d1==1&&d2==1&&d3==1&&d4==1&&d5==1&&d6==0){
            makeText2();
        }

        SharedPreferences.Editor mEditor=mySharedPref.edit();
        mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_6, 1);
        mEditor.apply();

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
        back.setText(R.string.egerg);
        back.setOnClickListener(toMainMenu());

            MainActivity.soundPlayer.makeFailureSound();
            text1.setText(R.string.rterge);
            text2.setText(R.string.yjrhevsd);
            ok.setText(R.string.rthehrhrh);
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
                Intent intent=new Intent(Level_six.this, Level_six.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
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
                Intent intent=new Intent(Level_six.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.OPEN_LEVELS_DIALOG);
                startActivity(intent);
            }
        };
    }
    private void checkThreadsBeforeExit() {
        if(controlSubThread1!=null){
            controlSubThread1.stop();
            controlSubThread1.destroy();
        }if(controlSubThread2!=null){
            controlSubThread2.stop();
            controlSubThread2.destroy();
        }if(controlSubThread3_keyFalling!=null){
            controlSubThread3_keyFalling.stop();
            controlSubThread3_keyFalling.destroy();
        }
        if(controlSubThread4_carrotFalling!=null){
           controlSubThread4_carrotFalling.stop();
           controlSubThread4_carrotFalling.destroy();
        }
        if(controlSubThread5_falling_squirrel_right!=null){
           controlSubThread5_falling_squirrel_right.stop();
           controlSubThread5_falling_squirrel_right.destroy();
        }
        if(controlSubThread5_falling_squirrel!=null){
           controlSubThread5_falling_squirrel.stop();
           controlSubThread5_falling_squirrel.destroy();
        }
        if(controlSubThread6_fox_moving!=null){
           controlSubThread6_fox_moving.stop();
           controlSubThread6_fox_moving.destroy();
        }
        if(controlSubThread7_boom_is_coming!=null){
           controlSubThread7_boom_is_coming.stop();
           controlSubThread7_boom_is_coming.destroy();
        }

    }
    @NonNull
    private View.OnClickListener toMainMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThreadsBeforeExit();
                Intent intent=new Intent(Level_six.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        };
    }
    @Override
    public void onBackPressed() {
        exitToMenu();
    }
    private void exitToMenu() {
        dialog=new Dialog(Level_six.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText(R.string.ebeerb);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText(R.string.yjtyhrhet);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThreadsBeforeExit();
                Intent intent = new Intent(Level_six.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText(R.string.rtherhege);
        text2.setText(R.string.efgwe);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void makeMoleRandomSound(){
        rand=random.nextInt(100);
        if(rand<=50){
            MainActivity.soundPlayer.makeMoleSound1();
        }else{
            MainActivity.soundPlayer.makeMoleSound2();
        }
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
            //MainActivity.soundPlayer.makeWoodHitSound_4();
            MainActivity.soundPlayer.makeWoodHitSound_6();
        }else if(rand>55&&rand<80){
            MainActivity.soundPlayer.makeWoodHitSound_5();
        }else{
            MainActivity.soundPlayer.makeWoodHitSound_6();
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
    public void woodCutSound(){
        rand=random.nextInt(100);
        if(rand<=33){
            MainActivity.soundPlayer.makeWoodCutSound1();
        }else if(rand>33&&rand<=66){
            MainActivity.soundPlayer.makeWoodCutSound2();
        }else if(rand>66&&rand<=100){
            MainActivity.soundPlayer.makeWoodCutSound3();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (musicPlayer != null) {
            musicPlayer.stopForestMusic();
            if (isFinishing()) {
                musicPlayer.stopForestMusic();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayer !=null){
            musicPlayer.resumeForestMusic();
        }
    }
    private void makeText2() {
        Toast.makeText(getBaseContext(), R.string.erretntb,Toast.LENGTH_LONG ).show();
    }































































































    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class ControlSubThread implements Runnable {
        private Thread worker;
        boolean running=false;
        boolean waited=false;
        boolean foxWaited=false;
        ImageView imageView;
        Message msg = new Message();
        private int littleInterval, startingInterval, moveCode;
        public ControlSubThread(int littleInterval, int startingInterval, int moveCode) {
            this.littleInterval = littleInterval;
            this.startingInterval=startingInterval;
            this.moveCode=moveCode;
        }
        public ControlSubThread(int littleInterval, int startingInterval, int moveCode, ImageView imageView) {
            this.littleInterval = littleInterval;
            this.startingInterval=startingInterval;
            this.moveCode=moveCode;
            this.imageView=imageView;
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

                if(moveCode==CONE_FALLING_CODE_1){
                    fallAction(pineCone2);
                }else if(moveCode==CONE_FALLING_CODE_2){
                    fallAction(pineCone3);
                }else if(moveCode==KEY_FALLING_CODE){
                    fallActionForKey(imageView);
                }else if(moveCode==CARROT_MOVE_CODE){
                    fallActionCarrot(imageView);
                }else if(moveCode==SQUIRREL_FALLING_CODE){
                    fallActionSquirrel(imageView);
                }else if(moveCode==SQUIRREL_RIGHT_FALLING_CODE){
                    fallActionSquirrelRight(imageView);
                }else if(moveCode==FOX_MOVE_CODE){
                    foxMoveAction(imageView);
                }else if(moveCode==BOOM_IS_COMING_CODE){
                    boomStartingAction(imageView);
                }
            }
        }

        private void boomStartingAction(ImageView imageView) {
            handledRedraw3(getResources().getDrawable(R.drawable.boom));
            sleep(littleInterval);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f1));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f2));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f3));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f4));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_5));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f5));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f6));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f6_12));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f6_13));
            sleep(580);
            handledRedraw(imageView,getResources().getDrawable(R.drawable.dynamite_f7));
            sleep(580);
            //boom comes here with sound and animation
            handledRedraw2(imageView);
            imageView=blowImageList.get(0);
            handledAction(imageView);

            for(int i=0;i<50;i++){
                sleep(18);
                handledResize(200,200, imageView);
            }
            handledRedrawMap();
            for(int i=0;i<50;i++){
                sleep(18);
                handledResize(-200,-200, imageView);
            }
            /////
            handledDissapear(imageView);
            sleep(2100);
            handledLevelOver();
            destroy();
        }

        private void handledLevelOver() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                   levelOver(false);
                }
            });
        }

        private void handledAction(final ImageView imageView) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if(wasNotSet){
                        wasNotSet=false;
                        imageView.setX(dynamiteList.get(0).getX());
                        imageView.setY(dynamiteList.get(0).getY());
                        imageView.setVisibility(View.VISIBLE);
                        MainActivity.soundPlayer.stopRopeBurningSound();
                        MainActivity.soundPlayer.makeExplosionSound();
                    }

                }
            });

        }

        private void handledRedrawMap() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.b10_burned));
                    tree1.setImageDrawable(getResources().getDrawable(R.drawable.tree_c4_burned));
                    tree2.setImageDrawable(getResources().getDrawable(R.drawable.tree2_c4_burned));
                    chest1.setImageDrawable(getResources().getDrawable(R.drawable.box_burned));
                    chest2.setImageDrawable(getResources().getDrawable(R.drawable.box_burned));
                    table.setImageDrawable(getResources().getDrawable(R.drawable.bear_table_burned));
                    sign.setImageDrawable(getResources().getDrawable(R.drawable.sign_burned));
                    cat.setImageDrawable(getResources().getDrawable(R.drawable.cat_bones));
                    rabbit.setImageDrawable(getResources().getDrawable(R.drawable.rabbit_burned));
                    fox.setImageDrawable(getResources().getDrawable(R.drawable.fox_burned));
                    owl.setImageDrawable(getResources().getDrawable(R.drawable.owl_burned));
                    stomp.setImageDrawable(getResources().getDrawable(R.drawable.stump_burned));
                    foxAlive=false;
                    rabbitAlive=false;
                    owlAlive=false;
                    catAlive=false;

                    bush.setImageDrawable(getResources().getDrawable(R.drawable.bush_burned));
                    coinCounterLayout.setVisibility(View.INVISIBLE);
                    for(int i=0;i<itemsToDisappear.size();i++){
                        itemsToDisappear.get(i).setVisibility(View.INVISIBLE);
                    }

                }
            });
        }

        public void handledRedraw2(final ImageView imageView){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                        imageView.setVisibility(View.INVISIBLE);
                        MainActivity.musicPlayer.stopForestMusic();


                }
            });
        }
        public void handledRedraw3(final Drawable drawable){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if(wasNotSpawned){
                        wasNotSpawned=false;
                        MainActivity.soundPlayer.makeRopeBurningSound();
                        addBlowingItemProgrammatically(itemSpawn, itemSpawn,0, PINE_CONE_ITEM_CODE,drawable,imageView.getX(), imageView.getY());
                    }
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
        public void handledResize(final int size_x_plus, final int size_y_plus, final ImageView imageView){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    imageView.getLayoutParams().width+=size_x_plus;
                    imageView.getLayoutParams().height+=size_y_plus;
                    imageView.requestLayout();
                    imageView.setX(imageView.getX()-size_x_plus/2);
                    imageView.setY(imageView.getY()-size_y_plus/2);

                }
            });
        }
        public void handledDissapear(final ImageView imageView){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    imageView.setVisibility(View.INVISIBLE);
                }
            });
        }

        private void foxMoveAction(final ImageView imageView) {
            if(foxWaited){
                sleep(littleInterval);
                imageView.setTranslationX(imageView.getTranslationX()-1);
                if(imageView.getX()+fox.getWidth()<=0){
                    running=false;
                    destroy();
                }
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        redraw(imageView,getResources().getDrawable(R.drawable.fox_with_bag));
                    }
                });
                sleep(1800);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        redraw(imageView,getResources().getDrawable(R.drawable.fox_with_bag_reverse));
                    }
                });
                sleep(1200);
                foxWaited=true;
            }

        }

        private void fallActionSquirrelRight(final ImageView imageView) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    redraw(imageView,getResources().getDrawable(R.drawable.squirrel_back));
                }
            });
            squirrelRightFellDown=true;
            sleep(littleInterval);
            imageView.setTranslationY(imageView.getTranslationY()+1);
            if(imageView.getY()>tree1.getY()+tree1.getHeight()){
                MainActivity.soundPlayer.makeWoodHitSound_2();
                imageView.setOnTouchListener(touch(imageView, SQUIRREL_ITEM_CODE));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        redraw(imageView,getResources().getDrawable(R.drawable.squirrel_back_dead));
                    }
                });
                running=false;
                destroy();
            }
        }


        private void fallActionSquirrel(final ImageView imageView) {
            sleep(littleInterval);
            imageView.setTranslationY(imageView.getTranslationY()+1);
            if(imageView.getY()>tree2.getY()+tree2.getHeight()){
                MainActivity.soundPlayer.makeWoodHitSound_2();
                imageView.setOnTouchListener(touch(imageView, SQUIRREL_ITEM_CODE));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        redraw(imageView,getResources().getDrawable(R.drawable.squirrel_back_dead2));
                    }
                });
                running=false;
                destroy();
            }
        }

        private void fallActionCarrot(ImageView imageView) {
            sleep(littleInterval);
            imageView.setTranslationY(imageView.getTranslationY()+1);
            if(imageView.getY()>tree1.getY()+tree1.getHeight()){
                MainActivity.soundPlayer.makeWoodHitSound_2();
                imageView.setOnTouchListener(touch(imageView, CARROT_ITEM_CODE));
                running=false;
                destroy();
            }
        }

        private void fallActionForKey(ImageView imageView) {
            sleep(littleInterval);
            imageView.setTranslationY(imageView.getTranslationY()+1);
            if(imageView.getY()>tree1.getY()+tree1.getHeight()){
                MainActivity.soundPlayer.makeWoodHitSound_2();
                imageView.setOnTouchListener(touch(imageView, KEY_ONE_ITEM_CODE));
                running=false;
                destroy();
            }
        }

        private void fallAction(ImageView imageView) {
            sleep(littleInterval);
            imageView.setTranslationY(imageView.getTranslationY()+1);
            if(imageView.getY()>tree2.getY()+tree2.getHeight()){
                MainActivity.soundPlayer.makeWoodHitSound_2();
                imageView.setOnTouchListener(touch(imageView, PINE_CONE_ITEM_CODE));
                running=false;
                destroy();
            }
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
    }
}
    ///////////////////////////////////////////////////////////////////////////////////////////////////


