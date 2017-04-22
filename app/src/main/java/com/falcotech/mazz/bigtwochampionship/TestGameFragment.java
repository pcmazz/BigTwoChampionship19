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
    @Inject
    ActivityTitleController titleController;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((GameActivity)getActivity()).addFragment(android.R.id.content, this);
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
        titleController.setTitle("ROCK HARD COCKS");
    }
}
