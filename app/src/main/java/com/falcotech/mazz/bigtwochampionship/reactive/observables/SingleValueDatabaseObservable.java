package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import com.falcotech.mazz.bigtwochampionship.reactive.BackgroundDisposable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by phima on 4/27/2017.
 */

public final class SingleValueDatabaseObservable extends Observable<Object> {
    private final DatabaseReference databaseReference;

    public SingleValueDatabaseObservable(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        Listener listener = new Listener(databaseReference, observer);
        observer.onSubscribe(listener);
        databaseReference.addListenerForSingleValueEvent(listener);
    }

    static final class Listener extends BackgroundDisposable implements ValueEventListener{
        private final DatabaseReference databaseReference;
        private final Observer<? super Object> observer;

        Listener(DatabaseReference databaseReference, Observer<? super Object> observer){
            this.databaseReference = databaseReference;
            this.observer = observer;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(!isDisposed()){
                observer.onNext(dataSnapshot);
                observer.onComplete();
                this.dispose();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

        @Override
        protected void onDispose() {
            databaseReference.removeEventListener(this);
        }
    }
}
