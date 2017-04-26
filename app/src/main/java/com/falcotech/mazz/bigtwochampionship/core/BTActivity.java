package com.falcotech.mazz.bigtwochampionship.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.falcotech.mazz.bigtwochampionship.flow.Navigator;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;


import javax.inject.Inject;

/**
 * Created by phima on 4/21/2017.
 */

public abstract class BTActivity extends AppCompatActivity {

    @Inject
    protected Navigator navigator;

    @Inject
    protected RxSharedPreferences rxSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);

    }

    protected void addFragment(int containerId, Fragment fragment) {
        //component().inject(fragment);
        //component().inject(new ActivityTitleController(this));
        getSupportFragmentManager().beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    protected BTApplicationComponent getApplicationComponent() {
        return ((BTApplication)getApplication()).getApplicationComponent();
    }

    protected BTActivityModule getActivityModule() {
        return new BTActivityModule(this);
    }
}
