package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by phima on 4/27/2017.
 */

@Singleton
public class RxPrefRepositoryData implements RxPrefRepository{

    @Inject
    RxSharedPreferences rxSharedPreferences;

    @Inject
    public RxPrefRepositoryData() {
    }

    @Override
    public Observable<Integer> completedTurns() {
        return rxSharedPreferences.getInteger(Utils.COMPLETED_TURNS).asObservable();
    }
}
