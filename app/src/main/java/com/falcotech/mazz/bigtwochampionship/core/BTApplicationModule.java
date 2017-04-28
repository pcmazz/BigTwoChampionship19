package com.falcotech.mazz.bigtwochampionship.core;

import android.content.Context;

import com.falcotech.mazz.bigtwochampionship.GameDataRepository;
import com.falcotech.mazz.bigtwochampionship.GameRepository;
import com.falcotech.mazz.bigtwochampionship.RxPrefRepository;
import com.falcotech.mazz.bigtwochampionship.RxPrefRepositoryData;
import com.falcotech.mazz.bigtwochampionship.TurnFactory;
import com.falcotech.mazz.bigtwochampionship.TurnFactoryData;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by phima on 4/21/2017.
 */

@Module
public class BTApplicationModule {
    private final BTApplication application;

    public BTApplicationModule(BTApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    GameRepository providesGameRepository(GameDataRepository gameDataRepository){
        return gameDataRepository;
    }

    @Provides
    @Singleton
    TurnFactory providesTurnFactory(TurnFactoryData turnFactoryData){
        return turnFactoryData;
    }

    @Provides
    @Singleton
    RxPrefRepository providesRxPrefsRepository(RxPrefRepositoryData rxPrefRepositoryData){
        return rxPrefRepositoryData;
    }

    @Provides
    @Singleton
    RxSharedPreferences providesRxSharedPreferences(){
        return RxSharedPreferences.create(application.getSharedPreferences(Utils.BIG_PREFS, Context.MODE_PRIVATE));
    }

}
