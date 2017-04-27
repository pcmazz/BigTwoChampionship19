package com.falcotech.mazz.bigtwochampionship.reactive;

import android.animation.Animator;
import android.support.annotation.CheckResult;
import android.view.animation.Animation;

import com.falcotech.mazz.bigtwochampionship.custom_views.CardView;
import com.falcotech.mazz.bigtwochampionship.custom_views.HandView;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.AnimationObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.AnimatorObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.CardViewObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.HandViewObservable;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 4/26/2017.
 */

public final class RxUtils {

    @CheckResult
    @NonNull
    public static Observable<Object> animationUpdate(@NonNull Animation animation){
        Preconditions.checkNotNull(animation, "animation = null");
        return new AnimationObservable(animation);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> animatorUpdate(@NonNull Animator animator){
        Preconditions.checkNotNull(animator, "animator = null");
        return new AnimatorObservable(animator);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> cardViewReady(@NonNull CardView cardView){
        Preconditions.checkNotNull(cardView, "cardView = null");
        return new CardViewObservable(cardView);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> handDealCompleted(@NonNull HandView handView){
        Preconditions.checkNotNull(handView, "handView = null");
        return new HandViewObservable(handView);
    }
}
