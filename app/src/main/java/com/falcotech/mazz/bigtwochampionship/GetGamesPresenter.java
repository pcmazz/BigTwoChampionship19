package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.flow.Presenter;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.DefaultObserver;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by phima on 4/27/2017.
 */

public class GetGamesPresenter implements Presenter{
    private final GetGames getGames;

    @Inject
    public GetGamesPresenter(GetGames getGames) {
        this.getGames = getGames;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getGames.dispose();
    }

    public void initialize(){
        doItBaby();
    }

    private void doItBaby(){
        this.getGames.execute(new GamesObserver(), GetGames.Params.forGames());
    }

    private final class GamesObserver extends DefaultObserver<Game>{
        @Override
        public void onNext(Game game) {
            //Utils.bugger(GetGamesPresenter.class, "GamesObserver - onNext", "game = " + game);
            if(!game.getId().equals("Game~0") && expired(game)){
                FirebaseDatabase.getInstance().getReference("games").child(game.getId()).setValue(null);
            }
        }

        @Override
        public void onError(Throwable e) {
           Utils.bugger(GetGamesPresenter.class, "GamesObserver - onError", "e = " + e);
        }

        @Override
        public void onComplete() {
            Utils.bugger(GetGamesPresenter.class, "GamesObserver - onComplete", "");
        }
        private boolean expired(Game game){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            try {
                Date gameDate = simpleDateFormat.parse(game.getLastUpdated());
                Date now = new Date();
                long diff = now.getTime() - gameDate.getTime();
                long hours = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
                Utils.bugger(GetGamesPresenter.class, "", "hours passed = " + hours);
                if(hours >= 2){
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return false;
        }
    }
}
