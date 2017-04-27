package com.falcotech.mazz.bigtwochampionship.flow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.falcotech.mazz.bigtwochampionship.R;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTActivity;
import com.falcotech.mazz.bigtwochampionship.flow.component.DaggerGameActivityComponent;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;
import com.falcotech.mazz.bigtwochampionship.flow.HasComponent;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.PlayerCardFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.PlayerHandFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.StageFragment;
import com.falcotech.mazz.bigtwochampionship.flow.fragment.TestGameFragment;
import com.falcotech.mazz.bigtwochampionship.models.Deck;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GameActivity extends BTActivity implements HasComponent<GameActivityComponent> {
   /* @BindView(R.id.tvTest)
    TextView test;
    @OnClick(R.id.btnTest)
    public void submit(){
        test.setText("COCKS");
    }*/


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
            this.addFragment(R.id.flStage, new StageFragment());
        }

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
