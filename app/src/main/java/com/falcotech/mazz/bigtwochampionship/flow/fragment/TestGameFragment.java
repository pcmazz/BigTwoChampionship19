package com.falcotech.mazz.bigtwochampionship.flow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.falcotech.mazz.bigtwochampionship.GetGamePresenter;
import com.falcotech.mazz.bigtwochampionship.Utils;
import com.falcotech.mazz.bigtwochampionship.core.BTFragment;
import com.falcotech.mazz.bigtwochampionship.flow.component.GameActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.view.Gravity.CENTER;

/**
 * Created by phima on 4/21/2017.
 * MAIN IDEA NOTES
 * Using dagger2 for structure create discrete references to
 */

public class TestGameFragment extends BTFragment {

    @Inject
    GetGamePresenter getGamePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(GameActivityComponent.class).inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //((BTActivity)this.getActivity()).component().inject(this);
        //activityTitleController.setTitle("COCKS");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setGravity(CENTER);
        tv.setText("Hello, World");
        ButterKnife.bind(this, tv);
        return tv;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.activityTitleController != null){
            this.activityTitleController.setTitle("ROCK HARD COCKS");
        }
        //rhc();
        testRx();
    }

    private void rhc(){
        if(this.getGamePresenter != null){
            getGamePresenter.initialize("");
        }else{
            Log.d("DEBUG", "getGamePresenter = nhull");
        }

    }

    private void testRx(){
        rxSharedPreferences.getString(Utils.DUMMY).asObservable().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Utils.bugger(TestGameFragment.this.getClass(), "testRx", "s = " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
