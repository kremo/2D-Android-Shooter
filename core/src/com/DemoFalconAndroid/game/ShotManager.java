package com.DemoFalconAndroid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Managed Plasma Shots
 */
public class ShotManager {
    private final Texture shotTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
    private  float timeSinceLastShot = 0;
    private  float MIN_TIME_BETWEEN_SHOTS = .5f;


    public ShotManager(Texture shotTexture) {

        this.shotTexture = shotTexture;
    }


    public void firePlayerShot(int shipCenterXLocation) {

       if(canFireShot()){
           Sprite newShot = new Sprite(shotTexture);
           AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
           newShotAnimated.setPostion(shipCenterXLocation, 90);
           newShotAnimated.setVelocity(new Vector2(0, 300));
           shots.add(newShotAnimated);
           timeSinceLastShot = 0f;
       }
    }

    private boolean canFireShot() {

        return timeSinceLastShot > MIN_TIME_BETWEEN_SHOTS;
        //Allows Ship to Fire; IF false no ammo is seen/shot
    }

    public void update() {
        //TODO: What is an Iterartor call and using it properly;
        Iterator<AnimatedSprite> i =  shots.iterator();

        while(i.hasNext()){
            AnimatedSprite shot = i.next();
            shot.move();
            if(shot.getY() > Gdx.graphics.getHeight()){
            i.remove();
            }
        }
        timeSinceLastShot += Gdx.graphics.getDeltaTime();

    }

    public void draw(SpriteBatch batch) {
        for(AnimatedSprite shot: shots){
            shot.draw(batch);
        }
    }
}
