package com.DemoFalconAndroid.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 *File for Enemy NPC
 *
 */
public class Enemy {

    private static final float ENEMY_SPEED = 250;
    private final Texture enemyTexture;
    private AnimatedSprite movingEnemy;
    private ShotManager shotManager;
    private float spawnTimeOut = 0f;
    public PlayerScore tempScore;


    public Enemy(Texture enemyTexture, ShotManager shotManager) {

    this.enemyTexture = enemyTexture;
    this.shotManager = shotManager;
    spawn();

    }

    private void spawn() {
        Sprite enemySprite = new Sprite(enemyTexture);
        this.movingEnemy = new AnimatedSprite(enemySprite);
        
        int xPostion = createRandomPostion();
        movingEnemy.setPostion(xPostion,Gdx.graphics.getHeight()-movingEnemy.getHeight());
        movingEnemy.setVelocity(new Vector2(ENEMY_SPEED,0));
        movingEnemy.setDead(false);

    }

    private int createRandomPostion() {
        Random random = new Random();
        int randomNumber = random.nextInt(Gdx.graphics.getWidth() - movingEnemy.getWidth()+1 );
        return randomNumber + movingEnemy.getWidth()/2;

    }

    public void draw(SpriteBatch batch) {
        if(!movingEnemy.isDead())
        movingEnemy.draw(batch);
    }

    public void update() {
        if(movingEnemy.isDead()){
            spawnTimeOut -= Gdx.graphics.getDeltaTime();
            if(spawnTimeOut <= 0){
                spawn();
            }
        }
        else {
            if (shouldChangeDirection())
                movingEnemy.changeDirection();

            if (shouldFire()) {
                shotManager.fireEnemyShot(movingEnemy.getX());
            }

            movingEnemy.move();
        }
    }

    private boolean shouldFire() {
        Random random = new Random();
        return random.nextInt(45)== 0;
    }

    public boolean shouldChangeDirection(){

        Random random = new Random();
        return random.nextInt(21)== 0;
        //Lower Number to have AI move more frequently

    }

    public Rectangle getBoundBox(){
        return movingEnemy.getBoundingBox(); //>
    }

    public void hit() {
        movingEnemy.setDead(true);

        PlayerScore.addPlayerScore();
        spawnTimeOut = 2f;
    }
    public int playerScore(){
        return tempScore.getPlayerScore();
    }

}
