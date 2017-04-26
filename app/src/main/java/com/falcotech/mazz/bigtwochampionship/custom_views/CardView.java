package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.falcotech.mazz.bigtwochampionship.CardAnimationListener;
import com.falcotech.mazz.bigtwochampionship.R;
import com.falcotech.mazz.bigtwochampionship.models.Card;

/**
 * Created by phima on 4/24/2017.
 */

public abstract class CardView extends ViewFlipper {
    protected Card card;
    protected Animation animIn;
    protected Animation animOut;
    protected String packageName;
    protected boolean deuces;
    protected CardAnimationListener cardAnimationListener;
    public CardView(Context context, Card card) {
        super(context);
        this.card = card;
        packageName = context.getPackageName();

        deuces = false;
        this.setId(makeId());
        this.setVisibility(View.INVISIBLE);
        ImageView cardBack = new ImageView(getContext());
        ImageView cardFront = new ImageView(getContext());
        cardBack.setImageResource(getImgId(false));
        cardFront.setImageResource(getImgId(true));
        this.addView(cardBack);
        this.addView(cardFront);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachAction();
    }

    private void kill(){
        card = null;
        animOut = null;
        animIn = null;
        //Utils.bugger(getClass(), "kill", "finish");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        kill();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureAction();
    }

    public abstract int getImgId(boolean front);
    public abstract int makeId();
    public abstract void measureAction();

    private void attachAction(){
        bindAnimations();
        this.setInAnimation(animIn);
        this.setOutAnimation(animOut);
        //flip();
    }

    private void bindAnimations(){
        animIn = AnimationUtils.loadAnimation(this.getContext(), R.anim.hand_card_in);
        animOut = AnimationUtils.loadAnimation(this.getContext(), R.anim.hand_card_out);
        //bindAnim(animIn);
        //bindAnim(animOut);
    }

    private void flip(){
        this.setVisibility(View.VISIBLE);
        this.showNext();
    }

    public void setCardAnimationListener(CardAnimationListener cardAnimationListener) {
        this.cardAnimationListener = cardAnimationListener;
    }
}
