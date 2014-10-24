package com.DemoFalconAndroid.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Like Ammo.java, Ship.java takes in a Texture and Animates  and sets an Ammo Texture its. That's it. Nothing more.
 */
public class Ship {

    private Texture shipTexture;
    private Sprite shipSprite;
    private AnimatedSprite shipAnimated;
    private Ammo shipAmmo;


    public Ship(Texture shipTexture, Ammo shipAmmo){
        this.shipTexture = shipTexture;
        this.shipAmmo = shipAmmo;


    }

    public void setShipAmmo(Ammo shipAmmo){

        this.shipAmmo = shipAmmo;
    }

    public void setPostion(float x, float y){
        this.shipAnimated.setPostion(x,y);
    }
    public void setVelocity(Vector2 velocity){
        shipAnimated.setVelocity(velocity);
    }



}
