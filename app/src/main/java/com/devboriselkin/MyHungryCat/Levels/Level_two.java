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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.ProgressBarAnimation;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_two extends AppCompatActivity {
    static Dialog dialog;
    TextView text1, text2;
    Button back, ok;
    public static float xOld, yOld;
    public int check;
    public int rottenFoodLimit = -1700;
    public int rottenFoodConsumed = 0;
    public int lidCatOverlap = 0;
    ImageView cat, fish1, fish2, bigFish, trb, restart, icon, bananaSkin, spray, graffiti;
    int catClickCounter = 0;
    int foodWasEaten=0;
    float dX, dY;
    boolean redrawable=true;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;
    int rand;
    Random random= new Random();
    boolean endWasNotLaunched=true;
    boolean endWasNotLaunched2=true;
    Handler mHandler;
    SharedPreferences mySharedPref;


    public static final int FISH_INCREMENT = 700;
    public static final int BIG_FISH_INCREMENT = 1200;


    public static final int FISH_ITEM_CODE = 1;
    public static final int BIG_FISH_ITEM_CODE = 2;
    public static final int BANANA_SKIN_ITEM_CODE = 3;
    public static final int BONE_FISH_ITEM_CODE = 10;
    public static final int SPRAY_ITEM_CODE=4;
    public static final int TRB_MOVE_CODE=5;

    ControlSubThread controlSubThread;
    Handler handler;

    boolean alive = true;
    CardView cardView;
    public int tooHighCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.level_two);

        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_CODE){
            makeInfoDialogWindow(getString(R.string.v),getString(R.string.b),getString(R.string.n));
        }else if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE){
            makeInfoDialogWindow(getString(R.string.v),getString(R.string.m),getString(R.string.n));
            musicPlayer.stopBackgroundMusic();
            musicPlayer.releaseMusicInMenu();
            musicPlayer.startInGameMusic();
        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            //nothing here
        }

        initUI();

    }

    private void initUI() {
        mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);
        constraintLayout = findViewById(R.id.layout);
        cat = findViewById(R.id.cat);
        trb = findViewById(R.id.trb);
        fish1 = findViewById(R.id.fish1);
        fish2 = findViewById(R.id.fish2);
        bigFish = findViewById(R.id.bigFish);
        progressBar = findViewById(R.id.progressBar);
        restart = findViewById(R.id.restart);
        bananaSkin=findViewById(R.id.banana_skin);
        graffiti=findViewById(R.id.graffiti);
        spray=findViewById(R.id.spray);
        graffiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!redrawable){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qw),getString(R.string.qe));
                    randomCatMeowSound();
                }
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catClickCounter<3){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.qr),getString(R.string.qt));
                    randomCatMeowSound();
                }else if(catClickCounter>=3&&catClickCounter<6){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.qy),getString(R.string.qu));
                    randomCatMeowSound();
                }else if(catClickCounter==6){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qi),getString(R.string.qo));
                    randomCatMeowSound();
                }else if(catClickCounter>6&&catClickCounter<12){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qp),getString(R.string.qa));
                    redraw(cat, getResources().getDrawable(R.drawable.sad_cat_big2));
                    rand=random.nextInt(10);
                    if(rand<4){
                        MainActivity.soundPlayer.makeCatLoudSound();
                    }else{
                        randomCatMeowSound();
                    }

                }
                else if(catClickCounter==12){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qs),getString(R.string.qd));
                    randomCatMeowSound();
                }else if(catClickCounter==13){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.qf), getString(R.string.qg));
                    MainActivity.soundPlayer.makeWoodHitSound_6();
                }else if(catClickCounter>13&&catClickCounter<18){
                    MainActivity.soundPlayer.makeWoodHitSound_6();
                }else if(catClickCounter==19){
                    redraw(cat, getResources().getDrawable(R.drawable.sad_cat_beated));
                    MainActivity.soundPlayer.makeWoodHitSound_6();
                }else if(catClickCounter>19&&catClickCounter<24){
                    MainActivity.soundPlayer.makeWoodHitSound_6();
                }else if(catClickCounter==25){
                    redraw(cat, getResources().getDrawable(R.drawable.sad_cat_beated2));
                    levelOver(false);
                }
                catClickCounter++;

            }
        });

        fish1.setOnTouchListener(touch(fish1, FISH_INCREMENT, FISH_ITEM_CODE));
        fish2.setOnTouchListener(touch(fish2, FISH_INCREMENT, FISH_ITEM_CODE));
        bigFish.setOnTouchListener(touch(bigFish, BIG_FISH_INCREMENT, BIG_FISH_ITEM_CODE));
        bananaSkin.setOnTouchListener(touch(bananaSkin, 0, BANANA_SKIN_ITEM_CODE));
        spray.setOnTouchListener(touch(spray, 0, SPRAY_ITEM_CODE));
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkThreadsBeforeExit();
                Intent intent=new Intent(Level_two.this, Level_two.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                startActivity(intent);
            }
        });

        trb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                trbMoveAction();
                return true;
            }
        });
        handler = new Handler(new Handler.Callback() {
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

    private void trbMoveAction() {
        MainActivity.soundPlayer.makeMoveBinSound();
        if(controlSubThread==null){
            controlSubThread=new ControlSubThread(20,0, TRB_MOVE_CODE);
            controlSubThread.start();
        }

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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            dX = imageView.getX() - event.getRawX();
            dY = imageView.getY() - event.getRawY();
            xOld = imageView.getX();
            yOld = imageView.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            imageView.animate()
                    .x(event.getRawX() + dX)
                    .y(event.getRawY() + dY)
                    .setDuration(0)
                    .start();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (event.getRawY() + dY < constraintLayout.getHeight() / 3) {
//                getBack(imageView);
//                if (tooHighCounter == 0) {
//                    makeText("Too high");
//                    tooHighCounter++;
//                }
//
//            }
            switch (itemCode) {
                case FISH_ITEM_CODE:
                    fishOverlapAction(imageView, increment);
                    break;
                case BIG_FISH_ITEM_CODE:
                    bigFishOverlapAction(imageView, increment);
                    break;
                case BANANA_SKIN_ITEM_CODE:
                    bananaSkinOverlapAction(imageView);
                    break;
                case BONE_FISH_ITEM_CODE:
                    boneFishOverlapAction(imageView);
                    break;
                case SPRAY_ITEM_CODE:
                    sprayOverlapAction(imageView);
                    break;
                default:
                    makeText("wtf");
            }
        } else {
            return false;
        }
        return true;
    }

    private void sprayOverlapAction(ImageView imageView) {
        if(wereOverlapped(graffiti, imageView)&&redrawable){
            redraw(graffiti, getResources().getDrawable(R.drawable.white_cat_graffiti));
            redrawable=false;
            MainActivity.soundPlayer.makeGraffitiSound();
        }else if(wereOverlapped(cat, imageView)){
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.qz), getString(R.string.qx));
            randomCatMeowSound();
            getBack(imageView);
        }
    }

    private void bananaSkinOverlapAction(ImageView imageView) {
        if (wereOverlapped(cat, imageView)) {
            getBack(imageView);
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qc), getString(R.string.qv));
            randomCatMeowSound();
        }
    }

    private void bigFishOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(cat, imageView)){
            redraw(imageView, getResources().getDrawable(R.drawable.big_fish_bone));
            imageView.setX(cat.getX() - 90);
            imageView.setOnTouchListener(touch(imageView, 0, BONE_FISH_ITEM_CODE));
            MainActivity.soundPlayer.makeLongEatingSound();
            changeProgress(increment);
            checkVictory(increment);
        }

    }

    private void fishOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(cat, imageView)){
            redraw(imageView, getResources().getDrawable(R.drawable.fish_bone));
            imageView.setX(cat.getX() - 90);
            imageView.setOnTouchListener(touch(imageView, 0, BONE_FISH_ITEM_CODE));
            catEatingSound();
            changeProgress(increment);
            checkVictory(increment);

        }

    }

    private void getBack(ImageView imageView) {
        imageView.animate().x(xOld).y(yOld).setDuration(0).start();
    }

    private void makeText(String a) {
        Toast.makeText(getBaseContext(), a, Toast.LENGTH_SHORT).show();
    }

    private boolean wereOverlapped(View view, View viewActive) {
        int viewHeightDivided = view.getHeight() / 4;
        int viewWidthDivided = view.getWidth() / 4;
        if (viewActive.getY() + viewActive.getHeight() > view.getY() + viewHeightDivided && viewActive.getY() < view.getY() + view.getHeight() - viewHeightDivided
                && viewActive.getX() + viewActive.getWidth() > view.getX() + viewWidthDivided && viewActive.getX() < view.getX() + view.getWidth() - viewWidthDivided) {
            return true;
        } else {
            return false;
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

    public void makeInfoDialogWindow(String title, String text, String buttonPositive) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.game_popup1);
      //  dialog.setCanceledOnTouchOutside(false);
      //  dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back = dialog.findViewById(R.id.buttonOkay);
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
                    return true;
                }
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

    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    private void boneFishOverlapAction(ImageView imageView) {
        if (wereOverlapped(cat, imageView)) {
            makeInfoDialogWindow(getString(R.string.cat), getString(R.string.qb), getString(R.string.qn));
            randomCatMeowSound();
            getBack(imageView);
        }
    }

    public void checkVictory(int increment) {
        if (progressBar.getProgress() + increment >= 2000) {
            //checkThreadsBeforeExit();
            levelOver(true);
        } else if (!alive) {
            //checkThreadsBeforeExit();
            levelOver(false);
        }
    }
    public void checkVictory2() {
        if (progressBar.getProgress() >= 2000&&endWasNotLaunched) {
            endWasNotLaunched=false;
                    Message msg = new Message();
                    msg.arg1=1;
                    handler.sendMessage(msg);
        }
    }

    public void levelOver(Boolean win) {
        if(endWasNotLaunched2){
            endWasNotLaunched2=false;
            checkThreadsBeforeExit();
            dialog = new Dialog(this);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(dialogOnBackPressed());
            dialog.setContentView(R.layout.game_popup_menu2);
            text1 = dialog.findViewById(R.id.text1);
            text2 = dialog.findViewById(R.id.text2);
            ok = dialog.findViewById(R.id.btnOk);
            back = dialog.findViewById(R.id.btnBack);
            icon = dialog.findViewById(R.id.img1);
            back.setText(R.string.qk);
            back.setOnClickListener(toMainMenu());

            if (win) {
                SharedPreferences.Editor mEditor=mySharedPref.edit();
                mEditor.putInt(MainActivity.LEVEL_3_UNLOCK_KEY, 33);
                mEditor.apply();


                text1.setText(R.string.qsa);
                text2.setText(R.string.qwe);
                ok.setText(R.string.qrr);
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_boss));
                ok.setOnClickListener(toLevelThree());
                MainActivity.soundPlayer.makeVictorySound();
            } else {
                int d1, d2, d3, d4, d5, d6;
                d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
                d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
                d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
                d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
                d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
                d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
                if(d1==1&&d2==0&&d3==1&&d4==1&&d5==1&&d6==1){
                    makeText2();
                }


                SharedPreferences.Editor mEditor=mySharedPref.edit();
                mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_2, 1);
                mEditor.apply();


                text1.setText(R.string.hg);
                text2.setText(R.string.gf);
                ok.setText(R.string.nnb);
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));
                ok.setOnClickListener(startOver());
                cardView = dialog.findViewById(R.id.cardView);
                cardView.setCardBackgroundColor(getResources().getColor(R.color.pinkNotif));
                ok.setBackground(getResources().getDrawable(R.drawable.pink_button));
                back.setBackground(getResources().getDrawable(R.drawable.pink_button));
                MainActivity.soundPlayer.makeFailureSound();

            }
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

    }


    @NonNull
    private View.OnClickListener toLevelThree() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Level_two.this, Level_three.class);
