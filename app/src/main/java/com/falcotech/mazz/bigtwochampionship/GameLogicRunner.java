package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.core.PerActivity;
import com.falcotech.mazz.bigtwochampionship.flow.Presenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by phima on 4/27/2017.
 */
@PerActivity
public class GameLogicRunner implements Presenter{
    private final WatchTurns watchTurns;

    @Inject
    public GameLogicRunner(WatchTurns watchTurns) {
        this.watchTurns = watchTurns;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.watchTurns.dispose();
    }

    public void initialize(){
        this.watchTurns.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Utils.bugger(GameLogicRunner.class, "initialize", "onNext integer = " + integer);

            }

            @Override
            public void onError(Throwable e) {
                Utils.bugger(GameLogicRunner.class, "initialize", "onError e = " + e);
            }

            @Override
            public void onComplete() {
                Utils.bugger(GameLogicRunner.class, "initialize", "onComplete");
            }
        }, WatchTurns.Params.forPrefs());
    }
}
