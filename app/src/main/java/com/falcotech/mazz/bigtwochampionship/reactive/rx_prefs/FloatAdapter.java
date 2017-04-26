package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 3/29/2017.
 */

final class FloatAdapter implements BTPreference.Adapter<Float> {
    static final FloatAdapter INSTANCE = new FloatAdapter();

    @Override public Float get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getFloat(key, 0f);
    }

    @Override public void set(@NonNull String key, @NonNull Float value,
                              @NonNull SharedPreferences.Editor editor) {
        editor.putFloat(key, value);
    }
}
