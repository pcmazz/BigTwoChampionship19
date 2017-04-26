package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;


import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Cancellable;

import static android.os.Build.VERSION_CODES.HONEYCOMB;
import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;



/**
 * Created by phima on 3/29/2017.
 */
@Singleton
public final class RxSharedPreferences {
    private static final Float DEFAULT_FLOAT = 0f;
    private static final Integer DEFAULT_INTEGER = 0;
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Long DEFAULT_LONG = 0L;
    private static final String DEFAULT_STRING = "";
    private static final Game DEFAULT_GAME = new Game();

    /** Create an instance of {@link RxSharedPreferences} for {@code preferences}. */
    @CheckResult
    @NonNull
    public static RxSharedPreferences create(@NonNull SharedPreferences preferences) {
        checkNotNull(preferences, "preferences == null");
        Utils.bugger(RxSharedPreferences.class, "create", "injecting");
        return new RxSharedPreferences(preferences);
    }

    private final SharedPreferences preferences;
    private final Observable<String> keyChanges;

    private RxSharedPreferences(final SharedPreferences preferences) {
        this.preferences = preferences;
        this.keyChanges = Observable.create(new ObservableOnSubscribe<String>() {
            @Override public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                final SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
                        emitter.onNext(key);
                    }
                };

                emitter.setCancellable(new Cancellable() {
                    @Override public void cancel() throws Exception {
                        preferences.unregisterOnSharedPreferenceChangeListener(listener);
                    }
                });

                preferences.registerOnSharedPreferenceChangeListener(listener);
            }
        }).share();
    }

    /** Create a boolean preference for {@code key}. Default is {@code false}. */
    @CheckResult
    @NonNull
    public Preference<Boolean> getBoolean(@NonNull String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    /** Create a boolean preference for {@code key} with a default of {@code defaultValue}. */
    @CheckResult
    @NonNull
    public Preference<Boolean> getBoolean(@NonNull String key, @NonNull Boolean defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, BooleanAdapter.INSTANCE, keyChanges);
    }

    /** Create an enum preference for {@code key} with a default of {@code defaultValue}. */
    @CheckResult
    @NonNull
    public <T extends Enum<T>> Preference<T> getEnum(@NonNull String key, @NonNull T defaultValue,
                                                     @NonNull Class<T> enumClass) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        checkNotNull(enumClass, "enumClass == null");
        return new BTPreference<>(preferences, key, defaultValue, new EnumAdapter<>(enumClass), keyChanges);
    }

    /** Create a float preference for {@code key}. Default is {@code 0}. */
    @CheckResult
    @NonNull
    public Preference<Float> getFloat(@NonNull String key) {
        return getFloat(key, DEFAULT_FLOAT);
    }

    /** Create a float preference for {@code key} with a default of {@code defaultValue}. */
    @CheckResult
    @NonNull
    public Preference<Float> getFloat(@NonNull String key, @NonNull Float defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, FloatAdapter.INSTANCE, keyChanges);
    }

    /** Create an integer preference for {@code key}. Default is {@code 0}. */
    @CheckResult
    @NonNull
    public Preference<Integer> getInteger(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getInteger(key, DEFAULT_INTEGER);
    }

    /** Create an integer preference for {@code key} with a default of {@code defaultValue}. */
    @CheckResult
    @NonNull
    public Preference<Integer> getInteger(@NonNull String key, @NonNull Integer defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, IntegerAdapter.INSTANCE, keyChanges);
    }

    /** Create a long preference for {@code key}. Default is {@code 0}. */
    @CheckResult
    @NonNull
    public Preference<Long> getLong(@NonNull String key) {
        //noinspection UnnecessaryBoxing
        return getLong(key, DEFAULT_LONG);
    }

    /** Create a long preference for {@code key} with a default of {@code defaultValue}. */
    @CheckResult
    @NonNull
    public Preference<Long> getLong(@NonNull String key, @NonNull Long defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, LongAdapter.INSTANCE, keyChanges);
    }

    /**
     * Create a preference for type {@code T} for {@code key} with a default of {@code defaultValue}.
     */
    @CheckResult
    @NonNull public <T> Preference<T> getObject(@NonNull String key,
                                                @Nullable T defaultValue, @NonNull Preference.Converter<T> converter) {
        checkNotNull(key, "key == null");
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        checkNotNull(converter, "converter == null");
        return new BTPreference<>(preferences, key, defaultValue,
                new ConverterAdapter<>(converter), keyChanges);
    }

    /** Create a string preference for {@code key}. Default is {@code ""}. */
    @CheckResult
    @NonNull
    public Preference<String> getString(@NonNull String key) {
        return getString(key, DEFAULT_STRING);
    }

    /** Create a string preference for {@code key} with a default of {@code defaultValue}. */
    @CheckResult
    @NonNull
    public Preference<String> getString(@NonNull String key, @NonNull String defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, StringAdapter.INSTANCE, keyChanges);
    }

    /**
     * Create a string set preference for {@code key}. Default is an empty set. Note that returned set
     * value will always be unmodifiable.
     */
    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public Preference<Set<String>> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /** Create a string set preference for {@code key} with a default of {@code defaultValue}. */
    @RequiresApi(HONEYCOMB)
    @CheckResult
    @NonNull
    public Preference<Set<String>> getStringSet(@NonNull String key,
                                                @NonNull Set<String> defaultValue) {
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, StringSetAdapter.INSTANCE, keyChanges);
    }

    public Preference<Game> getGame(@NonNull String key, @NonNull Game defaultValue){
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, GameAdapter.INSTANCE, keyChanges);
    }

    public Preference<Game> getGame(@NonNull String key){
        checkNotNull(key, "key == null");
        return new BTPreference<>(preferences, key, DEFAULT_GAME, GameAdapter.INSTANCE, keyChanges);
    }

    public Preference<List<Card>> getCardList(@NonNull String key, @NonNull List<Card> defaultValue){
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, CardListAdapter.INSTANCE, keyChanges);
    }

    public Preference<List<Card>> getCardList(@NonNull String key){
        checkNotNull(key, "key == null");
        return new BTPreference<>(preferences, key, null, CardListAdapter.INSTANCE, keyChanges);
    }

   /* public Preference<List<PlayerCardLog>> getPlayerCardLogList(@NonNull String key, @NonNull List<PlayerCardLog> defaultValue){
        checkNotNull(key, "key == null");
        checkNotNull(defaultValue, "defaultValue == null");
        return new BTPreference<>(preferences, key, defaultValue, PlayerCardLogListAdapter.INSTANCE, keyChanges);
    }

    public Preference<List<PlayerCardLog>> getPlayerCardLogList(@NonNull String key){
        checkNotNull(key, "key == null");
        return new BTPreference<>(preferences, key, null, PlayerCardLogListAdapter.INSTANCE, keyChanges);
    }*/
}
