package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.falcotech.mazz.bigtwochampionship.models.PlayerCardLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phima on 4/27/2017.
 */

public final class PlayerCardLogListAdapter implements BTPreference.Adapter<List<PlayerCardLog>>{
    static final PlayerCardLogListAdapter INSTANCE = new PlayerCardLogListAdapter();
    @Override
    public List<PlayerCardLog> get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String value = preferences.getString(key, null);
        assert value != null;
        return unzip(value);
    }

    @Override
    public void set(@NonNull String key, @NonNull List<PlayerCardLog> value, @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, zip(value));
    }

    private String zip(List<PlayerCardLog> logs){
        if(logs != null && logs.size() > 0){
            String result = logs.get(0).zip();
            for(int i = 1; i < logs.size(); i++){
                result += "~|~" + logs.get(i).zip();
            }
            return result;
        }
        return null;
    }

    private List<PlayerCardLog> unzip(String logStr){

        List<PlayerCardLog> logs = new ArrayList<>();
        for(String s : logStr.split("~\\|~")){
            logs.add(new PlayerCardLog(s));
        }
        return logs;
    }
}
