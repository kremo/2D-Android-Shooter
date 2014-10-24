package com.DemoFalconAndroid.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * I am going to make it easier to create Player Ship Objects and Enemey Object and to avoid code mess
 *
 * In DemoGame.Java, You'll create ONE Player Object and at least one Enemy Object (But more can be made)
 *
 * Player Object takes A Ship and an Ammo object as parameters.
 *
 * I won't make Ship and Ammo complicated, except that it's a quicker way to designate a Specific Sprite for
 * both the Enemy and Players' Ship and Ammo sprite obviously.
 *
 *
 */
public class Player {

    private Ammo playerAmmo;
    private Ship playerShip;
    private boolean alive;
    private int playerLives;
    private int playerHealth;



    public Player(Ship playerShip, Ammo playerAmmo){

        this.playerShip = playerShip;
        this.playerAmmo = playerAmmo;
        this.alive = true;
        playerLives = 3;
        this.playerHealth = 100;
    }

    public void spawn(){


    }



}
