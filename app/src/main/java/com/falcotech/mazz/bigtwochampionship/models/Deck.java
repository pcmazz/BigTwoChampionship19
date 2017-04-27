package com.falcotech.mazz.bigtwochampionship.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by phima on 4/26/2017.
 */

public class Deck {
    private List<Card> cards;
    private int cardCount;
    public Deck() {
        makeDeck();
    }

    public List<Card> dealPlayer(){
        List<Card> result = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            result.add(dealCard());
        }
        return result;
    }

    private Card dealCard(){
        Random random = new Random();
        int index = (cardCount > 1 ? random.nextInt(cardCount - 1) : 0);
        Card result = cards.get(index);
        cards.remove(index);
        cardCount--;
        return result;
    }

    private void makeDeck(){
        cards = new ArrayList<>();
        cardCount = 52;
        for(int i = 2; i < 15; i++){
            for(int ii = 1; ii < 5; ii++){
                cards.add(new Card(makeCard(i, ii)));
            }
        }
    }

    private String makeCard(int num, int suit){
        return makeFirst(num) + "_" + makeSecond(suit);
    }

    private String makeFirst(int val){
        switch(val){
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 10:
                return "ten";
            case 11:
                return "jack";
            case 12:
                return "queen";
            case 13:
                return "king";
            case 14:
                return "ace";
        }
        return null;
    }
    private String makeSecond(int val){
        switch(val){
            case 1:
                return "diamonds";
            case 2:
                return "clubs";
            case 3:
                return "hearts";
            case 4:
                return "spades";
        }
        return null;
    }
}
