package com.falcotech.mazz.bigtwochampionship;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by phima on 4/21/2017.
 */

@Module
public class BTActivityModule {
    private final Activity activity;

    public BTActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return activity;
    }
}
