package Model;

/**
 * Extension of a player to allow for automated behaviour
 */
public class CPUPlayer extends Player implements IRandomSetElement {

    /**
     * Picks a card according to the rules and preset picking priorities.
     * @return the picked card.
     */
    public Card pickCard()
    {
        //TODO - potentially overhaul this to be smarter
        Card ret= IRandomSetElement.getRandomElement(restrictedHand());
        Hand.remove(ret);
        return ret;
    }
}
