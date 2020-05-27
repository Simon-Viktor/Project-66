package Model;

public class CPUPlayer extends Player implements IRandomSetElement {

    public Card pickCard()
    {
        return IRandomSetElement.getRandomElement(restrictedHand());
    }
}
