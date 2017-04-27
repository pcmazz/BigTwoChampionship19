package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTApplication;
import com.falcotech.mazz.bigtwochampionship.custom_views.CardView;
import com.falcotech.mazz.bigtwochampionship.interfaces.CardAnimationListener;
import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.reactive.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * Created by phima on 4/26/2017.
 */

public final class CardViewObservable extends Observable<Object> {
    private final CardView view;

    public CardViewObservable(CardView view) {
        this.view = view;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        /*if (!Preconditions.checkMainThread(observer)) {
            return;
        }*/
        Listener listener = new Listener(view, observer);
        observer.onSubscribe(listener);
        view.setCardAnimationListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements CardAnimationListener {
        private final CardView view;
        private final Observer<? super Object> observer;

        public Listener(CardView view, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onFinished(Card card) {
            observer.onComplete();
            this.dispose();
        }

        @Override
        protected void onDispose() {
            Utils.bugger(CardViewObservable.class, "Listener", "disposed");
            view.setCardAnimationListener(null);
            BTApplication.instance.watch(this);
        }
    }
}
