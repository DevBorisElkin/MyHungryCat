package com.devboriselkin.MyHungryCat.Media;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundPlayer {
    int meow1, meow2, meow3, meow4, victory, failure, poisoning, eating1, eating2, eating3,
            eating4, catBeforeDeath, catSuperAngry, catFeelingBad, catLoud, purring, cashRegister,
            graffiti, movingBin, movingDoor, binPushed, hb1, hb2, hb3, hb4, totalWin, wh1, wh2_dwarf, wh3,
            wh4, wh5, wh6, totally_broken, long_eating, mole1, mole2, coinDrop, itemSold,
            mouse, explosion, chestOpening, chestLocked, ropeBurning, ropeBreaking, hittingWall, hitting_chest, brick, squirrel, rabbit,  fox,  owl, wc1, wc2, wc3, tree_fall, bush_cut, cashier_opening, bark;
    List<MediaPlayer> playersList = null;
    Random random = new Random();
    int rand;


    Context context;

    MediaPlayer mpCatMeow1, mpCatMeow2, mpCatMeow3, mpCatMeow4, mpVictory, mpFailure, mpPoisoning, mpCatEating1, mpCatEating2, mpCatEating3, mpCatEating4, mpCatBeforeDeath, mpCatSuperAngry,
            mpCatFeelingBad, mpCatLoud, mpPurring, mpCashRegister, mpGraffiti, mpMovingBin, mpMovingDoor, mpBinPushed, mphb1, mphb2, mphb3, mphb4, mpTotalWin,
            mpwh1, mpwh2, mpwh3, mpwh4, mpwh5, mpwh6, mpTotallyBroken, mpLongEating, mpMoleSound1, mpMoleSound2,
            mpCoinDrop, mpItemSold, mpMouse, mpExplosion, mpChestOpening, mpChestLocked, mpRopeBurning, mpRopeBreaking, mpHittingWall, mpHittingChest, mpBrick;
    MediaPlayer mainPlayer;


    public SoundPlayer(Context context, int meow1, int meow2, int meow3, int meow4, int victory, int failure, int poisoning, int eating1, int eating2, int eating3, int eating4,
                       int catBeforeDeath, int catSuperAngry, int catFeelingBad, int catLoud, int purring, int cashRegister, int graffiti, int movingBin, int movingDoor,
                       int binPushed, int hb1, int hb2, int hb3, int hb4, int totalWin, int wh1,
                       int wh2_dwarf, int wh3, int wh4, int wh5, int wh6, int totally_broken,
                       int long_eating, int mole1, int mole2, int coinDrop, int itemSold, int mouse, int explosion, int chestOpening, int chestLocked, int ropeBurning, int ropeBreaking,
                       int hittingWall, int hitting_chest, int brick, int squirrel, int rabbit, int fox, int owl, int wc1, int wc2, int wc3, int tree_fall, int bush_cut, int cashier_opening, int bark) {
        this.context = context;
        playersList=new ArrayList<>();
        this.meow1 = meow1;
        this.meow2 = meow2;
        this.meow3 = meow3;
        this.meow4 = meow4;
        this.victory = victory;
        this.failure = failure;
        this.poisoning = poisoning;
        this.eating1 = eating1;
        this.eating2 = eating2;
        this.eating3 = eating3;
        this.eating4 = eating4;
        this.catBeforeDeath = catBeforeDeath;
        this.catSuperAngry = catSuperAngry;
        this.catFeelingBad = catFeelingBad;
        this.catLoud = catLoud;
        this.purring = purring;
        this.cashRegister = cashRegister;
        this.graffiti = graffiti;
        this.movingBin = movingBin;
        this.movingDoor = movingDoor;
        this.binPushed = binPushed;
        this.hb1 = hb1;
        this.hb2 = hb2;
        this.hb3 = hb3;
        this.hb4 = hb4;
        this.totalWin = totalWin;
        this.wh1 = wh1;
        this.wh2_dwarf = wh2_dwarf;
        this.wh3 = wh3;
        this.wh4 = wh4;
        this.wh5 = wh5;
        this.wh6 = wh6;
        this.totally_broken = totally_broken;
        this.long_eating = long_eating;
        this.mole1 = mole1;
        this.mole2 = mole2;
        this.coinDrop = coinDrop;
        this.itemSold = itemSold;
        this.mouse = mouse;
        this.explosion = explosion;
        this.chestOpening = chestOpening;
        this.chestLocked = chestLocked;
        this.ropeBurning = ropeBurning;
        this.ropeBreaking = ropeBreaking;
        this.hittingWall = hittingWall;
        this.hitting_chest = hitting_chest;
        this.brick = brick;
        this.squirrel=squirrel;
        this.rabbit=rabbit;
        this.fox=fox;
        this.owl=owl;
        this.wc1=wc1;
        this.wc2=wc2;
        this.wc3=wc3;
        this.tree_fall=tree_fall;
        this.bush_cut=bush_cut;
        this.cashier_opening=cashier_opening;
        this.bark=bark;

        mpRopeBurning=MediaPlayer.create(context, ropeBurning);
        mainPlayer=MediaPlayer.create(context, meow1);
        playersList.add(mainPlayer);
        playersList.add(mpRopeBurning);


    }
    //---------------------------------------------------------------------------------------------


    //__________________________________________________________________________________________
    public void makeMeowSound1() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, meow1);
        mainPlayer.start();
    }

    public void makeMeowSound2() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, meow2);
        mainPlayer.start();
    }

    public void makeMeowSound3() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, meow3);
        mainPlayer.start();
    }

    public void makeMeowSound4() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, meow4);
        mainPlayer.start();
    }

    public void makeVictorySound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, victory);
        mainPlayer.start();
    }

    public void makeFailureSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context,failure);
        mainPlayer.start();
    }

    public void makePoisoningSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, poisoning);
        mainPlayer.start();
    }

    public void makeCatEaingSound1() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, eating1);
        mainPlayer.start();
    }

    public void makeCatEaingSound2() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, eating2);
        mainPlayer.start();
    }

    public void makeCatEaingSound3() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, eating3);
        mainPlayer.start();
    }

    public void makeCatEaingSound4() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, eating4);
        mainPlayer.start();
    }

    public void makeCatBeforeDeathSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, catBeforeDeath);
        mainPlayer.start();
    }

    public void makeCatSuperAngrySound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, catSuperAngry);
        mainPlayer.start();
    }

    public void makeCatFeelingBadSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, catFeelingBad);
        mainPlayer.start();
    }

    public void makeCatLoudSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, catLoud);
        mainPlayer.start();
    }

    public void makePurringSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, purring);
        mainPlayer.start();
    }

    public void makeMoveBinSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, movingBin);
        mainPlayer.start();
    }

    public void makeGraffitiSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, graffiti);
        mainPlayer.start();
    }

    public void makeCashRegisterSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, cashRegister);
        mainPlayer.start();
    }

    public void makeLongEatingSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, long_eating);
        mainPlayer.start();
    }

    public void makeMovingDoorSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, movingDoor);
        mainPlayer.start();
    }

    public void makeBinPushedSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, binPushed);
        mainPlayer.start();
    }

    public void makeHitBinSound1() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, hb1);
        mainPlayer.start();
    }

    public void makeHitBinSound2() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, hb2);
        mainPlayer.start();
    }

    public void makeHitBinSound3() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, hb3);
        mainPlayer.start();
    }

    public void makeHitBinSound4() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, hb4);
        mainPlayer.start();
    }

    public void makeTotalWinSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, totalWin);
        mainPlayer.start();
    }

    public void makeWoodHitSound_1() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wh1);
        mainPlayer.start();
    }

    public void makeWoodHitSound_2() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wh2_dwarf);
        mainPlayer.start();
    }

    public void makeWoodHitSound_3() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wh3);
        mainPlayer.start();
    }

    public void makeWoodHitSound_4() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wh4);
        mainPlayer.start();
    }

    public void makeWoodHitSound_5() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wh5);
        mainPlayer.start();
    }

    public void makeWoodHitSound_6() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wh6);
        mainPlayer.start();
    }

    public void makeChestBrokenSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, totally_broken);
        mainPlayer.start();
    }

    public void makeMoleSound1() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, mole1);
        mainPlayer.start();
    }

    public void makeMoleSound2() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, mole2);
        mainPlayer.start();
    }

    public void makeCoinDropSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, coinDrop);
        mainPlayer.start();
    }

    public void makeItemSoldSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, itemSold);
        mainPlayer.start();
    }

    public void makeMouseSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, mouse);
        mainPlayer.start();
    }

    public void makeExplosionSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, explosion);
        mainPlayer.start();
    }

    public void makeChestOpeningSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, chestOpening);
        mainPlayer.start();
    }

    public void makeChestLockedSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, chestLocked);
        mainPlayer.start();
    }

    public void makeRopeBurningSound() {
//        stopPreviousSounds();
//        mainPlayer=MediaPlayer.create(context, ropeBurning);
//        mainPlayer.start();
        stopPreviousSounds();
        if(mpRopeBurning!=null){
            mpRopeBurning.release();
            mpRopeBurning=null;
            mpRopeBurning=MediaPlayer.create(context, ropeBurning);
            mpRopeBurning.start();
        }

    }

    public void stopRopeBurningSound() {
        if(mpRopeBurning!=null){
            mpRopeBurning.stop();
            mpRopeBurning.release();
        }


    }

    public void makeRopeBreakingSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, ropeBreaking);
        mainPlayer.start();
    }

    public void makeHittingWallSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, hittingWall);
        mainPlayer.start();

    }

    public void makeHittingChestSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, hitting_chest);
        mainPlayer.start();
    }

    public void makeBrickSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, brick);
        mainPlayer.start();
    }
    public void makeSquirrelSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, squirrel);
        mainPlayer.start();
    }
    public void makeRabbitSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, rabbit);
        mainPlayer.start();
    }
    public void makeFoxSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, fox);
        mainPlayer.start();
    }
    public void makeOwlSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, owl);
        mainPlayer.start();
    }
    public void makeWoodCutSound1() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wc1);
        mainPlayer.start();
    }
    public void makeWoodCutSound2() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wc2);
        mainPlayer.start();
    }
    public void makeWoodCutSound3() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, wc3);
        mainPlayer.start();
    }
    public void makeTreeFallSound() {
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, tree_fall);
        mainPlayer.start();
    }
    public void makeBushCutSound(){
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, bush_cut);
        mainPlayer.start();
    }
    public void makeCashierOpeningSound(){
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, cashier_opening);
        mainPlayer.start();
    }
    public void makeBarkSound(){
        stopPreviousSounds();
        mainPlayer=MediaPlayer.create(context, bark);
        mainPlayer.start();
    }
    public void makeRandomSound(){
        rand=random.nextInt(59);
        switch (rand){
            case 1:
                 makeBarkSound();
                break;
            case 2:
                makeCashierOpeningSound();
                break;
            case 3:
                makeBushCutSound();
                break;
            case 4:
                makeTreeFallSound();
                break;
            case 5:
                makeWoodCutSound3();
                break;
            case 6:
                makeWoodCutSound2();
                break;
            case 7:
                makeWoodCutSound1();
                break;
            case 8:
                makeOwlSound();
                break;
            case 9:
                makeFoxSound();
                break;
            case 10:
                makeRabbitSound();
                break;
            case 11:
                makeSquirrelSound();
                break;
            case 12:
                makeBrickSound();
                break;
            case 13:
                makeHittingChestSound();
                break;
            case 14:
                makeHittingWallSound();
                break;
            case 15:
                makeRopeBreakingSound();
                break;
            case 16:
                makeRopeBurningSound();
                break;
            case 17:
                makeChestLockedSound();
                break;
            case 18:
                makeChestOpeningSound();
                break;
            case 19:
                makeExplosionSound();
                break;
            case 20:
                makeMouseSound();
                break;
            case 21:
                makeItemSoldSound();
                break;
            case 22:
                makeCoinDropSound();
                break;
            case 23:
                makeMoleSound2();
                break;
            case 24:
                makeMoleSound1();
                break;
            case 25:
                makeChestBrokenSound();
                break;
            case 26:
                makeWoodHitSound_6();
                break;
            case 27:
                makeWoodHitSound_5();
                break;
            case 28:
                makeWoodHitSound_4();
                break;
            case 29:
                makeWoodHitSound_3();
                break;
            case 30:
                makeWoodHitSound_2();
                break;
            case 31:
                makeWoodHitSound_1();
                break;
            case 32:
                makeTotalWinSound();
                break;
            case 33:
                makeHitBinSound4();
                break;
            case 34:
                makeHitBinSound3();
                break;
            case 35:
                makeHitBinSound2();
                break;
            case 36:
                makeHitBinSound1();
                break;
            case 37:
                makeBinPushedSound();
                break;
            case 38:
                makeMovingDoorSound();
                break;
            case 39:
                makeLongEatingSound();
                break;
            case 40:
                makeCashRegisterSound();
                break;
            case 41:
                makeGraffitiSound();
                break;
            case 42:
                makeMoveBinSound();
                break;
            case 43:
                makePurringSound();
                break;
            case 44:
                makeCatLoudSound();
                break;
            case 45:
                makeCatFeelingBadSound();
                break;
            case 46:
                makeCatSuperAngrySound();
                break;
            case 47:
                makeCatBeforeDeathSound();
                break;
            case 48:
                makeCatEaingSound4();
                break;
            case 49:
                makeCatEaingSound3();
                break;
            case 50:
                makeCatEaingSound2();
                break;
            case 51:
                makeCatEaingSound1();
                break;
            case 52:
                makePoisoningSound();
                break;
            case 53:
                makeFailureSound();
                break;
            case 54:
                makeVictorySound();
                break;
            case 55:
                makeMeowSound4();
                break;
            case 56:
                makeMeowSound3();
                break;
            case 57:
                makeMeowSound2();
                break;
            case 58:
                makeMeowSound1();
                break;
            default:
                makeHitBinSound1();
                break;
        }
    }

    //__________________________________________________________________________________________


    public void release() {
        for (int i = 0; i < playersList.size(); i++) {
            playersList.get(i).release();
//            playersList.get(i).reset();
//            playersList.get(i).release();

        }
    }

    public void stopPreviousSounds() {
        if(mainPlayer!=null){
            if(mainPlayer.isPlaying()){
                mainPlayer.pause();
                mainPlayer.seekTo(0);
                //mainPlayer.reset();
            }
            mainPlayer.release();
            mainPlayer=null;
        }
    }

    //    public void stopPreviousSounds(){
//        for(int i = 0; i<playersList.size();i++){
//            if(playersList.get(i).isPlaying()){
//                if(playersList.get(i)!=mpRopeBurning){
//                    playersList.get(i).pause();
//                    playersList.get(i).seekTo(0);
//                }
//            }
//        }
//    }
    public void prepareSounds() {
        for (int i = 0; i < playersList.size(); i++) {
            playersList.get(i).release();
        }
    }
}



