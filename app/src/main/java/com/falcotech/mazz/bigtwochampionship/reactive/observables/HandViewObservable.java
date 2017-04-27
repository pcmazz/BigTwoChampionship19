package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTApplication;
import com.falcotech.mazz.bigtwochampionship.custom_views.HandView;
import com.falcotech.mazz.bigtwochampionship.interfaces.DealCompletionListener;
import com.falcotech.mazz.bigtwochampionship.reactive.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * Created by phima on 4/26/2017.
 */

public final class HandViewObservable extends Observable<Object> {
    private final HandView handView;

    public HandViewObservable(HandView handView) {
        this.handView = handView;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        /*if (!Preconditions.checkMainThread(observer)) {
            return;
        }
*/
        Listener listener = new Listener(handView, observer);
        observer.onSubscribe(listener);
        handView.setDealCompletionListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements DealCompletionListener {
        private final HandView handView;
        private final Observer<? super Object> observer;

        public Listener(HandView handView, Observer<? super Object> observer) {
            this.handView = handView;
            this.observer = observer;
        }

        @Override
        public void onCompleted() {
            observer.onComplete();
            this.dispose();
        }

        @Override
        protected void onDispose() {
            Utils.bugger(this.getClass(), "HandViewObservable", "disposed");
            handView.setDealCompletionListener(null);
            BTApplication.instance.watch(this);
        }
    }
}