//                intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_CODE);
//                startActivity(intent);
                Intent intent=new Intent(Level_two.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.OPEN_LEVELS_DIALOG);
                startActivity(intent);
            }
        };
    }


    @NonNull
    private View.OnClickListener toMainMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_two.this, MainActivity.class);
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
                Intent intent=new Intent(Level_two.this, Level_two.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
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
    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        exitToMenu();
    }
    private void checkThreadsBeforeExit() {
        if(controlSubThread!=null){
            controlSubThread.stop();
            controlSubThread.destroy();
        }
    }
    private void exitToMenu() {
        dialog=new Dialog(Level_two.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText(R.string.nb);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText(R.string.afer);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level_two.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText(R.string.erg);
        text2.setText(R.string.thregrfw);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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
        public void resume() {
            running=true;
            run();
        }
        public void stop() {
            running=false;
        }
        public void destroy(){
            worker.interrupt();
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
                if(moveCode==TRB_MOVE_CODE){
                    trbMoveAction();
                }


            }
        }
        private void trbMoveAction() {
            try {
                Thread.sleep(littleInterval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            trb.setTranslationX(trb.getTranslationX()-1);
            if(trb.getX()+trb.getWidth()==0){
                running=false;
                controlSubThread=null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        checkThreadsBeforeExit();
        super.onDestroy();
    }
    private void makeText2() {
        Toast.makeText(getBaseContext(), R.string.bcd,Toast.LENGTH_LONG ).show();
    }
}
