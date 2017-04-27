package com.falcotech.mazz.bigtwochampionship.reactive;

import android.animation.Animator;
import android.support.annotation.CheckResult;
import android.view.animation.Animation;

import com.falcotech.mazz.bigtwochampionship.custom_views.CardView;
import com.falcotech.mazz.bigtwochampionship.custom_views.HandView;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.AnimationObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.AnimatorObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.CardViewObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.DatabaseObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.GetGameObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.GetGamesObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.HandViewObservable;
import com.falcotech.mazz.bigtwochampionship.reactive.observables.SingleValueDatabaseObservable;
import com.google.firebase.database.DatabaseReference;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/26/2017.
 */

public final class RxUtils {

    @CheckResult
    @NonNull
    public static Observable<Game> getGamesUpdate(@NonNull DatabaseReference databaseReference){
        checkNotNull(databaseReference, "databaseReference = null");
        return new GetGamesObservable(databaseReference);
    }

    @CheckResult
    @NonNull
    public static Observable<Game> getGameUpdate(@NonNull DatabaseReference databaseReference){
        checkNotNull(databaseReference, "databaseReference = null");
        return new GetGameObservable(databaseReference);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> databaseUpdate(@NonNull DatabaseReference databaseReference){
        checkNotNull(databaseReference, "databaseReference = null");
        return new DatabaseObservable(databaseReference);
    }

    @CheckResult
    @NonNull
    public static Observable<Object> singleValueDatabaseUpdate(@NonNull DatabaseReference databaseReference){
        checkNotNull(databaseReference, "databaseReference = null");
        return new SingleValueDatabaseObservable(databaseReference);
    }

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
