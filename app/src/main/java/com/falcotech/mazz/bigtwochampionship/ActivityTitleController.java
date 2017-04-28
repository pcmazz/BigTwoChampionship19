package com.falcotech.mazz.bigtwochampionship;

import android.app.Activity;

import com.falcotech.mazz.bigtwochampionship.core.PerActivity;
import com.falcotech.mazz.bigtwochampionship.flow.activity.GameActivity;

import javax.inject.Inject;

/**
 * Created by phima on 4/21/2017.
 */

@PerActivity
public class ActivityTitleController {
    private final Activity activity;

    @Inject
    public ActivityTitleController(Activity activity) {
        this.activity = activity;
    }

    public void setTitle(CharSequence title) {
        activity.setTitle(title);
    }

    public void turnCompletedUpdate(int turnNumCompleted){
        GameActivity gameActivity = (GameActivity)activity;
        gameActivity.turnCompleted(turnNumCompleted);
    }
}

