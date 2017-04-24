package com.falcotech.mazz.bigtwochampionship;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Component;

/**
 * Created by phima on 4/21/2017.
 */

@PerActivity
@Component(dependencies = BTApplicationComponent.class, modules = BTActivityModule.class)
public interface BTActivityComponent{
    //Exposed to sub-graphs.
    Activity activity();
}
