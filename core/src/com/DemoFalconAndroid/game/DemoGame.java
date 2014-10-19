package com.DemoFalconAndroid.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class DemoGame implements ApplicationListener {
    private static final int VIRTUAL_WIDTH = 420;
    private static final int VIRTUAL_HEIGHT = 800;
    SpriteBatch batch;
    private Texture background;
    private Sprite spaceship;
    private AnimatedSprite movingShip;
    private Viewport viewport;
    private Camera camera;
    private ShotManager shotManager;

    @Override
    public void create() {

        camera = new PerspectiveCamera();
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        // OrthographicCamera camera = new OrthographicCamera(); //Having to Declare Camera type before new.
        // camera.setToOrtho(false,800,480); Default Camera settings
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("Background.png"));
        Texture SpaceShipTexture = new Texture(Gdx.files.internal("rocketship.png"));
        spaceship = new Sprite(SpaceShipTexture);  //TEXTURE > SPRITE
        spaceship.setPosition(800 / 2 - (spaceship.getWidth() / 2), 0);
        movingShip = new AnimatedSprite(spaceship);


        Texture ammoTexture = new Texture(Gdx.files.internal("SpaceAmmo.png"));
        shotManager = new ShotManager(ammoTexture);


     /*
     Disabled Ammo Sprite
        Texture AmmoSprite = new Texture(Gdx.files.internal("SpaceAmmo.png"));
        Ammo = new Sprite(AmmoSprite);
        Ammo.setPosition(800/2-(Ammo.getWidth()/2),0);
        SpaceAmmo = new AnimatedSprite(Ammo);
      */

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
        batch.draw(background, 0, 0);

        spaceship.draw(batch);
        movingShip.draw(batch);
        shotManager.draw(batch);
        batch.end();

        handleinput();

        movingShip.move();
        shotManager.update();


    }

    private void handleinput() {
        if (Gdx.input.isTouched()) {
            int touchX = Gdx.input.getX();
            System.out.println(touchX);
            if (touchX > movingShip.getX()) {
                movingShip.moveRight();

            } else {
                movingShip.moveLeft();

            }
            shotManager.firePlayerShot(movingShip.getX());
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
