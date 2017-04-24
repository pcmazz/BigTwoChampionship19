package com.falcotech.mazz.bigtwochampionship;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phima on 4/24/2017.
 */

public class Hand {
    private List<Card> cards;
    private int handSize;

    public Hand() {
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
        handSize = cards.size();
    }

    @Override
    protected void finalize() throws Throwable {
        if(cards != null){
            for(Card card : cards){
                card.finalize();
            }
        }
        cards = null;
        super.finalize();
    }

    @Exclude
    public boolean isGreater(Hand hand) {
        if (isFiveCardHand() && validCompare(hand)) {
            if (isStraightFlush()) {
                if (hand.isStraightFlush()) {
                    return betterStraightFlush(hand);
                } else {
                    return true;
                }
            } else if (isFourKind()) {
                if (hand.isStraightFlush()) {
                    return false;
                } else if (hand.isFourKind()) {
                    return betterThreeOrFour(hand);
                } else {
                    return true;
                }
            } else if (isFullHouse()) {
                if (hand.isStraightFlush() || hand.isFourKind()) {
                    return false;
                } else if (hand.isFullHouse()) {
                    return betterThreeOrFour(hand);
                } else {
                    return true;
                }
            } else if (isFlush()) {
                if (hand.isStraightFlush() || hand.isFourKind() || hand.isFullHouse()) {
                    return false;
                } else if (hand.isFlush()) {
                    return betterFlush(hand);
                } else {
                    return true;
                }
            } else {
                if (hand.isFlush() || hand.isFullHouse() || hand.isFourKind() || hand.isStraightFlush()) {
                    return false;
                }
            }
        } else if (validCompare(hand)) {
            return hasHighCard(hand);
        }
        return false;
    }

    @Exclude
    public boolean contains(List<Card> testCards) {
        for (Card c : testCards) {
            if (!cardInHand(c)) {
                return false;
            }
        }
        return true;
    }

    @Exclude
    private boolean cardInHand(Card c) {
        for (Card card : cards) {
            if (card.sameCard(c)) {
                return true;
            }
        }
        return false;
    }

    @Exclude
    public boolean sameHand(Hand hand) {
        List<Card> me = sortHighToLow(cards);
        List<Card> them = sortHighToLow(hand.getCards());
        boolean same = true;
        int i = 0;
        while (same) {
            if (!me.get(i).sameCard(them.get(i))) {
                same = false;
            } else {
                if (i < handSize - 1) {
                    i++;
                } else {
                    break;
                }
            }
        }
        return same;
    }

    @Exclude
    public boolean validCompare(Hand hand) {
        if (isFiveCardHand() && hand.isFiveCardHand()) {
            return true;
        } else if (isTrip() && hand.isTrip()) {
            return true;
        } else if (isPair() && hand.isPair()) {
            return true;
        } else if (handSize == 1 && hand.getCards().size() == 1) {
            return true;
        }
        return false;
    }


    @Exclude
    private boolean betterStraightFlush(Hand hand) {
        Card myHigh = getHighestCard(cards);
        Card theirHigh = getHighestCard(hand.getCards());
        if (myHigh.sameNumber(theirHigh)) {
            return betterFlush(hand);
        } else {
            return myHigh.isGreater(theirHigh);
        }
    }

    @Exclude
    private boolean betterThreeOrFour(Hand hand) {
        int myThree = getTripNumber(cards);
        int theirThree = getTripNumber(hand.getCards());
        if (myThree > theirThree) {
            return true;
        }
        return false;
    }

