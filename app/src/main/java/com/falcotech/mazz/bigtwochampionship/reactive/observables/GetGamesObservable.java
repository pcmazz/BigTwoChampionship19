package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.BackgroundDisposable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by phima on 4/27/2017.
 */

public final class GetGamesObservable extends Observable<Game> {
    private final DatabaseReference databaseReference;

    public GetGamesObservable(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }
    @Override
    protected void subscribeActual(Observer<? super Game> observer) {
        Listener listener = new Listener(databaseReference, observer);
        observer.onSubscribe(listener);
        databaseReference.addListenerForSingleValueEvent(listener);
    }

    static final class Listener extends BackgroundDisposable implements ValueEventListener {
        private final DatabaseReference databaseReference;
        private final Observer<? super Game> observer;

        public Listener(DatabaseReference databaseReference, Observer<? super Game> observer) {
            this.databaseReference = databaseReference;
            this.observer = observer;
        }

        @Override
        protected void onDispose() {
            databaseReference.removeEventListener(this);
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if(!isDisposed()){

                /*Map<String, Object> games = (Map<String, Object>)dataSnapshot.getValue();
                Utils.bugger(GetGamesObservable.class, "onDataChange", "games size = " + games.size());
                for(Map.Entry<String, Object> entry : games.entrySet()){
                    Game game = (Game)entry.getValue();
                    Utils.bugger(GetGamesObservable.class, "onDataChange", "game = " + game);
                    observer.onNext(game);
                }*/
                /*for(Game game : games){
                    observer.onNext(game);
                }*/

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Game game = postSnapshot.getValue(Game.class);

                    //Utils.bugger(GetGamesObservable.class, "onDataChange", "game = " + game);
                    observer.onNext(game);
                }

                observer.onComplete();
                this.dispose();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

        private void debug(){
            Utils.bugger(GetGamesObservable.class, "debug", "enter");
            Utils.bugger(GetGamesObservable.class, "debug", "disposed = " + isDisposed());
        }

    }
}
