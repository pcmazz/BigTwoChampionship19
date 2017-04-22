package com.falcotech.mazz.bigtwochampionship;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by phima on 4/21/2017.
 */

public abstract class BTActivity extends AppCompatActivity {
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerId, Fragment fragment) {
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
