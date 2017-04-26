package com.falcotech.mazz.bigtwochampionship.core;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/24/2017.
 */

public abstract class BTOperation<T, Params> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public BTOperation(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    public abstract Observable<T> buildOperationObservable(Params params);

    public void execute(DisposableObserver<T> observer, Params params){
        checkNotNull(observer, "Observer cannot = null");
        final Observable<T> observable = this.buildOperationObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose(){
        if(!disposables.isDisposed()){
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable){
        checkNotNull(disposable, "disposable cannot = null");
        checkNotNull(disposables, "disposables cannot = null");
        disposables.add(disposable);
    }
}
