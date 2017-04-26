package com.falcotech.mazz.bigtwochampionship.reactive.rx_prefs;

import android.content.SharedPreferences;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by phima on 3/29/2017.
 */

public final class BTPreference<T> implements Preference<T> {
    /** Stores and retrieves instances of {@code T} in {@link SharedPreferences}. */
     interface Adapter<T> {
        /** Retrieve the value for {@code key} from {@code preferences}. */
        T get(@NonNull String key, @NonNull SharedPreferences preferences);

        /**
         * Store non-null {@code value} for {@code key} in {@code editor}.
         * <p>
         * Note: Implementations <b>must not</b> call {@code commit()} or {@code apply()} on
         * {@code editor}.
         */
        void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor);
    }


    private final SharedPreferences preferences;
    private final String key;
    private final T defaultValue;
    private final Adapter<T> adapter;
    private final Observable<T> values;

    BTPreference(SharedPreferences preferences, String keyValue, T defaultValue, Adapter<T> adapter, Observable<String> keyChanges) {
        this.preferences = preferences;
        this.key = keyValue;
        this.defaultValue = defaultValue;
        this.adapter = adapter;
        this.values = keyChanges //
                .filter(new Predicate<String>() {
                    @Override public boolean test(String changedKey) throws Exception {
                        return key.equals(changedKey);
                    }
                }) //
                .startWith("<init>") // Dummy value to trigger initial load.
                .map(new Function<String, T>() {
                    @Override public T apply(String s) throws Exception {
                        return get();
                    }
                });
    }

    @Override @NonNull
    public String key() {

        return key;
    }

    @Override @NonNull
    public T defaultValue() {
        return defaultValue;
    }

    @Override @NonNull
    public synchronized T get() {
        //Utils.bugger(getClass(), "get", "key = " + key + "\ncontains = " + preferences.contains(key));
        if (!preferences.contains(key)) {
            return defaultValue;
        }
        //Utils.bugger(getClass(), "get", "adaptor get = " + adapter.get(key, preferences));
        return adapter.get(key, preferences);
    }

    @Override public synchronized void set(@Nullable T value) {
        SharedPreferences.Editor editor = preferences.edit();
        if (value == null) {
            editor.remove(key);
        } else {
            adapter.set(key, value, editor);
        }
        editor.apply();
    }

    @Override public boolean isSet() {
        return preferences.contains(key);
    }

    @Override public void delete() {
        set(null);
    }

    @Override @CheckResult
    @NonNull
    public Observable<T> asObservable() {
        return values;
    }

    @Override @CheckResult
    @NonNull
    public Consumer<? super T> asConsumer() {
        return new Consumer<T>() {
            @Override public void accept(T value) throws Exception {
                set(value);
            }
        };
    }
}