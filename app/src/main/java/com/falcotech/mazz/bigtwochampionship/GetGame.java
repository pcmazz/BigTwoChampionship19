package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.core.BTOperation;
import com.falcotech.mazz.bigtwochampionship.core.PostExecutionThread;
import com.falcotech.mazz.bigtwochampionship.core.ThreadExecutor;
import com.falcotech.mazz.bigtwochampionship.models.Game;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.falcotech.mazz.bigtwochampionship.reactive.Preconditions.checkNotNull;

/**
 * Created by phima on 4/24/2017.
 */

public class GetGame extends BTOperation<Game, GetGame.Params> {
    private final GameRepository gameRepository;

    @Inject
    public GetGame(GameRepository gameRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.gameRepository = gameRepository;
    }

    @Override
    public Observable<Game> buildOperationObservable(Params params) {
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
