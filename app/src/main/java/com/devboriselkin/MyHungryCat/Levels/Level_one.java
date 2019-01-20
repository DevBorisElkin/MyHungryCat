package com.devboriselkin.MyHungryCat.Levels;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.Random;

import com.devboriselkin.MyHungryCat.MenuFolder.MainActivity;
import com.devboriselkin.MyHungryCat.R;

import static com.devboriselkin.MyHungryCat.MenuFolder.MainActivity.musicPlayer;

public class Level_one extends AppCompatActivity {
    static Dialog dialog;
    TextView text1, text2;
    Button back, ok;
    public static float xOld, yOld;
    public int check;
    ImageView cat, poison, fish, icon;
    int catClickCounter=0;
    float dX, dY;
    public static final int FISH_ITEM_CODE=1;
    public static final int POISON_ITEM_CODE=14;
    boolean alive=true;
    CardView cardView;
    boolean fishPoisoned=false;
    Random random=new Random();
    int rand=0;
    SharedPreferences mySharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.level_one);

        mySharedPref=getSharedPreferences(MainActivity.KEY, MODE_PRIVATE);

        Intent intent=getIntent();
        if(intent!=null){
            check=intent.getIntExtra(MainActivity.KEY, 0);
        }
        if(check==MainActivity.CASUAL_NOTIF_START_LEVEL_PLUS_MUSIC_CODE){
            makeInfoDialogWindow(getString(R.string.it_is_level_one),getString(R.string.i_think_you_don_t_need_help),getString(R.string.play_game));
            musicPlayer.stopBackgroundMusic();
            musicPlayer.releaseMusicInMenu();
            musicPlayer.startInGameMusic();

        }else if(check==MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE){
            //just nothing occurs
        }
        cat=findViewById(R.id.cat);
        fish=findViewById(R.id.fish);
        poison=findViewById(R.id.poison);
        cat.setOnClickListener(catClick());
        fish.setOnTouchListener(touch(fish, 0, FISH_ITEM_CODE));
        poison.setOnTouchListener(touch(poison, 0, POISON_ITEM_CODE));

    }

    @NonNull
    private View.OnClickListener catClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catClickCounter<=3){
                    makeInfoDialogWindow(getString(R.string.cat),getString(R.string.q), getString(R.string.w));
                    randomCatMeowSound();
                } else if(catClickCounter>3&&catClickCounter<7){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.e), getString(R.string.r));
                    randomCatMeowSound();
                } else if(catClickCounter==8){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.t), getString(R.string.y));
                    randomCatMeowSound();
                } else if(catClickCounter==9){
                    makeInfoDialogWindow(getString(R.string.cat), getString(R.string.u),getString(R.string.i));
                    MainActivity.soundPlayer.makeCatLoudSound();
                } else{

                }
                catClickCounter++;
            }
        };
    }

    private void newLevelStartDialog(String titleText, String text, String buttonBackText, String btnOkayText) {
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setOnKeyListener(dialogOnBackPressed());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        back = dialog.findViewById(R.id.buttonBack);
        ok = dialog.findViewById(R.id.buttonOkay);
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        text1.setText(titleText);
        text2.setText(text);
        ok.setText(btnOkayText);
        back.setText(buttonBackText);
        ok.setOnClickListener(close());
        back.setOnClickListener(toMainMenu());


    }


    @NonNull
    private View.OnClickListener toMainMenu() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_one.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
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
                //getBack(imageView);
            }
        };
    }
    private static void getBack(ImageView imageView) {
        imageView.setX(xOld);
        imageView.setY(yOld);
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
            switch (itemCode){
                case FISH_ITEM_CODE:
                    fishOverlapAction(imageView, increment);
                    break;
                case POISON_ITEM_CODE:
                    poisonOverlapAction(imageView);
                    break;
                default:makeText("wtf");
            }
        } else{return false;}
        return true;
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

    private void poisonOverlapAction(ImageView imageView) {
        if(wereOverlapped(cat,imageView)){
            makeInfoDialogWindow(getString(R.string.cat),getString(R.string.o),getString(R.string.p));
            getBack(imageView);
            randomCatMeowSound();
        }else if(wereOverlapped(fish, imageView)){
            redraw(fish, getResources().getDrawable(R.drawable.poisoned_fish));
            fishPoisoned=true;
            imageView.setX(fish.getX()-80);
            MainActivity.soundPlayer.makePoisoningSound();
        }
    }

    private void fishOverlapAction(ImageView imageView, int increment) {
        if(wereOverlapped(cat, imageView)&&alive&&!fishPoisoned){
            catEatingSound();
            imageView.setVisibility(View.INVISIBLE);
            levelOver(true);
        }else if (wereOverlapped(cat, imageView)&&alive&&fishPoisoned){
            catEatingSound();
            imageView.setVisibility(View.INVISIBLE);
            redraw(cat, getResources().getDrawable(R.drawable.poisoned_cat));
            alive=false;
            levelOver(false);
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

    private boolean wereOverlapped(View view, View viewActive) {
        int viewHeightDivided=view.getHeight()/4;
        int viewWidthDivided=view.getWidth()/4;
        if(viewActive.getY()+viewActive.getHeight()>view.getY()+viewHeightDivided&&viewActive.getY()<view.getY()+view.getHeight()-viewHeightDivided
                &&viewActive.getX()+viewActive.getWidth()>view.getX()+viewWidthDivided&&viewActive.getX()<view.getX()+view.getWidth()-viewWidthDivided){return true;}else {return false;}
    }
    @NonNull
    private View.OnClickListener toLevelTwo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Level_one.this, Level_two.class);
//                intent.putExtra(MainActivity.KEY, MainActivity.CASUAL_NOTIF_START_LEVEL_CODE);
//                startActivity(intent); old
                Intent intent=new Intent(Level_one.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.OPEN_LEVELS_DIALOG);
                startActivity(intent);

            }
        };
    }
    @SuppressLint("SetTextI18n")
    public void levelOver(Boolean win){
        dialog=new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        dialog.setContentView(R.layout.game_popup_menu2);
        text1=dialog.findViewById(R.id.text1);
        text2=dialog.findViewById(R.id.text2);
        ok=dialog.findViewById(R.id.btnOk);
        back=dialog.findViewById(R.id.btnBack);
        icon=dialog.findViewById(R.id.img1);
        back.setText(R.string.a);
        back.setOnClickListener(toMainMenu());

        if(win){

            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.LEVEL_2_UNLOCK_KEY, 22);
            mEditor.apply();


            text1.setText(R.string.s);
            text2.setText(R.string.d);
            ok.setText(R.string.f);
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_boss));
            ok.setOnClickListener(toLevelTwo());
            MainActivity.soundPlayer.makeVictorySound();
        }else{

            int d1, d2, d3, d4, d5, d6;
            d1=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_1, 0);
            d2=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_2, 0);
            d3=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_3, 0);
            d4=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_4, 0);
            d5=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_5, 0);
            d6=mySharedPref.getInt(MainActivity.CAT_KILLED_LEVEL_6, 0);
            if(d1==0&&d2==1&&d3==1&&d4==1&&d5==1&&d6==1){
                makeText2();
            }


            SharedPreferences.Editor mEditor=mySharedPref.edit();
            mEditor.putInt(MainActivity.CAT_KILLED_LEVEL_1, 1);
            mEditor.apply();


            text1.setText(R.string.g);
            text2.setText(R.string.h);
            ok.setText(R.string.j);
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_block));
            ok.setOnClickListener(startOver());
            cardView=dialog.findViewById(R.id.cardView);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.pinkNotif));
            ok.setBackground(getResources().getDrawable(R.drawable.pink_button));
            back.setBackground(getResources().getDrawable(R.drawable.pink_button));
            MainActivity.soundPlayer.makeFailureSound();


        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @NonNull
    private View.OnClickListener startOver() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_one.this, Level_one.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_RESTART_LEVEL_CODE);
                startActivity(intent);
            }
        };
    }
    private void makeText(String a) {
        Toast.makeText(getBaseContext(), a,Toast.LENGTH_SHORT ).show();
    }
    private void makeText2() {
        Toast.makeText(getBaseContext(), R.string.k,Toast.LENGTH_LONG ).show();
    }
    private void redraw(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
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
    @SuppressLint("SetTextI18n")
    private void exitToMenu() {
        dialog=new Dialog(Level_one.this);
        dialog.setContentView(R.layout.game_popup_menu1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(dialogOnBackPressed());
        text1 = dialog.findViewById(R.id.text1);
        text2 = dialog.findViewById(R.id.text2);
        back=dialog.findViewById(R.id.buttonBack);
        back.setText(R.string.l);
        back.setOnClickListener(close());
        ok=dialog.findViewById(R.id.buttonOkay);
        ok.setText(R.string.z);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level_one.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, MainActivity.IN_GAME_MUSIC_WAS_TURNED_ON_CODE);
                startActivity(intent);
            }
        });
        text1.setText(R.string.x);
        text2.setText(R.string.c);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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
}
