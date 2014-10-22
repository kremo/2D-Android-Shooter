package com.DemoFalconAndroid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Managed Plasma Shots
 */
public class ShotManager {
    private final Texture shotTexture;
    private final Texture enemyShotTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
    private List<AnimatedSprite> EnemyShots = new ArrayList<AnimatedSprite>();
    private List<AnimatedSprite> playerShots = new ArrayList<AnimatedSprite>();

    private  float timeSinceLastShot = 0;
    private  float MIN_TIME_BETWEEN_SHOTS = .5f;


    public ShotManager(Texture shotTexture, Texture enemyShotTexture) {

        this.shotTexture = shotTexture;
        this.enemyShotTexture = enemyShotTexture;
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


        //Player Ammo shot is cleared
        Iterator<AnimatedSprite> i =  shots.iterator();
        while(i.hasNext()){
            AnimatedSprite shot = i.next();
            shot.move();
            if(shot.getY() > Gdx.graphics.getHeight()){

            i.remove();
            }

        }
        timeSinceLastShot += Gdx.graphics.getDeltaTime();


        Iterator<AnimatedSprite> e =  EnemyShots.iterator();

        while(e.hasNext()){
            AnimatedSprite shot = e.next();
            shot.move();
            if(shot.getY() <= -1){
                e.remove();
            }

        }
        timeSinceLastShot += Gdx.graphics.getDeltaTime();




    }

    public void draw(SpriteBatch batch) {
        for(AnimatedSprite shot: shots){
            shot.draw(batch);
        }

        for(AnimatedSprite shot: EnemyShots){
            shot.draw(batch);
        }
    }

    public void fireEnemyShot(int enemyCenterXLocation) {
        Sprite enemyFire = new Sprite(enemyShotTexture);
        AnimatedSprite newShotAnimated = new AnimatedSprite(enemyFire);
        newShotAnimated.setPostion(enemyCenterXLocation, Gdx.graphics.getHeight()-enemyFire.getHeight()/2);
        newShotAnimated.setVelocity(new Vector2(0, -300));
        EnemyShots.add(newShotAnimated);


    }

    public boolean playerShotTouches(Rectangle boundingBox) {
        Iterator<AnimatedSprite> i = shots.iterator();
        while (i.hasNext()){
            AnimatedSprite shot = i.next();
            if(Intersector.overlaps(shot.getBoundingBox(),boundingBox)){
               i.remove();
                return true;
            }
        }
        return false;
    }

    public boolean enemyShotTouches(Rectangle boundingBox) {
        return shotTouches(EnemyShots,boundingBox);
    }

    private boolean shotTouches(List<AnimatedSprite> enemyShots, Rectangle boundingBox) {
        Iterator<AnimatedSprite> i = enemyShots.iterator();
        while (i.hasNext()){
            AnimatedSprite shot = i.next();
            if(Intersector.overlaps(shot.getBoundingBox(),boundingBox)){
                i.remove();
                return true;
            }
        }
        return false;
    }

}
