package com.falcotech.mazz.bigtwochampionship.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 3/29/2017.
 */

final class StringAdapter implements BTPreference.Adapter<String> {
    static final StringAdapter INSTANCE = new StringAdapter();

    @Override public String get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String value = preferences.getString(key, null);
        assert value != null; // Not called unless key is present.
        return value;
    }

    @Override public void set(@NonNull String key, @NonNull String value,
                              @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, value);
    }
}
