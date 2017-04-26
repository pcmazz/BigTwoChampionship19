package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.falcotech.mazz.bigtwochampionship.models.Card;


/**
 * Created by phima on 4/14/2017.
 */

public final class CardAdapter implements BTPreference.Adapter<Card>{
    static final CardAdapter INSTANCE = new CardAdapter();
    @Override
    public Card get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String value = preferences.getString(key, null);
        assert value != null;
        return new Card(value);
    }

    @Override
    public void set(@NonNull String key, @NonNull Card value, @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, value.getName());
    }
}
