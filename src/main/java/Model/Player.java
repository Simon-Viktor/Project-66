package Model;

import java.util.HashSet;

public class Player {
    public HashSet<Card> Hand;
    public HashSet<Card> Scored;

    public CardColour ColourRestriction;
    public Boolean PairRestriction;

    public Player()
    {
        Hand=new HashSet<Card>();
        Scored=new HashSet<Card>();
        ColourRestriction=null;
        PairRestriction=false;
    }

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

    public Card cardInHand(Integer target)
    {
        return (Card)(Hand.toArray()[target-1]);
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

    public void Draw(Card card){
        Hand.add(card);

    }

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
        }
        if(ret.size()==0)ret=Hand;
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
