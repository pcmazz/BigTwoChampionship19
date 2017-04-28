package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;
import android.widget.RelativeLayout;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTActivity;
import com.falcotech.mazz.bigtwochampionship.core.BTApplication;
import com.falcotech.mazz.bigtwochampionship.interfaces.DealCompletionListener;
import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.reactive.DefaultObserver;
import com.falcotech.mazz.bigtwochampionship.reactive.RxUtils;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by phima on 4/26/2017.
 */

public abstract class HandView extends RelativeLayout {
    protected final List<Card> cards;
    protected RxSharedPreferences rxPrefs;
    private int dealCounter;
    private DealCompletionListener dealCompletionListener;
    public HandView(Context context, List<Card> cards) {
        super(context);
        this.cards = cards;
        rxPrefs = ((BTActivity)context).getRxSharedPreferences();
        dealCounter = 0;
        setViewParams();

    }

    public void execute(){
        dealCounter = 0;
        addFrames();
        deal();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureAction();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BTApplication.instance.watch(this);
        kill();
    }

    private void kill(){
        //cards = null;
        //Utils.bugger(getClass(), "kill", "finish");
    }

    protected int getFrameId(Card card){
        return card.getNumber() + (card.getSuit() * 100);
    }

    protected void bindCardView(CardView cardView){
        RxUtils.cardViewReady(cardView).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CardViewObserver());
        /*RxUtils.bind(RxUtils.cardViewReady(cardView), new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                dealCounter++;
                if(dealCounter == cards.size() && dealCompletionListener != null){

                    dealCompletionListener.onCompleted();

                }
            }
        });*/

    }

    public abstract void deal();

    public abstract void measureAction();

    public abstract int estimateTotalWidth();

    public abstract void setViewParams();

    public abstract RelativeLayout.LayoutParams makeFrameParams(Card lastCard);

    public abstract int getRatio();

    public abstract CardFrame getFrame(Card card);

    public abstract void addFrames();

    public abstract int getDelay(int index);

    public void setDealCompletionListener(DealCompletionListener dealCompletionListener) {
        this.dealCompletionListener = dealCompletionListener;
    }

    private class CardViewObserver extends DefaultObserver{
        @Override
        public void onComplete() {
            dealCounter++;
            //Utils.bugger(getClass(), "onComplete", "dealCounter = " + dealCounter);
            //Utils.bugger(getClass(), "onComplete", "cards sizre = " + cards.size());
            //Utils.bugger(getClass(), "onComplete", "listener null = " + (dealCompletionListener != null ? "False" : "True"));
            if(dealCounter == cards.size() && dealCompletionListener != null){
                //Utils.bugger(getClass(), "onComplete", "pre listener call");
                dealCompletionListener.onCompleted();
            }
            dispose();
        }
    }
}
