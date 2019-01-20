package com.devboriselkin.MyHungryCat.Media;


import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundPlayer2 {
    int meow1, meow2, meow3, meow4, victory, failure, poisoning, eating1, eating2, eating3,
            eating4, catBeforeDeath, catSuperAngry, catFeelingBad, catLoud, purring, cashRegister,
            graffiti, movingBin, movingDoor, binPushed, hb1, hb2, hb3, hb4, totalWin, wh1, wh2_dwarf, wh3, wh4, wh5, wh6, totally_broken, long_eating;
    List<MediaPlayer> playersList=null;


    Context context;

    MediaPlayer mpGraffiti,mpMovingBin,mpMovingDoor,mpBinPushed,mphb1,mphb2,mphb3,mphb4,mpTotalWin,
            mpwh1,mpwh2,mpwh3,mpwh4,mpwh5,mpwh6,mpTotallyBroken, mpLongEating;


    public SoundPlayer2(Context context, int graffiti, int movingBin, int movingDoor,
                       int binPushed, int hb1, int hb2, int hb3, int hb4, int totalWin, int wh1, int wh2_dwarf, int wh3, int wh4, int wh5, int wh6, int totally_broken, int long_eating){
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
        this.graffiti=graffiti;
        this.movingBin=movingBin;
        this.movingDoor=movingDoor;
        this.binPushed=binPushed; this.hb1=hb1; this.hb2=hb2; this.hb3=hb3; this.hb4=hb4; this.totalWin=totalWin; this.wh1=wh1; this.wh2_dwarf=wh2_dwarf; this.wh3=wh3;
        this.wh4=wh4; this.wh5=wh5; this.wh6=wh6; this.totally_broken=totally_broken;
        this.long_eating=long_eating;

        mpGraffiti=MediaPlayer.create(context,graffiti);
        mpMovingBin=MediaPlayer.create(context,movingBin);
        mpMovingDoor=MediaPlayer.create(context,movingDoor);
        mpBinPushed=MediaPlayer.create(context,binPushed);
        mphb1=MediaPlayer.create(context,hb1);
        mphb2=MediaPlayer.create(context,hb2);
        mphb3=MediaPlayer.create(context,hb3);
        mphb4=MediaPlayer.create(context,hb4);
        mpTotalWin=MediaPlayer.create(context,totalWin);
        mpwh1=MediaPlayer.create(context,wh1);
        mpwh2=MediaPlayer.create(context,wh2_dwarf);
        mpwh3=MediaPlayer.create(context,wh3);
        mpwh4=MediaPlayer.create(context,wh4);
        mpwh5=MediaPlayer.create(context,wh5);
        mpwh6=MediaPlayer.create(context,wh6);
        mpTotallyBroken=MediaPlayer.create(context,totally_broken);
        mpLongEating=MediaPlayer.create(context, long_eating);

        playersList =new ArrayList<>();
        playersList.add(mpGraffiti);
        playersList.add(mpMovingBin);
        playersList.add(mpMovingDoor);
        playersList.add(mpBinPushed);
        playersList.add(mphb1);
        playersList.add(mphb2);
        playersList.add(mphb3);
        playersList.add(mphb4);
        playersList.add(mpTotalWin);
        playersList.add(mpwh1);
        playersList.add(mpwh2);
        playersList.add(mpwh3);
        playersList.add(mpwh4);
        playersList.add(mpwh5);
        playersList.add(mpwh6);
        playersList.add(mpTotallyBroken);
        playersList.add(mpLongEating);
    }
    //---------------------------------------------------------------------------------------------

    public void makeMoveBinSound(){
        prepareSounds();
        stopPreviousSounds();
        mpMovingBin.start();
    }
    public void makeGraffitiSound(){
        prepareSounds();
        stopPreviousSounds();
        mpGraffiti.start();
    }

    public void makeLongEatingSound(){
        prepareSounds();
        stopPreviousSounds();
        mpLongEating.start();
    }

    public void makeMovingDoorSound(){
        prepareSounds();
        stopPreviousSounds();
        mpMovingDoor.start();
    }
    public void makeBinPushedSound(){
        prepareSounds();
        stopPreviousSounds();
        mpBinPushed.start();
    }
    public void makeHitBinSound1(){
        prepareSounds();
        stopPreviousSounds();
        mphb1.start();
    }
    public void makeHitBinSound2(){
        prepareSounds();
        stopPreviousSounds();
        mphb2.start();
    }
    public void makeHitBinSound3(){
        prepareSounds();
        stopPreviousSounds();
        mphb3.start();
    }
    public void makeHitBinSound4(){
        prepareSounds();
        stopPreviousSounds();
        mphb4.start();
    }
    public void makeTotalWinSound(){
        prepareSounds();
        stopPreviousSounds();
        mpTotalWin.start();
    }
    public void makeWoodHitSound_1(){
        prepareSounds();
        stopPreviousSounds();
        mpwh1.start();
    }
    public void makeWoodHitSound_2(){
        prepareSounds();
        stopPreviousSounds();
        mpwh2.start();
    }
    public void makeWoodHitSound_3(){
        prepareSounds();
        stopPreviousSounds();
        mpwh3.start();
    }
    public void makeWoodHitSound_4(){
        prepareSounds();
        stopPreviousSounds();
        mpwh4.start();
    }
    public void makeWoodHitSound_5(){
        prepareSounds();
        stopPreviousSounds();
        mpwh5.start();
    }
    public void makeWoodHitSound_6(){
        prepareSounds();
        stopPreviousSounds();
        mpwh6.start();
    }
    public void makeChestBrokenSound(){
        prepareSounds();
        stopPreviousSounds();
        mpTotallyBroken.start();
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