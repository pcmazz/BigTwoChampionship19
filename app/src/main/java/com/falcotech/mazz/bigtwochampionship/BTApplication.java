package com.falcotech.mazz.bigtwochampionship;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by phima on 4/21/2017.
 */

public class BTApplication extends Application {
    private BTApplicationComponent applicationComponent;

    public BTApplicationComponent getApplicationComponent(){
        return this.applicationComponent;
    }

    private void initialize(){

    }

    private void installLeakCanary(){
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }


}
