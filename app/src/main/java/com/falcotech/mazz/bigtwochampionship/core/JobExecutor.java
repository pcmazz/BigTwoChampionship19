package com.falcotech.mazz.bigtwochampionship.core;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by phima on 4/21/2017.
 */

@Singleton
public class JobExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    JobExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new JobThreadFactory());
    }

    @Override public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_" + counter++);
        }
    }
}
