package com.falcotech.mazz.bigtwochampionship;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by phima on 4/21/2017.
 */

public class BTApplication extends Application {
    private BTApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initialize();
        this.installLeakCanary();
    }

    public BTApplicationComponent getApplicationComponent(){
        return this.applicationComponent;
    }

    private void initialize(){
        this.applicationComponent = DaggerBTApplicationComponent.builder()
                .bTApplicationModule(new BTApplicationModule(this))
                .build();
    }

    private void installLeakCanary(){
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }


}
