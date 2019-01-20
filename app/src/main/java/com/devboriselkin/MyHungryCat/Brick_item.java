package com.devboriselkin.MyHungryCat;

import android.widget.ImageView;

public class Brick_item {
    public int weight;
    public boolean onPlate;
    public boolean soundWasEmitted =false;
    public boolean soundWasOutted =false;



    public boolean startAction=true;
    public ImageView imageView;



    public int id;
    public Brick_item(ImageView imageView, int weight, boolean onPlate, int id){
        this.imageView=imageView;
        this.weight=weight;
        this.onPlate=onPlate;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isOnPlate() {
        return onPlate;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setOnPlate(boolean onPlate) {
        this.onPlate = onPlate;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setSoundWasEmitted(boolean soundWasEmitted) {
        this.soundWasEmitted = soundWasEmitted;
    }

    public boolean isSoundWasEmitted() {

        return soundWasEmitted;
    }
    public boolean isSoundWasOutted() {
        return soundWasOutted;
    }

    public void setSoundWasOutted(boolean soundWasOutted) {
        this.soundWasOutted = soundWasOutted;
    }
    public void setStartAction(boolean startAction) {
        this.startAction = startAction;
    }

    public boolean isStartAction() {

        return startAction;
    }
}
