package com.devboriselkin.MyHungryCat.Media;


import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundPlayer1 {
    int meow1, meow2, meow3, meow4, victory, failure, poisoning, eating1, eating2, eating3,
            eating4, catBeforeDeath, catSuperAngry, catFeelingBad, catLoud, purring, cashRegister;
    List<MediaPlayer> playersList=null;


    Context context;

    MediaPlayer mpCatMeow1, mpCatMeow2, mpCatMeow3, mpCatMeow4,mpVictory,mpFailure,mpPoisoning,mpCatEating1,mpCatEating2,mpCatEating3,mpCatEating4,mpCatBeforeDeath,mpCatSuperAngry,
            mpCatFeelingBad,mpCatLoud,mpPurring,mpCashRegister;


    public SoundPlayer1(Context context, int meow1, int meow2, int meow3, int meow4, int victory, int failure, int poisoning, int eating1, int eating2, int eating3, int eating4,
                       int catBeforeDeath, int catSuperAngry, int catFeelingBad, int catLoud, int purring, int cashRegister){
        this.context=context;
        this.meow1=meow1;
        this.meow2=meow2;
        this.meow3=meow3;
        this.meow4=meow4;
        this.victory=victory;
        this.failure=failure;
        this.poisoning=poisoning;
        this.eating1=eating1;
        this.eating2=eating2;
        this.eating3=eating3;
        this.eating4=eating4;
        this.catBeforeDeath=catBeforeDeath;
        this.catSuperAngry=catSuperAngry;
        this.catFeelingBad=catFeelingBad;
        this.catLoud=catLoud;
        this.purring=purring;
        this.cashRegister=cashRegister;


        mpCatMeow1=MediaPlayer.create(context,meow1);
        mpCatMeow2=MediaPlayer.create(context,meow2);
        mpCatMeow3=MediaPlayer.create(context,meow3);
        mpCatMeow4=MediaPlayer.create(context,meow4);
        mpVictory=MediaPlayer.create(context,victory);
        mpFailure=MediaPlayer.create(context,failure);
        mpPoisoning=MediaPlayer.create(context,poisoning);
        mpCatEating1=MediaPlayer.create(context,eating1);
        mpCatEating2=MediaPlayer.create(context,eating2);
        mpCatEating3=MediaPlayer.create(context,eating3);
        mpCatEating4=MediaPlayer.create(context,eating4);
        mpCatBeforeDeath=MediaPlayer.create(context,catBeforeDeath);
        mpCatSuperAngry=MediaPlayer.create(context,catSuperAngry);
        mpCatFeelingBad=MediaPlayer.create(context,catFeelingBad);
        mpCatLoud=MediaPlayer.create(context,catLoud);
        mpPurring=MediaPlayer.create(context,purring);
        mpCashRegister=MediaPlayer.create(context,cashRegister);

        playersList =new ArrayList<>();
        playersList.add(mpCatMeow1);
        playersList.add(mpCatMeow2);
        playersList.add(mpCatMeow3);
        playersList.add(mpCatMeow4);
        playersList.add(mpVictory);
        playersList.add(mpFailure);
        playersList.add(mpPoisoning);
        playersList.add(mpCatEating1);
        playersList.add(mpCatEating2);
        playersList.add(mpCatEating3);
        playersList.add(mpCatEating4);
        playersList.add(mpCatBeforeDeath);
        playersList.add(mpCatSuperAngry);
        playersList.add(mpCatFeelingBad);
        playersList.add(mpCatLoud);
        playersList.add(mpPurring);
        playersList.add(mpCashRegister);

    }
    //---------------------------------------------------------------------------------------------
    public void makeMeowSound1(){
        prepareSounds();
        stopPreviousSounds();
        mpCatMeow1.start();
    }
    public void makeMeowSound2(){
        prepareSounds();
        stopPreviousSounds();
        mpCatMeow2.start();
    }
    public void makeMeowSound3(){
        prepareSounds();
        stopPreviousSounds();
        mpCatMeow3.start();
    }
    public void makeMeowSound4(){
        prepareSounds();
        stopPreviousSounds();
        mpCatMeow4.start();
    }
    public void makeVictorySound(){
        prepareSounds();
        stopPreviousSounds();
        mpVictory.start();
    }
    public void makeFailureSound(){
        prepareSounds();
        stopPreviousSounds();
        mpFailure.start();
    }
    public void makePoisoningSound(){
        prepareSounds();
        stopPreviousSounds();
        mpPoisoning.start();
    }
    public void makeCatEaingSound1(){
        prepareSounds();
        stopPreviousSounds();
        mpCatEating1.start();
    }
    public void makeCatEaingSound2(){
        prepareSounds();
        stopPreviousSounds();
        mpCatEating2.start();
    }
    public void makeCatEaingSound3(){
        prepareSounds();
        stopPreviousSounds();
        mpCatEating3.start();
    }
    public void makeCatEaingSound4(){
        prepareSounds();
        stopPreviousSounds();
        mpCatEating4.start();
    }
    public void makeCatBeforeDeathSound(){
        prepareSounds();
        stopPreviousSounds();
        mpCatBeforeDeath.start();
    }
    public void makeCatSuperAngrySound(){
        prepareSounds();
        stopPreviousSounds();
        mpCatSuperAngry.start();
    }
    public void makeCatFeelingBadSound(){
        prepareSounds();
        stopPreviousSounds();
        mpCatFeelingBad.start();
    }
    public void makeCatLoudSound(){
        prepareSounds();
        stopPreviousSounds();
        mpCatLoud.start();
    }
    public void makePurringSound(){
        prepareSounds();
        stopPreviousSounds();
        mpPurring.start();
    }

    public void makeCashRegisterSound(){
        prepareSounds();
        stopPreviousSounds();
        mpCashRegister.start();
    }
    public void release(){
        for(int i = 0; i<playersList.size();i++){
            playersList.get(i).reset();
            playersList.get(i).release();
        }
    }
    public void stopPreviousSounds(){
        for(int i = 0; i<playersList.size();i++){
            if(playersList.get(i).isPlaying()){
                playersList.get(i).pause();
                playersList.get(i).seekTo(0);
            }
        }
    }
    public void prepareSounds(){

    }


    //---------------------------------------------------------------------------------------------

}
//        mpCatMeow1.release();
//        mpCatMeow2.release();
//        mpCatMeow3.release();
//        mpCatMeow4.release();
//        mpVictory.release();
//        mpFailure.release();
//        mpPoisoning.release();
//        mpCatEating1.release();
//        mpCatEating2.release();
//        mpCatEating3.release();
//        mpCatEating4.release();
//        mpCatBeforeDeath.release();
//        mpCatSuperAngry.release();
//        mpCatFeelingBad.release();
//        mpCatLoud.release();
//        mpPurring.release();
//        mpCashRegister.release();
//        mpMovingDoor.release();
//        mpBinPushed.release();
//        mphb1.release();
//        mphb2.release();
//        mphb3.release();
//        mphb4.release();
//        mpTotalWin.release();
//        mpwh1.release();
//        mpwh2.release();
//        mpwh3.release();
//        mpwh4.release();
//        mpwh5.release();
//        mpwh6.release();
//        mpTotallyBroken.release();
//        mpLongEating.release();