package com.falcotech.mazz.bigtwochampionship;

import com.falcotech.mazz.bigtwochampionship.models.Card;
import com.falcotech.mazz.bigtwochampionship.models.Deck;
import com.falcotech.mazz.bigtwochampionship.models.Game;
import com.falcotech.mazz.bigtwochampionship.models.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by phima on 4/27/2017.
 */

public final class GameFactory {

    public static Game newSoloGame(String gameId, String myUsername){
        Game game = new Game();
        game.setId(gameId);
        game.setGameLive(false);
        game.setRoundLive(false);
        game.setExcluded(true);
        String now = timestamp();
        Deck deck = new Deck();
        game.setPlayer(new Player(myUsername, 1, false, deck.dealPlayer(), 50));
        boolean found = false;
        if(hasStart(game.getPlayer(1))){
            found = true;
            game.setStartPlayerNum(1);
            game.setTurnPlayerNum(1);
        }
        for(int i = 2; i <= 4; i++){
            game.setPlayer(new Player("AI", i, true, deck.dealPlayer(), 50));
            if(!found && hasStart(game.getPlayer(i))){
                found = true;
                game.setStartPlayerNum(i);
                game.setTurnPlayerNum(i);
            }
        }
        game.setTurns(null);
        game.setRoundNum(1);
        game.setCreatedAt(now);
        game.setLastUpdated(now);
        return game;
    }

   private static boolean hasStart(Player player){
       for(Card card : player.getCards()){
           if(card.getNumber() == 3 && card.getSuit() == 1){
               return true;
           }
       }
       return false;
   }

    private static String timestamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
