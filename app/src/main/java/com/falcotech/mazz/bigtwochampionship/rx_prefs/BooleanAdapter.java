package com.falcotech.mazz.bigtwochampionship.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 3/29/2017.
 */

final class BooleanAdapter implements BTPreference.Adapter<Boolean> {
    static final BooleanAdapter INSTANCE = new BooleanAdapter();

    @Override public Boolean get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getBoolean(key, false);
    }

    @Override public void set(@NonNull String key, @NonNull Boolean value,
                              @NonNull SharedPreferences.Editor editor) {
        editor.putBoolean(key, value);
    }
}
