package com.falcotech.mazz.bigtwochampionship;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by phima on 4/24/2017.
 */

public interface GameRepository {

    Observable<Game> game(final String gameId);

    Observable<List<Game>> games();
}
