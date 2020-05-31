package Model;

/**
 * Wrapper class for handling the total score across all games.
 */
public class Score {
    public Integer playerScore;
    public Integer CPUScore;

    public Score(Integer player, Integer CPU)
    {
        playerScore=player;
        CPUScore=CPU;
    }
}
