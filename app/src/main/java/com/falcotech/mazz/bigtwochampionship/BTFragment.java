package com.falcotech.mazz.bigtwochampionship;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by phima on 4/21/2017.
 */

public abstract class BTFragment extends Fragment{
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
