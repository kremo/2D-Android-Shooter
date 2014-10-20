package com.DemoFalconAndroid.game;

/**
 * Created by Adam on 10/19/2014.
 */
public class CollisionManager {


    private AnimatedSprite movingShip;
    private Enemy enemy;
    private ShotManager shotManager;

    public CollisionManager(AnimatedSprite movingShip, Enemy enemy, ShotManager shotManager) {

        this.movingShip = movingShip;
        this.enemy = enemy;
        this.shotManager = shotManager;
    }

    public void handleCollisions() {
        handleEnemyShot();
        handlePlayerShot();
    }

    private void handlePlayerShot() {
        if(shotManager.enemyShotTouches(movingShip.getBoundingBox())){
            movingShip.setDead(true);
            PlayerScore.subtractPlayerScore();
        }
    }

    private void handleEnemyShot() {
        if(shotManager.playerShotTouches(enemy.getBoundBox()))
            enemy.hit();

    }
}
