package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.google.gson.Gson;


/**
 * Created by phima on 3/29/2017.
 */

public final class GameAdapter implements BTPreference.Adapter<Game> {
    static final GameAdapter INSTANCE = new GameAdapter();


   /* @Override
    public Game deserialize(@io.reactivex.annotations.NonNull String serialized) {
        Gson gson = new Gson();
        return gson.fromJson(serialized, Game.class);
    }

    @Override
    public String serialize(@io.reactivex.annotations.NonNull Game value) {
        Gson gson = new Gson();
        return gson.toJson(value, Game.class);
    }
*/
    @Override
    public Game get(@NonNull String key, @NonNull SharedPreferences preferences) {
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString(key, ""), Game.class);
    }

    @Override
    public void set(@NonNull String key, @NonNull Game value, @NonNull SharedPreferences.Editor editor) {
        Gson gson = new Gson();
        editor.putString(key, gson.toJson(value, Game.class));
    }
}
