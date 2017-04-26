package com.falcotech.mazz.bigtwochampionship;

import android.util.Log;

import com.falcotech.mazz.bigtwochampionship.core.PerActivity;
import com.falcotech.mazz.bigtwochampionship.flow.Presenter;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.DefaultObserver;

import javax.inject.Inject;

/**
 * Created by phima on 4/24/2017.
 */

@PerActivity
public class GetGamePresenter implements Presenter {
    private final GetGame getGame;

    @Inject
    public GetGamePresenter(GetGame getGame) {
        this.getGame = getGame;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getGame.dispose();
    }

    public void initialize(){
        this.doItBaby();
    }

    private void doItBaby(){
        this.getGame.execute(new GameObserver(), GetGame.Params.forGame("Game~0"));
    }

    private final class GameObserver extends DefaultObserver<Game> {
        @Override
        public void onNext(Game game) {
            Log.d("DEBUG", "GameObserver onNext game = " + game);
        }

        @Override
        public void onError(Throwable e) {
            Log.d("DEBUG", "GameObserver onError e = " + e);
        }

        @Override
        public void onComplete() {
            Log.d("DEBUG", "GameObserver onComplete");
        }
    }
}
