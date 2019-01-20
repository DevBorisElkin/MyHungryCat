package com.devboriselkin.MyHungryCat.Media;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {
    int backgroundMusic;
    int inGameMusic;
    int inGameForestMusic;
    Context context;
    MediaPlayer mpBackgroundMusic;
    MediaPlayer mpInGameMusic;
    MediaPlayer mpForestMusic;
    int backgroundMusicVolume=70;
    int inGameMusicVolume=50;
    int MAX_VOLUME=100;

    public MusicPlayer(Context context, int backgroundMusic, int inGameMusic, int inGameForestMusic){
        this.backgroundMusic= backgroundMusic;
        this.context=context;
        this.inGameMusic=inGameMusic;
        this.inGameForestMusic=inGameForestMusic;
    }

    public void initMusicInMenu(){
        mpBackgroundMusic = MediaPlayer.create(context, backgroundMusic);
        backgroundMusicSetVolume();
    }
    public void initForestMusic(){
        mpForestMusic=MediaPlayer.create(context, inGameForestMusic);
    }
    public void startForestMusic(){
        mpForestMusic.start();
        mpForestMusic.setLooping(true);
    }
    public void stopForestMusic(){
        mpForestMusic.pause();
    }
    public void resumeForestMusic(){
        if(mpForestMusic!=null){
            mpForestMusic.start();
        }

    }
    public void initMusicInGame(){
        mpInGameMusic=MediaPlayer.create(context, inGameMusic);
        //inGameMusicSetVolume();
    }
    public void releaseMusicInMenu(){
        if(mpBackgroundMusic!=null)mpBackgroundMusic.release();
    }
    //---------------------------------------------------------------------------------------------
    public void startInGameMusic(){
        mpInGameMusic.start();
        mpInGameMusic.setLooping(true);
    }
    public void stopInGameMusic(){
        mpInGameMusic.pause();
    }
    public void resumeInGameMusic() {
        mpInGameMusic.start();
    }
    //---------------------------------------------------------------------------------------------

        public void startBackgroundMusic(){
            mpBackgroundMusic.start();
            mpBackgroundMusic.setLooping(true);
        }
    public void stopBackgroundMusic(){
        mpBackgroundMusic.pause();
    }
    public void resumeBackgroundMusic(){
        mpBackgroundMusic.start();
    }

    public void backgroundMusicSetVolume(){
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - backgroundMusicVolume) / Math.log(MAX_VOLUME)));
        mpBackgroundMusic.setVolume(volume, volume);
    }
    public void inGameMusicSetVolume(){
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - inGameMusicVolume) / Math.log(MAX_VOLUME)));
        mpInGameMusic.setVolume(volume, volume);
    }
    public void release(){
        if(mpInGameMusic!=null)mpInGameMusic.release();
        if(mpBackgroundMusic!=null)mpBackgroundMusic.release();
        if(mpForestMusic!=null)mpForestMusic.release();
    }



}
