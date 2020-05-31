package Model;

/**
 * Wrapper class for trick results.
 */
public class TrickResult {
    public Card card1;
    public Card card2;
    public PlayerEnum scorer;
    public TrickResult(Card card1, Card card2, PlayerEnum scorer)
    {
        this.card1=card1;
        this.card2=card2;
        this.scorer=scorer;
    }
}
