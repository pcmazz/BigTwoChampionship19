package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.models.PlayerCardLog;
import com.falcotech.mazz.bigtwochampionship.reactive.RxUtils;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by phima on 4/26/2017.
 */

public class PlayerCardFrame extends CardFrame{
    private RxSharedPreferences rxPrefs;
    public PlayerCardFrame(@NonNull Context context, Card card, RelativeLayout.LayoutParams layoutParams) {
        super(context, card, layoutParams);
        ButterKnife.bind(this);
    }

    public PlayerCardFrame(@NonNull Context context, Card card, RelativeLayout.LayoutParams layoutParams, RxSharedPreferences rxPrefs) {
        super(context, card, layoutParams);
        this.rxPrefs = rxPrefs;
        ButterKnife.bind(this);
    }

    @Override
    protected int makeId() {
        return card.getNumber() + (card.getSuit() * 100);
    }

    @Override
    protected void viewAddAction() {
        this.setLayoutParams(layoutParams);
    }

    @OnClick
    public void onClick(){
        Utils.bugger(getClass(), "onClick", "clicky");
        //this.startAnimation(upTrans());
        trySlide(card);
    }

    private void slide(final boolean up){
        Utils.bugger(getClass(), "slide", "up = " + up);
        TranslateAnimation animationTrans = (up ? upTrans() : downTrans());
        RxUtils.animationUpdate(animationTrans).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                //d.dispose();
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                updateLog(card, up);

            }
        });
        this.startAnimation(animationTrans);
    }

    private void trySlide(Card card){
        List<PlayerCardLog> logs = rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).get();
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getCard().sameCard(card)){
                if(!logs.get(i).isSliding()){
                    logs.get(i).setSliding(true);
                    rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).set(logs);
                    slide(!logs.get(i).isPulled());
                    break;
                }

            }
        }
    }

    private boolean startAnim(Card card){
        List<PlayerCardLog> logs = rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).get();
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getCard().sameCard(card)){
                logs.get(i).setSliding(true);
                rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).set(logs);
                return !logs.get(i).isPulled();
            }
        }
        return false;
    }

    private void updateLog(Card card, boolean up){
        List<PlayerCardLog> logs = rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).get();
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getCard().sameCard(card)){
                logs.get(i).setPulled(up);
                logs.get(i).setSliding(false);
                rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).set(logs);
                break;
            }
        }
    }

    private TranslateAnimation upTrans(){
        TranslateAnimation animationTrans = new TranslateAnimation(0, 0, 0, -100);
        animationTrans.setDuration(500);
        animationTrans.setFillEnabled(true);
        animationTrans.setFillAfter(true);
        animationTrans.setFillBefore(false);
        return animationTrans;
    }
    private TranslateAnimation downTrans(){
        TranslateAnimation animationTrans = new TranslateAnimation(0, 0, -100, 0);
        animationTrans.setDuration(500);
        animationTrans.setFillEnabled(true);
        animationTrans.setFillAfter(true);
        animationTrans.setFillBefore(true);
        return animationTrans;
    }
}
