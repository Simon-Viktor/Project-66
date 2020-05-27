package Model;

import java.util.HashSet;

public class Deck implements IRandomSetElement {
    Boolean isClosed;
    HashSet<Card> deck;
    Card trump;

    public Deck()
    {
        deck=new HashSet<>();
        trump=null;
        isClosed=false;
    }

    public Card Draw()
    {
        Card ret=null;
        for (Card card: deck) {
            ret=card;
            break;
        }
        deck.remove(ret);
        return ret;
    }

    public CardColour trumpColour()
    {
        return trump.cardColour;
    }

    public void Shuffle(HashSet<Card> unshuffledDeck)
    {
        deck=new HashSet<Card>();
        Integer goal=unshuffledDeck.size();
        for(int i=0;i!=goal;i++)
        {
            Card card=IRandomSetElement.getRandomElement(unshuffledDeck);
            deck.add(card);
            unshuffledDeck.remove(card);
        }
    }

    public void HitTrump() {
        trump=Draw();
    }
}
