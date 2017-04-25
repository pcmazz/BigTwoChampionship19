package com.falcotech.mazz.bigtwochampionship;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GameActivity extends BTActivity implements HasComponent<GameActivityComponent>{
   /* @BindView(R.id.tvTest)
    TextView test;
    @OnClick(R.id.btnTest)
    public void submit(){
        test.setText("COCKS");
    }*/
   @OnClick(R.id.btnGameTest)
   public void submit(){
       rxSharedPreferences.getString(Utils.PLAYER_CARDS).set("ANUS");
   }

    @OnClick(R.id.btnGameTest2)
    public void submit2(){
        rxSharedPreferences.getString(Utils.DUMMY).set("HEADFUCK");
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
            testFrag();
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
        testRx();
        this.addFragment(android.R.id.content, new TestGameFragment());
    }


    @Override
    public GameActivityComponent getComponent() {
        return component;
    }
}
