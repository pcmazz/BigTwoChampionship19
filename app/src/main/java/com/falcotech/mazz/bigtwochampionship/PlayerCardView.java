package com.falcotech.mazz.bigtwochampionship;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by phima on 4/24/2017.
 */

public class PlayerCardView extends CardView{

    public PlayerCardView(Context context, Card card) {
        super(context, card);
    }

    @Override
    public int getImgId(boolean front) {
        if(front){
            return getResources().getIdentifier(card.getName(), "drawable", packageName);
        }else{
            return getResources().getIdentifier("card_back_red", "drawable", packageName);
        }
    }

    @Override
    public int makeId() {
        return card.getNumber() + (card.getSuit() * 1000);
    }

    @Override
    public void measureAction() {
        int width = this.getMeasuredWidth();
        /*RxSharedPreferences rxPrefs = RxSharedPreferences.create(getContext().getSharedPreferences(Utils.BIG_PREFS, Context.MODE_PRIVATE));
        if(width > rxPrefs.getInteger(Utils.PLAYER_CARD_WIDTH).get()){
            rxPrefs.getInteger(Utils.PLAYER_CARD_WIDTH).set(width);
        }*/
    }
}
