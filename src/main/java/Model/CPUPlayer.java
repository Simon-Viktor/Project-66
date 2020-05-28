package Model;

public class CPUPlayer extends Player implements IRandomSetElement {

    public Card pickCard()
    {
        //TODO - potentially overhaul this to be smarter
        Card ret= IRandomSetElement.getRandomElement(restrictedHand());
        Hand.remove(ret);
        return ret;
    }
}
