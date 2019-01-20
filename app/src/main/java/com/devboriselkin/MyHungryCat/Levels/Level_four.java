package com.devboriselkin.MyHungryCat.Levels;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_four extends AppCompatActivity {
    static ImageView plant1, plant2, plant3, trb, lid, mole, cat, mouse, waterPlant1, waterPlant2, shovel, pound, dwarf, apple, goldBag, itemSpawn, boot, cheese, sleeping_drug, bigFishSpoiled, crap ,restart;
    static ConstraintLayout constraintLayout;
    LinearLayout coinCounter;
    static double d;
    static int y, x;
    static Dialog dialog;
    TextView text1, text2, moneyCountTXT, price1, price2, price3, price4;
    Button back, ok;
    int catClickCounter=0;
    int moleTouchCounter=0;
    int moneyAmount=0;
    int i=50;
    int binPushCounter=0;
    int catHP=100;
    int dwarfWasClicked=0;
    int dwarfKickCounter=0;
    List<ImageView> goldenBagList=new ArrayList<>();
    List<ImageView> cheeseList=new ArrayList<>();
    boolean bootBuyable=true;
    boolean fishBuyable=true;
    boolean cheeseBuyable=true;
    boolean sleepingDrugBuyable=true;
    boolean cheeseSleepy=false;
    boolean mouseSleeped=false;
    boolean dwarfLiftable=false;

    SharedPreferences mySharedPref;

    //////////////////////

    public static float xOld, yOld;
    float dX, dY;
    ImageView spawnedItem, icon;
    Random random=new Random();
    int rand=0;

    //for shop------------
    Button btn_buy1, btn_buy2, btn_buy3, btn_buy4, btn_closeShop;
    TextView currentMoneyTxt;

    //-------------------
    public static final int CASUAL_ITEM_CODE=1;
    public static final int CHEESE_ITEM_CODE=2;
    public static final int SLEEPING_DRUG_ITEM_CODE=3;
    public static final int MOUSE_ENTITY_CODE=4;
    public static final int DIRTY_OLD_BOOT_ITEM_CODE=5;
    public static final int SLEEPY_CHEESE_ITEM_CODE=7;
    public static final int COIN_ITEM_CODE=8;
    public static final int COIN_BAG_ITEM_CODE=9;
    public static final int APPLE_ITEM_CODE=10;
    public static final int WATER_PLANT_ITEM_CODE=11;
    public static final int DWARF_ITEM_CODE=12;
    public static final int BIG_SPOILED_FISH_ITEM_CODE=13;
    public static final int CRAP_ITEM_CODE=14;

    public static final int BOOT_PRICE=5;
    public static final int BIG_FISH_PRICE=17;
    public static final int CHEESE_PRICE=7;
    public static final int SLEEPY_DRUG_PRICE=13;





    public static final int WATER_PLANT_PRICE_SELL=1;
    public static final int SHOVEL_PRICE_SELL=3;
    public static final int PLANT_PRICE_SELL=2;
    public static final int BIG_PLANT_PRICE_SELL=2;
    public static final int DWARF_PRICE_SELL=6;
    public static final int LID_PRICE_CELL=4;
    public static final int APPLE_PRICE_SELL=2;
    public static final int DIRTY_OLD_BOOT_SELL=2;
    public static final int CHEESE_PRICE_SELL=3;
    public static final int SLEEPY_DRUG_SELL=5;

    public int check;


    Level_three.ControlSubThread controlSubThread;
    Handler handler;

    boolean alive=true;
    CardView cardView;
    public int tooHighCounter =0;



    //////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.level_four);


        initUI();
    }

    private void initUI() {
        mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
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


        restart=findViewById(R.id.restart);
        restart.setOnClickListener(restartLevel());

        itemSpawn=findViewById(R.id.item_spawn);
        moneyCountTXT =findViewById(R.id.moneyCount);
        goldBag=new ImageView(this);
        cheese=new ImageView(this);
        constraintLayout=findViewById(R.id.layout);
        coinCounter=findViewById(R.id.coin_counter);
        coinCounter.setVisibility(View.INVISIBLE);
        plant1=findViewById(R.id.plant1);
        plant2=findViewById(R.id.plant2);
        plant3=findViewById(R.id.plant3);
        apple=findViewById(R.id.apple);
        trb=findViewById(R.id.trb);
        lid=findViewById(R.id.lid);
        mole=findViewById(R.id.mole);
        mouse=findViewById(R.id.mouse);
        cat=findViewById(R.id.cat);
        cat.setOnClickListener(catClicked());

        waterPlant1=findViewById(R.id.pound_plant1);
        waterPlant2=findViewById(R.id.pound_plant2);
        shovel=findViewById(R.id.shovel);
        pound=findViewById(R.id.pound);
        pound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeInfoDialogWindow(getString(R.string.cat), getString(R.string.utrthr), getString(R.string.drgdsd));
                randomCatMeowSound();
            }
        });
        dwarf=findViewById(R.id.dwarf);
        dwarf.setOnClickListener(dwarfClick());
        mouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mouseSleeped){
                    MainActivity.soundPlayer.makeMouseSound();
                }
            }
        });

        trb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.soundPlayer.makeHitBinSound4();
            }
        });
        trb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(binPushCounter<3){
                    trb.setX(trb.getX()+i);
                    MainActivity.soundPlayer.makeMoveBinSound();
                }
                binPushCounter++;
                return false;
            }
        });
        mole.setOnClickListener(moleWasTouched());

        plant1.setOnTouchListener(touch(plant1, PLANT_PRICE_SELL, CASUAL_ITEM_CODE));
        plant2.setOnTouchListener(touch(plant2, BIG_PLANT_PRICE_SELL, CASUAL_ITEM_CODE));
        plant3.setOnTouchListener(touch(plant3, PLANT_PRICE_SELL, CASUAL_ITEM_CODE));
        apple.setOnTouchListener(touch(apple, APPLE_PRICE_SELL, APPLE_ITEM_CODE));
        shovel.setOnTouchListener(touch(shovel, SHOVEL_PRICE_SELL, CASUAL_ITEM_CODE));
        lid.setOnTouchListener(touch(lid, LID_PRICE_CELL, CASUAL_ITEM_CODE));
        waterPlant1.setOnTouchListener(touch(waterPlant1, WATER_PLANT_PRICE_SELL, WATER_PLANT_ITEM_CODE));
        waterPlant2.setOnTouchListener(touch(waterPlant2, WATER_PLANT_PRICE_SELL, WATER_PLANT_ITEM_CODE));

        makeInfoForBeginning(getString(R.string.tyutuyt), getString(R.string.qeqweqwe), getString(R.string.cgbcbc));
    }

    @NonNull
    private View.OnClickListener dwarfClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dwarfLiftable){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.eqeq2eq), getString(R.string.hnvhn));
                    randomCatMeowSound();
                }

            }
        };
    }

    @NonNull
    private View.OnClickListener moleWasTouched() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleTouchCounter==0){
                    addGoldBagProgrammatically(goldBag, itemSpawn, 0, COIN_BAG_ITEM_CODE, getResources().getDrawable(R.drawable.bag_of_gold),mole.getX()+mole.getWidth()+50, mole.getY()+20, 110,110);
                    makeInfoDialogWindow(getString(R.string.mole), getString(R.string.rdsgsrg),getString(R.string.sefsefsef));
                    coinCounter.setVisibility(View.VISIBLE);
                    moleTouchCounter++;
                    makeMoleRandomSound();
                }else {
                    makeInfoDialogWindow(getString(R.string.mole), getString(R.string.rthrfthrth), getString(R.string.cfbcbcgb));
                    makeMoleRandomSound();
                }
            }
        };
    }

    public static void action() {

        actionXY(plant1, 0.0778, 0.60 );
        actionXY(plant2, 0.0443, 0.75 );
        actionXY(plant3, 0.0778, 0.90 );
        actionXY(trb, 0.22, 0.275);
        actionXY(mouse, 0.30, 0.32);
        mole.setX(constraintLayout.getWidth()-constraintLayout.getWidth()-150);
        actionXY(pound,0.35, 0.65);
        actionXY(shovel, 0.85,0.50);
        actionXY(dwarf, 0.70,0.19);
        actionXY(apple, 0.50,0.10);

        waterPlant1.setX(pound.getX()+pound.getWidth()/3);
        waterPlant1.setY(pound.getY()+pound.getHeight()/2);
        waterPlant2.setX(pound.getX()+pound.getWidth()/2);
        waterPlant2.setY(pound.getY()+pound.getHeight()/4);
        lid.setY(trb.getY()+250);
        lid.setX(trb.getX()+40);


    }

    private static void actionXY(ImageView imageView, double Y, double X) {
        d=constraintLayout.getHeight()*Y;
        y=(int)d;
        imageView.setY(y);
        d=constraintLayout.getWidth()*X;
        x=(int)d;
        imageView.setX(x);
    }

    @Override
    public void onBackPressed() {
        exitToMenu();
    }
    private void exitToMenu() {
        dialog=new Dialog(Level_four.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText(R.string.ytrert);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText(R.string.qwqwq);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level_four.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText(R.string.tgggtgt);
        text2.setText(R.string.aawaff);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void makeInfoForBeginning(String title, String text, String buttonPositive){
        dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.game_popup2);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
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
    private static View.OnClickListener close2() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                action();
            }
        };
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
    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @NonNull
    private View.OnClickListener catClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catClickCounter<=2){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.gbfgbfbg),getString(R.string.tfdbfgbfbf));
                } else if(catClickCounter>=3&&catClickCounter<7){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.vcbcvb),getString(R.string.fgbcgbcg));
                }else{
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.retetertew),getString(R.string.cvbcvbcvb));
                }
                catClickCounter++;
                randomCatMeowSound();
            }
        };
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
    public View.OnTouchListener touch(final ImageView imageView, final int cost, final int itemCode) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MovementUltimate(event, imageView, cost, itemCode);
            }
        };
    }
    @Nullable
    private Boolean MovementUltimate(MotionEvent event, ImageView imageView, int cost, int itemCode) {
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
            if(event.getRawY()+dY<constraintLayout.getHeight()/4) {
                getBack(imageView);
                if(tooHighCounter ==0){
                    makeText("Too high");
                    tooHighCounter++;
                }

            }
            switch (itemCode){
                case CASUAL_ITEM_CODE:
                    casualItemOverlapAction(imageView, cost);
                    break;
                case COIN_ITEM_CODE:
                    coinOverlapAction(imageView);
                    break;
                case COIN_BAG_ITEM_CODE:
                    coinBagOverlapAction(imageView);
                    break;
                case APPLE_ITEM_CODE:
                    appleOverlapAction(imageView, cost);
                    break;
                case WATER_PLANT_ITEM_CODE:
                    waterPlantOverlapAction(imageView, cost);
                    break;
                case DIRTY_OLD_BOOT_ITEM_CODE:
                    bootOverlapAction(imageView, cost);
                    break;
                case DWARF_ITEM_CODE:
                    dwarfOverlapAction(imageView, cost);
                    break;
                case BIG_SPOILED_FISH_ITEM_CODE:
                    fishOverlapAction(imageView, cost);
                    break;
                case CHEESE_ITEM_CODE:
                    cheeseOverlapAction(imageView, cost);
                    break;
                case CRAP_ITEM_CODE:
                    crapOverlapAction(imageView, cost);
                    break;
                case SLEEPING_DRUG_ITEM_CODE:
                    sleepingDrugOverlapAction(imageView, cost);
                    break;
                case MOUSE_ENTITY_CODE:
                    mouseOverlapAction(imageView, cost);
                    break;

                default:makeText("wtf");
            }
        } else{return false;}
        return true;
    }

    private void mouseOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole), getString(R.string.asdaddvdfv), getString(R.string.wfwefw));
        } else if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            levelOver(true);
        }
    }

    private void sleepingDrugOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            giveMoney(SLEEPY_DRUG_SELL);
        }else if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.drgdrgdrg),getString(R.string.fgnfgngn));
            getBack(imageView);
        }
        if(cheeseList.size()>0){
            if(wereOverlappedCheeseWithDrug(cheese, imageView)){
                cheese=cheeseList.get(0);
                redraw(cheese, getResources().getDrawable(R.drawable.cheese_sleepy));
                MainActivity.soundPlayer.makePoisoningSound();
                cheeseSleepy=true;
            }
        }
    }

    private void crapOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.rrwr), getString(R.string.sfsr), getString(R.string.dtgdgs));
        }else if(wereOverlapped(cat, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            redraw(cat, getResources().getDrawable(R.drawable.cat_feeling_bad));
        }
    }

    private void cheeseOverlapAction(ImageView imageView, int cost) {
        if(!cheeseSleepy){
            if(wereOverlapped(mouse, imageView)){
                imageView.setVisibility(View.INVISIBLE);
                addItemProgrammatically(crap, itemSpawn, 0, CRAP_ITEM_CODE,getResources().getDrawable(R.drawable.crap2), mouse.getX()+10, mouse.getY()+40, 70, 70);
                MainActivity.soundPlayer.makeCatEaingSound2();
            }else if(wereOverlapped(mole, imageView)){
                giveMoney(cost);
                imageView.setVisibility(View.INVISIBLE);
            }
            else if(wereOverlapped(cat, imageView)){
                getBack(imageView);
                makeInfoDialogWindow(getString(R.string.cat), getString(R.string.thfnhhd), getString(R.string.svsef));
            }
        }else{
            if(wereOverlapped(cat, imageView)){
                getBack(imageView);
                makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qwqeq), getString(R.string.fgbfg));
            }else if(wereOverlapped(mole, imageView)){
                giveMoney(cost);
                imageView.setVisibility(View.INVISIBLE);
            } else if(wereOverlapped(mouse, imageView)){
                imageView.setVisibility(View.INVISIBLE);
                redraw(mouse, getResources().getDrawable(R.drawable.mouse_sleepy));
                mouse.setOnTouchListener(touch(mouse, 0, MOUSE_ENTITY_CODE));
                mouseSleeped=true;
                MainActivity.soundPlayer.makeCatEaingSound2();
            }
        }

    }

    private void fishOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.mole),getString(R.string.awdawdab),getString(R.string.gbfcgb));
        }else if(wereOverlapped(cat, imageView)){
            redraw(imageView, getResources().getDrawable(R.drawable.big_fish_bone));
            redraw(cat, getResources().getDrawable(R.drawable.poisoned_cat));
            alive=false;
            levelOver(alive);
        }
    }

    private void dwarfOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            giveMoney(cost);
        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.awdabdfb),getString(R.string.rthyrtge));
        }
    }

    private void bootOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            giveMoney(cost);
            imageView.setVisibility(View.INVISIBLE);
        }else if(wereOverlapped(dwarf, imageView)){
            if(dwarfKickCounter<5){
                dwarfKickCounter++;
                MainActivity.soundPlayer.makeWoodHitSound_2();
                //makeSound...
            }else if(dwarfKickCounter==5){
                dwarfKickCounter++;
                redraw(dwarf, getResources().getDrawable(R.drawable.dwarf_pushed));
                dwarf.setX(dwarf.getX()+20);
                dwarf.setY(dwarf.getY()+40);
                dwarf.getLayoutParams().height = 85;
                dwarf.getLayoutParams().width=133;
                dwarf.setOnTouchListener(touch(dwarf, DWARF_PRICE_SELL, DWARF_ITEM_CODE));
                dwarfLiftable=true;
                MainActivity.soundPlayer.makeWoodHitSound_2();
            }

        }else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.dfbdrgs), getString(R.string.ghngh));
            randomCatMeowSound();
        }
    }

    private void waterPlantOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.rtew), getString(R.string.vbgxfv));
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)){
            giveMoney(cost);
            imageView.setVisibility(View.INVISIBLE);

        }
    }

    private void appleOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.wertret),getString(R.string.ghnfg));
            randomCatMeowSound();
        }else if(wereOverlapped(mole, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            giveMoney(cost);

        }
    }

    private void coinBagOverlapAction(ImageView imageView) {
        if(wereOverlapped(mole, imageView)){
            makeShopDialog();
            getBack(imageView);
            makeMoleRandomSound();
        } else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.tyrgfcsvbfg),getString(R.string.vbcxfv));
            randomCatMeowSound();
        }
    }

    private void coinOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.ghfbdfrnrn), getString(R.string.sefgsrgab));
            randomCatMeowSound();
        }
        if(goldenBagList.size()>0){
            if(wereOverlappedGoldenBagWithCoin(goldBag, imageView)){
                imageView.setVisibility(View.INVISIBLE);
                moneyCounterChange(1);
                MainActivity.soundPlayer.makeCoinDropSound();
                redrawBag();
            }else if(wereOverlapped(mole, imageView)){
                getBack(imageView);
                makeInfoDialogWindow(getString(R.string.mole), getString(R.string.qeqwedg), getString(R.string.ghnbgfe));
                makeMoleRandomSound();
            }
        }else if(wereOverlapped(mole, imageView)){
            if(moleTouchCounter==0){
                addGoldBagProgrammatically(goldBag, itemSpawn, 0, COIN_BAG_ITEM_CODE, getResources().getDrawable(R.drawable.bag_of_gold),mole.getX()+mole.getWidth()+50, mole.getY()+20, 110,110);
                makeInfoDialogWindow(getString(R.string.mole), getString(R.string.zdce4grthw),getString(R.string.tsgtesm));
                coinCounter.setVisibility(View.VISIBLE);
                moleTouchCounter++;
                makeMoleRandomSound();
                getBack(imageView);
            }
        }

    }

    private void redrawBag() {
        if(moneyAmount>=13&&moneyAmount<16){
            redraw(goldenBagList.get(0), getResources().getDrawable(R.drawable.bag_of_gold_full));
        }else if(moneyAmount>=16){
            redraw(goldenBagList.get(0), getResources().getDrawable(R.drawable.bag_of_gold_full2));
        }else{
            redraw(goldenBagList.get(0), getResources().getDrawable(R.drawable.bag_of_gold));
        }
    }

    private void casualItemOverlapAction(ImageView imageView, int cost) {
        if(wereOverlapped(mole, imageView)){
            imageView.setVisibility(View.INVISIBLE);
            giveMoney(cost);

        } else if(wereOverlapped(cat, imageView)){
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.gsegn), getString(R.string.fnsbs));
            randomCatMeowSound();
        }
    }

    private void giveMoney(int cost) {
        if(cost<5){
            for(int i=0; i<cost; i++){
                rand=random.nextInt(50);
                addItemProgrammatically(itemSpawn, itemSpawn, 1, COIN_ITEM_CODE, getResources().getDrawable(R.drawable.coin),mole.getX()+mole.getWidth()+30+i*rand, mole.getY()+50, 50,50);
            }
        }else{
            for(int i=0; i<cost; i++){
                rand=random.nextInt(10);
                addItemProgrammatically(itemSpawn, itemSpawn, 1, COIN_ITEM_CODE, getResources().getDrawable(R.drawable.coin),mole.getX()+mole.getWidth()+30+i*rand, mole.getY()+50, 50,50);
            }
        }
        MainActivity.soundPlayer.makeItemSoldSound();

    }

    private void getBack(ImageView imageView) {
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
    private boolean wereOverlappedCheeseWithDrug(View view, View viewActive){
        float gbX=cheeseList.get(0).getX();
        float gbY=cheeseList.get(0).getY();
        int gbWidth=cheeseList.get(0).getWidth();
        int gbHeight=cheeseList.get(0).getHeight();
        int viewHeightDivided=gbHeight/4;
        int viewWidthDivided=gbWidth/4;
        if(viewActive.getY()+viewActive.getHeight()>gbY+viewHeightDivided&&viewActive.getY()<gbY+gbHeight-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>gbX+viewWidthDivided&&viewActive.getX()<gbX+gbWidth-viewWidthDivided){return true;}else {return false;}
    }

    public void moneyCounterChange(int change){
        moneyAmount+=change;
        moneyCountTXT.setText(moneyAmount+"");
    }
    private void addItemProgrammatically(ImageView image, ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        image = actionAddingProgrammatcally(bindTo, cost, itemCode, drawable, x, y, height, width);
    }
    private void checkThreadsBeforeExit() {
        if(controlSubThread!=null){
            controlSubThread.stop();
            controlSubThread.destroy();
        }
    }

    private void addGoldBagProgrammatically(ImageView image, ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        image = actionAddingProgrammatcally(bindTo, cost, itemCode, drawable, x, y, height, width);
        goldenBagList.add(0, image);
    }

    @NonNull
    private ImageView actionAddingProgrammatcally(ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        ImageView image;
        int addTo;
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) bindTo.getLayoutParams();
        image = new ImageView(this);
        image.setOnTouchListener(touch(image,cost, itemCode));
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
        return image;
    }


    private void addCheeseProgrammatically(ImageView image, ImageView bindTo, int cost, int itemCode, Drawable drawable, float x, float y, int height, int width) {
        image = actionAddingProgrammatcally(bindTo, cost, itemCode, drawable, x, y, height, width);
        cheeseList.add(0, image);
    }
    public void makeShopDialog(){

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.shop_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());


        price1=dialog.findViewById(R.id.price1);
        price2=dialog.findViewById(R.id.price2);
        price3=dialog.findViewById(R.id.price3);
        price4=dialog.findViewById(R.id.price4);
        price1.setText("  "+BOOT_PRICE);
        price2.setText("  "+CHEESE_PRICE);
        price3.setText(SLEEPY_DRUG_PRICE+"");
        price4.setText(BIG_FISH_PRICE+"");


        btn_buy1=dialog.findViewById(R.id.buttonBuy1);
        if(!bootBuyable)btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
        btn_buy2=dialog.findViewById(R.id.buttonBuy2);
        if(!sleepingDrugBuyable)btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
        btn_buy3=dialog.findViewById(R.id.buttonBuy3);
        if(!cheeseBuyable)btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
        btn_buy4=dialog.findViewById(R.id.buttonBuy4);
        if(!fishBuyable)btn_buy4.setBackground(getResources().getDrawable(R.drawable.shop_item_2));


        currentMoneyTxt = dialog.findViewById(R.id.currentMoneyTxt);
        currentMoneyTxt.setText(moneyAmount+"");

        btn_buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyAmount>=BOOT_PRICE){
                    if(bootBuyable){
                        moneyAmount-=BOOT_PRICE;
                        addItemProgrammatically(boot, itemSpawn, DIRTY_OLD_BOOT_SELL, DIRTY_OLD_BOOT_ITEM_CODE, getResources().getDrawable(R.drawable.old_boot), mole.getX()+mole.getWidth()+80, mole.getY(), 100, 100);
                        currentMoneyTxt.setText(moneyAmount+"");
                        moneyCountTXT.setText(moneyAmount+"");
                        btn_buy1.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
                        bootBuyable=false;
                        MainActivity.soundPlayer.makeCashRegisterSound();
                        redrawBag();
                    }
                }
            }
        });
        btn_buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyAmount>=SLEEPY_DRUG_PRICE){
                    if(sleepingDrugBuyable){
                        moneyAmount-=SLEEPY_DRUG_PRICE;
                        addItemProgrammatically(sleeping_drug, itemSpawn, SLEEPY_DRUG_SELL, SLEEPING_DRUG_ITEM_CODE, getResources().getDrawable(R.drawable.sleeping_drug), mole.getX()+mole.getWidth()+80, mole.getY(), 110, 40);
                        currentMoneyTxt.setText(moneyAmount+"");
                        moneyCountTXT.setText(moneyAmount+"");
                        btn_buy2.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
                        sleepingDrugBuyable=false;
                        MainActivity.soundPlayer.makeCashRegisterSound();
                        redrawBag();
                    }


                }
            }
        });
        btn_buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyAmount>=CHEESE_PRICE){
                    if(cheeseBuyable){
                        moneyAmount-=CHEESE_PRICE;
                        addCheeseProgrammatically(cheese, itemSpawn, CHEESE_PRICE_SELL, CHEESE_ITEM_CODE, getResources().getDrawable(R.drawable.cheese), mole.getX()+mole.getWidth()+80, mole.getY(), 60, 90);
                        currentMoneyTxt.setText(moneyAmount+"");
                        moneyCountTXT.setText(moneyAmount+"");
                        btn_buy3.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
                        cheeseBuyable=false;
                        MainActivity.soundPlayer.makeCashRegisterSound();
                        redrawBag();
                    }


                }
            }
        });
        btn_buy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyAmount>=BIG_FISH_PRICE){
                    if(fishBuyable){
                        moneyAmount-=BIG_FISH_PRICE;
                        addItemProgrammatically(bigFishSpoiled, itemSpawn, 0, BIG_SPOILED_FISH_ITEM_CODE, getResources().getDrawable(R.drawable.big_spoiled_fish), mole.getX()+mole.getWidth()+80, mole.getY(), 80, 140);
                        currentMoneyTxt.setText(moneyAmount+"");
                        moneyCountTXT.setText(moneyAmount+"");
                        btn_buy4.setBackground(getResources().getDrawable(R.drawable.shop_item_2));
                        fishBuyable=false;
                        MainActivity.soundPlayer.makeCashRegisterSound();
                        redrawBag();
                    }


                }
            }
        });


        btn_closeShop=dialog.findViewById(R.id.close_shop);
        btn_closeShop.setOnClickListener(close());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
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
        back.setText(R.string.awdagdn);
        back.setOnClickListener(toMainMenu());

        if(win){
            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.LEVEL_5_UNLOCK_KEY, 55);
            mEditor.apply();


            MainActivity.soundPlayer.makeVictorySound();
            text1.setText(R.string.nberhjkiythg);
            text2.setText(R.string.nbvscs);
            ok.setText(R.string.nrgwergwr);
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_boss));
            ok.setOnClickListener(toLevelFive());
        }else{
            int d1, d2, d3, d4, d5, d6;
            d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
            d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
            d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
            d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
            d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
            d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
            if(d1==1&&d2==1&&d3==1&&d4==0&&d5==1&&d6==1){
                makeText2();
            }


            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_4, 1);
            mEditor.apply();


            MainActivity.soundPlayer.makeFailureSound();
            text1.setText(R.string.nhgrfaehtrjy);
            text2.setText(R.string.fnwgwegw);
            ok.setText(R.string.tyjtyjr);
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
    @NonNull
    private View.OnClickListener toMainMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_four.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener toLevelFive() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Level_four.this, Level_five.class);
//                intent.putExtra(MainActivity.KEY, 1);
//                startActivity(intent);
                Intent intent=new Intent(Level_four.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.OPEN_LEVELS_DIALOG);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener startOver() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_four.this, Level_four.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                startActivity(intent);
            }
        };
    }
    @NonNull
    private View.OnClickListener restartLevel() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_four.this, Level_four.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                checkThreadsBeforeExit();
                startActivity(intent);
            }
        };
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
    public void makeMoleRandomSound(){
        rand=random.nextInt(100);
        if(rand<=50){
            MainActivity.soundPlayer.makeMoleSound1();
        }else{
            MainActivity.soundPlayer.makeMoleSound2();
        }
    }
    private void makeText2() {
        Toast.makeText(getBaseContext(), R.string.jtyjt,Toast.LENGTH_LONG ).show();
    }
}
