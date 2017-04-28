package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.models.Card;

/**
 * Created by phima on 4/26/2017.
 */

public abstract class CardFrame extends FrameLayout{
    protected Card card;
    protected RelativeLayout.LayoutParams layoutParams;

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        viewAddAction();
    }

    public CardFrame(@NonNull Context context, Card card, RelativeLayout.LayoutParams layoutParams) {
        super(context);
        this.card = card;
        this.layoutParams = layoutParams;
        this.setId(makeId());

    }
    public void kill(){
        card = null;
        layoutParams = null;
        //Utils.bugger(getClass(), "kill", "finish");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        kill();
    }



    protected abstract int makeId();

    protected abstract void viewAddAction();
}
