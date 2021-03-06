package com.falcotech.mazz.bigtwochampionship;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by phima on 4/21/2017.
 */

@Singleton
public class Navigator {
    @Inject
    public Navigator() {
        //empty
    }

    public void goGame(Context context, boolean finish){
        if(context != null){
            Intent intent = GameActivity.getCallingIntent(context);
            context.startActivity(intent);
            if(finish){
                ((BTActivity)context).finish();
            }
        }
    }
}
