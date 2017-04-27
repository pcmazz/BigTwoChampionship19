package com.falcotech.mazz.bigtwochampionship.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTApplication;
import com.falcotech.mazz.bigtwochampionship.core.BTFragment;
import com.falcotech.mazz.bigtwochampionship.custom_views.PlayerHandView;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;
import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.reactive.RxUtils;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by phima on 4/26/2017.
 */

public class PlayerHandFragment extends BTFragment{
    private static final String PARAM_CARDS = "param_cards";

    public PlayerHandFragment() {
    }

    public static PlayerHandFragment initialize(List<Card> cards){
        final PlayerHandFragment playerHandFragment = new PlayerHandFragment();
        final Bundle args = new Bundle();
        args.putString(PARAM_CARDS, serialize(cards));
        playerHandFragment.setArguments(args);
        return playerHandFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(GameActivityComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PlayerHandView playerHandView = new PlayerHandView(getActivity(), deserialize(getArguments().getString(PARAM_CARDS)));
       /* RxUtils.handDealCompleted(playerHandView).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Utils.bugger(PlayerHandFragment.class, "onCreateView", "onComplete");

            }
        });*/
        ButterKnife.bind(this, playerHandView);
        return playerHandView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayerHandView playerHandView = (PlayerHandView)view;
        playerHandView.execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       /* RefWatcher refWatcher = BTApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);*/
       //BTApplication.instance.watch(this);
    }

    private static String serialize(List<Card> cards){
        String result = "";
        if(cards != null){
            result = cards.get(0).getName();
            for(int i = 1; i < cards.size(); i++){
                result += "|^|" + cards.get(i).getName();
            }
        }
        return result;
    }

    private static List<Card> deserialize(String cardsStr){
        List<Card> cards = new ArrayList<>();
        if(cardsStr != null && cardsStr.length() > 0){
            String[] cardParts = cardsStr.split("\\|\\^\\|");
            Utils.bugger(PlayerHandFragment.class, "deserialize", "cardParts size = " + cardParts.length);
            for(String cardStr : cardParts){
                cards.add(new Card(cardStr));
            }
        }
        return cards;
    }
}