    @Exclude
    private boolean betterFlush(Hand hand) {
        if (cards.get(0).sameSuit(hand.getCards().get(0))) {
            return hasHighCard(hand);
        } else {
            if (cards.get(0).getSuit() > hand.getCards().get(0).getSuit()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Exclude
    private boolean hasHighCard(Hand hand) {
        Card myHigh = getHighestCard(cards);
        Card theirHigh = hand.getCards().get(0);
        return myHigh.isGreater(theirHigh);
    }

    @Exclude
    public boolean hasThreeD() {
        for (Card card : cards) {
            if (card.getName().equals("three_diamonds")) {
                //Utils.bugger("Hand", "hasThreeD", "returning true ");
                return true;
            }
        }
        return false;
    }

    @Exclude
    public boolean isPair() {
        if (handSize == 2) {
            if (cards.get(0).sameNumber(cards.get(1))) {
                return true;
            }
        }
        return false;
    }

    @Exclude
    public boolean isTrip() {
        if (handSize == 3) {
            if (cards.get(0).sameNumber(cards.get(1)) && cards.get(0).sameNumber(cards.get(2))) {
                return true;
            }
        }
        return false;
    }

    @Exclude
    public boolean isFiveCardHand() {
        if (isStraight() || isFlush() || isFullHouse() || isFourKind()) {
            return true;
        }
        return false;
    }

    @Exclude
    public boolean validHand() {
        if (handSize == 1 || isPair() || isTrip() || isFiveCardHand()) {
            return true;
        }
        return false;
    }

    @Exclude
    public boolean isStraight() {
        List<Card> tempCards = sortHighToLow(cards);
        if (handSize == 5) {
            for (int i = 1; i < 5; i++) {
                if (tempCards.get(i - 1).getNumber() - tempCards.get(i).getNumber() != 1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Exclude
    public boolean isFlush() {
        if (handSize == 5) {
            int suit = cards.get(0).getSuit();
            for (int i = 1; i < 5; i++) {
                if (cards.get(i).getSuit() != suit) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Exclude
    public boolean isFullHouse() {
        if (handSize == 5) {
            int threeNum = getTripNumber(cards);
            if (threeNum > 0) {
                List<Card> temp = new ArrayList<>();
                for (Card val : cards) {
                    if (val.getNumber() != threeNum) {
                        temp.add(val);
                    }
                    if (temp.size() == 2) {
                        if (temp.get(0).sameNumber(temp.get(1))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Exclude
    public boolean isFourKind() {
        if (handSize == 5) {
            for (int i = 0; i < 5; i++) {
                int pick = cards.get(i).getNumber();
                int count = 0;
                for (int ii = 0; ii < 5; ii++) {
                    if (cards.get(ii).getNumber() == pick) {
                        count++;
                    }
                    if (count == 4) {
                        return true;
                    }
                }
                if (count != 1) {
                    return false;
                }
            }
        }
        return false;
    }

    @Exclude
    public boolean isStraightFlush() {
        if (handSize == 5) {
            if (isFlush() && isStraight()) {
                return true;
            }
        }
        return false;
    }

    @Exclude
    private int getTripNumber(List<Card> cards) {
        for (Card val : cards) {
            int numVal = val.getNumber();
            int count = 0;
            for (Card val2 : cards) {
                if (val2.getNumber() == numVal) {
                    count++;
                }
                if (count == 3) {
                    return numVal;
                }
            }
        }
        return -88;
    }

    @Exclude
    private Card getHighestCard(List<Card> cards) {
        Card high = cards.get(0);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isGreater(high)) {
                high = cards.get(i);
            }
        }
        return high;
    }

    @Exclude
    private List<Card> sortHighToLow(List<Card> cards) {
        List<Card> sorted = new ArrayList<>();
        sorted.add(cards.get(0));

        for (int i = 1; i < cards.size(); i++) {
            for (int hi = 0; hi < sorted.size(); hi++) {
                if (cards.get(i).isGreater(sorted.get(hi))) {
                    sorted.add(hi, cards.get(i));
                    break;
                } else if (hi + 1 == sorted.size()) {
                    sorted.add(cards.get(i));
                    break;
                }
            }
        }
        return sorted;
    }

    public List<Card> getCards() {
        return sortHighToLow(cards);
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        handSize = cards.size();
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "\ncards=" + cards +
                "\n handSize=" + handSize +
                '}';
    }
}
