package com.falcotech.mazz.bigtwochampionship;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Emitter;
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
        return testObservable();
    }

    @Override
    public Observable<List<Game>> games() {
        return null;
    }

    private Observable<Game> testObservable(){
       return Observable.create(new ObservableOnSubscribe<Game>() {
           @Override
           public void subscribe(final ObservableEmitter<Game> e) throws Exception {

               DatabaseReference ref = FirebaseDatabase.getInstance().getReference("games").child("Game~0");
               ref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       Game game = dataSnapshot.getValue(Game.class);
                       e.onNext(game);
                       e.onComplete();
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
              /* Game game = new Game();
               game.setGameLive(true);
               game.setRoundLive(true);
               game.setExcluded(false);
               e.onNext(game);
               e.onComplete();*/
           }
       });
    }
}
