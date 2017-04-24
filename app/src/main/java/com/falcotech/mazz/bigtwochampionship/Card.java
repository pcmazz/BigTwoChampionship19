package com.falcotech.mazz.bigtwochampionship;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

/**
 * Created by phima on 4/24/2017.
 */

public class Card implements Comparable<Card>, Serializable {
    private String name;
    private int number;
    private int suit;

    public Card() {
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public Card(String name) {
        //Log.d("DEBUG", "Card name = " + name);
        this.name = name;
        setVals();
    }

    @Exclude
    private void setVals(){
        String vals[] = name.split("_");
        number = numberVal(vals[0]);
        suit = suitVal(vals[1]);

    }

    @Exclude
    private int numberVal(String numStr){
        switch(numStr){
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            case "ten":
                return 10;
            case "jack":
                return 11;
            case "queen":
                return 12;
            case "king":
                return 13;
            case "ace":
                return 14;
            case "two":
                return 15;
            default:
                return 0;
        }
    }

    @Exclude
    private int suitVal(String suitStr){
        switch(suitStr){
            case "spades":
                return 4;
            case "hearts":
                return 3;
            case "clubs":
                return 2;
            case "diamonds":
                return 1;
            default:
                return 0;
        }
    }

    @Exclude
    public boolean isGreater(Card c){
        if(this.number > c.getNumber()){
            return true;
        }else if(this.number == c.getNumber()){
            if(this.suit > c.getSuit()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Exclude
    public boolean sameSuit(Card c){
        if(this.suit == c.getSuit()){
            return true;
        }
        return false;
    }

    @Exclude
    public boolean sameNumber(Card c){
        if(this.number == c.getNumber()){
            return true;
        }
        return false;
    }

    @Exclude
    public boolean sameCard(Card c){
        if(this.number == c.getNumber() && this.suit == c.getSuit()){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "\nname='" + name + '\'' +
                "\n number=" + number +
                "\n suit=" + suit +
                '}';
    }

    @Override
    public int compareTo(Card otherCard) {
        if(this.number == otherCard.getNumber()){
            return this.suit - otherCard.getSuit();
        }else{
            return this.number - otherCard.getNumber();
        }
    }
}
