package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.flow.Presenter;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.DefaultObserver;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        this.getGames.execute(new CockObserver(), GetGames.Params.forGames());
        /*this.getGames.execute(new DisposableObserver<Game>() {
            @Override
            public void onNext(Game game) {
                Utils.bugger(DatabaseCleaner.class, "initialize", "game Id = " + game.getId());
                if(!game.getId().equals("Game~0")){
                    if(expired(game)){
                        FirebaseDatabase.getInstance().getReference("games").child(game.getId()).setValue(null);
                    }
                }else{
                    this.onComplete();
                }
            }

            @Override
            public void onError(Throwable e) {
                Utils.bugger(DatabaseCleaner.class, "initialize - onError", "e = " + e);
            }

            @Override
            public void onComplete() {
                Utils.bugger(DatabaseCleaner.class, "initialize - onComplete", "");
                dispose();
            }
        }, GetGames.Params.forGames());*/
    }

    private boolean expired(Game game){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM-dd-yyyy HH:mm:ss");
        DateTime gameDate = formatter.parseDateTime(game.getLastUpdated());

        DateTime nowDate = DateTime.now(DateTimeZone.forID("America/Chicago"));
        long diff = nowDate.getMillis() - gameDate.getMillis();
        Utils.bugger(DatabaseCleaner.class, "expired", "diff = " + diff);
        long hours = ((diff/1000)/60)/60;
        Utils.bugger(DatabaseCleaner.class, "expired", "hours passed = " + hours);
        if(hours >= 2){
            return true;
        }
        return false;
    }

    private static final class CockObserver extends DefaultObserver<Game>{
        @Override
        public void onNext(Game game) {
            Utils.bugger(DatabaseCleaner.class, "CockObserver", "game Id = " + game.getId());
            if(!game.getId().equals("Game~0")){
                if(expired(game)){
                    FirebaseDatabase.getInstance().getReference("games").child(game.getId()).setValue(null);
                }
            }else{
                //this.onComplete();
            }
        }

        @Override
        public void onError(Throwable e) {
            Utils.bugger(DatabaseCleaner.class, "CockObserver - onError", "e = " + e);
        }

        @Override
        public void onComplete() {
            Utils.bugger(DatabaseCleaner.class, "CockObserver - onComplete", "");
        }

        @Override
        protected void onStart() {
            Utils.bugger(DatabaseCleaner.class, "CockObserver - onStart", "");
        }
        private boolean expired(Game game){
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM-dd-yyyy HH:mm:ss");
            DateTime gameDate = formatter.parseDateTime(game.getLastUpdated());

            DateTime nowDate = DateTime.now(DateTimeZone.forID("America/Chicago"));
            long diff = nowDate.getMillis() - gameDate.getMillis();
            Utils.bugger(DatabaseCleaner.class, "expired", "diff = " + diff);
            long hours = ((diff/1000)/60)/60;
            Utils.bugger(DatabaseCleaner.class, "expired", "hours passed = " + hours);
            if(hours >= 2){
                return true;
            }
            return false;
        }
    }

}
