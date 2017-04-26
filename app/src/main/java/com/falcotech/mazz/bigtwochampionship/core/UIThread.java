package com.falcotech.mazz.bigtwochampionship.core;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by phima on 4/21/2017.
 */

@Singleton
public class UIThread implements PostExecutionThread{

    @Inject
    UIThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
