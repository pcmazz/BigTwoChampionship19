package com.falcotech.mazz.bigtwochampionship;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import static android.view.Gravity.CENTER;

/**
 * Created by phima on 4/21/2017.
 */

public class TestGameFragment extends BTFragment{

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
        return tv;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.activityTitleController != null){
            this.activityTitleController.setTitle("ROCK HARD COCKS");
        }
    }
}
