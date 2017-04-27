package com.falcotech.mazz.bigtwochampionship.reactive.observables;

import android.animation.Animator;

import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTApplication;
import com.falcotech.mazz.bigtwochampionship.reactive.Preconditions;
import com.falcotech.mazz.bigtwochampionship.reactive.lifecycles.AnimatorLifecycle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

/**
 * Created by phima on 4/26/2017.
 */

public final class AnimatorObservable extends Observable<Object> {
    private final Animator animator;
    public AnimatorObservable(Animator animator) {
        this.animator = animator;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
       /* if (!Preconditions.checkMainThread(observer)) {
            return;
        }*/
        Listener listener = new Listener(animator, observer);
        observer.onSubscribe(listener);
        animator.addListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements Animator.AnimatorListener{
        private final Animator animator;
        private final Observer<? super Object> observer;

        public Listener(Animator animator, Observer<? super Object> observer) {
            this.animator = animator;
            this.observer = observer;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            if(!isDisposed()){
                observer.onNext(AnimatorLifecycle.START);
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(!isDisposed()){
                observer.onComplete();
                this.dispose();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            if(!isDisposed()){
                observer.onNext(AnimatorLifecycle.CANCEL);
            }
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            if(!isDisposed()){
                observer.onNext(AnimatorLifecycle.REPEAT);
            }
        }

        @Override
        protected void onDispose() {
            Utils.bugger(this.getClass(), "AnimatorObservable", "disposed");
            animator.removeAllListeners();
            BTApplication.instance.watch(this);
        }
    }
}
