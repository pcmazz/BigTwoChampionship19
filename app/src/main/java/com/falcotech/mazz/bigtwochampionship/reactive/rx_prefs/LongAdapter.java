package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 3/29/2017.
 */

final class LongAdapter implements BTPreference.Adapter<Long> {
    static final LongAdapter INSTANCE = new LongAdapter();

    @Override public Long get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getLong(key, 0L);
    }

    @Override public void set(@NonNull String key, @NonNull Long value,
                              @NonNull SharedPreferences.Editor editor) {
        editor.putLong(key, value);
    }
}