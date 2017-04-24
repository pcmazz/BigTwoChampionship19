package com.falcotech.mazz.bigtwochampionship;

import java.util.List;

/**
 * Created by phima on 4/24/2017.
 */

public class Player {
    private String name;
    private int number;
    private boolean ai;
    private List<Card> cards;
    private int points;

    public Player() {
    }

    public Player(Player copy) {
        this.name = copy.getName();
        this.number = copy.getNumber();
        this.ai = copy.isAi();
        this.cards = copy.getCards();
        this.points = copy.getPoints();
    }

    public Player(String name, int number, boolean ai, List<Card> cards, int points) {
        this.name = name;
        this.number = number;
        this.ai = ai;
        this.cards = cards;
        this.points = points;
    }

    @Override
    protected void finalize() throws Throwable {
        if(cards != null){
            for(Card card : cards){
                card.finalize();
            }
        }
        cards = null;
        super.finalize();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "Player{" +
                "\nname='" + name + '\'' +
                "\n number=" + number +
                "\n ai=" + ai +
                "\n cards=" + cards +
                "\n points=" + points +
                '}';
    }
}
