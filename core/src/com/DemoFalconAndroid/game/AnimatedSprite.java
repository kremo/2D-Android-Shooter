package com.DemoFalconAndroid.game;

/**
 * Controls Sprite Movement
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimatedSprite {
    private static final int FRAMES_COL = 2;
    private static final int FRAMES_ROW = 2;
    private Sprite sprite;
    private Animation animation;
    private TextureRegion[] frames;
    private TextureRegion currentFrame;
    private Vector2 velocity = new Vector2();
    private static final int Ship_Speed = 300;

    private float stateTime;

    public AnimatedSprite(Sprite sprite){
        this.sprite = sprite;
        Texture texture = sprite.getTexture();
        TextureRegion[][] temp = TextureRegion.split(texture, (int) getSpriteWidth(), texture.getHeight()/ FRAMES_ROW);
        frames = new TextureRegion[FRAMES_COL * FRAMES_ROW];
        int index = 0;
        for(int i = 0; i < FRAMES_ROW; i++){
            for(int j = 0; j < FRAMES_COL; j++)
            {
                frames[index++] = temp[i][j];
            }
        }
        animation = new Animation(0.1f,frames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch spriteBatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime,true);
        spriteBatch.draw(currentFrame, sprite.getX(),sprite.getY());
    }
    public float getSpriteCenterOffSet(){

        return getSpriteWidth()/2;
    }
    public float getSpriteWidth(){
        return sprite.getWidth()/2;

    }
    public void setPostion(float x, float y){
    sprite.setPosition(x-getSpriteCenterOffSet(),y);
    }
    public void moveRight(){
        velocity = new  Vector2(Ship_Speed,0);
    }
    public void moveLeft(){
       velocity = new  Vector2(-Ship_Speed,0);

    }
    public int getX(){
        return (int) (sprite.getX()+getSpriteCenterOffSet());
    }
    public void move(){
        int xMovement = (int) (velocity.x * Gdx.graphics.getDeltaTime());
        int yMovement = (int) (velocity.y * Gdx.graphics.getDeltaTime());
        sprite.setPosition(sprite.getX() + xMovement, sprite.getY() + yMovement);
        if(sprite.getX() < 0){
            sprite.setX(0);
            velocity.x = 0;
        }
        if(sprite.getX()+getSpriteWidth() > Gdx.graphics.getWidth()){
            sprite.setX(Gdx.graphics.getWidth()-sprite.getWidth());
            velocity.x=0;
            /*Using GDX.Grpahics.GetWidth; Sets the Width to the Screen of the Device
            One of the issues I'm running into is simply getting the 800x420 Game to
            Stretch to the Device instead of just becoming massively bigger/Smaller;
            Maybe it's a Camera issue?**/
        }
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
