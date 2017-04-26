package com.falcotech.mazz.bigtwochampionship.models;

/**
 * Created by phima on 4/24/2017.
 */

public class Turn {
    private int turnNum;
    private int playerNum;
    private Hand play;

    public Turn() {
    }

    public Turn(int turnNum, int playerNum, Hand play) {
        this.turnNum = turnNum;
        this.playerNum = playerNum;
        this.play = play;
    }

    @Override
    protected void finalize() throws Throwable {
        if(play != null){
            play.finalize();
            play = null;
        }
        super.finalize();
    }

    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public Hand getPlay() {
        return play;
    }

    public void setPlay(Hand play) {
        this.play = play;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "\nturnNum=" + turnNum +
                "\n playerNum=" + playerNum +
                "\n play=" + play +
                '}';
    }
}
