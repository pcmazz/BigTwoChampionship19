package com.falcotech.mazz.bigtwochampionship.flow.activity;

import android.os.Bundle;

import com.falcotech.mazz.bigtwochampionship.R;
import com.falcotech.mazz.bigtwochampionship.core.BTActivity;

public class SplashActivity extends BTActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.navigator.goGame(this, true);
    }
}
