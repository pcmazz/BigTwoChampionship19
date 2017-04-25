package com.falcotech.mazz.bigtwochampionship.rx_prefs;

/**
 * Created by phima on 3/29/2017.
 */

import android.support.annotation.CheckResult;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;


public interface Preference<T> {

    interface Converter<T> {

        @NonNull T deserialize(@NonNull String serialized);


        @NonNull String serialize(@NonNull T value);
    }

    /** The key for which this preference will store and retrieve values. */
    @NonNull String key();

    /** The value used if none is stored. */
    @NonNull T defaultValue();

    /**
     * Retrieve the current value for this preference. Returns {@link #defaultValue()} if no value is
     * set.
     */
    @NonNull
    T get();

    /**
     * Change this preference's stored value to {@code value}. A value of {@code null} will delete
     * the preference.
     */
    void set(@Nullable T value);

    /** Returns true if this preference has a stored value. */
    boolean isSet();

    /** Delete the stored value for this preference, if any. */
    void delete();

    /**
     * Observe changes to this preference. The current value or {@link #defaultValue()} will be
     * emitted on first subscribe.
     */
    @CheckResult
    @NonNull
    Observable<T> asObservable();

    /**
     * An action which stores a new value for this preference. Passing {@code null} will delete the
     * preference.
     */
    @CheckResult
    @NonNull
    Consumer<? super T> asConsumer();
}