package com.falcotech.mazz.bigtwochampionship.reactive;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.disposables.Disposable;

/**
 * Created by phima on 4/26/2017.
 */

public abstract class BackgroundDisposable implements Disposable{
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    @Override
    public final boolean isDisposed() {
        return unsubscribed.get();
    }

    @Override
    public final void dispose() {
        if (unsubscribed.compareAndSet(false, true)) {
            onDispose();
        }
    }

    protected abstract void onDispose();
}
