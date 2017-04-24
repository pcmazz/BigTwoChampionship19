package com.falcotech.mazz.bigtwochampionship;

import com.google.firebase.database.Exclude;

import java.util.List;

/**
 * Created by phima on 4/24/2017.
 */

public class Game{
    private int id;
    private String createdAt;
    private String lastUpdated;
    private boolean gameLive;
    private boolean roundLive;
    private boolean excluded;
    private int turnPlayerNum;
    private int startPlayerNum;
    private int roundNum;
    private List<Turn> turns;
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;

    public Game() {
    }

    public Game(int id, String createdAt, String lastUpdated, boolean gameLive, boolean roundLive, boolean excluded, int turnPlayerNum, int startPlayerNum, int roundNum, List<Turn> turns, Player playerOne, Player playerTwo, Player playerThree, Player playerFour) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.gameLive = gameLive;
        this.roundLive = roundLive;
        this.excluded = excluded;
        this.turnPlayerNum = turnPlayerNum;
        this.startPlayerNum = startPlayerNum;
        this.roundNum = roundNum;
        this.turns = turns;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
        this.playerFour = playerFour;
    }

    @Exclude
    public void setPlayer(Player player){
        int playerNum = player.getNumber();
        switch(playerNum){
            case 1:
                setPlayerOne(player);
                break;
            case 2:
                setPlayerTwo(player);
                break;
            case 3:
                setPlayerThree(player);
                break;
            case 4:
                setPlayerFour(player);
                break;
        }
    }

    @Exclude
    public Player getPlayer(int playerNum){
        switch(playerNum){
            case 1:
                return playerOne;
            case 2:
                return playerTwo;
            case 3:
                return playerThree;
            default:
                return playerFour;
        }
    }

    @Exclude
    public int turnNumber(){
        if(turns != null && turns.size() > 0){
            return turns.size();
        }
        return 0;
    }

    @Exclude
    public Turn lastTurn(){
        if(turnNumber() > 0){
            return turns.get(turnNumber() - 1);
        }
        return null;
    }

    @Exclude
    public Hand winningHand(){
        if(turnNumber() > 0){
            for(int i = turnNumber() - 1; i >= 0; i--){
                Turn turn = turns.get(i);
                if(turn.getPlay() != null){
                    return turn.getPlay();
                }
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isGameLive() {
        return gameLive;
    }

    public void setGameLive(boolean gameLive) {
        this.gameLive = gameLive;
    }

    public boolean isRoundLive() {
        return roundLive;
    }

    public void setRoundLive(boolean roundLive) {
        this.roundLive = roundLive;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public int getTurnPlayerNum() {
        return turnPlayerNum;
    }

    public void setTurnPlayerNum(int turnPlayerNum) {
        this.turnPlayerNum = turnPlayerNum;
    }

    public int getStartPlayerNum() {
        return startPlayerNum;
    }

    public void setStartPlayerNum(int startPlayerNum) {
        this.startPlayerNum = startPlayerNum;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getPlayerThree() {
        return playerThree;
    }

    public void setPlayerThree(Player playerThree) {
        this.playerThree = playerThree;
    }

    public Player getPlayerFour() {
        return playerFour;
    }

    public void setPlayerFour(Player playerFour) {
        this.playerFour = playerFour;
    }

    @Override
    public String toString() {
        return "Game{" +
                "\nid=" + id +
                "\n createdAt='" + createdAt + '\'' +
                "\n lastUpdated='" + lastUpdated + '\'' +
                "\n gameLive=" + gameLive +
                "\n roundLive=" + roundLive +
                "\n excluded=" + excluded +
                "\n turnPlayerNum=" + turnPlayerNum +
                "\n startPlayerNum=" + startPlayerNum +
                "\n roundNum=" + roundNum +
                "\n turns=" + turns +
                "\n playerOne=" + playerOne +
                "\n playerTwo=" + playerTwo +
                "\n playerThree=" + playerThree +
                "\n playerFour=" + playerFour +
                '}';
    }
}
