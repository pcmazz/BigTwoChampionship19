package com.falcotech.mazz.bigtwochampionship.core;

import android.app.Application;
import android.content.Context;


import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by phima on 4/21/2017.
 */

public class BTApplication extends Application {
    private BTApplicationComponent applicationComponent;
    private RefWatcher refWatcher;
    public static BTApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context){
        return ((BTApplication)context.getApplicationContext()).refWatcher;
    }

    public void watch(Object object){
        if(refWatcher != null){
            refWatcher.watch(object);
        }
    }

}
