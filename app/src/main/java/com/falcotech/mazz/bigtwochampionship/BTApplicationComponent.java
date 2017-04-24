package com.falcotech.mazz.bigtwochampionship;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by phima on 4/21/2017.
 */

@Singleton
@Component(modules = BTApplicationModule.class)
public interface BTApplicationComponent {
    void inject(BTActivity btActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    GameRepository gameRepository();
}
