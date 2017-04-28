package com.falcotech.mazz.bigtwochampionship;

import android.view.View;

import com.falcotech.mazz.bigtwochampionship.core.PerActivity;
import com.falcotech.mazz.bigtwochampionship.flow.Presenter;
import com.falcotech.mazz.bigtwochampionship.models.Game;

import javax.inject.Inject;

import io.reactivex.functions.Action;

/**
 * Created by phima on 4/27/2017.
 */
@PerActivity
public class TurnRunner implements Presenter{
    private final TurnProcess turnProcess;

    @Inject
    public TurnRunner(TurnProcess turnProcess) {
        this.turnProcess = turnProcess;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        turnProcess.dispose();
    }

    public void initialize(View view, final int turnNum){
        this.turnProcess.execute(new Action() {
            @Override
            public void run() throws Exception {
                //THIS IS FUCKING BEAUTIFUL. NO LEAKS. MIMICK THIS SETUP FOR EVERYTHING
                Utils.bugger(TurnRunner.class, "initialize", "onComplete");
                if(turnNum == 30){
                    turnProcess.dispose();
                }
            }
        }, TurnProcess.Params.forTurn(view, turnNum));
    }
}
