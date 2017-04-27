package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.RelativeLayout;

import com.falcotech.mazz.bigtwochampionship.models.Card;

/**
 * Created by phima on 4/26/2017.
 */

public class PlayerCardFrame extends CardFrame{
    public PlayerCardFrame(@NonNull Context context, Card card, RelativeLayout.LayoutParams layoutParams) {
        super(context, card, layoutParams);
    }

    @Override
    protected int makeId() {
        return card.getNumber() + (card.getSuit() * 100);
    }

    @Override
    protected void viewAddAction() {
        this.setLayoutParams(layoutParams);
    }
}
