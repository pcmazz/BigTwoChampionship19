package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.models.Game;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by phima on 4/24/2017.
 */

public interface GameRepository {

    Observable<Game> game(final String gameId);

    Observable<Game> games();
}
