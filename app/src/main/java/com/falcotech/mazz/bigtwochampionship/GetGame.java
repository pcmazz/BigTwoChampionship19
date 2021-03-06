package com.falcotech.mazz.bigtwochampionship;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.falcotech.mazz.bigtwochampionship.Preconditions.checkNotNull;

/**
 * Created by phima on 4/24/2017.
 */

public class GetGame extends BTOperation<Game, GetGame.Params>{
    private final GameRepository gameRepository;

    @Inject
    public GetGame(GameRepository gameRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.gameRepository = gameRepository;
    }

    @Override
    Observable<Game> buildOperationObservable(Params params) {
        checkNotNull(params, "params cannot = null");
        return this.gameRepository.game(params.gameId);
    }

    public static final class Params{
        private final String gameId;

        public Params(String gameId) {
            this.gameId = gameId;
        }

        public static Params forGame(String gameId){
            return new Params(gameId);
        }
    }
}
