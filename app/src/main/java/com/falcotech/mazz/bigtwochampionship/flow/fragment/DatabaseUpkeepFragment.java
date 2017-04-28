package com.falcotech.mazz.bigtwochampionship.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.falcotech.mazz.bigtwochampionship.DatabaseCleaner;
import com.falcotech.mazz.bigtwochampionship.TurnRunner;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTFragment;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;

import javax.inject.Inject;

/**
 * Created by phima on 4/27/2017.
 */

public class DatabaseUpkeepFragment extends BTFragment{


    @Inject
    DatabaseCleaner databaseCleaner;

    public DatabaseUpkeepFragment() {
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
        if(this.databaseCleaner != null){
            databaseCleaner.initialize();
        }else{
            Utils.bugger(getClass(), "onResume", "databaseCleaner = null...");
        }
    }

    @Override
    public void onDestroy() {
        Utils.bugger(getClass(), "onDestroy", "enter");
        super.onDestroy();
        this.databaseCleaner.destroy();
    }
}
