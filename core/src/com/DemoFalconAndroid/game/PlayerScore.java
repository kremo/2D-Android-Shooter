package com.DemoFalconAndroid.game;

/**
 * A Player Score list
 */
public class PlayerScore {

    private static int totalScore = 0;

   public void playerScore(int totalScore){
   this.totalScore = totalScore;
   }
   public static void addPlayerScore(){

       int tempScore= 10;
       totalScore+= tempScore;

   }
   public static void subtractPlayerScore(){
        if(totalScore != 0){
            int tempScore= -5;
            totalScore+= tempScore;
        }
       else totalScore = 0;

   }
   public static int getPlayerScore(){
       return totalScore;
   }
   public static void resetScore(){
    totalScore = 0;
   }
}
