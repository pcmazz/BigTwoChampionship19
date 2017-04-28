package com.falcotech.mazz.bigtwochampionship;

import io.reactivex.Observable;

/**
 * Created by phima on 4/27/2017.
 */

public interface RxPrefRepository {

    Observable<Integer> completedTurns();
}
