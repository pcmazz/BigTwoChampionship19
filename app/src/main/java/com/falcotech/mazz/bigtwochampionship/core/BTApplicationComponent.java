package com.falcotech.mazz.bigtwochampionship.core;

import android.content.Context;

import com.falcotech.mazz.bigtwochampionship.GameRepository;
import com.falcotech.mazz.bigtwochampionship.RxPrefRepository;
import com.falcotech.mazz.bigtwochampionship.TurnFactory;
import com.falcotech.mazz.bigtwochampionship.TurnFactoryData;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

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
    TurnFactory turnFactory();
    RxPrefRepository rxPrefRepository();
    RxSharedPreferences rxSharedPreferences();
}
