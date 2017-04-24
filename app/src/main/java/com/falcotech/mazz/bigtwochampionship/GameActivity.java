package com.falcotech.mazz.bigtwochampionship;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

public class GameActivity extends BTActivity implements HasComponent<GameActivityComponent>{


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
        if(savedInstanceState == null){
            testFrag();
        }

    }

    private void initializeInjector(){
        this.component = DaggerGameActivityComponent.builder()
                .bTApplicationComponent(getApplicationComponent())
                .bTActivityModule(getActivityModule())
                .build();
    }

    private void testFrag(){
        this.addFragment(android.R.id.content, new TestGameFragment());
    }


    @Override
    public GameActivityComponent getComponent() {
        return component;
    }
}
