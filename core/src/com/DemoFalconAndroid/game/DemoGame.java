package com.DemoFalconAndroid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class DemoGame implements ApplicationListener {
	SpriteBatch batch;
    private Texture background;
    private Sprite spaceship;
    AnimatedSprite movingShip;
    private Viewport viewport;
    private Camera camera;
    private static final int VIRTUAL_WIDTH = 420;
    private static final int VIRTUAL_HEIGHT = 800;

	@Override
	public void create () {

        camera = new PerspectiveCamera();
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
       // OrthographicCamera camera = new OrthographicCamera(); //Having to Declare Camera type before new.
       // camera.setToOrtho(false,800,480);
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("Background.png"));
        Texture SpaceShipTexture = new Texture(Gdx.files.internal("rocketship.png"));
        spaceship = new Sprite(SpaceShipTexture);  //TEXTURE > SPRITE
        spaceship.setPosition(800/2 - (spaceship.getWidth()/2),0);
        this.movingShip = new AnimatedSprite(spaceship);

	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);

    }

    @Override
    public void render() {
      // Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
       batch.draw(background,0,0);

        //spaceship.draw(batch);
        movingShip.draw(batch);
        handleinput();
        movingShip.move();


        batch.end();
    }

    private void handleinput() {
        if (Gdx.input.isTouched()) {
            int touchX = Gdx.input.getX();
            System.out.println(touchX);
            if( touchX > movingShip.getX()){
               movingShip.moveRight();
            }
            else
            {
                movingShip.moveLeft();
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
