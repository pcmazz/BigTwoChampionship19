package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.core.BTOperation;
import com.falcotech.mazz.bigtwochampionship.core.PostExecutionThread;
import com.falcotech.mazz.bigtwochampionship.core.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/27/2017.
 */

public class WatchTurns extends BTOperation<Integer, WatchTurns.Params>{
    private final RxPrefRepository rxPrefRepository;

    @Inject
    public WatchTurns(RxPrefRepository rxPrefRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.rxPrefRepository = rxPrefRepository;
    }

    @Override
    public Observable<Integer> buildOperationObservable(Params params) {
        checkNotNull(params, "params cannot = null");
        return this.rxPrefRepository.completedTurns();
    }

    public static final class Params{
        public Params() {
        }
        public static Params forPrefs(){
            return new Params();
        }
    }
}
