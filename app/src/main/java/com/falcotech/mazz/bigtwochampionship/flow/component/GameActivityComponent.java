package com.falcotech.mazz.bigtwochampionship.flow.component;

import com.falcotech.mazz.bigtwochampionship.core.BTActivityComponent;
import com.falcotech.mazz.bigtwochampionship.core.BTActivityModule;
import com.falcotech.mazz.bigtwochampionship.core.BTApplicationComponent;
import com.falcotech.mazz.bigtwochampionship.core.PerActivity;
import com.falcotech.mazz.bigtwochampionship.flow.activity.GameActivity;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.DatabaseUpkeepFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.PlayerCardFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.PlayerHandFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.StageFragment;
import com.falcotech.mazz.bigtwochampionship.flow.module.GameActivityModule;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.TestGameFragment;

import dagger.Component;

/**
 * Created by phima on 4/24/2017.
 */
@PerActivity
@Component(dependencies = BTApplicationComponent.class, modules = {BTActivityModule.class, GameActivityModule.class})
public interface GameActivityComponent extends BTActivityComponent {
    void inject(TestGameFragment testGameFragment);
    void inject(PlayerHandFragment PlayerHandFragment);
    void inject(StageFragment stageFragment);
    void inject(DatabaseUpkeepFragment databaseUpkeepFragment);
}
