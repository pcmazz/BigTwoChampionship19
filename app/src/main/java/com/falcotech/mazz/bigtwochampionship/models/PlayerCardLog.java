package com.falcotech.mazz.bigtwochampionship.models;

/**
 * Created by phima on 4/27/2017.
 */

public class PlayerCardLog {
    private Card card;
    private boolean pulled;
    private boolean sliding;
    private boolean tap;
    public PlayerCardLog(Card card) {
        this.card = card;
        pulled = false;
        sliding = false;
        tap = false;
    }

    public PlayerCardLog(String zipped) {
        String[] parts = zipped.split("-\\^-");

        card = new Card(parts[0]);
        pulled = parseBool(parts[1]);
        sliding = parseBool(parts[2]);
        tap = parseBool(parts[3]);
    }

    private boolean parseBool(String bool){
        if(bool.equals("true")){
            return true;
        }
        return false;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isPulled() {
        return pulled;
    }

    public void setPulled(boolean pulled) {
        this.pulled = pulled;
    }

    public boolean isSliding() {
        return sliding;
    }

    public void setSliding(boolean sliding) {
        this.sliding = sliding;
    }

    public boolean isTap() {
        return tap;
    }

    public void setTap(boolean tap) {
        this.tap = tap;
    }

    public String zip(){
        String pullStr = (pulled ? "true" : "false");
        String slideStr = (sliding ? "true" : "false");
        String tapStr = (tap ? "true" : "false");
        return card.getName() + "-^-" + pullStr + "-^-" + slideStr + "-^-" + tapStr;
    }

    @Override
    public String toString() {
        return "PlayerCardLog{" +
                "\ncard=" + card +
                "\n pulled=" + pulled +
                "\n sliding=" + sliding +
                '}';
    }
}
