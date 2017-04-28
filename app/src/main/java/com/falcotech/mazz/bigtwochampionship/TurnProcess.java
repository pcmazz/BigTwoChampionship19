package com.falcotech.mazz.bigtwochampionship;

import android.view.View;

import com.falcotech.mazz.bigtwochampionship.core.BTProcess;
import com.falcotech.mazz.bigtwochampionship.core.PostExecutionThread;
import com.falcotech.mazz.bigtwochampionship.core.ThreadExecutor;
import com.falcotech.mazz.bigtwochampionship.models.Game;

import javax.inject.Inject;

import io.reactivex.Completable;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/27/2017.
 */

public class TurnProcess extends BTProcess<TurnProcess.Params>{
    private final TurnFactory turnFactory;

    @Inject
    public TurnProcess(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TurnFactory turnFactory) {
        super(threadExecutor, postExecutionThread);
        this.turnFactory = turnFactory;
    }

    @Override
    public Completable buildProcessCompletable(Params params) {
        checkNotNull(params, "params cannot = null");
        return this.turnFactory.turn(params.view, params.turnNum);
    }

    public static final class Params{
        private final View view;
        private final int turnNum;


        public Params(View view, int turnNum) {
            this.view = view;
            this.turnNum = turnNum;

        }

        public static Params forTurn(View stage, int turnNum){
            return new Params(stage, turnNum);
        }
    }
}
