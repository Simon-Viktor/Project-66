package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashSet;


/**
 * Class describing a human player of the game.
 */
public class Player {
    public HashSet<Card> Hand;
    public HashSet<Card> Scored;

    public CardColour ColourRestriction;
    public Boolean PairRestriction;
    public ObjectProperty<CardColour> TrumpColour;
    public Integer ExtraScore;

    public Player()
    {
        Hand=new HashSet<Card>();
        Scored=new HashSet<Card>();
        ColourRestriction=null;
        PairRestriction=false;
        TrumpColour=new SimpleObjectProperty<CardColour>();
        ExtraScore=0;
    }

    /**
     * returns a card in a given position and removes it from the hand, if the player can play it.
     * @param selected
     * @return
     */
    public Card Play(Integer selected)
    {
        Card ret=(Card)Hand.toArray()[selected];
        if(restrictedHand().contains(ret))
        {
            Hand.remove(ret);
            return ret;
        }
        return null;
    }

    /**
     * Returns the card in a particular position of the player's hand.
     * @param target The position of the needed card
     * @return
     */
    public Card cardInHand(Integer target)
    {
        return (Card)(Hand.toArray()[target-1]);
    }

    /**
     * Returns the set of colours that the player has a pair of.
     * @return
     */
    public HashSet<CardColour> HasPair()
    {
        HashSet<CardColour> ret=new HashSet<CardColour>();
        for (CardColour colour: CardColour.values()) {
            var king=new Card(colour, CardFace.King);
            var upper=new Card(colour, CardFace.Upperknave);
            if(Hand.contains(king)&&Hand.contains(upper)) ret.add(colour);
        }
        return ret;
    }

    /**
     * Scores the cards contained in the {@code TrickResult}
     * @param result
     * @return returns true if the player has won the game as a result of scoring the cards.
     */
    public Boolean ScoreCards(TrickResult result)
    {
        Scored.add(result.card1);
        Scored.add(result.card2);
        if(Score()>=66)return true;
        return false;
    }

    /**
     * Adds a drawn card to the player's hand
     * @param card
     */
    public void Draw(Card card){
        Hand.add(card);
    }

    /**
     * Returns which cards in a player's hand can be played.
     * @return
     */
    protected HashSet<Card> restrictedHand()
    {
        HashSet<Card> ret=new HashSet<Card>();
        if(ColourRestriction!=null)
        {
            for (Card card : Hand) {
                if (card.cardColour == ColourRestriction) ret.add(card);
            }
            if(PairRestriction)
            {
                ret.removeIf(card -> !(card.cardFace == CardFace.King || card.cardFace == CardFace.Upperknave));
            }
            else if(ColourRestriction!=TrumpColour.get())
            {
                for (Card card : Hand) {
                    if(card.cardColour==TrumpColour.get()) ret.add(card);
                }
            }
        }
        if(ret.size()==0)ret=Hand;
        return ret;
    }

    /**
     * Returns the score of the player
     * @return
     */
    public Integer Score()
    {
        Integer ret=0;
        for (Card card: Scored) {
            ret+=card.cardFace.value;
        }
        ret+=ExtraScore;
        return ret;
    }
}
