package Model;

import java.util.HashSet;

public class Player {
    HashSet<Card> Hand;
    HashSet<Card> Scored;

    public Player()
    {
        Hand=null;
        Scored=null;
    }

    public Card Play(Integer selected)
    {
        Card ret=(Card)Hand.toArray()[selected];
        Hand.remove(ret);
        return ret;
    }

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



    public Integer Score()
    {
        Integer ret=0;
        for (Card card: Scored) {
            ret+=card.cardFace.value;
        }
        return ret;
    }
}
