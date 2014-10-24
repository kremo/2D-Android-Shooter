package com.DemoFalconAndroid.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;




public class DemoGame extends Game{
    private static final int VIRTUAL_WIDTH = 420;
    private static final int VIRTUAL_HEIGHT = 800;
    SpriteBatch batch;
    private Texture background;
    private Sprite spaceship;
    private AnimatedSprite movingShip;
    private Viewport viewport;
    private Camera camera;
    private ShotManager shotManager;
    private Music gameMusic;
    private Enemy enemy;
    private Sprite EnemyShip;
    private CollisionManager collisionManager;
    private boolean isGameOver = false;
    public int playerScore;
    private PlayerScore totalScore;
    private Sprite newBackground;
    private Player mainPlayer;
    private static final String gameName = "Jay Shooter";

    @Override
    public void create() {

        setScreen(new Splash());


        camera = new PerspectiveCamera();
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        // OrthographicCamera camera = new OrthographicCamera(); //Having to Declare Camera type before new.
        // camera.setToOrtho(false,800,480); Default Camera settings + Size
        batch = new SpriteBatch();


        background = new Texture(Gdx.files.internal("darkPurple.png"));
        newBackground = new Sprite(background);
        newBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        newBackground.setSize(1f,
                1f * newBackground.getHeight() / newBackground.getWidth());

        Ammo playerAmmo = new Ammo(background);


        Texture SpaceShipTexture = new Texture(Gdx.files.internal("rocketsprite.png"));
        spaceship = new Sprite(SpaceShipTexture);  //TEXTURE > SPRITE
        spaceship.setPosition(800 / 2 - (spaceship.getWidth() / 2), 0);
        movingShip = new AnimatedSprite(spaceship);


        Texture ammoTexture = new Texture(Gdx.files.internal("SpaceAmmo.png"));
        Texture EnemyShotTexture = new Texture(Gdx.files.internal("Enemy_Shooting_Sprite.png"));
        shotManager = new ShotManager(ammoTexture,EnemyShotTexture);

        Texture enemyTexture = new Texture(Gdx.files.internal("enemy_sprite.png"));
        enemy = new Enemy(enemyTexture, shotManager);


        collisionManager = new CollisionManager(movingShip, enemy, shotManager);

        /*
       Music Manager, Files, Volumes
             */

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Battle.mp3"));
        gameMusic.setVolume(.25f); //Figure out what the F Means
        gameMusic.setLooping(true);
        gameMusic.play();

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
            newBackground.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
            batch.draw(newBackground,0,0);
        //TODO FIGURE OUT HOW TO DRAW BACKGROUND ACROSS ENTIRE SCREEN, CURRENT IDEA: LOOP IT


        BitmapFont scoreFont = new BitmapFont();
        scoreFont.setScale(2);
        scoreFont.draw(batch,"SCORE: "+PlayerScore.getPlayerScore(),0,Gdx.graphics.getHeight()-40);


        if(isGameOver){
            BitmapFont font = new BitmapFont();
            font.setScale(2);
            font.draw(batch,"YOU GOT HIT",Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/2);
        }

       // spaceship.draw(batch);

        movingShip.draw(batch);
        shotManager.draw(batch);
        enemy.draw(batch);
        batch.end();

        handleinput();

        if(!isGameOver)
        {
            movingShip.move();
            shotManager.update();
            enemy.update();
            collisionManager.handleCollisions();
        }


        if(movingShip.isDead()){
            isGameOver = true;
        }

    //5269
    }

    private void handleinput() {
        if (Gdx.input.isTouched()) {
            if(isGameOver){
                dispose();
                movingShip.setDead(false);
                isGameOver = false;
            }
            int touchX = Gdx.input.getX();

            if (touchX > movingShip.getX()) {
                movingShip.moveRight();

            } else {
                movingShip.moveLeft();

            }
            shotManager.firePlayerShot(movingShip.getX());
            shotManager.update();
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
    public static int addPlayerScore(){
        int totalScore =0;
        totalScore=+ 100;
        return totalScore;
    }

}
