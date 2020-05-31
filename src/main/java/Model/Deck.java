package Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashSet;
import java.util.Stack;

/**
 * Class for managing the functions of the deck.
 */
public class Deck implements IRandomSetElement {
    public Boolean isClosed;
    public Stack<Card> deck;
    public Card trump;
    ObjectProperty<CardColour> trumpColour;

    public Deck()
    {
        deck=new Stack<>();
        trump=null;
        isClosed=false;
        trumpColour=new SimpleObjectProperty<CardColour>();
    }

    /**
     * Returns the top card of the deck.
     * @return a {@code Card}.
     */
    public Card Draw()
    {
        Card ret=null;
        if(deck.size()>0) {
            for (Card card : deck) {
                ret = card;
                break;
            }
            deck.remove(ret);
        }
        else
        {
            ret=trump;
            trump=null;
            isClosed=true;
        }
        return ret;
    }

    /**
     * Using the provided cards, shuffles the deck into a randomized order.
     * @param unshuffledDeck the hashset of all cards in teh game.
     */
    public void Shuffle(HashSet<Card> unshuffledDeck)
    {
        deck=new Stack<Card>();
        Integer goal=unshuffledDeck.size();
        for(int i=0;i!=goal;i++)
        {
            Card card=IRandomSetElement.getRandomElement(unshuffledDeck);
            deck.add(card);
            unshuffledDeck.remove(card);
        }
    }

    /**
     * Reveals the trump card, determining it's colour for the rest of the game.
     */
    public void HitTrump() {
        trump=Draw();
        trumpColour.set(trump.cardColour);
    }
}
