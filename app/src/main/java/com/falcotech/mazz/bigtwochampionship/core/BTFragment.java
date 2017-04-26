package com.falcotech.mazz.bigtwochampionship.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.falcotech.mazz.bigtwochampionship.ActivityTitleController;
import com.falcotech.mazz.bigtwochampionship.flow.HasComponent;
import com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs.RxSharedPreferences;

import javax.inject.Inject;

/**
 * Created by phima on 4/21/2017.
 */

public abstract class BTFragment extends Fragment{
    @Inject
    protected ActivityTitleController activityTitleController;

    @Inject
    protected RxSharedPreferences rxSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("DEBUG", "BTFragment injecting");

    }

    private void daggerInitialize(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
