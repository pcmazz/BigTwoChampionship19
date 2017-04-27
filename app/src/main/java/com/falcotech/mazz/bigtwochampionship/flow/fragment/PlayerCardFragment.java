package com.falcotech.mazz.bigtwochampionship.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falcotech.mazz.bigtwochampionship.core.BTFragment;
import com.falcotech.mazz.bigtwochampionship.custom_views.PlayerCardView;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;
import com.falcotech.mazz.bigtwochampionship.models.Card;

import butterknife.ButterKnife;

/**
 * Created by phima on 4/26/2017.
 */

public class PlayerCardFragment extends BTFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getComponent(GameActivityComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PlayerCardView playerCardView = new PlayerCardView(getActivity(), new Card("ace_spades"));
        ButterKnife.bind(this, playerCardView);
        return playerCardView;
    }
}
