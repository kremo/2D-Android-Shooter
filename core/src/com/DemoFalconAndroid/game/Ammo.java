package com.DemoFalconAndroid.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Generic Class for Ammo Objects in the game
 */
public class Ammo implements Pool.Poolable {

    private Vector2 speed;
    private Texture ammoTexture;
    private Sprite ammoSprite;
    private AnimatedSprite animatedAmmo;
    private boolean alive;


    public Ammo(Texture texture) {
        this.ammoTexture = texture;
        this.ammoSprite = new Sprite(ammoTexture);
        this.animatedAmmo = new AnimatedSprite(ammoSprite);
        this.alive = true;

    }
    /*
        Set Texture>Sprite>Animated Sprite of Ammo
     */
    public void setAmmoTexture(Texture texture){

        this.ammoTexture = texture;
        this.ammoSprite = new Sprite(ammoTexture);
        this.animatedAmmo = new AnimatedSprite(ammoSprite);

    }

    public void setSpeed(float x, float y){
        speed.set(x,y);

    }



    @Override
    public void reset() {
    this.alive = false;

    }


}

