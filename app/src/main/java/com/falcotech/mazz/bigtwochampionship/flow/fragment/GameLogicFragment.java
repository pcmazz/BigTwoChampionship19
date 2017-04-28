package com.falcotech.mazz.bigtwochampionship.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falcotech.mazz.bigtwochampionship.GameLogicRunner;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTFragment;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;

import javax.inject.Inject;

/**
 * Created by phima on 4/27/2017.
 */
public class GameLogicFragment extends BTFragment{

    @Inject
    GameLogicRunner gameLogicRunner;

    public GameLogicFragment() {
        setRetainInstance(true);
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
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(gameLogicRunner != null){
            gameLogicRunner.initialize();
        }else{
            Utils.bugger(getClass(), "onResume", "gameLogicRunner = null...");
        }
    }

    @Override
    public void onDestroy() {
        Utils.bugger(getClass(), "onDestroy", "enter");
        super.onDestroy();
        this.gameLogicRunner.destroy();
    }
}

/*public class GameLogicFragment extends BTFragment{

    @Inject
    GameLogicRunner gameLogicRunner;

    public GameLogicFragment() {
        setRetainInstance(true);
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
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(gameLogicRunner != null){
            gameLogicRunner.initialize();
        }else{
            Utils.bugger(getClass(), "onResume", "gameLogicRunner = null...");
        }
    }

    @Override
    public void onDestroy() {
        Utils.bugger(getClass(), "onDestroy", "enter");
        super.onDestroy();
        this.gameLogicRunner.destroy();
    }
}*/
