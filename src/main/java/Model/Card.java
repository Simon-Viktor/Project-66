package Model;

/**
 * Class for describing a Card in the game.
 */
public class Card {
    public CardColour cardColour;
    public CardFace cardFace;
    public Card(CardColour cC, CardFace cN)
    {
        cardColour=cC;
        cardFace =cN;
    }

    /**
     * Returns the value of the given card.
     * @return the value of the card.
     */
    public Integer Value()
    {
        return this.cardFace.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Card) && (((Card) obj).cardColour == this.cardColour)&&(((Card) obj).cardFace == this.cardFace)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return cardColour.hashCode()+ cardFace.value;
    }
}
