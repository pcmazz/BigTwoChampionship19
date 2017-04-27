package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.flow.Presenter;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by phima on 4/27/2017.
 */

public class DatabaseCleaner implements Presenter{
    private final GetGames getGames;

    @Inject
    public DatabaseCleaner(GetGames getGames) {
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
        Utils.bugger(getClass(), "destory", "destorying");
        this.getGames.dispose();
    }

    public void initialize(){
        this.getGames.execute(new DisposableObserver<Game>() {
            @Override
            public void onNext(Game game) {
                if(!game.getId().equals("Game~0") && expired(game)){
                    FirebaseDatabase.getInstance().getReference("games").child(game.getId()).setValue(null);
                }
            }

            @Override
            public void onError(Throwable e) {
                Utils.bugger(DatabaseCleaner.class, "initialize - onError", "e = " + e);
            }

            @Override
            public void onComplete() {
                Utils.bugger(DatabaseCleaner.class, "initialize - onComplete", "");
            }
        }, GetGames.Params.forGames());
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
