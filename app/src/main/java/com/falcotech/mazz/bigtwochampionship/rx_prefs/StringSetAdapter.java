package com.falcotech.mazz.bigtwochampionship.rx_prefs;

import android.annotation.TargetApi;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

import io.reactivex.annotations.NonNull;

import static android.os.Build.VERSION_CODES.HONEYCOMB;

/**
 * Created by phima on 3/29/2017.
 */

@TargetApi(HONEYCOMB)
final class StringSetAdapter implements BTPreference.Adapter<Set<String>> {
    static final StringSetAdapter INSTANCE = new StringSetAdapter();

    @Override public Set<String> get(@NonNull String key, @NonNull SharedPreferences preferences) {
        Set<String> value = preferences.getStringSet(key, null);
        assert value != null; // Not called unless key is present.
        return Collections.unmodifiableSet(value);
    }

    @Override public void set(@NonNull String key, @NonNull Set<String> value,
                              @NonNull SharedPreferences.Editor editor) {
        editor.putStringSet(key, value);
    }
}
