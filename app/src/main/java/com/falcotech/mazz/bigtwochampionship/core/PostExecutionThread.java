package com.falcotech.mazz.bigtwochampionship.core;

import io.reactivex.Scheduler;

/**
 * Created by phima on 4/21/2017.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
