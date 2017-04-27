package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.BackgroundDisposable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by phima on 4/26/2017.
 */

public final class GetGameObservable extends Observable<Game>{
    private final DatabaseReference databaseReference;

    public GetGameObservable(DatabaseReference databaseReference) {
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
            if(!isDisposed() && dataSnapshot != null){
                Game game = dataSnapshot.getValue(Game.class);
                observer.onNext(game);
                observer.onComplete();
                this.dispose();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
