package com.falcotech.mazz.bigtwochampionship.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.falcotech.mazz.bigtwochampionship.R;
import com.falcotech.mazz.bigtwochampionship.TurnRunner;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTFragment;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;

/**
 * Created by phima on 4/26/2017.
 */

public class StageFragment extends BTFragment{
    @Inject
    TurnRunner turnRunner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(GameActivityComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_stage, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //turnRunner.initialize(getView(), 8);
    }

    public void runTurn(int turnNum){
        Utils.bugger(getClass(), "runTurn", "turnNum = " + turnNum);
        turnRunner.initialize(getView(), turnNum);
    }


    private Completable test(){
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                TextView westName = ButterKnife.findById(getView(), R.id.tvNameWest);
                westName.setText("COCK MASTER");
                e.onComplete();
            }
        });

    }
}
