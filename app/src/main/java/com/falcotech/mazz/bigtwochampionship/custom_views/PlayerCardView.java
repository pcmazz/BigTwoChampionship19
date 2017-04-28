package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by phima on 4/24/2017.
 */

public class PlayerCardView extends CardView{



    public PlayerCardView(Context context, Card card) {
        super(context, card);
        ButterKnife.bind(this);
    }

    public PlayerCardView(Context context, Card card, RxSharedPreferences rxPrefs) {
        super(context, card, rxPrefs);
        ButterKnife.bind(this);
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
        //Utils.bugger(getClass(), "measureAction", "width = " + width);
        if(width > rxPrefs.getInteger(Utils.PLAYER_CARD_WIDTH).get()){
            rxPrefs.getInteger(Utils.PLAYER_CARD_WIDTH).set(width);
        }
    }


}
