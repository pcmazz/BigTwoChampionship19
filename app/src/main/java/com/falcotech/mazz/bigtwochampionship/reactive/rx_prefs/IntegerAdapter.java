package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 3/29/2017.
 */

final class IntegerAdapter implements BTPreference.Adapter<Integer> {
    static final IntegerAdapter INSTANCE = new IntegerAdapter();

    @Override public Integer get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getInt(key, 0);
    }

    @Override public void set(@NonNull String key, @NonNull Integer value,
                              @NonNull SharedPreferences.Editor editor) {
        editor.putInt(key, value);
    }
}