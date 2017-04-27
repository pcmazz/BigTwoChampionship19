package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import android.view.animation.Animation;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTApplication;
import com.falcotech.mazz.bigtwochampionship.reactive.Preconditions;
import com.falcotech.mazz.bigtwochampionship.reactive.lifecycles.AnimationLifecycle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * Created by phima on 4/26/2017.
 */

public final class AnimationObservable extends Observable<Object> {
    private final Animation animation;

    public AnimationObservable(Animation animation) {
        this.animation = animation;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        Utils.bugger(AnimationObservable.class, "subscribeActual", "enter");
        /*if (!Preconditions.checkMainThread(observer)) {
            return;
        }*/
        Listener listener = new Listener(animation, observer);
        observer.onSubscribe(listener);
        animation.setAnimationListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements Animation.AnimationListener{
        private final Animation animation;
        private final Observer<? super Object> observer;

        public Listener(Animation animation, Observer<? super Object> observer) {
            this.animation = animation;
            this.observer = observer;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            //Utils.bugger(AnimationObservable.class, "onAnimationStart", "fire");
            if(!isDisposed()){
                observer.onNext(AnimationLifecycle.START);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(!isDisposed()){
                //Utils.bugger(AnimationObservable.class, "onAnimationEnd", "not disposed");
                observer.onNext(AnimationLifecycle.END);
                observer.onComplete();
                this.dispose();
            }else{
                //Utils.bugger(AnimationObservable.class, "onAnimationEnd", "disposed");
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            if(!isDisposed()){
                observer.onNext(AnimationLifecycle.REPEAT);
            }
        }

        @Override
        protected void onDispose() {
            Utils.bugger(AnimationObservable.class, "Listener", "disposed");
            animation.setAnimationListener(null);
            BTApplication.instance.watch(this);
        }
    }
}
