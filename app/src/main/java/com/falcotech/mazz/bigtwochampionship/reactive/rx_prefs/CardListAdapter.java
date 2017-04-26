package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;


import com.falcotech.mazz.bigtwochampionship.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phima on 4/14/2017.
 */

public final class CardListAdapter implements BTPreference.Adapter<List<Card>>{
    static final CardListAdapter INSTANCE = new CardListAdapter();
    @Override
    public List<Card> get(@NonNull String key, @NonNull SharedPreferences preferences) {
        String value = preferences.getString(key, null);
        assert value != null;
        return unzip(value);
    }

    @Override
    public void set(@NonNull String key, @NonNull List<Card> value, @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, zip(value));
    }

    private String zip(List<Card> cards){
        if(cards != null && cards.size() > 0){
            String result = cards.get(0).getName();
            for(int i = 1; i < cards.size(); i++){
                result += "~|~" + cards.get(i);
            }
            return result;
        }
        return null;
    }

    private List<Card> unzip(String cardStr){
        List<Card> cards = new ArrayList<>();
        for(String s : cardStr.split("~|~")){
            cards.add(new Card(s));
        }
        return cards;
    }
}
