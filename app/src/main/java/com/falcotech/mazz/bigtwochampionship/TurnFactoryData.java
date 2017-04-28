package com.falcotech.mazz.bigtwochampionship;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.RxUtils;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by phima on 4/27/2017.
 */
@Singleton
public class TurnFactoryData implements TurnFactory{
    @Inject
    RxSharedPreferences rxSharedPreferences;

    @Inject
    public TurnFactoryData() {
    }

    @Override
    public Completable turn(View stage, int turnNum){
        return test(stage, turnNum);
    }

    private Completable test(final View stage, int turnNum){
        return Completable.concatArray(delay(500),
                moveChip(ButterKnife.findById(stage, R.id.ivChipWest), ButterKnife.findById(stage, R.id.ivChipNorth)),
                finishTurn(turnNum));
        /*return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                Utils.bugger(TurnFactoryData.class, "test", "gameId = " + rxSharedPreferences.getString(Utils.GAME_ID).get());
                RxUtils.getGameUpdate(FirebaseDatabase.getInstance().getReference("games").child(rxSharedPreferences.getString(Utils.GAME_ID).get())).subscribe(new Consumer<Game>() {
                    @Override
                    public void accept(@NonNull Game game) throws Exception {
                        TextView westName = ButterKnife.findById(stage, R.id.tvNameWest);
                        westName.setText(game.getPlayer(1).getName());
                        e.onComplete();
                    }
                });

            }
        });*/
    }

    private Completable finishTurn(final int turnNum){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                rxSharedPreferences.getInteger(Utils.COMPLETED_TURNS).set(turnNum);
            }
        });
    }

    private Completable delay(int delay){
        return Completable.timer(delay, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread());
    }

    private Completable moveChip(View hideView, View showView){
        return Completable.mergeArray(showChip(showView), hideChip(hideView));
    }

    private Completable showChip(View view){
        final Animator animator = fadeInAnim(view);
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                RxUtils.animatorUpdate(animator).ignoreElements()

                        .subscribe(new Action() {
                            @Override
                            public void run() throws Exception {
                                e.onComplete();
                            }
                        });
                animator.start();
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    private Completable hideChip(View view){
        final Animator animator = fadeOutAnim(view);
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(final CompletableEmitter e) throws Exception {
                RxUtils.animatorUpdate(animator).ignoreElements()

                        .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        e.onComplete();
                    }
                });
                animator.start();
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    private Animator fadeInAnim(View view){
        return ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                .setDuration(500);
    }
    private Animator fadeOutAnim(View view){
        return ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
                .setDuration(500);
    }
}
