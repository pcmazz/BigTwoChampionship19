package com.falcotech.mazz.bigtwochampionship;

import android.view.View;

import com.falcotech.mazz.bigtwochampionship.models.Game;

import io.reactivex.Completable;

/**
 * Created by phima on 4/27/2017.
 */

public interface TurnFactory {
    Completable turn(View stage, int turnNum);
}
