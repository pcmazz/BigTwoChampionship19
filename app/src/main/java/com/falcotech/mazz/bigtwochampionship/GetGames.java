package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.core.BTOperation;
import com.falcotech.mazz.bigtwochampionship.core.PostExecutionThread;
import com.falcotech.mazz.bigtwochampionship.core.ThreadExecutor;
import com.falcotech.mazz.bigtwochampionship.models.Game;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/27/2017.
 */

public class GetGames extends BTOperation<Game, GetGames.Params> {
    private final GameRepository gameRepository;

    @Inject
    public GetGames(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, GameRepository gameRepository) {
        super(threadExecutor, postExecutionThread);
        this.gameRepository = gameRepository;
    }

    @Override
    public Observable<Game> buildOperationObservable(Params params) {
        checkNotNull(params, "params cannot = null");
        return this.gameRepository.games();
    }

    public static final class Params{


        public Params() {

        }

        public static Params forGames(){
            return new Params();
        }
    }
}
