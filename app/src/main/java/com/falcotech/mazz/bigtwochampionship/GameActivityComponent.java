package com.falcotech.mazz.bigtwochampionship;

import dagger.Component;

/**
 * Created by phima on 4/24/2017.
 */
@PerActivity
@Component(dependencies = BTApplicationComponent.class, modules = {BTActivityModule.class, GameActivityModule.class})
public interface GameActivityComponent extends BTActivityComponent{
    void inject(TestGameFragment testGameFragment);
}
