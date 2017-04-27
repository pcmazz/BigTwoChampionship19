package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.reactive.RxUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by phima on 4/24/2017.
 */

@Singleton
public class GameDataRepository implements GameRepository{

    @Inject
    public GameDataRepository() {
    }

    @Override
    public Observable<Game> game(String gameId) {
        Utils.bugger(getClass(), "game", "gameId = " + gameId);
        return RxUtils.getGameUpdate(FirebaseDatabase.getInstance().getReference("games").child(gameId));
    }

    @Override
    public Observable<Game> games() {
        return RxUtils.getGamesUpdate(FirebaseDatabase.getInstance().getReference("games"));
    }

}
