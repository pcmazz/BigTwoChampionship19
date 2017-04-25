package com.falcotech.mazz.bigtwochampionship.rx_prefs;

import android.content.SharedPreferences;

import io.reactivex.annotations.NonNull;

import static com.falcotech.mazz.bigtwochampionship.Preconditions.checkNotNull;


/**
 * Created by phima on 3/29/2017.
 */

final class ConverterAdapter<T> implements BTPreference.Adapter<T> {
    private final Preference.Converter<T> converter;

    ConverterAdapter(Preference.Converter<T> converter) {
        this.converter = converter;
    }

    @Override public T get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String serialized = preferences.getString(key, null);
        assert serialized != null; // Not called unless key is present.
        T value = converter.deserialize(serialized);
        checkNotNull(value, "Deserialized value must not be null from string: " + serialized);
        return value;
    }

    @Override
    public void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor) {
        String serialized = converter.serialize(value);
        checkNotNull(serialized, "Serialized string must not be null from value: " + value);
        editor.putString(key, serialized);
    }
}
