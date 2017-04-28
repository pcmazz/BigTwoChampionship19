package com.falcotech.mazz.bigtwochampionship.custom_views;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.models.PlayerCardLog;
import com.falcotech.mazz.bigtwochampionship.reactive.RxUtils;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by phima on 4/26/2017.
 */

public class PlayerHandView extends HandView{
    public PlayerHandView(Context context, List<Card> cards) {
        super(context, cards);
    }

    @Override
    public void deal() {
        //Utils.bugger(getClass(), "deal", "enter cards.size = " + cards.size());
        for(int i = 0; i < cards.size(); i++){
            final int index = i;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getFrame(cards.get(index)).addView(makePlayerCardView(cards.get(index)));
                }
            }, getDelay(index));
        }
    }

    @Override
    public void measureAction() {
        int width = this.getMeasuredWidth();
        //Utils.bugger(getClass(), "measureAction", "measured width = " + width);
        if(width > rxPrefs.getInteger(Utils.PLAYER_SCREEN_WIDTH).get()){
            rxPrefs.getInteger(Utils.PLAYER_SCREEN_WIDTH).set(width);
        }
        //rxPrefs.getInteger(Utils.PLAYER_SCREEN_WIDTH).set(2550);
    }

    @Override
    public int estimateTotalWidth() {
        int totalCards = cards.size();

        int maxCards = rxPrefs.getInteger(Utils.PLAYER_CARD_WIDTH, 216).get() * totalCards;
        //Utils.bugger(getClass(), "estimateTotalWidth", "maxCards = " + maxCards);
        //Utils.bugger(getClass(), "estimateTotalWidth", "getRatio() = " + getRatio());
        //Utils.bugger(getClass(), "estimateTotalWidth", "totalCards = " + totalCards);
        int result = maxCards - (getRatio() * (totalCards - 1));
        //Utils.bugger(getClass(), "estimateTotalWidth", "result = " + result);
        return result;
    }

    @Override
    public void setViewParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(estimateTotalWidth(), LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        this.setLayoutParams(layoutParams);
        this.setGravity(Gravity.BOTTOM);
    }

    @Override
    public LayoutParams makeFrameParams(Card lastCard) {
        if(lastCard != null){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //Utils.bugger(getClass(), "makeParams", "ratio = " + getRatio());
            params.setMargins(-getRatio(), 0, 0, 0);
            params.addRule(RelativeLayout.RIGHT_OF, getFrameId(lastCard));
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            return params;
        }else{
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            return params;
        }
    }

    @Override
    public int getRatio() {
        int totalCards = cards.size();
        if(totalCards < 9){
            totalCards = 9;
        }

        int cardWidth = rxPrefs.getInteger(Utils.PLAYER_CARD_WIDTH, 216).get();
        int totalWidth = (rxPrefs.getInteger(Utils.PLAYER_SCREEN_WIDTH, 2550).get() - (2 * cardWidth)) - 50;
        int singleSpace = totalWidth/totalCards;
        return cardWidth - singleSpace;
    }

    @Override
    public CardFrame getFrame(Card card) {
        return (PlayerCardFrame)findViewById(getFrameId(card));
    }

    @Override
    public void addFrames() {
        makeLogList();
        for(int i = 0; i < cards.size(); i++){
            if(i == 0){
                PlayerCardFrame playerCardFrame = new PlayerCardFrame(this.getContext(), cards.get(i), makeFrameParams(null), rxPrefs);
                this.addView(playerCardFrame);
            }else{
                PlayerCardFrame playerCardFrame = new PlayerCardFrame(this.getContext(), cards.get(i), makeFrameParams(cards.get(i - 1)), rxPrefs);
                this.addView(playerCardFrame);
            }
        }
    }

    private void makeLogList(){
        List<PlayerCardLog> logs = new ArrayList<>();
        for(Card card : cards){
            logs.add(new PlayerCardLog(card));
        }
        rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).set(logs);
    }

    @Override
    public int getDelay(int index) {
        return index * delayFactor(index);
    }

    private PlayerCardView makePlayerCardView(Card card){
        PlayerCardView playerCardView = new PlayerCardView(this.getContext(), card, rxPrefs);
        bindCardView(playerCardView);
        return playerCardView;
    }



/*    private void bindPrefClicks(){
        RxSharedPreferences rxPrefs = RxSharedPreferences.create(getContext().getSharedPreferences(Utils.BIG_PREFS, Context.MODE_PRIVATE));
        Utils.bugger(getClass(), "bindPrefClicks", "logs = " + (rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).get() != null ? rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).get().size() : "NULL"));
        rxPrefs.getPlayerCardLogList(Utils.PLAYER_CARDS).asObservable().compose(RxLifecycleAndroid.bindView(this)).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                Utils.bugger(PlayerHandView.class, "bindPrefClicks", "onSubscribe");

            }

            @Override
            public void onNext(Object o) {
                Utils.bugger(PlayerHandView.class, "bindPrefClicks", "onNext");
                parseLog((List<PlayerCardLog>)o);
            }

            @Override
            public void onError(Throwable e) {
                Utils.bugger(PlayerHandView.class, "bindPrefClicks", "onError");
            }

            @Override
            public void onComplete() {
                Utils.bugger(PlayerHandView.class, "bindPrefClicks", "onComplete");
            }
        });
    }

    private void parseLog(List<PlayerCardLog> logs){
        for(PlayerCardLog log : logs){
            if(log.isTap() && !log.isSliding()){
                log.setSliding(true);
                log.setTap(false);
                PlayerUtils.updateLog(getContext(), log);
                slide(log, !log.isPulled());
            }
        }
    }*/
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
    /*private void slide(final PlayerCardLog log, final boolean up){
        TranslateAnimation animationTrans = (up ? upTrans() : downTrans());
        RxUtils.animationUpdate(animationTrans).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                log.setPulled(up);
                log.setSliding(false);
                PlayerUtils.updateLog(getContext(), log);
            }
        });
        getFrame(log.getCard()).startAnimation(animationTrans);
    }*/

    private int delayFactor(int index){
        switch(index + 1) {
            case 2:
                return 50;
            case 3:
                return 45;
            case 4:
                return 40;
            case 5:
                return 40;
            case 6:
                return 35;
            case 7:
                return 35;
            case 8:
                return 30;
            case 9:
                return 30;
            case 10:
                return 25;
            case 11:
                return 25;
            case 12:
                return 20;
            case 13:
                return 20;
            default:
                return 0;
        }
    }
}
