package com.falcotech.mazz.bigtwochampionship.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

/**
 * Created by phima on 3/29/2017.
 */

final class EnumAdapter<T extends Enum<T>> implements BTPreference.Adapter<T> {
    private final Class<T> enumClass;

    EnumAdapter(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override public T get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String value = preferences.getString(key, null);
        assert value != null; // Not called unless key is present.
        return Enum.valueOf(enumClass, value);
    }

    @Override
    public void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, value.name());
    }
}