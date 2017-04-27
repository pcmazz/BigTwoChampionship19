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

    public void initialize(String gameId){
        this.doItBaby(gameId);
    }

    private void doItBaby(String gameId){
        this.getGame.execute(new GameObserver(), GetGame.Params.forGame(gameId));
    }

    private final class GameObserver extends DefaultObserver<Game> {
        @Override
        public void onNext(Game game) {
            Utils.bugger(GetGamePresenter.class, "GameObserver - onNext", "game = " + game);
            dispose();
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
