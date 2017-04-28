package com.falcotech.mazz.bigtwochampionship.flow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.falcotech.mazz.bigtwochampionship.GameFactory;
import com.falcotech.mazz.bigtwochampionship.GetGamePresenter;
import com.falcotech.mazz.bigtwochampionship.R;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTActivity;


import com.falcotech.mazz.bigtwochampionship.flow.component.DaggerGameActivityComponent;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;
import com.falcotech.mazz.bigtwochampionship.flow.HasComponent;

import com.falcotech.mazz.bigtwochampionship.flow.fragment.DatabaseUpkeepFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.GameLogicFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.PlayerHandFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.StageFragment;

import com.falcotech.mazz.bigtwochampionship.models.Deck;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GameActivity extends BTActivity implements HasComponent<GameActivityComponent> {



   @OnClick(R.id.btnGamePass)
   public void submit(){
       //rxSharedPreferences.getString(Utils.PLAYER_CARDS).set("ANUS");
       this.removeFragment(R.id.flHandCards);
       testFrag();
   }

    @OnClick(R.id.btGamePlay)
    public void submit2(){
        //rxSharedPreferences.getString(Utils.DUMMY).set("HEADFUCK");
        this.removeFragment(R.id.flHandCards);
        this.removeFragment("cleanerFrag");

    }


    public static Intent getCallingIntent(Context context){
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }

    private GameActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.initializeInjector();
        ButterKnife.bind(this);
        if(savedInstanceState == null){
            //testFrag();

            soloGame();
        }

    }

    private void soloGame(){
        /*if(getSupportFragmentManager().findFragmentByTag("cleanerFrag") != null){
            this.removeFragment("cleanerFrag");
        }else{
            this.addFragment("cleanerFrag", new DatabaseUpkeepFragment());
        }*/
        this.addFragment("cleanerFrag", new DatabaseUpkeepFragment());
        this.addFragment("gameLogic", new GameLogicFragment());
        String gameId = FirebaseDatabase.getInstance().getReference("games").push().getKey();
        rxSharedPreferences.getString(Utils.GAME_ID).set(gameId);
        rxSharedPreferences.getInteger(Utils.COMPLETED_TURNS).set(0);
        FirebaseDatabase.getInstance().getReference("games").child(gameId).setValue(GameFactory.newSoloGame(gameId, "Falco"));
        this.addFragment(R.id.flStage, new StageFragment());

    }

    public void turnCompleted(int turnCompletedNum){
        Utils.bugger(getClass(), "turnCompleted", "turnCompletedNum = " + turnCompletedNum);
        int nextTurn = turnCompletedNum + 1;
        ((StageFragment)getSupportFragmentManager().findFragmentById(R.id.flStage)).runTurn(nextTurn);
    }



    private void testRx(){

        rxSharedPreferences.getString(Utils.PLAYER_CARDS).asObservable().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Utils.bugger(GameActivity.this.getClass(), "testRx", "s = " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initializeInjector(){
        this.component = DaggerGameActivityComponent.builder()
                .bTApplicationComponent(getApplicationComponent())
                .bTActivityModule(getActivityModule())
                .build();
    }

    private void testFrag(){
        //testRx();
        Deck deck = new Deck();
        this.addFragment(R.id.flHandCards, PlayerHandFragment.initialize(deck.dealPlayer()));
    }


    @Override
    public GameActivityComponent getComponent() {
        return component;
    }
}
