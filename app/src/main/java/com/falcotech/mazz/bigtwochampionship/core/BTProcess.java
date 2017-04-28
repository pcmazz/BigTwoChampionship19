package com.falcotech.mazz.bigtwochampionship.core;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/27/2017.
 */

public abstract class BTProcess<Params> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public BTProcess(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    public abstract Completable buildProcessCompletable(Params params);

    public void execute(Action completeAction, Params params){
        checkNotNull(completeAction, "completeAction cannot = null");
        final Completable completable = this.buildProcessCompletable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(completable.subscribe(completeAction));

    }

    public void dispose(){
        if(!disposables.isDisposed()){
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        checkNotNull(disposable, "disposable cannot = null");
        checkNotNull(disposables, "disposables cannot = null");
        disposables.add(disposable);
    }
}
